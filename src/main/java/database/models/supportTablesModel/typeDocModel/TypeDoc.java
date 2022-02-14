package database.models.supportTablesModel.typeDocModel;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import database.models.supportTablesModel.SupportModel;

import java.util.ArrayList;

public class TypeDoc extends SupportModel {
    public TypeDoc() {super();
    }

    @DatabaseField(dataType = DataType.SERIALIZABLE, columnName = "PROJECT_DOC")
    private ArrayList<ProjectDoc> projectDocs;

    public ArrayList<ProjectDoc> getProjectDocs() {
        return projectDocs;
    }

    public void setProjectDocs(ArrayList<ProjectDoc> projectDocs) {
        this.projectDocs = projectDocs;
    }
}
