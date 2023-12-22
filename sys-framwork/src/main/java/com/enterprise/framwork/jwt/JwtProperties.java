package com.enterprise.framwork.jwt;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt相关配置
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String header;

    private String secret;

    private Long expiration;

    private String authPath;

    private String md5Key;

    public List<String> ignoreUrl;

}
