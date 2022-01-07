package com.mycompany.author.controller;

import com.mycompany.domain.model.HealthHigh;
import com.mycompany.domain.model.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    final private String UP = "UP";
    final private String APP_VERSION = "1.0.0";
    final private String DOMAINE_NAME = "Author";

    @GetMapping("/health")
    public Health healthCheck() {
        return healthLowCheck();
    }

    @GetMapping("/healthHigh")
    public HealthHigh healthHighCheck()
    {
        HealthHigh healthHigh = new HealthHigh();
        healthHigh.setStatus(UP);
        healthHigh.setAppVersion(APP_VERSION);
        return healthHigh;
    }

    @GetMapping("/healthLow")
    public Health healthLowCheck() {
        return Health.builder().status(UP).appVersion(APP_VERSION).domain(DOMAINE_NAME).build();
    }
}
