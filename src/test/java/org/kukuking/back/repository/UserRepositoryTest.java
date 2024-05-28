package org.kukuking.back.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kukuking.back.component.utils.Gender;
import org.kukuking.back.DO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){
        User user = User.builder()
                .account("2021213220")
                .password("123456789")
                .name("张小鹏")
                .email("zxp1112210@outlook.com")
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
        boolean ifPresent = userRepository.findUserByAccountAndPassword("2021213196","112210ly") != null;
        System.out.println(ifPresent);
    }

    @Test
    public void findUsersByGender() {
        List<User> users = userRepository.findUsersByGender(Gender.Male.toInt());
        for (User user : users) {
            System.out.println(user);
        }
    }
}