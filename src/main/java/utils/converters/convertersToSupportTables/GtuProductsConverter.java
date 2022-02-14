package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.GtuProducts;
import modelFx.supportsModelFX.GtuProductsFX;

public class GtuProductsConverter {
    public static GtuProductsFX convertToGtuProductsFX(GtuProducts gtuProducts){
        GtuProductsFX gtuProductsFX = new GtuProductsFX();
        gtuProductsFX.setId(gtuProducts.getId());
        gtuProductsFX.setName(gtuProducts.getName());
        return gtuProductsFX;
    }
}
