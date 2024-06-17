package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.User;
import org.kukuking.back.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByAccountOrIdCard(String account, String idCard) {
        return userRepository.findUserByAccountOrIdCard(account, idCard);
    }

    public User getUserByAccount(String account){
        return userRepository.findUserByAccount(account);
    }

    public User getUserById(String id){
        return userRepository.findUserById(id);
    }

    public void saveUser(User user){
        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateUser(User user){
        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
