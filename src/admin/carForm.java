/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.Session;
import config.dbConnector;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.red;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Bentastic
 */
public class carForm extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard
     */
    public carForm() {
        initComponents();
        displayData();
    }

        boolean checkadd = true;
        Color navcolor = new Color(0,102,102);
        Color hovercolor = new Color(153,153,255);
    
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String path2 = null;
        
        public String destination = ""; 
        File selectedFile;
        public String oldpath;
        public String path;
    
        
 
        
    public void displayData() {
        try {
            dbConnector dbc = new dbConnector();
            // Update the table model with the new result set
            try ( // Select only from tbl_cars
                    ResultSet rs = dbc.getData("SELECT c_id, c_name, c_model, c_price, c_quantity, c_status FROM tbl_cars")) {
                // Update the table model with the new result set
                carsTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
    
   public static void insertCars() {
        Connection connector = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get a connection to the database
            connector = dbConnector.getConnection();  // Use dbConnector class to get connection

            // SQL query to insert the data
            String sql = "INSERT INTO tbl_cars (c_name, c_model, c_price, c_quantity, c_status) "
                    + "VALUES (?, ?, ?, ?, ?)";

            // Prepare the statement
            preparedStatement = connector.prepareStatement(sql);

            // Set the values for the placeholders
            preparedStatement.setString(1, "Car 1");           // Set c_name
            preparedStatement.setString(2, "Model A");         // Set c_model
            preparedStatement.setDouble(3, 25000.00);          // Set c_price
            preparedStatement.setInt(4, 10);                   // Set c_quantity
            preparedStatement.setString(5, "Available");       // Set c_status

            // Execute the insert
            preparedStatement.executeUpdate();

            // Insert the second car
            preparedStatement.setString(1, "Car 2");
            preparedStatement.setString(2, "Model B");
            preparedStatement.setDouble(3, 30000.00);
            preparedStatement.setInt(4, 5);
            preparedStatement.setString(5, "Available");

            // Execute the second insert
            preparedStatement.executeUpdate();

            System.out.println("Cars inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connector != null) connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   }
   
    public void logCarAddition(String carName) {
    try (Connection con = dbConnector.getConnection()) {
        Session sess = Session.getInstance();

        String insertLogQuery = "INSERT INTO tbl_log (u_id, u_username, login_time, u_type, log_status, log_description) " +
                                "VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(insertLogQuery)) {
            pst.setInt(1, sess.getUid()); // u_id
            pst.setString(2, sess.getUsername()); // u_username
            pst.setString(3, sess.getType()); // u_type
            pst.setString(4, "Active"); // log_status
           pst.setString(5, sess.getUsername() + " added a new car: " + carName);


            pst.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Failed to log car addition: " + e.getMessage());
    }
}
    public void logCarDeletion(String carName) {
    try (Connection con = dbConnector.getConnection()) {
        Session sess = Session.getInstance();
        String insertLogQuery = "INSERT INTO tbl_log (u_id, u_username, login_time, u_type, log_status, log_description) " +
                                "VALUES (?, ?, CURRENT_TIMESTAMP, ?, 'Active', ?)";
        
        try (PreparedStatement pst = con.prepareStatement(insertLogQuery)) {
            pst.setInt(1, sess.getUid());
            pst.setString(2, sess.getUsername());
            pst.setString(3, sess.getType());
            pst.setString(4, "Admin deleted a car: " + carName);

            pst.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Failed to log car deletion: " + e.getMessage());
    }
}

    public void logCarUpdate(String carName) {
    try (Connection con = dbConnector.getConnection()) {
        Session sess = Session.getInstance();
        String insertLogQuery = "INSERT INTO tbl_log (u_id, u_username, login_time, u_type, log_status, log_description) " +
                                "VALUES (?, ?, CURRENT_TIMESTAMP, ?, 'Active', ?)";

        try (PreparedStatement pst = con.prepareStatement(insertLogQuery)) {
            pst.setInt(1, sess.getUid());
            pst.setString(2, sess.getUsername());
            pst.setString(3, sess.getType());
            pst.setString(4, "Admin updated car: " + carName); // ✅ Log details

            pst.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Failed to log car update: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        acc_id = new javax.swing.JLabel();
        acc_name1 = new javax.swing.JLabel();
        aa = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carsTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cid = new javax.swing.JTextField();
        cname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cprice = new javax.swing.JLabel();
        cm = new javax.swing.JTextField();
        cstat = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        update = new javax.swing.JButton();
        cprice1 = new javax.swing.JLabel();
        cp = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        cprice2 = new javax.swing.JLabel();
        adds = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAR FORM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BACK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 595, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-50.png"))); // NOI18N
        jLabel11.setBorder(new javax.swing.border.MatteBorder(null));

        acc_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        acc_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_id.setText("ID");

        acc_name1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        acc_name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_name1.setText("USERS");
        acc_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_name1MouseClicked(evt);
            }
        });

        aa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aa.setText("Current User:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(acc_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(aa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(acc_name1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        carsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        carsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(carsTable);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Car ID:");

        cid.setEnabled(false);
        cid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Name:");

        cprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cprice.setText("Car Model:");

        cstat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Out of Stock" }));
        cstat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cstatActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("User Status:");

        clear.setBackground(new java.awt.Color(0, 102, 102));
        clear.setText("CLEAR");
        clear.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(0, 102, 102));
        update.setText("UPDATE");
        update.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        cprice1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cprice1.setText("Car Price:");

        cprice2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cprice2.setText("Change Quantity:");

        adds.setBackground(new java.awt.Color(0, 102, 102));
        adds.setText("ADD");
        adds.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        adds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(0, 102, 102));
        delete.setText("DELETE");
        delete.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(adds, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(cprice2)
                            .addComponent(cprice1)
                            .addComponent(cprice)
                            .addComponent(jLabel7))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cstat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cm, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adds, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cprice))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cprice1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cprice2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cstat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        adminDashboard ds = new adminDashboard();
        ds.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
        acc_id.setText(""+sess.getUid());
        acc_name1.setText(""+sess.getFname());
        
    }//GEN-LAST:event_formWindowActivated

    private void cidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cidActionPerformed

    private void cstatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cstatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cstatActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
    checkadd = true;
    cid.setText("");
    cname.setText("");
    cm.setText("");
    cp.setText("");
    cstat.setSelectedIndex(0);
            
    
    }//GEN-LAST:event_clearActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
