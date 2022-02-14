package database.dbUtils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import database.models.entitesModel.Business;
import database.models.*;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.models.entitesModel.Contractor;
import database.models.saleDocModel.InvoiceBuy;
import database.models.saleDocModel.InvoiceSale;
import database.models.saleDocModel.Receipt;
import database.models.storeDocModel.ReceivingDoc;
import database.models.storeDocModel.ReleaseDoc;
import database.models.supportTablesModel.*;
import database.models.supportTablesModel.typeDocModel.TypeSaleDoc;
import database.models.supportTablesModel.typeDocModel.TypeStoreDoc;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager{

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./Dokumenty - system fakturowania/database/system_fakturowania";
    private static final String USER = "admin";
    private static final String PASS = "";

    private static ConnectionSource connectionSource;

    public static void initDatabase(boolean dropTable){
        createConnectionSource();
        if(dropTable)
            dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD,USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource!=null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Contractor.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);
            TableUtils.createTableIfNotExists(connectionSource, TypeProducts.class);
            TableUtils.createTableIfNotExists(connectionSource, UnitsProducts.class);
            TableUtils.createTableIfNotExists(connectionSource, VatProducts.class);
            TableUtils.createTableIfNotExists(connectionSource, GtuProducts.class);
            TableUtils.createTableIfNotExists(connectionSource, InvoiceSale.class);
            TableUtils.createTableIfNotExists(connectionSource, InvoiceBuy.class);
            TableUtils.createTableIfNotExists(connectionSource, Receipt.class);
            TableUtils.createTableIfNotExists(connectionSource, TypeSaleDoc.class);
            TableUtils.createTableIfNotExists(connectionSource, PaymentMethod.class);
            TableUtils.createTableIfNotExists(connectionSource, Business.class);
            TableUtils.createTableIfNotExists(connectionSource, TypeStoreDoc.class);
            TableUtils.createTableIfNotExists(connectionSource, ReleaseDoc.class);
            TableUtils.createTableIfNotExists(connectionSource, ReceivingDoc.class);
            FillSupportTables.FillUtilsTables();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private  static  void  dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Contractor.class, true);
            TableUtils.dropTable(connectionSource, Product.class, true);
            TableUtils.dropTable(connectionSource, TypeProducts.class, true);
            TableUtils.dropTable(connectionSource, UnitsProducts.class, true);
            TableUtils.dropTable(connectionSource, VatProducts.class, true);
            TableUtils.dropTable(connectionSource, GtuProducts.class, true);
            TableUtils.dropTable(connectionSource, InvoiceSale.class, true);
            TableUtils.dropTable(connectionSource, InvoiceBuy.class, true);
            TableUtils.dropTable(connectionSource, Receipt.class, true);
            TableUtils.dropTable(connectionSource, TypeSaleDoc.class, true);
            TableUtils.dropTable(connectionSource, PaymentMethod.class, true);
            TableUtils.dropTable(connectionSource, Business.class, true);
            TableUtils.dropTable(connectionSource, TypeStoreDoc.class, true);
            TableUtils.dropTable(connectionSource, ReleaseDoc.class, true);
            TableUtils.dropTable(connectionSource, ReceivingDoc.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}