package by.milosh.mapping;

import java.util.List;
import java.util.stream.Collectors;

import by.milosh.dto.DocumentDto;
import by.milosh.model.Document;

public class DocumentMapper {

    public static DocumentDto toDocumentDto(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .type(document.getType())
                .build();
    }

    public static List<DocumentDto> toDocumentDtoList(List<Document> documents) {
        return documents.stream().map(document -> toDocumentDto(document)).collect(Collectors.toList());
    }
}
