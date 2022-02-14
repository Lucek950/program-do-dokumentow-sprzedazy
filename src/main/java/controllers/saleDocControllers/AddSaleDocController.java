package controllers.saleDocControllers;

import controllers.AddDocsController;
import controllers.AddProductToDocController;
import controllers.Controller;
import controllers.NewValueController;
import controllers.contactorControllers.ContractorController;
import controllers.warehouseControllers.productControllers.ProductController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelFx.docsFX.DocsFX;
import modelFx.docsFX.DocsModel;
import modelFx.docsFX.saleDocFX.SaleDocModel;
import modelFx.docsFX.storeDocFX.StoreDocModel;
import modelFx.entitiesFX.EntitiesFX;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;
import modelFx.productFX.ProductFX;
import modelFx.productFX.ProductModel;
import modelFx.supportsModelFX.PaymentMethodFX;
import utils.*;
import utils.exceptions.ApplicationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AddSaleDocController implements AddDocsController, Controller {

    //TitledBusiness
    @FXML
    private TextField nipBus;
    @FXML
    private Label wrongNipBus;
    @FXML
    private TextField phoneBus;
    @FXML
    private Label wrongPhoneBus;
    @FXML
    private TextField nameBus;
    @FXML
    private Label wrongNameBus;
    @FXML
    private TextField townBus;
    @FXML
    private Label wrongTownBus;
    @FXML
    private TextField zc1Bus;
    @FXML
    private Label wrongZc1Bus;
    @FXML
    private TextField zc2Bus;
    @FXML
    private Label wrongZc2Bus;
    @FXML
    private TextField streetBus;
    @FXML
    private TextField addressBus;
    @FXML
    private Label wrongAddressBus;
    @FXML
    private Label wrongBankBus;
    @FXML
    private TextField bank1Bus;
    @FXML
    private Label wrongBank1Bus;
    @FXML
    private TextField bank2Bus;
    @FXML
    private Label wrongBank2Bus;
    @FXML
    private TextField bank3Bus;
    @FXML
    private Label wrongBank3Bus;
    @FXML
    private TextField bank4Bus;
    @FXML
    private Label wrongBank4Bus;
    @FXML
    private TextField bank5Bus;
    @FXML
    private Label wrongBank5Bus;
    @FXML
    private TextField bank6Bus;
    @FXML
    private Label wrongBank6Bus;
    @FXML
    private TextField bank7Bus;
    @FXML
    private Label wrongBank7Bus;

    //TitledContractor
    @FXML
    private VBox vBoxContractorData;
    @FXML
    private TextField nipCon;
    @FXML
    private Label wrongNipCon;
    @FXML
    private RadioButton nipIdConRbtn;
    @FXML
    private HBox peselIdConHbox;
    @FXML
    private TextField peselCon;
    @FXML
    private Label wrongPeselCon;
    @FXML
    private RadioButton peselIdConRbtn;
    @FXML
    private HBox noneIdConHbox;
    @FXML
    private RadioButton noneIdConRbtn;
    @FXML
    private TextField nameCon;
    @FXML
    private Label wrongNameCon;
    @FXML
    private TextField townCon;
    @FXML
    private Label wrongTownCon;
    @FXML
    private TextField zc1Con;
    @FXML
    private Label wrongZc1Con;
    @FXML
    private TextField zc2Con;
    @FXML
    private Label wrongZc2Con;
    @FXML
    private TextField streetCon;
    @FXML
    private TextField addressCon;
    @FXML
    private Label wrongAddressCon;

    //TitledTypeDoc & DocNumber #1
    @FXML
    private TitledPane titleTypeDoc;
    @FXML
    private HBox enterDocHbox_Number;
    @FXML
    private Label typeDoc_Number;
    @FXML
    private TextField counterDoc_Number;
    @FXML
    private Label dataDoc_Number;
    @FXML
    private HBox descriptionDocHbox_Number;
    @FXML
    private Label docNumber;
    @FXML
    private Label wrongNumber;
    @FXML
    private Label notUniqueDocNumber;
    @FXML
    private CheckBox autoNumbering;
    //TitledTypeDoc #2
    @FXML
    private VBox additionalDocVbox;
    @FXML
    private TextField additionalDocTf;
    @FXML
    private Label additionalDocLabel;
    @FXML
    private TextField issuePlace;
    @FXML
    private Label wrongIssuePlace;
    @FXML
    private DatePicker issueDate;
    @FXML
    private Label wrongIssueDate;
    @FXML
    private DatePicker executeDate;
    @FXML
    private Label wrongExecuteDate;

    //ProductTableView
    @FXML
    private Button oddProductBtn;
    @FXML
    private Button addProductBtn;
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
    private TableColumn<ListProductsToAddDoc, ListProductsToAddDoc> editQuantityBtnCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> unitCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> unitPriceNetCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, String> vatCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> discountCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, ListProductsToAddDoc> editDiscountBtnCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> volNetCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> sumVatCol;
    @FXML
    private TableColumn<ListProductsToAddDoc, Number> volGrossCol;

    //TitledPayment
    @FXML
    private ComboBox<PaymentMethodFX> paymentMethod;
    @FXML
    private Label wrongPaymentMethod;
    @FXML
    private VBox splitPaymentVbox;
    @FXML
    private CheckBox splitPayment;
    @FXML
    private VBox paymentDateVbox;
    @FXML
    private DatePicker paymentDate;
    @FXML
    private Label wrongPaymentDate;
    
    //TitledIssuer
    @FXML
    private TitledPane titledPaneIssuer;
    @FXML
    private TextField issued;
    @FXML
    private Label wrongIssued;

    //RateProductTableView
    @FXML
    private TableView<RateProductsToAddDoc> ratesTableView;
    @FXML
    private TableColumn<RateProductsToAddDoc, String> ratesVatCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesVolumeNetCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesSumVatCol;
    @FXML
    private TableColumn<RateProductsToAddDoc, Number> ratesVolumeGrossCol;
    @FXML
    private TextField totalRateNetCol;
    @FXML
    private TextField totalRateSumVatCol;
    @FXML
    private TextField totalRateGrossCol;

    //PriceToPay
    @FXML
    private Label priceToPay;
    @FXML
    private Label priceToPayInWords;

    //WrongsDataDescription
    @FXML
    private Label descDataISCorrect;
    @FXML
    private Label descFillTable;

    @FXML
    private Button saveBtn;

    private SaleDocModel saleDocModel;
    protected SaleDocModel getSalesDocModel() {
        return saleDocModel;
    }

    private final ArrayList<ProductFX> productFXArrayList = new ArrayList<>();
    public void setProductFXArrayList(ProductFX productFX){
        productFXArrayList.add(productFX);
    }

    private boolean edit = false;
    protected void startEdit() {
        this.edit = true;
    }

    private void setControllers(){
        AddProductToDocController.setAddDocController(this, SaleDocController.getFlagWhatOpen() == 1);
        NewValueController.setAddDocController(this, SaleDocController.getFlagWhatOpen() == 1);
    }

    private int flagWhatSaleDocIsActive = 1;

    private void initModel(){
        if(!edit){
            saleDocModel = new SaleDocModel();
            try {
                saleDocModel.init();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
            DocsModel.getDocsFXObjectProperty(saleDocModel).setProjectDoc(saleDocModel.getTypeDocFXObservableList().get(SaleDocController.getFlagWhatOpen()).getProjectDocs().get(SaleDocTabController.getPositionSelected()));
        }
    }

    @FXML
    protected void initialize() {
        setControllers();
        initModel();
        titleTypeDoc.textProperty().set(DocsModel.getDocsFXObjectProperty(saleDocModel).getProjectDoc().getName());
        typeDoc_Number.textProperty().set(DocsModel.getDocsFXObjectProperty(saleDocModel).getProjectDoc().getShortcut());
        if(titleTypeDoc.getText().equals("faktura sprzeda¿y bez paragonu") || typeDoc_Number.getText().equals("RS ") || typeDoc_Number.getText().equals("RZ ") || typeDoc_Number.getText().equals("PA "))
            unitPriceNetCol.setText("Cena j. brutto");
        if(typeDoc_Number.getText().equals("FZ ") || typeDoc_Number.getText().equals("RZ ")){
            additionalDocVbox.setVisible(true);
            additionalDocLabel.setText("numer orygina³u");
        }
        if(titleTypeDoc.getText().equals("faktura sprzeda¿y bez paragonu")){
            additionalDocVbox.setVisible(true);
            additionalDocLabel.setText("do paragonu");
        }
        if(typeDoc_Number.getText().equals("RZ ") || typeDoc_Number.getText().equals("FZ ")) {
            peselIdConHbox.setVisible(false);
            titledPaneIssuer.setText("Odebra³");
            flagWhatSaleDocIsActive = 0;
        }
        validation();
        initCboxes();
        setTooltips();
        selectAllContentsFromTF();
        bindings();
        bindingDocNumber();
        saleDocModel.fillRatesTable();
        setButtonsCol();
        if(edit)
            showNipOrPesel();
        else
            onChooseNipOrPesel();
        setReceipt();
        disabledChangeDocNumber();
    }

    private void setReceipt(){
        if(typeDoc_Number.getText().equals("PA ")){
            paymentDateVbox.setVisible(false);
            splitPaymentVbox.setVisible(false);
            wrongPaymentDate.visibleProperty().bind(new SimpleBooleanProperty(false));
        }
        if(titleTypeDoc.getText().equals("paragon")) {
            if((nipCon.textProperty().isEmpty().get() || nipCon.textProperty().isNull().get())
                    && (peselCon.textProperty().isEmpty().get() || peselCon.textProperty().isNull().get())) {
                noneIdConRbtn.setSelected(true);
                onChooseNipOrPesel();
            }
            noneIdConHbox.setVisible(true);
            vBoxContractorData.setVisible(false);
            wrongNameCon.setVisible(false);
            wrongTownCon.setVisible(false);
            wrongZc1Con.setVisible(false);
            wrongZc2Con.setVisible(false);
            wrongAddressCon.setVisible(false);
            wrongNipCon.setVisible(false);
            wrongPeselCon.setVisible(false);
        }
    }

    private void validation(){
        nameBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(nameBus,80, wrongNameBus));
        nipBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(nipBus, 10, 10, wrongNipBus));
        phoneBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(phoneBus, 9, 12, wrongPhoneBus));
        townBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(townBus, 80, wrongTownBus));
        zc1Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(zc1Bus, 2, 2, wrongZc1Bus));
        zc2Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(zc2Bus, 3, 3, wrongZc2Bus));
        streetBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(streetBus, 40, utils.fxmlFieldUtils.ChangeListener.WITHOUT_WRONG_LABEL));
        addressBus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(addressBus, 9, wrongAddressBus));
        bank1Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank1Bus, 2, 2, wrongBank1Bus));
        bank2Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank2Bus, 4, 4, wrongBank2Bus));
        bank3Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank3Bus, 4, 4, wrongBank3Bus));
        bank4Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank4Bus, 4, 4, wrongBank4Bus));
        bank5Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank5Bus, 4, 4, wrongBank5Bus));
        bank6Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank6Bus, 4, 4, wrongBank6Bus));
        bank7Bus.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(bank7Bus, 4, 4, wrongBank7Bus));

        wrongBankBus.visibleProperty().bind(new When(bank1Bus.textProperty().isEmpty().and(bank2Bus.textProperty().isEmpty()).and(bank3Bus.textProperty().isEmpty())
                .and(bank4Bus.textProperty().isEmpty()).and(bank5Bus.textProperty().isEmpty()).and(bank6Bus.textProperty().isEmpty()).and(bank7Bus.textProperty().isEmpty()))
                .then(true).otherwise(wrongBank1Bus.visibleProperty()
                        .or(wrongBank2Bus.visibleProperty())
                        .or(wrongBank3Bus.visibleProperty())
                        .or(wrongBank4Bus.visibleProperty())
                        .or(wrongBank5Bus.visibleProperty())
                        .or(wrongBank6Bus.visibleProperty())
                        .or(wrongBank7Bus.visibleProperty())));

        nameCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(nameCon,80, wrongNameCon));
        nipCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(nipCon, 10, 10, wrongNipCon));
        peselCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(peselCon, 11, 11, wrongPeselCon));
        townCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(townCon, 80, wrongTownCon));
        zc1Con.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(zc1Con, 2, 2, wrongZc1Con));
        zc2Con.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(zc2Con, 3, 3, wrongZc2Con));
        streetCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(streetCon, 40, utils.fxmlFieldUtils.ChangeListener.WITHOUT_WRONG_LABEL));
        addressCon.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(addressCon, 9, wrongAddressCon));

        counterDoc_Number.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(counterDoc_Number,80, wrongNumber));
        additionalDocTf.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(additionalDocTf, 20, utils.fxmlFieldUtils.ChangeListener.WITHOUT_WRONG_LABEL));
        issuePlace.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(issuePlace, 80, wrongIssuePlace));
        issued.textProperty().addListener(new utils.fxmlFieldUtils.ChangeListener(issued,80, wrongIssued));

        wrongIssueDate.visibleProperty().bind(issueDate.valueProperty().isNull());
        wrongExecuteDate.visibleProperty().bind(executeDate.valueProperty().isNull());
        wrongPaymentMethod.visibleProperty().bind(paymentMethod.valueProperty().isNull());
        wrongPaymentDate.visibleProperty().bind(paymentDate.valueProperty().isNull());

        executeDate.disableProperty().bind(issueDate.valueProperty().isNull());
        paymentDate.disableProperty().bind(issueDate.valueProperty().isNull());

        descDataISCorrect.visibleProperty()
                .bind(wrongNameBus.visibleProperty()
                        .or(wrongTownBus.visibleProperty())
                        .or(wrongZc1Bus.visibleProperty())
                        .or(wrongZc2Bus.visibleProperty())
                        .or(wrongAddressBus.visibleProperty())
                        .or(wrongNipBus.visibleProperty())
                        .or(wrongPhoneBus.visibleProperty())
                        .or(wrongBankBus.visibleProperty())
                        .or(wrongNameCon.visibleProperty())
                        .or(wrongTownCon.visibleProperty())
                        .or(wrongZc1Con.visibleProperty())
                        .or(wrongZc2Con.visibleProperty())
                        .or(wrongAddressCon.visibleProperty())
                        .or(wrongNipCon.visibleProperty())
                        .or(wrongPeselCon.visibleProperty())
                        .or(wrongNumber.visibleProperty())
                        .or(wrongIssuePlace.visibleProperty())
                        .or(wrongIssueDate.visibleProperty())
                        .or(wrongExecuteDate.visibleProperty())
                        .or(wrongPaymentMethod.visibleProperty())
                        .or(wrongPaymentDate.visibleProperty())
                        .or(wrongIssued.visibleProperty())
                        .or(descFillTable.visibleProperty())
                        .or(notUniqueDocNumber.visibleProperty()));

        saveBtn.disableProperty().bind(descDataISCorrect.visibleProperty());
    }

    private void selectAllContentsFromTF(){
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nameBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(townBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(zc1Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(zc2Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(streetBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(addressBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nipBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(phoneBus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank1Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank2Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank3Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank4Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank5Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank6Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(bank7Bus);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nameCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(townCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(zc1Con);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(zc2Con);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(streetCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(addressCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(nipCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(peselCon);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(counterDoc_Number);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(additionalDocTf);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(issuePlace);
        UtilsProject.selectAllInTextFieldWhenClickOnMouse(issued);
    }

    private void bindingDocNumber(){
        if(!edit)
            if(autoNumbering.isSelected()){
                counterDoc_Number.textProperty().set("(auto)");
                counterDoc_Number.editableProperty().set(false);
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().unbind();
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().bind(new SimpleStringProperty(typeDoc_Number.getText() + saleDocModel.autoNumber(typeDoc_Number.getText(), dataDoc_Number.getText())));
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberToPrintProperty().unbind();
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberToPrintProperty().bind(
                        new SimpleStringProperty(DocsModel.getDocsFXObjectProperty(saleDocModel).getProjectDoc().getNameToPrint() + saleDocModel.autoNumber(typeDoc_Number.getText(), dataDoc_Number.getText())));
            } else {
                counterDoc_Number.textProperty().set("");
                counterDoc_Number.editableProperty().set(true);
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().unbind();
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().bind(new SimpleStringProperty(typeDoc_Number.getText() + saleDocModel.autoNumber(typeDoc_Number.getText(), dataDoc_Number.getText())));
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberToPrintProperty().unbind();
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberToPrintProperty().bind(
                        new SimpleStringProperty(DocsModel.getDocsFXObjectProperty(saleDocModel).getProjectDoc().getNameToPrint() + saleDocModel.autoNumber(typeDoc_Number.getText(), dataDoc_Number.getText())));
                DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().addListener((observableValue, s, t1) -> {
                    if(saleDocModel.checkIfDocNumberIsUnique(t1)) {
                        notUniqueDocNumber.visibleProperty().bindBidirectional(new SimpleBooleanProperty(true));
                    }
                    else{
                        notUniqueDocNumber.visibleProperty().bindBidirectional(new SimpleBooleanProperty(false));
                    }
                });
            }
    }

    @FXML
    private void onSetAutoNumber() {
        bindingDocNumber();
    }

    private void initCboxes() {
        paymentMethod.setItems(saleDocModel.getPaymentMethodFXObservableList());
    }

    private void disabledChangeDocNumber(){
        if(edit){
            descriptionDocHbox_Number.visibleProperty().set(true);
            enterDocHbox_Number.visibleProperty().set(false);
            DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty().unbind();
        }
    }

    private void bindings(){
        bindingsBusiness();
        bindingsContractor();

        docNumber.textProperty().bind(DocsModel.getDocsFXObjectProperty(saleDocModel).numberProperty());
        additionalDocTf.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).additionalDocProperty());
        issuePlace.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).issuePlaceProperty());
        issueDate.valueProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).issueDateProperty());
        executeDate.valueProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).executeDateProperty());

        productsTV.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(productsTV.getItems().size() > 0)
                oddProductBtn.setDisable(false);
        });

        bindingProductsTV();
        DocsModel.getDocsFXObjectProperty(saleDocModel).listRateProductsToAddDocsProperty().bind(ratesTableView.itemsProperty());
        bindingRatesTV();
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumNetProperty().bind(totalRateNetCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumVatProperty().bind(totalRateSumVatCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).rateSumGrossProperty().bind(totalRateGrossCol.textProperty());
        DocsModel.getDocsFXObjectProperty(saleDocModel).priceToPayProperty().bind(priceToPay.textProperty());

        priceToPay.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalVolGrossPropertyProperty()));
        totalRateNetCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateVolNetPropertyProperty()));
        totalRateSumVatCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateSumVatPropertyProperty()));
        totalRateGrossCol.textProperty().bind(Bindings.format("%.2f", saleDocModel.totalRateVolGrossPropertyProperty()));

        paymentMethod.valueProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).paymentMethodFXProperty());
        paymentDate.valueProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).paymentDateProperty());
        splitPayment.selectedProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).splitPaymentProperty());

        issued.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).issuedProperty());
        saleDocModel.totalsVolGainFromProductTable();

        issueDate.valueProperty().addListener((observableValue, localDate, t1) -> {
                if(t1 == null)
                    dataDoc_Number.textProperty().set("/........");
                else {
                    dataDoc_Number.textProperty().set("/" + issueDate.valueProperty().get().getYear());
                    bindingDocNumber();
                }
        });

        executeDate.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(issueDate.getValue()));
            }});

        paymentDate.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(issueDate.getValue()));
            }});

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
        nameCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).nameConProperty());
        townCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).townConProperty());
        zc1Con.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).zc1ConProperty());
        zc2Con.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).zc2ConProperty());
        streetCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).streetConProperty());
        addressCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).addressConProperty());
        nipCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).nipConProperty());
        peselCon.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).peselConProperty());
    }

    private void bindingsBusiness(){
        nameBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).nameBusProperty());
        townBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).townBusProperty());
        zc1Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).zc1BusProperty());
        zc2Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).zc2BusProperty());
        streetBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).streetBusProperty());
        addressBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).addressBusProperty());
        nipBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).nipBusProperty());
        phoneBus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).phoneBusProperty());
        bank1Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank1BusProperty());
        bank2Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank2BusProperty());
        bank3Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank3BusProperty());
        bank4Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank4BusProperty());
        bank5Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank5BusProperty());
        bank6Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank6BusProperty());
        bank7Bus.textProperty().bindBidirectional(DocsModel.getDocsFXObjectProperty(saleDocModel).bank7BusProperty());
    }

    public void bindingProductsTV(){
        productsTV.setItems(DocsModel.getDocsFXObjectProperty(saleDocModel).getListProductToAddDocs());
        lpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLp()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        quantityCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));
        unitCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnit()));
        if(typeDoc_Number.getText().equals("PA ") || titleTypeDoc.getText().equals("faktura sprzeda¿y bez paragonu")) {
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
        disabledOrNotSetTableLabel();
    }

    private void bindingRatesTV(){
        ratesTableView.setItems(saleDocModel.getListRateProductsToDocs());
        ratesVatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVat()));
        ratesVolumeNetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolumeNet()));
        ratesVolumeNetCol.setCellFactory(col ->
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

        ratesVolumeGrossCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getVolumeGain()));
        ratesVolumeGrossCol.setCellFactory(col ->
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

    private void setButtonsCol(){
        editQuantityBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editQuantityBtnCol.setCellFactory(parm -> new TableCell<ListProductsToAddDoc, ListProductsToAddDoc>(){
            final Button buttonEdit = UtilsProject.createButton(FxmlPath.ICON_EDIT);
            @Override
            protected void updateItem(ListProductsToAddDoc item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                buttonEdit.setTooltip(new Tooltip("Zmieñ iloœæ"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.NEW_VALUE_FXML)));
                    NewValueController controller = (NewValueController) FxmlUtils.getController();
                    stage = new Stage();

                    controller.setTextLabel();
                    controller.setSalesDocModel(saleDocModel, stage, SaleDocController.getFlagWhatOpen(), SaleDocTabController.getPositionSelected());
                    controller.setFlag(0);
                    controller.setLp(item.getLp() - 1);

                    stage.setTitle("Zmieñ iloœæ tego produktu");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });

        editDiscountBtnCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editDiscountBtnCol.setCellFactory(parm -> new TableCell<ListProductsToAddDoc, ListProductsToAddDoc>(){
            final Button buttonEdit = UtilsProject.createButton(FxmlPath.ICON_EDIT);
            @Override
            protected void updateItem(ListProductsToAddDoc item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }
                buttonEdit.setTooltip(new Tooltip("Zmieñ rabat"));
                setGraphic(buttonEdit);
                buttonEdit.setOnAction(event -> {
                    Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.NEW_VALUE_FXML)));
                    stage = new Stage();

                    NewValueController controller = (NewValueController) FxmlUtils.getController();
                    controller.setSalesDocModel(saleDocModel, stage, SaleDocController.getFlagWhatOpen(), SaleDocTabController.getPositionSelected());
                    controller.setFlag(1);
                    controller.setLp(item.getLp() - 1);
                    controller.setTextLabel();

                    stage.setTitle("Zmieñ wysokoœæ rabatu");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                });
            }
        });
    }

    private void setContractorToForm(EntitiesFX entitiesFX, String pesel){
        nameCon.textProperty().set(entitiesFX.getName());
        streetCon.textProperty().set(entitiesFX.getStreet());
        addressCon.textProperty().set(entitiesFX.getAddress());
        townCon.textProperty().set(entitiesFX.getTown());
        zc1Con.textProperty().set(entitiesFX.getZc1());
        zc2Con.textProperty().set(entitiesFX.getZc2());
        nipCon.textProperty().set(entitiesFX.getNip());
        if(typeDoc_Number.getText().equals("RZ ") || typeDoc_Number.getText().equals("FZ "))
            showNip(true);
        else {
            peselCon.textProperty().set(pesel);
            showNipOrPesel();
        }
    }

    private void setBusinessToForm(EntitiesFX entitiesFx){
        nameBus.textProperty().set(entitiesFx.getName());
        streetBus.textProperty().set(entitiesFx.getStreet());
        addressBus.textProperty().set(entitiesFx.getAddress());
        townBus.textProperty().set(entitiesFx.getTown());
        zc1Bus.textProperty().set(entitiesFx.getZc1());
        zc2Bus.textProperty().set(entitiesFx.getZc2());
        nipBus.textProperty().set(entitiesFx.getNip());
        phoneBus.textProperty().set(entitiesFx.getPhone());
        bank1Bus.textProperty().set(entitiesFx.getBank1());
        bank2Bus.textProperty().set(entitiesFx.getBank2());
        bank3Bus.textProperty().set(entitiesFx.getBank3());
        bank4Bus.textProperty().set(entitiesFx.getBank4());
        bank5Bus.textProperty().set(entitiesFx.getBank5());
        bank6Bus.textProperty().set(entitiesFx.getBank6());
        bank7Bus.textProperty().set(entitiesFx.getBank7());
    }

    private void setTooltips(){
        oddProductBtn.setTooltip(new Tooltip("Usuñ produkt"));
        addProductBtn.setTooltip(new Tooltip("Dodaj produkt"));
    }

    private void clearFewFields(){
        clearContractor();
        additionalDocTf.clear();
        DocsModel.getDocsFXObjectProperty(saleDocModel).getListProductToAddDocs().clear();
        saleDocModel.getListRateProductsToDocs().clear();
        saleDocModel.totalsFromColsInRatesTable();
        paymentDate.setValue(null);
        executeDate.setValue(null);
        paymentMethod.selectionModelProperty().get().clearSelection();
        splitPayment.selectedProperty().set(false);
    }

    @FXML
    private void clearAllFieldsAction(){
        clearBusiness();
        issuePlace.clear();
        issueDate.setValue(null);
        clearFewFields();
        issued.clear();
    }

    @FXML
    private void onCancel(){
        SaleDocTabController.getStage().close();
    }

    private static SaleDocController saleDocController;
    protected static void setSalesDocController(SaleDocController saleDocController) {
        AddSaleDocController.saleDocController = saleDocController;
    }

    @FXML
    private void onSave() {
        Optional<ButtonType> result = DialogUtils.confirmDialog();
        if(result.isPresent()) {
            try {
                saleDocModel.saveSalesDocInDataBase();
                createStoreDoc(saleDocModel);
                for (ProductFX productFX : productFXArrayList) {
                    ProductModel productModel = new ProductModel();
                    productModel.setProductFXObjectProperty(productFX);
                    productModel.saveProductInDataBase();
                }
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
            saleDocController.initialize();
            if(edit){
                edit = false;
            } else {
                clearFewFields();
                initialize();
            }
            SaleDocTabController.getStage().close();
        }
    }

    private void disabledOrNotSetTableLabel(){
        descFillTable.setVisible(DocsModel.getDocsFXObjectProperty(saleDocModel).listProductToAddDocsProperty().get().isEmpty());
    }

    @FXML
    private void onOddProduct() {
        try{
            DocsModel.getDocsFXObjectProperty(saleDocModel).listProductToAddDocsProperty().get()
                    .remove(productsTV.getSelectionModel().getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException e){
            DialogUtils.infoDialog("Wybierz pozycje z listy do usuniêcia");
        }
        saleDocModel.totalsFromColsInRatesTable();
        saleDocModel.fillRatesTable();
        saleDocModel.totalsVolGainFromProductTable();
        saleDocModel.setLpWhenDeleteElementFromList();
        disabledOrNotSetTableLabel();
        oddProductBtn.setDisable(true);
    }

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }

    @FXML
    private void onAddPaymentMethod() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.NEW_VALUE_FXML)));
        stage = new Stage();

        NewValueController controller = (NewValueController) FxmlUtils.getController();
        controller.setSalesDocModel(saleDocModel, stage, SaleDocController.getFlagWhatOpen(), SaleDocTabController.getPositionSelected());
        controller.setFlag(2);
        controller.setTextLabel();

        stage.setTitle("Utwórz metodê p³atnoœci");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onAddProduct() {
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.PRODUCT_FXML)));
        ProductController controller = (ProductController) FxmlUtils.getController();
        controller.visibleAddSaleDocTitledPane();
        AddProductToDocController.setDocsModel(saleDocModel);
        stage = new Stage();
        stage.setTitle("Baza towrów/us³ug");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        disabledOrNotSetTableLabel();
    }

    private void clearContractor(){
        nameCon.clear();
        townCon.clear();
        zc1Con.clear();
        zc2Con.clear();
        streetCon.clear();
        addressCon.clear();
        nipCon.clear();
        peselCon.clear();
    }

    private void clearBusiness(){
        nameBus.clear();
        townBus.clear();
        zc1Bus.clear();
        zc2Bus.clear();
        streetBus.clear();
        addressBus.clear();
        nipBus.clear();
        phoneBus.clear();
        bank1Bus.clear();
        bank2Bus.clear();
        bank3Bus.clear();
        bank4Bus.clear();
        bank5Bus.clear();
        bank6Bus.clear();
        bank7Bus.clear();
    }

    private void gotoContractor(){
        FXMLLoader loader = FxmlUtils.getLoader(FxmlPath.CONTRACTOR_FXML);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        ContractorController controller = loader.getController();
        controller.visibleAddSaleDocTitledPane();

        stage = new Stage();
        stage.setTitle("Baza kontrahentów");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void onSetContractor() {
        clearContractor();
        gotoContractor();
        setContractorToForm(DocsModel.contractorFXObjectPropertyProperty().get(), DocsModel.contractorFXObjectPropertyProperty().get().getPesel());
    }

    @FXML
    private void onSetBusiness() {
        clearBusiness();
        setBusinessToForm(saleDocModel.getBusinessFXObjectProperty());
    }

    @FXML
    private void onClearOtherDp() {
        executeDate.valueProperty().set(null);
        paymentDate.valueProperty().set(null);
    }

    @FXML
    private void onChooseNipOrPesel() {
        if(noneIdConRbtn.isSelected()){
            nipCon.clear();
            peselCon.clear();
            nipCon.setVisible(nipIdConRbtn.selectedProperty().get());
            peselCon.setVisible(nipIdConRbtn.selectedProperty().get());
            wrongNipCon.setVisible(nipIdConRbtn.selectedProperty().get());
            wrongPeselCon.setVisible(nipIdConRbtn.selectedProperty().get());
        }
        if(nipIdConRbtn.isSelected()) {
            peselCon.clear();
            showNip(nipIdConRbtn.selectedProperty().get());
            wrongNipCon.setVisible(nipIdConRbtn.selectedProperty().get());
        }
        if(peselIdConRbtn.isSelected()){
            nipCon.clear();
            showPesel(peselIdConRbtn.selectedProperty().get());
            wrongPeselCon.setVisible(peselIdConRbtn.selectedProperty().get());
        }
    }

    private void showNipOrPesel(){
        if(nipCon.textProperty().isEmpty().get() || nipCon.textProperty().isNull().get()){
            peselIdConRbtn.setSelected(true);
            showPesel(peselIdConRbtn.selectedProperty().get());
        }
        if(peselCon.textProperty().isEmpty().get() || peselCon.textProperty().isNull().get()){
            nipIdConRbtn.setSelected(true);
            showNip(nipIdConRbtn.selectedProperty().get());
        }
    }

    private void showNip(boolean flag){
        nipCon.setVisible(flag);
        peselCon.setVisible(!flag);
        wrongPeselCon.setVisible(!flag);
    }

    private void showPesel(boolean flag){
        nipCon.setVisible(!flag);
        peselCon.setVisible(flag);
        wrongNipCon.setVisible(!flag);
    }

    private void createStoreDoc(SaleDocModel saleDocModel) throws ApplicationException {
        StoreDocModel storeDocModel = new StoreDocModel();
        storeDocModel.setFlag(flagWhatSaleDocIsActive);
        storeDocModel.init();

        DocsFX storeDoc = DocsModel.getDocsFXObjectProperty(storeDocModel);
        DocsFX saleDoc = DocsModel.getDocsFXObjectProperty(saleDocModel);

        String shortcutTypeDoc = storeDocModel.getTypeDocFXObservableList().get(flagWhatSaleDocIsActive).getProjectDocs().get(0).getShortcut();
        String numberDoc = storeDocModel.autoNumber(shortcutTypeDoc , "/" + issueDate.valueProperty().get().getYear());

        storeDoc.setNumber(shortcutTypeDoc + numberDoc);
        storeDoc.setNumberToPrint("Auto " + storeDocModel.getTypeDocFXObservableList().get(flagWhatSaleDocIsActive).getProjectDocs().get(0).getShortcut() + numberDoc);

        for (DocsFX storeDocItem : storeDocModel.getDocsFXObservableList()) {
            if (storeDocItem.getRelatedDoc() != null && storeDocItem.getRelatedDoc().equals(saleDoc.getNumber())) {
                storeDocModel.setDocsFXObjectProperty(storeDocItem);
                storeDoc = DocsModel.getDocsFXObjectProperty(storeDocModel);
                storeDoc.setId(storeDocItem.getId());
                storeDoc.setNumber(storeDocItem.getNumber());
                storeDoc.setNumberToPrint(storeDocItem.getNumberToPrint());
                break;
            }
        }

        storeDoc.setProjectDoc(storeDocModel.getTypeDocFXObservableList().get(flagWhatSaleDocIsActive).getProjectDocs().get(0));

        storeDoc.setNameBus(saleDoc.getNameBus());
        storeDoc.setTownBus(saleDoc.getTownBus());
        storeDoc.setZc1Bus(saleDoc.getZc1Bus());
        storeDoc.setZc2Bus(saleDoc.getZc2Bus());
        storeDoc.setStreetBus(saleDoc.getStreetBus());
        storeDoc.setAddressBus(saleDoc.getAddressBus());
        storeDoc.setNipBus(saleDoc.getNipBus());
        storeDoc.setPhoneBus(saleDoc.getPhoneBus());
        storeDoc.setBank1Bus(saleDoc.getBank1Bus());
        storeDoc.setBank2Bus(saleDoc.getBank2Bus());
        storeDoc.setBank3Bus(saleDoc.getBank3Bus());
        storeDoc.setBank4Bus(saleDoc.getBank4Bus());
        storeDoc.setBank5Bus(saleDoc.getBank5Bus());
        storeDoc.setBank6Bus(saleDoc.getBank6Bus());
        storeDoc.setBank7Bus(saleDoc.getBank7Bus());

        storeDoc.setNameCon(saleDoc.getNameCon());
        storeDoc.setTownCon(saleDoc.getTownCon());
        storeDoc.setZc1Con(saleDoc.getZc1Con());
        storeDoc.setZc2Con(saleDoc.getZc2Con());
        storeDoc.setStreetCon(saleDoc.getStreetCon());
        storeDoc.setAddressCon(saleDoc.getAddressCon());
        storeDoc.setNipCon(saleDoc.getNipCon());
        storeDoc.setPeselCon(saleDoc.getPeselCon());

        storeDoc.setIssuePlace(saleDoc.getIssuePlace());
        storeDoc.setIssueDate(saleDoc.getIssueDate());

        storeDoc.setListProductToAddDocs(saleDoc.getListProductToAddDocs());
        storeDoc.setRateSumNet(saleDoc.getRateSumNet());

        storeDoc.setPriceToPay(saleDoc.getPriceToPay());
        storeDoc.setPriceToPayInWords(saleDoc.getPriceToPayInWords());

        storeDoc.setIssued(saleDoc.getIssued());
        storeDoc.setCost(String.valueOf(BigDecimal.valueOf(saleDocModel.totalCostPropertyProperty().get())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue()));

        storeDoc.setRelatedDoc(saleDoc.getNumber());

        storeDocModel.saveDocsInDataBase();
    }
}
