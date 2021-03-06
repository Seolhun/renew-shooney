package hi.cord.com.common.domain.pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
public class Pagination<E> implements Serializable {
    private List<E> items;

    /** For Pagination Field */
    // current Page
    private int pageIndex;
    // Total Page
    private long totalCount;
    // pagination limit count
    private int pageSize;

    /** For searching Field */
    //To search PK
    private String id;
    //To search Index
    private long idx;
    //To search Type
    private int searchType;
    //To search Text
    private String searchText;
    //To serach Date
    private Date searchDate;

    public Pagination() {

    }

    public Pagination(int pageIndex, long totalCount, int pageSize) {
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }
}