package kz.com.audio.Entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audio_files")
public class AudioFile {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_date")
    private Date fileDate;

    @Column(name = "file_path")
    private String filePath;
}
