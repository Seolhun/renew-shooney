package hi.cord.com.jpa2.file.domain;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.jpa2.board.domain.Board;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "TB_FILE_DATA")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class FileData extends CommonDomainInfo{
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_BOARD_FK"), name = "FILE_BOARD_ID", referencedColumnName = "BOARD_ID", nullable = false)
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
}
