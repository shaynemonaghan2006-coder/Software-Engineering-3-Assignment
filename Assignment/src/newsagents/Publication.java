package newsagent;

public class Publication {

    private int publicationId;
    private String publicationName;
    private double price;
    private String frequency;
    private String type;
    private int stockQuantity;

    
    public Publication(int publicationId, String publicationName,
                       double price, String frequency,
                       String type, int stockQuantity) {
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
