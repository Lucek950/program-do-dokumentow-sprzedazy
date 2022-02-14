package database.models.saleDocModel;

import com.j256.ormlite.table.DatabaseTable;
import database.models.BaseModel;
import database.models.Docs;

@DatabaseTable(tableName = "INVOICE_SALE")
public class InvoiceSale extends Docs implements BaseModel {
    public InvoiceSale(){super();}
}
