package cn.lmcw.becomic.user.service;

import cn.lmcw.becomic.user.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Insert;
import org.wildfly.common.annotation.NotNull;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dingyong
 * @since 2020-06-02
 */
public interface IUserService extends IService<User> {

    User findUserByNameAndPassword(String uname, String password);

    User findUserByUid(int uid);

    int hasUser(String uname);

    String getToken(User user);

    boolean register(String uname, String password, @NotNull Map<String, Object> extras);

}
