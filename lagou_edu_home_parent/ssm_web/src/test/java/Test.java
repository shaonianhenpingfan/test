import com.zut.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestService testService = (TestService) applicationContext.getBean("testService");
        List<com.zut.domain.Test> allTest = testService.findAllTest();
        for (com.zut.domain.Test test : allTest) {
            System.out.println(test);
        }
    }
}
