package business.entities;

import business.exceptions.ValueNotAssignedException;
import business.services.CarportUtil;

public class CarportDrawing extends SvgImage {
    private final Material pillar;
    private final Material frame;
    private final Material rafter;
    private final Material roof;
    private final int width;
    private final int length;
    private final int height;
    private int indent;
    private float gap;

    public CarportDrawing(Carport carport) {
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

    public void draw() throws ValueNotAssignedException {
        if (gap == 0) throw new ValueNotAssignedException("gap", "setApproximateGap( <gap> )", "createSvgImage( )");
        drawRoof();
        drawSideFrames(indent);
        drawRafters(CarportUtil.countAdditionalRafters(rafter, length, gap));
        drawPillars(indent);
        setViewBox(-50, -50, (length + 100), (width + 100));
    }

    public void drawPillars(int y0) {
        float y1 = width - y0 - pillar.getThickness();
        float x0 = CarportUtil.snapPillarToFirstRafter(pillar, rafter) + 2*gap;
        float x1 = CarportUtil.snapPillarToLastRafter(pillar, rafter, length) - gap/2;

        this.addRect(x0, y0, pillar.getThickness(), pillar.getThickness(), "none");
        this.addRect(x0, y1, pillar.getThickness(), pillar.getThickness(), "none");
        this.addRect(x1, y0, pillar.getThickness(), pillar.getThickness(), "none");
        this.addRect(x1, y1, pillar.getThickness(), pillar.getThickness(), "none");

        float dx = x1 - x0;
        if (dx > width - 2*y0) {
            float x = x0 + ((int) (dx/gap))*gap/2;
            this.addRect(x, y0, pillar.getThickness(), pillar.getThickness(), "none");
            this.addRect(x, y1, pillar.getThickness(), pillar.getThickness(), "none");
        }
    }

    public void drawSideFrames(int inset) {
        float y0 = CarportUtil.snapFrameToLeftPillar(frame, pillar, inset);
        float y1 = CarportUtil.snapFrameToRightPillar(frame, pillar, inset, width);

        this.addRect(0, y0, length, frame.getThickness());
        this.addRect(0, y1, length, frame.getThickness());
    }

    public void drawRafters(int dividend) {
        for (int i = 0; i <= dividend; i++)
            this.addRect(i*gap, 0, rafter.getThickness(), width);
    }

    public void drawRoof() {
        this.addRect(0, 0, length, width, "none");
    }

}
