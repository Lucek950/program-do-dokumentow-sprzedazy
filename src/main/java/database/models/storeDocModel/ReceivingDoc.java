package database.models.storeDocModel;

import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;
import database.models.Docs;

@DatabaseTable(tableName = "RECEIVING_DOC")
public class ReceivingDoc extends Docs implements BaseModel {
    public ReceivingDoc(){super();}
}
