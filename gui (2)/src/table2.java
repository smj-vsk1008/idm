import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;


public class table2 extends AbstractTableModel implements Observer {
  
    private static final String[] columnNames={"File URL","Size","Progress","Speed","Status"};
    private static final Class[] columnClasses = {String.class, String.class, JProgressBar.class, String.class, String.class};
    private ArrayList downList=new ArrayList();
    
    public void addDownload(Download download){
        //System.out.println("In addDownload no probs\n");
        download.addObserver(this);
        downList.add(download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
     }
    public Download getDownload(int row){
        return (Download) downList.get(row);
    }
    
        @Override
        public int getRowCount() {
        return downList.size();
           }

        @Override
        public int getColumnCount() {
            //System.out.println("returning"+columnNames.length+"\n");
            return columnNames.length;
        }

         // Get a column's name.
         @Override
        public String getColumnName(int col) {
                 return columnNames[col];
            }
     
         // Get a column's class.
        @Override
         public Class getColumnClass(int col) {
                return columnClasses[col];
            }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        Download download = (Download) downList.get(rowIndex);
        switch (columnIndex) {
            case 0: // URL
               System.out.println("table get"+download.getURL());
                return download.getURL();
            case 1: // Size
                int size = download.getSize();
              
                return (size == -1) ? "" : Integer.toString(size);
            case 2: // Progress
                System.out.println("table get"+download.getProgress());
                return new Float(download.getProgress());
            case 3: //Speed
                System.out.println("table get"+download.getProgress());
                return String.format("%.2f", download.getSpeed());
            case 4: // Status
               
                return Download.STATUSES[download.getStatus()];
                
        }
        return "";
        }
     

    @Override
    public void update(Observable o, Object arg) {
        int index = downList.indexOf(o);
         System.out.println("In update"+(int)arg);
        // Fire table row update notification to table.
         switch((int)arg){
             case 4:
                fireTableRowsUpdated(index, index);
                 break;
             default:
                 fireTableCellUpdated(index,(int)arg);
        }
         return;
    }
} 

