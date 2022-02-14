package database.dbUtils;

import database.dao.*;
import database.models.entitesModel.Business;
import database.models.supportTablesModel.*;
import database.models.supportTablesModel.typeDocModel.ProjectDoc;
import database.models.supportTablesModel.typeDocModel.TypeSaleDoc;
import database.models.supportTablesModel.typeDocModel.TypeStoreDoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.UtilsProject;
import utils.exceptions.ApplicationException;

import java.util.ArrayList;

public class FillSupportTables {
    private static final ArrayList<TypeProducts> typeList = new ArrayList<>();
    private static final ArrayList<GtuProducts> gtuList = new ArrayList<>();
    private static final ArrayList<UnitsProducts> unitsList = new ArrayList<>();
    private static final ArrayList<VatProducts> vatList = new ArrayList<>();
    private static final ArrayList<TypeSaleDoc> typeSaleDocList = new ArrayList<>();
    private static final ArrayList<TypeStoreDoc> typeStoreDocList = new ArrayList<>();
    private static final ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();

    protected static void FillUtilsTables(){
        fillTypeList();
        fillUnitsList();
        fillVatList();
        fillGtuList();
        fillTypeSaleDocList();
        fillTypeStoreDocList();
        fillPaymentMethodList();
        fillBusinessTable();
        daoCreateOrUpdate();
    }

