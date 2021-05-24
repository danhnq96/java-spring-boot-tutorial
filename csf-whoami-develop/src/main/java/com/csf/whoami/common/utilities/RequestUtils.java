/**
 *
 */
package com.csf.whoami.common.utilities;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Tuan Dang
 *
 */
public class RequestUtils {

    @SuppressWarnings("unused")
    @Autowired
    private RestTemplate restTemplate;

    public HttpHeaders generateHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }

    public <T> HttpEntity<T> generateRequest(T body) {
        HttpHeaders header = generateHeader();
//		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return new HttpEntity<>(body, header);
    }

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
