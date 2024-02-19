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
        private String secretAdmin;
        private String secretAdminPass;
    }

    @Getter
    @Setter
    public static class Jwt{
        private String username;
        private String password;
        private String secret;
        private Long validTo;
    }
}
