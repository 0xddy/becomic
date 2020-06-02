package cn.lmcw.becomic.conf.interceptors;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor extends BaseInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AdminInterceptor " + request.getRequestURI());

        if (authorization(request, response, 0)) {
            return true;
        } else {
            return false;
        }
    }
}
