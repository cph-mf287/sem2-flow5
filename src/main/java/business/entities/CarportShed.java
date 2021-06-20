package business.entities;

import business.persistence.Database;

public class CarportShed {
    private final int width;
    private final int length;

    public CarportShed(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public void loadMaterials(Database database) {
        // TODO: load materials
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

}
