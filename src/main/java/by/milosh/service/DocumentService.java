package by.milosh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import by.milosh.dto.DocumentDto;
import by.milosh.dto.FileDescriptionDto;
import by.milosh.dto.SearchDto;
import by.milosh.mapping.DocumentMapper;
import by.milosh.model.Document;
import by.milosh.repository.DocumentRepository;
import by.milosh.repository.specification.DocumentSpecification;
import lombok.AllArgsConstructor;

import static org.springframework.data.jpa.domain.Specification.where;
import static by.milosh.repository.specification.DocumentSpecification.hasType;
import static by.milosh.repository.specification.DocumentSpecification.titleContains;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentService {

    private FilePathService filePathService;
    private DocumentRepository documentRepository;

    public void save(MultipartFile file, FileDescriptionDto fileDescriptionDto) throws IOException {
        Path filePath = filePathService.getFilePath(fileDescriptionDto);
        Files.write(filePath, file.getBytes());
        Document document = Document.builder()
                .title(fileDescriptionDto.getTitle())
                .type(fileDescriptionDto.getType().name().toLowerCase())
                .localDateTime(LocalDateTime.now())
                .url(filePath.toString())
                .build();
        documentRepository.save(document);
    }

    public Document getById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public List<DocumentDto> getByCriteria(SearchDto searchDto) {
        List<Document> documents = documentRepository.findAll(where(hasType(searchDto.getType())).and(titleContains(searchDto.getTitle())));
        return DocumentMapper.toDocumentDtoList(documents);
    }
}
