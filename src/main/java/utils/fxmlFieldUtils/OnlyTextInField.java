package utils.fxmlFieldUtils;

import javafx.scene.control.TextField;

public class OnlyTextInField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {
        if(text.matches("[A-Za-z�󹜳���� ]") || text.isEmpty()){
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
