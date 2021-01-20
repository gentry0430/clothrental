package clothrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Cancellation_table")
public class Cancellation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        CancelCanceled cancelCanceled = new CancelCanceled();
        BeanUtils.copyProperties(this, cancelCanceled);
        cancelCanceled.publishAfterCommit();


    }

    @PrePersist
    public void onPrePersist(){
        System.out.println("################# Order cancellation start");

        try {
            Thread.currentThread().sleep((long) (500 + Math.random() * 220));
            // Thread.currentThread().sleep((long) (800 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
