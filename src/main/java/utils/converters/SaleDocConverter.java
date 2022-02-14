package utils.converters;

import database.models.Docs;
import database.models.saleDocModel.InvoiceBuy;
import database.models.saleDocModel.Receipt;
import database.models.saleDocModel.InvoiceSale;
import modelFx.docsFX.DocsFX;
import utils.UtilsProject;
import utils.converters.convertersToSupportTables.PaymentMethodConverter;

public class SaleDocConverter {
    public static Docs convertToSalesDoc(int flag, DocsFX docsFX){
        Docs docs = null;
        switch (flag){
            case 0:
                docs = new InvoiceSale();
                break;
            case 1:
                docs = new InvoiceBuy();
                break;
            case 2:
                docs = new Receipt();
                break;
        }
        assert docs != null;
        docs.setId(docsFX.getId());
        docs.setProjectDoc(docsFX.getProjectDoc());

        docs.setNameBus(docsFX.getNameBus());
        docs.setTownBus(docsFX.getTownBus());
        docs.setZc1Bus(docsFX.getZc1Bus());
        docs.setZc2Bus(docsFX.getZc2Bus());
        docs.setStreetBus(docsFX.getStreetBus());
        docs.setAddressBus(docsFX.getAddressBus());
        docs.setNipBus(docsFX.getNipBus());
        docs.setPhoneBus(docsFX.getPhoneBus());
        docs.setBank1Bus(docsFX.getBank1Bus());
        docs.setBank2Bus(docsFX.getBank2Bus());
        docs.setBank3Bus(docsFX.getBank3Bus());
        docs.setBank4Bus(docsFX.getBank4Bus());
        docs.setBank5Bus(docsFX.getBank5Bus());
        docs.setBank6Bus(docsFX.getBank6Bus());
        docs.setBank7Bus(docsFX.getBank7Bus());

        docs.setNameCon(docsFX.getNameCon());
        docs.setTownCon(docsFX.getTownCon());
        docs.setZc1Con(docsFX.getZc1Con());
        docs.setZc2Con(docsFX.getZc2Con());
        docs.setStreetCon(docsFX.getStreetCon());
        docs.setAddressCon(docsFX.getAddressCon());
        docs.setNipCon(docsFX.getNipCon());
        docs.setPeselCon(docsFX.getPeselCon());

        docs.setNumber(docsFX.getNumber());
        docs.setNumberToPrint(docsFX.getNumberToPrint());
        docs.setAdditionalDoc(docsFX.getAdditionalDoc());
        docs.setIssuePlace(docsFX.getIssuePlace());
        docs.setIssueDate(UtilsProject.convertToDate(docsFX.getIssueDate()));
        docs.setExecuteDate(UtilsProject.convertToDate(docsFX.getExecuteDate()));

        docs.setListProductToAddDocs(UtilsProject.convertToArrayListProducts(docsFX.getListProductToAddDocs()));
        docs.setListRateProductsToAddDocs(UtilsProject.convertToArrayListRateProducts(docsFX.getListRateProductsToAddDocs()));
        docs.setRateSumNet(docsFX.getRateSumNet());
        docs.setRateSumVat(docsFX.getRateSumVat());
        docs.setRateSumGross(docsFX.getRateSumGross());
        docs.setPriceToPay(docsFX.getPriceToPay());
        docs.setPriceToPayInWords(docsFX.getPriceToPayInWords());

        docs.setSplitPayment(docsFX.isSplitPayment());
        docs.setPaymentDate(UtilsProject.convertToDate(docsFX.getPaymentDate()));

        docs.setComment(docsFX.getComment());

        docs.setIssued(docsFX.getIssued());

        docs.setRelatedDoc(docsFX.getRelatedDoc());
        return docs;
    }

    public static DocsFX convertToSalesDocFX(Docs docs){
        DocsFX docsFX = new DocsFX();
        docsFX.setId(docs.getId());
        docsFX.setProjectDoc(docs.getProjectDoc());

        docsFX.setNameBus(docs.getNameBus());
        docsFX.setTownBus(docs.getTownBus());
        docsFX.setZc1Bus(docs.getZc1Bus());
        docsFX.setZc2Bus(docs.getZc2Bus());
        docsFX.setStreetBus(docs.getStreetBus());
        docsFX.setAddressBus(docs.getAddressBus());
        docsFX.setNipBus(docs.getNipBus());
        docsFX.setPhoneBus(docs.getPhoneBus());
        docsFX.setBank1Bus(docs.getBank1Bus());
        docsFX.setBank2Bus(docs.getBank2Bus());
        docsFX.setBank3Bus(docs.getBank3Bus());
        docsFX.setBank4Bus(docs.getBank4Bus());
        docsFX.setBank5Bus(docs.getBank5Bus());
        docsFX.setBank6Bus(docs.getBank6Bus());
        docsFX.setBank7Bus(docs.getBank7Bus());

        docsFX.setNameCon(docs.getNameCon());
        docsFX.setTownCon(docs.getTownCon());
        docsFX.setZc1Con(docs.getZc1Con());
        docsFX.setZc2Con(docs.getZc2Con());
        docsFX.setStreetCon(docs.getStreetCon());
        docsFX.setAddressCon(docs.getAddressCon());
        docsFX.setNipCon(docs.getNipCon());
        docsFX.setPeselCon(docs.getPeselCon());

        docsFX.setNumber(docs.getNumber());
        docsFX.setNumberToPrint(docs.getNumberToPrint());
        docsFX.setAdditionalDoc(docs.getAdditionalDoc());
        docsFX.setIssuePlace(docs.getIssuePlace());
        docsFX.setIssueDate(UtilsProject.convertToLocalDate(docs.getIssueDate()));
        docsFX.setExecuteDate(UtilsProject.convertToLocalDate(docs.getExecuteDate()));

        docsFX.setListProductToAddDocs(UtilsProject.convertToObservableListProducts(docs.getListProductToAddDocs()));
        docsFX.setListRateProductsToAddDocs(UtilsProject.convertToObservableListRateProducts((docs.getListRateProductsToAddDocs())));
        docsFX.setRateSumNet(docs.getRateSumNet());
        docsFX.setRateSumVat(docs.getRateSumVat());
        docsFX.setRateSumGross(docs.getRateSumGross());
        docsFX.setPriceToPay(docs.getPriceToPay());
        docsFX.setPriceToPayInWords(docs.getPriceToPayInWords());

        docsFX.setPaymentMethodFX(PaymentMethodConverter.convertToPaymentMethodFX(docs.getPaymentMethod()));
        docsFX.setSplitPayment(docs.isSplitPayment());
        docsFX.setPaymentDate(UtilsProject.convertToLocalDate(docs.getPaymentDate()));

        docsFX.setComment(docs.getComment());

        docsFX.setIssued(docs.getIssued());

        docsFX.setRelatedDoc(docs.getRelatedDoc());
        return docsFX;
    }
}