    private static void fillBusinessTable(){
        Business business = new Business();
        EntitiesDao entitiesDao = new EntitiesDao();
        try {
            entitiesDao.createOrUpdate(business);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }

    private static void fillTypeSaleDocList() {
        ObservableList<ProjectDoc> fs = FXCollections.observableArrayList();
        fs.add(new ProjectDoc("faktura sprzeda¿y", "Faktura VAT ", "FS "));
        fs.add(new ProjectDoc("faktura sprzeda¿y bez paragonu", "Faktura VAT ", "FS "));
        fs.add(new ProjectDoc("rachunek sprzeda¿y", "Rachunek sprzeda¿y ", "RS "));

        ObservableList<ProjectDoc> fz = FXCollections.observableArrayList();
        fz.add(new ProjectDoc("faktura zakupu", "Faktura VAT ", "FZ "));
        fz.add(new ProjectDoc("rachunek zakupu", "Rachunek zakupu ", "RZ "));

        ObservableList<ProjectDoc> sd = FXCollections.observableArrayList();
        sd.add(new ProjectDoc("paragon", "Paragon ", "PA "));
        sd.add(new ProjectDoc("paragon imienny", "Paragon imienny ", "PA "));

        typeSaleDocList.add(new TypeSaleDoc(1,"Faktury sprzeda¿y ", UtilsProject.convertToArrayListInvoiceType(fs)));
        typeSaleDocList.add(new TypeSaleDoc(2,"Faktury zakupu ", UtilsProject.convertToArrayListInvoiceType(fz)));
        typeSaleDocList.add(new TypeSaleDoc(3,"Sprzeda¿ detaliczna ", UtilsProject.convertToArrayListInvoiceType(sd)));
    }

    private static void fillTypeStoreDocList() {
        ObservableList<ProjectDoc> pm = FXCollections.observableArrayList();
        pm.add(new ProjectDoc("przyjêcie zewnêtrzne", "Przyjêcie zewnêtrzne ", "PZ "));
        pm.add(new ProjectDoc("przychód wewnêtrzny", "Przychód wewnêtrzny ", "PW "));

        ObservableList<ProjectDoc> wm = FXCollections.observableArrayList();
        wm.add(new ProjectDoc("wydanie zewnêtrzne", "Wydanie zewnêtrzne ", "WZ "));
        wm.add(new ProjectDoc("rozchód wewnêtrzny", "Rachunek sprzeda¿y ", "RW "));

        typeStoreDocList.add(new TypeStoreDoc(1,"Przyjêcia magazynowe ", UtilsProject.convertToArrayListInvoiceType(pm)));
        typeStoreDocList.add(new TypeStoreDoc(2,"Wydania magazynowe ", UtilsProject.convertToArrayListInvoiceType(wm)));
    }

    private static void fillPaymentMethodList() {
        paymentMethodList.add(new PaymentMethod(1,"gotówka"));
        paymentMethodList.add(new PaymentMethod(2,"przelew"));
    }

    private static void fillTypeList() {
        typeList.add(new TypeProducts(1,"towar"));
        typeList.add(new TypeProducts(2,"us³uga"));
    }

    private static void fillUnitsList() {
        unitsList.add(new UnitsProducts(1,"szt"));
        unitsList.add(new UnitsProducts(2,"kg"));
        unitsList.add(new UnitsProducts(3,"m2"));
        unitsList.add(new UnitsProducts(4,"litr"));
        unitsList.add(new UnitsProducts(5,"t"));
    }

    private static void fillVatList() {
        vatList.add(new VatProducts(1,"23", 23));
        vatList.add(new VatProducts(2,"8", 8));
        vatList.add(new VatProducts(3,"5", 5));
        vatList.add(new VatProducts(4,"0", 0));
        vatList.add(new VatProducts(5,"NP", 0));
        vatList.add(new VatProducts(6,"OO", 0));
        vatList.add(new VatProducts(7,"ZW", 0));
    }

    private static void fillGtuList() {
        gtuList.add(new GtuProducts(1,"brak"));
        gtuList.add(new GtuProducts(2,"GTU_01 Napoje alkoholowe – alkohol etylowy, piwa, wina, napoje fermentowane i wyroby poœrednie"));
        gtuList.add(new GtuProducts(3,"GTU_02 Paliwa napêdowe i oleje napêdowe lub opa³owe"));
        gtuList.add(new GtuProducts(4,"GTU_03 Olej opa³owy w rozumieniu przepisów o podatku akcyzowym orz olej smarowy, pozosta³e oleje opa³owe"));
        gtuList.add(new GtuProducts(5,"GTU_04 Wyroby tytoniowe, susz tytoniowy, p³yny (liquid) do papierosów elektronicznych i wyroby nowatorskie"));
        gtuList.add(new GtuProducts(6,"GTU_05 Odpady i surowce wtórne"));
        gtuList.add(new GtuProducts(7,"GTU_06 Urz¹dzenia elektroniczne oraz czêœci i materia³y"));
        gtuList.add(new GtuProducts(8,"GTU_07 Samochody oraz czêœci samochodowych"));
        gtuList.add(new GtuProducts(9,"GTU_08 Bi¿uteria, monety, metale szlachetne, wyroby z burszynu - antyki"));
        gtuList.add(new GtuProducts(10,"GTU_09 Leki oraz wyroby medyczne z wykazu leków zagro¿onych brakiem dostêpnoœci"));
        gtuList.add(new GtuProducts(11,"GTU_10 Budynki, budowle i grunty"));
        gtuList.add(new GtuProducts(12,"GTU_12 Œwiadczenie us³ug o charakterze niematerialnym"));
        gtuList.add(new GtuProducts(13,"GTU_13 Œwiadczenie us³ug transportowych i gospodarki magazynowej"));
    }

    private static void daoCreateOrUpdate(){
        SupportDao gtuDao = new SupportDao();
        gtuList.forEach(item -> {
            try {
                gtuDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                System.out.println(e.getMessage());
            }
        });
        DbManager.closeConnectionSource();

        SupportDao vatDao = new SupportDao();
        vatList.forEach(item -> {
            try {
                vatDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
        DbManager.closeConnectionSource();

        SupportDao typeDao = new SupportDao();
        typeList.forEach(item -> {
            try {
                typeDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                System.out.println(e.getMessage());
            }
        });
        DbManager.closeConnectionSource();

        SupportDao unitsDao = new SupportDao();
        unitsList.forEach(item -> {
            try {
                unitsDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
        DbManager.closeConnectionSource();

        SupportDao typeSaleDocDao = new SupportDao();
        typeSaleDocList.forEach(item ->{
            try {
                typeSaleDocDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
        DbManager.closeConnectionSource();

        SupportDao typeStoreDocDao = new SupportDao();
        typeStoreDocList.forEach(item ->{
            try {
                typeStoreDocDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
        DbManager.closeConnectionSource();

        SupportDao supportDao = new SupportDao();
        paymentMethodList.forEach(item ->{
            try {
                supportDao.createOrUpdate(item);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
        DbManager.closeConnectionSource();
    }

    public static ArrayList<TypeProducts> getTypeList() {
        return typeList;
    }

    public static ArrayList<GtuProducts> getGtuList() {
        return gtuList;
    }

    public static ArrayList<UnitsProducts> getUnitsList() {
        return unitsList;
    }

    public static ArrayList<VatProducts> getVatList() {
        return vatList;
    }

    public static ArrayList<TypeSaleDoc> getTypeSaleDocList() {
        return typeSaleDocList;
    }

    public static ArrayList<TypeStoreDoc> getTypeStoreDocList() {
        return typeStoreDocList;
    }

    public static ArrayList<PaymentMethod> getPaymentMethodList() {
        return paymentMethodList;
    }
}
