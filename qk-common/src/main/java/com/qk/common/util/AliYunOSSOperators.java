package com.qk.common.util;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.qk.common.config.AliYunOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;

@Slf4j
@Component
@SuppressWarnings("all")
public class AliYunOSSOperators {

//    private static final String ENDPOINT = "https://oss-cn-hangzhou.aliyuncs.com";
//    private static final String BUCKET_NAME = "javaweb-rightsquare2025";
//    private static final String REGION = "cn-hangzhou";
      @Autowired
      private AliYunOSSProperties aliYunOSSProperties;

    public String upload(byte[] content, String objectName) throws Exception {
        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = null;
        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint( aliYunOSSProperties.getEndpoint())
                    .credentialsProvider(new EnvironmentVariableCredentialsProvider())
                    .clientConfiguration(clientBuilderConfiguration)
                    .region( aliYunOSSProperties.getRegion())
                    .build();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(aliYunOSSProperties.getBucketName(), objectName, new ByteArrayInputStream(content));
            // 上传文件
            ossClient.putObject(putObjectRequest);

            // 返回文件访问URL
            return "https://" + aliYunOSSProperties.getBucketName() + "." + aliYunOSSProperties.getEndpoint().substring(8) + "/" + objectName;
        } catch (Exception e) {
            log.error("Caught an OSSException: " + e.getMessage());
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}