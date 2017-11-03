package hi.cord.com.jpa2.file.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.jpa2.content.domain.Content;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;

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
	private Content contentInFile;

	@NotEmpty
	@Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
	private String fileDataOriginName;

	@NotEmpty
	@Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
	private String fileDataSavedName;

	@Column(name = "FILE_SAVED_PATH", nullable = false, length = 200)
	private String fileDataSavedPath;

	@Column(name = " FILE_TYPE", nullable = false, length = 20)
	private String fileDataType;

	@CreatedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "CREATED_BY")),
			@AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME", length = 60))
	})
	@Embedded
	private CreatedByEntity createdByEntity;

	@LastModifiedBy
	@AssociationOverrides({
			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "LAST_MODIFIED_BY"))
	})
	@AttributeOverrides({
			@AttributeOverride(name = "user", column = @Column(name = "LAST_MODIFIED_BY")),
			@AttributeOverride(name = "nickname", column = @Column(name = "LAST_MODIFIED_NICKNAME", length = 60))
	})
	@Embedded
	private ModifiedByEntity modifiedByEntity;

	/**
	 * Requirement parameter in Entity
	 */
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Pagination<FileData> pagination;

	//------------Entity Filed finished----------------
}
