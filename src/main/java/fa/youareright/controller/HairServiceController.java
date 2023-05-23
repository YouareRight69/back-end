package fa.youareright.controller;

import fa.youareright.dto.HairServiceDto;
import fa.youareright.model.HairService;
import fa.youareright.model.User;
import fa.youareright.service.HairServiceService;
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
@RequestMapping("/api/hairService")
@CrossOrigin("http://localhost:3000")
public class HairServiceController {

    @Autowired
    HairServiceService hairServiceService;

    @GetMapping("")
    public ResponseEntity<Page<HairService>> findAll(@PageableDefault(value = 5) Pageable pageable, @RequestParam Optional<String> keyword) {
        Page<HairService> hairServices = hairServiceService.findAll(pageable, keyword.orElse(""));
        if (hairServices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hairServices, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<FieldError>> create(@RequestBody @Valid HairServiceDto hairServiceDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        HairService hairService = new HairService();
        BeanUtils.copyProperties(hairServiceDto, hairService);
        this.hairServiceService.save(hairService);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
