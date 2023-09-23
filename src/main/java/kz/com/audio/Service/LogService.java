package kz.com.audio.Service;

import kz.com.audio.Entity.Log;
import kz.com.audio.Repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    public void save(String requestDetail, String responseDetail, String filename)  {
        Log log = new Log();
        log.setRequestDetails(requestDetail);
        log.setResponseDetails(responseDetail);
        log.setTimestamp(LocalDateTime.now());
        log.setFilename(filename);
        logRepository.save(log);
    }
    public List<Log> findAll() {
        return logRepository.findAll();
    }
}
