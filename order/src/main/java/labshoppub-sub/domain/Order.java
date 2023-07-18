package labshoppub-sub.domain;

import labshoppub-sub.domain.OrderPlaced;
import labshoppub-sub.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Order_table")
@Data

public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String productId;
    
    
    
    
    
    private Integer qty;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private Double amount;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshoppub-sub.external.DecreaseStockCommand decreaseStockCommand = new labshoppub-sub.external.DecreaseStockCommand();
        // mappings goes here
        OrderApplication.applicationContext.getBean(labshoppub-sub.external.InventoryService.class)
            .decreaseStock(/* get???(), */ decreaseStockCommand);



        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
        // Get request from Order
        //labshoppub-sub.external.Order order =
        //    Application.applicationContext.getBean(labshoppub-sub.external.OrderService.class)
        //    .getOrder(/** mapping value needed */);

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
