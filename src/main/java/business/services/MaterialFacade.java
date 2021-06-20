package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

public class MaterialFacade {
    private final MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public Material getPillarMaterial() throws UserException {
        return materialMapper.getCarportMaterial(1, 0);
    }

    public Material getPillarMaterial(int length) throws UserException {
        return materialMapper.getCarportMaterial(1, length);
    }

    public Material getFrameMaterial() throws UserException {
        return materialMapper.getCarportMaterial(2, 0);
    }

    public Material getFrameMaterial(int length) throws UserException {
        return materialMapper.getCarportMaterial(2, length);
    }

    public Material getRafterMaterial() throws UserException {
        return materialMapper.getCarportMaterial(3, 0);
    }

    public Material getRafterMaterial(int length) throws UserException {
        return materialMapper.getCarportMaterial(3, length);
    }

    public Material getRoofMaterial() throws UserException {
        return materialMapper.getCarportMaterial(4, 0);
    }

    public Material getRoofMaterial(int length) throws UserException {
        return materialMapper.getCarportMaterial(4, length);
    }

}
