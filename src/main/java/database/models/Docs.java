package database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import database.models.supportTablesModel.PaymentMethod;
import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;

import java.util.ArrayList;
import java.util.Date;

public class Docs implements BaseModel{
    public Docs(){}

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ProjectDoc projectDoc;

    @DatabaseField(columnName = "NAME_SEL")
    private String nameBus;
    @DatabaseField(columnName = "TOWN_SEL")
    private String townBus;
    @DatabaseField(columnName = "ZC1_SEL")
    private String zc1Bus;
    @DatabaseField(columnName = "ZC2_SEL")
    private String zc2Bus;
    @DatabaseField(columnName = "STREET_SEL")
    private String streetBus;
    @DatabaseField(columnName = "ADDRESS_SEL")
    private String addressBus;
    @DatabaseField(columnName = "NIP_SEL")
    private String nipBus;
    @DatabaseField(columnName = "PHONE_SEL")
    private String phoneBus;
    @DatabaseField(columnName = "BANK1_SEL")
    private String bank1Bus;
    @DatabaseField(columnName = "BANK2_SEL")
    private String bank2Bus;
    @DatabaseField(columnName = "BANK3_SEL")
    private String bank3Bus;
    @DatabaseField(columnName = "BANK4_SEL")
    private String bank4Bus;
    @DatabaseField(columnName = "BANK5_SEL")
    private String bank5Bus;
    @DatabaseField(columnName = "BANK6_SEL")
    private String bank6Bus;
    @DatabaseField(columnName = "BANK7_SEL")
    private String bank7Bus;

    @DatabaseField(columnName = "NAME_BUY")
    private String nameCon;
    @DatabaseField(columnName = "TOWN_BUY")
    private String townCon;
    @DatabaseField(columnName = "ZC1_BUY")
    private String zc1Con;
    @DatabaseField(columnName = "ZC2_BUY")
    private String zc2Con;
    @DatabaseField(columnName = "STREET_BUY")
    private String streetCon;
    @DatabaseField(columnName = "ADDRESS_BUY")
    private String addressCon;
    @DatabaseField(columnName = "NIP_BUY")
    private String nipCon;
    @DatabaseField(columnName = "PESEL_BUY")
    private String peselCon;

    @DatabaseField(columnName = "NUMBER", unique = true)
    private String number;
    @DatabaseField(columnName = "NUMBER_TO_PRINT")
    private String numberToPrint;
    @DatabaseField(columnName = "ADDITIONAL_DOC")
    private String additionalDoc;
    @DatabaseField(columnName = "ISSUE_PLACE")
    private String issuePlace;
    @DatabaseField(columnName = "ISSUE_DATE")
    private Date issueDate;
    @DatabaseField(columnName = "EXECUTE_DATE")
    private Date executeDate;

    @DatabaseField(dataType = DataType.SERIALIZABLE, columnName = "LIST_PRODUCTS")
    private ArrayList<ListProductsToAddDoc> listProductToAddDocs;
    @DatabaseField(dataType = DataType.SERIALIZABLE, columnName = "RATES_PRODUCTS")
    private ArrayList<RateProductsToAddDoc> listRateProductsToAddDocs;
    @DatabaseField(columnName = "RATE_SUM_NET")
    private String rateSumNet;
    @DatabaseField(columnName = "RATE_SUM_VAT")
    private String rateSumVat;
    @DatabaseField(columnName = "RATE_SUM_GROSS")
    private String rateSumGross;
    @DatabaseField(columnName = "PRICE_TO_PAY")
    private String priceToPay;
    @DatabaseField(columnName = "PRICE_TO_PAY_IN_WORDS")
    private String priceToPayInWords;

