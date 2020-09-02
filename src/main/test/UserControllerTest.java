import com.docker.demo.DockerDemoApplication;
import com.docker.demo.validation.UserRequest;
import com.docker.demo.validation.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类
 *
 * @author DUCHONG
 * @since 2020-08-24 23:48
 **/
@SpringBootTest(classes = DockerDemoApplication.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void registerUserTest(){

        UserRequest user = UserRequest.builder()
                .age(10)
                .email("1111")
                .nickName("dc").build();

        userController.registerUser1(user) ;
    }
}
