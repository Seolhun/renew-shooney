package hi.cord.com.content.main.file.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.content.main.content.domain.BlogContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "TB_FILE_DATA")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_FILE_DATA_IDX_CREATED_BY", columnNames = {"IDX", "CREATED_NICKNAME"}))
public class FileData extends BaseEntity implements Serializable {
	@Id
	@Column(name = "FILE_ID", length = 120)
	private String id;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	/****** Transient Start *********
	 * This part not saved into Database
	 *********************************/
    @Transient
    private byte[] bytes;

	@Transient
	private List<MultipartFile> multipartFiles;

	/****** Transient End **********/

	//------------Entity Filed finished----------------
}
