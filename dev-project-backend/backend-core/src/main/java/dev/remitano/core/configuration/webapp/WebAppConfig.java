package dev.remitano.core.configuration.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;

@SuppressWarnings("deprecation")
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("/**/*")
        .addResourceLocations("classpath:/static/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(String resourcePath,
              Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);
            return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                : new ClassPathResource("/static/index.html");
          }
        });
  }

  @Bean
  public ClassLoaderTemplateResolver templateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/static/");
    templateResolver.setCacheable(true);
    templateResolver.setSuffix(".html");
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setCharacterEncoding("UTF-8");
    return viewResolver;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }

}
