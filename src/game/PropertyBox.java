package src.game;

import javax.swing.JLabel;
import src.spaces.NormalProperty;
import src.spaces.RailroadProperty;
import src.spaces.UtilityProperty;

/**
 * Represents a selectable box containing a property in the trading menu.
 */
public class PropertyBox {
    public JLabel boxLabel;
    
    public NormalProperty normalProperty;
    public RailroadProperty railroadProperty;
    public UtilityProperty utilityProperty;
    
    public String propertyType;
    public boolean selected;

    /**
    * Constructor.
    */
    public PropertyBox() {
        selected = false;
    }
}
