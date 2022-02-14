package database.models.supportTablesModel.typeDocModel;

import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "TYPE_SALES_DOC")
public class TypeSaleDoc extends TypeDoc {
    public TypeSaleDoc() {super();
    }

    public TypeSaleDoc(int id, String name, ArrayList<ProjectDoc> projectDocs) {
        super();
        setId(id);
        setName(name);
        setProjectDocs(projectDocs);
    }
}
