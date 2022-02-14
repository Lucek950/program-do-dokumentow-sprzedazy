package database.models.supportTablesModel;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import database.models.Product;

import java.io.Serializable;

@DatabaseTable(tableName = "VAT_PRODUCTS")
public class VatProducts extends SupportModel implements Serializable {
    public VatProducts() {super();}

    public VatProducts(int id , String name, int rateVat){
        super();
        setId(id);
        setName(name);
        setRateVat(rateVat);
    }

    @DatabaseField(columnName = "RATE_VAT", canBeNull = false)
    private double rateVat;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Product> products;

    public double getRateVat() {
        return rateVat;
    }

    public void setRateVat(double rateVat) {
        this.rateVat = (rateVat / 100) + 1;
    }
}