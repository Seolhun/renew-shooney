package hi.cord.com.jpa2.comment.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.Pagination;
import hi.cord.com.jpa2.board.domain.Board;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TB_COMMENT")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Comment extends CommonDomainInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional=true, cascade=CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "BOARD_COMMENT_FK"), name = "BOARD_ID", referencedColumnName = "BOARD_ID", nullable=false)
	private Board boardInComment;

	@Column(name = "ENTITY_NAME", length=20, nullable = false)
	private String entityName;

	@Column(name = "CONTENT", length=300 ,nullable = false)
	private String content;

	@Column(name = "LIKES")
	private int likes;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Pagination<Comment> pagination;
}
