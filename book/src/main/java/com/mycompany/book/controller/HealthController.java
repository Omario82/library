package book.controller;

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
}
