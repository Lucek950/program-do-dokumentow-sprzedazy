package utils;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

public class FxmlUtils {
    private static Controller controller;

    public static Controller getController() {
        return controller;
    }

    public static Parent fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        Parent parent;
        try {
            parent = loader.load();
            controller = loader.getController();
            return parent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
    }

    public static void disabledComponentWhenNotSelectRow(TableView<?> tableView, Node component){
        component.disableProperty().set(tableView.selectionModelProperty().get().selectedItemProperty().get() == null);
    }
}
