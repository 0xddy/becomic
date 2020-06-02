package cn.lmcw.becomic.user.controller.api;

import cn.lmcw.becomic.comm.RespWrapper;
import cn.lmcw.becomic.comm.Verifys;
import cn.lmcw.becomic.user.entity.User;
import cn.lmcw.becomic.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserLoginController {

    @Autowired
    IUserService userService;

    /**
     * 用户登录
     *
     * @param uname    用户名
     * @param password 密码（明文）
     * @return User.class
     */
    @GetMapping("/login")
    public Object login(String uname, String password) {

        if (!Verifys.testUserName(uname)) {
            return RespWrapper.error("用户名不合法");
        }

        User user = userService.findUserByNameAndPassword(uname, password);
        if (user != null) {
            String token = userService.getToken(user);
            user.setToken(token);
            return RespWrapper.success(user);
        } else {
            return RespWrapper.error("账号或密码错误");
        }
    }

    @PostMapping("/register")
    public Object register(String uname, String password) {
        if (!Verifys.testUserName(uname)) {
            return RespWrapper.error("用户名不合法");
        }
        // 注册账号
        int count = userService.hasUser(uname);
        if (count > 0) {
            // 用户已存在
            return RespWrapper.error("用户名已存在");
        }



        return RespWrapper.success("注册成功");

    }
}
