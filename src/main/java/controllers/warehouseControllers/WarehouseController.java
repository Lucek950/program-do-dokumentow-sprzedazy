package controllers.warehouseControllers;

import controllers.Controller;
import controllers.warehouseControllers.storeDocControllers.AddStoreDocController;
import controllers.warehouseControllers.storeDocControllers.StoreDocController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class WarehouseController implements Controller {

    @FXML
    public TabPane tabPane;
    @FXML
    private StoreDocController releaseDocController;
    @FXML
    private StoreDocController receivingDocController;

    private static int flagWhatOpen;

    public static void setFlagWhatOpen(int flagWhatOpen) {
        WarehouseController.flagWhatOpen = flagWhatOpen - 1;
    }

    public static int getFlagWhatOpen() {
        return flagWhatOpen;
    }

    @FXML
    public void initialize(){
        AddStoreDocController.setWarehouseController(this);

        receivingDocController.initialize();
        releaseDocController.initialize();
    }
    @FXML
    private void onSelectionReceivingDoc() {
        WarehouseController.setFlagWhatOpen(tabPane.getSelectionModel().getSelectedIndex());
        initialize();
    }

    @FXML
    private void onSelectionReleaseDoc() {
        WarehouseController.setFlagWhatOpen(tabPane.getSelectionModel().getSelectedIndex());
        initialize();
    }
}
