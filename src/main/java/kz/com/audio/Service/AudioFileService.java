package kz.com.audio.Service;

import kz.com.audio.Entity.AudioFile;
import kz.com.audio.Repository.AudioFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AudioFileService {
    private final AudioFileRepository audioFileRepository;

    public void saveFile(MultipartFile file) throws IOException, ParseException {
        // Process the file and extract name, date, path
        String fileName = file.getOriginalFilename();
        Date fileDate = extractDateFromFileName(fileName);

        String uploadDir = "C:\\Users\\ayanb\\Desktop\\audio"; // Указываем путь к директории
        File uploadedFile = new File(uploadDir, fileName);
        file.transferTo(uploadedFile);

        // Save audio file to db
        AudioFile audioFile = new AudioFile();
        audioFile.setFileName(fileName);
        audioFile.setFileDate(fileDate);
        audioFile.setFilePath(uploadedFile.getAbsolutePath());
        audioFileRepository.save(audioFile);

    }
    public Optional<AudioFile> getFileInfo(String fileName) {
        return audioFileRepository.findByFileName(fileName);
    }

    public static Date extractDateFromFileName(String fileName) {
        try {
            // Split the filename by "_" and take the date part
            String[] parts = fileName.split("_");
            String datePart = parts[0];

            // Parse the date by DDMMYYYY format
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            return dateFormat.parse(datePart);
        } catch (ParseException e) {
            return null;
        }
    }
}

