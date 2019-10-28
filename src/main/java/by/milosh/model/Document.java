package by.milosh.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import by.milosh.dto.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "document", schema = "document_storage")
public class Document extends BaseEntity<Long> {

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "upload_date")
    private LocalDateTime localDateTime;

    @Column(name = "url")
    private String url;
}
