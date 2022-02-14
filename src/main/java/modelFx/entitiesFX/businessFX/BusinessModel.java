package modelFx.entitiesFX.businessFX;

import database.dao.EntitiesDao;
import database.dbUtils.DbManager;
import database.models.entitesModel.Business;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import utils.converters.BusinessConverter;
import utils.exceptions.ApplicationException;

public class BusinessModel {
    private final ObjectProperty<BusinessFX> businessFXObjectProperty = new SimpleObjectProperty<>(new BusinessFX());

    public void init() throws ApplicationException {
        EntitiesDao entitiesDao = new EntitiesDao();
        Business business = entitiesDao.findById(Business.class, 1);
        this.businessFXObjectProperty.set(BusinessConverter.convertToBusinessFX(business));
    }

    public BooleanBinding whatIsHaveBusiness(){
        return new When(this.getBusinessFXObjectProperty().nameProperty().isNull().or(this.getBusinessFXObjectProperty().nameProperty().isEmpty())
                .and(this.getBusinessFXObjectProperty().nipProperty().isNull().or(this.getBusinessFXObjectProperty().nipProperty().isEmpty())))
                .then(true).otherwise(false);
    }

    public void saveBusinessInDataBase() throws ApplicationException {
        EntitiesDao entitiesDao = new EntitiesDao();
        entitiesDao.createOrUpdate(BusinessConverter.convertToBusiness(getBusinessFXObjectProperty()));
        DbManager.closeConnectionSource();
        this.init();
    }

    public BusinessFX getBusinessFXObjectProperty() {
        return businessFXObjectProperty.get();
    }
}
