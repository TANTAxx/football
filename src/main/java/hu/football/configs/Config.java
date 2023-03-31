package hu.football.configs;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("backend")
public class Config {

    private Football football;



    @Getter
    @Setter
    public static class Football {
        private String secretUser;
        private String secretPass;
    }
}
