package distribution_system;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class ResidentFrame extends javax.swing.JFrame {

    private void populateRoomComboBox() {
        RoomComboBox.removeAllItems();
        for (int i = 1; i <= 15; i++) {
            RoomComboBox.addItem(String.valueOf(i)); // Add room numbers as strings
        }
    }
    
    private void populateEvacuationComboBox() {
        EvacuationComboBox.removeAllItems();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/relief_db", "root", "");

            String sql = "SELECT DISTINCT site_name FROM evacuation_sites";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            boolean hasData = false;
            while (rs.next()) {
                EvacuationComboBox.addItem(rs.getString("site_name"));
                hasData = true;
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(this, "No evacuation sites found in database.", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading evacuation locations: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    
    private void populateGenderComboBox() {
        GenderComboBox.removeAllItems();
        GenderComboBox.addItem("Male");
        GenderComboBox.addItem("Female");
    }
    
    public ResidentFrame() {
        initComponents();
        loadResidents();
        populateRoomComboBox();
        populateEvacuationComboBox();
        populateGenderComboBox();
        setTitle("RESIDENT");
        setLocationRelativeTo(null);
    }

    public void loadResidents() {
        String url = "jdbc:mysql://localhost:3306/relief_db";
        String user = "root";
        String password = "";  

        String query = "SELECT name, age, gender, place_of_origin, evacuation_site_id, room_number FROM residents";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel model = (DefaultTableModel) ResidentTable.getModel();
            model.setRowCount(0); 

            while (rs.next()) {
                Object[] row = {
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("place_of_origin"),
                    rs.getString("evacuation_site_id"),
                    rs.getString("room_number")
                };
                model.addRow(row);
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
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResidentTable = new javax.swing.JTable();
        RemoveButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        RefreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        AddressTextField = new javax.swing.JTextField();
        ResidentTextField = new javax.swing.JTextField();
        AgeTextField = new javax.swing.JTextField();
        GenderComboBox = new javax.swing.JComboBox<>();
        EvacuationComboBox = new javax.swing.JComboBox<>();
        RoomComboBox = new javax.swing.JComboBox<>();
        AddResidentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BALAYAN CENTER");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        ResidentTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        ResidentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Resident Name", "Age", "Gender", "Address", "Evauation Site", "Room Assignment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ResidentTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 320));

        RemoveButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        RemoveButton.setText("REMOVE RESIDENT");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RemoveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 150, 30));

        BackButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 150, 30));

        RefreshButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        RefreshButton.setText("REFRESH");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RefreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 150, 30));

        jTabbedPane1.addTab("List of Residents", jPanel1);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BALAYAN CENTER");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Age:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Address:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Evacuation Site:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Room Assignment:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gender:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Resident Name:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        AddressTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 150, 30));

        ResidentTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(ResidentTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 150, 30));

        AgeTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(AgeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 150, 30));

        GenderComboBox.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(GenderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 150, 30));

        EvacuationComboBox.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(EvacuationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 150, 30));

        RoomComboBox.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(RoomComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 150, 30));

        AddResidentButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        AddResidentButton.setText("ADD RESIDENT");
        AddResidentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddResidentButtonActionPerformed(evt);
            }
        });
        jPanel2.add(AddResidentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 150, 30));

        jTabbedPane1.addTab("Add Resident", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Main_Dashboard main = new Main_Dashboard();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void AddResidentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddResidentButtonActionPerformed

        String residentName = ResidentTextField.getText().trim();
        String ageText = AgeTextField.getText().trim();
        String gender = (String) GenderComboBox.getSelectedItem();
        String address = AddressTextField.getText().trim();
        String selectedSite = (String) EvacuationComboBox.getSelectedItem();
        String selectedRoom = (String) RoomComboBox.getSelectedItem();

        // Check if any field is empty
        if (residentName.isEmpty() || ageText.isEmpty() || gender == null || 
            address.isEmpty() || selectedSite == null || selectedRoom == null) {

            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return; // Stop if validation fails
        }

        // Convert age to integer
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age! Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Connect to database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/relief_db", "root", "");

            // Get site ID from site name
            String siteQuery = "SELECT site_id FROM evacuation_sites WHERE site_name = ?";
            pst = con.prepareStatement(siteQuery);
            pst.setString(1, selectedSite);
            rs = pst.executeQuery();

            int evacuationSiteId = -1;
            if (rs.next()) {
                evacuationSiteId = rs.getInt("site_id");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid evacuation site selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the room is full (limit 5 residents)
            String roomCheckQuery = "SELECT COUNT(*) AS count FROM residents WHERE evacuation_site_id = ? AND room_number = ?";
            pst = con.prepareStatement(roomCheckQuery);
            pst.setInt(1, evacuationSiteId);
            pst.setString(2, selectedRoom);
            rs = pst.executeQuery();

            if (rs.next() && rs.getInt("count") >= 5) {
                JOptionPane.showMessageDialog(this, "Room " + selectedRoom + " is full! Please select another room.", "Room Full", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert resident into database
            String sql = "INSERT INTO residents (name, age, gender, place_of_origin, evacuation_site_id, room_number) VALUES (?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, residentName);
            pst.setInt(2, age);
            pst.setString(3, gender);
            pst.setString(4, address);
            pst.setInt(5, evacuationSiteId);
            pst.setString(6, selectedRoom);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Resident added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                ResidentTextField.setText("");
                AgeTextField.setText("");
                GenderComboBox.setSelectedIndex(0);
                AddressTextField.setText("");
                EvacuationComboBox.setSelectedIndex(0);
                RoomComboBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add resident.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_AddResidentButtonActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        loadResidents();
    }//GEN-LAST:event_RefreshButtonActionPerformed

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        int selectedRow = ResidentTable.getSelectedRow(); // Get selected row index

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a resident to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to remove this resident?", 
            "Confirm Removal", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) ResidentTable.getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Resident removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_RemoveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ResidentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddResidentButton;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JTextField AgeTextField;
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox<String> EvacuationComboBox;
    private javax.swing.JComboBox<String> GenderComboBox;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JTable ResidentTable;
    private javax.swing.JTextField ResidentTextField;
    private javax.swing.JComboBox<String> RoomComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
