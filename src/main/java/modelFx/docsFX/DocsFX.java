package modelFx.docsFX;

import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;
import modelFx.supportsModelFX.PaymentMethodFX;

import java.time.LocalDate;

public class DocsFX {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<ProjectDoc> projectDoc = new SimpleObjectProperty<>();

    private final StringProperty nameBus = new SimpleStringProperty();
    private final StringProperty townBus = new SimpleStringProperty();
    private final  StringProperty zc1Bus = new SimpleStringProperty();
    private final  StringProperty zc2Bus = new SimpleStringProperty();
    private final  StringProperty streetBus = new SimpleStringProperty();
    private final StringProperty addressBus = new SimpleStringProperty();
    private final StringProperty nipBus = new SimpleStringProperty();
    private final StringProperty phoneBus = new SimpleStringProperty();
    private final StringProperty bank1Bus = new SimpleStringProperty();
    private final StringProperty bank2Bus = new SimpleStringProperty();
    private final StringProperty bank3Bus = new SimpleStringProperty();
    private final StringProperty bank4Bus = new SimpleStringProperty();
    private final StringProperty bank5Bus = new SimpleStringProperty();
    private final StringProperty bank6Bus = new SimpleStringProperty();
    private final StringProperty bank7Bus = new SimpleStringProperty();

    private final StringProperty nameCon = new SimpleStringProperty();
    private final StringProperty townCon = new SimpleStringProperty();
    private final StringProperty zc1Con = new SimpleStringProperty();
    private final StringProperty zc2Con = new SimpleStringProperty();
    private final StringProperty streetCon = new SimpleStringProperty();
    private final StringProperty addressCon = new SimpleStringProperty();
    private final StringProperty nipCon = new SimpleStringProperty();
    private final StringProperty peselCon = new SimpleStringProperty();

    private final StringProperty number = new SimpleStringProperty();
    private final StringProperty numberToPrint = new SimpleStringProperty();
    private final StringProperty additionalDoc = new SimpleStringProperty();
    private final StringProperty issuePlace = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> issueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> executeDate = new SimpleObjectProperty<>();

    private final SimpleObjectProperty<ObservableList<ListProductsToAddDoc>> listProductToAddDocs = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private final SimpleObjectProperty<ObservableList<RateProductsToAddDoc>> listRateProductsToAddDocs = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private final StringProperty rateSumNet = new SimpleStringProperty();
    private final StringProperty rateSumVat = new SimpleStringProperty();
    private final StringProperty rateSumGross = new SimpleStringProperty();
    private final StringProperty priceToPay = new SimpleStringProperty();
    private final StringProperty priceToPayInWords = new SimpleStringProperty();

    private final ObjectProperty<PaymentMethodFX> paymentMethodFX = new SimpleObjectProperty<>();
    private final BooleanProperty splitPayment = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> paymentDate = new SimpleObjectProperty<>();

    private final StringProperty comment = new SimpleStringProperty();

    private final StringProperty issued = new SimpleStringProperty();

    private final StringProperty relatedDoc = new SimpleStringProperty();
    private final StringProperty cost = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public ProjectDoc getProjectDoc() {
        return projectDoc.get();
    }

    public ObjectProperty<ProjectDoc> projectDocProperty() {
        return projectDoc;
    }

    public void setProjectDoc(ProjectDoc projectDoc) {
        this.projectDoc.set(projectDoc);
    }

    public String getNameBus() {
        return nameBus.get();
    }

    public StringProperty nameBusProperty() {
        return nameBus;
    }

    public void setNameBus(String nameBus) {
        this.nameBus.set(nameBus);
    }

    public String getTownBus() {
        return townBus.get();
    }

    public StringProperty townBusProperty() {
        return townBus;
    }

    public void setTownBus(String townBus) {
        this.townBus.set(townBus);
    }

    public String getZc1Bus() {
        return zc1Bus.get();
    }

    public StringProperty zc1BusProperty() {
        return zc1Bus;
    }

    public void setZc1Bus(String zc1Bus) {
        this.zc1Bus.set(zc1Bus);
    }

    public String getZc2Bus() {
        return zc2Bus.get();
    }

    public StringProperty zc2BusProperty() {
        return zc2Bus;
    }

    public void setZc2Bus(String zc2Bus) {
        this.zc2Bus.set(zc2Bus);
    }

    public String getStreetBus() {
        return streetBus.get();
    }

    public StringProperty streetBusProperty() {
        return streetBus;
    }

