package utils;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import modelFx.docsFX.DocsFX;
import modelFx.itemsToModels.ListProductsToAddDoc;
import modelFx.itemsToModels.RateProductsToAddDoc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DocAsByte {
    private static void saveDocToByte(Document document, String filename){
        ByteArrayOutputStream docOutStream = new ByteArrayOutputStream();
        document.saveToFile(docOutStream, FileFormat.Docx);
        byte[] docBytes = docOutStream.toByteArray();
        PrintWriter zapis = null;
        try {
            zapis = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < docBytes.length; i++) {
            if(i == docBytes.length)
                zapis.print(docBytes[i]);
            else {
                assert zapis != null;
                zapis.print(docBytes[i] + ",");
            }
        }

        assert zapis != null;
        zapis.close();
    }

    protected static Document readByteToCreateDoc(String path){
        InputStream resource = UtilsProject.class.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resource)));

        String[] str = new String[0];
        try {
            str = reader.readLine().split(",");
            reader.close();
            resource.close();
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        List<String> listByteFromDoc = Arrays.asList(str);
        byte[] docInTabByte = new byte[listByteFromDoc.size() - 1];
        for(int i = 0; i < docInTabByte.length; i++){
            docInTabByte[i] = Byte.parseByte(listByteFromDoc.get(i));
        }

        return new Document(new ByteArrayInputStream(docInTabByte));
    }

    protected static byte[] convertObjectToBytes1(ArrayList<ListProductsToAddDoc> obj) throws IOException {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        }
    }

    protected static byte[] convertObjectToBytes2(ArrayList<RateProductsToAddDoc> obj) throws IOException {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        }
    }

    public static void generateTxtWithByteDoc(){
        Document docInvoice = new Document("templatesDocx/templateInvoice.docx");
        Document docReceipt = new Document("templatesDocx/templateReceipt.docx");
        Document docReceiptNipOrPesel = new Document("templatesDocx/templateReceiptNipPesel.docx");
        Document docPwRw = new Document("templatesDocx/templatePwRw.docx");
        Document docPzWz = new Document("templatesDocx/templatePzWz.docx");

        DocAsByte.saveDocToByte(docInvoice, "templateInvoice.txt");
        DocAsByte.saveDocToByte(docReceipt, "templateReceipt.txt");
        DocAsByte.saveDocToByte(docReceiptNipOrPesel, "templateReceiptNipOrPesel.txt");
        DocAsByte.saveDocToByte(docPwRw, "templatePwRw.txt");
        DocAsByte.saveDocToByte(docPzWz, "templatePzWz.txt");
    }

    public static void saveFile(DocsFX docsFX) throws IOException {
        PrintWriter zapis;
        try {
            zapis = new PrintWriter(docsFX.getNumber().replace("/", "_") + ".txt");
            zapis.println(docsFX.getProjectDoc());
            zapis.println("NameBus " + docsFX.getNameBus());
            zapis.println("TownBus " + docsFX.getTownBus());
            zapis.println("Zc1Bus " + docsFX.getZc1Bus());
            zapis.println("Zc2Bus " + docsFX.getZc2Bus());
            zapis.println("StreetBus " + docsFX.getStreetBus());
            zapis.println("AddressBus " + docsFX.getAddressBus());
            zapis.println("NipBus " + docsFX.getNipBus());
            zapis.println("PhoneBus " + docsFX.getPhoneBus());
            zapis.println("Bank1Bus " + docsFX.getBank1Bus());
            zapis.println("Bank2Bus " + docsFX.getBank2Bus());
            zapis.println("Bank3Bus " + docsFX.getBank3Bus());
            zapis.println("Bank4Bus " + docsFX.getBank4Bus());
            zapis.println("Bank5Bus " + docsFX.getBank5Bus());
            zapis.println("Bank6Bus " + docsFX.getBank6Bus());
            zapis.println("Bank7Bus " + docsFX.getBank7Bus());
            zapis.println("NameCon " + docsFX.getNameCon());
            zapis.println("TownCon " + docsFX.getTownCon());
            zapis.println("Zc1Con " + docsFX.getZc1Con());
            zapis.println("Zc2Con " + docsFX.getZc2Con());
            zapis.println("StreetCon " + docsFX.getStreetCon());
            zapis.println("AddressCon " + docsFX.getAddressCon());
            zapis.println("NipCon " + docsFX.getNipCon());
            zapis.println("PeselCon " + docsFX.getPeselCon());
            zapis.println("Number " + docsFX.getNumber());
            zapis.println("NumberToPrint " + docsFX.getNumberToPrint());
            zapis.println("AdditionalDoc " + docsFX.getAdditionalDoc());
            zapis.println("IssuePlace " + docsFX.getIssuePlace());
            zapis.println("IssueDate " + docsFX.getIssueDate());
            zapis.println("ExecuteDate " + docsFX.getExecuteDate());
            zapis.println("ListProduct byte " + Arrays.toString(convertObjectToBytes1(UtilsProject.convertToArrayListProducts(docsFX.getListProductToAddDocs()))));
            zapis.println("RateProduct byte " + Arrays.toString(convertObjectToBytes2(UtilsProject.convertToArrayListRateProducts(docsFX.getListRateProductsToAddDocs()))));
            zapis.println("RateSumNet " + docsFX.getRateSumNet());
            zapis.println("RateSumVat " + docsFX.getRateSumVat());
            zapis.println("RateSumGross " + docsFX.getRateSumGross());
            zapis.println("PriceToPay " + docsFX.getPriceToPay());
            zapis.println("PriceToPayInWords " + docsFX.getPriceToPayInWords());
            if(docsFX.getPaymentMethodFX() != null)
                zapis.println("PaymentMethod " + docsFX.getPaymentMethodFX().getId());
            zapis.println("SplitPayment " + docsFX.isSplitPayment());
            zapis.println("PaymentDate " + docsFX.getPaymentDate());
            zapis.println("Comment " + docsFX.getComment());
            zapis.println("Issue " + docsFX.getIssued());
            zapis.println("RelatedDoc " + docsFX.getRelatedDoc());
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
