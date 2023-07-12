package com.example.hello.user;

import com.example.hello.user.domain.User;
import com.example.hello.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        // given(준비)
        String nickname = "선녀";
        User user = new User(
                nickname
        );
        // when(실행)
        user = userRepository.save(user);
        // then(검증)
        assertNotNull(user.getId());
        // (예상값, 실제값)
        assertEquals(nickname, user.getNickname());
    }

    @Test
    void deleteUser() {
        // given(준비)
        String nickname = "선녀";
        User user = new User(
                1,
                nickname
        );
        user = userRepository.save(user);

        // when(실행)
        userRepository.delete(user);
//        System.out.println(userRepository.findByNickname(nickname).get());

        // then(검증)
//        userRepository.existsByNickname(nickname);
        assertThrows(NoSuchElementException.class,
                () -> userRepository.findById(1).get()
        );
    }

//    @Test
//    void hi() {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        String[] beans = applicationContext.getBeanDefinitionNames();
//        for (String bean : beans) {
//            System.out.println(bean);
//        }
//    }
}
