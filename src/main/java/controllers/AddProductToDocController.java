package controllers;

import controllers.warehouseControllers.productControllers.ProductController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelFx.docsFX.DocsModel;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.productFX.ProductFX;
import modelFx.productFX.ProductModel;
import utils.DialogUtils;
import utils.exceptions.ApplicationException;

import java.util.concurrent.atomic.AtomicBoolean;

public class AddProductToDocController {

    @FXML
    private TextField quantityTf;
    @FXML
    private TextField discountTf;
    @FXML
    private Button btnAccept;

    private static ProductModel productModel;
    private static ProductFX productFX;

    public static void setProductControllerModel(ProductModel productModel, ProductFX productFX){
        AddProductToDocController.productModel = productModel;
        AddProductToDocController.productFX = productFX;
    }

    @FXML
    private void initialize(){
        btnAccept.disableProperty().bind(quantityTf.textProperty().isEmpty().or(quantityTf.textProperty().isEqualTo("0")));
    }

    private static DocsModel docsModel;
    public static void setDocsModel(DocsModel docsModel) {
        AddProductToDocController.docsModel = docsModel;
    }

    private static AddDocsController addDocsController;
    private static boolean flagIsBuy;
    public static void setAddDocController(AddDocsController addDocsController, boolean flagIsBuy) {
        AddProductToDocController.addDocsController = addDocsController;
        AddProductToDocController.flagIsBuy = flagIsBuy;
    }

    @FXML
    private void onAccept() {
        if(discountTf.textProperty().get().isEmpty())
            discountTf.textProperty().set("0");

        AtomicBoolean flag = new AtomicBoolean(false);
        for (ListProductsToAddDoc item : DocsModel.getDocsFXObjectProperty(docsModel).listProductToAddDocsProperty().get()) {
            if (item.getProductId() == productFX.getId()) {
                flag.set(true);
                break;
            }
        }

        if(!flag.get()) {
            if(flagIsBuy) {
                if((productFX.getTypeProductFX().getId() == 2))
                    DialogUtils.infoDialog("Nie mo¿na dodaæ us³ugi w celu zakupu lub przyjêcia magazynowego");
                else{
                    try {
                        docsModel.addProductToList(productModel.getProductToListSale(productFX, Integer.parseInt(quantityTf.textProperty().get()), Integer.parseInt(discountTf.textProperty().get()), flagIsBuy));
                        productFX.setQuantity(productFX.getQuantity() + Integer.parseInt(quantityTf.textProperty().get()));
                        addDocsController.setProductFXArrayList(productFX);
                    } catch (ApplicationException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                }
                addDocsController.bindingProductsTV();
                ProductController.getStage().close();
            } else if (productFX.getTypeProductFX().getId() == 2){
                try {
                    docsModel.addProductToList(productModel.getProductToListSale(productFX, Integer.parseInt(quantityTf.textProperty().get()),Integer.parseInt(discountTf.textProperty().get()), false));
                } catch (ApplicationException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
                addDocsController.bindingProductsTV();
                ProductController.getStage().close();
            } else {
                if (productFX.getQuantity() >= Integer.parseInt(quantityTf.textProperty().get())) {
                    try {
                        docsModel.addProductToList(productModel.getProductToListSale(productFX, Integer.parseInt(quantityTf.textProperty().get()), Integer.parseInt(discountTf.textProperty().get()), false));
                        productFX.setQuantity(productFX.getQuantity() - Integer.parseInt(quantityTf.textProperty().get()));
                        addDocsController.setProductFXArrayList(productFX);
                    } catch (ApplicationException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                    addDocsController.bindingProductsTV();
                    ProductController.getStage().close();
                } else
                    DialogUtils.infoDialog("Podana iloœæ przekracza iloœæ dostêpnego towaru \nWybierz dostêpny towar lub mniejsz¹ iloœæ tego samego towaru");
            }
        }
        else
            DialogUtils.infoDialog("Ten produkt juz znajdujê siê na liœcie");
    }

    @FXML
    private void onCancel() {
        ProductController.getStage().close();
    }
}
