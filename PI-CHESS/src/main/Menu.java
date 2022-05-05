package main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import piese.Alianta;
import piese.Cal;
import piese.Nebun;
import piese.Piesa;
import piese.Pion;
import piese.Rege;
import piese.Regina;
import piese.Turn;
import tabla.Spatiu;
import tabla.Tabla;

public class Menu extends javax.swing.JFrame {

    Connection cn;

    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        newGameBtn = new javax.swing.JButton();
        loadGameBtn = new javax.swing.JButton();
        ExitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Șah");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setText("Șah");

        newGameBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        newGameBtn.setText("New game");
        newGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBtnActionPerformed(evt);
            }
        });

        loadGameBtn.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        loadGameBtn.setText("Load game");
        loadGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameBtnActionPerformed(evt);
            }
        });

        ExitBtn.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        ExitBtn.setText("Exit");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(newGameBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadGameBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(newGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loadGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void loadGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameBtnActionPerformed
        Tabla tabla = incarcaTabla();
        if (tabla != null) {
            new TablaGUI(tabla).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No game saved");
        }
        clearSql();
    }//GEN-LAST:event_loadGameBtnActionPerformed

    private void newGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBtnActionPerformed
        clearSql();
        Tabla tabla = Tabla.creeazaTablaStandard();
        new TablaGUI(tabla).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newGameBtnActionPerformed

    public void clearSql() {
        connection();

        try {
            PreparedStatement ps = cn.prepareStatement("delete from chessboard");
            ps.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chessgame", "root", "");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection is close!");
        }
    }

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadGameBtn;
    private javax.swing.JButton newGameBtn;
    // End of variables declaration//GEN-END:variables

    public Tabla incarcaTabla() {
        connection();

        List<Spatiu> listaSpatii = new ArrayList<>();

        // Initializare tabla cu spatii goale
        for (int i = 0; i < 64; i++) {
            listaSpatii.add(i, new Spatiu(i, null));
        }

        Tabla tabla = new Tabla(listaSpatii);
        try {

            PreparedStatement ps = cn.prepareStatement("Select * from chessboard where position > -1");
            ResultSet rs = ps.executeQuery();
            List<String> piese = new ArrayList<>();
            List<Integer> pozitii = new ArrayList<>();

            while (rs.next()) {
                piese.add(rs.getString("type"));
                pozitii.add(rs.getInt("position"));
            }

            for (int i = 0; i < piese.size(); i++) {

                int pozitie = pozitii.get(i);
                Piesa piesa = convertirePiesaSql(piese.get(i), pozitie, tabla);

                tabla.getSpatii().get(pozitie).setPiesa(piesa);

            }

            if (piese.isEmpty()) {
                tabla = null;
            }

            cn.close();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return tabla;
    }

    private Piesa convertirePiesaSql(String piesaStr, int pozitie, Tabla tabla) {
        Alianta alianta;

        if (piesaStr.substring(0, 1).equals("W")) {
            alianta = Alianta.ALB;
        } else {
            alianta = Alianta.NEGRU;
        }

        Piesa piesaRezultata = null;

        if (piesaStr.substring(1, 2).equals("R")) {
            piesaRezultata = new Turn(pozitie, alianta, tabla);
        } else if (piesaStr.substring(1, 2).equals("N")) {
            piesaRezultata = new Cal(pozitie, alianta, tabla);
        } else if (piesaStr.substring(1, 2).equals("B")) {
            piesaRezultata = new Nebun(pozitie, alianta, tabla);
        } else if (piesaStr.substring(1, 2).equals("K")) {
            piesaRezultata = new Rege(pozitie, alianta, tabla);
        } else if (piesaStr.substring(1, 2).equals("Q")) {
            piesaRezultata = new Regina(pozitie, alianta, tabla);
        } else if (piesaStr.substring(1, 2).equals("P")) {
            piesaRezultata = new Pion(pozitie, alianta, tabla);
        }
        return piesaRezultata;
    }
}
