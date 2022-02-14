package controllers.contactorControllers;

import controllers.Controller;
import controllers.saleDocControllers.AddSaleDocController;
import controllers.warehouseControllers.storeDocControllers.AddStoreDocController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.docsFX.DocsModel;
import modelFx.entitiesFX.contractorFX.ContractorFX;
import modelFx.entitiesFX.contractorFX.ContractorModel;
import utils.DialogUtils;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.UtilsProject;
import utils.exceptions.ApplicationException;

import java.util.Objects;
import java.util.Optional;

public class ContractorController implements Controller {

    @FXML
    private TitledPane titledPaneAddStoreDoc;
    @FXML
    private TitledPane titledPaneAddSaleDoc;
    @FXML
    private TableView<ContractorFX> contractorTableView;
    @FXML
    private TableColumn<ContractorFX, Number> idCol;
    @FXML
    private TableColumn<ContractorFX, String> nameCol;
    @FXML
    private TableColumn<ContractorFX, String> nipCol;
    @FXML
    private TableColumn<ContractorFX, String> peselCol;
    @FXML
    private TableColumn<ContractorFX, String> townCol;
    @FXML
    private TableColumn<ContractorFX, String> zipCodeCol;
    @FXML
    private TableColumn<ContractorFX, String> streetCol;
    @FXML
    private TableColumn<ContractorFX, String> numberCol;
    @FXML
    private TableColumn<ContractorFX, String> countryCol;
    @FXML
    private TableColumn<ContractorFX, ContractorFX> personalDataBtnCol;
    @FXML
    private TableColumn<ContractorFX, ContractorFX> editBtnCol;
    @FXML
    private TableColumn<ContractorFX, ContractorFX> deleteBtnCol;
    @FXML
    private ComboBox<String> filtersComboBox;
    @FXML
    private TextField searchField;

    private static Stage stage;
    protected static Stage getStage() {
        return stage;
    }

    private ContractorModel contractorModel;

    public void visibleAddSaleDocTitledPane(){
        titledPaneAddSaleDoc.visibleProperty().set(true);
    }

    public void visibleAddStoreDocTitledPane(){
        titledPaneAddStoreDoc.visibleProperty().set(true);
    }

