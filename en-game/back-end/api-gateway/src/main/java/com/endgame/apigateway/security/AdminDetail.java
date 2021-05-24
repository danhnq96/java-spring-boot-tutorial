package com.endgame.apigateway.security;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/26
 * @project: spm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@JsonDeserialize
public class AdminDetail implements UserDetails {
  private String userName;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private Boolean accountNonExpired;
  private Boolean accountNonLocked;
  private Boolean credentialsNonExpired;
  private Boolean enabled;

  @JsonDeserialize(using = CustomAuthorityDeserializer.class)
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}

class CustomAuthorityDeserializer extends JsonDeserializer {

  @Override
  public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    ObjectMapper mapper = (ObjectMapper) jp.getCodec();
    JsonNode jsonNode = mapper.readTree(jp);
    List<GrantedAuthority> grantedAuthorities = new LinkedList<>();

    Iterator<JsonNode> elements = jsonNode.elements();
    while (elements.hasNext()) {
      JsonNode next = elements.next();
      JsonNode authority = next.get("authority");
      grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
    }
    return grantedAuthorities;
  }

}
