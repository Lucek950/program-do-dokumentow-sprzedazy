package utils;

import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class UtilsProject {
    public static Date convertToDate(LocalDate localDate){
        if (localDate == null)
            return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date){
        if (date == null)
            return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static ArrayList<ListProductsToAddDoc> convertToArrayListProducts(ObservableList<ListProductsToAddDoc> observableList){
        return new ArrayList<>(observableList);
    }

    public static ObservableList<ListProductsToAddDoc> convertToObservableListProducts(ArrayList<ListProductsToAddDoc> listProductFXArrayList){
        ObservableList<ListProductsToAddDoc> observableList = FXCollections.observableArrayList();
        observableList.setAll(listProductFXArrayList);
        return observableList;
    }

    public static ArrayList<RateProductsToAddDoc> convertToArrayListRateProducts(ObservableList<RateProductsToAddDoc> observableList){
        return new ArrayList<>(observableList);
    }

    public static ObservableList<RateProductsToAddDoc> convertToObservableListRateProducts(ArrayList<RateProductsToAddDoc> listProductFXArrayList){
        ObservableList<RateProductsToAddDoc> observableList = FXCollections.observableArrayList();
        observableList.setAll(listProductFXArrayList);
        return observableList;
    }

    public static ArrayList<ProjectDoc> convertToArrayListInvoiceType(ObservableList<ProjectDoc> observableList){
        return new ArrayList<>(observableList);
    }

    public static ObservableList<ProjectDoc> convertToObservableListInvoiceType(ArrayList<ProjectDoc> listProductFXArrayList){
        ObservableList<ProjectDoc> observableList = FXCollections.observableArrayList();
        observableList.setAll(listProductFXArrayList);
        return observableList;
    }

    public static void setStageSize(Stage stage, int width, int height){
        stage.setWidth(width);
        stage.setHeight(height);
        if(width > Toolkit.getDefaultToolkit().getScreenSize().width - 1)
            stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().width - 1);
        if(height > Toolkit.getDefaultToolkit().getScreenSize().height - 30)
            stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().height - 30);
    }

    public static Button createButton(String path){
        Button button = new Button();
        Image image = new Image(Objects.requireNonNull(UtilsProject.class.getResource(path)).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }

    public static void selectAllInTextFieldWhenClickOnMouse(TextField textField){
        textField.setOnMouseClicked(e -> textField.selectAll());
    }
}
