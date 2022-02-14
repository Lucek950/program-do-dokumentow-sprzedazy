package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.UnitsProducts;
import modelFx.supportsModelFX.UnitsProductsFX;

public class UnitsProductsConverter {
    public static UnitsProductsFX convertToUnitsProductsFX(UnitsProducts unitsProducts){
        UnitsProductsFX unitsProductsFX = new UnitsProductsFX();
        unitsProductsFX.setId(unitsProducts.getId());
        unitsProductsFX.setName(unitsProducts.getName());
        return unitsProductsFX;
    }

    public static UnitsProducts convertToUnitsProducts(UnitsProductsFX unitsProductsFX){
        UnitsProducts unitsProducts = new UnitsProducts();
        unitsProducts.setId(unitsProductsFX.getId());
        unitsProducts.setName(unitsProductsFX.getName());
        return unitsProducts;
    }
}

