package cn.lmcw.becomic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String save(){
//        User user = new User();
//        user.setId(1);
//        user.setUname("admin");
//        user.setPassword("1234562");
//        userMapper.insert(user);
        return "1";
    }

}
