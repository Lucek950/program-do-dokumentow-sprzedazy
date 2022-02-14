
package modelFx.entitiesFX.contractorFX;

import database.dao.EntitiesDao;
import database.dbUtils.DbManager;
import database.models.entitesModel.Contractor;
import utils.converters.ContractorConverter;
import utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.List;

public class ContractorModel{
    private final ObjectProperty<ContractorFX> contractorFXObjectProperty = new SimpleObjectProperty<>(new ContractorFX());
    private final ObservableList<ContractorFX> contractorFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        EntitiesDao entitiesDao = new EntitiesDao();
        List<Contractor> contractorList = entitiesDao.queryForAll(Contractor.class);
        this.contractorFXObservableList.clear();
        contractorList.forEach(contractor -> this.contractorFXObservableList.add(ContractorConverter.convertToContractorFX(contractor)));
    }

    public void saveContractorInDataBase() throws ApplicationException {
        saveOrUpdate(this.getContractorFXObjectProperty());
    }

    private void saveOrUpdate(ContractorFX contractorFXObjectPropertyEdit) throws ApplicationException {
        EntitiesDao entitiesDao = new EntitiesDao();
        entitiesDao.createOrUpdate(ContractorConverter.convertToContractor(contractorFXObjectPropertyEdit));
        DbManager.closeConnectionSource();
        this.init();
    }

    public void deleteContractorInDataBase(ContractorFX item) throws ApplicationException{
        EntitiesDao entitiesDao = new EntitiesDao();
        entitiesDao.deleteById(Contractor.class, item.getId());
        this.init();
    }

    public ContractorFX getContractorFXObjectProperty() {
        return contractorFXObjectProperty.get();
    }

    public void setContractorFXObjectProperty(ContractorFX contractorFXObjectProperty) {
        this.contractorFXObjectProperty.set(contractorFXObjectProperty);
    }

    public ObservableList<ContractorFX> getContractorFXObservableList() {
        return contractorFXObservableList;
    }

    //filtrowanie
    private final FilteredList<ContractorFX> filteredData = new FilteredList<>(getContractorFXObservableList(), p -> true);

    public FilteredList<ContractorFX> getFilteredData() {
        return filteredData;
    }
}