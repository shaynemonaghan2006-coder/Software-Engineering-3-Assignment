
package newsagents;

public class DeliveryDocket {
	private int docketId;
    private String deliveryDate;
    private String deliveryStatus;
    private int orderId;

    public DeliveryDocket(int orderId, String deliveryDate, String deliveryStatus) {
    	this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public int getOrderId() {
    	return orderId;
    }
    
    public void setOrderId(int orderId) {
    	this.orderId = orderId;
    }
    
    public int getDocketId() {
    	return docketId;
    }
    
    public void setDocketId(int docketId) {
    	this.docketId = docketId;
    }


    @Override
    public String toString() {
        return "DeliveryDocket{" +
                "deliveryDate='" + deliveryDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
