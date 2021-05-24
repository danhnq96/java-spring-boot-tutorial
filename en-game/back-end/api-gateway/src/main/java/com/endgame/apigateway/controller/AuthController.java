package com.endgame.apigateway.controller;

import com.endgame.apigateway.entity.ERole;
import com.endgame.apigateway.entity.Role;
import com.endgame.apigateway.exception.BadRequestException;
import com.endgame.apigateway.entity.AuthProvider;
import com.endgame.apigateway.entity.User;
import com.endgame.apigateway.payload.response.ApiResponse;
import com.endgame.apigateway.payload.response.AuthResponse;
import com.endgame.apigateway.payload.request.LoginRequest;
import com.endgame.apigateway.payload.request.SignUpRequest;
import com.endgame.apigateway.repository.RoleRepository;
import com.endgame.apigateway.repository.UserRepository;
import com.endgame.apigateway.security.TokenProvider;
import com.endgame.apigateway.services.ReCaptchaService;
import com.endgame.apigateway.util.MySecurity;
import com.endgame.apigateway.util.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private ReCaptchaService reCaptchaService;

  @Autowired
  private SendEmail sendEmail;

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
  private static final String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
    "MIICdAIBADANBgkqhkiG9w0BAQEFAASCAl4wggJaAgEAAoGAc7FGbWBASbrz2kw5\n" +
    "kiLAHj+7ty3y/OIUPJw5GKnIp2fYuHVlvcw6ATD88LTQDA+8mn5ctUHt0G3Ngaoe\n" +
    "UprvBhQ+rimcnBUGusOF1BagIjYtm3CpZINcc7WLTH5ySUMER3J7/UYl3Cb0DovR\n" +
    "+Z7g+jtfoINJMlt1WfKbQMCxuEUCAwEAAQKBgGR+RV22sMEaur0RXyS4OMdNtyHL\n" +
    "sPjG7KslTFdHC7uWceLwXtr5rhzgz6XKebkwZjSQtLjhttBIwKzz93jridk66lkZ\n" +
    "DX2CJHng8YWg1OfjfCMHzH+ouIKdgjrSMQ5rE0DLUX6FwHNdLW+EUlixRMc3eu+K\n" +
    "zQzEJ/KWgTTwCS61AkEA3AZuaeTV1hWhNeAMN3VC6maXjES4GpO8lrF/NWFvWtP3\n" +
    "tzMGuzbpYdse4VOBdea+NLONsyzIQj545nVQaejPDwJBAIabzaLTmo4GoHYHEOWQ\n" +
    "dLmCHnlhbLHsGudxsM8ACelnhYR5dGz/P9dIvG2sQfSCTn+fWM9KNY8r8GcS3p6q\n" +
    "A2sCQALX9m9Z7MWSi/3VQnMpCc9buhOvA64cQLC15I39D2JeIPJ2L10WDZgdK93/\n" +
    "pUmvzL+Dno41QvVQN2HLHFnyD1MCQHgq2vcDRLSJwfHb+P+XaDy7pozhpnFnv9Ti\n" +
    "L997v114pz9OVduAOvPr4RtfbjnjokRYcqYLL6kyppi9xbOLPyUCQAW1bJUwoRiX\n" +
    "CxxHLWVLoPZRlFu1Hi2s2X7OTHii18CxfMkvc47s2pXzBgREeHwWSIwq7x6unJid\n" +
    "b66p14Ad/mU=\n" +
    "-----END PRIVATE KEY-----";
  private static final String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
    "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHOxRm1gQEm689pMOZIiwB4/u7ct\n" +
    "8vziFDycORipyKdn2Lh1Zb3MOgEw/PC00AwPvJp+XLVB7dBtzYGqHlKa7wYUPq4p\n" +
    "nJwVBrrDhdQWoCI2LZtwqWSDXHO1i0x+cklDBEdye/1GJdwm9A6L0fme4Po7X6CD\n" +
    "STJbdVnym0DAsbhFAgMBAAE=\n" +
    "-----END PUBLIC KEY-----";

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    boolean isReCaptchaValid = reCaptchaService.verify(loginRequest.getReCaptchaResponse());

    if (!isReCaptchaValid) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Invalid ReCaptcha!"));
    }

    Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

    if (optionalUser.isPresent() && !optionalUser.get().getActive()) {
      return ResponseEntity.status(HttpStatus.LOCKED).body(new ApiResponse(false, "User is not active!"));
    }

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getEmail(),
        loginRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication);
    return ResponseEntity.ok(new AuthResponse(token));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, MessagingException {
    logger.info("[registerUser] - SignUpRequest {}", signUpRequest);
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Email address is already in " +
        "used!"));
    }

    // Creating user's account
    User user = new User();
    user.setName(signUpRequest.getName());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(signUpRequest.getPassword());
    user.setProvider(AuthProvider.local);
    user.setActivationCode(MySecurity.signSHA256RSA(String.valueOf(System.currentTimeMillis()) + signUpRequest.getPassword(), privateKey));

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (ERole.valueOf(role)) {
          case ROLE_ADMIN:
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case ROLE_MODERATOR:
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    User result = userRepository.save(user);

    String htmlMsg = "<h3>Im testing send a HTML email</h3>"
      + "<a href='http://localhost:8762/api/auth/activate?code="
      + URLEncoder.encode(user.getActivationCode(), StandardCharsets.UTF_8.toString())
      + "'>Click here to activate!!!</a>";

    sendEmail.sendHTMLEmail(user.getEmail(), htmlMsg);

    URI location = ServletUriComponentsBuilder
      .fromCurrentContextPath().path("/user/me")
      .buildAndExpand(result.getId()).toUri();

    return ResponseEntity.created(location)
      .body(new ApiResponse(true, "User registered successfully@"));
  }

  @GetMapping("/activate")
  public RedirectView activateUser(@Valid @RequestParam("code") String code) throws UnsupportedEncodingException {
    logger.info("[activateUser] - code: {}", code);
    Optional<User> optionalUser = userRepository.findByActivationCode(code);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setActivationCode(null);
      user.setActive(true);

      userRepository.save(user);

      return new RedirectView("http://localhost:4200/activate");
    }

    return new RedirectView("http://localhost:4200/sign-in");
  }
}
