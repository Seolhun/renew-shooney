package hi.cord.com.content.main2.spam.domain;

import hi.cord.com.common.domain.entity.BaseCreatedBy;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.BaseModifiedBy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "TB_SPAM")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
public class Spam extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPAM_ID")
    private long id;

    @NotNull(message = "SpamType is requirement")
    @Column(name = "SPAM_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private SpamType spamType;

    @Column(name = "CONTENT", length = 250, nullable = false)
    private String content;

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

    //------------Entity Filed finished----------------
}
