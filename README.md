# Tugas Besar 1 Aljabar Linear dan Geometri Kelompok 33
## Anggota Kelompok 33
1. Farrel Farandieka Fibriyanto (13520054)
2. Bariza Haqi (13520018)
3. Rozan Fadhil Al Hafidz (13520039)

## Menu Utama
- Sistem Persamaan Linear
- Determinan
- Matriks Balikan
- Interpolasi Polinom
- Regresi Linier Berganda
- Keluar

## Fitur Program
- Membaca input matriks dari file
- Menunjukkan langkah-langkah yang dijalankan program untuk mendapatkan solusi
- Menyimpan solusi ke dalam file

### Cara Menjalankan Program
- Buka folder src pada program
- Klik buka run.bat
- Pilih perintah yang ingin dijalankan
- Program akan bertanya kepada pengguna mengenai baris dan kolom matriks yang akan digunakan
- Setelah itu, pengguna memilih jenis input. Apakah input dari keyboard atau input dari file

### Cara memasukkan input dari fike
- Saat memilih jenis input "Input dari file", masukkan path lengkap file txt yang hanya berisi matriks. Contohnya "D:\Data\Input\Matriks.txt". File txt yang digunakan harus memiliki endline di akhir (baris terakhir file merupakan baris kosong).
- Jika terdapat pesan kesalahan "Error : No line found", berarti file tidak memiliki endline di akhir ataupun matriks pada file tidak sesuai dengan input pengguna.

### Cara menyimpan hasil ke dalam file
- Setelah mencetak solusi ke layar, program akan menayakan apakan pengguna ingin menyimpan solusi ke dalam file. Jika pengguna memilih pilihan "Ya", maka program akan menanyakan nama file yang ingin disimpan menyimpan solusi dengan nama yang diberikan pengguna di folder ../output berformat txt (pengguna tidak perlu menuliskan .txt di akhir file)
- Jika file belum ada, program akan membuat file baru
- Jika file sudah ada, maka program akan mengganti isi dari file yang lama menjadi file baru

## Perintah yang dapat dilakukan program :
### A. Mencari solusi sistem persamaan linear menggunakan metode :
  1. Eliminasi Gauss
  2. Eliminasi Gauss-Jordan
  3. Matriks balikan
  4. Kaidah Cramer

### B. Mencari determinan dari suatu matriks dengan menggunakan metode :
  1. Ekspansi kofaktor
  2. Operasi baris elementer
  3. Sarrus (khusus matriks berdimensi 3x3

### C. Mencari balikan suatu matriks dengan menggunakan metode :
  1. Gauss-Jordan
  2. Adjoin

### D. Mencari interpolasi polinom dari n buah titik yang diberikan

### E. Mencari hasil dari regresi linier berganda dari sampel yang diberikan

