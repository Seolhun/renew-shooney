//package hi.cord.com.jpa2.tag.domain;
//
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import hi.cord.com.common.domain.entity.BaseEntity;
//import hi.cord.com.common.domain.entity.CreatedByEntity;
//import hi.cord.com.common.domain.entity.ModifiedByEntity;
//import hi.cord.com.common.domain.pagination.Pagination;
//import hi.cord.com.jpa2.content.domain.BlogContent;
//import hi.cord.com.jpa2.content.domain.SpamType;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.LastModifiedBy;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity(name = "TB_TAG")
//@EqualsAndHashCode(callSuper = false)
//@ToString(callSuper = true)
//@Getter
//@Setter
//public class Tag extends BaseEntity implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "TAG_ID")
//	private long id;
//
//	@Column(name = "CONTENT_TYPE", nullable = false, length = 50)
//	@Enumerated(EnumType.STRING)
//	private SpamType contentType;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
//	@JoinColumn(foreignKey = @ForeignKey(name = "CONTENT_TAG_FK"), name = "CONTENT_ID", referencedColumnName = "CONTENT_ID", nullable=false)
//	private BlogContent fromTag;
//
//	@Column(name = "CONTENT", length=250 ,nullable = false)
//	private String content;
//
//	@Column(name = "LIKES")
//	private int likes;
//
//	@CreatedBy
//	@AssociationOverrides({
//			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
//	})
//	@AttributeOverrides({
//			@AttributeOverride(name = "user", column = @Column(name = "CREATED_BY")),
//			@AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME", length = 60))
//	})
//	@Embedded
//	private CreatedByEntity createdByEntity;
//
//	@LastModifiedBy
//	@AssociationOverrides({
//			@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "LAST_MODIFIED_BY"))
//	})
//	@AttributeOverrides({
//			@AttributeOverride(name = "user", column = @Column(name = "LAST_MODIFIED_BY")),
//			@AttributeOverride(name = "nickname", column = @Column(name = "LAST_MODIFIED_NICKNAME", length = 60))
//	})
//	@Embedded
//	private ModifiedByEntity modifiedByEntity;
//
//	@Transient
//	@JsonSerialize
//	@JsonDeserialize
//	private Pagination<Tag> pagination;
//}
