/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import carrental4.createUserForm;
import carrental4.loginForm;
import config.Session;
import config.dbConnector;
import java.awt.Color;
import static java.awt.Color.red;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Bentastic
 */
public class adminDashboard extends javax.swing.JFrame {

  
    /**
     * Creates new form adminDashboard
     */
    public adminDashboard() {
        initComponents();
        
      

    }

    Color navcolor = new Color(255,255,255);
    Color hovercolor = new Color(153,204,255);

 private void loadLogs() {
    Session sess = Session.getInstance();
    dbConnector connector = new dbConnector();

    try (Connection con = dbConnector.getConnection()) {
        System.out.println("1");

        // Update 'Pending' log_status to 'Active' for recent logins
        String updateQuery = "UPDATE tbl_log SET log_status = 'Active' WHERE log_status = 'Pending'";
        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            System.out.println("2");
            updateStmt.executeUpdate();
        }

        // Fetch updated logs including logout time and description
        String selectQuery = "SELECT l.log_id, l.u_username, l.login_time, l.logout_time, l.u_type, " +
                             "CASE WHEN u.u_username IS NULL THEN 'Invalid User' ELSE l.log_status END AS log_status, " +
                             "l.log_description " +
                             "FROM tbl_log l LEFT JOIN tbl_users u ON l.u_username = u.u_username " +
                             "ORDER BY l.login_time DESC";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {
            System.out.println("3");

            // Now with 7 columns including Description
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Log ID", "Username", "Login Time", "Logout Time", "User Type", "Status", "Description"}, 0
            );

            while (rs.next()) {
                System.out.println("4");

                model.addRow(new Object[]{
                        rs.getInt("log_id"),
                        rs.getString("u_username"),
                        rs.getTimestamp("login_time"),
                        rs.getTimestamp("logout_time"),
                        rs.getString("u_type"),
                        rs.getString("log_status"),
                        rs.getString("log_description") // ✅ Added this line
                });
            }

            logstbl.setModel(model);
        }

    } catch (SQLException ex) {
        System.out.println("5");
        JOptionPane.showMessageDialog(null, "Error loading logs: " + ex.getMessage());
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


  
  private void logoutUser(String username) {
    dbConnector connector = new dbConnector();
    try (Connection con = dbConnector.getConnection()) {
        
        // Update log_status to "Inactive" and set logout_time
        String updateQuery = "UPDATE tbl_log SET log_status = 'Inactive', logout_time = NOW() " +
                             "WHERE u_username = ? AND log_status = 'Active'";
        
        try (PreparedStatement stmt = con.prepareStatement(updateQuery)) {
            stmt.setString(1, username);
            int updatedRows = stmt.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "User " + username + " has logged out successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No active session found for " + username);
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error logging out: " + ex.getMessage());
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
        jLabel3 = new javax.swing.JLabel();
        acc_fname = new javax.swing.JLabel();
        acc_lname = new javax.swing.JLabel();
        r_name = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        a_name = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logstbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ADMIN DASHBOARD");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("LOGOUT");
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
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-50.png"))); // NOI18N
        jLabel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 33, 73, -1));

        acc_fname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        acc_fname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_fname.setText("ADMIN");
        jPanel2.add(acc_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 98, 97, -1));

        acc_lname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        acc_lname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_lname.setText("ADMIN");
        jPanel2.add(acc_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 122, 97, -1));

        r_name.setBackground(new java.awt.Color(255, 255, 255));
        r_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                r_nameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                r_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                r_nameMouseExited(evt);
            }
        });
        r_name.setLayout(null);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-50.png"))); // NOI18N
        r_name.add(jLabel9);
        jLabel9.setBounds(20, 10, 50, 50);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("USERS");
        r_name.add(jLabel10);
        jLabel10.setBounds(20, 70, 50, 20);

        jPanel2.add(r_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 100, 100));

        a_name.setBackground(new java.awt.Color(255, 255, 255));
        a_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                a_nameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                a_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                a_nameMouseExited(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-car-rental-50.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("UPDATE CARS");

        javax.swing.GroupLayout a_nameLayout = new javax.swing.GroupLayout(a_name);
        a_name.setLayout(a_nameLayout);
        a_nameLayout.setHorizontalGroup(
            a_nameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a_nameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        a_nameLayout.setVerticalGroup(
            a_nameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a_nameLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        jPanel2.add(a_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 100, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        logstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "log_id", "u_username", "login_time", "u_type", "log_status", "log_description"
            }
        ));
        jScrollPane1.setViewportView(logstbl);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
          Session sess = Session.getInstance();
    if (sess.getUid() != 0) {
        logoutUser(sess.getUsername());  // Log out the current user
    }

    loginForm loginFrame = new loginForm();
    JOptionPane.showMessageDialog(null, "Log-out Success!");
    loginFrame.setVisible(true);
    this.dispose();

    }//GEN-LAST:event_jLabel2MouseClicked

    private void r_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_nameMouseClicked
        usersForm usf = new usersForm();
        usf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_r_nameMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       Session sess = Session.getInstance();
    if (sess.getUid() == 0) {
        JOptionPane.showMessageDialog(null, "No account, Login First!"); 
        loginForm lf = new loginForm();
        lf.setVisible(true);
        this.dispose();
    }
    
    acc_fname.setText("" + sess.getFname());
    acc_lname.setText("" + sess.getLname());

  
    loadLogs();
    }//GEN-LAST:event_formWindowActivated

    private void r_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_nameMouseEntered
        r_name.setBackground(hovercolor);
    }//GEN-LAST:event_r_nameMouseEntered

    private void r_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_r_nameMouseExited
        r_name.setBackground(navcolor);
    }//GEN-LAST:event_r_nameMouseExited

    private void a_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a_nameMouseClicked
        carForm cor = new carForm();
        cor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_a_nameMouseClicked

    private void a_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a_nameMouseEntered
        a_name.setBackground(hovercolor);
    }//GEN-LAST:event_a_nameMouseEntered

    private void a_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a_nameMouseExited
        a_name.setBackground(navcolor);
    }//GEN-LAST:event_a_nameMouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
   
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new adminDashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel a_name;
    private javax.swing.JLabel acc_fname;
    private javax.swing.JLabel acc_lname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logstbl;
    private javax.swing.JPanel r_name;
    // End of variables declaration//GEN-END:variables
}
