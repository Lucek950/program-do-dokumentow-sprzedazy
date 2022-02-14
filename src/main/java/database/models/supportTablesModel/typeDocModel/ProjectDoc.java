package database.models.supportTablesModel.typeDocModel;

import java.io.Serializable;

public class ProjectDoc implements Serializable {
    private final String name;
    private final String nameToPrint;
    private final String shortcut;

    public ProjectDoc(String name, String nameToPrint, String shortcut) {
        this.name = name;
        this.nameToPrint = nameToPrint;
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public String getNameToPrint() {
        return nameToPrint;
    }

    public String getShortcut() {
        return shortcut;
    }

    @Override
    public String toString() {
        return name;
    }
}
