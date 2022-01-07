package com.mycompany.book.service.author;

import com.mycompany.domain.logic.IHaveUri;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @see <a href="https://www.amitph.com/spring-boot-configuration-properties/#Reading_Simple_Properties">other examples</a>
 */
@Configuration
@ConfigurationProperties("api.author")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorApiInfo implements IHaveUri {
    private String uri;
}
