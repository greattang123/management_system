package com.example.management_system.interceptor;

import com.example.management_system.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    /**
     * 先经过Login拦截器，未登录则不会进入
     * login拦截器已经将权限id注入request attribute，可直接取出，判断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       int aid=(int)request.getAttribute("aid");
       if(aid!= User.ADMIN_AUTHORITY){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN,"无权限");
       }
        return true;
    }
}
