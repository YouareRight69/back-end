package fa.youareright.controller;

import fa.youareright.dto.AccountDto;
import fa.youareright.dto.UserDto;
import fa.youareright.model.Account;
import fa.youareright.model.User;
import fa.youareright.service.AccountService;
import fa.youareright.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Page<User>> findAll(@PageableDefault(value = 5) Pageable pageable, @RequestParam Optional<String> keyword) {
        Page<User> users = userService.findAll(pageable, keyword.orElse(""));
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<FieldError>> create(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        AccountDto accountDto;
        accountDto = userDto.getAccountDto();
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);

        accountService.save(account);

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/updateInfo")
    public ResponseEntity<?> updateInfo(){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}