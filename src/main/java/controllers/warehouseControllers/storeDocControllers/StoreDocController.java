package controllers.warehouseControllers.storeDocControllers;

import controllers.Controller;
import controllers.warehouseControllers.WarehouseController;
import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.docsFX.DocsFX;
import modelFx.docsFX.storeDocFX.StoreDocModel;
import utils.*;
import utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class StoreDocController implements Controller {

    private static Stage stage;
    @FXML
    private ComboBox<ProjectDoc> typeDoc;

    public static Stage getStage() {
        return stage;
    }

    @FXML
    private TableView<DocsFX> docTableView;
    @FXML
    private TableColumn<DocsFX, Number> idCol;
    @FXML
    private TableColumn<DocsFX, LocalDate> dateCol;
    @FXML
    private TableColumn<DocsFX, String> numberCol;
    @FXML
    private TableColumn<DocsFX, String> contractorCol;
    @FXML
    private TableColumn<DocsFX, String> nipAndPeselCol;
    @FXML
    private TableColumn<DocsFX, String> volNetCol;
    @FXML
    private TableColumn<DocsFX, String> costCol;
    @FXML
    private TableColumn<DocsFX, String> relatedDocCol;
    @FXML
    private TableColumn<DocsFX, DocsFX> aboutBtnCol;
    @FXML
    private TableColumn<DocsFX, DocsFX> editBtnCol;
    @FXML
    private TableColumn<DocsFX, DocsFX> deleteBtnCol;
    @FXML
    private TableColumn<DocsFX, DocsFX> generateBtnCol;

    @FXML
    private ComboBox<String> filtersCbox;
    @FXML
    private TextField searchField;

    private StoreDocModel storeDocModel;

    private static int positionSelected;
    public static int getPositionSelected() {
        return positionSelected;
    }

    @FXML
    public void initialize() {
        this.storeDocModel = new StoreDocModel();
        try {
            this.storeDocModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        typeDoc.setItems(storeDocModel.getTypeDocFXObservableList().get(WarehouseController.getFlagWhatOpen()).getProjectDocs());
        typeDoc.getSelectionModel().select(0);

        bindingsTableView();
        setButtonsCol();
        tableViewFiltering();
    }

    private void bindingsTableView() {
        this.docTableView.setItems(this.storeDocModel.getDocsFXObservableList());
        this.idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.dateCol.setCellValueFactory(cellData -> cellData.getValue().issueDateProperty());
        this.numberCol.setCellValueFactory(cellData -> cellData.getValue().numberProperty());

        this.contractorCol.setVisible(true);
        this.contractorCol.setCellValueFactory(cellData -> cellData.getValue().nameConProperty());
        this.nipAndPeselCol.setCellValueFactory(cellData -> {
            if(cellData.getValue().nipConProperty().get() == null || cellData.getValue().nipConProperty().get().isEmpty())
                return cellData.getValue().peselConProperty();
            else
                return cellData.getValue().nipConProperty();
        });

        this.volNetCol.setCellValueFactory(cellData -> cellData.getValue().rateSumNetProperty());

        if (WarehouseController.getFlagWhatOpen() == 1) {
            this.costCol.setVisible(true);
            this.costCol.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        } else
            this.costCol.setVisible(false);

        this.relatedDocCol.setCellValueFactory(cellData -> cellData.getValue().relatedDocProperty());
    }

    private void setButtonsCol(){
        this.aboutBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.aboutBtnCol.setCellFactory(parm -> new TableCell<DocsFX, DocsFX>(){
            final Button buttonAbout = UtilsProject.createButton(FxmlPath.ICON_PERSONAL);
            @Override
            protected void updateItem(DocsFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                setGraphic(buttonAbout);
                buttonAbout.setTooltip(new Tooltip("Wiêcej informacji"));
                buttonAbout.setOnAction(event -> {
                    FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.INFO_STORE_DOC_FXML);
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                    InfoStoreDocController controller = loader.getController();
                    controller.getStorageDocModel().setDocsFXObjectProperty(item);
                    controller.initFeatures();

                    stage = new Stage();
                    stage.setTitle("Informacje o dokumencie magazynowym");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });
        this.editBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editBtnCol.setCellFactory(parm -> new TableCell<DocsFX, DocsFX>(){
            final Button buttonEdit = UtilsProject.createButton(FxmlPath.ICON_EDIT);
            @Override
            protected void updateItem(DocsFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                if(item.getRelatedDoc() != null)
                    buttonEdit.setDisable(true);
                buttonEdit.setTooltip(new Tooltip("Edytuj"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    initialize();
                    FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.ADD_STORE_DOC_FXML);
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                    AddStoreDocController controller = loader.getController();
                    controller.startEdit();
                    controller.getStoreDocModel().setDocsFXObjectProperty(item);
                    controller.initialize();

                    stage = new Stage();
                    stage.setTitle("Edytuj dokument magazynowy");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                });
            }
        });
        this.deleteBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.deleteBtnCol.setCellFactory(parm -> new TableCell<DocsFX, DocsFX>(){
            final Button buttonDelete = UtilsProject.createButton(FxmlPath.ICON_DELETE);
            @Override
            protected void updateItem(DocsFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                if(item.getRelatedDoc() != null)
                    buttonDelete.setDisable(true);
                buttonDelete.setTooltip(new Tooltip("Usuñ"));
                setGraphic(buttonDelete);
                buttonDelete.setOnAction(event -> {
                    Optional<ButtonType> result = DialogUtils.confirmDialog();
                    if(result.isPresent()) {
                        try {
                            storeDocModel.deleteDocsInDataBase(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    }
                });
            }
        });
        this.generateBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.generateBtnCol.setCellFactory(parm -> new TableCell<DocsFX, DocsFX>(){
            final Button buttonGenerate = UtilsProject.createButton(FxmlPath.ICON_PDF);
            @Override
            protected void updateItem(DocsFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                buttonGenerate.setTooltip(new Tooltip("Generuj do PDF"));
                setGraphic(buttonGenerate);
                buttonGenerate.setOnAction(event -> {
//                    try {
//                        DocAsByte.saveFile(item);
//                    } catch (IOException e) {
//                        System.out.println(e.getMessage());;
//                    }
                    UtilsGenerateDocument.generateStoreDoc(item, " orygina³");
                    UtilsGenerateDocument.generateStoreDoc(item, " kopia");
                    DialogUtils.infoDialog("Wygenerowano dokument magazynowy");
                });
            }
        });
    }

    private void tableViewFiltering(){
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add(0,this.dateCol.getText());
        category.add(1,this.numberCol.getText());
        category.add(2,this.contractorCol.getText());
        category.add(3,this.nipAndPeselCol.getText());
        category.add(4,this.volNetCol.getText());
        category.add(5,this.relatedDocCol.getText());


        this.filtersCbox.setItems(category);
        this.filtersCbox.getSelectionModel().select(0);

        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> this.storeDocModel.getFilteredData().setPredicate(storageDocFX -> {
            if (newValue == null || newValue.isEmpty())
                return true;
            if (this.filtersCbox.getSelectionModel().isSelected(0))
                return storageDocFX.getIssueDate().toString().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(1))
                return storageDocFX.getNumber().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(2))
                return storageDocFX.getNameCon().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(3))
                if (storageDocFX.getNipCon() != null)
                    return storageDocFX.getNipCon().toLowerCase().contains(newValue.toLowerCase());
                else
                    return storageDocFX.getPeselCon().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(4))
                return storageDocFX.getRateSumNet().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(5))
                return storageDocFX.getRelatedDoc().toLowerCase().contains(newValue.toLowerCase());
            return false;
        }));

        SortedList<DocsFX> sortedList = new SortedList<>(this.storeDocModel.getFilteredData());
        sortedList.comparatorProperty().bind(this.docTableView.comparatorProperty());
        this.docTableView.setItems(sortedList);
    }

    @FXML
    private void onAddDoc() {
        positionSelected = typeDoc.getSelectionModel().getSelectedIndex();
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_STORE_DOC_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj dokument");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
