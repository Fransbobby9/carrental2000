/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrental4;

import admin.usersForm;
import static carrental4.regForm.email;
import static carrental4.regForm.username;
import config.Session;
import config.dbConnector;
import config.passwordHasher;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Bentastic
 */
public class createUserForm extends javax.swing.JFrame {

    /**
     * Creates new form createUserForm
     */
    public createUserForm() {
        initComponents();
    }
    
    
    
       private String userId; // Declare userId at the class level

    public void setUserId(String id) {
        this.userId = id; // Store the user ID for later use
    }
    
   
     
        public static String mail,usname;
        
    public String destination = ""; 
    File selectedFile;
    public String oldpath;
    public String path;
    

   public boolean duplicateCheck(String username, String email) { 
    dbConnector dbc = new dbConnector();
    
    try {
        String query = "SELECT * FROM tbl_users WHERE u_username = '" + username + "' OR u_email = '" + email + "'";
        ResultSet resultSet = dbc.getData(query);
        
        if (resultSet.next()) {
            String existingEmail = resultSet.getString("u_email");
            if (existingEmail.equals(email)) {
                JOptionPane.showMessageDialog(null, "Email is already used!");
                return true;
            }

            String existingUsername = resultSet.getString("u_username");
            if (existingUsername.equals(username)) {
                JOptionPane.showMessageDialog(null, "Username is already used!");
                return true;
            }
        }
    } catch (SQLException ex) {
        System.out.println("" + ex);
    }
    
    return false; // No duplicate found
}

