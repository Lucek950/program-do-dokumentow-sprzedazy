package utils;

import javafx.scene.control.*;

import java.util.Optional;

public class DialogUtils {

    private static ButtonType confirmBtn;
    public static ButtonType getConfirmBtn() {
        return confirmBtn;
    }

    public static void dialogAboutApp(){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("about");
        infoAlert.setHeaderText("about.header");
        infoAlert.setContentText("about.content");
        infoAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy napewno wykonaæ tê operacjê?");
        confirmBtn = new ButtonType("Wykonaj", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cofnij", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmBtn, cancelButton);
        return alert.showAndWait();
}

    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("error.title");
        errorAlert.setHeaderText("error.message");

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();
    }

    public static void infoDialog(String info){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Informacja");
        infoAlert.setHeaderText(info);
        infoAlert.showAndWait();
    }

    public static String editDialog(String value){
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle("Edycja");
        dialog.setHeaderText("Wpisz now¹ nazwê");
        Optional<String> result = dialog.showAndWait();
        //czy jest wartoœæ
        return result.orElse(null);
    }
}
