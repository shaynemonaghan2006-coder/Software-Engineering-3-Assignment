
package newsagents;

public class Bill {

    private int billId;
    private String paymentStatus;
    private String paymentDate;
    private double totalAmount;

    public Bill(String paymentStatus, String paymentDate, double totalAmount) {
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
    }

    public Bill(int billId, String paymentStatus, String paymentDate, double totalAmount) {
        this.billId = billId;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
