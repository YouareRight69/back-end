package fa.youareright.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HairServiceDto {
    private String serviceId;
    private String name;
    private float price;
    private String description;
    private String type;
    private int isDelete;
}
