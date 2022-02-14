package utils.fxmlFieldUtils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeListener implements javafx.beans.value.ChangeListener<String> {

    private final TextField textField;
    private final int maxLength;
    private int minLength = 0;
    private Label wrongLabel = null;
    private int exception = 0;
    private BooleanProperty deactivate = new SimpleBooleanProperty(false);

    public static final int FIELDS_WHO_CAN_BE_NULL = 1;
    public static final int WITHOUT_WRONG_LABEL = 2;

    public ChangeListener(TextField textField, int maxLength, int exception) {
        this.textField = textField;
        this.maxLength = maxLength;
        this.exception = exception;
    }

    public ChangeListener(TextField textField, int maxLength, int exception, BooleanProperty deactivate) {
        this.textField = textField;
        this.maxLength = maxLength;
        this.exception = exception;
        this.deactivate = deactivate;
    }

    public ChangeListener(TextField textField, int maxLength, Label wrongLabel) {
        this.textField = textField;
        this.maxLength = maxLength;
        this.wrongLabel = wrongLabel;
    }

    public ChangeListener(TextField textField, int maxLength, Label wrongLabel, BooleanProperty deactivate) {
        this.textField = textField;
        this.maxLength = maxLength;
        this.wrongLabel = wrongLabel;
        this.deactivate = deactivate;
    }

    public ChangeListener(TextField textField, int minLength, int maxLength, Label wrongLabel) {
        this.textField = textField;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.wrongLabel = wrongLabel;
    }

    public ChangeListener(TextField textField, int minLength, int maxLength, Label wrongLabel, BooleanProperty deactivate) {
        this.textField = textField;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.wrongLabel = wrongLabel;
        this.deactivate = deactivate;
    }

    public ChangeListener(TextField textField, int minLength, int maxLength, Label wrongLabel, int exception) {
        this.textField = textField;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.wrongLabel = wrongLabel;
        this.exception = exception;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue,  String oldValue,  String newValue) {
        if(!deactivate.get()){
            if (newValue == null)
                return;
            if (newValue.length() > maxLength){
                textField.setText(oldValue);
                isWrongData();
            } else {
                textField.setText(newValue);
                isWrongData();
            }
        } else{
            if(exception != WITHOUT_WRONG_LABEL){
                wrongLabel.setVisible(false);
            }
        }
    }

    private void isWrongData(){
        if(exception != WITHOUT_WRONG_LABEL){
            if ((textField.getText().length() <= maxLength) && (textField.getText().length() >= minLength) && (!textField.getText().isEmpty())) {
                wrongLabel.visibleProperty().set(false);
            } else {
                wrongLabel.visibleProperty().set(true);
                if ((textField.getText().length() == 0) && exception == FIELDS_WHO_CAN_BE_NULL)
                    wrongLabel.visibleProperty().set(false);
            }
        }
    }
}
