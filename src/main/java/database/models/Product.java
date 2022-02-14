package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import database.models.supportTablesModel.GtuProducts;
import database.models.supportTablesModel.TypeProducts;
import database.models.supportTablesModel.UnitsProducts;
import database.models.supportTablesModel.VatProducts;

@DatabaseTable(tableName = "PRODUCTS")
public class Product implements BaseModel {
    public Product(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "TYPE_PRODUCT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private TypeProducts typeProduct;

    @DatabaseField(columnName = "NAME")
    private String name;

    @DatabaseField(columnName = "QUANTITY")
    private double quantity;

    @DatabaseField(columnName = "UNITS_PRODUCT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private UnitsProducts unitsProduct;

    @DatabaseField(columnName = "VAT_PRODUCT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private VatProducts vatProduct;

    @DatabaseField(columnName = "GTU_PRODUCT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private GtuProducts gtuProduct;

    @DatabaseField(columnName = "PRODUCT_CODE")
    private String productCode;

    @DatabaseField(columnName = "NR_CATALOG")
    private String nrCatalog;

    @DatabaseField(columnName = "PKWIU_CN")
    private String pkwIuCN;

    @DatabaseField(columnName = "BARCODE")
    private String barcode;

    @DatabaseField(columnName = "BUY_NET_PRICE")
    private double buyNetPrice;

    @DatabaseField(columnName = "BUY_VAT_PRODUCT", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private VatProducts buyVatProduct;

    @DatabaseField(columnName = "BUY_GROSS_PRICE")
    private String buyGrossPrice;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "SALE_NET_PRICE")
    private double saleNetPrice;

    @DatabaseField(columnName = "SALE_GROSS_PRICE")
    private String saleGrossPrice;

    @DatabaseField(columnName = "OVERHEAD")
    private String overhead;

    @DatabaseField(columnName = "GAIN_NET")
    private String gainNet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeProducts getTypeProduct() { return typeProduct;
    }

    public void setTypeProduct(TypeProducts typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UnitsProducts getUnitsProduct() {
        return unitsProduct;
    }

    public void setUnitsProduct(UnitsProducts unitsProduct) {
        this.unitsProduct = unitsProduct;
    }

    public VatProducts getVatProduct() {
        return vatProduct;
    }

    public void setVatProduct(VatProducts vatProduct) {
        this.vatProduct = vatProduct;
    }

    public GtuProducts getGtuProduct() {
        return gtuProduct;
    }

    public void setGtuProduct(GtuProducts gtuProduct) {
        this.gtuProduct = gtuProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNrCatalog() {
        return nrCatalog;
    }

    public void setNrCatalog(String nrCatalog) {
        this.nrCatalog = nrCatalog;
    }

    public String getPkwIuCN() {
        return pkwIuCN;
    }

    public void setPkwIuCN(String pkwIuCN) {
        this.pkwIuCN = pkwIuCN;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getBuyNetPrice() {
        return buyNetPrice;
    }

    public void setBuyNetPrice(double buyNetPrice) {
        this.buyNetPrice = buyNetPrice;
    }

    public VatProducts getBuyVatProduct() {
        return buyVatProduct;
    }

    public void setBuyVatProduct(VatProducts buyVatProduct) {
        this.buyVatProduct = buyVatProduct;
    }

    public String getBuyGrossPrice() {
        return buyGrossPrice;
    }

    public void setBuyGrossPrice(String buyGrossPrice) {
        this.buyGrossPrice = buyGrossPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSaleNetPrice() {
        return saleNetPrice;
    }

    public void setSaleNetPrice(double saleNetPrice) {
        this.saleNetPrice = saleNetPrice;
    }

    public String getSaleGrossPrice() {
        return saleGrossPrice;
    }

    public void setSaleGrossPrice(String saleGrossPrice) {
        this.saleGrossPrice = saleGrossPrice;
    }

    public String getOverhead() {
        return overhead;
    }

    public void setOverhead(String overhead) {
        this.overhead = overhead;
    }

    public String getGainNet() {
        return gainNet;
    }

    public void setGainNet(String gainNet) {
        this.gainNet = gainNet;
    }
}
