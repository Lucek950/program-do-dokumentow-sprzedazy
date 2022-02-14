package utils;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import controllers.saleDocControllers.SaleDocController;
import modelFx.docsFX.DocsFX;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UtilsGenerateDocument {
    public static void generateStoreDoc(DocsFX docsFX, String copy){
        Document document = null;

        if(docsFX.getProjectDoc().getShortcut().equals("PZ ") || docsFX.getProjectDoc().getShortcut().equals("WZ "))
            document = DocAsByte.readByteToCreateDoc("/template/templatePzWz.txt");
        else if(docsFX.getProjectDoc().getShortcut().equals("PW ") || docsFX.getProjectDoc().getShortcut().equals("RW "))
            document = DocAsByte.readByteToCreateDoc("/template/templatePwRw.txt");

        Map<String, String> map = new HashMap<>();
        map.put("nameBusiness", docsFX.getNameBus());
        String addressSeller;
        if(docsFX.getStreetBus() == null)
            addressSeller = " ";
        else
            addressSeller = docsFX.getStreetBus();
        map.put("streetAddressZcTownBusiness", addressSeller + " " + docsFX.getAddressBus() + ", " + docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
        map.put("phoneNipBusiness", "tel.: " + docsFX.getPhoneBus() + ", NIP: " + docsFX.getNipBus());
        map.put("bankBusiness", docsFX.getBank1Bus() + " " + docsFX.getBank2Bus() + " " + docsFX.getBank3Bus() + " " + docsFX.getBank4Bus() + " " + docsFX.getBank5Bus() + " " + docsFX.getBank6Bus() + " " + docsFX.getBank7Bus());
        map.put("issuePlace", docsFX.getIssuePlace());
        map.put("issueDate", docsFX.getIssueDate().toString());
        if(docsFX.getProjectDoc().getShortcut().equals("PZ ")){
            map.put("nameTabSeller", docsFX.getNameCon());
            map.put("streetAddressTabSeller", addressSeller + " " + docsFX.getAddressCon());
            map.put("zcTownTabSeller", docsFX.getZc1Con() + "-" + docsFX.getZc2Con() + " " + docsFX.getTownCon());
            map.put("nipTabSeller", "NIP: " + docsFX.getNipCon());
            map.put("nameTabBuyer", docsFX.getNameBus());
            String addressBuyer;
            if(docsFX.getStreetBus() == null)
                addressBuyer = " ";
            else
                addressBuyer = docsFX.getStreetBus();
            map.put("streetAddressTabBuyer", addressBuyer + " " + docsFX.getAddressCon());
            map.put("zcTownTabBuyer", docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
            map.put("nipOrPeselTabBuyer", "NIP: " + docsFX.getNipBus());
        }
        else {
            map.put("nameTabSeller", docsFX.getNameBus());
            map.put("streetAddressTabSeller", addressSeller + " " + docsFX.getAddressBus());
            map.put("zcTownTabSeller", docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
            map.put("nipTabSeller", "NIP: " + docsFX.getNipBus());
            map.put("nameTabBuyer", docsFX.getNameCon());
            String addressBuyer;
            if(docsFX.getStreetCon() == null)
                addressBuyer = " ";
            else
                addressBuyer = docsFX.getStreetCon();
            map.put("streetAddressTabBuyer", addressBuyer + " " + docsFX.getAddressCon());
            map.put("zcTownTabBuyer", docsFX.getZc1Con() + "-" + docsFX.getZc2Con() + " " + docsFX.getTownCon());
            if(docsFX.getProjectDoc().getName().equals("paragon")){
                if(docsFX.getPeselCon() == null || docsFX.getPeselCon().isEmpty()){
                    map.put("nipOrPeselLabel", "NIP:");
                    map.put("nipOrPesel", docsFX.getNipCon());
                }
                else {
                    map.put("nipOrPeselLabel", "PESEL:");
                    map.put("nipOrPesel", docsFX.getPeselCon());
                }
            }
            else {
                if(docsFX.getPeselCon() == null || docsFX.getPeselCon().isEmpty())
                    map.put("nipOrPeselTabBuyer", "NIP: " + docsFX.getNipCon());
                else
                    map.put("nipOrPeselTabBuyer", "NIP: brak, PESEL: " + docsFX.getPeselCon());
            }
        }

        map.put("priceToPay", docsFX.getPriceToPay());
        map.put("priceToPayInWords", docsFX.getPriceToPayInWords());
        map.put("comments", docsFX.getComment());

        if(docsFX.getProjectDoc().getShortcut().equals("PZ "))
            map.put("recipient", docsFX.getIssued());
        else
            map.put("issued", docsFX.getIssued());
        map.put("footerNumberInvoice", docsFX.getNumberToPrint() + copy);
        Section section1 = document.getSections().get(0);

        section1.getParagraphs().get(0).appendText(docsFX.getNumberToPrint() + copy).getCharacterFormat().setFontSize(16);
        section1.getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
        section1.getParagraphs().get(0).getFormat().setBeforeSpacing(5);
        section1.getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
        section1.getParagraphs().get(0).getFormat().setAfterSpacing(5);
        section1.getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        if(docsFX.getRelatedDoc() != null){
            Paragraph paragraph = section1.addParagraph();
            paragraph.appendText("do dokumentu: " + docsFX.getRelatedDoc()).getCharacterFormat().setFontSize(10);
            paragraph.getFormat().setBeforeAutoSpacing(false);
            paragraph.getFormat().setBeforeSpacing(0);
            paragraph.getFormat().setAfterAutoSpacing(false);
            paragraph.getFormat().setAfterSpacing(5);
            paragraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        if(docsFX.getAdditionalDoc() != null){
            Paragraph paragraph = section1.addParagraph();
            paragraph.appendText("dokument dostawcy: " + docsFX.getAdditionalDoc()).getCharacterFormat().setFontSize(10);
            paragraph.getFormat().setBeforeAutoSpacing(false);
            paragraph.getFormat().setBeforeSpacing(0);
            paragraph.getFormat().setAfterAutoSpacing(false);
            paragraph.getFormat().setAfterSpacing(5);
            paragraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        String[] headerProduct = {"lp.", "nazwa - opis", "iloœæ", "j.m.", "cena jedn. netto", "wartoœæ netto"};

        String[][] dataProduct;
        if(docsFX.getProjectDoc().getShortcut().equals("WZ ") || docsFX.getProjectDoc().getShortcut().equals("RW ")){
            headerProduct = new String[]{"lp.", "nazwa - opis", "iloœæ", "j.m.", "cena jedn. netto", "wartoœæ netto", "koszt"};
            dataProduct = new String[docsFX.getListProductToAddDocs().size()][7];
            for(int i = 0; i < docsFX.getListProductToAddDocs().size(); i++){
                dataProduct[i][0] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getLp());
                dataProduct[i][1] = docsFX.getListProductToAddDocs().get(i).getName();
                dataProduct[i][2] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getQuantity());
                dataProduct[i][3] = docsFX.getListProductToAddDocs().get(i).getUnit();
                dataProduct[i][4] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getUnitPriceNet());
                dataProduct[i][5] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolNet());
                dataProduct[i][6] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getCost());
            }
        }
        else {
            dataProduct = new String[docsFX.getListProductToAddDocs().size()][6];
            for(int i = 0; i < docsFX.getListProductToAddDocs().size(); i++){
                dataProduct[i][0] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getLp());
                dataProduct[i][1] = docsFX.getListProductToAddDocs().get(i).getName();
                dataProduct[i][2] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getQuantity());
                dataProduct[i][3] = docsFX.getListProductToAddDocs().get(i).getUnit();
                dataProduct[i][4] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getUnitPriceNet());
                dataProduct[i][5] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolNet());
            }
        }

        PreferredWidth widthProduct = new PreferredWidth(WidthType.Percentage, (short) 100);

        Table tableProduct = section1.addTable(true);
        tableProduct.resetCells(dataProduct.length + 1, headerProduct.length);

        TableRow rowProduct = tableProduct.getRows().get(0);
        rowProduct.isHeader(true);
        rowProduct.setHeight(25);
        rowProduct.setHeightType(TableRowHeightType.Exactly);
        rowProduct.getRowFormat().setBackColor(new Color(217,217,217));

        for (int i = 0; i < headerProduct.length; i++) {
            rowProduct.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            rowProduct.getCells().get(i).addParagraph().appendText(headerProduct[i]).getCharacterFormat().setFontSize(8);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setAfterSpacing(0);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        for (int r = 0; r < dataProduct.length; r++) {
            TableRow dataRowProduct = tableProduct.getRows().get(r + 1);
            dataRowProduct.setHeightType(TableRowHeightType.Auto);
            dataRowProduct.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < headerProduct.length; c++) {
                dataRowProduct.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Top);
                dataRowProduct.getCells().get(c).getCellFormat().setTextWrap(true);
                dataRowProduct.getCells().get(c).addParagraph().appendText(dataProduct[r][c]).getCharacterFormat().setFontSize(8);
                dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
                dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
                dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
                dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterSpacing(0);
                if((c == 0) || (c == 1))
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
                if((c == 3))
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                if((c == 2) || (c == 4) || (c == 5))
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
                if(docsFX.getProjectDoc().getShortcut().equals("WZ ") || docsFX.getProjectDoc().getShortcut().equals("RW "))
                    if((c == 6))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
            }
        }

        tableProduct.autoFit(AutoFitBehaviorType.Fixed_Column_Widths);
        tableProduct.setPreferredWidth(widthProduct);

        if(docsFX.getProjectDoc().getShortcut().equals("WZ ") || docsFX.getProjectDoc().getShortcut().equals("RW "))
            for (int r = 0; r < tableProduct.getRows().getCount(); r++ ) {
                tableProduct.getRows().get(r).getCells().get(0).setCellWidth((float) 5.8, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(1).setCellWidth((float) 35.34, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(2).setCellWidth((float) 8.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(3).setCellWidth((float) 7.40, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(4).setCellWidth((float) 9.5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(5).setCellWidth((float) 12, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(6).setCellWidth((float) 12, CellWidthType.Percentage);
            }
        else
            for (int r = 0; r < tableProduct.getRows().getCount(); r++ ) {
                tableProduct.getRows().get(r).getCells().get(0).setCellWidth((float) 5.8, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(1).setCellWidth((float) 35.34, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(2).setCellWidth((float) 8.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(3).setCellWidth((float) 7.40, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(4).setCellWidth((float) 9.5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(5).setCellWidth((float) 12, CellWidthType.Percentage);
            }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            //Locate the bookmark
            BookmarksNavigator bookmarkNavigator = new BookmarksNavigator(document);
            bookmarkNavigator.moveToBookmark(entry.getKey());
            bookmarkNavigator.replaceBookmarkContent(entry.getValue(), true);
        }

        String path = "Dokumenty - system fakturowania/Magazyn";

        File file = new File(path);
        file.mkdir();

        if(docsFX.getProjectDoc().getShortcut().equals("WZ ") || docsFX.getProjectDoc().getShortcut().equals("RW "))
            path = path + "/Dokumenty wydania";
        else if(docsFX.getProjectDoc().getShortcut().equals("PZ ") || docsFX.getProjectDoc().getShortcut().equals("PW "))
            path = path + "/Dokumenty przyjêcia";

        file  = new File(path);
        file.mkdir();

        document.saveToFile(file.getAbsolutePath() + "/" + docsFX.getNumber().replace("/","_")  + copy + ".pdf", FileFormat.PDF);
    }

    public static void generateSalesDoc(DocsFX docsFX, String copy){
        Document document;
        if(docsFX.getProjectDoc().getName().equals("paragon")){
            if((docsFX.getPeselCon() == null || docsFX.getPeselCon().isEmpty())
                    && (docsFX.getNipCon() == null || docsFX.getNipCon().isEmpty()) ) {
                document = DocAsByte.readByteToCreateDoc("/template/templateReceipt.txt");
            }
            else
                document = DocAsByte.readByteToCreateDoc("/template/templateReceiptNipOrPesel.txt");
        }
        else
            document = DocAsByte.readByteToCreateDoc("/template/templateInvoice.txt");

        Map<String, String> map = new HashMap<>();
        map.put("nameBusiness", docsFX.getNameBus());
        String addressSeller;
        if(docsFX.getStreetBus() == null)
            addressSeller = " ";
        else
            addressSeller = docsFX.getStreetBus();
        map.put("streetAddressZcTownBusiness", addressSeller + " " + docsFX.getAddressBus() + ", " + docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
        map.put("phoneNipBusiness", "tel.: " + docsFX.getPhoneBus() + ", NIP: " + docsFX.getNipBus());
        map.put("bankBusiness", docsFX.getBank1Bus() + " " + docsFX.getBank2Bus() + " " + docsFX.getBank3Bus() + " " + docsFX.getBank4Bus() + " " + docsFX.getBank5Bus() + " " + docsFX.getBank6Bus() + " " + docsFX.getBank7Bus());
        map.put("issuePlace", docsFX.getIssuePlace());
        map.put("executeDate", docsFX.getExecuteDate().toString());
        map.put("issueDate", docsFX.getIssueDate().toString());
        if(docsFX.getProjectDoc().getShortcut().equals("RZ ") || docsFX.getProjectDoc().getShortcut().equals("FZ ")){
            map.put("nameTabSeller", docsFX.getNameCon());
            map.put("streetAddressTabSeller", addressSeller + " " + docsFX.getAddressCon());
            map.put("zcTownTabSeller", docsFX.getZc1Con() + "-" + docsFX.getZc2Con() + " " + docsFX.getTownCon());
            map.put("nipTabSeller", "NIP: " + docsFX.getNipCon());
            map.put("nameTabBuyer", docsFX.getNameBus());
            String addressBuyer;
            if(docsFX.getStreetBus() == null)
                addressBuyer = " ";
            else
                addressBuyer = docsFX.getStreetBus();
            map.put("streetAddressTabBuyer", addressBuyer + " " + docsFX.getAddressCon());
            map.put("zcTownTabBuyer", docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
            map.put("nipOrPeselTabBuyer", "NIP: " + docsFX.getNipBus());
        }
        else {
            map.put("nameTabSeller", docsFX.getNameBus());
            map.put("streetAddressTabSeller", addressSeller + " " + docsFX.getAddressBus());
            map.put("zcTownTabSeller", docsFX.getZc1Bus() + "-" + docsFX.getZc2Bus() + " " + docsFX.getTownBus());
            map.put("nipTabSeller", "NIP: " + docsFX.getNipBus());
            map.put("nameTabBuyer", docsFX.getNameCon());
            String addressBuyer;
            if(docsFX.getStreetCon() == null)
                addressBuyer = " ";
            else
                addressBuyer = docsFX.getStreetCon();
            map.put("streetAddressTabBuyer", addressBuyer + " " + docsFX.getAddressCon());
            map.put("zcTownTabBuyer", docsFX.getZc1Con() + "-" + docsFX.getZc2Con() + " " + docsFX.getTownCon());
            if(docsFX.getProjectDoc().getName().equals("paragon")){
                if(docsFX.getPeselCon() == null || docsFX.getPeselCon().isEmpty()){
                    map.put("nipOrPeselLabel", "NIP:");
                    map.put("nipOrPesel", docsFX.getNipCon());
                }
                else {
                    map.put("nipOrPeselLabel", "PESEL:");
                    map.put("nipOrPesel", docsFX.getPeselCon());
                }
            }
            else {
                if(docsFX.getPeselCon() == null || docsFX.getPeselCon().isEmpty())
                    map.put("nipOrPeselTabBuyer", "NIP: " + docsFX.getNipCon());
                else
                    map.put("nipOrPeselTabBuyer", "NIP: brak, PESEL: " + docsFX.getPeselCon());
            }
        }

        map.put("totalRateVolNetCol", docsFX.getRateSumNet());
        map.put("totalRateSumVatCol", docsFX.getRateSumVat());
        map.put("totalRateVolGainCol", docsFX.getRateSumGross());
        map.put("priceToPay", docsFX.getPriceToPay());
        map.put("priceToPayInWords", docsFX.getPriceToPayInWords());
        if(docsFX.isSplitPayment())
            map.put("splitPayment", "Mo¿na zastosowaæ: \"mechanizm podzielonej p³atnoœci\"");
        map.put("paymentMethod", docsFX.getPaymentMethodFX().getName());
        if(!docsFX.getProjectDoc().getShortcut().equals("PA ")){
            map.put("payDateLabel", "Termin p³atnoœci:");
            map.put("paymentDate", docsFX.getPaymentDate().toString());
        }
        if(docsFX.getProjectDoc().getShortcut().equals("RZ ") || docsFX.getProjectDoc().getShortcut().equals("FZ "))
            map.put("recipient", docsFX.getIssued());
        else
            map.put("issued", docsFX.getIssued());
        map.put("footerNumberInvoice", docsFX.getNumberToPrint() + copy);
        Section section1 = document.getSections().get(0);

        section1.getParagraphs().get(0).appendText(docsFX.getNumberToPrint() + copy).getCharacterFormat().setFontSize(16);
        section1.getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
        section1.getParagraphs().get(0).getFormat().setBeforeSpacing(5);
        section1.getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
        section1.getParagraphs().get(0).getFormat().setAfterSpacing(5);
        section1.getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        if(docsFX.getProjectDoc().getName().equals("faktura sprzeda¿y bez paragonu") || docsFX.getProjectDoc().getShortcut().equals("RZ ") || docsFX.getProjectDoc().getShortcut().equals("FZ ")){
            Paragraph paragraph = section1.addParagraph();
            if(docsFX.getProjectDoc().getName().equals("faktura sprzeda¿y bez paragonu"))
                paragraph.appendText("do paragonu: " + docsFX.getAdditionalDoc()).getCharacterFormat().setFontSize(10);
            if(docsFX.getProjectDoc().getShortcut().equals("RZ ") || docsFX.getProjectDoc().getShortcut().equals("FZ "))
                paragraph.appendText("dokument dostawcy: " + docsFX.getAdditionalDoc()).getCharacterFormat().setFontSize(10);
            paragraph.getFormat().setBeforeAutoSpacing(false);
            paragraph.getFormat().setBeforeSpacing(0);
            paragraph.getFormat().setAfterAutoSpacing(false);
            paragraph.getFormat().setAfterSpacing(5);
            paragraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        String[] headerProduct = {"lp.", "nazwa - opis", "kod CN/PKWiU", "iloœæ", "j.m.", "cena jedn. netto", "VAT[%]", "rabat[%]", "wartoœæ netto", "kwota VAT", "wartoœæ brutto"};
        if(docsFX.getProjectDoc().getShortcut().equals("RS ") || docsFX.getProjectDoc().getShortcut().equals("RZ ") || docsFX.getProjectDoc().getName().equals("faktura sprzeda¿y bez paragonu")){
            headerProduct = new String[]{"lp.", "nazwa - opis", "kod CN/PKWiU", "iloœæ", "j.m.", "cena jedn. brutto", "VAT[%]", "rabat[%]", "wartoœæ netto", "kwota VAT", "wartoœæ brutto"};
        }
        String[][] dataProduct;
        if(docsFX.getProjectDoc().getShortcut().equals("PA ")){
            headerProduct = new String[]{"lp.", "nazwa - opis", "iloœæ", "j.m.", "cena jedn. brutto", "VAT[%]", "rabat[%]", "wartoœæ netto", "kwota VAT", "wartoœæ brutto"};
            dataProduct = new String[docsFX.getListProductToAddDocs().size()][10];
            for(int i = 0; i < docsFX.getListProductToAddDocs().size(); i++){
                dataProduct[i][0] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getLp());
                dataProduct[i][1] = docsFX.getListProductToAddDocs().get(i).getName();
                dataProduct[i][2] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getQuantity());
                dataProduct[i][3] = docsFX.getListProductToAddDocs().get(i).getUnit();
                dataProduct[i][4] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getUnitPriceNet());
                dataProduct[i][5] = docsFX.getListProductToAddDocs().get(i).getVat();
                dataProduct[i][6] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getDiscount());
                dataProduct[i][7] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolNet());
                dataProduct[i][8] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getSumVat());
                dataProduct[i][9] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolGross());
            }
        }
        else {
            dataProduct = new String[docsFX.getListProductToAddDocs().size()][11];
            for(int i = 0; i < docsFX.getListProductToAddDocs().size(); i++){
                dataProduct[i][0] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getLp());
                dataProduct[i][1] = docsFX.getListProductToAddDocs().get(i).getName();
                dataProduct[i][2] = docsFX.getListProductToAddDocs().get(i).getPkwIuCN();
                dataProduct[i][3] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getQuantity());
                dataProduct[i][4] = docsFX.getListProductToAddDocs().get(i).getUnit();
                dataProduct[i][5] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getUnitPriceNet());
                dataProduct[i][6] = docsFX.getListProductToAddDocs().get(i).getVat();
                dataProduct[i][7] = String.valueOf(docsFX.getListProductToAddDocs().get(i).getDiscount());
                dataProduct[i][8] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolNet());
                dataProduct[i][9] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getSumVat());
                dataProduct[i][10] = String.format("%.2f", docsFX.getListProductToAddDocs().get(i).getVolGross());
            }
        }

        PreferredWidth widthProduct = new PreferredWidth(WidthType.Percentage, (short) 100);

        Table tableProduct = section1.addTable(true);
        tableProduct.resetCells(dataProduct.length + 1, headerProduct.length);

        TableRow rowProduct = tableProduct.getRows().get(0);
        rowProduct.isHeader(true);
        rowProduct.setHeight(25);
        rowProduct.setHeightType(TableRowHeightType.Exactly);
        rowProduct.getRowFormat().setBackColor(new Color(217,217,217));

        for (int i = 0; i < headerProduct.length; i++) {
            rowProduct.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            rowProduct.getCells().get(i).addParagraph().appendText(headerProduct[i]).getCharacterFormat().setFontSize(8);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setAfterSpacing(0);
            rowProduct.getCells().get(i).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        for (int r = 0; r < dataProduct.length; r++) {
            TableRow dataRowProduct = tableProduct.getRows().get(r + 1);
            dataRowProduct.setHeightType(TableRowHeightType.Auto);
            dataRowProduct.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < headerProduct.length; c++) {
                if(docsFX.getProjectDoc().getShortcut().equals("PA ")){
                    dataRowProduct.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Top);
                    dataRowProduct.getCells().get(c).getCellFormat().setTextWrap(true);
                    dataRowProduct.getCells().get(c).addParagraph().appendText(dataProduct[r][c]).getCharacterFormat().setFontSize(8);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterSpacing(0);
                    if((c == 0) || (c == 1) || (c == 2) || (c == 3))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
                    if((c == 5) || (c == 6))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                    if((c == 4) || (c == 7) || (c == 8) || (c == 9))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
                }
                else {
                    dataRowProduct.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Top);
                    dataRowProduct.getCells().get(c).getCellFormat().setTextWrap(true);
                    dataRowProduct.getCells().get(c).addParagraph().appendText(dataProduct[r][c]).getCharacterFormat().setFontSize(8);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
                    dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setAfterSpacing(0);
                    if((c == 0) || (c == 1) || (c == 2) || (c == 4))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
                    if((c == 6) || (c == 7))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                    if((c == 3) || (c == 5) || (c == 8) || (c == 9) || (c == 10))
                        dataRowProduct.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
                }
            }
        }

        tableProduct.autoFit(AutoFitBehaviorType.Fixed_Column_Widths);
        tableProduct.setPreferredWidth(widthProduct);

        if(docsFX.getProjectDoc().getShortcut().equals("PA ")){
            for (int r = 0; r < tableProduct.getRows().getCount(); r++ ) {
                tableProduct.getRows().get(r).getCells().get(0).setCellWidth((float) 4.8, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(1).setCellWidth((float) 28.04, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(2).setCellWidth((float) 6.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(3).setCellWidth((float) 5.40, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(4).setCellWidth((float) 8.5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(5).setCellWidth((float) 4.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(6).setCellWidth((float) 5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(7).setCellWidth((float) 9, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(8).setCellWidth((float) 9, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(9).setCellWidth((float) 9, CellWidthType.Percentage);
            }
        }
        else {
            for (int r = 0; r < tableProduct.getRows().getCount(); r++ ) {
                tableProduct.getRows().get(r).getCells().get(0).setCellWidth((float) 4.8, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(1).setCellWidth((float) 19.54, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(2).setCellWidth((float) 8.50, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(3).setCellWidth((float) 6.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(4).setCellWidth((float) 5.40, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(5).setCellWidth((float) 8.5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(6).setCellWidth((float) 4.3, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(7).setCellWidth((float) 5, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(8).setCellWidth((float) 9, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(9).setCellWidth((float) 9, CellWidthType.Percentage);
                tableProduct.getRows().get(r).getCells().get(10).setCellWidth((float) 9, CellWidthType.Percentage);
            }
        }

        Section section2 = document.getSections().get(1);

        String[] headerRates = {"wed³ug stawki Vat", "wartoœæ netto", "kwota VAT", "wartoœæ brutto"};
        String[][] dataRates = new String[docsFX.getListRateProductsToAddDocs().size() + 1][4];
        for(int i = 0; i < docsFX.getListRateProductsToAddDocs().size() + 1; i++){
            if(i < docsFX.getListRateProductsToAddDocs().size()) {
                dataRates[i][0] = String.valueOf(docsFX.getListRateProductsToAddDocs().get(i).getVat());
                dataRates[i][1] = String.format("%.2f", docsFX.getListRateProductsToAddDocs().get(i).getVolumeNet());
                dataRates[i][2] = String.format("%.2f", docsFX.getListRateProductsToAddDocs().get(i).getSumVat());
                dataRates[i][3] = String.format("%.2f", docsFX.getListRateProductsToAddDocs().get(i).getVolumeGain());
            } else {
                dataRates[i][0] = "razem:";
                dataRates[i][1] = docsFX.getRateSumNet();
                dataRates[i][2] = docsFX.getRateSumVat();
                dataRates[i][3] = docsFX.getRateSumGross();
            }
        }

        Table tableRates = section2.addTable(true);
        tableRates.resetCells(dataRates.length + 1, headerRates.length);
        tableRates.getTableFormat().setLeftIndent(173);
        PreferredWidth widthRate = new PreferredWidth(WidthType.Percentage, (short) 67);

        TableRow rowRates = tableRates.getRows().get(0);
        rowRates.isHeader(true);
        rowRates.getRowFormat().setBackColor(new Color(217,217,217));

        for (int i = 0; i < headerRates.length; i++) {
            rowRates.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            rowRates.getCells().get(i).addParagraph().appendText(headerRates[i]).getCharacterFormat().setFontSize(8);
            rowRates.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
            rowRates.getCells().get(i).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
            rowRates.getCells().get(i).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
            rowRates.getCells().get(i).getParagraphs().get(0).getFormat().setAfterSpacing(0);
            rowRates.getCells().get(i).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        }

        for (int r = 0; r < dataRates.length; r++) {
            TableRow dataRowRates = tableRates.getRows().get(r + 1);
            dataRowRates.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < headerRates.length; c++) {
                dataRowRates.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRowRates.getCells().get(c).addParagraph().appendText(dataRates[r][c]).getCharacterFormat().setFontSize(8);
                dataRowRates.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeAutoSpacing(false);
                dataRowRates.getCells().get(c).getParagraphs().get(0).getFormat().setBeforeSpacing(0);
                dataRowRates.getCells().get(c).getParagraphs().get(0).getFormat().setAfterAutoSpacing(false);
                dataRowRates.getCells().get(c).getParagraphs().get(0).getFormat().setAfterSpacing(0);
                dataRowRates.getCells().get(c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                tableRates.getRows().get(r).getCells().get(c).setCellWidth(25, CellWidthType.Percentage);
            }
        }

        for (int c = 0; c < headerRates.length; c++) {
            tableRates.get(dataRates.length, c).getParagraphs().get(0).getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
            tableRates.get(dataRates.length, c).getCellFormat().setBackColor(new Color(217,217,217));
        }

        tableRates.autoFit(AutoFitBehaviorType.Fixed_Column_Widths);
        tableRates.setPreferredWidth(widthRate);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            //Locate the bookmark
            BookmarksNavigator bookmarkNavigator = new BookmarksNavigator(document);
            bookmarkNavigator.moveToBookmark(entry.getKey());
            bookmarkNavigator.replaceBookmarkContent(entry.getValue(), true);
        }

        String path = "Dokumenty - system fakturowania/Faktury";
        File file = new File(path);
        file.mkdir();

        switch (SaleDocController.getFlagWhatOpen()){
            case 0:
                path = path + "/Faktury sprzeda¿y";
                break;
            case 1:
                path = path + "/Faktury zakupu";
                break;
            case 2:
                path = path + "/Sprzeda¿ detaliczna";
                break;
        }

        file  = new File(path);
        file.mkdir();

        document.saveToFile(file.getAbsolutePath() + "/" + docsFX.getNumber().replace("/","_")  + copy + ".pdf", FileFormat.PDF);
    }
}
