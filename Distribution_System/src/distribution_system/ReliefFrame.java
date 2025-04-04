package distribution_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.HashMap;



public class ReliefFrame extends javax.swing.JFrame {

    public ReliefFrame() {
        initComponents();
        loadResidentsData();
        loadDonationsData();
        loadDonationItems();
        setTitle("RELIEF");
        setLocationRelativeTo(null);
    }
    
    private void loadDonationItems() {
        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT item_name FROM donations")) {

            ItemComboBox.removeAllItems();

            while (rs.next()) {
                ItemComboBox.addItem(rs.getString("item_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDonationsData() {
        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        DefaultTableModel model = (DefaultTableModel) DonationTable.getModel();
        model.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM inventory")) {

            while (rs.next()) {
                model.addRow(new Object[]{
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
    
    private void loadResidentsData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/relief_db", "root", "");

            HashMap<Integer, String> siteMap = new HashMap<>();
            String siteQuery = "SELECT site_id, site_name FROM evacuation_sites";
            PreparedStatement sitePst = conn.prepareStatement(siteQuery);
            ResultSet siteRs = sitePst.executeQuery();
            while (siteRs.next()) {
                siteMap.put(siteRs.getInt("site_id"), siteRs.getString("site_name"));
            }
            siteRs.close();
            sitePst.close();

            String residentQuery = "SELECT name, age, gender, place_of_origin, evacuation_site_id FROM residents";
            PreparedStatement residentPst = conn.prepareStatement(residentQuery);
            ResultSet residentRs = residentPst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (residentRs.next()) {
                int siteId = residentRs.getInt("evacuation_site_id");
                String siteName = siteMap.getOrDefault(siteId, "Unknown"); // Get site name from HashMap

                Object[] row = {
                    residentRs.getString("name"),
                    residentRs.getInt("age"),
                    residentRs.getString("gender"),
                    residentRs.getString("place_of_origin"),
                    siteName
                };
                model.addRow(row);
            }

            residentRs.close();
            residentPst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ItemComboBox = new javax.swing.JComboBox<>();
        QuantityTextField = new javax.swing.JTextField();
        ResidentTextField = new javax.swing.JTextField();
        DistributeButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        DonationTable = new javax.swing.JTable();
        RefreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BALAYAN CENTER");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Item:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quantity:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Resident:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        ItemComboBox.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jPanel1.add(ItemComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 150, 30));

        QuantityTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        QuantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuantityTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(QuantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 150, 30));

        ResidentTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        ResidentTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidentTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(ResidentTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 150, 30));

        DistributeButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        DistributeButton.setText("DISTRIBUTE");
        DistributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DistributeButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DistributeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 120, 30));

        BackButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 400, 120, 30));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Age", "Gender", "Address", "Site"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 450, 150));

        DonationTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        DonationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item Name", "Category", "Quantity", "Expiration Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(DonationTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 450, 160));

        RefreshButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        RefreshButton.setText("REFRESH");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RefreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Main_Dashboard main = new Main_Dashboard();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void QuantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuantityTextFieldActionPerformed
        String selectedItem = (String) ItemComboBox.getSelectedItem();
        String quantityText = QuantityTextField.getText().trim();

        if (selectedItem == null || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an item and enter a quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int quantityToGive;
        try {
            quantityToGive = Integer.parseInt(quantityText);
            if (quantityToGive <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                QuantityTextField.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            QuantityTextField.setText("");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT quantity FROM donations WHERE item_name = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, selectedItem);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int availableStock = rs.getInt("quantity");

                if (quantityToGive > availableStock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock available! Maximum: " + availableStock, "Error", JOptionPane.ERROR_MESSAGE);
                    QuantityTextField.setText("");
                    return;
                }

                JOptionPane.showMessageDialog(this, "Valid quantity entered!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item not found in stock.", "Error", JOptionPane.ERROR_MESSAGE);
                QuantityTextField.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_QuantityTextFieldActionPerformed

    private void ResidentTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResidentTextFieldActionPerformed
        String searchText = ResidentTextField.getText().trim();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a resident's name to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }//GEN-LAST:event_ResidentTextFieldActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        loadResidentsData();
        loadDonationsData();
        loadDonationItems();
    }//GEN-LAST:event_RefreshButtonActionPerformed

    private void DistributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DistributeButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a resident from the table.");
            return;
        }

        String residentName = jTable1.getValueAt(selectedRow, 0).toString();
        String itemName = ItemComboBox.getSelectedItem().toString();
        String quantityText = QuantityTextField.getText().trim();

        if (quantityText.isEmpty() || !quantityText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            return;
        }

        int quantity = Integer.parseInt(quantityText);

        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            // Get resident_id
            String residentQuery = "SELECT resident_id FROM residents WHERE name = ?";
            try (PreparedStatement residentStmt = conn.prepareStatement(residentQuery)) {
                residentStmt.setString(1, residentName);
                ResultSet residentRs = residentStmt.executeQuery();

                if (!residentRs.next()) {
                    JOptionPane.showMessageDialog(this, "Resident not found.");
                    return;
                }
                int residentId = residentRs.getInt("resident_id");

                // Get inventory_id and available stock from inventory table
                String inventoryQuery = "SELECT inventory_id, quantity FROM inventory WHERE item_name = ?";
                try (PreparedStatement inventoryStmt = conn.prepareStatement(inventoryQuery)) {
                    inventoryStmt.setString(1, itemName);
                    ResultSet inventoryRs = inventoryStmt.executeQuery();

                    if (!inventoryRs.next()) {
                        JOptionPane.showMessageDialog(this, "Item not found in stock.");
                        return;
                    }
                    int inventoryId = inventoryRs.getInt("inventory_id");
                    int availableQuantity = inventoryRs.getInt("quantity");

                    if (availableQuantity < quantity) {
                        JOptionPane.showMessageDialog(this, "Not enough stock available.");
                        return;
                    }

                    // Insert into distribution table
                    String insertQuery = "INSERT INTO distribution (resident_id, donation_id, quantity, date_distributed) VALUES (?, ?, ?, NOW())";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, residentId);
                        insertStmt.setInt(2, inventoryId);  // Use inventory_id instead of donation_id
                        insertStmt.setInt(3, quantity);
                        insertStmt.executeUpdate();
                    }

                    // Update stock in inventory table
                    String updateQuery = "UPDATE inventory SET quantity = quantity - ? WHERE inventory_id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, inventoryId);
                        updateStmt.executeUpdate();
                    }

                    conn.commit(); // Commit transaction
                    JOptionPane.showMessageDialog(this, "Item distributed successfully.");
                    loadDonationsData(); // Refresh donations table
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred.");
        }
    }//GEN-LAST:event_DistributeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ReliefFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReliefFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReliefFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReliefFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReliefFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DistributeButton;
    private javax.swing.JTable DonationTable;
    private javax.swing.JComboBox<String> ItemComboBox;
    private javax.swing.JTextField QuantityTextField;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JTextField ResidentTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
