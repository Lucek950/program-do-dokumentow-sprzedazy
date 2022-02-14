package controllers.warehouseControllers.productControllers;

import controllers.Controller;
import controllers.NewValueController;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.productFX.ProductModel;
import modelFx.supportsModelFX.GtuProductsFX;
import modelFx.supportsModelFX.UnitsProductsFX;
import modelFx.supportsModelFX.TypeProductsFX;
import modelFx.supportsModelFX.VatProductsFX;
import utils.DialogUtils;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.UtilsProject;
import utils.exceptions.ApplicationException;
import javafx.beans.binding.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import utils.fxmlFieldUtils.ChangeListener;

import java.util.Objects;
import java.util.Optional;

public class AddProductController implements Controller {

    @FXML
    private VBox buyPriceVbox;

    @FXML
    private ComboBox<TypeProductsFX> typeProductCbox;
    @FXML
    private TextField nameTf;
    @FXML
    private ComboBox<UnitsProductsFX> unitsCbox;
    @FXML
    private ComboBox<VatProductsFX> saleVatCbox;
    @FXML
    private ComboBox<GtuProductsFX> gtuCbox;
    @FXML
    private TextField productCodeTf;
    @FXML
    private TextField nrCatalogTf;
    @FXML
    private TextField pkwIuCnTf;
    @FXML
    private TextField barcodeTf;
    @FXML
    private TextField buyNetPriceTf;
    @FXML
    private ComboBox<VatProductsFX> buyVatCbox;
    @FXML
    private TextField buyGrossPriceTf;
    @FXML
    private TextArea descTa;
    @FXML
    private TextField saleNetPriceTf;
    @FXML
    private TextField saleGrossPriceTf;
    @FXML
    private TextField overheadTf;
    @FXML
    private TextField gainNetTf;

    @FXML
    private Label wrongType;
    @FXML
    private Label wrongName;
    @FXML
    private Label wrongUnit;
    @FXML
    private Label wrongSaleVat;
    @FXML
    private Label wrongBuyPrice;
    @FXML
    private Label wrongBuyVat;
    @FXML
    private Label wrongSalePrice;

    @FXML
    private Label correctLabel;

    @FXML
    private Button saveBtn;

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    private boolean edit = false;
    protected void startEdit() {
        typeProductCbox.setDisable(true);
        this.edit = true;
    }

    private ProductModel productModel;
    protected ProductModel getProductModel() {
        return productModel;
    }

