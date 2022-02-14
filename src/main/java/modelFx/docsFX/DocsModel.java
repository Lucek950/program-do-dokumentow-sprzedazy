package modelFx.docsFX;

import controllers.saleDocControllers.SaleDocController;
import controllers.saleDocControllers.SaleDocTabController;
import database.dao.EntitiesDao;
import database.dao.SupportDao;
import database.dbUtils.FillSupportTables;
import database.models.entitesModel.Business;
import database.models.supportTablesModel.PaymentMethod;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import modelFx.entitiesFX.businessFX.BusinessFX;
import modelFx.entitiesFX.contractorFX.ContractorFX;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;
import modelFx.supportsModelFX.PaymentMethodFX;
import modelFx.supportsModelFX.TypeDocFX;
import utils.converters.BusinessConverter;
import utils.converters.convertersToSupportTables.PaymentMethodConverter;
import utils.exceptions.ApplicationException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DocsModel {
    private final ObjectProperty<DocsFX> docsFXObjectProperty = new SimpleObjectProperty<>(new DocsFX());

    public static DocsFX getDocsFXObjectProperty(DocsModel docsModel) {
        return docsModel.docsFXObjectProperty.get();
    }

    public void setDocsFXObjectProperty(DocsFX docsFXObjectProperty) {
        this.docsFXObjectProperty.set(docsFXObjectProperty);
    }

    private final ObservableList<DocsFX> docsFXObservableList = FXCollections.observableArrayList();

    public ObservableList<DocsFX> getDocsFXObservableList() {
        return docsFXObservableList;
    }

    private final ObservableList<TypeDocFX> typeDocFXObservableList = FXCollections.observableArrayList();

    public ObservableList<TypeDocFX> getTypeDocFXObservableList() {
        return typeDocFXObservableList;
    }

    private final ObservableList<PaymentMethodFX> paymentMethodFXObservableList = FXCollections.observableArrayList();

    public ObservableList<PaymentMethodFX> getPaymentMethodFXObservableList() {
        return paymentMethodFXObservableList;
    }

    public void initPaymentMethodList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<PaymentMethod> paymentMethodList = supportDao.queryForAll(PaymentMethod.class);
        paymentMethodFXObservableList.clear();
        paymentMethodList.forEach(unitProduct -> {
            PaymentMethodFX paymentMethodFX = PaymentMethodConverter.convertToPaymentMethodFX(unitProduct);
            paymentMethodFXObservableList.add(paymentMethodFX);
        });
    }

    private final ObjectProperty<PaymentMethodFX> paymentMethodFXObjectProperty = new SimpleObjectProperty<>(new PaymentMethodFX());

    public PaymentMethodFX getPaymentMethodFXObjectProperty() {
        return paymentMethodFXObjectProperty.get();
    }

    public void savePaymentMethodInDataBase() throws ApplicationException {
        PaymentMethod paymentMethod = PaymentMethodConverter.convertToPaymentMethod(this.getPaymentMethodFXObjectProperty());
        SupportDao supportDao = new SupportDao();
        supportDao.createOrUpdate(paymentMethod);
    }

    private final ObjectProperty<BusinessFX> businessFXObjectProperty = new SimpleObjectProperty<>(new BusinessFX());

    public BusinessFX getBusinessFXObjectProperty() {
        return businessFXObjectProperty.get();
    }

    protected void initBusiness() throws ApplicationException {
        EntitiesDao entitiesDao = new EntitiesDao();
        Business business = entitiesDao.findById(Business.class, 1);
        this.businessFXObjectProperty.set(BusinessConverter.convertToBusinessFX(business));
    }

    private static final ObjectProperty<ContractorFX> contractorFXObjectProperty = new SimpleObjectProperty<>(new ContractorFX());
    public static ObjectProperty<ContractorFX> contractorFXObjectPropertyProperty() {
        return contractorFXObjectProperty;
    }
    public static void setContractorFXObjectProperty(ContractorFX contractorFXObjectProperty) {
        DocsModel.contractorFXObjectProperty.set(contractorFXObjectProperty);
    }

    private final FilteredList<DocsFX> filteredData = new FilteredList<>(getDocsFXObservableList(), p -> true);

    public FilteredList<DocsFX> getFilteredData() {
        return filteredData;
    }

    public void addProductToList(ListProductsToAddDoc listProductsToAddDoc){
        int lp = getDocsFXObjectProperty(this).listProductToAddDocsProperty().get().size() + 1;
        getDocsFXObjectProperty(this).listProductToAddDocsProperty().get().add(new ListProductsToAddDoc(listProductsToAddDoc, lp, FillSupportTables.getTypeSaleDocList().get(SaleDocController.getFlagWhatOpen()).getProjectDocs().get(SaleDocTabController.getPositionSelected()).getShortcut()));
        totalsVolGainFromProductTable();
        fillRatesTable();
    }

    public void setLpWhenDeleteElementFromList(){
        ObservableList<ListProductsToAddDoc> tmpList = FXCollections.observableArrayList();
        for(int i = 0; i < getDocsFXObjectProperty(this).getListProductToAddDocs().size(); i++){
            ListProductsToAddDoc listProductsToAddDoc = new ListProductsToAddDoc(getDocsFXObjectProperty(this).getListProductToAddDocs().get(i), i + 1);
            tmpList.add(listProductsToAddDoc);
        }
        getDocsFXObjectProperty(this).getListProductToAddDocs().clear();
        getDocsFXObjectProperty(this).getListProductToAddDocs().setAll(tmpList);
    }

    private final DoubleProperty totalVolGrossProperty = new SimpleDoubleProperty();
    private final DoubleProperty totalCostProperty = new SimpleDoubleProperty();
    public void totalsVolGainFromProductTable(){
        Double totalVolGross = 0.0;
        totalVolGross = getDocsFXObjectProperty(this).getListProductToAddDocs().stream().map(ListProductsToAddDoc::getVolGross).reduce(totalVolGross, Double::sum);
        this.totalVolGrossProperty.set(totalVolGross);
        Double totalCost = 0.0;
        totalCost = getDocsFXObjectProperty(this).getListProductToAddDocs().stream().map(ListProductsToAddDoc::getCost).reduce(totalCost, Double::sum);
        this.totalCostProperty.set(totalCost);
    }

    public ObservableList<RateProductsToAddDoc> getListRateProductsToDocs() {
        return listRateProductsToAddDoc;
    }

    private final ObservableList<RateProductsToAddDoc> listRateProductsToAddDoc = FXCollections.observableArrayList();
    public void fillRatesTable(){
        listRateProductsToAddDoc.clear();
        for(int i = 0; i < getDocsFXObjectProperty(this).getListProductToAddDocs().size(); i++) {
            RateProductsToAddDoc rateProductsToAddDoc = new RateProductsToAddDoc(getDocsFXObjectProperty(this).getListProductToAddDocs().get(i));

            AtomicInteger position = new AtomicInteger();
            AtomicBoolean flag = new AtomicBoolean(false);
            listRateProductsToAddDoc.forEach(item -> {
                if(item.getVat().equals(rateProductsToAddDoc.getVat())) {
                    flag.set(true);
                    position.set(0);
                }
                position.getAndIncrement();
            });
            if (flag.get())
                listRateProductsToAddDoc.set(listRateProductsToAddDoc.size() - position.get(),
                        new RateProductsToAddDoc(listRateProductsToAddDoc.get(listRateProductsToAddDoc.size() - position.get()), rateProductsToAddDoc.getVolumeNet(), rateProductsToAddDoc.getSumVat()));
            else
                listRateProductsToAddDoc.add(rateProductsToAddDoc);
            totalsFromColsInRatesTable();
        }
    }

    private final DoubleProperty totalRateVolNetProperty = new SimpleDoubleProperty();
    private final DoubleProperty totalRateSumVatProperty = new SimpleDoubleProperty();
    private final DoubleProperty totalRateVolGainProperty = new SimpleDoubleProperty();

    public void totalsFromColsInRatesTable(){
        Double totalRateVolNet = 0.0;
        totalRateVolNet = this.listRateProductsToAddDoc.stream().map(RateProductsToAddDoc::getVolumeNet).reduce(totalRateVolNet, Double::sum);
        totalRateVolNetProperty.set(totalRateVolNet);
        Double totalRateSumVat = 0.0;
        totalRateSumVat = this.listRateProductsToAddDoc.stream().map(RateProductsToAddDoc::getSumVat).reduce(totalRateSumVat, Double::sum);
        totalRateSumVatProperty.set(totalRateSumVat);
        Double totalRateVolGain = 0.0;
        totalRateVolGain = this.listRateProductsToAddDoc.stream().map(RateProductsToAddDoc::getVolumeGain).reduce(totalRateVolGain, Double::sum);
        totalRateVolGainProperty.set(totalRateVolGain);
    }

    public DoubleProperty totalVolGrossPropertyProperty() {
        return totalVolGrossProperty;
    }

    public DoubleProperty totalCostPropertyProperty() {
        return totalCostProperty;
    }

    public DoubleProperty totalRateVolNetPropertyProperty() {
        return totalRateVolNetProperty;
    }

    public DoubleProperty totalRateSumVatPropertyProperty() {
        return totalRateSumVatProperty;
    }

    public DoubleProperty totalRateVolGrossPropertyProperty() {
        return totalRateVolGainProperty;
    }

    public String autoNumber(String typeDocNumber, String dataNumber){
        int counter = 1;
        String tmpString = typeDocNumber + counter + dataNumber;
        String result = counter + dataNumber;
        while(checkIfDocNumberIsUnique(tmpString))
            for(int i = 0; i < getDocsFXObservableList().size(); i++){
                if(getDocsFXObservableList().get(i).getNumber().equals(tmpString)) {
                    counter++;
                    tmpString = typeDocNumber + counter + dataNumber;
                    result = counter + dataNumber;
                }
            }
        return result;
    }

    public boolean checkIfDocNumberIsUnique(String number){
        AtomicBoolean flag = new AtomicBoolean(false);
        getDocsFXObservableList().forEach(item ->{
            if(item.getNumber().equals(number))
                flag.set(true);
        });
        return flag.get();
    }
}
