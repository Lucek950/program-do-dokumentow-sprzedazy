package database.models.supportTablesModel;

import com.j256.ormlite.field.DatabaseField;
import database.models.BaseModel;

public class SupportModel implements BaseModel {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