// Ensure destination and oldpath are initialized with default values
String defaultPath = "src/usersimages/";
    if (destination == null || destination.isEmpty()) destination = defaultPath;
    if (oldpath == null || oldpath.isEmpty()) oldpath = defaultPath;

    if (cid.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select a car first!");
        return;
    }

    if (cname.getText().isEmpty() || cm.getText().isEmpty() || cp.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Car name, model, and price are required!");
        return;
    }

    dbConnector dbc = new dbConnector();
    int currentQty = 0;
    String existingImagePath = "";

    try {
        ResultSet rs = dbc.getData("SELECT c_quantity, c_image FROM tbl_cars WHERE c_id = '" + cid.getText() + "'");
        if (rs.next()) {
            currentQty = rs.getInt("c_quantity"); // FIXED column name
            existingImagePath = rs.getString("c_image");
        }
        rs.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }

    // Quantity and status logic
    int qty = currentQty;
    if (!quantity.getText().isEmpty()) {
        qty = Integer.parseInt(quantity.getText());
    }
    String status = (qty < 1) ? "Out of Stock" : "Available"; // or use: cstat.getSelectedItem().toString()

    String c_image = existingImagePath;

    // Build update query
    StringBuilder updateQuery = new StringBuilder("UPDATE tbl_cars SET ");
    updateQuery.append("c_name = '").append(cname.getText()).append("', ");
    updateQuery.append("c_model = '").append(cm.getText()).append("', ");
    updateQuery.append("c_price = '").append(cp.getText()).append("', ");
    updateQuery.append("c_status = '").append(status).append("', ");
    updateQuery.append("c_image = '").append(c_image).append("'");

    if (!quantity.getText().isEmpty()) {
        updateQuery.append(", c_quantity = '").append(quantity.getText()).append("'");
    }

    updateQuery.append(" WHERE c_id = '").append(cid.getText()).append("'");

    dbc.updateData(updateQuery.toString());

    JOptionPane.showMessageDialog(null, "Car record updated!");
    displayData();
    checkadd = true;

    // Reset form
    cid.setText("");
    cname.setText("");
    cm.setText("");
    cp.setText("");
    quantity.setText("");
    cstat.setSelectedIndex(0);
    adds.setEnabled(true);
    adds.setForeground(Color.BLACK);
    
    logCarUpdate(cname.getText());


    // Stay on same page; remove dashboard redirect unless needed
    // adminDashboard ad = new adminDashboard();
    // ad.setVisible(true);
    // this.dispose();
    }//GEN-LAST:event_updateActionPerformed

    private void carsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carsTableMouseClicked
  int rowIndex = carsTable.getSelectedRow();

