package modelFx.docsFX.saleDocFX;

import controllers.saleDocControllers.SaleDocController;
import database.dao.*;
import database.models.Docs;
import database.models.saleDocModel.Receipt;
import database.models.saleDocModel.InvoiceBuy;
import database.models.saleDocModel.InvoiceSale;
import database.models.supportTablesModel.PaymentMethod;
import database.models.supportTablesModel.typeDocModel.TypeSaleDoc;
import modelFx.docsFX.DocsFX;
import modelFx.docsFX.DocsModel;
import modelFx.supportsModelFX.TypeDocFX;
import utils.converters.SaleDocConverter;
import utils.converters.convertersToSupportTables.TypeDocConverter;
import utils.exceptions.ApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SaleDocModel extends DocsModel {
    private void initInvoiceSaleList() throws ApplicationException {
        DocsDao DocsDao = new DocsDao();
        List<InvoiceSale> invoiceSaleList = DocsDao.queryForAll(InvoiceSale.class);
        getDocsFXObservableList().clear();
        invoiceSaleList.forEach(invoiceSale -> getDocsFXObservableList().add(SaleDocConverter.convertToSalesDocFX(invoiceSale)));
    }

    private void initInvoiceBuyList() throws ApplicationException {
        DocsDao DocsDao = new DocsDao();
        List<InvoiceBuy> invoiceBuyList = DocsDao.queryForAll(InvoiceBuy.class);
        getDocsFXObservableList().clear();
        invoiceBuyList.forEach(invoiceBuy -> getDocsFXObservableList().add(SaleDocConverter.convertToSalesDocFX(invoiceBuy)));
    }

    private void initReceiptList() throws ApplicationException {
        DocsDao DocsDao = new DocsDao();
        List<Receipt> receiptsList = DocsDao.queryForAll(Receipt.class);
        getDocsFXObservableList().clear();
        receiptsList.forEach(receipt -> getDocsFXObservableList().add(SaleDocConverter.convertToSalesDocFX(receipt)));
    }

    protected void initTypeSalesDocList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<TypeSaleDoc> typeSaleDocList = supportDao.queryForAll(TypeSaleDoc.class);
        getTypeDocFXObservableList().clear();
        typeSaleDocList.forEach(typeSaleDoc -> {
            TypeDocFX typeDocFX = TypeDocConverter.convertToTypeDocFX(typeSaleDoc);
            getTypeDocFXObservableList().add(typeDocFX);
        });
    }

    public void init() throws ApplicationException {
        initBusiness();
        switch (SaleDocController.getFlagWhatOpen()){
            case 0:
                initInvoiceSaleList();
                break;
            case 1:
                initInvoiceBuyList();
                break;
            case 2:
                initReceiptList();
                break;
        }
        initTypeSalesDocList();
        initPaymentMethodList();
    }

    public void saveSalesDocInDataBase() throws ApplicationException {
        Docs docs = null;
        switch (SaleDocController.getFlagWhatOpen()){
            case 0:
                docs = SaleDocConverter.convertToSalesDoc(0, SaleDocModel.getDocsFXObjectProperty(this));
                break;
            case 1:
                docs = SaleDocConverter.convertToSalesDoc(1, SaleDocModel.getDocsFXObjectProperty(this));
                break;
            case 2:
                docs = SaleDocConverter.convertToSalesDoc(2, SaleDocModel.getDocsFXObjectProperty(this));
                break;
        }

        SupportDao supportDao = new SupportDao();
        PaymentMethod paymentMethod = supportDao.findById(PaymentMethod.class, SaleDocModel.getDocsFXObjectProperty(this).getPaymentMethodFX().getId());
        assert docs != null;
        docs.setPaymentMethod(paymentMethod);

        DocsDao docsDao = new DocsDao();
        docsDao.createOrUpdate(docs);
    }

    public void deleteSalesDocInDataBase(DocsFX item) throws ApplicationException {
        DocsDao docsDao = new DocsDao();
        switch (SaleDocController.getFlagWhatOpen()){
            case 0:
                docsDao.deleteById(InvoiceSale.class, item.getId());
                break;
            case 1:
                docsDao.deleteById(InvoiceBuy.class, item.getId());
                break;
            case 2:
                docsDao.deleteById(Receipt.class, item.getId());
                break;
        }
        this.init();
    }
}