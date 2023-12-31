package labshoppub-sub.domain;

import labshoppub-sub.domain.*;
import labshoppub-sub.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;

    public OrderPlaced(Order aggregate){
        super(aggregate);
    }
    public OrderPlaced(){
        super();
    }
}
