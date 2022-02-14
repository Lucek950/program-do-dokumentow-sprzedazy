package modelFx.supportsModelFX;

import javafx.beans.property.*;

public class VatProductsFX extends SupportModelFX{
    public VatProductsFX() {super();
    }

    private DoubleProperty rateVat = new SimpleDoubleProperty();

    public double getRateVat() {
        return rateVat.get();
    }

    public DoubleProperty rateVatProperty() {
        return rateVat;
    }

    public void setRateVat(double rateVat) {
        this.rateVat.set(rateVat);
    }
}