    public void setStreetBus(String streetBus) {
        this.streetBus.set(streetBus);
    }

    public String getAddressBus() {
        return addressBus.get();
    }

    public StringProperty addressBusProperty() {
        return addressBus;
    }

    public void setAddressBus(String addressBus) {
        this.addressBus.set(addressBus);
    }

    public String getNipBus() {
        return nipBus.get();
    }

    public StringProperty nipBusProperty() {
        return nipBus;
    }

    public void setNipBus(String nipBus) {
        this.nipBus.set(nipBus);
    }

    public String getPhoneBus() {
        return phoneBus.get();
    }

    public StringProperty phoneBusProperty() {
        return phoneBus;
    }

    public void setPhoneBus(String phoneBus) {
        this.phoneBus.set(phoneBus);
    }

    public String getBank1Bus() {
        return bank1Bus.get();
    }

    public StringProperty bank1BusProperty() {
        return bank1Bus;
    }

    public void setBank1Bus(String bank1Bus) {
        this.bank1Bus.set(bank1Bus);
    }

    public String getBank2Bus() {
        return bank2Bus.get();
    }

    public StringProperty bank2BusProperty() {
        return bank2Bus;
    }

    public void setBank2Bus(String bank2Bus) {
        this.bank2Bus.set(bank2Bus);
    }

    public String getBank3Bus() {
        return bank3Bus.get();
    }

    public StringProperty bank3BusProperty() {
        return bank3Bus;
    }

    public void setBank3Bus(String bank3Bus) {
        this.bank3Bus.set(bank3Bus);
    }

    public String getBank4Bus() {
        return bank4Bus.get();
    }

    public StringProperty bank4BusProperty() {
        return bank4Bus;
    }

    public void setBank4Bus(String bank4Bus) {
        this.bank4Bus.set(bank4Bus);
    }

    public String getBank5Bus() {
        return bank5Bus.get();
    }

    public StringProperty bank5BusProperty() {
        return bank5Bus;
    }

    public void setBank5Bus(String bank5Bus) {
        this.bank5Bus.set(bank5Bus);
    }

    public String getBank6Bus() {
        return bank6Bus.get();
    }

    public StringProperty bank6BusProperty() {
        return bank6Bus;
    }

    public void setBank6Bus(String bank6Bus) {
        this.bank6Bus.set(bank6Bus);
    }

    public String getBank7Bus() {
        return bank7Bus.get();
    }

    public StringProperty bank7BusProperty() {
        return bank7Bus;
    }

    public void setBank7Bus(String bank7Bus) {
        this.bank7Bus.set(bank7Bus);
    }

    public String getNameCon() {
        return nameCon.get();
    }

    public StringProperty nameConProperty() {
        return nameCon;
    }

    public void setNameCon(String nameCon) {
        this.nameCon.set(nameCon);
    }

    public String getTownCon() {
        return townCon.get();
    }

    public StringProperty townConProperty() {
        return townCon;
    }

    public void setTownCon(String townCon) {
        this.townCon.set(townCon);
    }

    public String getZc1Con() {
        return zc1Con.get();
    }

    public StringProperty zc1ConProperty() {
        return zc1Con;
    }

    public void setZc1Con(String zc1Con) {
        this.zc1Con.set(zc1Con);
    }

    public String getZc2Con() {
        return zc2Con.get();
    }

    public StringProperty zc2ConProperty() {
        return zc2Con;
    }

    public void setZc2Con(String zc2Con) {
        this.zc2Con.set(zc2Con);
    }

    public String getStreetCon() {
        return streetCon.get();
    }

    public StringProperty streetConProperty() {
        return streetCon;
    }

    public void setStreetCon(String streetCon) {
        this.streetCon.set(streetCon);
    }

    public String getAddressCon() {
        return addressCon.get();
    }

    public StringProperty addressConProperty() {
        return addressCon;
    }

    public void setAddressCon(String addressCon) {
        this.addressCon.set(addressCon);
    }

    public String getNipCon() {
        return nipCon.get();
    }

    public StringProperty nipConProperty() {
        return nipCon;
    }

    public void setNipCon(String nipCon) {
        this.nipCon.set(nipCon);
    }

    public String getPeselCon() {
        return peselCon.get();
    }

    public StringProperty peselConProperty() {
        return peselCon;
    }

