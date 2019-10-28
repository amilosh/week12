package by.milosh.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import by.milosh.model.Document;

public class DocumentSpecification {

    public static Specification<Document> hasType(final String type) {
        if (type != null) {
            return (document, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(document.get("type"), type);
        } else {
            return (document, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(document.get("type"));
        }
    }

    public static Specification<Document> titleContains(final String title) {
        if (title != null) {
            return (document, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(document.get("title"), "%" + title + "%");
        } else {
            return (document, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(document.get("title"));
        }
    }
}
