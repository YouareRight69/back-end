package fa.youareright.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class BookingRestController {

    @GetMapping("")
    public ResponseEntity<?> testPostMan() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
