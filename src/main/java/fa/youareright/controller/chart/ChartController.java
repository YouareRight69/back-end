package fa.youareright.controller.chart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.youareright.model.User;
import fa.youareright.repository.Chart;
import fa.youareright.repository.InvoiceRepository;
import fa.youareright.repository.Total;
import fa.youareright.repository.UserRepository;
import fa.youareright.repository.UserTotal;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/chart")
public class ChartController {


	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/total")
	@RolesAllowed("ROLE_ADMIN")
	List<Total> listAll(){
		return invoiceRepository.getTotal();
	}
	
	@GetMapping("/chart")
	@RolesAllowed("ROLE_ADMIN")
	List<Chart> listAllUser(){
		return invoiceRepository.getAll();
	}
	
	
	@GetMapping("/limmit")
	@RolesAllowed("ROLE_ADMIN")
	List<Total> listAllLimit(){
		return invoiceRepository.getLimit();
	}
	
	@GetMapping("/totalUser")
	@RolesAllowed("ROLE_ADMIN")
	List<UserTotal> listAlluser(){
		return  userRepository.getCountUser();
	}
}