    @FXML
    private void initialize() {
        productModel = new ProductModel();
        try {
            productModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        selectAllContentsFromTF();
        validation();
        saveButtonActivate();
        initCboxes();
        bindings();
        calculateOverheadAndGainNet();
        gtuCbox.getSelectionModel().selectFirst();
    }

    private void selectAllContentsFromTF() {
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nameTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(productCodeTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nrCatalogTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(pkwIuCnTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(barcodeTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(buyNetPriceTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(buyGrossPriceTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(saleNetPriceTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(saleGrossPriceTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(overheadTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(gainNetTf);
    }

    private void validation() {
        wrongType.visibleProperty().bind(new When(typeProductCbox.valueProperty().isNull()).then(true).otherwise(false));
        nameTf.textProperty().addListener(new ChangeListener(nameTf, 80, wrongName));
        wrongUnit.visibleProperty().bind(new When(unitsCbox.valueProperty().isNull()).then(true).otherwise(false));
        wrongSaleVat.visibleProperty().bind(new When(saleVatCbox.valueProperty().isNull()).then(true).otherwise(false));
        buyNetPriceTf.textProperty().addListener(new ChangeListener(buyNetPriceTf, 20, wrongBuyPrice));
        wrongBuyVat.visibleProperty().bind(new When(buyVatCbox.valueProperty().isNull()).then(true).otherwise(false));
        saleNetPriceTf.textProperty().addListener(new ChangeListener(saleNetPriceTf, 20, wrongSalePrice));
    }

    private void saveButtonActivate() {
        correctLabel.visibleProperty()
                .bind(wrongName.visibleProperty()
                        .or(wrongType.visibleProperty())
                        .or(wrongName.visibleProperty())
                        .or(wrongUnit.visibleProperty())
                        .or(wrongSaleVat.visibleProperty())
                        .or(wrongBuyPrice.visibleProperty())
                        .or(wrongBuyVat.visibleProperty())
                        .or(wrongSalePrice.visibleProperty()));

        saveBtn.disableProperty().bind(correctLabel.visibleProperty());
    }

    private void initCboxes() {
        typeProductCbox.setItems(productModel.getTypeProductsFXObservableList());
        unitsCbox.setItems(productModel.getUnitsProductsFXObservableList());
        saleVatCbox.setItems(productModel.getVatProductsFXObservableList());
        gtuCbox.setItems(productModel.getGtuProductsFXObservableList());
        buyVatCbox.setItems(productModel.getVatProductsFXObservableList());
    }

    protected void bindings() {
        typeProductCbox.valueProperty().bindBidirectional(productModel.getProductFXObjectProperty().typeProductFXProperty());
        nameTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().nameProperty());
        unitsCbox.valueProperty().bindBidirectional(productModel.getProductFXObjectProperty().unitsProductFXProperty());
        saleVatCbox.valueProperty().bindBidirectional(productModel.getProductFXObjectProperty().saleVatProductFXProperty());
        gtuCbox.valueProperty().bindBidirectional(productModel.getProductFXObjectProperty().gtuProductFXProperty());
        productCodeTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().productCodeProperty());
        nrCatalogTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().nrCatalogProperty());
        pkwIuCnTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().pkwIuCNProperty());
        barcodeTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().barcodeProperty());
        buyNetPriceTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().buyNetPriceProperty(), new NumberStringConverter("#0.00"));
        buyVatCbox.valueProperty().bindBidirectional(productModel.getProductFXObjectProperty().buyVatProductFXProperty());
        descTa.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().descriptionProperty());
        saleNetPriceTf.textProperty().bindBidirectional(productModel.getProductFXObjectProperty().saleNetPriceProperty(), new NumberStringConverter("#0.00"));

        productModel.getProductFXObjectProperty().buyGrossPriceProperty().bind(buyGrossPriceTf.textProperty());
        productModel.getProductFXObjectProperty().saleGrossPriceProperty().bind(saleGrossPriceTf.textProperty());
        productModel.getProductFXObjectProperty().overheadProperty().bind(overheadTf.textProperty());
        productModel.getProductFXObjectProperty().gainNetProperty().bind(gainNetTf.textProperty());
    }

    protected void unbinds() {
        typeProductCbox.valueProperty().unbindBidirectional(productModel.getProductFXObjectProperty().typeProductFXProperty());
        nameTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().nameProperty());
        unitsCbox.valueProperty().unbindBidirectional(productModel.getProductFXObjectProperty().unitsProductFXProperty());
        saleVatCbox.valueProperty().unbindBidirectional(productModel.getProductFXObjectProperty().saleVatProductFXProperty());
        gtuCbox.valueProperty().unbindBidirectional(productModel.getProductFXObjectProperty().gtuProductFXProperty());
        productCodeTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().productCodeProperty());
        nrCatalogTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().nrCatalogProperty());
        pkwIuCnTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().pkwIuCNProperty());
        barcodeTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().barcodeProperty());
        buyNetPriceTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().buyNetPriceProperty());
        buyVatCbox.valueProperty().unbindBidirectional(productModel.getProductFXObjectProperty().buyVatProductFXProperty());
        descTa.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().descriptionProperty());
        saleNetPriceTf.textProperty().unbindBidirectional(productModel.getProductFXObjectProperty().saleNetPriceProperty());

        productModel.getProductFXObjectProperty().buyGrossPriceProperty().unbind();
        productModel.getProductFXObjectProperty().saleGrossPriceProperty().unbind();
        productModel.getProductFXObjectProperty().overheadProperty().unbind();
        productModel.getProductFXObjectProperty().gainNetProperty().unbind();
    }

    private void setters() {
        productModel.getProductFXObjectProperty().typeProductFXProperty().set(typeProductCbox.valueProperty().get());
        productModel.getProductFXObjectProperty().nameProperty().set(nameTf.textProperty().get());
        productModel.getProductFXObjectProperty().unitsProductFXProperty().set(unitsCbox.valueProperty().get());
        productModel.getProductFXObjectProperty().saleVatProductFXProperty().set(saleVatCbox.valueProperty().get());
        productModel.getProductFXObjectProperty().gtuProductFXProperty().set(gtuCbox.valueProperty().get());
        productModel.getProductFXObjectProperty().productCodeProperty().set(productCodeTf.textProperty().get());
        productModel.getProductFXObjectProperty().nrCatalogProperty().set(nrCatalogTf.textProperty().get());
        productModel.getProductFXObjectProperty().pkwIuCNProperty().set(pkwIuCnTf.textProperty().get());
        productModel.getProductFXObjectProperty().barcodeProperty().set(barcodeTf.textProperty().get());
        productModel.getProductFXObjectProperty().buyNetPriceProperty().set(Double.parseDouble(buyNetPriceTf.textProperty().get().replace(",", ".")));
        productModel.getProductFXObjectProperty().buyVatProductFXProperty().set(buyVatCbox.valueProperty().get());
        productModel.getProductFXObjectProperty().descriptionProperty().set(descTa.textProperty().get());
        productModel.getProductFXObjectProperty().saleNetPriceProperty().set(Double.parseDouble(saleNetPriceTf.textProperty().get().replace(",", ".")));
        productModel.getProductFXObjectProperty().setBuyGrossPrice(buyGrossPriceTf.textProperty().get());
        productModel.getProductFXObjectProperty().setSaleGrossPrice(saleGrossPriceTf.textProperty().get());
        productModel.getProductFXObjectProperty().setOverhead(overheadTf.textProperty().get());
        productModel.getProductFXObjectProperty().setGainNet(gainNetTf.textProperty().get());
    }

    @FXML
    private void onCalculateSaleGrossPrice() {
        saleGrossPriceTf.textProperty().bind(Bindings.format("%.2f", productModel.getProductFXObjectProperty().saleNetPriceProperty()
                .multiply(productModel.getVatProductsFXObservableList().get(saleVatCbox.getSelectionModel().selectedIndexProperty().get()).getRateVat())));
    }

    @FXML
    private void onCalculateBuyGrossPrice() {
        buyGrossPriceTf.textProperty().bind(Bindings.format("%.2f", productModel.getProductFXObjectProperty().buyNetPriceProperty()
                .multiply(productModel.getVatProductsFXObservableList().get(buyVatCbox.getSelectionModel().selectedIndexProperty().get()).getRateVat())));
    }

    protected void initValueCboxToEditData() {
        saleVatCbox.getSelectionModel().select(saleVatCbox.getSelectionModel().selectedItemProperty().get().getId() - 1);
        onCalculateSaleGrossPrice();
        buyVatCbox.getSelectionModel().select(buyVatCbox.getSelectionModel().selectedItemProperty().get().getId() - 1);
        onCalculateBuyGrossPrice();
    }

    protected void calculateOverheadAndGainNet() {
        gainNetTf.textProperty().bind(Bindings.format("%.2f", productModel.getProductFXObjectProperty().saleNetPriceProperty()
                .subtract(productModel.getProductFXObjectProperty().buyNetPriceProperty())));

        overheadTf.textProperty().bind(Bindings.format("%.1f", ((productModel.getProductFXObjectProperty().saleNetPriceProperty()
                .subtract(productModel.getProductFXObjectProperty().buyNetPriceProperty()))
                .divide(productModel.getProductFXObjectProperty().buyNetPriceProperty()))
                .multiply(100)));
    }

    @FXML
    private void onSwitchWareOrService() {
        if (typeProductCbox.valueProperty().get().getName().equals("towar")) {
            buyPriceVbox.disableProperty().set(false);
            validation();
        }
        if (typeProductCbox.valueProperty().get().getName().equals("us³uga")) {
            wrongBuyVat.visibleProperty().unbind();
            wrongBuyPrice.visibleProperty().set(false);
            wrongBuyVat.visibleProperty().set(false);
            buyPriceVbox.disableProperty().set(true);
            buyNetPriceTf.textProperty().set("0");
            buyVatCbox.getSelectionModel().select(2);
        }
        calculateOverheadAndGainNet();
    }

    @FXML
    private void onClearAllFields() {
        nameTf.clear();
        gtuCbox.getSelectionModel().selectFirst();
        productCodeTf.clear();
        nrCatalogTf.clear();
        pkwIuCnTf.clear();
        barcodeTf.clear();
        buyNetPriceTf.clear();
        descTa.clear();
        saleNetPriceTf.clear();
    }

    @FXML
    private void onCancel() {
        ProductController.getStage().close();
    }

    private static ProductController productController;
    protected static void setProductController(ProductController productController) {
        AddProductController.productController = productController;
    }

    @FXML
    private void onSave() {
        Optional<ButtonType> result = DialogUtils.confirmDialog();
        if (result.isPresent()) {
            if (edit) {
                setters();
            }
            try {
                productModel.saveProductInDataBase();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
            productController.initialize();
            onClearAllFields();
            if (edit) {
                ProductController.getStage().close();
                edit = false;
            }
        }
    }

    @FXML
    private void onAddUnit() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.NEW_VALUE_FXML)));
        NewValueController controller = (NewValueController) FxmlUtils.getController();
        controller.setProductModel(productModel);
        controller.setFlag(9);
        controller.setTextLabel();

        stage = new Stage();
        stage.setTitle("Dodaj jednostkê");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
