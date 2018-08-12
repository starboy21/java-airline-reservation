/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airlineresevation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author gtbstud
 */
public class createemployee extends javax.swing.JInternalFrame implements myvariables{

    /**
     * Creates new form createemployee
     */
        JFileChooser fc;
    File myfile;
    String pic = "";

    public createemployee() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Create Employee");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 80, 150, -1));

        jLabel1.setText("USERNAME");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 83, -1, -1));

        jLabel2.setText("PASSWORD");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 139, -1, -1));
        getContentPane().add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 136, 150, -1));

        jButton1.setText("CREATE Employee");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 120, 130));

        jButton2.setText("UPLOAD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
              String filename="";
                
        if(myfile!=null){
         filename = "images\\"+new Date().getTime() + "_" + myfile.getName();//Date.getTime() added for unique file name.
        int i;
        FileInputStream rd = null;
        FileOutputStream outs = null;
        try {

            rd = new FileInputStream(myfile);
            outs = new FileOutputStream(filename);//writing the new file in 'Images' folder, in the project

            byte[] b = new byte[2048];
            while ((i = rd.read(b)) > 0) {
                outs.write(b, 0, i);

            }
            JOptionPane.showMessageDialog(rootPane, "File saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "error" + e.getMessage());
        } finally {
            try {
                rd.close();
                outs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "error in closing file" + e.getMessage());
            }
        }
        }else
        {
            filename="images\\default.JPG";
        
        }

        try
        {
            Connection myconnection;

            myconnection=DriverManager.getConnection(path+place, username, password);

            try
            {
                String q="insert into employeetable values(?,?,?)";
                PreparedStatement mystatement=myconnection.prepareStatement(q);
                mystatement.setString(1,jTextField1.getText());
                mystatement.setString(2, jPasswordField3.getText());
                mystatement.setString(3, filename);
                mystatement.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Employee Created", "Success", JOptionPane.INFORMATION_MESSAGE);
              
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, "Error in query due to"+e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                myconnection.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error in connection" +e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        fc = new JFileChooser();

        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                int pos = f.getName().lastIndexOf('.');
                if (pos == -1) {
                    return false;
                } else {
                    String extention = f.getName().substring(pos + 1);//saving the extension
                    String str[] = {"gif", "png", "jpg", "jpeg"};//allowed extentions
                    for (String allwd : str) {

                        if (extention.equalsIgnoreCase(allwd)) {
                            return true;
                        }
                    }
                    return false;
                }

            }

            @Override
            public String getDescription() {

                return "";
            }
        });
        int result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            myfile = fc.getSelectedFile();

            try {
                jLabel3.setIcon(new ImageIcon(ImageIO.read(myfile)));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(rootPane, "Error");
                e.printStackTrace();
            }
        }
                                       

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
