package controllers.saleDocControllers;

import controllers.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class SaleDocController implements Controller {
    @FXML
    private TabPane tabPane;

    @FXML
    private SaleDocTabController invoiceSaleController;
    @FXML
    private SaleDocTabController invoiceBuyController;
    @FXML
    private SaleDocTabController receiptController;

    private static int flagWhatOpen;

    protected static void setFlagWhatOpen(int flagWhatOpen) {
        SaleDocController.flagWhatOpen = flagWhatOpen;
    }

    public static int getFlagWhatOpen() {
        return flagWhatOpen;
    }

    @FXML
    protected void initialize(){
        AddSaleDocController.setSalesDocController(this);

        invoiceSaleController.initialize();
        invoiceBuyController.initialize();
        receiptController.initialize();
    }

    @FXML
    private void onSelectionInvoiceSale() {
        SaleDocController.setFlagWhatOpen(tabPane.getSelectionModel().getSelectedIndex());
        initialize();
    }

    @FXML
    private void onSelectionInvoiceBuy() {
        SaleDocController.setFlagWhatOpen(tabPane.getSelectionModel().getSelectedIndex());
        initialize();
    }

    @FXML
    private void onSelectionReceipt() {
        SaleDocController.setFlagWhatOpen(tabPane.getSelectionModel().getSelectedIndex());
        initialize();
    }
}
