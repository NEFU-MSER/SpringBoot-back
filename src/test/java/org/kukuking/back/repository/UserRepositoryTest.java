package org.kukuking.back.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kukuking.back.utils.Gender;
import org.kukuking.back.dox.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){
        User user = User.builder()
                .account("2021213196")
                .password("112210ly")
                .name("KUKUKING233")
                .email("ly1112210@outlook.com")
                .gender(Gender.Male.toInt())
                .build();
        userRepository.save(user);
    }

    @Test
    public void findUserByAccount() {
        User user = userRepository.findUserByAccount("2021213196");
        System.out.println(user);
    }

    @Test
    public void findUserByAccountAndPassword() {
        boolean ifExit = userRepository.findUserByAccountAndPassword("2021213196","112210ly") != null;
        System.out.println(ifExit);
    }
}