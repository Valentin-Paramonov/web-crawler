package paramonov.valentine.web_crawler.site_checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "paramonov.valentine.web_crawler.site_checker" })
class SiteCheckConfig extends WebMvcConfigurerAdapter {
    @Autowired
    public void configureHandlerAdapter(RequestMappingHandlerAdapter handlerAdapter) {
        List<HandlerMethodArgumentResolver> oldArgumentResolvers = handlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(oldArgumentResolvers);
        List<HandlerMethodArgumentResolver> argumentResolvers = Collections.unmodifiableList(newArgumentResolvers);
        handlerAdapter.setArgumentResolvers(argumentResolvers);
    }
}
