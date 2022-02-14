package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.VatProducts;
import modelFx.supportsModelFX.VatProductsFX;

public class VatProductsConverter {
    public static VatProductsFX convertToVatProductsFX(VatProducts vatProducts){
        VatProductsFX vatProductsFX = new VatProductsFX();
        vatProductsFX.setId(vatProducts.getId());
        vatProductsFX.setName(vatProducts.getName());
        vatProductsFX.setRateVat(vatProducts.getRateVat());
        return vatProductsFX;
    }
}
