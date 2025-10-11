package activity;

import com.qk.common.PageResult;
import com.qk.entity.Activity;
import com.qk.management.QkManagementApplication;
import com.qk.management.mapper.ActivityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 18:13
 * @Description:
 */
@SpringBootTest(classes = QkManagementApplication.class)
public class ActivityMapperTests {
    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void testSelect() {
        Activity build = Activity.builder()
                .id(1)
                .build();
        Activity activity = activityMapper.selectById(build.getId());
    }

    @Test
    public void testInsert() {
        Activity build = Activity.builder()
                .channel(1)
                .name("测试")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .description("测试")
                .type(1)
                .discount(0.9)
                .voucher(10)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        int insert = activityMapper.insert(build);
        System.out.println(insert);
    }

    @Test
    public void testUpdate() {
        Activity updateActivity = Activity.builder()
                .id(13)
                .updateTime(LocalDateTime.now())
                .description("我是修改后的")
                .build();
        int update = activityMapper.updateById(updateActivity);
        System.out.println(update);
    }

    @Test
    public void testDelete() {
        int delete = activityMapper.deleteById(13);
        System.out.println(delete);
    }
}
