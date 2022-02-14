package controllers.businessControllers;

import controllers.Controller;
import controllers.mainControllers.MainController;
import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.entitiesFX.businessFX.BusinessModel;
import utils.DialogUtils;
import utils.FillDatabaseTestData;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.exceptions.ApplicationException;

import java.util.Objects;

public class InfoBusinessController implements Controller {

    @FXML
    private HBox infoBusinessHbox;
    @FXML
    private VBox vboxButtonAddBusiness;
    @FXML
    private Label nameLabel;
    @FXML
    private Label nipLabel;
    @FXML
    private Label townLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label townPostLabel;
    @FXML
    private Label zipCodePostLabel;
    @FXML
    private Label streetPostLabel;
    @FXML
    private Label numberPostLabel;
    @FXML
    private Label countryPostLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label faxLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label websiteLabel;
    @FXML
    private Label bankLabel;

    private BusinessModel businessModel;

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    @FXML
    public void initialize(){
        MainController.setInfoBusinessController(this);
        AddBusinessController.setInfoBusinessController(this);
        businessModel = new BusinessModel();
        try {
            businessModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        vboxButtonAddBusiness.visibleProperty().bind(businessModel.whatIsHaveBusiness());
        infoBusinessHbox.disableProperty().bind(new When(vboxButtonAddBusiness.visibleProperty()).then(true).otherwise(false));
        bindings();
    }

    private void bindings(){
        nameLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().nameProperty());
        nipLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().nipProperty());
        townLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().townProperty());
        zipCodeLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().zc1Property()
                .concat("-").concat(businessModel.getBusinessFXObjectProperty().zc2Property()));
        streetLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().streetProperty());
        numberLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().addressProperty());
        countryLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().countryProperty());
        townPostLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().townPostProperty());
        zipCodePostLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().zc1PostProperty()
                .concat("-").concat(businessModel.getBusinessFXObjectProperty().zc2PostProperty()));
        streetPostLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().streetPostProperty());
        numberPostLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().addressPostProperty());
        countryPostLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().countryPostProperty());
        phoneLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().phoneProperty());
        faxLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().faxProperty());
        emailLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().emailProperty());
        websiteLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().websiteProperty());
        bankLabel.textProperty().bind(businessModel.getBusinessFXObjectProperty().bank1Property().concat(" ")
                .concat(businessModel.getBusinessFXObjectProperty().bank2Property().concat(" ")
                        .concat(businessModel.getBusinessFXObjectProperty().bank3Property().concat(" ")
                                .concat(businessModel.getBusinessFXObjectProperty().bank4Property().concat(" ")
                                        .concat(businessModel.getBusinessFXObjectProperty().bank5Property().concat(" ")
                                                .concat(businessModel.getBusinessFXObjectProperty().bank6Property().concat(" ")
                                                        .concat(businessModel.getBusinessFXObjectProperty().bank7Property())))))));
        if(businessModel.getBusinessFXObjectProperty().zc1Property().get() == null) {
            zipCodeLabel.textProperty().unbind();
            zipCodeLabel.setText("");
        }
        if(businessModel.getBusinessFXObjectProperty().zc1PostProperty().get() == null) {
            zipCodePostLabel.textProperty().unbind();
            zipCodePostLabel.setText("");
        }
        if(businessModel.getBusinessFXObjectProperty().bank1Property().get() == null) {
            bankLabel.textProperty().unbind();
            bankLabel.setText("__ ____ ____ ____ ____ ____ ____");
        }
    }

    @FXML
    private void onSelectBusiness() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.ADD_BUSINESS_FXML)));
        stage = new Stage();
        stage.setTitle("Dodaj firmê");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private static MainController mainController;
    public static void setMainController(MainController mainController) {
        InfoBusinessController.mainController = mainController;
    }

    @FXML
    private void onSetSampleData() {
        FillDatabaseTestData.fillAllTables();
        mainController.initialize();
        initialize();
    }
}
