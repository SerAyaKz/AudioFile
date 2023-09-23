package kz.com.audio.Repository;

import kz.com.audio.Entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {
    Optional<AudioFile> findByFileName(String fileName);
}
