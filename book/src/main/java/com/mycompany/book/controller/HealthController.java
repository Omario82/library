package com.mycompany.book.controller;

import com.mycompany.domain.model.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    final private String UP = "UP";
    final private String APP_VERSION = "1.0.0";
    final private String DOMAINE_NAME = "Book";

    @GetMapping("/health")
    public Health healthLowCheck() {
        return Health.builder().status(UP).appVersion(APP_VERSION).domain(DOMAINE_NAME).build();
    }
}
