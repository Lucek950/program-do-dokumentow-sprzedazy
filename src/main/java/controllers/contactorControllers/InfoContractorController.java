package controllers.contactorControllers;

import controllers.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelFx.entitiesFX.contractorFX.ContractorModel;
import utils.DialogUtils;
import utils.exceptions.ApplicationException;

public class InfoContractorController implements Controller {

    @FXML
    private Label nameLabel;
    @FXML
    private VBox nipVbox;
    @FXML
    private Label nipLabel;
    @FXML
    private VBox peselVbox;
    @FXML
    private Label peselLabel;
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

    private ContractorModel contractorModel;
    protected ContractorModel getContractorModel() {
        return contractorModel;
    }

    @FXML
    private void initialize() {
        contractorModel = new ContractorModel();
        try {
            contractorModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bindings();
    }

    protected void bindings(){
        nameLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().nameProperty());
        nipLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().nipProperty());
        peselLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().peselProperty());
        townLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().townProperty());
        zipCodeLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().zc1Property()
                .concat("-").concat(contractorModel.getContractorFXObjectProperty().zc2Property()));
        streetLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().streetProperty());
        numberLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().addressProperty());
        countryLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().countryProperty());
        townPostLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().townPostProperty());
        zipCodePostLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().zc1PostProperty()
                .concat("-").concat(contractorModel.getContractorFXObjectProperty().zc2PostProperty()));
        streetPostLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().streetPostProperty());
        numberPostLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().addressPostProperty());
        countryPostLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().countryPostProperty());
        phoneLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().phoneProperty());
        faxLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().faxProperty());
        emailLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().emailProperty());
        websiteLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().websiteProperty());
        if(contractorModel.getContractorFXObjectProperty().getBank1() == null)
            bankLabel.textProperty().bind(new SimpleStringProperty("__")
                    .concat(" ").concat("____")
                            .concat(" ").concat("____")
                                    .concat(" ").concat("____")
                                            .concat(" ").concat("____")
                                                    .concat(" ").concat("____")
                                                            .concat(" ").concat("____"));
        else
            bankLabel.textProperty().bind(contractorModel.getContractorFXObjectProperty().bank1Property()
                    .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank2Property())
                            .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank3Property())
                                    .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank4Property())
                                            .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank5Property())
                                                    .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank6Property())
                                                            .concat(" ").concat(contractorModel.getContractorFXObjectProperty().bank7Property()));
    showNipOrPesel();
    }

    private void showNipOrPesel(){
        showNip(nipLabel.textProperty().isNotEmpty().get() || nipLabel.textProperty().isNotNull().get());
        showPesel(peselLabel.textProperty().isNotEmpty().get() || peselLabel.textProperty().isNotNull().get());
    }

    private void showNip(boolean flag){
        nipVbox.setVisible(flag);
        peselVbox.setVisible(!flag);
    }

    private void showPesel(boolean flag){
        nipVbox.setVisible(!flag);
        peselVbox.setVisible(flag);
    }

    @FXML
    private void onClose() {
        ContractorController.getStage().close();
    }
}
