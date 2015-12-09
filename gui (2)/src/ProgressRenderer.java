import java.awt.Color;
import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ProgressRenderer extends JProgressBar implements TableCellRenderer {
    // Constructor for ProgressRenderer.
    public ProgressRenderer(int min, int max) {
        super(min, max);
        this.setBackground(Color.BLUE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             if((int) ((Float) value).floatValue()==100)
            {this.setOpaque(true);
                System.out.println("ho gya green"+(Float)value);
            }
        setValue((int) ((Float) value).floatValue());
        return this;
    }
}
