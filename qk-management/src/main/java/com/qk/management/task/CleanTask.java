package com.qk.management.task;

import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyuncs.exceptions.ClientException;
import com.qk.common.util.AliYunOSSOperators;
import com.qk.entity.User;
import com.qk.management.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: RightSquare
 * @Date: 2025/10/12 18:38
 * @Description:
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class CleanTask {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AliYunOSSOperators aliYunOSSOperators;

    private static final String ALIYUN_OSS_PREFIX = "https://javaweb-rightsquare2025.oss-cn-hangzhou.aliyuncs.com/";
    @Scheduled(cron = "0 0 12 ? * ? ")
    public void clean() throws ClientException {
        // 获取所有用户
        List<User> userList = userMapper.selectList( null);
        //https://javaweb-rightsquare2025.oss-cn-hangzhou.aliyuncs.com/65c3a5a9-02e5-4799-9834-b328027daea0.png
        List<OSSObjectSummary> aliyunFiles = aliYunOSSOperators.listFiles();
        // 遍历阿里云文件列表
        for (OSSObjectSummary aliyunFile : aliyunFiles) {
            String deleteFile = aliyunFile.getKey();
            //要比对的文件名
            String fileName = ALIYUN_OSS_PREFIX+ aliyunFile.getKey();
            // 检查该文件是否被任何用户引用
            boolean isFileInUse = false;
            for (User user : userList) {
                String userImage = user.getImage();
                if (fileName.equals(userImage)) {
                    isFileInUse = true;
                    break;
                }
            }
            // 如果文件没有被任何用户引用，则删除该文件
            if (!isFileInUse) {
                aliYunOSSOperators.delete(deleteFile);
                log.info("Deleted unused file: {}", deleteFile);
            }
        }
    }

}
