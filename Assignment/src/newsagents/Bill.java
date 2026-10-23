package newagents;

public class Bill {

    private int receiptId;
    private String orderReference;
    private int driverId;
    private String paymentDate;

    public Bill(int receiptId, String orderReference, int driverId, String paymentDate) {
        this.receiptId = receiptId;
        this.orderReference = orderReference;
        this.driverId = driverId;
        this.paymentDate = paymentDate;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", orderReference='" + orderReference + '\'' +
                ", driverId=" + driverId +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
