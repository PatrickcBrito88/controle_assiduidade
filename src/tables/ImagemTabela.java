/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author patri
 */
public class ImagemTabela extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, 
            boolean isSelected, boolean hasFocus, int row, int column) {
              
        if (o instanceof JLabel){
            JLabel lbl = (JLabel)o;
            return lbl;
        }
        return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
    }
    
    DefaultTableCellRenderer letras = new DefaultTableCellRenderer() {

    public void setValue(Object value) {
        setBackground(new Color(238, 238, 238));
        setForeground(Color.BLACK);
        setHorizontalAlignment(JLabel.CENTER);
        //outras alterações entram aqui…
        super.setValue(value);
}
};
    
    
}
