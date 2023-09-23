package kz.com.audio.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_details")
    private String requestDetails;

    @Column(name = "response_details")
    private String responseDetails;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "filename")
    private String filename;
}
