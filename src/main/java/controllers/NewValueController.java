package controllers;

import controllers.warehouseControllers.productControllers.AddProductController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelFx.docsFX.DocsModel;
import modelFx.docsFX.saleDocFX.SaleDocModel;
import modelFx.docsFX.storeDocFX.StoreDocModel;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.productFX.ProductFX;
import modelFx.productFX.ProductModel;
import utils.DialogUtils;
import utils.exceptions.ApplicationException;

public class NewValueController implements Controller {

    @FXML
    private Label textLabel;
    @FXML
    private TextField newValueTf;
    @FXML
    private Button btnConfirm;

    private DocsModel docModel;
    public void setSalesDocModel(SaleDocModel saleDocModel, Stage stage, int itsFlagWhatOpen, int itsPositionSelected) {
        this.docModel = saleDocModel;
        this.stage = stage;
        this.itsFlagWhatOpen = itsFlagWhatOpen;
        this.itsPositionSelected = itsPositionSelected;
    }
    public void setStorageDocModel(StoreDocModel storeDocModel, Stage stage, int itsFlagWhatOpen, int itsPositionSelected) {
        this.docModel = storeDocModel;
        this.stage = stage;
        this.itsFlagWhatOpen = itsFlagWhatOpen;
        this.itsPositionSelected = itsPositionSelected;
    }

    private int itsPositionSelected;
    private int itsFlagWhatOpen;
    private Stage stage;

    private static AddDocsController addDocsController;
    private static boolean flagIsBuy;
    public static void setAddDocController(AddDocsController addDocsController, boolean flagIsBuy) {
        NewValueController.addDocsController = addDocsController;
        NewValueController.flagIsBuy = flagIsBuy;
    }

    private ProductModel productModel;
    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    @FXML
    private void initialize() {
        if(flag <= 1){
            productModel = new ProductModel();
            try {
                productModel.init();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }

        btnConfirm.disableProperty().bind(newValueTf.textProperty().isEmpty());
    }

    private int flag;
    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int lp;
    public void setLp(int lp) {
        this.lp = lp;
    }

    public void setTextLabel(){
        if(flag == 0)
            textLabel.textProperty().set("WprowadŸ now¹ iloœæ");
        else if (flag == 1)
            textLabel.textProperty().set("WprowadŸ now¹ wysokoœæ rabatu");
        else if (flag == 2)
            textLabel.textProperty().set("WprowadŸ now¹ metodê p³atoœci");
        else
            textLabel.textProperty().set("WprowadŸ jednostkê");
    }

    @FXML
    private void onConfirm() {
        switch (flag){
            case 0:
                if(DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getIdProductType() == 2) {
                    DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get()
                            .set(lp, new ListProductsToAddDoc(DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp),
                                    Integer.parseInt(newValueTf.textProperty().get()),
                                    DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getDiscount(),
                                    docModel.getTypeDocFXObservableList().get(itsFlagWhatOpen).getProjectDocs().get(itsPositionSelected).getShortcut()));
                    docModel.fillRatesTable();
                    docModel.totalsVolGainFromProductTable();
                }
                else {
                    int value = DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getQuantity() - Integer.parseInt(newValueTf.textProperty().get());
                    for (ProductFX productFX : productModel.getProductFXObservableList())
                        if (productFX.getId() == DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getProductId()) {
                            productFX.setQuantity(productFX.getQuantity() + DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getQuantity());
                            DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get()
                                    .set(lp, new ListProductsToAddDoc(DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp), 0,
                                            DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getDiscount(),
                                            docModel.getTypeDocFXObservableList().get(itsFlagWhatOpen).getProjectDocs().get(itsPositionSelected).getShortcut()));
                            if (productFX.getQuantity() < Integer.parseInt(newValueTf.textProperty().get()))
                                DialogUtils.infoDialog("Podana iloœæ przekracza iloœæ " + productFX.getQuantity() + " jednostek dostêpnego towaru");
                            else {
                                DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get()
                                        .set(lp, new ListProductsToAddDoc(DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp),
                                                Integer.parseInt(newValueTf.textProperty().get()),
                                                DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getDiscount(),
                                                docModel.getTypeDocFXObservableList().get(itsFlagWhatOpen).getProjectDocs().get(itsPositionSelected).getShortcut()));
                                docModel.fillRatesTable();
                                docModel.totalsVolGainFromProductTable();

                                if (flagIsBuy)
                                    productFX.setQuantity(productFX.getQuantity() - value);
                                else
                                    productFX.setQuantity(productFX.getQuantity() + value);

                                addDocsController.setProductFXArrayList(productFX);
                                break;
                            }
                        }
                }
                stage.close();
                break;
            case 1:
                DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get()
                        .set(lp, new ListProductsToAddDoc(DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp),
                                DocsModel.getDocsFXObjectProperty(docModel).listProductToAddDocsProperty().get().get(lp).getQuantity(),
                                Integer.parseInt(newValueTf.textProperty().get()),
                                docModel.getTypeDocFXObservableList().get(itsFlagWhatOpen).getProjectDocs().get(itsPositionSelected).getShortcut()));
                docModel.fillRatesTable();
                docModel.totalsVolGainFromProductTable();
                stage.close();
                break;
            case 2:
                docModel.getPaymentMethodFXObjectProperty().setName(newValueTf.textProperty().get());
                try {
                    docModel.savePaymentMethodInDataBase();
                    docModel.initPaymentMethodList();
                } catch (ApplicationException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
                stage.close();
                break;
            default:
                productModel.getUnitsProductsFXObjectProperty().setName(newValueTf.textProperty().get());
                try {
                    productModel.saveUnitsProductInDataBase();
                    productModel.initUnitProductList();
                } catch (ApplicationException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
                AddProductController.getStage().close();
                break;
        }
    }
}
