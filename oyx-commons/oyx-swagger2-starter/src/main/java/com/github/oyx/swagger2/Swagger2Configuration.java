package com.github.oyx.swagger2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.xiaoymin.swaggerbootstrapui.configuration.SwaggerBootstrapUIConfiguration;
import com.github.xiaoymin.swaggerbootstrapui.filter.ProductionSecurityFilter;
import com.github.xiaoymin.swaggerbootstrapui.filter.SecurityBasicAuthFilter;

import lombok.extern.slf4j.Slf4j;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 启动类
 * 启动条件：
 * 1，配置文件中oyx.swagger.enabled=true
 * 2，配置文件中不存在：oyx.swagger.enabled 值
 * @author OYX
 * @date 2019-12-7 16:09
 */
@Configuration
@ConditionalOnProperty(name = "oyx.swagger.enabled", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class, SwaggerBootstrapUIConfiguration.class, Swagger2Configuration.OyxSecurityConfiguration.class})
public class Swagger2Configuration implements WebMvcConfigurer {

    /**
     * spring boot 不会自动注入swagger的资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Slf4j
    public static class OyxSecurityConfiguration{

        /**
         * 生产环境安全策略
         * @param swaggerProperties
         * @return
         */
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = "oyx.swagger.production", havingValue = "true")
        public ProductionSecurityFilter productionSecurityFilter(SwaggerProperties swaggerProperties){
            return new ProductionSecurityFilter(swaggerProperties.getProduction());
        }

        /**
         * 接口文档密码设置
         * @param swaggerProperties
         * @return
         */
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = "oyx.swagger.basic.enable", havingValue = "true")
        public SecurityBasicAuthFilter securityBasicAuthFilter(SwaggerProperties swaggerProperties) {
            SwaggerProperties.Basic basic = swaggerProperties.getBasic();
            return new SecurityBasicAuthFilter(basic.getEnable(), basic.getUsername(), basic.getPassword());
        }
    }
}
