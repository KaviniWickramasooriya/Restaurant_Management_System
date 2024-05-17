/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurant.management.system;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import dao.UserDao;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kavini
 */
public class VerifyUsers extends javax.swing.JFrame {

    /**
     * Creates new form VerifyUsers
     */
    public VerifyUsers() {
        initComponents();
    }
    
    public void getAllRecords(String email){
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        ArrayList<User>  list = UserDao.getAllRecords(email);
        Iterator<User> itr = list.iterator();
        
        while(itr.hasNext()){
            User userObj = itr.next();
            if(!userObj.getEmail().equals("admin@gmail.com")){
                dtm.addRow(new Object[]{userObj.getId(),userObj.getName(),userObj.getEmail(),userObj.getMobile(),userObj.getAddress(),userObj.getSecurity(),userObj.getStatus()} );                
            }
        }
    }
    
     // Method to generate the report
private void generateReport() {
    Document document = new Document();
    String filePath = "D:\\Downloads\\Customer_Report.pdf"; // Ensure this path is accessible
    try {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        writer.setPageEvent(new HeaderFooterPageEvent());

        document.open();
        addTitlePage(document);
        addContent(document);

        JOptionPane.showMessageDialog(null, "Report Generated Successfully!");
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
    } finally {
        document.close();
    }
}

private void addTitlePage(Document document) throws DocumentException {
    Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLUE.darker());
    Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);
    Paragraph title = new Paragraph("Customer Report", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);

    Paragraph subTitle = new Paragraph("Generated on: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), subTitleFont);
    subTitle.setAlignment(Element.ALIGN_CENTER);
    document.add(subTitle);

    document.add(Chunk.NEWLINE);
    document.add(Chunk.NEWLINE);
}

private void addContent(Document document) throws DocumentException {
    Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    PdfPTable table = new PdfPTable(7); // 7 columns instead of 8 to match column count
    table.setWidthPercentage(100);

    // Define the widths for each column
    float[] columnWidths = {0.5f, 1.5f, 1.5f, 1, 1, 1.5f, 0.8f};
    table.setWidths(columnWidths);

    String[] headers = {"ID", "Name", "Email", "Contact No.", "Address", "Security Question", "Status"};
    for (String header : headers) {
        PdfPCell cell = new PdfPCell(new Phrase(header, tableHeaderFont));
        BaseColor creamColor = new BaseColor(255, 253, 208);
        cell.setBackgroundColor(creamColor); // Set background color
        table.addCell(cell);
    }

    // Create font for the table content
    Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        for (int j = 0; j < model.getColumnCount(); j++) {
            // Create PdfPCell with table content font
            PdfPCell cell = new PdfPCell(new Phrase(model.getValueAt(i, j).toString(), tableFont));
            if (j == 2) { // Email field
                cell.setFixedHeight(20f); // Adjust the height for the email field as needed
            }
            table.addCell(cell);
        }
    }
    document.add(table);
}

private static class HeaderFooterPageEvent extends PdfPageEventHelper {
    private final Font footerFont = new Font(Font.FontFamily.HELVETICA, 6);

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable footer = new PdfPTable(1);
        try {
            footer.setWidths(new int[]{24});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            footer.addCell(new Phrase("Restaurant Customer Report - Page " + writer.getPageNumber(), footerFont));
            footer.writeSelectedRows(0, -1, 34, 50, writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnDownload = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1406, 650));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/verify users.png"))); // NOI18N
        jLabel1.setText("Verify Users");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 230, 50));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 60, 35, 35));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Search");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 110, 30));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 330, 30));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Mobile", "Address", "Security Question", "Status"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 1010, 300));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Click On Row To Change Status");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 590, 290, 39));

        btnDownload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/View Bills & Order Placed Details.png"))); // NOI18N
        btnDownload.setText("Download Reports");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });
        getContentPane().add(btnDownload, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 150, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/full-page-background.PNG"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1370, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        int a= JOptionPane.showConfirmDialog(null, "Do you want to Close this ?", "Select", JOptionPane.YES_NO_OPTION);
        if(a==0){
            //setVisible(false);
            new Admin().setVisible(true);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        getAllRecords("");
    }//GEN-LAST:event_formComponentShown

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String email = txtSearch.getText();
        getAllRecords(email);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String email = model.getValueAt(index, 2).toString();
        String status = model.getValueAt(index, 6).toString();
        if(status.equals("true")){
            status="false";
        }else{
            status="true";
        }
        int a = JOptionPane.showConfirmDialog(null, "Do you want to change Status of "+email+"","Select",JOptionPane.YES_NO_OPTION);
        if(a==0){
            UserDao.changeStatus(email, status);
            //setVisible(false);
            new VerifyUsers().setVisible(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        // TODO add your handling code here:
        generateReport();
    }//GEN-LAST:event_btnDownloadActionPerformed

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
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifyUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDownload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
