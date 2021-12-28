package com.mycompany.author.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HealthLow {
    private String status;
    private String appVersion;
}
