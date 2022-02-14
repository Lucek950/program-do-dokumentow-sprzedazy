package utils.converters.convertersToSupportTables;

import database.models.supportTablesModel.PaymentMethod;
import modelFx.supportsModelFX.PaymentMethodFX;

public class PaymentMethodConverter {
    public static PaymentMethodFX convertToPaymentMethodFX(PaymentMethod paymentMethod){
        PaymentMethodFX paymentMethodFX = new PaymentMethodFX();
        paymentMethodFX.setId(paymentMethod.getId());
        paymentMethodFX.setName(paymentMethod.getName());
        return paymentMethodFX;
    }

    public static PaymentMethod convertToPaymentMethod(PaymentMethodFX paymentMethodFX){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentMethodFX.getId());
        paymentMethod.setName(paymentMethodFX.getName());
        return paymentMethod;
    }
}
