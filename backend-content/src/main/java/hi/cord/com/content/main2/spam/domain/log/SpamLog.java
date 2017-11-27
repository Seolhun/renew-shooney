package hi.cord.com.content.main2.spam.domain.log;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TB_LOG_SPAM")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
public class SpamLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPAM_ID")
    private long id;

    @Column(name = "CONTENT", length = 250, nullable = false)
    private String content;

    /**
     * Requirement parameter in Entity
     */
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Pagination<SpamLog> pagination;

    //------------Entity Filed finished----------------
}
