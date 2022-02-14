package controllers.warehouseControllers.productControllers;

import controllers.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import modelFx.productFX.ProductModel;
import utils.DialogUtils;
import utils.exceptions.ApplicationException;

public class InfoProductController implements Controller {

    @FXML
    private Label typeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label unitLabel;
    @FXML
    private Label saleVatLabel;
    @FXML
    private Label gtuLabel;
    @FXML
    private Label productCodeLabel;
    @FXML
    private Label nrCatalogLabel;
    @FXML
    private Label pkwIuLabel;
    @FXML
    private Label barcodeLabel;
    @FXML
    private TextField buyNetPriceTf;
    @FXML
    private TextField buyVatTf;
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
        bindings();
    }

    protected void bindings() {
        typeLabel.textProperty().bind(productModel.getProductFXObjectProperty().typeProductFXProperty().asString());
        nameLabel.textProperty().bind(productModel.getProductFXObjectProperty().nameProperty());
        unitLabel.textProperty().bind(productModel.getProductFXObjectProperty().unitsProductFXProperty().asString());
        saleVatLabel.textProperty().bind(productModel.getProductFXObjectProperty().saleVatProductFXProperty().asString().concat("%"));
        gtuLabel.textProperty().bind(productModel.getProductFXObjectProperty().gtuProductFXProperty().asString());
        gtuLabel.setTooltip(new Tooltip(gtuLabel.getText()));
        productCodeLabel.textProperty().bind(productModel.getProductFXObjectProperty().productCodeProperty());
        nrCatalogLabel.textProperty().bind(productModel.getProductFXObjectProperty().nrCatalogProperty());
        pkwIuLabel.textProperty().bind(productModel.getProductFXObjectProperty().pkwIuCNProperty());
        barcodeLabel.textProperty().bind(productModel.getProductFXObjectProperty().barcodeProperty());
        buyNetPriceTf.textProperty().bind(productModel.getProductFXObjectProperty().buyNetPriceProperty().asString());
        buyVatTf.textProperty().bind(productModel.getProductFXObjectProperty().buyVatProductFXProperty().asString().concat("%"));
        descTa.textProperty().bind(productModel.getProductFXObjectProperty().descriptionProperty());
        saleNetPriceTf.textProperty().bind(productModel.getProductFXObjectProperty().saleNetPriceProperty().asString());

        buyGrossPriceTf.textProperty().bind(productModel.getProductFXObjectProperty().buyGrossPriceProperty());
        saleGrossPriceTf.textProperty().bind(productModel.getProductFXObjectProperty().saleGrossPriceProperty());
        overheadTf.textProperty().bind(productModel.getProductFXObjectProperty().overheadProperty());
        gainNetTf.textProperty().bind(productModel.getProductFXObjectProperty().gainNetProperty());
    }

    @FXML
    private void onClose() {
        ProductController.getStage().close();
    }

    @FXML
    private void onShowGtuContent() {
        gtuLabel.tooltipProperty().getName();
    }
}
