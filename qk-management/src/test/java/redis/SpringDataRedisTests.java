package redis;

import com.qk.management.QkManagementApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: RightSquare
 * @Date: 2025/10/16 11:21
 * @Description:
 *  1、演示基于spring data redis 操作redis
 * 核心对象：
 *  1.1  RedisTemplate 早期操作Redis对象需要【手动】指定序列化器和反序列化器
 *  1.2、StringRedisTemplate 默认使用StringRedisSerializer进行序列化和反序列化 无需人工参与【推荐使用！！！！】
 *  2、 使用操作redis 之前 明确操作数据类型！
 *   - 字符串
 *   - 列表
 *   - 哈希
 *   - 集合
 *   - 有序集合
 *  风格1
 *       redisClient.opsForValue() // 操作字符串
 *       redisClient.opsForHash()  // 操作hash
 *       redisClient.opsForList()  // 操作list
 *       redisClient.opsForSet()   // 操作set
 *       redisClient.opsForZSet()  // 操作zset
 *  风格2
 *       redisClient.boundValueOps(); // 操作字符串
 *       redisClient.boundHashOps();  // 操作hash
 *       redisClient.boundListOps();  // 操作list
 *       redisClient.boundSetOps();   // 操作 set
 *       redisClient.boundZSetOps();  // 操作zset
 */
@SpringBootTest(classes = QkManagementApplication.class)
public class SpringDataRedisTests {
    @Autowired
    private StringRedisTemplate redisClient;


    @Test
    public void testStringRedisTemplate() {
        redisClient.opsForValue().set("name", "刘亦菲");
        String name = redisClient.opsForValue().get("name");
        System.out.println( name);
        redisClient.opsForValue().set("age", "18");
        Map<String, String> map = new HashMap<>();
        map.put("name", "阮秀");
        map.put("age", "18");
        map.put("sex", "女");
        map.put("jineng", "天开神秀");
        redisClient.opsForValue().multiSet(map);
        List<String> strings = redisClient.opsForValue().multiGet(map.keySet());
        System.out.println(strings);
        redisClient.opsForValue().increment("age", 1);
        redisClient.opsForValue().increment("age", 5);
        long offset1 = 100;
        redisClient.opsForValue().set("gouba", "18",offset1);
        TimeUnit timessss = TimeUnit.SECONDS;
        redisClient.opsForValue().set("dress", "green",offset1, timessss);
    }

    @Test
    public void testHashRedisTemplate() {
        redisClient.opsForHash().put("user:1", "name", "刘亦菲");
        Object name = redisClient.opsForHash().get("user:1", "name");
        System.out.println(name);
        redisClient.opsForHash().put("user:1", "age", "18");
        redisClient.opsForHash().put("user:1", "sex", "女");
        redisClient.opsForHash().put("user:1", "jineng", "天开神秀");

        redisClient.opsForHash().put("user:2", "boys","doing");
        Map<Object, Object> map = redisClient.opsForHash().entries("user:1");
        System.out.println(map);
        List<Object> objects = redisClient.opsForHash().multiGet("user:1", map.keySet());
        System.out.println(objects);
        redisClient.opsForHash().increment("user:1", "age", 1);
       redisClient.opsForHash().delete("user:2", "boys");
    }

    @Test
    public void testListRedisTemplate() {
        redisClient.opsForList().leftPush("list:1", "1");
        redisClient.opsForList().leftPush("list:1", "2");
        redisClient.opsForList().leftPush("list:1", "3");
        redisClient.opsForList().leftPush("list:1", "4");
        redisClient.opsForList().leftPush("list:1", "5");
        String s = redisClient.opsForList().leftPop("list:1");
        System.out.println(s);
        List<String> range = redisClient.opsForList().range("list:1", 0, -1);
        System.out.println(range);
    }

    @Test
    public void testSetRedisTemplate() {
        redisClient.opsForSet().add("set:1", "刘亦菲");
        redisClient.opsForSet().add("set:1", "有村架纯");
        redisClient.opsForSet().add("set:1", "阮秀");
        Set<String> members = redisClient.opsForSet().members("set:1");
        System.out.println(members);
    }

    @Test
    public void testZSetRedisTemplate() {
        redisClient.opsForZSet().add("zset:1", "刘亦菲", 5000);
        redisClient.opsForZSet().add("zset:1", "有村架纯", 4500);
        redisClient.opsForZSet().add("zset:1", "阮秀", 10000);
        Set<String> strings = redisClient.opsForZSet().rangeByScore("zset:1", 0, 10000);
        System.out.println(strings);
        redisClient.opsForZSet().remove("zset:1", "有村架纯");
        Set<String> strings1 = redisClient.opsForZSet().rangeByScore("zset:1", 10000, -1);
        System.out.println(strings1);
    }
}
