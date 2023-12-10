package src.game;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class extends the PlainDocument class, and will be used to set a limit on the number of
 * characters in a JTextField.
 */
public class LimitTextField extends PlainDocument {
    private int max;

    /**
    * Constructor.
    */
    LimitTextField(int max) {
        super();
        this.max = max;
    }

    /**
    * Inserts content into the document.
    */
    public void insertString(int offset, String text, AttributeSet attribute) 
                throws BadLocationException {
        if (text == null) {
            return;
        }
        if ((getLength() + text.length()) <= max) {
            super.insertString(offset, text, attribute);
        }
        
    }    
}
