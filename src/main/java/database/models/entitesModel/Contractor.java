package database.models.entitesModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;

@DatabaseTable(tableName = "CONTRACTORS")
public class Contractor extends Entities implements BaseModel {
    public Contractor(){super();}

    @DatabaseField(columnName = "PESEL")
    private String pesel;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
