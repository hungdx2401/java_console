/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.views;

import console.java.models.JModel;
import javax.swing.JOptionPane;

/**
 *
 * @author DongHo
 */
public class JAdminLogin extends javax.swing.JFrame {

     /**
      * Creates new form JFrameMain
      */
     public JAdminLogin() {
	  initComponents();
     }

     /**
      * This method is called from within the constructor to initialize the
      * form. WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          jLabel1 = new javax.swing.JLabel();
          jLabel2 = new javax.swing.JLabel();
          txtAccount = new javax.swing.JTextField();
          txtPasswordCharArray = new javax.swing.JPasswordField();
          jButton1 = new javax.swing.JButton();
          jButton2 = new javax.swing.JButton();
          errAccount = new javax.swing.JLabel();
          errPassword = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("Đăng nhập");

          jLabel1.setText("Email");

          jLabel2.setText("Mật khẩu");

          jButton1.setText("Đăng nhập");
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });

          jButton2.setText("Làm lại");

          errAccount.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
          errAccount.setForeground(new java.awt.Color(255, 0, 51));

          errPassword.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
          errPassword.setForeground(new java.awt.Color(255, 0, 51));

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jLabel1)
                         .addComponent(jLabel2))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(jButton1)
                              .addGap(18, 18, 18)
                              .addComponent(jButton2))
                         .addComponent(txtAccount)
                         .addComponent(txtPasswordCharArray, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                         .addComponent(errAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(errPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(63, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel1)
                         .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(errAccount)
                    .addGap(46, 46, 46)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jLabel2)
                         .addComponent(txtPasswordCharArray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addComponent(errPassword)
                    .addGap(32, 32, 32)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jButton1)
                         .addComponent(jButton2))
                    .addContainerGap(37, Short.MAX_VALUE))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

	  if (!txtAccount.getText().matches("\\A([^@\\s]+)@((?:[-a-z0-9]+\\.)+[a-z]{2,})\\z")) {
	       errAccount.setText("Hãy nhập định dạng Email!");
	       return;
	  } else {
	       errAccount.setText("");
	  }
	  if (txtPasswordCharArray.getPassword().length == 0) {
	       errPassword.setText("Hãy nhập Password!");
	       return;
	  } else {
	       errPassword.setText("");
	  }

	  int login = JModel.loginAdmin(txtAccount.getText(), new String(txtPasswordCharArray.getPassword()));
	  if (login > 0) {
	       JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
	       this.setVisible(false);
	       new JMain().setVisible(true);
	  } else {
	       JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập!\n Vui lòng thử lại!");
	       return;
	  }

     }//GEN-LAST:event_jButton1ActionPerformed

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
	  } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	       java.util.logging.Logger.getLogger(JAdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	  }
	  //</editor-fold>

	  /* Create and display the form */
	  java.awt.EventQueue.invokeLater(new Runnable() {
	       public void run() {
		    new JAdminLogin().setVisible(true);
	       }
	  });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JLabel errAccount;
     private javax.swing.JLabel errPassword;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JTextField txtAccount;
     private javax.swing.JPasswordField txtPasswordCharArray;
     // End of variables declaration//GEN-END:variables
}
