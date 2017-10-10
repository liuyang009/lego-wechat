package com.harmontronics.lego.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:07
 */
@Service
public class LocaleMessageSourceService {
    private static String DEFAULT_LANG = "zhCN";
    private static String LANG;

    private static Map<String, Locale> _LangMap = new HashMap<String, Locale>(){{
        put("zhcn", Locale.SIMPLIFIED_CHINESE);
        put("en_us", Locale.US);
        put("de",Locale.GERMANY);
    }};

    public LocaleMessageSourceService(){
        LANG = DEFAULT_LANG;
    }

    @Resource
    private MessageSource messageSource;

    public void setLang(String lang){
        if(lang == null){
            LANG = DEFAULT_LANG;
            return;
        }

        if(lang.equals(LANG)) return;

        Locale locale = _LangMap.get(lang);
        if(locale != null){
            LANG = lang;
        }else{
            LANG = DEFAULT_LANG;
        }
    }

    public String getMessage(String code){
        return getMessage(code,null);
    }

    public String getMessage(String code,Object[] args){
        return getMessage(code, args,"");
    }

    public String getMessage(String code,Object[] args,String defaultMessage){
        return messageSource.getMessage(code, args, defaultMessage, _LangMap.get(LANG.toLowerCase()));
    }

    public String getErrorMessage(String errorCode){
        return getMessage("ds.config.error." + errorCode);
    }
}
