package business.entities;

public class Material {
    private final int id;
    private final String name;
    private final int width;
    private final int thickness;
    private int length;
    private final int price;
    private String description;

    public Material(int id, String name, int thickness, int width, int length, int price) {
        this.id = id;
        this.name = name;
        this.thickness = thickness;
        this.width = width;
        this.length = length;
        this.price = price;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getThickness() {
        return thickness;
    }

    public int getLength() {
        return length;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
