import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
@Data
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey purchaseListKey;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

}
