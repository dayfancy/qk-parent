package oss;

import com.qk.management.util.AliYunOSSOperators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 13:59
 * @Description: Tests
 */
@SpringBootTest(classes = AliYunOSSOperators.class)
public class AliYunOSSOperatorsTests {
    @Autowired
    private AliYunOSSOperators oss;

    @Test
    public void testUpload() throws Exception {
        String url = oss.upload("hello world".getBytes(), "test.txt");
        System.out.println(url);
    }
}
