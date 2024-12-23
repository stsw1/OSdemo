package com.tject.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tject.common.JsonResult;
import com.tject.common.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    //执行时机：在执行controller方法之前执行preHandle，
    // 所以可以校验用户是否登录，已经登录过了，放行，未登录就返回未登录，阻止访问controller的方法
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //校验token
        //获取token（请求头中获取token，定义key是token，value是token的值）
        String tokenValue = request.getHeader("token");
        if (tokenValue != null && !"".equals(tokenValue.trim())) {
            try {
                JwtTokenUtil.parseJwt(tokenValue);
                //放行
                return true;
            } catch (Exception e) {
                log.error("解析token异常，{}", e.getMessage());
            }
        }
        //解决中文乱码
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        //返回json 未登录或者登录过期
        JsonResult result = JsonResult.fail(-1, "未登录或者登录已过期，请重新登录");
        //使用jackson的ObjectMapper对象把JsonResult对象转换成json字符串
        String json = objectMapper.writeValueAsString(result);
        //把json字符串输出到前端
        response.getWriter().write(json);
        return false;
    }
}
