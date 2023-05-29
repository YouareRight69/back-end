package fa.youareright.repository;

import fa.youareright.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Query(value = "select e from Employee e")
	List<Employee> listAll();
}
