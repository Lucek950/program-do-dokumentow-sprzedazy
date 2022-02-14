package database.models.storeDocModel;

import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;
import database.models.Docs;

@DatabaseTable(tableName = "RELEASE_DOC")
public class ReleaseDoc extends Docs implements BaseModel {
    public ReleaseDoc(){super();}
}
