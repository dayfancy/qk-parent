package log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * @Author: RightSquare
 * @Date: 2025/9/30 16:01
 * @Description:
 */
@Slf4j
public class LogTests {
    //定义日志记录对象

    @Test
    public void testLog(){
        log.debug("开始计算...");
        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        log.info("计算结果为: {}", sum);
        log.debug("结束计算...");
        log.info("info0000000000000000000000000000000");    // 记录普通信息
        log.debug("debug00000000000000000000000000000");    // 记录调试信息
        log.error("error000000000000000000000000000000");   // 记录错误信息
        log.warn("warn00000000000000000000000000000000");   // 记录警告信息
        log.trace("trace000000000000000000000000000000");   // 记录堆栈信息
        // log.fatal("fatal");  // 致命错误，虚拟机都来不及处理
    }
}
