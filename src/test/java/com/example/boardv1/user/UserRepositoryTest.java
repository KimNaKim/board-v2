package com.example.boardv1.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // AutoWired로 가져올 클래스 Import하는 어노테이션
@DataJpaTest // EntityManager를 IoC에 등록하는 어노테이션
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        User user = userRepository.findById(id);

        // eye
        System.out.println(user);
    }

    @Test
    public void save_test() {
        // given
        User user = new User();
        user.setUsername("mi5");
        user.setPassword("1234");
        user.setEmail("mi5qjgt@gmail.com");

        // when
        User findUser = userRepository.save(user);

        // eye (user 객체가 DB 데이터와 동기화되었음)
        System.out.println(findUser);
    }

}
