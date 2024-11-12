
# Aplikasi Cek Cuaca Sederhana

**Tugas Pemrograman GUI**  
Nama: Atma Fathul Hadi  
NPM: 2210010425  

## 1. Deskripsi Program
Aplikasi ini adalah program berbasis GUI menggunakan Java untuk mengecek cuaca pada kota-kota tertentu. Program ini memungkinkan pengguna untuk melihat kondisi cuaca pada kota favorit, menyimpan hasil perhitungan ke dalam file, dan melakukan pencarian kondisi cuaca dari kota yang dipilih. Data cuaca diperoleh dari API eksternal.

## 2. Komponen GUI
Aplikasi ini menggunakan komponen GUI berikut:

- **JFrame**: Sebagai jendela utama aplikasi.
- **JPanel**: Untuk mengatur layout dan grup komponen.
- **JLabel**: Digunakan untuk menampilkan label teks dan ikon cuaca.
- **JTextField**: Tempat untuk memasukkan nama kota yang akan dicari.
- **JComboBox**: Untuk memilih kota favorit.
- **JButton**: Tombol untuk mencari cuaca, menyimpan hasil, dan keluar.
- **JTable**: Untuk menampilkan data cuaca kota.
- **JScrollPane**: Untuk memberikan scroll pada tabel.
- **JFileChooser**: Untuk memilih tempat penyimpanan file hasil pencarian cuaca.

## 3. Logika Program
Program ini bekerja dengan cara mengambil data cuaca dari API berdasarkan kota yang dimasukkan atau dipilih pengguna. Pengguna dapat melakukan hal berikut:
- Mengecek cuaca pada kota yang dipilih atau dimasukkan.
- Menampilkan data suhu dan kondisi cuaca pada tabel.
- Menyimpan data cuaca ke dalam file.

### Kode Terkait:
```java
private void perbaruiTampilanCuaca(String jsonResponse, String kota) {
    JSONObject weatherJson = new JSONObject(jsonResponse);
    JSONObject kondisiCuaca = weatherJson.getJSONObject("currentConditions");

    double suhu = kondisiCuaca.getDouble("temp");
    String kondisiCuacaStr = kondisiCuaca.getString("conditions");

    model.addRow(new Object[]{kota, String.valueOf(suhu), kondisiCuacaStr});
    simpanDataCuacaKeFile(kota, String.valueOf(suhu), kondisiCuacaStr);
}
```
### Fungsi `perbaruiTampilanCuaca` digunakan untuk memperbarui tampilan tabel dengan data cuaca yang baru.

```java
private void cariActionPerformed(java.awt.event.ActionEvent evt) {                                     
    String kota = jTextField1.getText().trim();
    String response = ambilDataCuaca(kota);

    if (response != null) {
        perbaruiTampilanCuaca(response, kota);
    } else {
        jLabel2.setText("Gagal mendapatkan data cuaca.");
    }
}
```
### Fungsi `cariActionPerformed` menangani aksi pencarian dan menampilkan data cuaca kota yang diminta.

### 4. Events
- **ActionListener pada tombol Keluar**: Menutup aplikasi saat tombol "Keluar" ditekan.
```java
Keluar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
});
```
- **ActionListener pada tombol Simpan**: Menyimpan hasil pencarian cuaca ke dalam file.
```java
simpan.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        simpanDataCuacaKeFile(kota, suhu, kondisi);
    }
});
```
- **ActionListener pada tombol Cari**: Mencari kondisi cuaca dari kota yang dipilih atau dimasukkan.
```java
cari.addActionListener(new ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        cariActionPerformed(evt);
    }
});
```

### 5. Hasil Program
Aplikasi ini menampilkan hasil berupa suhu dan kondisi cuaca kota yang dipilih. Data cuaca juga dapat disimpan ke dalam file CSV.

### 6. Indikator Penilaian
| No  | Komponen         |  Persentase  |
| :-: | ---------------- |   :-----:    |
|  1  | Komponen GUI     |    20%       |
|  2  | Logika Program   |    20%       |
|  3  | Kesesuaian UI    |    15%       |
|  4  | Constructor      |    15%       |
|  5  | Memenuhi Variasi |    30%       |
|     | **TOTAL**        | 100%         |

### 7. Cara Menjalankan Program
1. Buka program di IDE seperti NetBeans atau Eclipse.
2. Jalankan program dan masukkan atau pilih nama kota pada kolom input atau dropdown.
3. Klik tombol "Cari" untuk mencari cuaca kota yang diinginkan.
4. Klik tombol "Simpan" untuk menyimpan hasil ke dalam file CSV.
5. Klik "Keluar" untuk menutup aplikasi.
