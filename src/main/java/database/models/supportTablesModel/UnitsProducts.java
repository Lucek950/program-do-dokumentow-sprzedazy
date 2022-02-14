package database.models.supportTablesModel;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UNITS_PRODUCTS")
public class UnitsProducts extends SupportModel {
    public UnitsProducts() {super();}

    public UnitsProducts(int id , String value){
        super();
        setId(id);
        setName(value);
    }
}
