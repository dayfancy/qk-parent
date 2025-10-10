package com.qk.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 20:22
 * @Description: optimize code  -> config aliYun oss
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliYunOSSProperties {
    private String endpoint;
    private String bucketName;
    private  String region;
}
