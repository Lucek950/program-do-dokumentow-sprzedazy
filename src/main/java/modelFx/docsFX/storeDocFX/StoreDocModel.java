package modelFx.docsFX.storeDocFX;

import controllers.warehouseControllers.WarehouseController;
import database.dao.DocsDao;
import database.dao.SupportDao;
import database.models.Docs;
import database.models.storeDocModel.ReceivingDoc;
import database.models.storeDocModel.ReleaseDoc;
import database.models.supportTablesModel.typeDocModel.TypeStoreDoc;
import modelFx.docsFX.DocsFX;
import modelFx.docsFX.DocsModel;
import modelFx.supportsModelFX.TypeDocFX;
import utils.converters.StoreDocConverter;
import utils.converters.convertersToSupportTables.TypeDocConverter;
import utils.exceptions.ApplicationException;

import java.util.List;

public class StoreDocModel extends DocsModel {

    private int flag = 0;
    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void initReceivingDocList() throws ApplicationException {
        DocsDao DocsDao = new DocsDao();
        List<ReceivingDoc> receivingDocList = DocsDao.queryForAll(ReceivingDoc.class);
        getDocsFXObservableList().clear();
        receivingDocList.forEach(receivingDoc -> getDocsFXObservableList().add(StoreDocConverter.convertToStorageDocFX(receivingDoc)));
    }

    public void initReleaseDocList() throws ApplicationException {
        DocsDao DocsDao = new DocsDao();
        List<ReleaseDoc> releaseDocList = DocsDao.queryForAll(ReleaseDoc.class);
        getDocsFXObservableList().clear();
        releaseDocList.forEach(releaseDoc -> getDocsFXObservableList().add(StoreDocConverter.convertToStorageDocFX(releaseDoc)));
    }

    private void initTypeStorageList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<TypeStoreDoc> typeStoreDocList = supportDao.queryForAll(TypeStoreDoc.class);
        getTypeDocFXObservableList().clear();
        typeStoreDocList.forEach(typeStoreDoc -> {
            TypeDocFX typeDocFX = TypeDocConverter.convertToTypeDocFX(typeStoreDoc);
            getTypeDocFXObservableList().add(typeDocFX);
        });
    }

    public void init() throws ApplicationException {
        initBusiness();
        if(this.flag == 0)
            this.flag = WarehouseController.getFlagWhatOpen();

        switch (this.flag){
            case 0:
                initReceivingDocList();
                break;
            case 1:
                initReleaseDocList();
                break;
        }
        initTypeStorageList();
    }

    public void saveDocsInDataBase() throws ApplicationException {
        Docs docs = null;
        if(this.flag == 0)
            this.flag = WarehouseController.getFlagWhatOpen();

        switch (this.flag){
            case 0:
                docs = StoreDocConverter.convertToStorageDoc(0, StoreDocModel.getDocsFXObjectProperty(this));
                break;
            case 1:
                docs = StoreDocConverter.convertToStorageDoc(1, StoreDocModel.getDocsFXObjectProperty(this));
                break;
        }

        DocsDao docsDao = new DocsDao();
        assert docs != null;
        docsDao.createOrUpdate(docs);
    }

    public void deleteDocsInDataBase(DocsFX item) throws ApplicationException {
        DocsDao docsDao = new DocsDao();
        if(this.flag == 0)
            this.flag = WarehouseController.getFlagWhatOpen();

        switch (this.flag){
            case 0:
                docsDao.deleteById(ReceivingDoc.class, item.getId());
                break;
            case 1:
                docsDao.deleteById(ReleaseDoc.class, item.getId());
                break;
        }
        this.init();
    }
}
