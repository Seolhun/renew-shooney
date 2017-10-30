package hi.cord.com.jpa2.board.domain;

import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.file.domain.FileData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity(name = "TB_BOARD")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Board extends CommonDomainInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOARD_ID")
	private long id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@NotEmpty
	@Column(name = "CONTENT", nullable = false)
	@Lob
	private String content;

	@Column(name = "HITS", length = 200)
	private int hits;

	@Column(name = "LIKES", length = 100)
	private int likes;
	
	@Column(name = " TYPE", nullable = false, length = 10)
	private BoardType types;
	
	@Column(name = "COMMENT_COUNTS", length = 200)
	private int commentCounts;

	@OneToMany(mappedBy="boardInComment", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="boardInFile", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FileData> files;

//	@OneToMany(mappedBy="boardInReply", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Reply> replyListInBoard;
//
//	@OneToMany(mappedBy="boardInComment", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<CommentRepostiroy> commentListInBoard;
}
