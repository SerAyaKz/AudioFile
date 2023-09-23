package kz.com.audio.Controller;

import kz.com.audio.Entity.AudioFile;
import kz.com.audio.Service.AudioFileService;
import kz.com.audio.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/audio")
public class AudioFileController {

    @Autowired
    private AudioFileService audioFileService;
    @Autowired
    private LogService logService;
    //private static final Logger logger = LoggerFactory.getLogger(AudioFileController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            audioFileService.saveFile(file);
            logService.save("upload","SUCCESS",file.getOriginalFilename());
            //logger.info("File '{}' uploaded successfully.", file.getOriginalFilename());
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException | ParseException e) {
            logService.save("upload","FAIL",file.getOriginalFilename());
            //logger.error("Failed to upload file.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/getFileInfo")
    public ResponseEntity<Object> getFileInfo(@RequestBody Map<String, String> requestMap) {
        String command = requestMap.get("command");
        String fileName = requestMap.get("fileName");
        if ("fileInfo".equals(command)) {

            // search for audio file
            Optional<AudioFile> audioFileOptional = audioFileService.getFileInfo(fileName);

            if (audioFileOptional.isPresent()) {
                AudioFile audioFile = audioFileOptional.get();

                // create object with necessary data
                Map<String, String> fileInfo = new HashMap<>();
                fileInfo.put("fileName", audioFile.getFileName());
                fileInfo.put("fileDate", audioFile.getFileDate().toString());
                fileInfo.put("filePath", audioFile.getFilePath());

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + audioFile.getFileName());
                //logger.info("File Info '{}' got successfully.", audioFile.getFileName());
                logService.save("getInfo","SUCCESS",audioFile.getFileName());
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(fileInfo);
            } else {
                //logger.error("File is NOT FOUND.");
                logService.save("getInfo","NOT_FOUND",fileName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File is NOT FOUND.");
            }
        } else {
            //logger.error("Command is not correct.");
            logService.save("getInfo","FAIL",fileName);
            return ResponseEntity.badRequest().body("Command is not correct.");
        }
    }
}
