package modelFx.productFX;

import modelFx.supportsModelFX.GtuProductsFX;
import modelFx.supportsModelFX.UnitsProductsFX;
import modelFx.supportsModelFX.TypeProductsFX;
import modelFx.supportsModelFX.VatProductsFX;
import javafx.beans.property.*;

public class ProductFX {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<TypeProductsFX> typeProductFX = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty quantity = new SimpleDoubleProperty();
    private final ObjectProperty<UnitsProductsFX> unitsProductFX = new SimpleObjectProperty<>();
    private final ObjectProperty<VatProductsFX> saleVatProductFX = new SimpleObjectProperty<>();
    private final ObjectProperty<GtuProductsFX> gtuProductFX = new SimpleObjectProperty<>();
    private final StringProperty productCode = new SimpleStringProperty();
    private final StringProperty nrCatalog = new SimpleStringProperty();
    private final StringProperty pkwIuCN = new SimpleStringProperty();
    private final StringProperty barcode = new SimpleStringProperty();
    private final DoubleProperty buyNetPrice = new SimpleDoubleProperty();
    private final ObjectProperty<VatProductsFX> buyVatProductFX = new SimpleObjectProperty<>();
    private final StringProperty buyGrossPrice = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty saleNetPrice = new SimpleDoubleProperty();
    private final StringProperty saleGrossPrice = new SimpleStringProperty();
    private final StringProperty overhead = new SimpleStringProperty();
    private final StringProperty gainNet = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public TypeProductsFX getTypeProductFX() {
        return typeProductFX.get();
    }

    public ObjectProperty<TypeProductsFX> typeProductFXProperty() {
        return typeProductFX;
    }

    public void setTypeProductFX(TypeProductsFX typeProductsFX) {
        this.typeProductFX.set(typeProductsFX);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public DoubleProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    public UnitsProductsFX getUnitsProductFX() {
        return unitsProductFX.get();
    }

    public ObjectProperty<UnitsProductsFX> unitsProductFXProperty() {
        return unitsProductFX;
    }

    public void setUnitsProductFX(UnitsProductsFX unitsProductFX) {
        this.unitsProductFX.set(unitsProductFX);
    }

    public VatProductsFX getSaleVatProductFX() {
        return saleVatProductFX.get();
    }

    public ObjectProperty<VatProductsFX> saleVatProductFXProperty() {
        return saleVatProductFX;
    }

    public void setSaleVatProductFX(VatProductsFX saleVatProductFX) {
        this.saleVatProductFX.set(saleVatProductFX);
    }

    public GtuProductsFX getGtuProductFX() {
        return gtuProductFX.get();
    }

    public ObjectProperty<GtuProductsFX> gtuProductFXProperty() {
        return gtuProductFX;
    }

    public void setGtuProductFX(GtuProductsFX gtuProductFX) {
        this.gtuProductFX.set(gtuProductFX);
    }

    public String getProductCode() {
        return productCode.get();
    }

    public StringProperty productCodeProperty() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode.set(productCode);
    }

    public String getNrCatalog() {
        return nrCatalog.get();
    }

    public StringProperty nrCatalogProperty() {
        return nrCatalog;
    }

    public void setNrCatalog(String nrCatalog) {
        this.nrCatalog.set(nrCatalog);
    }

    public String getPkwIuCN() {
        return pkwIuCN.get();
    }

    public StringProperty pkwIuCNProperty() {
        return pkwIuCN;
    }

    public void setPkwIuCN(String pkwIuCN) {
        this.pkwIuCN.set(pkwIuCN);
    }

    public String getBarcode() {
        return barcode.get();
    }

    public StringProperty barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public double getBuyNetPrice() {
        return buyNetPrice.get();
    }

    public DoubleProperty buyNetPriceProperty() {
        return buyNetPrice;
    }

    public void setBuyNetPrice(double buyNetPrice) {
        this.buyNetPrice.set(buyNetPrice);
    }

    public VatProductsFX getBuyVatProductFX() {
        return buyVatProductFX.get();
    }

    public ObjectProperty<VatProductsFX> buyVatProductFXProperty() {
        return buyVatProductFX;
    }

    public void setBuyVatProductFX(VatProductsFX buyVatProductFX) {
        this.buyVatProductFX.set(buyVatProductFX);
    }

    public String getBuyGrossPrice() {
        return buyGrossPrice.get();
    }

    public StringProperty buyGrossPriceProperty() {
        return buyGrossPrice;
    }

    public void setBuyGrossPrice(String buyGrossPrice) {
        this.buyGrossPrice.set(buyGrossPrice);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getSaleNetPrice() {
        return saleNetPrice.get();
    }

    public DoubleProperty saleNetPriceProperty() {
        return saleNetPrice;
    }

    public void setSaleNetPrice(double saleNetPrice) {
        this.saleNetPrice.set(saleNetPrice);
    }

    public String getSaleGrossPrice() {
        return saleGrossPrice.get();
    }

    public StringProperty saleGrossPriceProperty() {
        return saleGrossPrice;
    }

    public void setSaleGrossPrice(String saleGrossPrice) {
        this.saleGrossPrice.set(saleGrossPrice);
    }

    public String getOverhead() {
        return overhead.get();
    }

    public StringProperty overheadProperty() {
        return overhead;
    }

    public void setOverhead(String overhead) {
        this.overhead.set(overhead);
    }

    public String getGainNet() {
        return gainNet.get();
    }

    public StringProperty gainNetProperty() {
        return gainNet;
    }

    public void setGainNet(String gainNet) {
        this.gainNet.set(gainNet);
    }
}
