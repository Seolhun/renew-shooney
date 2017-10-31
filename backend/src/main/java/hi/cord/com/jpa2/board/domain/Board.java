package hi.cord.com.jpa2.board.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.CommonDomainInfo;
import hi.cord.com.common.domain.Pagination;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.file.domain.FileData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "TB_BOARD")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class Board extends CommonDomainInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private long id;

    @NotEmpty
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Lob
    @NotEmpty
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "HITS", length = 10)
    private int hits;

    @Column(name = "LIKES", length = 10)
    private int likes;

    @Column(name = "BOARD_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(name = "COMMENT_DEPTH", length = 10)
    private int depth;

    @OneToMany(mappedBy = "boardInComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "boardInFile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileData> files;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Pagination<Board> pagination;

    @PostConstruct
    private void init() {
        if (boardType == null) {
            boardType = BoardType.Freeboard;
        }
    }
}
