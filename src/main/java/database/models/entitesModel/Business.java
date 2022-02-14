package database.models.entitesModel;

import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;

@DatabaseTable(tableName = "BUSINESS")
public class Business extends Entities implements BaseModel {
    public Business() {super(); }
}
