package com.mycompany.author.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Health {
    private String status;
    private String appVersion;
    private String domain;
}
