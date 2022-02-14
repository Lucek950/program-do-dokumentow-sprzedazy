package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.TypeProducts;
import modelFx.supportsModelFX.TypeProductsFX;

public class TypeProductsConverter {
    public static TypeProductsFX convertToTypeProductsFX(TypeProducts typeProducts){
        TypeProductsFX typeProductsFX = new TypeProductsFX();
        typeProductsFX.setId(typeProducts.getId());
        typeProductsFX.setName(typeProducts.getName());
        return typeProductsFX;
    }
}
