package fa.youareright.service;

import fa.youareright.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> listAllEmpl(String condition, Pageable pageable);
    void save(Employee employee);
//    void createEmployee(String employeeId, )

    String getLastEmpId();

    String getNextId(String inputId);
}
