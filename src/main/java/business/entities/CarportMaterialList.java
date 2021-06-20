package business.entities;

import business.exceptions.ValueNotAssignedException;
import business.services.CarportUtil;

public class CarportMaterialList extends MaterialList {
    private final Material pillar;
    private final Material frame;
    private final Material rafter;
    private final Material roof;
    private final int width;
    private final int length;
    private final int height;
    private int indent;
    private float gap;

    public CarportMaterialList(Carport carport) {
        width = carport.getWidth()*10;
        length = carport.getLength()*10;
        height = carport.getHeight()*10;

        pillar = carport.getPillarMaterial();
        frame = carport.getFrameMaterial();
        rafter = carport.getRafterMaterial();
        roof = carport.getRoofMaterial();
    }

    public void setPillarIndent(int indent) {
        this.indent = indent;
    }

    public void setApproximateGapBetweenRafters(float gap) throws ValueNotAssignedException {
        if (width == 0) throw new ValueNotAssignedException("width", "setWidth( <width> )", "setApproximateGap( <gap> )");
        if (length == 0) throw new ValueNotAssignedException("length", "setLength( <length> )", "setApproximateGap( <gap> )");
        this.gap = (float) (length - rafter.getThickness()) / CarportUtil.countAdditionalRafters(rafter, length, gap); // what would the actual gap be?
    }

    public void load() throws ValueNotAssignedException {
        if (gap == 0) throw new ValueNotAssignedException("gap", "setApproximateGap( <gap> )", "createMaterialList( )");
        float x0 = CarportUtil.snapPillarToFirstRafter(pillar, rafter) + 2*gap;
        float x1 = CarportUtil.snapPillarToLastRafter(pillar, rafter, length) - gap/2;
        if (x1 - x0 >= width - 2* indent) this.add(pillar, 6);
        else this.add(pillar, 4);
        this.add(frame, 2);
        this.add(rafter, 1 + CarportUtil.countAdditionalRafters(rafter, length, gap)); // remember to include the first rafter

        Material plateA = roof;
        plateA.setLength(length/10);
        this.add(plateA, 6);

        Material plateB = roof;
        plateB.setLength(width/10);
        this.add(plateB, 6);
    }

}
