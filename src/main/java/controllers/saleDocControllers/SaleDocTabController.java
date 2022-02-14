package controllers.saleDocControllers;

import controllers.Controller;
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
import modelFx.docsFX.saleDocFX.SaleDocModel;
import modelFx.docsFX.storeDocFX.StoreDocModel;
import modelFx.supportsModelFX.PaymentMethodFX;
import utils.*;
import utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class SaleDocTabController implements Controller {


    private static Stage stage;
    @FXML
    private ComboBox<ProjectDoc> typeDoc;

    protected static Stage getStage() {
        return stage;
    }

    @FXML
    private TableView<DocsFX> docTableView;
    @FXML
    private TableColumn<DocsFX, Number> idCol;
    @FXML
    private TableColumn<DocsFX, String> numberCol;
    @FXML
    private TableColumn<DocsFX, String> contractorCol;
    @FXML
    private TableColumn<DocsFX, String> nipAndPeselCol;
    @FXML
    private TableColumn<DocsFX, String> priceCol;
    @FXML
    private TableColumn<DocsFX, LocalDate> createDateCol;
    @FXML
    private TableColumn<DocsFX, PaymentMethodFX> methodPayCol;
    @FXML
    private TableColumn<DocsFX, DocsFX> productDataBtnCol;
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

    private SaleDocModel saleDocModel;
    private StoreDocModel storeDocModel;

    private static int positionSelected;
    public static int getPositionSelected() {
        return positionSelected;
    }

    @FXML
    protected void initialize() {
        this.saleDocModel = new SaleDocModel();
        this.storeDocModel = new StoreDocModel();
        if(SaleDocController.getFlagWhatOpen() == 1)
            storeDocModel.setFlag(0);
        else
            storeDocModel.setFlag(1);
        try {
            this.saleDocModel.init();
            this.storeDocModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        typeDoc.setItems(saleDocModel.getTypeDocFXObservableList().get(SaleDocController.getFlagWhatOpen()).getProjectDocs());
        typeDoc.getSelectionModel().select(0);

        bindingsTableView();
        setButtonsCol();
        tableViewFiltering();
    }

    private void bindingsContractor(){
        this.contractorCol.setVisible(true);
        this.contractorCol.setCellValueFactory(cellData -> cellData.getValue().nameConProperty());
        this.nipAndPeselCol.setCellValueFactory(cellData -> {
            if(cellData.getValue().nipConProperty().get() == null || cellData.getValue().nipConProperty().get().isEmpty())
                return cellData.getValue().peselConProperty();
            else
                return cellData.getValue().nipConProperty();
        });
    }

    private void bindingsReceipt(){
        this.contractorCol.setVisible(false);
        this.nipAndPeselCol.setCellValueFactory(cellData -> {
            if(cellData.getValue().nipConProperty().get() == null || cellData.getValue().nipConProperty().get().isEmpty())
                return cellData.getValue().peselConProperty();
            else
                return cellData.getValue().nipConProperty();
        });
    }

    private void bindingsTableView() {
        this.docTableView.setItems(this.saleDocModel.getDocsFXObservableList());
        this.idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.numberCol.setCellValueFactory(cellData -> cellData.getValue().numberProperty());

        switch (SaleDocController.getFlagWhatOpen()){
            case 0:
            case 1:
                bindingsContractor();
                break;
            case 2:
                bindingsReceipt();
                break;
        }

        this.priceCol.setCellValueFactory(cellData -> cellData.getValue().priceToPayProperty());
        this.createDateCol.setCellValueFactory(cellData -> cellData.getValue().issueDateProperty());
        this.methodPayCol.setCellValueFactory(cellData -> cellData.getValue().paymentMethodFXProperty());
    }

    private void setButtonsCol(){
        this.productDataBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.productDataBtnCol.setCellFactory(parm -> new TableCell<DocsFX, DocsFX>(){
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
                    FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.INFO_SALE_DOC_FXML);
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                    InfoSaleDocController controller = loader.getController();
                    controller.getSalesDocModel().setDocsFXObjectProperty(item);
                    controller.initFeatures();

                    stage = new Stage();
                    stage.setTitle("Informacje o fakturze");
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
                buttonEdit.setTooltip(new Tooltip("Edytuj"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    initialize();
                    FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.ADD_SALE_DOC_FXML);
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                    AddSaleDocController controller = loader.getController();
                    controller.startEdit();
                    controller.getSalesDocModel().setDocsFXObjectProperty(item);
                    controller.initialize();

                    stage = new Stage();
                    stage.setTitle("Edytuj fakturê");
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
                buttonDelete.setTooltip(new Tooltip("Usuñ"));
                setGraphic(buttonDelete);
                buttonDelete.setOnAction(event -> {
                    Optional<ButtonType> result = DialogUtils.confirmDialog();
                    if(result.isPresent()) {
                        try {
                            saleDocModel.deleteSalesDocInDataBase(item);
                            for (DocsFX storeDocFX : storeDocModel.getDocsFXObservableList())
                                if(storeDocFX.getRelatedDoc().equals(item.getNumber()))
                                    storeDocModel.deleteDocsInDataBase(storeDocFX);

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
                    UtilsGenerateDocument.generateSalesDoc(item, " orygina³");
                    UtilsGenerateDocument.generateSalesDoc(item, " kopia");
                    for (DocsFX storeDocFX : storeDocModel.getDocsFXObservableList()){
                        if(storeDocFX.getRelatedDoc().equals(item.getNumber())) {
//                            try {
//                                DocAsByte.saveFile(storeDocFX);
//                            } catch (IOException e) {
//                                System.out.println(e.getMessage());;
//                            }
                            UtilsGenerateDocument.generateStoreDoc(storeDocFX, " orygina³");
                            UtilsGenerateDocument.generateStoreDoc(storeDocFX, " kopia");
                            break;
                        }}
                    DialogUtils.infoDialog("Wygenerowano fakturê\noraz dokument magazynowania");
                });
            }
        });
    }

    private void tableViewFiltering(){
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add(0,this.numberCol.getText());
        category.add(1,this.contractorCol.getText());
        category.add(2,this.nipAndPeselCol.getText());
        category.add(3,this.priceCol.getText());
        category.add(4,this.createDateCol.getText());
        category.add(5,this.methodPayCol.getText());

        this.filtersCbox.setItems(category);
        this.filtersCbox.getSelectionModel().select(0);

        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> this.saleDocModel.getFilteredData().setPredicate(salesDocFX -> {
            if (newValue == null || newValue.isEmpty())
                return true;
            if (this.filtersCbox.getSelectionModel().isSelected(0))
                return salesDocFX.getNumber().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(1))
                return salesDocFX.getNameCon().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(2))
                if (salesDocFX.getNipCon() != null)
                    return salesDocFX.getNipCon().toLowerCase().contains(newValue.toLowerCase());
                else
                    return salesDocFX.getPeselCon().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(3))
                return salesDocFX.getPriceToPay().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(4))
                return salesDocFX.getIssueDate().toString().toLowerCase().contains(newValue.toLowerCase());
            if (this.filtersCbox.getSelectionModel().isSelected(5))
                return salesDocFX.getPaymentMethodFX().getName().toLowerCase().contains(newValue.toLowerCase());
            return false;
        }));

        SortedList<DocsFX> sortedList = new SortedList<>(this.saleDocModel.getFilteredData());
        sortedList.comparatorProperty().bind(this.docTableView.comparatorProperty());
        this.docTableView.setItems(sortedList);
    }

    @FXML
    private void onAddSaleDoc() {
        positionSelected = typeDoc.getSelectionModel().getSelectedIndex();
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_SALE_DOC_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj fakturê");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}