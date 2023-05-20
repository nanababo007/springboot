package com.kdhppo.smplcms.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kdhppo.smplcms.cmn.PageItc;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${user.upload.path}")
    String userUploadPath;
    @Value("${user.static.css}")
    String userStaticCss;
    @Value("${user.static.images}")
    String userStaticImages;
    @Value("${user.static.js}")
    String userStaticJs;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageItc())
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/upload/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations(userUploadPath);
        registry.addResourceHandler("/css/**").addResourceLocations(userStaticCss);
        registry.addResourceHandler("/images/**").addResourceLocations(userStaticImages);
        registry.addResourceHandler("/js/**").addResourceLocations(userStaticJs);
    }

}