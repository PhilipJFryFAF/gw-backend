package com.faforever.gw;

import io.katharsis.spring.boot.KatharsisConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@SpringBootApplication
@Import(KatharsisConfigV2.class)
public class GalacticWarConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(GalacticWarConfiguration.class, args);
    }
}
