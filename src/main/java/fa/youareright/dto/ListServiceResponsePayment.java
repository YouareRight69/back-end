package fa.youareright.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListServiceResponsePayment {
    private String serviceId;
    private String serviceName;
    private String employeeName;
    private double price;
}
