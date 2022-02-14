package controllers.warehouseControllers.storeDocControllers;

import controllers.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelFx.docsFX.DocsModel;
import modelFx.docsFX.storeDocFX.StoreDocModel;
import modelFx.itemsToModels.ListProductsToAddDoc;
import utils.DialogUtils;
import utils.Translation;
import utils.exceptions.ApplicationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InfoStoreDocController implements Controller {

    @FXML
    private Label nipBus;
    @FXML
    private Label nameBus;
    @FXML
    private Label townBus;
    @FXML
    private Label zipCodeBus;
    @FXML
    private Label streetBus;
    @FXML
    private Label addressBus;
    @FXML
    private Label bankBus;

    @FXML
    private Label noneLabel;
    @FXML
    private VBox vBoxContractorData;
    @FXML
    private VBox nipConVbox;
    @FXML
    private Label nipCon;
    @FXML
    private VBox peselConVbox;
    @FXML
    private Label peselCon;
    @FXML
    private Label nameCon;
    @FXML
    private Label townCon;
    @FXML
    private Label zipCodeCon;
    @FXML
    private Label streetCon;
    @FXML
    private Label addressCon;

    @FXML
    private TitledPane titleTypeDoc;
    @FXML
    private Label number;
    @FXML
    private VBox additionalDocVbox;
    @FXML
    private Label additionalDocValue;
    @FXML
    private Label additionalDocLabel;
    @FXML
    private Label issuePlace;
    @FXML
    private Label issueDate;

    @FXML
    private TableView<ListProductsToAddDoc> productsTV;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> lpCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> nameCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> quantityCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> unitCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> unitPriceNetCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> vatCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> discountCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> volNetCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> volCostCol;

    @FXML
    private Label priceToPay;
    @FXML
    private Label priceToPayInWords;

    @FXML
    private TextArea commentTa;

    @FXML
    private Label issued;

    private StoreDocModel storeDocModel;
    public StoreDocModel getStorageDocModel() {
        return storeDocModel;
    }

    @FXML
    private void initialize() {
        storeDocModel = new StoreDocModel();
        try {
            storeDocModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        initFeatures();
    }

    public void initFeatures(){
        bindings();
        storeDocModel.fillRatesTable();
        visiblePeselOrNip();
        if(titleTypeDoc.getText().equals("przyjêcie zewnêtrzne") || titleTypeDoc.getText().equals("przychód wewnêtrzny"))
            visibleAdditionalDoc();
        if(titleTypeDoc.getText().equals("wydanie zewnêtrzne") || titleTypeDoc.getText().equals("rozchód wewnêtrzny")) {
            nameCol.setPrefWidth(247);
            volCostCol.setVisible(true);
        }
        setNoneLabel();
    }

    private void visibleAdditionalDoc(){
        additionalDocVbox.setVisible(true);
        additionalDocLabel.setText("dokument dostawcy");
    }

    private void setNoneLabel(){
        if(titleTypeDoc.getText().equals("wydanie zewnêtrzne")){
            if((nipCon.textProperty().isEmpty().get() || nipCon.textProperty().isNull().get())
                    && (peselCon.textProperty().isEmpty().get() || peselCon.textProperty().isNull().get())) {
                nipConVbox.setVisible(false);
                peselConVbox.setVisible(false);
                noneLabel.setVisible(true);
            }
            vBoxContractorData.setVisible(false);
        }
    }

    private void bindings(){
        bindingsBusiness();
        bindingsContractor();

        number.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).numberProperty());
        titleTypeDoc.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).projectDocProperty().asString());
        additionalDocValue.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).additionalDocProperty());
        issuePlace.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).issuePlaceProperty());
        issueDate.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).issueDateProperty().asString());

        bindingProductsTV();
        DocsModel.getDocsFXObjectProperty(storeDocModel).priceToPayProperty().bind(priceToPay.textProperty());

        priceToPay.textProperty().bind(Bindings.format("%.2f", storeDocModel.totalRateVolNetPropertyProperty()));
        commentTa.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).commentProperty());

        issued.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).issuedProperty());
        storeDocModel.totalsVolGainFromProductTable();

        priceToPay.textProperty().addListener((observableValue, s, t1) -> {
            if(t1 != null){
                long integers = (long) (Double.parseDouble((priceToPay.textProperty().get()).replace(",",".")));
                int fractions = (int) ((BigDecimal.valueOf(Double.parseDouble((priceToPay.textProperty().get()).replace(",",".")) - integers)).setScale(2,RoundingMode.HALF_UP).doubleValue() * 100);

                String numbersInWords = Translation.numberToText(integers) + "PLN " + fractions + "/100";
                DocsModel.getDocsFXObjectProperty(storeDocModel).priceToPayInWordsProperty().bind(new SimpleStringProperty(numbersInWords));
                priceToPayInWords.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).priceToPayInWordsProperty());
            }
        });
    }

    private void bindingsContractor(){
        nameCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).nameConProperty());
        townCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).townConProperty());
        zipCodeCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).zc1ConProperty()
                .concat("-").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).zc2ConProperty()));
        streetCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).streetConProperty());
        addressCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).addressConProperty());
        nipCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).nipConProperty());
        peselCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).peselConProperty());
    }

    private void bindingsBusiness(){
        nameBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).nameBusProperty());
        townBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).townBusProperty());
        zipCodeBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).zc1BusProperty()
                .concat("-").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).zc2BusProperty()));
        streetBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).streetBusProperty());
        addressBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).addressBusProperty());
        nipBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).nipBusProperty());
        if(DocsModel.getDocsFXObjectProperty(storeDocModel).getBank1Bus() == null)
            bankBus.textProperty().bind(new SimpleStringProperty("__")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____"));
        else
            bankBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(storeDocModel).bank1BusProperty()
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank2BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank3BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank4BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank5BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank6BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(storeDocModel).bank7BusProperty()));
    }

    private void bindingProductsTV(){
        productsTV.setItems(DocsModel.getDocsFXObjectProperty(storeDocModel).getListProductToAddDocs());
        lpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLp()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        quantityCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));
        unitCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnit()));
        if(titleTypeDoc.getText().equals("paragon") || titleTypeDoc.getText().equals("paragon imienny")) {
            nameCol.setPrefWidth(280);
            unitPriceNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitPriceGross()));
        }
        else {
            unitPriceNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitPriceNet()));
        }
        unitPriceNetCol.setCellFactory(col ->
                new TableCell<ListProductsToAddDoc, Number>() {
                    @Override
                    public void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
        vatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVat()));
        discountCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDiscount()));
        volNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolNet()));
        volNetCol.setCellFactory(col ->
                new TableCell<ListProductsToAddDoc, Number>() {
                    @Override
                    public void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
        if(titleTypeDoc.getText().equals("wydanie zewnêtrzne") || titleTypeDoc.getText().equals("rozchód wewnêtrzny")) {
            volCostCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCost()));
            volCostCol.setCellFactory(col ->
                    new TableCell<ListProductsToAddDoc, Number>() {
                        @Override
                        public void updateItem(Number price, boolean empty) {
                            super.updateItem(price, empty);
                            if (empty) {
                                setText(null);
                            } else {
                                setText(String.format("%.2f", price.doubleValue()));
                            }
                        }
                    });
        }
    }

    private void visiblePeselOrNip(){
        if(DocsModel.getDocsFXObjectProperty(storeDocModel).getPeselCon() == null || DocsModel.getDocsFXObjectProperty(storeDocModel).getPeselCon().isEmpty()){
            nipConVbox.visibleProperty().set(true);
            peselConVbox.visibleProperty().set(false);
        } else {
            nipConVbox.visibleProperty().set(false);
            peselConVbox.visibleProperty().set(true);
        }
    }

    @FXML
    private void onClose() {
        StoreDocController.getStage().close();
    }
}
