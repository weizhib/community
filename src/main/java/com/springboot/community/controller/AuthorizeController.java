package com.springboot.community.controller;

import com.springboot.community.dto.AccessTokenDTO;
import com.springboot.community.dto.GithubUser;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.User;
import com.springboot.community.provider.GithubProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO atdto = new AccessTokenDTO();
        atdto.setClient_id(clientId);
        atdto.setClient_secret(clientSecret);
        atdto.setCode(code);
        atdto.setRedirect_url(redirectUrl);
        atdto.setState(state);
        String accessToken = githubProvider.getAccessToken(atdto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            //登录成功
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_Url());
            userMapper.insert(user);
            //写入cookie和session
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("githubUser", githubUser);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
