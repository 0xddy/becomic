package cn.lmcw.becomic.conf.interceptors;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends BaseInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (authorization(request, response, 1)) {
            return true;
        } else {
            return false;
        }
    }
}
