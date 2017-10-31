package hi.cord.com.jpa2.file.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.Pagination;
import hi.cord.com.jpa2.board.domain.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity(name = "TB_FILE_DATA")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class FileData extends CommonDomainInfo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FILE_ID")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_BOARD_FK"), name = "BOARD_ID", referencedColumnName = "BOARD_ID", nullable = false)
	private Board boardInFile;

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

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Pagination<FileData> pagination;
}
