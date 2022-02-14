package modelFx.supportsModelFX;

import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeDocFX extends SupportModelFX{
    private SimpleObjectProperty<ObservableList<ProjectDoc>> projectDocs = new SimpleObjectProperty<>(FXCollections.observableArrayList());

    public ObservableList<ProjectDoc> getProjectDocs() {
        return projectDocs.get();
    }

    public SimpleObjectProperty<ObservableList<ProjectDoc>> projectDocsProperty() {
        return projectDocs;
    }

    public void setProjectDocs(ObservableList<ProjectDoc> projectDocList) {
        this.projectDocs.set(projectDocList);
    }
}
