package fa.youareright.controller;

import fa.youareright.dto.AccountDto;
import fa.youareright.dto.UserDto;
import fa.youareright.model.Account;
import fa.youareright.model.User;
import fa.youareright.repository.UserRepository;
import fa.youareright.service.AccountService;
import fa.youareright.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @Autowired
    UserRepository userRepository;
    /**
     * @param page, condition
     * @return listAll()
     * @Creator HuyenTN2
     * @Date 30/05/2023
     */
    @GetMapping("")
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_RECEPTIONIST"})
    public ResponseEntity<Page<User>> findAllByCondition(
            @RequestParam(value = "c", defaultValue = "") String condition,
            @RequestParam(name = "p", defaultValue = "0") Integer page) {
        return new ResponseEntity<>(userService.listAll(condition, PageRequest.of(page, 5)), HttpStatus.OK);
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


//    @GetMapping("findAll")
//    public ResponseEntity<Page<User>> findAll( ) {
//        Page<User> users = userService.findAll(Pageable.unpaged(),"");
//        if (users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    /**
     * @param userId
     * @return if success status 2xx else if error status 4xx
     * @Creator HuyenTN2
     * @Date 30/05/2023
     */
    @PutMapping("/{userId}")
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_RECEPTIONIST"})
    public ResponseEntity<User> updateStatus(@PathVariable String userId) {
        User user = userService.findByUserId(userId);
        if (user== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.updateStatus(userId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/detail")
    public ResponseEntity<User> findById(@RequestParam(name= "id")  String userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!userRepository.findById(userId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.orElse(null), HttpStatus.OK);
    }

}
