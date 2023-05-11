package fa.youareright.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BookingDetail {
    @Id
    private String id;
    private String name;
    
	
    
}
