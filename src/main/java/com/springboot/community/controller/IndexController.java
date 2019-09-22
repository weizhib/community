package com.springboot.community.controller;

import com.springboot.community.mapper.QuestionMapper;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.Question;
import com.springboot.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<Question> questionList = questionMapper.list();
        model.addAttribute("questions", questionList);
        return "index";
    }


}
