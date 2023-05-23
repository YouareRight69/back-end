package fa.youareright.service;

import fa.youareright.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    void save(Account account);
}
