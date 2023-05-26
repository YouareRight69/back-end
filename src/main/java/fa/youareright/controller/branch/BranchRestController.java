package fa.youareright.controller.branch;

import fa.youareright.dto.BranchMediaDTO;
import fa.youareright.model.Branch;
import fa.youareright.model.Media;
import fa.youareright.repository.MediaRepository;
import fa.youareright.service.BranchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/branch")
public class BranchRestController {
    @Autowired
    BranchService branchService;

    @Autowired
    MediaRepository mediaRepository;

    @GetMapping("")
    public ResponseEntity<Page<Branch>> findAllByCondition(
            @RequestParam(value = "c", defaultValue = "") String condition,
            @RequestParam(name = "p", defaultValue = "0") Integer page) {
        return new ResponseEntity<>(branchService.listAll(condition, PageRequest.of(page, 5)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid BranchMediaDTO branchMediaDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        Branch branch = new Branch();
        BeanUtils.copyProperties(branchMediaDTO, branch);
        this.branchService.save(branch);

        for (String url : branchMediaDTO.getMedia()) {
            Media media = new Media();
            BeanUtils.copyProperties(branchMediaDTO, media, "media");
            media.setUrl(url);
            media.setBranch(branch);
            mediaRepository.save(media);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<Branch> findById(@PathVariable String branchId) {
        Optional<Branch> branch = branchService.findById(branchId);

        if (!branchService.findById(branchId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(branch.orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<Branch> delete(@PathVariable String branchId) {
        Optional<Branch> branch = branchService.findById(branchId);

        if (!branch.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        branchService.delete(branchId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListBranch() {
        return new ResponseEntity<>(branchService.findList(), HttpStatus.OK);

    }

}
