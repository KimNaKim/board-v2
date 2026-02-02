package com.example.boardv1.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository uRepository;

    @Transactional
    public User insert(String username, String password, String email) { // 회원가입
        // id(username 중복 체크)
        User findUser = uRepository.findByUsername(username);
        if (findUser != null) {
            throw new RuntimeException("username이 중복되었습니다.");
        }

        // 2. 비영속 객체
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // 3. save()호출
        User GUser = uRepository.save(user);

        return GUser;
    }

    public User detail(int id) {
        User user = uRepository.findById(id);
        return user;
    }

    public User login(String username, String password) { // 로그인하기
        // 1. username으로 사용자 조회
        User user = uRepository.findByUsername(username);

        // 2. 비밀번호 검증
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        // 3. 로그인 성공
        return user;
    }

    public void findAll() {
        List<User> list = uRepository.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
