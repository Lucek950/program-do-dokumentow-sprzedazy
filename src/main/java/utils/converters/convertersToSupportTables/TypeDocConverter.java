package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.typeDocModel.TypeDoc;
import modelFx.supportsModelFX.TypeDocFX;
import utils.UtilsProject;

public class TypeDocConverter {
    public static TypeDocFX convertToTypeDocFX(TypeDoc typeDoc){
        TypeDocFX typeDocFX = new TypeDocFX();
        typeDocFX.setId(typeDoc.getId());
        typeDocFX.setName(typeDoc.getName());
        typeDocFX.setProjectDocs(UtilsProject.convertToObservableListInvoiceType(typeDoc.getProjectDocs()));
        return typeDocFX;
    }
}
