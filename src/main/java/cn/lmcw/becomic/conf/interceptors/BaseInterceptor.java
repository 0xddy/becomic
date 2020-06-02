package cn.lmcw.becomic.conf.interceptors;

import cn.lmcw.becomic.comm.JwtAuth;
import cn.lmcw.becomic.comm.RespWrapper;
import cn.lmcw.becomic.utils.StringUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class BaseInterceptor extends JwtAuth implements HandlerInterceptor {

    @Value("${jwt.secret}")
    protected String secret;

    /**
     * @param request
     * @param response
     * @param from     1 JSON  other web
     * @return
     */
    protected boolean authorization(HttpServletRequest request, HttpServletResponse response, int from) {
        int status;
        String HEADER_AUTH = "Authorization";
        String authorization = request.getHeader(HEADER_AUTH);
        if (!StringUtils.isEmpty(authorization)) {
            String TOKEN_PREFIX = "Bearer";
            String signToken = authorization.replace(TOKEN_PREFIX + " ", "");
            try {
                // 解析用户信息
                String userId = decrypt(secret, signToken).split("%")[0];
                System.out.println("当前请求用户ID " + userId + " , path : " + request.getRequestURI());
                // 写入Session
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("uid", userId);

                status = RespWrapper.RESP_CODE_SUCCESS;
            } catch (ExpiredJwtException e) {
                status = RespWrapper.RESP_CODE_ERROR2;
            } catch (Exception e) {
                status = RespWrapper.RESP_CODE_ERROR3;
            }
        } else {
            status = RespWrapper.RESP_CODE_ERROR4;
        }
        if (status != RespWrapper.RESP_CODE_SUCCESS) {
            // api 返回 登录错误 json
            if (from == 1) {
                String respMgs = "尚未登录";
                if (status == RespWrapper.RESP_CODE_ERROR2) {
                    respMgs = "身份过期，请重新登录";
                } else if (status == RespWrapper.RESP_CODE_ERROR3) {
                    respMgs = "校验身份失败，请重新登录";
                }

                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; charset=UTF-8");

                try (PrintWriter writer = response.getWriter()) {
                    writer.write(new Gson().toJson(RespWrapper.error(status, respMgs)));
                    writer.flush();
                } catch (Exception ignored) {

                }
            } else {
                // web 方式跳转页面
                try {
                    response.sendRedirect("/login");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } else {
            return true;
        }

    }
}
