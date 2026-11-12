
package newsagents;

public class DeliveryDocket {

    private int docketId;
    private String deliveryDate;
    private String deliveryStatus;

    public DeliveryDocket(String deliveryDate, String deliveryStatus) {
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDocketId() {
        return docketId;
    }

    public void setDocketId(int docketId) {
        this.docketId = docketId;
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
                ", deliveryDate='" + deliveryDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