    @FXML
    protected void initialize() {
        AddContractorController.setContractorController(this);
        contractorModel = new ContractorModel();
        try {
            contractorModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bindingsTableView();
        setButtonsCol();
        tableViewFiltering();
    }

    private void bindingsTableView() {
        contractorTableView.setItems(contractorModel.getContractorFXObservableList());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nipCol.setCellValueFactory(cellData -> cellData.getValue().nipProperty());
        peselCol.setCellValueFactory(cellData -> cellData.getValue().peselProperty());
        townCol.setCellValueFactory(cellData -> cellData.getValue().townProperty());
        zipCodeCol.setCellValueFactory(
                cellData -> cellData.getValue().zc1Property().concat(new SimpleStringProperty("-")).concat(cellData.getValue().zc2Property()));
        streetCol.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
        numberCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        countryCol.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
    }

    private void setButtonsCol(){
        personalDataBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        personalDataBtnCol.setCellFactory(parm -> new TableCell<ContractorFX, ContractorFX>(){
            final Button buttonAbout = UtilsProject.createButton(FxmlPath.ICON_PERSONAL);
            @Override
            protected void updateItem(ContractorFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                setGraphic(buttonAbout);
                buttonAbout.setTooltip(new Tooltip("Wiêcej infromacji"));
                buttonAbout.setOnAction(event -> {
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.INFO_CONTRACTOR_FXML)));
                    InfoContractorController controller = (InfoContractorController) FxmlUtils.getController();
                    controller.getContractorModel().setContractorFXObjectProperty(item);
                    controller.bindings();

                    stage = new Stage();
                    stage.setTitle("Informacje o kontrahencie");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });
        editBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editBtnCol.setCellFactory(parm -> new TableCell<ContractorFX, ContractorFX>(){
            final Button buttonEdit = UtilsProject.createButton(FxmlPath.ICON_EDIT);
            @Override
            protected void updateItem(ContractorFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                buttonEdit.setTooltip(new Tooltip("Edytuj"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    initialize();
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_CONTRACTOR_FXML)));
                    AddContractorController controller = (AddContractorController) FxmlUtils.getController();
                    controller.startEdit();
                    controller.getContractorModel().setContractorFXObjectProperty(item);
                    controller.initialize();

                    stage = new Stage();
                    stage.setTitle("Edycja kontrahenta");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                });
            }
        });
        deleteBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteBtnCol.setCellFactory(parm -> new TableCell<ContractorFX, ContractorFX>(){
            final Button buttonDelete = UtilsProject.createButton(FxmlPath.ICON_DELETE);
            @Override
            protected void updateItem(ContractorFX item, boolean empty) {
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
                            contractorModel.deleteContractorInDataBase(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    }
                });
            }
        });
    }

    private void tableViewFiltering(){
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add(0, nameCol.getText());
        category.add(1, nipCol.getText());
        category.add(2, peselCol.getText());
        category.add(3, townCol.getText());
        category.add(4, zipCodeCol.getText());
        category.add(5, countryCol.getText());

        filtersComboBox.setItems(category);
        filtersComboBox.getSelectionModel().select(0);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> contractorModel.getFilteredData().setPredicate(contractorFX -> {
            if (newValue == null || newValue.isEmpty())
                return true;
            if (filtersComboBox.getSelectionModel().isSelected(0))
                return contractorFX.getName().toLowerCase().contains(newValue.toLowerCase());
            if (filtersComboBox.getSelectionModel().isSelected(1))
                if (contractorFX.getNip() != null)
                    return contractorFX.getNip().toLowerCase().contains(newValue.toLowerCase());
            if (filtersComboBox.getSelectionModel().isSelected(2))
                if (contractorFX.getPesel() != null)
                    return contractorFX.getPesel().toLowerCase().contains(newValue.toLowerCase());
            if (filtersComboBox.getSelectionModel().isSelected(3))
                return contractorFX.getTown().toLowerCase().contains(newValue.toLowerCase());
            if (filtersComboBox.getSelectionModel().isSelected(4))
                return (contractorFX.getZc1() + "-" + contractorFX.getZc2()).toLowerCase().contains(newValue.toLowerCase());
            if (filtersComboBox.getSelectionModel().isSelected(5))
                return contractorFX.getCountry().toLowerCase().contains(newValue.toLowerCase());
            return false;
        }));

        SortedList<ContractorFX> sortedList = new SortedList<>(contractorModel.getFilteredData());
        sortedList.comparatorProperty().bind(contractorTableView.comparatorProperty());
        contractorTableView.setItems(sortedList);
    }

    @FXML
    private void openAddContractor() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_CONTRACTOR_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj kontrahenta");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void addToSaleDoc() {
        DocsModel.setContractorFXObjectProperty(contractorTableView.getSelectionModel().getSelectedItem());
        AddSaleDocController.getStage().close();
    }

    @FXML
    private void addToStoreDoc() {
        DocsModel.setContractorFXObjectProperty(contractorTableView.getSelectionModel().getSelectedItem());
        AddStoreDocController.getStage().close();
    }

    @FXML
    private void onMouseClicked() {
        FxmlUtils.disabledComponentWhenNotSelectRow(contractorTableView, titledPaneAddSaleDoc);
        FxmlUtils.disabledComponentWhenNotSelectRow(contractorTableView, titledPaneAddStoreDoc);
    }

    @FXML
    private void onKeyPressed() {
        FxmlUtils.disabledComponentWhenNotSelectRow(contractorTableView, titledPaneAddSaleDoc);
        FxmlUtils.disabledComponentWhenNotSelectRow(contractorTableView, titledPaneAddStoreDoc);
    }
}
