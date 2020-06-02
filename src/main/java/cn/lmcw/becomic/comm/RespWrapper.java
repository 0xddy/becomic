package cn.lmcw.becomic.comm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RespWrapper {

    public static final int RESP_CODE_SUCCESS = 200;

    // 没有权限
    public static final int RESP_CODE_ERROR1 = 301;
    // Token过期
    public static final int RESP_CODE_ERROR2 = 500;
    // 未登录
    public static final int RESP_CODE_ERROR4 = 400;
    // 解析异常错误
    public static final int RESP_CODE_ERROR3 = 3001;


    public static Object send(int code, String msg, Serializable data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", code);
        map.put("msg", msg == null ? "" : msg);
        if (data != null) {
            map.put("data", data);
        }
        return map;
    }

    public static Object success(String msg, Serializable data) {
        return send(RESP_CODE_SUCCESS, msg, data);
    }

    public static Object success(String msg) {
        return send(RESP_CODE_SUCCESS, msg, null);
    }

    public static Object success(Serializable data) {
        return success("操作成功", data);
    }

    public static Object error(int code, String msg) {
        return send(code, msg, null);
    }

    public static Object error(String msg) {
        return send(RespWrapper.RESP_CODE_ERROR1, msg, null);
    }

}
