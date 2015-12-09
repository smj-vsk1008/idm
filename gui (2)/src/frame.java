
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class frame extends javax.swing.JFrame {
    public static table2 dataM;
    public static PrimaryThread pid;;
    private int flag=0;
    
    public frame() {
        this.setTitle("Viral Downloader");
        this.pid = new PrimaryThread();
        dataM=new table2();
        initComponents();
        drag.getDocument().addDocumentListener(new DocumentListener(){

                     @Override
                     public void insertUpdate(DocumentEvent e) {
                            
                         fun(); 
                           //  addURLtext.setText("");
                     }

                     @Override
                     public void removeUpdate(DocumentEvent e) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 
                     }

                     @Override
                     public void changedUpdate(DocumentEvent e) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 
                     }
                     private void fun()
                     {  URL verifiedURL=verifyUrl(drag.getText());
                        flag=1;
                      
                            if(verifiedURL!=null)
                    {
                           
                         dataM.addDownload(new Download(verifiedURL,pid));
                          
                    System.out.println("hello") ;
                    } 
                    addURLtext.addKeyListener(null);
                     //addURLtext.setText("");
                    }
             
         });
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addURLtext = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        resume = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        scheduler = new javax.swing.JButton();
        addURL = new javax.swing.JButton();
        drag = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(dataM);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Viral Downloader"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(200, 200));

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));

        addURLtext.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                addURLtextMouseMoved(evt);
            }
        });
        addURLtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addURLtextActionPerformed(evt);
            }
        });

        cancel.setBackground(new java.awt.Color(255, 204, 51));
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444544925_cancel.png"))); // NOI18N
        cancel.setToolTipText("Cancel Download");
        cancel.setContentAreaFilled(false);
        cancel.setFocusPainted(false);
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        resume.setBackground(new java.awt.Color(255, 204, 51));
        resume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444544900_button_blue_play.png"))); // NOI18N
        resume.setToolTipText("Resume Download");
        resume.setBorderPainted(false);
        resume.setContentAreaFilled(false);
        resume.setFocusPainted(false);
        resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeActionPerformed(evt);
            }
        });

        pause.setBackground(new java.awt.Color(255, 204, 51));
        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444544638_button_blue_pause.png"))); // NOI18N
        pause.setToolTipText("Pause Download");
        pause.setBorderPainted(false);
        pause.setContentAreaFilled(false);
        pause.setFocusPainted(false);
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        scheduler.setBackground(new java.awt.Color(255, 204, 51));
        scheduler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444548496_alarm.png"))); // NOI18N
        scheduler.setToolTipText("Schedule Download");
        scheduler.setBorderPainted(false);
        scheduler.setContentAreaFilled(false);
        scheduler.setFocusPainted(false);
        scheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedulerActionPerformed(evt);
            }
        });

        addURL.setBackground(new java.awt.Color(255, 204, 51));
        addURL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444545166_icon-81-document-add.png"))); // NOI18N
        addURL.setToolTipText("Add Download");
        addURL.setBorderPainted(false);
        addURL.setContentAreaFilled(false);
        addURL.setFocusPainted(false);
        addURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addURLActionPerformed(evt);
            }
        });

        drag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                dragMouseMoved(evt);
            }
        });
        drag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dragMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1444529174_ark2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("Drag and Drop");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addURL, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addURLtext, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resume, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scheduler, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drag, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drag, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addURLtext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addURL, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scheduler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resume, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setName("All Downloads"); // NOI18N

        jTable1.setFillsViewportHeight(true);
        jTable1.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // Set up ProgressBar as renderer for progress column.
        ProgressRenderer renderer = new ProgressRenderer(0, 100);
        renderer.setStringPainted(true); // show progress text
        renderer.setOpaque(false);
        //renderer.setBackground(Color.BLUE);
        jTable1.setDefaultRenderer(JProgressBar.class, renderer);

        // Set table's row height large enough to fit JProgressBar.
        jTable1.setRowHeight(
            (int) renderer.getPreferredSize().getHeight());
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        SwingUtilities.invokeLater(new fileBrowser(jTabbedPane1));
        //jTabbedPane1.setTitleAt(0,"All Downloads");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addURLActionPerformed
        // TODO add your handling code here:
        URL verifiedUrl = verifyUrl(addURLtext.getText());
        if (verifiedUrl != null) {
            dataM.addDownload(new Download(verifiedUrl,pid));
            addURLtext.setText(""); // reset add text field
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid Download URL", "Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_addURLActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        int selectedRowIdx=jTable1.getSelectedRow();
        if(selectedRowIdx!=-1){
            System.out.println("frame:got idx"+selectedRowIdx);
            Download d=dataM.getDownload(selectedRowIdx);
            d.pause();
        }
    }//GEN-LAST:event_pauseActionPerformed

    private void resumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeActionPerformed
        int selectedRowIdx=jTable1.getSelectedRow();
        Download d=dataM.getDownload(selectedRowIdx);
        d.resume();
    }//GEN-LAST:event_resumeActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        int selectedRowIdx=jTable1.getSelectedRow();
        Download d=dataM.getDownload(selectedRowIdx);
        d.cancel();
    }//GEN-LAST:event_cancelActionPerformed

    private void addURLtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addURLtextActionPerformed
      
    }//GEN-LAST:event_addURLtextActionPerformed

    private void schedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedulerActionPerformed
             new scheduling().setVisible(true);
    }//GEN-LAST:event_schedulerActionPerformed

    private void addURLtextMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addURLtextMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_addURLtextMouseMoved

    private void dragMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragMouseMoved
        drag.setText("");
    }//GEN-LAST:event_dragMouseMoved

    private void dragMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragMouseClicked
         drag.setText("");
    }//GEN-LAST:event_dragMouseClicked
     static URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://")&&!url.toLowerCase().startsWith("https://"))
            return null;
         
        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
         
        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
         
        return verifiedUrl;
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        background thread=new background();
        thread.setDaemon(true);
        thread.start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addURL;
    private javax.swing.JTextField addURLtext;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField drag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pause;
    private javax.swing.JButton resume;
    private javax.swing.JButton scheduler;
    // End of variables declaration//GEN-END:variables
}
