package com.mycompany.author.controller;

import com.mycompany.author.domain.HealthHigh;
import com.mycompany.author.domain.HealthLow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    final private String UP = "UP";
    final private String APP_VERSION = "1.0.0";
    @GetMapping("/health")
    public String healthCheck() {
        return "Up";
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
    public HealthLow healthLowCheck() {
        return HealthLow.builder().status(UP).appVersion(APP_VERSION).build();
    }
}
