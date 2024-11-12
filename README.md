 
# Aplikasi Penghitung Kata

**Tugas Pemrograman GUI**  
Nama: Atma Fathul Hadi  
NPM: 2210010425  

## 1. Deskripsi Program
Aplikasi ini adalah program berbasis GUI menggunakan Java untuk menghitung jumlah kata dan karakter dalam teks yang dimasukkan oleh pengguna. Program ini juga memungkinkan pengguna untuk mencari kata dalam teks dan menyimpan hasil perhitungan ke dalam file. Teks dapat disalin ke dalam area input, dan program akan menampilkan jumlah kata, karakter, dan memberi informasi tentang jumlah kemunculan kata yang dicari.

## 2. Komponen GUI
Aplikasi ini menggunakan komponen GUI berikut:

- **JFrame**: Sebagai jendela utama aplikasi.
- **JPanel**: Untuk mengatur layout dan grup komponen.
- **JLabel**: Digunakan untuk menampilkan label teks.
- **JTextArea**: Tempat untuk memasukkan teks yang akan dihitung.
- **JTextField**: Digunakan untuk memasukkan kata yang ingin dicari dalam teks.
- **JButton**: Tombol untuk mencari kata, menyimpan hasil, dan keluar.
- **JScrollPane**: Untuk memberikan scroll pada area teks.
- **JFileChooser**: Untuk memilih tempat penyimpanan file hasil perhitungan.

## 3. Logika Program
Program ini bekerja dengan cara menghitung jumlah kata dan karakter yang ada di dalam teks yang dimasukkan pengguna. Pengguna dapat melakukan hal berikut:
- Menghitung jumlah kata dan karakter.
- Mencari kata tertentu dalam teks dan menghitung kemunculannya.
- Menyimpan hasil perhitungan ke dalam file teks.

### Kode Terkait:
```java
private void updateCounts() {
    String text = inputtext.getText();
    int wordCount = text.trim().isEmpty() ? 0 : text.trim().split("\s+").length;
    int charCount = text.length();
    hasil.setText("Jumlah Kata: " + wordCount + ", Jumlah Karakter: " + charCount);
}
```
### Fungsi updateCounts() akan mengupdate jumlah kata dan karakter setiap kali ada perubahan pada input teks.

```java
private void searchInText() {
    String text = inputtext.getText();
    String searchWord = cariText.getText().trim();
    if (searchWord.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Silakan masukkan kata yang ingin dicari.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int count = 0;
    Pattern pattern = Pattern.compile("\b" + Pattern.quote(searchWord) + "\b", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
        count++;
    }

    JOptionPane.showMessageDialog(this, "Kata "" + searchWord + "" ditemukan sebanyak " + count + " kali.");
}
```
### Fungsi searchInText() digunakan untuk mencari dan menghitung berapa kali kata yang dimasukkan muncul dalam teks.

### 4. Events
ActionListener pada tombol Keluar: Menutup aplikasi saat tombol "Keluar" ditekan.
```java
keluar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
});
```
### ActionListener pada tombol Simpan: Menyimpan hasil perhitungan ke dalam file.
```java
simpan.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        saveToFile();
    }
});
```
### ActionListener pada tombol Cari: Mencari kata yang dimasukkan dalam teks.
```java
cariButton.addActionListener(new ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        cariButtonActionPerformed(evt);
    }
});
```
### 5. Hasil Program
Aplikasi ini menampilkan hasil berupa jumlah kata, jumlah karakter, dan informasi tentang jumlah kemunculan kata yang dicari. Hasil tersebut dapat disimpan dalam file teks.

Screenshot Hasil Program:
![S](https://github.com/atmafathulhadi/Tugas6-AplikasiCekCuacaSederhana/blob/main/S.png)


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
Buka program di IDE seperti NetBeans atau Eclipse.
Jalankan program dan masukkan teks pada kolom "Maukkan Kata".
Klik tombol "Simpan" untuk menyimpan hasil ke dalam file.
Masukkan kata yang ingin dicari pada kolom "Cari teks" dan klik "Cari".
Hasil perhitungan kata dan karakter akan ditampilkan di bawahnya.
Klik "Keluar" untuk menutup aplikasi.
