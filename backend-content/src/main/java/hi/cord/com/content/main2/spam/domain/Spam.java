package hi.cord.com.content.main2.spam.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

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
    @AssociationOverrides({
            @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "user", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME", length = 60))
    })

    /**
     * Requirement parameter in Entity
     */
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Pagination<Spam> pagination;

    //------------Entity Filed finished----------------
}
