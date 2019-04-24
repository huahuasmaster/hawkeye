package com.zyz.hawkeye.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfiguer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/holmeslog/**").addResourceLocations("file:" + SystemConfig.getProperty("holmeslog.path"));
//        registry.addResourceHandler("/processorlog/**").addResourceLocations("file:" + SystemConfig.getProperty("processorlog.path"));
//        registry.addResourceHandler("/installtar/**").addResourceLocations("file:" + SystemConfig.getProperty("installtar.path"));
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/"); //重新指定spring boot的静态资源处理前缀
        super.addResourceHandlers(registry);
    }
}
