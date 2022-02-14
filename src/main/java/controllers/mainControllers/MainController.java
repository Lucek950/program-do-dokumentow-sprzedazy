package controllers.mainControllers;

import controllers.Controller;
import controllers.businessControllers.AddBusinessController;
import controllers.businessControllers.InfoBusinessController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.entitiesFX.businessFX.BusinessModel;
import utils.DialogUtils;
import utils.FillDatabaseTestData;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.exceptions.ApplicationException;

import java.util.Objects;

public class MainController implements Controller {

    @FXML
    private HBox topMenu;
    @FXML
    private BorderPane mainFrame;

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    @FXML
    public void initialize(){
        InfoBusinessController.setMainController(this);
        AddBusinessController.setMainController(this);
        BusinessModel businessModel = new BusinessModel();
        try {
            businessModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        topMenu.disableProperty().bind(businessModel.whatIsHaveBusiness());
        TopMenuController.setMainController(this);
    }

    protected void setLeft(String fxmlPath){
        mainFrame.setLeft(FxmlUtils.fxmlLoader(fxmlPath));
    }

    @FXML
    private void onBusinessInfo() {
        this.setLeft(FxmlPath.INFO_BUSINESS_FXML);
    }

    @FXML
    private void onSetBusiness() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_BUSINESS_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj firmê");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private static InfoBusinessController infoBusinessController;
    public static void setInfoBusinessController(InfoBusinessController infoBusinessController) {
        MainController.infoBusinessController = infoBusinessController;
    }

    @FXML
    private void onSetSampleData() {
        FillDatabaseTestData.fillAllTables();
        initialize();
        infoBusinessController.initialize();
    }
}
