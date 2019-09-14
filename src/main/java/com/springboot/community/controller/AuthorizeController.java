package com.springboot.community.controller;

import com.springboot.community.dto.AccessTokenDTO;
import com.springboot.community.dto.GithubUser;
import com.springboot.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO atdto = new AccessTokenDTO();
        atdto.setClient_id(clientId);
        atdto.setClient_secret(clientSecret);
        atdto.setCode(code);
        atdto.setRedirect_url(redirectUrl);
        atdto.setState(state);
        String accessToken = githubProvider.getAccessToken(atdto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
