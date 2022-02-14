package database.models.supportTablesModel;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PAYMENT_METHOD")
public class PaymentMethod extends SupportModel {
    public PaymentMethod() {super();
    }

    public PaymentMethod(int id, String name) {
        super();
        setId(id);
        setName(name);
    }
}
