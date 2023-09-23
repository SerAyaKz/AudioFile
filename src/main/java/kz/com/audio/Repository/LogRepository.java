package kz.com.audio.Repository;

import kz.com.audio.Entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
