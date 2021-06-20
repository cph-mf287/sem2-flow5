package business.entities;

import java.util.ArrayList;
import java.util.List;

public class MaterialList {
    private final List<MaterialOrder> entries;
    private int totalCost;

    public MaterialList() {
        entries = new ArrayList<>();
    }

    public void add(Material material, int amount) {
        entries.add(new MaterialOrder(material, amount));
        totalCost += material.getPrice() * amount; // TODO: account for length of the material
    }

    public List<MaterialOrder> getEntries() {
        return entries;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String toString() {
        String str = "<table>\n" + "<thead>\n" + "\t<tr><th>Træ & Tagplader</th><th>Længde</th><th>Antal</th><th>Beskrivelse</th><th>Stykpris</th></tr>\n" + "</thead>\n" + "<tbody>\n";
        for (MaterialOrder entry : entries) {
            Material material = entry.getMaterial();
            int amount = entry.getAmount();
            str+= String.format("\t<tr><td>%s</td><td style=\"text-align: right\">%s cm</td><td style=\"text-align: right\">%s stk</td><td>%s</td><td style=\"text-align: right\">%d,%02d kr./stk</td></tr>\n", material.getName(), material.getLength(), amount, material.getDescription(), material.getPrice() / 100, material.getPrice() % 100);
        }
        str+= String.format("\t<tr><td></td><td></td><td></td><td></td><td><b>%d,%02d kr.</b></td></tr>\n", totalCost / 100, totalCost % 100);
        str+= "</tbody>\n" + "</table>\n";
        return str;
    }

}
