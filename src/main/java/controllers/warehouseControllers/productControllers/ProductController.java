package controllers.warehouseControllers.productControllers;

import controllers.Controller;
import controllers.AddProductToDocController;
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
import modelFx.productFX.ProductFX;
import modelFx.productFX.ProductModel;
import modelFx.supportsModelFX.TypeProductsFX;
import modelFx.supportsModelFX.UnitsProductsFX;
import modelFx.supportsModelFX.VatProductsFX;
import utils.DialogUtils;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.UtilsProject;
import utils.exceptions.ApplicationException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ProductController implements Controller {

    @FXML
    private TitledPane titledPaneAddStoreDoc;
    @FXML
    private TitledPane titledPaneAddSaleDoc;
    @FXML
    private TableView<ProductFX> productTableView;
    @FXML
    private TableColumn<ProductFX, Number> idCol;
    @FXML
    private TableColumn<ProductFX, TypeProductsFX> rodzajCol;
    @FXML
    private TableColumn<ProductFX, String> nazwaCol;
    @FXML
    private TableColumn<ProductFX, Number> quantityCol;
    @FXML
    private TableColumn<ProductFX, UnitsProductsFX> jmCol;
    @FXML
    private TableColumn<ProductFX, VatProductsFX> vatCol;
    @FXML
    private TableColumn<ProductFX, String> kodCol;
    @FXML
    private TableColumn<ProductFX, String> nrKatCol;
    @FXML
    private TableColumn<ProductFX, String> pkwIuCnCol;
    @FXML
    private TableColumn<ProductFX, String> kreskowyCol;
    @FXML
    private TableColumn<ProductFX, Number> cNettoCol;
    @FXML
    private TableColumn<ProductFX, String> cBruttoCol;
    @FXML
    private TableColumn<ProductFX,ProductFX> productDataBtnCol;
    @FXML
    private TableColumn<ProductFX,ProductFX> editBtnCol;
    @FXML
    private TableColumn<ProductFX,ProductFX> deleteBtnCol;
    @FXML
    private ComboBox<String> filtersCbox;
    @FXML
    private TextField searchField;

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    private ProductModel productModel;

    public void visibleAddSaleDocTitledPane(){
        titledPaneAddSaleDoc.visibleProperty().set(true);
    }
    public void visibleAddStoreDocTitledPane(){
        titledPaneAddStoreDoc.visibleProperty().set(true);
    }

    @FXML
    protected void initialize() {
        AddProductController.setProductController(this);
        productModel = new ProductModel();
        try {
            productModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bindingsTableView();
        setButtonsCol();
        tableViewFiltering();
    }
    
    private void bindingsTableView() {
        productTableView.setItems(productModel.getProductFXObservableList());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        rodzajCol.setCellValueFactory(cellData -> cellData.getValue().typeProductFXProperty());
        nazwaCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        jmCol.setCellValueFactory(cellData -> cellData.getValue().unitsProductFXProperty());
        vatCol.setCellValueFactory(cellData -> cellData.getValue().saleVatProductFXProperty());
        kodCol.setCellValueFactory(cellData -> cellData.getValue().productCodeProperty());
        nrKatCol.setCellValueFactory(cellData -> cellData.getValue().nrCatalogProperty());
        pkwIuCnCol.setCellValueFactory(cellData -> cellData.getValue().pkwIuCNProperty());
        kreskowyCol.setCellValueFactory(cellData -> cellData.getValue().barcodeProperty());
        cNettoCol.setCellValueFactory(cellData -> cellData.getValue().saleNetPriceProperty());
        cNettoCol.setCellFactory(col ->
                new TableCell<ProductFX, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
        cBruttoCol.setCellValueFactory(cellData -> cellData.getValue().saleGrossPriceProperty());
    }

    private void setButtonsCol(){
        productDataBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        productDataBtnCol.setCellFactory(parm -> new TableCell<ProductFX, ProductFX>(){
            final Button buttonAbout = UtilsProject.createButton(FxmlPath.ICON_PERSONAL);
            @Override
            protected void updateItem(ProductFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                setGraphic(buttonAbout);
                buttonAbout.setTooltip(new Tooltip("Wiêcej informacji"));
                buttonAbout.setOnAction(event -> {
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.INFO_PRODUCT_FXML)));
                    InfoProductController controller = (InfoProductController) FxmlUtils.getController();
                    controller.getProductModel().setProductFXObjectProperty(item);
                    controller.bindings();

                    stage = new Stage();
                    stage.setTitle("Informacje o produkcie");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });
        editBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editBtnCol.setCellFactory(parm -> new TableCell<ProductFX, ProductFX>(){
            final Button buttonEdit = UtilsProject.createButton(FxmlPath.ICON_EDIT);
            @Override
            protected void updateItem(ProductFX item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                buttonEdit.setTooltip(new Tooltip("Edytuj"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_PRODUCT_FXML)));
                    AddProductController controller = (AddProductController) FxmlUtils.getController();
                    controller.getProductModel().setProductFXObjectProperty(item);
                    controller.bindings();
                    controller.initValueCboxToEditData();
                    controller.unbinds();
                    controller.startEdit();

                    stage = new Stage();
                    stage.setTitle("Edytuj towar/us³ugê");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });
        deleteBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteBtnCol.setCellFactory(parm -> new TableCell<ProductFX, ProductFX>(){
            final Button buttonDelete = UtilsProject.createButton(FxmlPath.ICON_DELETE);
            @Override
            protected void updateItem(ProductFX item, boolean empty) {
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
                            if(item.getQuantity() > 0)
                                DialogUtils.infoDialog("Towar mo¿na usun¹æ gdy jego stan wynosi: 0");
                            else
                                productModel.deleteProductInDataBase(item);
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
        category.add(0, rodzajCol.getText());
        category.add(1, nazwaCol.getText());
        category.add(2, vatCol.getText());
        category.add(3, kodCol.getText());
        category.add(4, nrKatCol.getText());
        category.add(5, pkwIuCnCol.getText());
        category.add(6, kreskowyCol.getText());

        filtersCbox.setItems(category);
        filtersCbox.getSelectionModel().select(1);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> productModel.getFilteredData().setPredicate(productFX -> {
            if (newValue == null || newValue.isEmpty())
                return true;
            if (filtersCbox.getSelectionModel().isSelected(0))
                return productFX.getTypeProductFX().getName().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(1))
                return productFX.getName().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(2))
                return productFX.getSaleVatProductFX().getName().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(3))
                if (productFX.getProductCode() != null)
                    return productFX.getProductCode().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(4))
                if (productFX.getNrCatalog() != null)
                    return productFX.getNrCatalog().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(5))
                if (productFX.getPkwIuCN() != null)
                    return productFX.getPkwIuCN().toLowerCase().contains(newValue.toLowerCase());
            if (filtersCbox.getSelectionModel().isSelected(6))
                if (productFX.getBarcode() != null)
                    return productFX.getBarcode().toLowerCase().contains(newValue.toLowerCase());
            return false;
        }));

        SortedList<ProductFX> sortedList = new SortedList<>(productModel.getFilteredData());
        sortedList.comparatorProperty().bind(productTableView.comparatorProperty());
        productTableView.setItems(sortedList);
    }

    @FXML
    private void onAddWare() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_PRODUCT_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj towar/us³ugê");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onAddToSaleDoc() {
        AddProductToDocController.setProductControllerModel(productModel, productTableView.getSelectionModel().getSelectedItem());
        FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.CONFIRM_PRODUCT_FXML);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        stage = new Stage();
        stage.setTitle("Potwierdzenie");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onAddToStoreDoc() {
        AddProductToDocController.setProductControllerModel(productModel, productTableView.getSelectionModel().getSelectedItem());
        FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.CONFIRM_PRODUCT_FXML);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        stage = new Stage();
        stage.setTitle("Potwierdzenie");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onMouseClicked() {
        FxmlUtils.disabledComponentWhenNotSelectRow(productTableView, titledPaneAddSaleDoc);
        FxmlUtils.disabledComponentWhenNotSelectRow(productTableView, titledPaneAddStoreDoc);
    }

    @FXML
    private void onKeyPressed() {
        FxmlUtils.disabledComponentWhenNotSelectRow(productTableView, titledPaneAddSaleDoc);
        FxmlUtils.disabledComponentWhenNotSelectRow(productTableView, titledPaneAddStoreDoc);
    }
}
