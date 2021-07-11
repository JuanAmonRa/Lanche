
package projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Utilit {
    
    public void limpaTela(JPanel p){
        Component comp[] = p.getComponents();
        for (Component component : comp) {
            if(component instanceof JTextField){
                ((JTextField)component).setText(null);
            }
        }
    }

    public void limpaTela(JTabbedPane jPDadosPessoais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
