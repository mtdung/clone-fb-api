package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


@Table(name = "FILE_INFO")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String fileId = UUID.randomUUID().toString();
    @Column(name = "BUCKET")
    private String bucket;
    @Column(name = "FILE_KEY")
    private String fileKey;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "CONTENT_TYPE")
    private Integer contentType;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "SIZE")
    private Long size;
}
