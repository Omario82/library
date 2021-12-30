package com.mycompany.book.service.author;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties("api")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiAuthorProperties {
    String authorUri;
}
