package kz.com.audio.Component;

import kz.com.audio.Entity.Log;
import kz.com.audio.Service.LogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private final LogService logService;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ScheduledTasks(LogService logService) {
        this.logService = logService;
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleTaskWithFixedRate() {
        List<Log> logs = logService.findAll();
        logger.info("No. of logs :: {} Execution Time - {}", logs.size(), dateTimeFormatter.format(LocalDateTime.now()));
    }
}