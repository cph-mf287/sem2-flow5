package business.entities;

public class MaterialOrder {
    private final Material material;
    private final int amount;

    public MaterialOrder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

}
