
package newsagents;

public class Publication {

    private int publicationId;
    private String publicationName;
    private double price;
    private String frequency;
    private String type;
    private int stockQuantity;

    public Publication(int publicationId, String publicationName, double price, String frequency, String type, int stockQuantity) {
        this.publicationId = publicationId;
        this.publicationName = publicationName;
        this.price = price;
        this.frequency = frequency;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }


    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public boolean verifyPublicationId(int id) {
        return id > 0;
    }

    public boolean verifyPublicationName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean verifyPrice(double price) {
        return Double.isFinite(price) && price >= 0;
    }

    public boolean verifyFrequency(String frequency) {
        return frequency != null && !frequency.trim().isEmpty();
    }

    public boolean verifyType(String type) {
        return type != null && !type.trim().isEmpty();
    }

    public boolean verifyStockQuantity(int quantity) {
        return quantity >= 0;
    }

    public boolean addStock(int quantity) {
        if (quantity <= 0 || quantity > Integer.MAX_VALUE - stockQuantity
                || stockQuantity < 0) {
            return false;
        }

        stockQuantity += quantity;
        return true;
    }

    public boolean removeStock(int quantity) {
        if (quantity <= 0 || quantity > stockQuantity
                || stockQuantity < 0) {
            return false;
        }

        stockQuantity -= quantity;
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "publicationId=" + publicationId +
                ", publicationName='" + publicationName + '\'' +
                ", price=" + price +
                ", frequency='" + frequency + '\'' +
                ", type='" + type + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