    @DatabaseField(columnName = "PAYMENT_METHOD_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private PaymentMethod paymentMethod;
    @DatabaseField(columnName = "SPLIT_PAYMENT")
    private boolean splitPayment;
    @DatabaseField(columnName = "PAYMENT_DATE")
    private Date paymentDate;

    @DatabaseField(columnName = "COMMENT")
    private String comment;

    @DatabaseField(columnName = "ISSUED")
    private String issued;
    @DatabaseField(columnName = "RELATED_DOC")
    private String relatedDoc;
    @DatabaseField(columnName = "COST")
    private String cost;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectDoc getProjectDoc() {
        return projectDoc;
    }

    public void setProjectDoc(ProjectDoc projectDoc) {
        this.projectDoc = projectDoc;
    }

    public String getNameBus() {
        return nameBus;
    }

    public void setNameBus(String nameBus) {
        this.nameBus = nameBus;
    }

    public String getTownBus() {
        return townBus;
    }

    public void setTownBus(String townBus) {
        this.townBus = townBus;
    }

    public String getZc1Bus() {
        return zc1Bus;
    }

    public void setZc1Bus(String zc1Bus) {
        this.zc1Bus = zc1Bus;
    }

    public String getZc2Bus() {
        return zc2Bus;
    }

    public void setZc2Bus(String zc2Bus) {
        this.zc2Bus = zc2Bus;
    }

    public String getStreetBus() {
        return streetBus;
    }

    public void setStreetBus(String streetBus) {
        this.streetBus = streetBus;
    }

    public String getAddressBus() {
        return addressBus;
    }

    public void setAddressBus(String addressBus) {
        this.addressBus = addressBus;
    }

    public String getNipBus() {
        return nipBus;
    }

    public void setNipBus(String nipBus) {
        this.nipBus = nipBus;
    }

    public String getPhoneBus() {
        return phoneBus;
    }

    public void setPhoneBus(String phoneBus) {
        this.phoneBus = phoneBus;
    }

    public String getBank1Bus() {
        return bank1Bus;
    }

    public void setBank1Bus(String bank1Bus) {
        this.bank1Bus = bank1Bus;
    }

    public String getBank2Bus() {
        return bank2Bus;
    }

    public void setBank2Bus(String bank2Bus) {
        this.bank2Bus = bank2Bus;
    }

    public String getBank3Bus() {
        return bank3Bus;
    }

    public void setBank3Bus(String bank3Bus) {
        this.bank3Bus = bank3Bus;
    }

    public String getBank4Bus() {
        return bank4Bus;
    }

    public void setBank4Bus(String bank4Bus) {
        this.bank4Bus = bank4Bus;
    }

    public String getBank5Bus() {
        return bank5Bus;
    }

    public void setBank5Bus(String bank5Bus) {
        this.bank5Bus = bank5Bus;
    }

    public String getBank6Bus() {
        return bank6Bus;
    }

    public void setBank6Bus(String bank6Bus) {
        this.bank6Bus = bank6Bus;
    }

    public String getBank7Bus() {
        return bank7Bus;
    }

    public void setBank7Bus(String bank7Bus) {
        this.bank7Bus = bank7Bus;
    }

    public String getNameCon() {
        return nameCon;
    }

    public void setNameCon(String nameCon) {
        this.nameCon = nameCon;
    }

    public String getTownCon() {
        return townCon;
    }

    public void setTownCon(String townCon) {
        this.townCon = townCon;
    }

    public String getZc1Con() {
        return zc1Con;
    }

    public void setZc1Con(String zc1Con) {
        this.zc1Con = zc1Con;
    }

    public String getZc2Con() {
        return zc2Con;
    }

    public void setZc2Con(String zc2Con) {
        this.zc2Con = zc2Con;
    }

    public String getStreetCon() {
        return streetCon;
    }

    public void setStreetCon(String streetCon) {
        this.streetCon = streetCon;
    }

    public String getAddressCon() {
        return addressCon;
    }

    public void setAddressCon(String addressCon) {
        this.addressCon = addressCon;
    }

    public String getNipCon() {
        return nipCon;
    }

    public void setNipCon(String nipCon) {
        this.nipCon = nipCon;
    }

    public String getPeselCon() {
        return peselCon;
    }

    public void setPeselCon(String peselCon) {
        this.peselCon = peselCon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberToPrint() {
        return numberToPrint;
    }

    public void setNumberToPrint(String numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    public String getAdditionalDoc() {
        return additionalDoc;
    }

    public void setAdditionalDoc(String additionalDoc) {
        this.additionalDoc = additionalDoc;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public ArrayList<ListProductsToAddDoc> getListProductToAddDocs() {
        return listProductToAddDocs;
    }

    public void setListProductToAddDocs(ArrayList<ListProductsToAddDoc> listProductToAddDocs) {
        this.listProductToAddDocs = listProductToAddDocs;
    }

    public ArrayList<RateProductsToAddDoc> getListRateProductsToAddDocs() {
        return listRateProductsToAddDocs;
    }

    public void setListRateProductsToAddDocs(ArrayList<RateProductsToAddDoc> listRateProductsToAddDocs) {
        this.listRateProductsToAddDocs = listRateProductsToAddDocs;
    }

    public String getRateSumNet() {
        return rateSumNet;
    }

    public void setRateSumNet(String rateSumNet) {
        this.rateSumNet = rateSumNet;
    }

    public String getRateSumVat() {
        return rateSumVat;
    }

    public void setRateSumVat(String rateSumVat) {
        this.rateSumVat = rateSumVat;
    }

    public String getRateSumGross() {
        return rateSumGross;
    }

    public void setRateSumGross(String rateSumGross) {
        this.rateSumGross = rateSumGross;
    }

    public String getPriceToPay() {
        return priceToPay;
    }

    public void setPriceToPay(String priceToPay) {
        this.priceToPay = priceToPay;
    }

    public String getPriceToPayInWords() {
        return priceToPayInWords;
    }

    public void setPriceToPayInWords(String priceToPayInWords) {
        this.priceToPayInWords = priceToPayInWords;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSplitPayment() {
        return splitPayment;
    }

    public void setSplitPayment(boolean splitPayment) {
        this.splitPayment = splitPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getRelatedDoc() {
        return relatedDoc;
    }

    public void setRelatedDoc(String relatedDoc) {
        this.relatedDoc = relatedDoc;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
