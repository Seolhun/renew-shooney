package hi.cord.com.content.main.tag.domain;

import hi.cord.com.common.domain.entity.BaseCreatedBy;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.BaseModifiedBy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TB_TAG")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Tag extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ID")
	private long id;

	@Column(name = "TAG_NAME")
	private String name;

    @Version
    @Column(name = "VERSION")
    private long version;

	@CreatedBy
	@AttributeOverrides({
			@AttributeOverride(name = "createdByUserId", column = @Column(name = "CREATED_BY_ID", length = 120)),
			@AttributeOverride(name = "createdByNickname", column = @Column(name = "CREATED_BY_NICKNAME", length = 60))
	})
	private BaseCreatedBy createdBy;

	@LastModifiedBy
	@AttributeOverrides({
			@AttributeOverride(name = "modifiedByUserId", column = @Column(name = "MODIFIED_BY_ID", length = 120)),
			@AttributeOverride(name = "modifiedByNickname", column = @Column(name = "MODIFIED_BY_NICKNAME", length = 60))
	})
	private BaseModifiedBy modifiedBy;
}
