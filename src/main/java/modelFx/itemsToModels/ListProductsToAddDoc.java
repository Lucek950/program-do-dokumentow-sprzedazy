package modelFx.itemsToModels;

import modelFx.productFX.ProductFX;
import modelFx.supportsModelFX.VatProductsFX;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ListProductsToAddDoc implements Serializable {

    public ListProductsToAddDoc(ProductFX productFX, int quantity, int discount, boolean itsBuyProducts){
        setIdProductType(productFX.getTypeProductFX().getId());
        if(productFX.getDescription() == null)
            setName(productFX.getName());
        else
            setName(productFX.getName().concat(" ").concat(productFX.getDescription()));
        setProductId(productFX.getId());
        setPkwIuCN(productFX.getPkwIuCN());
        setQuantity(quantity);
        setUnit(productFX.getUnitsProductFX().getName());
        setUnitPriceNetBuy(productFX.getBuyNetPrice());
        if(itsBuyProducts){
            setUnitPriceNet(productFX.getBuyNetPrice());
            setUnitPriceGross(productFX.getBuyNetPrice() * productFX.getBuyVatProductFX().getRateVat());
            setVat(productFX.getBuyVatProductFX());
        }
        else {
            setUnitPriceNet(productFX.getSaleNetPrice());
            setUnitPriceGross(productFX.getSaleNetPrice() * productFX.getSaleVatProductFX().getRateVat());
            setVat(productFX.getSaleVatProductFX());
        }
        setDiscount(discount);
        setUnitPriceNetWithDiscount();
        setVolNet();
        setSumVat();
        setVolGross();
        setCost();
    }

    public ListProductsToAddDoc(ListProductsToAddDoc listProductsToAddDoc, int quantity, int discount, String flag){
        setIdProductType(listProductsToAddDoc.getIdProductType());
        setLp(listProductsToAddDoc.getLp());
        setProductId(listProductsToAddDoc.getProductId());
        setName(listProductsToAddDoc.getName());
        setPkwIuCN(listProductsToAddDoc.getPkwIuCN());
        setQuantity(quantity);
        setUnit(listProductsToAddDoc.getUnit());
        setUnitPriceNetBuy(listProductsToAddDoc.getUnitPriceNetBuy());
        setUnitPriceNet(listProductsToAddDoc.getUnitPriceNet());
        setUnitPriceGross(listProductsToAddDoc.getUnitPriceGross());
        if((flag.equals("RS ")) || (flag.equals("RZ "))){
            this.vat = "ZW";
            this.rateVat = 0;
        } else {
            this.vat = listProductsToAddDoc.getVat();
            this.rateVat = listProductsToAddDoc.getRateVat();
        }
        setDiscount(discount);
        setUnitPriceNetWithDiscount();
        setVolNet();
        setSumVat();
        setVolGross();
        setCost();
    }

    public ListProductsToAddDoc(ListProductsToAddDoc listProductsToAddDoc, int lp){
        setIdProductType(listProductsToAddDoc.getIdProductType());
        setLp(lp);
        setProductId(listProductsToAddDoc.getProductId());
        setName(listProductsToAddDoc.getName());
        setPkwIuCN(listProductsToAddDoc.getPkwIuCN());
        setQuantity(listProductsToAddDoc.getQuantity());
        setUnit(listProductsToAddDoc.getUnit());
        setUnitPriceNetBuy(listProductsToAddDoc.getUnitPriceNetBuy());
        setUnitPriceNet(listProductsToAddDoc.getUnitPriceNet());
        setUnitPriceGross(listProductsToAddDoc.getUnitPriceGross());
        this.vat = listProductsToAddDoc.getVat();
        this.rateVat = listProductsToAddDoc.getRateVat();
        setDiscount(listProductsToAddDoc.getDiscount());
        setUnitPriceNetWithDiscount();
        setVolNet();
        setSumVat();
        setVolGross();
        setCost();
    }

    public ListProductsToAddDoc(ListProductsToAddDoc listProductsToAddDoc, int lp, String flag){
        setIdProductType(listProductsToAddDoc.getIdProductType());
        setLp(lp);
        setProductId(listProductsToAddDoc.getProductId());
        setName(listProductsToAddDoc.getName());
        setPkwIuCN(listProductsToAddDoc.getPkwIuCN());
        setQuantity(listProductsToAddDoc.getQuantity());
        setUnit(listProductsToAddDoc.getUnit());
        setUnitPriceNetBuy(listProductsToAddDoc.getUnitPriceNetBuy());
        setUnitPriceNet(listProductsToAddDoc.getUnitPriceNet());
        setUnitPriceGross(listProductsToAddDoc.getUnitPriceGross());
        if((flag.equals("RS ")) || (flag.equals("RZ "))){
            this.vat = "ZW";
            this.rateVat = 0;
        } else{
            this.vat = listProductsToAddDoc.getVat();
            this.rateVat = listProductsToAddDoc.getRateVat();
        }
        setDiscount(listProductsToAddDoc.getDiscount());
        setUnitPriceNetWithDiscount();
        setVolNet();
        setSumVat();
        setVolGross();
        setCost();
    }

    private int lp;
    private int productId;
    private int idProductType;
    private String name;
    private String pkwIuCN;
    private int quantity;
    private String unit;
    private int discount;

    private double unitPriceNetBuy;

    private double unitPriceNet;
    private double unitPriceGross;
    private String vat;
    private double rateVat;

    private double unitPriceNetWithDiscount;
    private double volNet;
    private double sumVat;
    private double volGross;

    private double cost;

    public int getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(int idProductType) {
        this.idProductType = idProductType;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPkwIuCN() {
        return pkwIuCN;
    }

    public void setPkwIuCN(String pkwIuCN) {
        this.pkwIuCN = pkwIuCN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getUnitPriceNetBuy() {
        return unitPriceNetBuy;
    }

    public void setUnitPriceNetBuy(double unitPriceNetBuy) {
        this.unitPriceNetBuy = BigDecimal.valueOf(unitPriceNetBuy).doubleValue();
    }

    public double getUnitPriceNet() {
        return unitPriceNet;
    }

    public void setUnitPriceNet(double unitPriceNet) {
        this.unitPriceNet = BigDecimal.valueOf(unitPriceNet).doubleValue();
    }

    public double getUnitPriceGross() {
        return unitPriceGross;
    }

    public void setUnitPriceGross(double unitPriceGross) {
        this.unitPriceGross = BigDecimal.valueOf(unitPriceGross).doubleValue();
    }

    public String getVat() {
        return vat;
    }

    public void setVat(VatProductsFX vatProductFX) {
        this.vat = vatProductFX.getName();
        this.rateVat = ((vatProductFX.getRateVat() - 1) * 100);
    }

    public double getRateVat() {
        return rateVat;
    }

    public void setUnitPriceNetWithDiscount() {
        this.unitPriceNetWithDiscount = BigDecimal.valueOf(this.unitPriceNet - ((this.unitPriceNet * this.discount)/100))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double getVolNet() {
        return volNet;
    }

    public void setVolNet() {
        this.volNet = BigDecimal.valueOf(this.quantity * this.unitPriceNetWithDiscount)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double getSumVat() {
        return sumVat;
    }

    public void setSumVat() {
        this.sumVat = this.quantity * BigDecimal.valueOf((this.unitPriceNetWithDiscount * this.rateVat) / 100)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double getVolGross() {
        return volGross;
    }

    public void setVolGross() {
        this.volGross = BigDecimal.valueOf(this.sumVat + this.volNet)
                .doubleValue();
    }

    public double getCost() {
        return cost;
    }

    public void setCost() {
        this.cost = BigDecimal.valueOf(this.unitPriceNetBuy * this.quantity)
                .doubleValue();
    }
}
