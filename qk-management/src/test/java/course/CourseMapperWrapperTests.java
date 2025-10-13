package course;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qk.entity.Activity;
import com.qk.entity.Course;
import com.qk.management.QkManagementApplication;
import com.qk.management.mapper.ActivityMapper;
import com.qk.management.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/13 11:35
 * @Description: // eq  equals 等于 =
         *      // lt  less than  小于 <
         *     // gt   grate than  大于 >
         *    // le  less than or equals  小于等于 <=
         *   // ge  grate than or equals  大于等于 >=
         */
@Component
@MapperScan("com.qk")
@SpringBootTest(classes = QkManagementApplication.class)
public class CourseMapperWrapperTests {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void testActivityMapperWrapper1() {
        //将id为4,5,6的活动折扣统一上调1
        activityMapper.update(Wrappers.lambdaUpdate(Activity.class)
                .setSql("discount = discount + 1")
                .in(Activity::getId, 4, 5, 6)
        );
    }

    @Test
    public void testActivityMapperWrapper3() {
        // 将id为12，16的活动的discount改成8
        activityMapper.update(Wrappers.lambdaUpdate(Activity.class)
                .set(Activity::getDiscount, 8)
                .in(Activity::getId, 12, 16)
        );

    }

    @Test
    public void testCourseMapperWrapper4() {
        //根据课程的 学科 subject、名称 name、课程适用人群 target 删除课程
        courseMapper.delete(Wrappers.lambdaQuery(Course.class)
                .eq(Course::getId, 14)
        );
    }


    @Test
    public void testCourseMapperWrapper2() {
        //根据课程的 学科 subject、名称 name、课程适用人群 target 查询课程的 id、名称、价格、适用人群、课程描述 字段信息，并根据更新时间 update_time 降序排序
        courseMapper.selectList(Wrappers.lambdaQuery(Course.class)
                .eq(Course::getSubject, 1)
                .like(Course::getName, "AI")
                .eq(Course::getTarget, 2)
                .select(Course::getId, Course::getName, Course::getPrice, Course::getTarget, Course::getDescription)
                .orderByDesc(Course::getUpdateTime)
        ).forEach(System.out::println);
    }

    @Test
    public void testCourseMapperWrapper1() {
        courseMapper.selectList(Wrappers.lambdaQuery(Course.class)
                .eq(Course::getSubject, 1)
                .like(Course::getName, "AI")
                .eq(Course::getTarget, 2)
                .orderByDesc(Course::getUpdateTime)
        ).forEach(System.out::println);

    }


}
