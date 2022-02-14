package modelFx.entitiesFX.contractorFX;

import javafx.beans.property.*;
import modelFx.entitiesFX.EntitiesFX;

public class ContractorFX extends EntitiesFX {
    private final StringProperty pesel = new SimpleStringProperty();

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }
}
