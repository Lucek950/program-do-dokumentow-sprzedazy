package database.models.supportTablesModel.typeDocModel;

import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "TYPE_STORAGE_DOC")
public class TypeStoreDoc extends TypeDoc {
    public TypeStoreDoc() {super();
    }

    public TypeStoreDoc(int id, String name, ArrayList<ProjectDoc> projectDocs) {
        super();
        setId(id);
        setName(name);
        setProjectDocs(projectDocs);
    }
}
