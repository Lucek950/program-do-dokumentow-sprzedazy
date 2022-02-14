package controllers.saleDocControllers;

import controllers.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelFx.docsFX.DocsModel;
import modelFx.docsFX.saleDocFX.SaleDocModel;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;
import utils.DialogUtils;
import utils.Translation;
import utils.exceptions.ApplicationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InfoSaleDocController implements Controller {

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
    private TitledPane titlePaneType;
    @FXML
    private Label number;
    @FXML
    private VBox vboxAdditionalDoc;
    @FXML
    private Label additionalDocValue;
    @FXML
    private Label additionalDocLabel;
    @FXML
    private Label issuePlace;
    @FXML
    private Label issueDate;
    @FXML
    private Label executeDate;

    @FXML
    private TableView<ListProductsToAddDoc> productsTV;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> lpCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> nameCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> pkwiuCol;
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
    private TableColumn<ListProductsToAddDoc, Number> sumVatCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> volGrossCol;

    @FXML
    private TableView<RateProductsToAddDoc> ratesTV;
    @FXML
    private TableColumn<RateProductsToAddDoc, String> ratesVatCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesNetCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesSumVatCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesGrossCol;

    @FXML
    private TextField totalRateNetCol;
    @FXML
    private TextField totalRateSumVatCol;
    @FXML
    private TextField totalRateGrossCol;

    @FXML
    private Label priceToPay;
    @FXML
    private Label priceToPayInWords;

    @FXML
    private Label paymentMethod;
    @FXML
    private VBox splitPaymentVbox;
    @FXML
    private CheckBox splitPayment;
    @FXML
    private VBox paymentDateVbox;
    @FXML
    private Label paymentDate;

    @FXML
    private Label issued;

    private SaleDocModel saleDocModel;
    protected SaleDocModel getSalesDocModel() {
        return saleDocModel;
    }

    @FXML
    private void initialize() {
        saleDocModel = new SaleDocModel();
        try {
            saleDocModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        initFeatures();
    }

    protected void initFeatures(){
        bindings();
        saleDocModel.fillRatesTable();
        visiblePeselOrNip();
        if(titlePaneType.getText().equals("faktura sprzeda¿y bez paragonu") || titlePaneType.getText().equals("rachunek sprzeda¿y") || titlePaneType.getText().equals("rachunek zakupu") || titlePaneType.getText().equals("paragon") || titlePaneType.getText().equals("paragon imienny"))
            unitPriceNetCol.setText("Cena j. brutto");
        if(titlePaneType.getText().equals("faktura zakupu") || titlePaneType.getText().equals("rachunek zakupu")){
            vboxAdditionalDoc.setVisible(true);
            additionalDocLabel.setText("dokument dostawcy");
        }
        if(titlePaneType.getText().equals("faktura sprzeda¿y bez paragonu")){
            vboxAdditionalDoc.setVisible(true);
            additionalDocLabel.setText("do paragonu");
        }
        setReceipt();
    }

    private void setReceipt(){
        if(titlePaneType.getText().equals("paragon") || titlePaneType.getText().equals("paragon imienny")){
            paymentDateVbox.setVisible(false);
            splitPaymentVbox.setVisible(false);
        }
        if(titlePaneType.getText().equals("paragon")){
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

        number.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty());
        titlePaneType.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).projectDocProperty().asString());
        additionalDocValue.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).additionalDocProperty());
        issuePlace.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).issuePlaceProperty());
        issueDate.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).issueDateProperty().asString());
        executeDate.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).executeDateProperty().asString());

        bindingProductsTV();
        DocsModel.getDocsFXObjectProperty(saleDocModel).listRateProductsToAddDocsProperty().bind(ratesTV.itemsProperty());
        bindingRatesTV();
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumNetProperty().bind(totalRateNetCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumVatProperty().bind(totalRateSumVatCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumGrossProperty().bind(totalRateGrossCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).priceToPayProperty().bind(priceToPay.textProperty());

        priceToPay.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalVolGrossPropertyProperty()));
        totalRateNetCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateVolNetPropertyProperty()));
        totalRateSumVatCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateSumVatPropertyProperty()));
        totalRateGrossCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateVolGrossPropertyProperty()));

        paymentMethod.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).paymentMethodFXProperty().asString());
        paymentDate.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).paymentDateProperty().asString());
        splitPayment.selectedProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).splitPaymentProperty());

        issued.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).issuedProperty());
        saleDocModel.totalsVolGainFromProductTable();

        priceToPay.textProperty().addListener((observableValue, s, t1) -> {
            if(t1 != null){
                long integers = (long) (Double.parseDouble((priceToPay.textProperty().get()).replace(",",".")));
                int fractions = (int) ((BigDecimal.valueOf(Double.parseDouble((priceToPay.textProperty().get()).replace(",",".")) - integers)).setScale(2,RoundingMode.HALF_UP).doubleValue() * 100);

                String numbersInWords = Translation.numberToText(integers) + "PLN " + fractions + "/100";
                DocsModel.getDocsFXObjectProperty(saleDocModel).priceToPayInWordsProperty().bind(new SimpleStringProperty(numbersInWords));
                priceToPayInWords.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).priceToPayInWordsProperty());
            }
        });
    }

    private void bindingsContractor(){
        nameCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).nameConProperty());
        townCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).townConProperty());
        zipCodeCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).zc1ConProperty()
                .concat("-").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).zc2ConProperty()));
        streetCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).streetConProperty());
        addressCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).addressConProperty());
        nipCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).nipConProperty());
        peselCon.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).peselConProperty());
    }

    private void bindingsBusiness(){
        nameBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).nameBusProperty());
        townBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).townBusProperty());
        zipCodeBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).zc1BusProperty()
                .concat("-").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).zc2BusProperty()));
        streetBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).streetBusProperty());
        addressBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).addressBusProperty());
        nipBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).nipBusProperty());
        if(DocsModel.getDocsFXObjectProperty(saleDocModel).getBank1Bus() == null)
            bankBus.textProperty().bind(new SimpleStringProperty("__")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____")
                    .concat(" ").concat("____"));
        else
            bankBus.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).bank1BusProperty()
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank2BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank3BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank4BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank5BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank6BusProperty())
                    .concat(" ").concat(DocsModel.getDocsFXObjectProperty(saleDocModel).bank7BusProperty()));
    }

    private void bindingProductsTV(){
        productsTV.setItems(DocsModel.getDocsFXObjectProperty(saleDocModel).getListProductToAddDocs());
        lpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLp()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        quantityCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));
        unitCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnit()));
        if(titlePaneType.getText().equals("paragon") || titlePaneType.getText().equals("paragon imienny")) {
            nameCol.setPrefWidth(280);
            pkwiuCol.setVisible(false);
            unitPriceNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitPriceGross()));
        }
        else {
            pkwiuCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPkwIuCN()));
            unitPriceNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitPriceNet()));
        }
        unitPriceNetCol.setCellFactory(col ->
                new TableCell<ListProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
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
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });

        sumVatCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSumVat()));
        sumVatCol.setCellFactory(col ->
                new TableCell<ListProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });

        volGrossCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolGross()));
        volGrossCol.setCellFactory(col ->
                new TableCell<ListProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
    }

    private void bindingRatesTV(){
        ratesTV.setItems(saleDocModel.getListRateProductsToDocs());
        ratesVatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVat()));
        ratesNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolumeNet()));
        ratesNetCol.setCellFactory(col ->
                new TableCell<RateProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
        ratesSumVatCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSumVat()));
        ratesSumVatCol.setCellFactory(col ->
                new TableCell<RateProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
        ratesGrossCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolumeGain()));
        ratesGrossCol.setCellFactory(col ->
                new TableCell<RateProductsToAddDoc, Number>() {
                    @Override
                    protected void updateItem(Number price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", price.doubleValue()));
                        }
                    }
                });
    }

    private void visiblePeselOrNip(){
        if(DocsModel.getDocsFXObjectProperty(saleDocModel).getPeselCon() == null || DocsModel.getDocsFXObjectProperty(saleDocModel).getPeselCon().isEmpty()){
            nipConVbox.visibleProperty().set(true);
            peselConVbox.visibleProperty().set(false);
        } else {
            nipConVbox.visibleProperty().set(false);
            peselConVbox.visibleProperty().set(true);
        }
    }
    
    @FXML
    private void onClose() {
        SaleDocTabController.getStage().close();
    }
}
