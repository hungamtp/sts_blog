package hunnid.com.blog.service.impl;

import hunnid.com.blog.entity.User;
import hunnid.com.blog.repository.UserRepository;
import hunnid.com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void processOAuthPostLogin(String username) {
        User existUser = userRepository.findByName(username);

    }
}
