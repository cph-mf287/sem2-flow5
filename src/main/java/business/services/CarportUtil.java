package business.services;

import business.entities.Material;

public class CarportUtil {

    public static int snapPillarToFirstRafter(Material pillar, Material rafter) {
        return rafter.getThickness()/2 - pillar.getThickness()/2;
    }

    public static int snapPillarToLastRafter(Material pillar, Material rafter, int length) {
        return length - rafter.getThickness()/2 - pillar.getThickness()/2;
    }

    public static int snapFrameToLeftPillar(Material frame, Material pillar, int inset) {
        return inset + pillar.getThickness()/2 - frame.getThickness()/2;
    }

    public static int snapFrameToRightPillar(Material frame, Material pillar, int inset, int width) {
        return width - inset - pillar.getThickness()/2 - frame.getThickness()/2;
    }

    // how many rafters (in addition to the first)?
    public static int countAdditionalRafters(Material rafter, int length, float gap) {
        return Math.round((length - rafter.getThickness()) / gap);
    }

}
