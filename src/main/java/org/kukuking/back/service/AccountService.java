package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import org.kukuking.back.dox.User;
import org.kukuking.back.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;

    @Transactional
    public boolean login(String account, String password) {
        boolean result = false;
//        User user = userRepository.findUserByAccountAndPassword(account, password);
//        if (user != null) {
//            result = true;
//        }
        return result;
    }
}
