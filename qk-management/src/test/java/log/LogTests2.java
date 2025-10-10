package log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Author: RightSquare
 * @Date: 2025/9/30 16:06
 * @Description:
 */
@Slf4j
@SuppressWarnings("all")
public class LogTests2 {
    @Test
    public void testLog(){
        log.info("这是info日志");
        String msg1 ="我是第一条信息";
        String msg2 ="我是第二条信息";
        log.info("错误信息1是：{}错误信息2是：{}",msg1,msg2);
    }

}
