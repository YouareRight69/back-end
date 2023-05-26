package fa.youareright.controller;

import fa.youareright.dto.HairServiceDto;
import fa.youareright.model.*;
import fa.youareright.repository.MediaRepository;
import fa.youareright.service.HairServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/hairService")
@CrossOrigin("*")
public class HairServiceController {

    @Autowired
    HairServiceService hairServiceService;

    @Autowired
    MediaRepository mediaRepository;

    @GetMapping("")
    public ResponseEntity<Page<HairService>> findAllByCondition(
            @RequestParam(value = "c", defaultValue = "") String condition,
            @RequestParam(name = "p", defaultValue = "0") Integer page) {
        return new ResponseEntity<>(hairServiceService.listAll(condition, PageRequest.of(page, 5)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid HairServiceDto hairServiceDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        HairService hairService = new HairService();
        BeanUtils.copyProperties(hairServiceDto, hairService);
        this.hairServiceService.save(hairService);

        for (String url : hairServiceDto.getMedia()) {
            Media media = new Media();
            BeanUtils.copyProperties(hairServiceDto, media, "media");
            media.setUrl(url);
            media.setHairService(hairService);
            mediaRepository.save(media);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<HairService> findById(@PathVariable String serviceId) {
        Optional<HairService> hairService = hairServiceService.findById(serviceId);

        if (!hairServiceService.findById(serviceId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hairService.orElse(null), HttpStatus.OK);
    }

    @PatchMapping("/{serviceId}")
    public ResponseEntity<HairService> update(@PathVariable String serviceId,
                                              @Valid @RequestBody HairServiceDto hairServiceDto,
                                              BindingResult bindingResult) {
        Optional<HairService> currentHairService = hairServiceService.findById(serviceId);

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        if (!currentHairService.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentHairService.get().setServiceId(hairServiceDto.getServiceId());
        currentHairService.get().setName(hairServiceDto.getName());
        currentHairService.get().setPrice(hairServiceDto.getPrice());
        currentHairService.get().setDescription(hairServiceDto.getDescription());
        currentHairService.get().setType(hairServiceDto.getType());
        currentHairService.get().setMedia(hairServiceDto.getMedia());

        hairServiceService.save(currentHairService.get());

        return new ResponseEntity<>(currentHairService.get(), HttpStatus.OK);
    }


    @DeleteMapping("/{serviceId}")
    public ResponseEntity<HairService> delete(@PathVariable String serviceId) {
        Optional<HairService> hairService = hairServiceService.findById(serviceId);

        if (!hairService.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        hairServiceService.delete(serviceId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListService() {
        return new ResponseEntity<>(hairServiceService.findList(), HttpStatus.OK);

    }
}
