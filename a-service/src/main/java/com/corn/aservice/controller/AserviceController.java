package com.corn.aservice.controller;

import com.corn.aservice.feign.BserviceFeignClient;
import com.corn.security.model.AuthenticAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: AserviceController
 * @description: TODO
 * @date 2022/8/2 10:12
 */


@RestController
public class AserviceController {

    @Autowired
    private BserviceFeignClient bserviceFeignClient;

    //@Value("${service-b}")
    private String serviceUrl;


    @GetMapping("/urlConfig")
    public String serviceUrl(){
        System.out.println("urlConfig");
        return serviceUrl;
    }


    @GetMapping("/invokeb")
    public String invokeb(){
        System.out.println("a invoke b");
        return bserviceFeignClient.invokeB();
    }

    //@Value("${javahome}")
    private String javahome;


    @GetMapping("/invoke")
    public String invoke(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("invoke");
        return "response from svc-a";
}

    @GetMapping("/health")
    public String health() {
        return "success";
    }

    //@RolesAllowed("ROLE_USER")
    @RequestMapping("/who")
    public String who(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2Authentication user = (OAuth2Authentication)authentication;
        Authentication userAuthentication = user.getUserAuthentication();
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken)userAuthentication;
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        AuthenticAccount account = (AuthenticAccount) principal.getPrincipal();
        StringBuffer sb = new StringBuffer();
        authorities.stream().forEach(v->{
            sb.append(v);
            sb.append(",");
        });
        return account.getUsername()+""+sb.toString();
    }
}
