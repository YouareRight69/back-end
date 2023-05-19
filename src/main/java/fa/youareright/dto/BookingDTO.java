package fa.youareright.dto;

import fa.youareright.model.Branch;
import fa.youareright.model.Employee;
import fa.youareright.model.WorkingTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class BookingDTO {
    private List<Branch> branchList;
    private List<Employee> employeeList;
    private List<WorkingTime> workingTimeList;


}
