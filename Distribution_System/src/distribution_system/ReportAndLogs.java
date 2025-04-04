package distribution_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportAndLogs extends javax.swing.JFrame {

    public ReportAndLogs() {
        initComponents();
        loadInventoryData();
        loadDistributionData();
        setTitle("LOGS AND REPORTS");
        setLocationRelativeTo(null);
    }

    private void loadInventoryData() {
    String url = "jdbc:mysql://localhost:3306/relief_db";
    String user = "root";
    String password = "";

    String query = "SELECT " +
                   "SUM(CASE WHEN LOWER(category) = 'food' THEN quantity ELSE 0 END) AS food, " +
                   "SUM(CASE WHEN LOWER(category) = 'clothing' THEN quantity ELSE 0 END) AS clothing, " +
                   "SUM(CASE WHEN LOWER(category) = 'drink' THEN quantity ELSE 0 END) AS drinks " +
                   "FROM inventory";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) InventoryTable.getModel();
        model.setRowCount(0);

        if (rs.next()) {
            int foodQty = rs.getInt("food");
            int clothingQty = rs.getInt("clothing");
            int drinksQty = rs.getInt("drinks");

            model.addRow(new Object[]{foodQty, clothingQty, drinksQty});
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private void loadDistributionData() {
        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";

        String distributionQuery = "SELECT distribution_id, donation_id, resident_id, quantity, date_distributed FROM distribution ORDER BY date_distributed DESC";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(distributionQuery);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) DistributionTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int distributionId = rs.getInt("distribution_id");
                int donationId = rs.getInt("donation_id");
                int residentId = rs.getInt("resident_id");
                int quantity = rs.getInt("quantity");
                Date dateDistributed = rs.getDate("date_distributed");

                String itemName = "";
                String category = "";
                String donationQuery = "SELECT item_name, category FROM inventory WHERE inventory_id = ?";
                try (PreparedStatement donationPst = con.prepareStatement(donationQuery)) {
                    donationPst.setInt(1, donationId);
                    try (ResultSet donationRs = donationPst.executeQuery()) {
                        if (donationRs.next()) {
                            itemName = donationRs.getString("item_name");
                            category = donationRs.getString("category");
                        }
                    }
                }
                
                String residentName = "";
                String residentQuery = "SELECT name FROM residents WHERE resident_id = ?";
                try (PreparedStatement resPst = con.prepareStatement(residentQuery)) {
                    resPst.setInt(1, residentId);
                    try (ResultSet resRs = resPst.executeQuery()) {
                        if (resRs.next()) {
                            residentName = resRs.getString("name");
                        }
                    }
                }

                // Add row to table
                model.addRow(new Object[]{residentName, itemName, category, quantity, dateDistributed});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        DistributionTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BALAYAN CENTER");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        InventoryTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "FOODS", "CLOTHES", "DRINKS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(InventoryTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 710, 60));

        DistributionTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        DistributionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Resident Name", "Item Name", "Category ", "Quantity", "Date Distributed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(DistributionTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 710, 330));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DISTRIBUTION LOGS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INVENTORY");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        BackButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Main_Dashboard main = new Main_Dashboard();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ReportAndLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportAndLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportAndLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportAndLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportAndLogs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTable DistributionTable;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
