package by.milosh.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.milosh.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>, JpaSpecificationExecutor<Document> {

}
