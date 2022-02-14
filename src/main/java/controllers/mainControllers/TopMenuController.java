package controllers.mainControllers;

import controllers.Controller;
import javafx.fxml.FXML;
import utils.FxmlPath;

public class TopMenuController implements Controller {
    private static MainController mainController;
    protected static void setMainController(MainController mainController) {
        TopMenuController.mainController = mainController;
    }

    @FXML
    private void openSalesDocs() {
        mainController.setLeft(FxmlPath.SALE_DOC_FXML);
    }

    @FXML
    private void openContractor() {
        mainController.setLeft(FxmlPath.CONTRACTOR_FXML);
    }

    @FXML
    private void openWarehouse() {
        mainController.setLeft(FxmlPath.WAREHOUSE_FXML);
    }
}
