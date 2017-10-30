package hi.cord.com.jpa2.comment.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.Pagination;
import hi.cord.com.jpa2.board.domain.Board;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_COMMENT")
public class Comment extends CommonDomainInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long commentId;

	@ManyToOne(fetch = FetchType.LAZY, optional=true, cascade=CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "BLOG_FK"), name = "BLOG_ID", referencedColumnName = "BLOG_ID", nullable=false)
	private Board blogInComment;

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
