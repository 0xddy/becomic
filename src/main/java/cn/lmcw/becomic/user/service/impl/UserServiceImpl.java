package cn.lmcw.becomic.user.service.impl;

import cn.lmcw.becomic.comm.JwtAuth;
import cn.lmcw.becomic.user.entity.User;
import cn.lmcw.becomic.user.mapper.UserMapper;
import cn.lmcw.becomic.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dingyong
 * @since 2020-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Value("${jwt.secret}")
    protected String secret;

    public User findUserByNameAndPassword(String uname, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("uname", uname)
                .eq("password", password)
                .select("uname", "wallet", "id", "options");


        return getOne(queryWrapper, false);
    }

    public User findUserByUid(int uid) {
        User user = getById(uid);
        if (user != null) {
            user.setToken(getToken(user));
        }
        return user;
    }

    @Override
    public int hasUser(String uname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("uname", uname);
        return this.getBaseMapper().selectCount(queryWrapper);
    }

    @Override
    public String getToken(User user) {
        JwtAuth jwtAuth = new JwtAuth();
        return jwtAuth.sign(secret, user.getId() + "%" + System.currentTimeMillis());
    }

    @Override
    public boolean register(String uname, String password) {

        User user = new User();
        user.setUname(uname);
        user.setPassword(password);

        boolean addRet = this.save(user);
        //写入meta数据
        if (addRet) {

        }

        return addRet;
    }

}
