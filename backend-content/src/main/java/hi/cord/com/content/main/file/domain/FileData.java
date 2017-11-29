package hi.cord.com.content.main.file.domain;

import hi.cord.com.common.domain.entity.BaseCreatedBy;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.BaseModifiedBy;
import hi.cord.com.content.main.content.domain.BlogContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_FILE_DATA")
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_FILE_DATA_IDX_CREATED_BY", columnNames = {"IDX", "CREATED_BY_NICKNAME"}))
@BatchSize(size = 20)
public class FileData extends BaseEntity implements Serializable {
    @Id
    @Column(name = "FILE_ID", length = 120)
    private String id;

    @Column(name = "IDX")
    private long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FILE_CONTENT_FK"), name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private BlogContent contentInFiles;

    @NotEmpty
    @Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
    private String originName;

    @NotEmpty
    @Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
    private String savedName;

    @Column(name = "FILE_SAVED_PATH", nullable = false, length = 200)
    private String savedPath;

    @Column(name = " FILE_TYPE", nullable = false, length = 30)
    private String fileType;

    @Column(name = " FILE_SIZE", nullable = false)
    private long size;

    @CreatedBy
    @AttributeOverrides({
            @AttributeOverride(name = "createdByUserId", column = @Column(name = "CREATED_BY_ID", length = 120)),
            @AttributeOverride(name = "createdByNickname", column = @Column(name = "CREATED_BY_NICKNAME", length = 60))
    })
    private BaseCreatedBy baseCreatedBy;

    @LastModifiedBy
    @AttributeOverrides({
            @AttributeOverride(name = "modifiedByUserId", column = @Column(name = "MODIFIED_BY_ID", length = 120)),
            @AttributeOverride(name = "modifiedByNickname", column = @Column(name = "MODIFIED_BY_NICKNAME", length = 60))
    })
    private BaseModifiedBy baseModifiedBy;

    /****** Transient Start *********
     * This part not saved into Database
     *********************************/
    //@Transient
    //private byte[] bytes;

    @Transient
    private MultipartFile multipartFile;


    /****** Constructor With Multipart **********/
    public FileData() {
    }

    public FileData(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    //------------Entity Filed finished----------------
}
