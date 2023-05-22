package fa.youareright.service.impl;

import fa.youareright.model.User;
import fa.youareright.repository.AccountRepository;
import fa.youareright.repository.AccountRoleRepository;
import fa.youareright.repository.UserRepository;
import fa.youareright.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Page<User> findAll(Pageable pageable, String keyword) {
        return userRepository.findAll(pageable, keyword);
    }

    @Override
    public void save(User user) {
        user.setAccount(user.getAccount());
        userRepository.save(user);
    }
}
