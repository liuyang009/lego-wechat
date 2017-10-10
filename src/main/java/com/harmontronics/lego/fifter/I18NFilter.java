package com.harmontronics.lego.fifter;

import com.harmontronics.lego.service.LocaleMessageSourceService;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soul on 2017/6/8.
 */
@Order(1)   //@Order注解表示执行过滤顺序，值越小，越先执行
@WebFilter(filterName = "i18nFilter", urlPatterns = "/api/v1/*")
public class I18NFilter implements Filter {
    private static final String LANG = "lang";

    /**
     * 消息服务类
     */
    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    private Map<String, String> getHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private String getHeaderParameter(HttpServletRequest request, String key){
        Map<String, String> headerMap = getHeadersMap(request);
        return headerMap.get(key);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String lang = getHeaderParameter(request, LANG);

        if(lang == null || lang.isEmpty()) {
            lang = request.getParameter(LANG);
        }
        if (lang == null)
            return;
        localeMessageSourceService.setLang(lang.toLowerCase());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
