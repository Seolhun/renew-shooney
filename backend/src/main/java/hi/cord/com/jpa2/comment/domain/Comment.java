package hi.cord.com.jpa2.comment.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.common.domain.enumtypes.ContentType;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.jpa2.content.domain.Content;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TB_COMMENT")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Comment extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	private long id;

	@Column(name = "CONTENT_TYPE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private ContentType contentType;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "CONTENT_COMMENT_FK"), name = "CONTENT_ID", referencedColumnName = "CONTENT_ID", nullable=false)
	private Content contentInContent;

	@Column(name = "CONTENT", length=250 ,nullable = false)
	private String content;

	@Column(name = "LIKES")
	private int likes;

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

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Pagination<Comment> pagination;
}