    public boolean UpdateCheck(){
        
    dbConnector dbc = new dbConnector();
        
     try{
            String query = "SELECT * FROM tbl_users  WHERE (u_username = '" +un.getText()+ "' OR u_email = '" +em.getText()+ "') AND u_id != '"+uid.getText()+"'";
            ResultSet resultSet = dbc.getData(query);
           
            if(resultSet.next()){
                email = resultSet.getString("u_email");
                if(email.equals(em.getText())){
                JOptionPane.showMessageDialog(null, "Email is already used!");
                em.setText("");
                }
                username = resultSet.getString("u_username");
                if(username.equals(un.getText())){
                JOptionPane.showMessageDialog(null, "Username is already used!");
                un.setText("");
                }
                return true;
        }else{
                
                return false;
     }
     }catch(SQLException ex){
         System.out.println(""+ex);
         return false;
     }
    }
    
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }    
    
       public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
            if(ImagePath !=null){
                MyImage = new ImageIcon(ImagePath);
            }else{
                MyImage = new ImageIcon(pic);
            }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
       
        public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/usersimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    
        public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
    
     public void logEvent(int userId, String username, String action) 
    {
        dbConnector dbc = new dbConnector();
        Connection con = dbc.getConnection();
        PreparedStatement pstmt = null;
        Timestamp time = new Timestamp(new Date().getTime());

        try {
            String sql = "INSERT INTO tbl_log (u_id, u_username, login_time, log_status, log_description) "
                    + "VALUES ('" + userId + "', '" + username + "', '" + time + "', '" + action + "')";
            pstmt = con.prepareStatement(sql);

            /*            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstmt.setString(4, userType);*/
            pstmt.executeUpdate();
            System.out.println("Login log recorded successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error recording log: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        uid = new javax.swing.JTextField();
        ln = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        ps = new javax.swing.JTextField();
        ut = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        us = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        fn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sq = new javax.swing.JComboBox<>();
        ans = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        u_image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("User ID:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Last Name:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Email:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 163, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("UserName:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 203, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Password:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 238, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Account Type:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 273, -1, -1));

        uid.setEnabled(false);
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        jPanel3.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 56, 150, -1));
        jPanel3.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 126, 150, -1));
        jPanel3.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 161, 149, -1));
        jPanel3.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 201, 149, -1));
        jPanel3.add(ps, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 236, 149, -1));

        ut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel3.add(ut, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 271, 120, -1));

        add.setBackground(new java.awt.Color(0, 102, 102));
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel3.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 66, -1));

        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });
        jPanel3.add(us, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 120, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("User Status:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 302, -1, -1));

        cancel.setBackground(new java.awt.Color(0, 102, 102));
        cancel.setText("CANCEL");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel3.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        refresh.setBackground(new java.awt.Color(0, 102, 102));
        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel3.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));
        jPanel3.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 91, 149, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("First Name:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 93, -1, -1));

        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        sq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's the name of your first pet?", "What's the lastname of your Mother?", "What's your favorite food?", "What's your favorite Color?", "What's your birth month?" }));
        jPanel3.add(sq, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));
        jPanel3.add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 170, -1));

        u_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(u_image, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(u_image, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 220, 170));

        select.setBackground(new java.awt.Color(0, 102, 102));
        select.setText("SELECT");
        select.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel3.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 70, -1));

        remove.setBackground(new java.awt.Color(0, 102, 102));
        remove.setText("REMOVE");
        remove.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel3.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 70, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
 
   dbConnector dbc = new dbConnector();
    Session sess = Session.getInstance();  // Logged-in admin

    String fname = fn.getText().trim();
    String lname = ln.getText().trim();
    String uname = un.getText().trim();
    String pass = ps.getText().trim();
    String email = em.getText().trim();
    String status = us.getSelectedItem().toString().trim();
    String type = ut.getSelectedItem().toString();
    String question = sq.getSelectedItem().toString();
    String answer = ans.getText().trim();

    // Use the class-level selectedFile
    if (selectedFile == null) {
        JOptionPane.showMessageDialog(null, "Please select an image.");
        return;
    }

    String imageName = uname + "_" + selectedFile.getName();
    String destinationDir = "src/usersimages";
    new File(destinationDir).mkdirs();  // Ensure folder exists
    String destinationPath = destinationDir + "/" + imageName;

    // Validation
    if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || pass.isEmpty() ||
        email.isEmpty() || question.isEmpty() || answer.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all fields.");
        return;
    } else if (pass.length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters.");
        return;
    } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
        JOptionPane.showMessageDialog(null, "Enter a valid email address.");
        return;
    } else if (duplicateCheck(uname, email)) {
        return; // Already shown warning inside duplicateCheck
    }

    try {
        String hashedPassword = passwordHasher.hashPassword(pass);
        String hashedAnswer = passwordHasher.hashPassword(answer);

        // Save image to folder
        Files.copy(selectedFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Insert user into DB
        String insertQuery = "INSERT INTO tbl_users (u_fname, u_lname, u_username, u_status, u_password, u_email, u_type, u_image, security_question, security_answer) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbc.getConnection();
             PreparedStatement pst = conn.prepareStatement(insertQuery)) {

            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, uname);
            pst.setString(4, status);
            pst.setString(5, hashedPassword);
            pst.setString(6, email);
            pst.setString(7, type);
            pst.setString(8, imageName);
            pst.setString(9, question);
            pst.setString(10, hashedAnswer);

            int rowsInserted = pst.executeUpdate();

            if (rowsInserted > 0) {
                // Insert log entry
               String logQuery = "INSERT INTO tbl_log (u_id, u_username, u_type, log_status, log_description) VALUES (?, ?, ?, ?, ?)";

try (PreparedStatement logPst = conn.prepareStatement(logQuery)) {
    logPst.setInt(1, sess.getUid());  // User ID of the admin performing the action
    logPst.setString(2, sess.getUsername()); // Admin username
    logPst.setString(3, sess.getType()); // Assuming you have this in session
    logPst.setString(4, "Active"); // You can adjust this as needed
    logPst.setString(5, "Admin Added a New Account: " + uname); // Description of the action
    logPst.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                new usersForm().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed!");
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addActionPerformed

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        usersForm usf = new usersForm();
        usf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
    Session sess = Session.getInstance();  // Logged-in admin
        
    String id = uid.getText().trim();
    String email = em.getText().trim();
    String username = un.getText().trim();
    String pass = ps.getText().trim();
    String firstName = fn.getText().trim();  // First name
    String lastName = ln.getText().trim();   // Last name
    String type = ut.getSelectedItem().toString();  // User type
    String statusValue = us.getSelectedItem().toString(); // User status

    // Validation
    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Error: User ID is missing.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (firstName.isEmpty() || lastName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Error: First Name and Last Name are required.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Email validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    if (!email.matches(emailRegex)) {
        JOptionPane.showMessageDialog(this, "Invalid Email! Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Username validation
    if (!username.matches("[a-zA-Z0-9_]{5,}")) {
        JOptionPane.showMessageDialog(this, "Invalid Username! Must be at least 5 characters and contain only letters, numbers, and underscores.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Hash the password using SHA-256 before storing
        String hashedPassword = passwordHasher.hashPassword(pass);

        dbConnector dbc = new dbConnector();
        String checkQuery = "SELECT COUNT(*) FROM tbl_users WHERE (u_username = ? OR u_email = ?) AND u_id != ?";

        try (Connection conn = dbc.getConnection();
             PreparedStatement pst = conn.prepareStatement(checkQuery)) {

            pst.setString(1, username);
            pst.setString(2, email);
            pst.setInt(3, Integer.parseInt(id));

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Username or Email already exists! Please use different credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Use the same connection to execute the update query
            String updateQuery = "UPDATE tbl_users SET u_fname = ?, u_lname = ?, u_username = ?, u_email = ?, u_password = ?, u_status = ?, u_type = ? WHERE u_id = ?";

            try (PreparedStatement updatePst = conn.prepareStatement(updateQuery)) {
                updatePst.setString(1, firstName);   // First name
                updatePst.setString(2, lastName);    // Last name
                updatePst.setString(3, username);    // Username
                updatePst.setString(4, email);       // Email
                updatePst.setString(5, hashedPassword);  // Store hashed password
                updatePst.setString(6, statusValue); // Status (Corrected order)
                updatePst.setString(7, type);        // Type (Corrected order)
                updatePst.setInt(8, Integer.parseInt(id));  // User ID

                int updated = updatePst.executeUpdate();
                if (updated > 0) {
                    // ✅ Log the update action if update is successful
                    String logQuery = "INSERT INTO tbl_log (u_id, u_username, u_type, log_status, log_description) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement logPst = conn.prepareStatement(logQuery)) {
                        logPst.setInt(1, sess.getUid()); // Admin ID from session
                        logPst.setString(2, sess.getUsername()); // Admin username
                        logPst.setString(3, sess.getType()); // Admin type, e.g., "Admin"
                        logPst.setString(4, "Active"); // Status of the log
                        logPst.setString(5, "Admin Updated user account: " + username); // Description
                        logPst.executeUpdate();
                    } catch (SQLException logEx) {
                        JOptionPane.showMessageDialog(this, "Log Error: " + logEx.getMessage(), "Log Error", JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new usersForm().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/usersimages/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path= "";
                }else{
                    u_image.setIcon(ResizeImage(path, null, u_image));
                    select.setEnabled(false);
                    remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    
    }//GEN-LAST:event_selectActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        remove.setEnabled(false);
        select.setEnabled(true);
        u_image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

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
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    private javax.swing.JTextField ans;
    private javax.swing.JButton cancel;
    public javax.swing.JTextField em;
    public javax.swing.JTextField fn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField ln;
    public javax.swing.JTextField ps;
    private javax.swing.JButton refresh;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JComboBox<String> sq;
    private javax.swing.JLabel u_image;
    public javax.swing.JTextField uid;
    public javax.swing.JTextField un;
    public javax.swing.JComboBox<String> us;
    public javax.swing.JComboBox<String> ut;
    // End of variables declaration//GEN-END:variables
}
