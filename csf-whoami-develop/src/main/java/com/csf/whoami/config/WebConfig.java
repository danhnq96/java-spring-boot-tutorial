package com.csf.whoami.config;

import java.util.List;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.csf.whoami.constant.UrlConstants;
import com.csf.whoami.interceptor.AdminInterceptor;

@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private Environment env;

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*").allowedOrigins("*")
                .allowCredentials(false).maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/static/");

        registry.addResourceHandler(
                "/static/sitemap.xml",
                "/static/robots.txt",
                "/css/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/plugin/**",
                "/pageJs/**",
                "/lib/**",
                "/sample/**",
                "/admins/**",
                "/dist/**",
                "/front/**",
                "/files/**",
                "/about/**",
                "/advisor/**",
                "/contact/**",
                "/dist/**",
                "/faq/**",
                "/fonts/**",
                "/footer/**",
                "/header/**",
                "/home/**",
                "/media/**",
                "/project/**",
                "/roadmap/**",
                "/sample/**",
                "/team/**",
                "/vendor/**",
                "/pages/**",
                "/modal/**"
        )
                .addResourceLocations(
                        "classpath:/sitemap.xml",
                        "classpath:/robots.txt",
                        "classpath:/static/css/",
                        "classpath:/static/lib/",
                        "classpath:/static/fonts/",
                        "classpath:/static/js/",
                        "classpath:/static/plugin/",
                        "classpath:/static/sample/",
                        "classpath:/static/admin/",
                        "classpath:/static/dist/",
                        "classpath:/static/front/",
                        "classpath:/static/files/",
                        "classpath:/static/about/",
                        "classpath:/static/advisor/",
                        "classpath:/static/contact/",
                        "classpath:/static/dist/",
                        "classpath:/static/faq/",
                        "classpath:/static/fronts/",
                        "classpath:/static/footer/",
                        "classpath:/static/header/",
                        "classpath:/static/home/",
                        "classpath:/static/media/",
                        "classpath:/static/project/",
                        "classpath:/static/roadmap/",
                        "classpath:/static/sample/",
                        "classpath:/static/team/",
                        "classpath:/static/vendor/",
                        "classpath:/static/pages/",
                        "classpath:/static/pageJs/",
                        "classpath:/static/images/",
                        "classpath:/static/modal/"
                );
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/admin/home");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AdminInterceptor loginIntercepter = new AdminInterceptor();
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");

        // TODO: Multiple language label.
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(UrlConstants.LANGUAGE_PATH);
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Bean
    public DeviceResolverHandlerInterceptor
    deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(UrlConstants.MESSAGE_PATH);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
