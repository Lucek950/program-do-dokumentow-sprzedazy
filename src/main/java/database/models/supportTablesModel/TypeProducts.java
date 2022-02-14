package database.models.supportTablesModel;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TYPE_PRODUCTS")
public class TypeProducts extends SupportModel {
    public TypeProducts() {super();}

    public TypeProducts(int id , String value){
        super();
        setId(id);
        setName(value);
    }
}
