package com.mycompany.book.service.author;

import com.mycompany.book.service.common.IHaveUri;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @see <a href="https://www.amitph.com/spring-boot-configuration-properties/#Reading_Simple_Properties">other examples</a>
 */
@Component
@ConfigurationProperties("api.author")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorApiInfo implements IHaveUri {
    private String uri;
}
