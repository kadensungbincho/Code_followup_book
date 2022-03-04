package book.spring.batch.ch2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class Ch2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        Map map = new TreeMap<String, String>();
        System.out.println(map.get("myname"));
    }
}
