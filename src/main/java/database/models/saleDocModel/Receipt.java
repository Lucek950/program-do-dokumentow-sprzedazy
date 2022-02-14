package database.models.saleDocModel;

import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;
import database.models.Docs;

@DatabaseTable(tableName = "RECEIPT")
public class Receipt extends Docs implements BaseModel {
    public Receipt(){super();}
}