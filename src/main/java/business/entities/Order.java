package business.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id;
    private Carport carport;
    private MaterialList materialList;
    private Recipient recipient;
    private final String remarks;
    private int price;
    private LocalDateTime created;

    public Order(int id, Carport carport, Recipient recipient, String remarks, int price, String created) {
        this.id = id;
        this.carport = carport;
        this.recipient = recipient;
        this.remarks = remarks;
        this.price = price;
        this.created = LocalDateTime.parse(created.toString(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public Order(int id, MaterialList materialList, Recipient recipient, String remarks, int price, Long created) {
        this.id = id;
        this.materialList = materialList;
        this.recipient = recipient;
        this.remarks = remarks;
        this.price = price;
        this.created = LocalDateTime.parse(created.toString(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public int getId() {
        return id;
    }

    public Carport getCarport() {
        return carport;
    }

    public MaterialList getMaterialList() {
        return materialList;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setTotalPrice(int totalPrice) {
        this.price = totalPrice;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getDateCreated() {
        return created;
    }

    // TODO: getters for other statuses

}
