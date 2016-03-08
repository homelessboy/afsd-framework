package me.afsd.site.base.handler;

import com.alibaba.fastjson.JSON;
import me.afsd.site.base.BaseDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: afsd
 * Date: 2016/3/4
 * Time: 16:16
 */
public class BaseExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HandlerMethod method = (HandlerMethod) handler;
        ResponseBody body = method.getMethodAnnotation(ResponseBody.class);
        if (body == null) {
            return doNormalResolveException(request,response,handler,ex);
        } else {
            return doResponseException(request,response,handler,ex);
        }
    }

    protected ModelAndView doNormalResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        return super.doResolveException(request, response, handler, ex);
    }

    protected ModelAndView doResponseException(HttpServletRequest request, HttpServletResponse response,
                                               Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control","no-cache,must-revalidate");
        try {
            response.getWriter().write(JSON.toJSONString(BaseDataResponse.fail().error(ex)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
