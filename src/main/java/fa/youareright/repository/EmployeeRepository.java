package fa.youareright.repository;

import fa.youareright.model.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	@Query(value = "select e from Employee e")
//	List<Employee> listAll();
//	@Query(value = "select * from employee " +
//			"inner join user on employee.user_id = user.user_id " +
//			"inner join branch on employee.branch_id = branch.branch_id " +
//			"where (user.full_name like concat('%','abc','%') or branch.name like concat('%','002','%'))", nativeQuery = true)
//	List<Employee> listAll();

	@Query(value = "select * from employee " +
			"inner join user on employee.user_id = user.user_id " +
			"inner join branch on employee.branch_id = branch.branch_id " +
			"where (user.full_name like concat('%',:fullName,'%')  or branch.name like concat('%',:name,'%')) ", nativeQuery = true)
	Page<Employee> listAllEmp(@Param("fullName") String fullName,
							  @Param("name") String name,
							  Pageable pageable);

	@Query(value = "select emp_id from employee order by emp_id desc limit 1", nativeQuery = true)
	public String getLastEmpId();

	@Query(value = "select e from Employee e")
	List<Employee> listAll();
}
