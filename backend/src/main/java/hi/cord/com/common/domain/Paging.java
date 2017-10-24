package hi.cord.com.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Paging<E> implements Serializable {
    private E e;

    /** For Paging Field */
    // current Page
    private int pageIndex;
    // Total Page
    private long totalPage;
    // paging limit count
    private int limit;

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

    public Paging() {

    }

    public Paging(int pageIndex, long totalPage) {
        this.pageIndex = pageIndex;
        this.totalPage = totalPage;
    }
}