if (rowIndex < 0) {
    JOptionPane.showMessageDialog(null, "Please select a car to load.");
} else {
    try {
        dbConnector dbc = new dbConnector();
        TableModel tbl = carsTable.getModel();
        String selectedCarId = tbl.getValueAt(rowIndex, 0).toString(); // Make sure column 0 is c_id

        ResultSet rs = dbc.getData("SELECT * FROM tbl_cars WHERE c_id = '" + selectedCarId + "'");

        if (rs.next()) {
            cid.setText(rs.getString("c_id"));
            cname.setText(rs.getString("c_name"));
            cm.setText(rs.getString("c_model"));
            cp.setText(rs.getString("c_price"));
            cstat.setSelectedItem(rs.getString("c_status"));
            quantity.setText(rs.getString("c_quantity")); // <-- Don't forget this!

            // Optional: highlight update mode
            adds.setEnabled(false); // disable add
            adds.setForeground(Color.RED); // highlight add as inactive
            checkadd = false;
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_carsTableMouseClicked

    private void addsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsActionPerformed
 if (checkadd) {
        if (cname.getText().isEmpty() || cm.getText().isEmpty() || cp.getText().isEmpty() || quantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields including quantity are required!");
        } else { 
            dbConnector dbc = new dbConnector();

            // Insert new car into tbl_cars
            dbc.insertData("INSERT INTO tbl_cars (c_name, c_model, c_price, c_status, c_image, c_quantity) " +
                           "VALUES ('" + cname.getText() + "','" + cm.getText() + "','" + cp.getText() + "','" + cstat.getSelectedItem() + "','', '" + quantity.getText() + "')");

            // ✅ Log the action
            logCarAddition(cname.getText());  // <-- Add this line to log

            JOptionPane.showMessageDialog(null, "Successfully Added!");
            displayData();
            checkadd = true;

            // Clear fields
            cid.setText("");
            cname.setText("");
            cm.setText("");
            cp.setText("");
            cstat.setSelectedIndex(0);
            quantity.setText("");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Clear the field first!");
    }

    }//GEN-LAST:event_addsActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
  int selectedRow = carsTable.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select a row to delete!");
    return;
}

int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
if (confirm == JOptionPane.YES_OPTION) {
    String carIdToDelete = carsTable.getValueAt(selectedRow, 0).toString(); // Assuming 0 = car ID
    String carName = carsTable.getValueAt(selectedRow, 1).toString();       // Assuming 1 = car name

    dbConnector dbc = new dbConnector();
    String deleteQuery = "DELETE FROM tbl_cars WHERE c_id = '" + carIdToDelete + "'";
    dbc.updateData(deleteQuery);

    // ✅ Log the deletion
    logCarDeletion(carName);

    // Remove from table view
    DefaultTableModel model = (DefaultTableModel) carsTable.getModel();
    model.removeRow(selectedRow);

    JOptionPane.showMessageDialog(null, "Record deleted successfully!");
}

    }//GEN-LAST:event_deleteActionPerformed

    private void acc_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_name1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_acc_name1MouseClicked

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
            java.util.logging.Logger.getLogger(carForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(carForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(carForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(carForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new carForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aa;
    private javax.swing.JLabel acc_id;
    private javax.swing.JLabel acc_name1;
    private javax.swing.JButton adds;
    private javax.swing.JTable carsTable;
    public javax.swing.JTextField cid;
    private javax.swing.JButton clear;
    public javax.swing.JTextField cm;
    public javax.swing.JTextField cname;
    public javax.swing.JTextField cp;
    private javax.swing.JLabel cprice;
    private javax.swing.JLabel cprice1;
    private javax.swing.JLabel cprice2;
    public javax.swing.JComboBox<String> cstat;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField quantity;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
