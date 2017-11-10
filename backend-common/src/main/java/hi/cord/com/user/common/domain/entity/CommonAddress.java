package hi.cord.com.user.common.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author HunSeol
 * @created_date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */
@Data
@Embeddable
public class CommonAddress {
    /*
        About CommonAddress
    */
    @Column(name = "ZIP_CODE", length = 50)
    private String zipCode;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "NATION_CODE", length = 10)
    private String nation;
}
