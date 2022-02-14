package database.models.supportTablesModel;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "GTU_PRODUCTS")
public class GtuProducts extends SupportModel {
    public GtuProducts() {super();}

    public GtuProducts(int id , String value){
        super();
        setId(id);
        setName(value);
    }
}