    public void setPeselCon(String peselCon) {
        this.peselCon.set(peselCon);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getNumberToPrint() {
        return numberToPrint.get();
    }

    public StringProperty numberToPrintProperty() {
        return numberToPrint;
    }

    public void setNumberToPrint(String numberToPrint) {
        this.numberToPrint.set(numberToPrint);
    }

    public String getAdditionalDoc() {
        return additionalDoc.get();
    }

    public StringProperty additionalDocProperty() {
        return additionalDoc;
    }

    public void setAdditionalDoc(String additionalDoc) {
        this.additionalDoc.set(additionalDoc);
    }

    public String getIssuePlace() {
        return issuePlace.get();
    }

    public StringProperty issuePlaceProperty() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace.set(issuePlace);
    }

    public LocalDate getIssueDate() {
        return issueDate.get();
    }

    public ObjectProperty<LocalDate> issueDateProperty() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate.set(issueDate);
    }

    public LocalDate getExecuteDate() {
        return executeDate.get();
    }

    public ObjectProperty<LocalDate> executeDateProperty() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate.set(executeDate);
    }

    public ObservableList<ListProductsToAddDoc> getListProductToAddDocs() {
        return listProductToAddDocs.get();
    }

    public SimpleObjectProperty<ObservableList<ListProductsToAddDoc>> listProductToAddDocsProperty() {
        return listProductToAddDocs;
    }

    public void setListProductToAddDocs(ObservableList<ListProductsToAddDoc> listProductToAddDocs) {
        this.listProductToAddDocs.set(listProductToAddDocs);
    }

    public ObservableList<RateProductsToAddDoc> getListRateProductsToAddDocs() {
        return listRateProductsToAddDocs.get();
    }

    public SimpleObjectProperty<ObservableList<RateProductsToAddDoc>> listRateProductsToAddDocsProperty() {
        return listRateProductsToAddDocs;
    }

    public void setListRateProductsToAddDocs(ObservableList<RateProductsToAddDoc> listRateProductsToAddDocs) {
        this.listRateProductsToAddDocs.set(listRateProductsToAddDocs);
    }

    public String getRateSumNet() {
        return rateSumNet.get();
    }

    public StringProperty rateSumNetProperty() {
        return rateSumNet;
    }

    public void setRateSumNet(String rateSumNet) {
        this.rateSumNet.set(rateSumNet);
    }

    public String getRateSumVat() {
        return rateSumVat.get();
    }

    public StringProperty rateSumVatProperty() {
        return rateSumVat;
    }

    public void setRateSumVat(String rateSumVat) {
        this.rateSumVat.set(rateSumVat);
    }

    public String getRateSumGross() {
        return rateSumGross.get();
    }

    public StringProperty rateSumGrossProperty() {
        return rateSumGross;
    }

    public void setRateSumGross(String rateSumGross) {
        this.rateSumGross.set(rateSumGross);
    }

    public String getPriceToPay() {
        return priceToPay.get();
    }

    public StringProperty priceToPayProperty() {
        return priceToPay;
    }

    public void setPriceToPay(String priceToPay) {
        this.priceToPay.set(priceToPay);
    }

    public String getPriceToPayInWords() {
        return priceToPayInWords.get();
    }

    public StringProperty priceToPayInWordsProperty() {
        return priceToPayInWords;
    }

    public void setPriceToPayInWords(String priceToPayInWords) {
        this.priceToPayInWords.set(priceToPayInWords);
    }

    public PaymentMethodFX getPaymentMethodFX() {
        return paymentMethodFX.get();
    }

    public ObjectProperty<PaymentMethodFX> paymentMethodFXProperty() {
        return paymentMethodFX;
    }

    public void setPaymentMethodFX(PaymentMethodFX paymentMethodFX) {
        this.paymentMethodFX.set(paymentMethodFX);
    }

    public boolean isSplitPayment() {
        return splitPayment.get();
    }

    public BooleanProperty splitPaymentProperty() {
        return splitPayment;
    }

    public void setSplitPayment(boolean splitPayment) {
        this.splitPayment.set(splitPayment);
    }

    public LocalDate getPaymentDate() {
        return paymentDate.get();
    }

    public ObjectProperty<LocalDate> paymentDateProperty() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate.set(paymentDate);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getIssued() {
        return issued.get();
    }

    public StringProperty issuedProperty() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued.set(issued);
    }

    public String getRelatedDoc() {
        return relatedDoc.get();
    }

    public StringProperty relatedDocProperty() {
        return relatedDoc;
    }

    public void setRelatedDoc(String relatedDoc) {
        this.relatedDoc.set(relatedDoc);
    }

    public String getCost() {
        return cost.get();
    }

    public StringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }
}
