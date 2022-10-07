package com.dalsom.management.common.config;

import com.dalsom.management.common.PageParameter;
import com.dalsom.management.common.PasswordEncoderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class SpringInitializeConfig implements WebMvcConfigurer {

    private final PasswordEncoder encoder;

    public SpringInitializeConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageParameter.PageParameterArgumentResolver());
    }

    @PostConstruct
    public void init() {
        PasswordEncoderFactory.addEncoder(PasswordEncoderFactory.DEFAULT_ENCODER_NAME, encoder);
    }
}
