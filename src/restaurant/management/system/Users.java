/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurant.management.system;

//import dao.UserDao;
//import java.util.ArrayList;
//import java.util.Iterator;
//import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import dao.UserDao;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.User;

/**
 *
 * @author ASUS
 */
public class Users extends javax.swing.JFrame {

    /**
     * Creates new form Users
     */
    public Users() {
        initComponents();
        btnUpdateUser.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
    public void getAllRecords(String email){
        DefaultTableModel dtm = (DefaultTableModel) jTableUsers1.getModel();
        dtm.setRowCount(0);
        ArrayList<User>  list = UserDao.getAllRecords(email);
        Iterator<User> itr = list.iterator();
        
        while(itr.hasNext()){
            User userObj = itr.next();
            if(!userObj.getEmail().equals("admin@gmail.com")){
                dtm.addRow(new Object[]{userObj.getId(),userObj.getName(),userObj.getEmail(),userObj.getMobile(),userObj.getAddress(),userObj.getSecurity(),userObj.getAnswer(),userObj.getStatus()} );                
            }
        }
    }
    
    // Method to generate the report
    private void generateReport() {
        Document document = new Document();
        String filePath = "D:\\Downloads\\Customers_Report.pdf"; // Ensure this path is accessible
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            writer.setPageEvent(new HeaderFooterPageEvent());

            document.open();
            addTitlePage(document);
            addContent(document);

            JOptionPane.showMessageDialog(null, "Report Generated Successfully !");
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
        } finally {
            document.close();
        }
    }

    private void addTitlePage(Document document) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLUE.darker());
        Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 14);
        Paragraph title = new Paragraph("Customers Report", titleFont);
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
        float[] columnWidths = {0.5f, 1.5f, 1.5f, 1, 1.5f, 1.5f, 1, 0.6f};
        table.setWidths(columnWidths);

        String[] headers = {"ID", "Name", "Email", "Contact No.", "Address", "Security Question", "Answer", "Status"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, tableHeaderFont));
            BaseColor creamColor = new BaseColor(255, 253, 208);
            cell.setBackgroundColor(creamColor); // Set background color
            table.addCell(cell);
        }

        // Create font for the table content
        Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

        DefaultTableModel model = (DefaultTableModel) jTableUsers1.getModel();
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
                footer.addCell(new Phrase("Restaurant Customers Report - Page " + writer.getPageNumber(), footerFont));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsers1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtAnswer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnUpdateUser = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnDownload = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtSecurity1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1408, 768));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableUsers1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTableUsers1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Mobile", "Address", "Security Question", "Answer", "Status"
            }
        ));
        jTableUsers1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsers1MouseClicked(evt);
            }
        });
        jTableUsers1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTableUsers1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsers1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 720, 380));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generate bill & print.png"))); // NOI18N
        jLabel1.setText("User Management");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ID : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 37, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("00");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 80, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Name : ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 56, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 260, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Email : ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 56, -1));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 260, -1));

        txtMobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 260, -1));

        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 260, -1));

        txtAnswer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 260, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Mobile : ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 65, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Address : ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 71, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Answer :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 580, -1, -1));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 381, 37, -1));

        btnUpdateUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnUpdateUser.setText("Update");
        btnUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdateUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/View Bills & Order Placed Details.png"))); // NOI18N
        jLabel11.setText("Edit User Details");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 270, -1));

        btnAddUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new product.png"))); // NOI18N
        btnAddUser.setText("Add User");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 210, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Search :");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("View Users");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, -1, -1));

        btnDownload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/View Bills & Order Placed Details.png"))); // NOI18N
        btnDownload.setText("Download Reports");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });
        getContentPane().add(btnDownload, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 150, 200, 30));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 30, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Security Question : ");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, -1, -1));

        txtSecurity1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtSecurity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 260, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/full-page-background.PNG"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, -1, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*private void formComponentShown(java.awt.event.ComponentEvent evt) {                                    
        getAllRecords("");
    } */
    
    private void jTableUsers1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsers1MouseClicked
        int index = jTableUsers1.getSelectedRow();
        TableModel model = jTableUsers1.getModel();
        String id = model.getValueAt(index, 0).toString();
        jLabel3.setText(id);
        String name = model.getValueAt(index, 1).toString();
        txtName.setText(name);
        String email = model.getValueAt(index, 2).toString();
        txtEmail.setText(email);
        String mobile = model.getValueAt(index, 3).toString();
        txtMobile.setText(mobile);
        String address = model.getValueAt(index, 4).toString();
        txtAddress.setText(address);
        String security = model.getValueAt(index, 5).toString();
        txtSecurity1.setText(security);
        String answer = model.getValueAt(index, 6).toString();
        txtAnswer.setText(answer);
        
        btnUpdateUser.setEnabled(true);
        btnDelete.setEnabled(true);
        /*ComboBoxTableNo.removeAllItems();
        ComboBoxTableNo.addItem(tableNo);*/
        
        /*ArrayList<User> arrayList = UserDao.getAllRecords();
        Iterator<Reservationtable> reservationtableItr = reservationtableList.iterator();
        while(reservationtableItr.hasNext()){
                Reservationtable reservationtableObj = reservationtableItr.next();
                if(!reservationtableObj.getName().equals(tableNo))
                    ComboBoxTableNo.addItem(reservationtableObj.getName());
            }*/
    }//GEN-LAST:event_jTableUsers1MouseClicked

    private void jTableUsers1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTableUsers1ComponentShown
        // TODO add your handling code here:
        getAllRecords("");
    }//GEN-LAST:event_jTableUsers1ComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        getAllRecords("");
    }//GEN-LAST:event_formComponentShown

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String id = jLabel3.getText();
        int a = JOptionPane.showConfirmDialog(null,"Do yo want to Delete this User ? ","Select",JOptionPane.YES_NO_OPTION);
        if(a==0){
            UserDao.delete(id);
            //setVisible(false);
            new Users().setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        // TODO add your handling code here:
        String id = jLabel3.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String address = txtAddress.getText();
        String security = txtSecurity1.getText();
        String answer = txtAnswer.getText();

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAddress(address);
        user.setSecurity(security);
        user.setAnswer(answer);

        int a = JOptionPane.showConfirmDialog(null, "Do you want to Update this User?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            UserDao.update(user);
            new Users().setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        //setVisible(false);
        new AddUser().setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String email = txtSearch.getText();
        getAllRecords(email);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        // TODO add your handling code here:
        generateReport();
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        int a= JOptionPane.showConfirmDialog(null, "Do you want to Close this ?", "Select", JOptionPane.YES_NO_OPTION);
        if(a==0){
            //setVisible(false);
            new Admin().setVisible(true);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsers1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSecurity1;
    // End of variables declaration//GEN-END:variables
}
