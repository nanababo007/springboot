package com.kdhppo.smplcms.cfg;

import org.springframework.beans.factory.annotation.Autowired;
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

    //인터셉터
    //new 로 생성해서 인터셉터를 설정하면, 스프링 빈을 주입받지 못함.
    //이렇게 자동 주입받아서, 인터셉터를 생성해서, 등록 설정을 해줘야,
    //해당 인터셉터에서 스프링 빈이 자동 주입이 가능해짐.
    @Autowired
    PageItc pageItc;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageItc)
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