/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package distribution_system;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class DonationFrame extends javax.swing.JFrame {

    public DonationFrame() {
        initComponents();
        loadDonationsData();
        setupCategoryComboBox();
        setTitle("DONATION");
        setLocationRelativeTo(null);
    }
    

    private void setupCategoryComboBox() {
        CategoryComboBox.removeAllItems();
        CategoryComboBox.addItem("Food");
        CategoryComboBox.addItem("Drink");
        CategoryComboBox.addItem("Clothing");
    }
    
    
    private void loadDonationsData() {
        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        DefaultTableModel model = (DefaultTableModel) DonationTable.getModel();
        model.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM donations")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("donor_name"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getString("expiration_date")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DonationTable = new javax.swing.JTable();
        BackButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        RefreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CategoryComboBox = new javax.swing.JComboBox<>();
        ItemTextField = new javax.swing.JTextField();
        QuantityTextField = new javax.swing.JTextField();
        DonorTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ExpirationDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DonationTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        DonationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Donor Name", "Item Name", "Category ", "Quantity", "Expiration Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(DonationTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 590, 320));

        BackButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 120, 30));

        DeleteButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        DeleteButton.setText("DELETE");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 120, 30));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BALAYAN CENTER");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        RefreshButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        RefreshButton.setText("Refresh");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RefreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 120, 30));

        jTabbedPane1.addTab("Donations ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Item Name:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 100, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Category:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 100, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Expiration Date:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 100, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Donor Name:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 100, -1));
        jPanel2.add(CategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 150, 30));
        jPanel2.add(ItemTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 150, 30));
        jPanel2.add(QuantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 150, 30));
        jPanel2.add(DonorTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 150, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 100, -1));

        AddButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        AddButton.setText("ADD DONATION");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });
        jPanel2.add(AddButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 150, 30));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BALAYAN CENTER");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));
        jPanel2.add(ExpirationDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 150, 30));

        jTabbedPane1.addTab("Add Donation", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Main_Dashboard main = new Main_Dashboard();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        int selectedRow = DonationTable.getSelectedRow();

        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete this item?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) { 
                DefaultTableModel model = (DefaultTableModel) DonationTable.getModel();
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        if (DonorTextField.getText().isEmpty() || 
        ItemTextField.getText().isEmpty() || 
        QuantityTextField.getText().isEmpty() || 
        CategoryComboBox.getSelectedIndex() == -1) {

        JOptionPane.showMessageDialog(this, "Please fill in all fields before adding a donation.", "Warning", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    String donor = DonorTextField.getText().trim();
    String item = ItemTextField.getText().trim();
    String quantityStr = QuantityTextField.getText().trim();
    String category = CategoryComboBox.getSelectedItem().toString().trim();

    // Ensure category matches ENUM values
    if (!category.equals("Food") && !category.equals("Drink") && !category.equals("Clothing")) {
        JOptionPane.showMessageDialog(this, "Invalid category! Please select 'Food', 'Drink', or 'Clothing'.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int quantity;
    try {
        quantity = Integer.parseInt(quantityStr);
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid quantity! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String expiration = null;
    if (ExpirationDateChooser.getDate() != null) {
        expiration = sdf.format(ExpirationDateChooser.getDate());
    }

    if ((category.equals("Food") || category.equals("Drink")) && expiration == null) {
        JOptionPane.showMessageDialog(this, "Expiration date is required for food and drinks.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Connection conn = null;
    PreparedStatement pstDonation = null;
    PreparedStatement pstInventory = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/relief_db", "root", "");
        conn.setAutoCommit(false); // Prevent partial insertions

        // Insert into donations table
        String sqlDonation = "INSERT INTO donations (donor_name, item_name, quantity, category, expiration_date) VALUES (?, ?, ?, ?, ?)";
        pstDonation = conn.prepareStatement(sqlDonation);
        pstDonation.setString(1, donor);
        pstDonation.setString(2, item);
        pstDonation.setInt(3, quantity);
        pstDonation.setString(4, category);
        if (expiration != null) {
            pstDonation.setString(5, expiration);
        } else {
            pstDonation.setNull(5, java.sql.Types.NULL);
        }
        pstDonation.executeUpdate();

        // Insert into inventory table
        String sqlInventory = "INSERT INTO inventory (item_name, category, quantity, expiration_date) VALUES (?, ?, ?, ?)";
        pstInventory = conn.prepareStatement(sqlInventory);
        pstInventory.setString(1, item);
        pstInventory.setString(2, category);
        pstInventory.setInt(3, quantity);
        if (expiration != null) {
            pstInventory.setString(4, expiration);
        } else {
            pstInventory.setNull(4, java.sql.Types.NULL);
        }
        pstInventory.executeUpdate();

        conn.commit(); // Commit transaction
        JOptionPane.showMessageDialog(this, "Donation added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear form fields
        DonorTextField.setText("");
        ItemTextField.setText("");
        QuantityTextField.setText("");
        CategoryComboBox.setSelectedIndex(-1);
        ExpirationDateChooser.setDate(null);

    } catch (SQLException ex) {
        if (conn != null) {
            try {
                conn.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        try {
            if (pstDonation != null) pstDonation.close();
            if (pstInventory != null) pstInventory.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_AddButtonActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        loadDonationsData();
    }//GEN-LAST:event_RefreshButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DonationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DonationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox<String> CategoryComboBox;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTable DonationTable;
    private javax.swing.JTextField DonorTextField;
    private com.toedter.calendar.JDateChooser ExpirationDateChooser;
    private javax.swing.JTextField ItemTextField;
    private javax.swing.JTextField QuantityTextField;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
