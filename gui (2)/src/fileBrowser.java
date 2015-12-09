import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class fileBrowser extends JTree implements Runnable,Observer {

    static private DefaultMutableTreeNode root,node;
    public JTabbedPane j;
    static private DefaultTreeModel treeModel;
   
   static public JTree tree;
   public static JTabbedPane fm;
 static private void createChildren(File fileRoot, 
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) return;
              System.out.println("IN CREATE CHILDRREN");
            for (File file : files) {
                DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                }
            }
            DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        Icon closedIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547805_file.png");
        Icon openIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547424_opened_folder.png");
        Icon leafIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547805_file.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        }

    
    static public void fun(String savedir,String file)
{
    System.out.println("IN FUN");
           System.out.println("CHILD->"+root.getChildAt(0)+" "+root.getChildAt(1).getParent());
    System.out.println("helloo"+savedir+" "+file);
    int i=0;
     File fileRoot = new File("C:\\Users\\lenovo\\Desktop\\All Downloads");
     root.removeAllChildren(); //this removes all nodes
    treeModel.reload();
     System.out.println("helloo");
     System.out.println("RELOADING ");
     createChildren( fileRoot, root);
}
    fileBrowser(JTabbedPane fm) {
        this.fm=fm;
    }

     public void run() {
           System.out.println("IN FUN KA RUN");
    File fileRoot = new File("C:\\Users\\user\\Desktop\\All Downloads");

        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        //((DefaultTreeModel)tree.getModel()).reload(root);
        treeModel = new DefaultTreeModel(root);
       tree = new JTree(treeModel);
      fm.add(tree);
        // ((DefaultTreeModel)tree.getModel()).reload(root);
       tree.setShowsRootHandles(true);
      CreateChildNodes ccn = 
     new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
         tree.setShowsRootHandles(true);
    }
@Override
    public void update(Observable o, Object arg) {
     
    }
 public class CreateChildNodes implements Runnable {

        private DefaultMutableTreeNode root;

        private File fileRoot;

        public CreateChildNodes(File fileRoot, 
                DefaultMutableTreeNode root) {
       //     System.out.println("vhgghghjgbhj");
            this.fileRoot = fileRoot;
            this.root = root;
              System.out.println("IN CREATE CHILD NODES");
            
            
        }

        @Override
        public void run() {
            createChildren(fileRoot, root);
              System.out.println("IN CREATE CHILD NODE KA RUN");
  //          System.out.println("vhgghghjgbhj");
        }
//System.out.println("vhgghghjgbhj");
        private void createChildren(File fileRoot, 
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) return;
              System.out.println("IN CREATE CHILDRREN");
            for (File file : files) {
                DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                }
            }
        
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        Icon closedIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547805_file.png");
        Icon openIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547424_opened_folder.png");
        Icon leafIcon = new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\gui _ softa\\gui (2)\\images\\1444547805_file.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
    
        }
 }
   static  public class FileNode {

         private File file;

        public FileNode( File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }

}