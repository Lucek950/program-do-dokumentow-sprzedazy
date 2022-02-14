package modelFx.productFX;

import database.dao.ProductDao;
import database.dao.SupportDao;
import database.models.*;
import database.models.supportTablesModel.GtuProducts;
import database.models.supportTablesModel.TypeProducts;
import database.models.supportTablesModel.UnitsProducts;
import database.models.supportTablesModel.VatProducts;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.supportsModelFX.GtuProductsFX;
import modelFx.supportsModelFX.UnitsProductsFX;
import modelFx.supportsModelFX.TypeProductsFX;
import modelFx.supportsModelFX.VatProductsFX;
import utils.converters.ProductConverter;
import utils.converters.convertersToSupportTables.GtuProductsConverter;
import utils.converters.convertersToSupportTables.UnitsProductsConverter;
import utils.converters.convertersToSupportTables.TypeProductsConverter;
import utils.converters.convertersToSupportTables.VatProductsConverter;
import utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.List;

public class ProductModel {
    private final ObjectProperty<ProductFX> productFXObjectProperty = new SimpleObjectProperty<>(new ProductFX());

    public ProductFX getProductFXObjectProperty() {
        return productFXObjectProperty.get();
    }

    public void setProductFXObjectProperty(ProductFX productFXObjectProperty) {
        this.productFXObjectProperty.set(productFXObjectProperty);
    }
    private final ObservableList<ProductFX> productFXObservableList = FXCollections.observableArrayList();
    
    public ObservableList<ProductFX> getProductFXObservableList() {
        return productFXObservableList;
    }

    private void initProductList() throws ApplicationException{
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.queryForAll(Product.class);
        this.productFXObservableList.clear();
        productList.forEach(product -> this.productFXObservableList.add(ProductConverter.convertToProductFX(product)));
    }

    private final ObservableList<TypeProductsFX> typeProductsFXObservableList = FXCollections.observableArrayList();

    public ObservableList<TypeProductsFX> getTypeProductsFXObservableList() {
        return typeProductsFXObservableList;
    }

    private void initTypeProductsList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<TypeProducts> typeProductsList = supportDao.queryForAll(TypeProducts.class);
        typeProductsFXObservableList.clear();
        typeProductsList.forEach(typeProduct -> {
            TypeProductsFX typeProductsFX = TypeProductsConverter.convertToTypeProductsFX(typeProduct);
            typeProductsFXObservableList.add(typeProductsFX);
        });
    }

    private final ObservableList<UnitsProductsFX> unitsProductsFXObservableList = FXCollections.observableArrayList();

    public ObservableList<UnitsProductsFX> getUnitsProductsFXObservableList() {
        return unitsProductsFXObservableList;
    }

    public void initUnitProductList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<UnitsProducts> unitsProductsList = supportDao.queryForAll(UnitsProducts.class);
        unitsProductsFXObservableList.clear();
        unitsProductsList.forEach(unitProduct -> {
            UnitsProductsFX unitsProductsFX = UnitsProductsConverter.convertToUnitsProductsFX(unitProduct);
            unitsProductsFXObservableList.add(unitsProductsFX);
        });
    }

    private final ObjectProperty<UnitsProductsFX> unitsProductsFXObjectProperty = new SimpleObjectProperty<>(new UnitsProductsFX());

    public UnitsProductsFX getUnitsProductsFXObjectProperty() {
        return unitsProductsFXObjectProperty.get();
    }

    public void saveUnitsProductInDataBase() throws ApplicationException {
        UnitsProducts unitsProducts = UnitsProductsConverter.convertToUnitsProducts(this.getUnitsProductsFXObjectProperty());
        SupportDao supportDao = new SupportDao();
        supportDao.createOrUpdate(unitsProducts);
    }

    private final ObservableList<VatProductsFX> vatProductsFXObservableList = FXCollections.observableArrayList();

    public ObservableList<VatProductsFX> getVatProductsFXObservableList() {
        return vatProductsFXObservableList;
    }

    private void initVatProductList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<VatProducts> vatProductsList = supportDao.queryForAll(VatProducts.class);
        vatProductsFXObservableList.clear();
        vatProductsList.forEach(vatProduct -> {
            VatProductsFX vatProductsFX = VatProductsConverter.convertToVatProductsFX(vatProduct);
            vatProductsFXObservableList.add(vatProductsFX);
        });
    }

    private final ObservableList<GtuProductsFX> gtuProductsFXObservableList = FXCollections.observableArrayList();

    public ObservableList<GtuProductsFX> getGtuProductsFXObservableList() {
        return gtuProductsFXObservableList;
    }

    private void initGtuProductList() throws ApplicationException {
        SupportDao supportDao = new SupportDao();
        List<GtuProducts> gtuProductsList = supportDao.queryForAll(GtuProducts.class);
        gtuProductsFXObservableList.clear();
        gtuProductsList.forEach(gtuProduct -> {
            GtuProductsFX gtuProductsFX = GtuProductsConverter.convertToGtuProductsFX(gtuProduct);
            gtuProductsFXObservableList.add(gtuProductsFX);
        });
    }

    public void init() throws ApplicationException {
        initProductList();
        initTypeProductsList();
        initUnitProductList();
        initVatProductList();
        initGtuProductList();
    }

    public void saveProductInDataBase() throws ApplicationException {
        Product product = ProductConverter.convertToProduct(this.getProductFXObjectProperty());

        SupportDao typeProductsDao = new SupportDao();
        TypeProducts typeProducts = typeProductsDao.findById(TypeProducts.class, this.getProductFXObjectProperty().getTypeProductFX().getId());
        product.setTypeProduct(typeProducts);

        SupportDao unitsProductsDao = new SupportDao();
        UnitsProducts unitsProducts = unitsProductsDao.findById(UnitsProducts.class, this.getProductFXObjectProperty().getUnitsProductFX().getId());
        product.setUnitsProduct(unitsProducts);

        SupportDao vatProductsDao = new SupportDao();
        VatProducts vatProducts = vatProductsDao.findById(VatProducts.class, this.getProductFXObjectProperty().getSaleVatProductFX().getId());
        VatProducts buyVatProducts = vatProductsDao.findById(VatProducts.class, this.getProductFXObjectProperty().getBuyVatProductFX().getId());
        product.setVatProduct(vatProducts);
        product.setBuyVatProduct(buyVatProducts);

        SupportDao supportDao = new SupportDao();
        GtuProducts gtuProducts = supportDao.findById(GtuProducts.class, this.getProductFXObjectProperty().getGtuProductFX().getId());
        product.setGtuProduct(gtuProducts);

        ProductDao productDao = new ProductDao();
        productDao.createOrUpdate(product);
    }

    public void deleteProductInDataBase(ProductFX item) throws ApplicationException {
        ProductDao productDao = new ProductDao();
        productDao.deleteById(Product.class, item.getId());
        this.init();
    }

    //filtrowanie
    private final FilteredList<ProductFX> filteredData = new FilteredList<>(getProductFXObservableList(), p -> true);

    public FilteredList<ProductFX> getFilteredData() {
        return filteredData;
    }

    public ListProductsToAddDoc getProductToListSale(ProductFX productFX, int quantity, int discount, boolean itsBuyProduct) throws ApplicationException {
        return new ListProductsToAddDoc(productFX, quantity, discount, itsBuyProduct);
    }
}
