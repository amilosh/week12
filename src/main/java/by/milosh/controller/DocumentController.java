package by.milosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import by.milosh.dto.DocumentDto;
import by.milosh.dto.FileDescriptionDto;
import by.milosh.dto.SearchDto;
import by.milosh.model.Document;
import by.milosh.service.DocumentService;
import by.milosh.service.FilePathService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/document")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentController {

    private DocumentService documentService;
    private FilePathService filePathService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDocument(@RequestParam("file") MultipartFile file,
            @ModelAttribute FileDescriptionDto fileDescriptionDto) throws IOException {
        documentService.save(file, fileDescriptionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void uploadFile(HttpServletResponse response, @PathVariable Long id) throws IOException {
        Document document = documentService.getById(id);
        if (document == null) {
            response.setStatus(404);
        } else {
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getTitle() + ".txt\"");
            Path path = Paths.get(document.getUrl());
            OutputStream outputStream = response.getOutputStream();
            Files.copy(path, outputStream);
            outputStream.flush();
            outputStream.close();
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<DocumentDto>> searchDocuments(@ModelAttribute SearchDto searchDto) {
        List<DocumentDto> documents = documentService.getByCriteria(searchDto);
        return ResponseEntity.ok(documents);
    }
}
