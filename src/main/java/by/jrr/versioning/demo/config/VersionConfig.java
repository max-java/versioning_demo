package by.jrr.versioning.demo.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.constraints.NotNull;

//this reads properties from application properties and set in into actuator/info
@Configuration
@ConfigurationProperties(prefix = "application")
public class VersionConfig {
    @NotNull
    private String version;
    //getter and setter here

    @Bean
    public InfoContributor versionInfoContributor(VersionConfig versionConfig) {

        return builder -> {
            builder.withDetail("version", versionConfig.getVersion());
            builder.withDetail("ImplementationVersion", this.getClass().getPackage().getImplementationVersion());
        };
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
