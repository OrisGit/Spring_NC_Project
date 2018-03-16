package com.nc.netcracker_project.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.html");
        registry.addViewController("/drugs").setViewName("forward:drugs.html");
        registry.addViewController("/drugstores").setViewName("forward:drugstores.html");
        registry.addViewController("/prices").setViewName("forward:prices.html");
    }

}
