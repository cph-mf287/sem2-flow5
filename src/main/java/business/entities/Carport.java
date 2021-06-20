package business.entities;

import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;

public class Carport {
    private final int width;
    private final int length;
    private final int height;
    private final int roofType;
    private CarportShed shed;
    private Material pillar;
    private Material frame;
    private Material rafter;
    private Material roof;

    public Carport(int width, int length, int height, int roofType) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.roofType = roofType;
    }

    public void loadMaterials(Database database) throws UserException {
        MaterialFacade materialFacade = new MaterialFacade(database);

        pillar = materialFacade.getPillarMaterial(height);
        frame = materialFacade.getFrameMaterial(length);
        rafter = materialFacade.getRafterMaterial(width);
        roof = materialFacade.getRoofMaterial();
    }

    public Material getPillarMaterial() {
        return pillar;
    }

    public Material getFrameMaterial() {
        return frame;
    }

    public Material getRafterMaterial() {
        return rafter;
    }

    public Material getRoofMaterial() {
        return roof;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getRoofType() {
        return roofType;
    }

    public void setShed(CarportShed shed) {
        this.shed = shed;
    }

    public boolean hasShed() {
        return shed != null;
    }

    public CarportShed getShed() {
        return shed;
    }

}
