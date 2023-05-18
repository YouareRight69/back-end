package fa.youareright.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkingTime {
    @Id
    @Column(name="working_time_id",columnDefinition = "varchar(10)")
    private String workingTimeId;
    @Column(name="time_zone", columnDefinition = "Time")
    private LocalTime timeZone;
}
