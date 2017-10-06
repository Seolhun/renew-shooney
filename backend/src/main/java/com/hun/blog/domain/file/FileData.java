package com.hun.blog.domain.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hun.blog.domain.AbstractCommon;
import com.hun.blog.domain.blog.Blog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_BLOG_FILE")
public class FileData extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long fileDataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FILE_BLOG_FK"), name = "FILE_BLOG_ID", referencedColumnName = "BLOG_ID")
    private Blog blogInFile;

    @Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
    private String fileDataOriginName;

    @Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
    private String fileDataSavedName;

    @Column(name = "FILE_SAVED_PATH", nullable = false, length = 200)
    private String fileDataSavedPath;

    @Column(name = " FILE_TYPE", nullable = false, length = 20)
    private String fileDataType;

    @Column(name = " FILE_SIZE", nullable = false, length = 20)
    private Long fileDataSize;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Transient
    private byte[] fileByte;

    @Transient
    private MultipartFile[] files;
}
