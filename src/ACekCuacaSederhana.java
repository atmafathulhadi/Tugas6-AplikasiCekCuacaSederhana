/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author atmaf
 */
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;

public class ACekCuacaSederhana extends javax.swing.JFrame {

    private ArrayList<String> kotaFavorit = new ArrayList<>();
    private DefaultTableModel model;

    /**
     * Creates new form ACekCuacaSederhana
     */
    public ACekCuacaSederhana() {
        initComponents();
        model = new DefaultTableModel(new String[]{"Kota", "Suhu", "Kondisi"}, 0);
        jTable1.setModel(model);
        muatDataCuacaDariFile();
    }

    private String ambilDataCuaca(String lokasi) {
        try {
            String urlString = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + lokasi + "?key=VMQBS3HNUMH4SYWX76C5WL2T6&unitGroup=metric";
            URL url = new URL(urlString);
            HttpURLConnection koneksi = (HttpURLConnection) url.openConnection();
            koneksi.setRequestMethod("GET");

            int kodeRespon = koneksi.getResponseCode();
            if (kodeRespon == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = koneksi.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("Kode kesalahan HTTP: " + kodeRespon);
                BufferedReader in = new BufferedReader(new InputStreamReader(koneksi.getErrorStream()));
                String inputLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    errorResponse.append(inputLine);
                }
                in.close();
                System.out.println("Respons kesalahan: " + errorResponse.toString());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void perbaruiTampilanCuaca(String jsonResponse, String kota) {
        try {
            JSONObject weatherJson = new JSONObject(jsonResponse);
            JSONObject kondisiCuaca = weatherJson.getJSONObject("currentConditions");

            double suhu = kondisiCuaca.getDouble("temp");
            String kondisiCuacaStr = kondisiCuaca.getString("conditions");

            model.addRow(new Object[]{kota, String.valueOf(suhu), kondisiCuacaStr});

            simpanDataCuacaKeFile(kota, String.valueOf(suhu), kondisiCuacaStr);

            if (!kotaFavorit.contains(kota)) {
                tambahKeFavorit(kota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tambahKeFavorit(String kota) {
        if (!kotaFavorit.contains(kota)) {
            kotaFavorit.add(kota);
            jComboBox1.addItem(kota);
        }
    }

    private void muatDataCuacaDariFile() {
        File file = new File("datacuaca.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("datacuaca.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        simpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kota = (String) jComboBox1.getSelectedItem();
                String suhu = jTable1.getValueAt(jTable1.getRowCount() - 1, 1).toString();
                String kondisi = jTable1.getValueAt(jTable1.getRowCount() - 1, 2).toString();

                if (suhu != null && kondisi != null) {
                    simpanDataCuacaKeFile(kota, suhu, kondisi);
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak ada data yang disimpan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Keluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Keluar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("APLIKASI CEK CUACA SEDERHANA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(221, 221, 221))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setText("hasil");

        Keluar.setText("Keluar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "jakarta", "Bandung", "Surabaya", "Medan", "Bali", "Yogyakarta" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        simpan.setText("simpan");

        cari.setText("cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kota", "Suhu", "Kondisi"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Keluar)
                        .addGap(18, 18, 18)
                        .addComponent(simpan))
                    .addComponent(cari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(145, 145, 145))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addComponent(cari)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Keluar)
                            .addComponent(simpan)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void simpanDataCuacaKeFile(String kota, String suhu, String kondisiCuaca) {
        try (FileWriter writer = new FileWriter("datacuaca.csv", true)) {
            writer.append(kota).append(",").append(suhu).append(",").append(kondisiCuaca).append("\n");
            System.out.println("Data disimpan ke CSV: " + kota + ", " + suhu + ", " + kondisiCuaca);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        String kota;

        if (!jTextField1.getText().trim().isEmpty()) {
            kota = jTextField1.getText().trim();
        } else if (jComboBox1.getSelectedItem() != null && !jComboBox1.getSelectedItem().toString().isEmpty()) {
            kota = jComboBox1.getSelectedItem().toString().trim();
        } else {
            JOptionPane.showMessageDialog(this, "Masukkan atau pilih nama kota terlebih dahulu.");
            return;
        }

        String response = ambilDataCuaca(kota);
        System.out.println("Response: " + response);

        if (response != null) {
            try {
                JSONObject jsonObj = new JSONObject(response);
                JSONObject main = jsonObj.getJSONObject("currentConditions");
                double temp = main.getDouble("temp");
                String weatherDescription = main.getString("conditions");

                String weather = weatherDescription.toLowerCase();
                String iconPath = "";

                if (weather.contains("cloud")) {
                    iconPath = "/images/cloudy.png";
                } else if (weather.contains("rain") && weather.contains("thunderstorm")) {
                    iconPath = "/images/thunderstorm.png";
                } else if (weather.contains("rain")) {
                    iconPath = "/images/rain.png";
                } else {
                    iconPath = "/images/sunny.png";
                }

                ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
                Image image = originalIcon.getImage();

                int labelWidth = jLabel2.getWidth();
                int labelHeight = jLabel2.getHeight();
                Image resizedImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                jLabel2.setIcon(new ImageIcon(resizedImage));

                String outputText = String.format("%.1f°C di %s", temp, kota);
                jLabel2.setText(outputText);

                perbaruiTampilanCuaca(response, kota);

                if (!kotaFavorit.contains(kota)) {
                    tambahKeFavorit(kota);
                }

            } catch (Exception e) {
                e.printStackTrace();
                jLabel2.setText("Gagal mendapatkan data cuaca.");
                jLabel2.setIcon(null);
            }
        } else {
            jLabel2.setText("Gagal mendapatkan data cuaca.");
        }


    }//GEN-LAST:event_cariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACekCuacaSederhana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Keluar;
    private javax.swing.JButton cari;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton simpan;
    // End of variables declaration//GEN-END:variables
}
