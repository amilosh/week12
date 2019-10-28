package by.milosh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

import by.milosh.dto.FileDescriptionDto;

@Service
public class FilePathService {

    @Value("${call.file.path}")
    private String filePath;

    public Path getFilePath(FileDescriptionDto fileDescriptionDto) {
        return Paths.get(filePath, fileDescriptionDto.getTitle() + ".txt");
    }
}
