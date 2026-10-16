package newagents;

public class DeliveryDocket {

    private int docketId;
    private int orderId;
    private String deliveryDate;
    private String deliveryStatus;

    public DeliveryDocket(int docketId, int orderId,
            String deliveryDate, String deliveryStatus) {
        this.docketId = docketId;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDocketId() {
        return docketId;
    }

    public void setDocketId(int docketId) {
        this.docketId = docketId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "DeliveryDocket{" +
                "docketId=" + docketId +
                ", orderId=" + orderId +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}