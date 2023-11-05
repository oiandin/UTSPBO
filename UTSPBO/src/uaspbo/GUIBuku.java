/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uaspbo;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import java.util.HashMap;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lmao
 */
public class GUIBuku extends javax.swing.JFrame {

    private Timer refreshTimer;

    public GUIBuku() {
        try {
            dataBuku = new ArrayList<>();
            initComponents();
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BukuPBO", "postgres", "123");
            tampil(conn);

            refreshTimer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tampil(conn);
                }
            });
            refreshTimer.start();
        } catch (SQLException ex) {
            Logger.getLogger(GUIBuku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(rootPane, pesan);
    }

    ArrayList<BukuPOJO> dataBuku;

    private int masukkanData(Connection conn, String isbn, String judul_buku, String tahun_terbit, String penerbit) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO buku (isbn,judul_buku,tahun_terbit,penerbit)" + " VALUES (?,?,?,?)");
        pst.setString(1, isbn);
        pst.setString(2, judul_buku);
        pst.setString(3, tahun_terbit);
        pst.setString(4, penerbit);
        return pst.executeUpdate();
    }

    private int ubahData(Connection conn, String isbn, String judul_buku, String tahun_terbit, String penerbit) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("UPDATE buku SET judul_buku = ?, tahun_terbit = ?, penerbit = ?" + " WHERE isbn = ?");
        pst.setString(1, judul_buku);
        pst.setString(2, tahun_terbit);
        pst.setString(3, penerbit);
        pst.setString(4, isbn);
        return pst.executeUpdate();
    }

    private int hapusData(Connection conn, String isbn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("DELETE FROM buku WHERE isbn=?");
        pst.setString(1, isbn);
        return pst.executeUpdate();
    }

    private void tampil(Connection conn) {
        dataBuku.clear();
        try {
            String sql = "select * from buku";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BukuPOJO data = new BukuPOJO();
                data.setISBN(String.valueOf(rs.getObject(1)));
                data.setJudulBuku(String.valueOf(rs.getObject(2)));
                data.setTahunTerbit(String.valueOf(rs.getObject(3)));
                data.setPenerbit(String.valueOf(rs.getObject(4)));
                dataBuku.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
            model.setRowCount(0);
            for (BukuPOJO data : dataBuku) {

                Object[] baris = new Object[4];
                baris[0] = data.getISBN();
                baris[1] = data.getJudulBuku();
                baris[2] = data.getTahunTerbit();
                baris[3] = data.getPenerbit();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUIBuku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelISBN = new javax.swing.JLabel();
        jLabelJudulBuku = new javax.swing.JLabel();
        jLabelTahunTerbit = new javax.swing.JLabel();
        jLabelPenerbit = new javax.swing.JLabel();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldJudulBuku = new javax.swing.JTextField();
        jTextFieldTahunTerbit = new javax.swing.JTextField();
        jTextFieldPenerbit = new javax.swing.JTextField();
        jButtonTambah = new javax.swing.JButton();
        jButtonUbah = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jButtonCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel1.setText("PROGRAM PEMINJAMAN BUKU");

        jLabelISBN.setText("ISBN");

        jLabelJudulBuku.setText("Judul Buku");

        jLabelTahunTerbit.setText("Tahun Terbit");

        jLabelPenerbit.setText("Penerbit");

        jTextFieldISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldISBNActionPerformed(evt);
            }
        });

        jTextFieldJudulBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJudulBukuActionPerformed(evt);
            }
        });

        jTextFieldTahunTerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTahunTerbitActionPerformed(evt);
            }
        });

        jTextFieldPenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPenerbitActionPerformed(evt);
            }
        });

        jButtonTambah.setText("TAMBAH");
        jButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahActionPerformed(evt);
            }
        });

        jButtonUbah.setText("UBAH");
        jButtonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbahActionPerformed(evt);
            }
        });

        jButtonHapus.setText("HAPUS");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });

        jButtonCetak.setText("CETAK");
        jButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCetakActionPerformed(evt);
            }
        });

        jTableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "Judul Buku", "Tahun Terbit", "Penerbit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBuku);
        if (jTableBuku.getColumnModel().getColumnCount() > 0) {
            jTableBuku.getColumnModel().getColumn(0).setResizable(false);
            jTableBuku.getColumnModel().getColumn(1).setResizable(false);
            jTableBuku.getColumnModel().getColumn(2).setResizable(false);
            jTableBuku.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jTextFieldJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonTambah)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonUbah)
                                        .addGap(12, 12, 12)
                                        .addComponent(jButtonHapus))
                                    .addComponent(jButtonCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelTahunTerbit, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(jLabelPenerbit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTahunTerbit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabelISBN))
                            .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabelJudulBuku))
                            .addComponent(jTextFieldJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabelTahunTerbit))
                            .addComponent(jTextFieldTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabelPenerbit))
                            .addComponent(jTextFieldPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTambah)
                            .addComponent(jButtonUbah)
                            .addComponent(jButtonHapus))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCetak))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldISBNActionPerformed

    private void jTextFieldJudulBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJudulBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldJudulBukuActionPerformed

    private void jTextFieldTahunTerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTahunTerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTahunTerbitActionPerformed

    private void jTextFieldPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPenerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPenerbitActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        String isbn = jTextFieldISBN.getText().trim();
        EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        Buku b = entityManager.find(Buku.class, isbn);

        if (b != null) {
            entityManager.remove(b);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();

        jTextFieldISBN.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldTahunTerbit.setText("");
        jTextFieldPenerbit.setText("");

    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahActionPerformed
        String isbn = jTextFieldISBN.getText().trim();
        String judulBuku = jTextFieldJudulBuku.getText();
        String tahunTerbit = jTextFieldTahunTerbit.getText();
        String penerbit = jTextFieldPenerbit.getText();

        //Awal Persistence
        EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();
        entityManager.getTransaction().begin();
        Buku b = new Buku();
        b.setIsbn(isbn);
        b.setJudulBuku(judulBuku);
        b.setTahunTerbit(tahunTerbit);
        b.setPenerbit(penerbit);

        entityManager.persist(b);
        entityManager.getTransaction().commit();
        // akhir persistence

        jTextFieldISBN.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldTahunTerbit.setText("");
        jTextFieldPenerbit.setText("");
    }//GEN-LAST:event_jButtonTambahActionPerformed

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbahActionPerformed
        String isbn = jTextFieldISBN.getText().trim();
        String judul_buku = jTextFieldJudulBuku.getText();
        String tahun_terbit = jTextFieldTahunTerbit.getText();
        String penerbit = jTextFieldPenerbit.getText();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Buku buku = em.find(Buku.class, isbn); // Mencari buku berdasarkan ISBN

            if (buku != null) {
                buku.setJudulBuku(judul_buku);
                buku.setTahunTerbit(tahun_terbit);
                buku.setPenerbit(penerbit);

                em.merge(buku);
                em.getTransaction().commit(); // Commit transaksi
                peringatan("Data berhasil diubah.");
            } else {
                peringatan("Data dengan ISBN " + isbn + " tidak ditemukan.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Rollback jika terjadi kesalahan
            peringatan("Terjadi kesalahan saat mengubah data.");
        } finally {
            jTextFieldISBN.setText("");
            jTextFieldJudulBuku.setText("");
            jTextFieldTahunTerbit.setText("");
            jTextFieldPenerbit.setText("");
        }
    }//GEN-LAST:event_jButtonUbahActionPerformed

    private void jTableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBukuMouseClicked
        int row = jTableBuku.getSelectedRow();
        if (row >= 0) {
            String ISBN = jTableBuku.getValueAt(row, 0).toString();
            String judulBuku = jTableBuku.getValueAt(row, 1).toString();
            String tahunTerbit = jTableBuku.getValueAt(row, 2).toString();
            String penerbit = jTableBuku.getValueAt(row, 3).toString();

            jTextFieldISBN.setText(ISBN);
            jTextFieldJudulBuku.setText(judulBuku);
            jTextFieldTahunTerbit.setText(tahunTerbit);
            jTextFieldPenerbit.setText(penerbit);
        }
    }//GEN-LAST:event_jTableBukuMouseClicked

    private void jButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCetakActionPerformed
        String reportPath = "src/uaspbo/reportBuku.jrxml";
        //awal persistence 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Buku> cq = cb.createQuery(Buku.class);
        Root<Buku> buku1 = cq.from(Buku.class);
        cq.select(buku1);

        TypedQuery<Buku> q = em.createQuery(cq);
        List<Buku> list = q.getResultList();
        Query query = em.createQuery("SELECT b FROM Buku b");
        List<Buku> hasil = query.getResultList();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hasil);

        try {
            // TODO add your handling code here:
            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
            JasperViewer vw = new JasperViewer(jp, false);
            vw.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(GUIBuku.class.getName()).log(Level.SEVERE, null, ex);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

    }//GEN-LAST:event_jButtonCetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIBuku guiBuku = new GUIBuku();
                guiBuku.setVisible(true);

                // Menambahkan window listener untuk mematikan timer saat aplikasi ditutup
                guiBuku.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        guiBuku.refreshTimer.stop();
                        System.exit(0);
                    }
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCetak;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonTambah;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelISBN;
    private javax.swing.JLabel jLabelJudulBuku;
    private javax.swing.JLabel jLabelPenerbit;
    private javax.swing.JLabel jLabelTahunTerbit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldJudulBuku;
    private javax.swing.JTextField jTextFieldPenerbit;
    private javax.swing.JTextField jTextFieldTahunTerbit;
    // End of variables declaration//GEN-END:variables
}
