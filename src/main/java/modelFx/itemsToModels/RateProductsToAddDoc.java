package modelFx.itemsToModels;

import java.io.Serializable;
import java.math.BigDecimal;

public class RateProductsToAddDoc implements Serializable {
    public RateProductsToAddDoc(ListProductsToAddDoc listProductsToAddDoc){
        setRate(listProductsToAddDoc.getRateVat());
        setVat(listProductsToAddDoc.getVat());
        setVolumeNet(listProductsToAddDoc.getVolNet());
        setSumVat(listProductsToAddDoc.getSumVat());
        setVolumeGain();
    }

    public RateProductsToAddDoc(RateProductsToAddDoc rateProductsToAddDoc, double volumeNetPlus, double sumVatPlus){
        setRate(rateProductsToAddDoc.getRate());
        setVat(rateProductsToAddDoc.getVat());
        setVolumeNet(rateProductsToAddDoc.getVolumeNet() + volumeNetPlus);
        setSumVat(rateProductsToAddDoc.getSumVat() + sumVatPlus);
        setVolumeGain();
    }

    private double rate;
    private String vat;
    private double volumeNet;
    private double sumVat;
    private double volumeGain;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public double getVolumeNet() {
        return volumeNet;
    }

    public void setVolumeNet(double volumeNet) {
        this.volumeNet = volumeNet;
    }

    public double getSumVat() {
        return sumVat;
    }

    public void setSumVat(double sumVat) {
        this.sumVat = sumVat;
    }

    public double getVolumeGain() {
        return volumeGain;
    }

    public void setVolumeGain() {
        this.volumeGain = BigDecimal.valueOf(this.sumVat + this.volumeNet).doubleValue();
    }
}
