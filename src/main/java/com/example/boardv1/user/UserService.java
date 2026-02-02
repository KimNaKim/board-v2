package com.example.boardv1.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository uRepository;

    @Transactional
    public User insert(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        User GUser = uRepository.save(user);

        return GUser;
    }

    public User detail(int id) {
        User user = uRepository.findById(id);
        return user;
    }

    @Transactional
    public void deleteById(int id) {
        User user = uRepository.findById(id);
        uRepository.delete(user);
    }

    // 로그인 패스워드 일치 여부를 확인하기
    public void isLoginable() {
        User user = uRepository.findByUsername(null);

    }
}
