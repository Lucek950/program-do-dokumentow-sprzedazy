package utils.converters;

import database.models.Product;
import modelFx.productFX.ProductFX;
import utils.converters.convertersToSupportTables.GtuProductsConverter;
import utils.converters.convertersToSupportTables.UnitsProductsConverter;
import utils.converters.convertersToSupportTables.TypeProductsConverter;
import utils.converters.convertersToSupportTables.VatProductsConverter;

public class ProductConverter {
    public static Product convertToProduct(ProductFX productFX){
        Product product = new Product();
        product.setId(productFX.getId());
        product.setQuantity(productFX.getQuantity());
        product.setName(productFX.getName());
        product.setProductCode(productFX.getProductCode());
        product.setNrCatalog(productFX.getNrCatalog());
        product.setPkwIuCN(productFX.getPkwIuCN());
        product.setBarcode(productFX.getBarcode());
        product.setBuyNetPrice(productFX.getBuyNetPrice());
        product.setBuyGrossPrice(productFX.getBuyGrossPrice());
        product.setDescription(productFX.getDescription());
        product.setSaleNetPrice(productFX.getSaleNetPrice());
        product.setSaleGrossPrice(productFX.getSaleGrossPrice());
        product.setOverhead(productFX.getOverhead());
        product.setGainNet(productFX.getGainNet());
        return product;
    }

    public static ProductFX convertToProductFX(Product product){
        ProductFX productFX = new ProductFX();
        productFX.setId(product.getId());
        productFX.setTypeProductFX(TypeProductsConverter.convertToTypeProductsFX(product.getTypeProduct()));
        productFX.setQuantity(product.getQuantity());
        productFX.setName(product.getName());
        productFX.setUnitsProductFX(UnitsProductsConverter.convertToUnitsProductsFX(product.getUnitsProduct()));
        productFX.setSaleVatProductFX(VatProductsConverter.convertToVatProductsFX(product.getVatProduct()));
        productFX.setGtuProductFX(GtuProductsConverter.convertToGtuProductsFX(product.getGtuProduct()));
        productFX.setProductCode(product.getProductCode());
        productFX.setNrCatalog(product.getNrCatalog());
        productFX.setPkwIuCN(product.getPkwIuCN());
        productFX.setBarcode(product.getBarcode());
        productFX.setBuyNetPrice(product.getBuyNetPrice());
        productFX.setBuyVatProductFX(VatProductsConverter.convertToVatProductsFX(product.getBuyVatProduct()));
        productFX.setBuyGrossPrice(product.getBuyGrossPrice());
        productFX.setDescription(product.getDescription());
        productFX.setSaleNetPrice(product.getSaleNetPrice());
        productFX.setSaleGrossPrice(product.getSaleGrossPrice());
        productFX.setOverhead(product.getOverhead());
        productFX.setGainNet(product.getGainNet());
        return productFX;
    }
}