
# Matriks
> Additional information or tagline

Program yang memanipulasi matriks untuk mendapat hasil yang diinginkan

## Installing / Getting started

A quick introduction of the minimal setup you need to get a hello world up &
running.

```shell
git clone https://github.com/frdmmm/Algeo01-22008.git
cd Algeo01-22008
java matrix/bin/Main
```

Jalankan kode diatas.
Kode diatas akan memulai program.



## Features

Di dalam program, terdapat MENU untuk memilih hal yang ingin dilakukan.
Setelah memilih yang akan dilakukan, user akan diminta memilih metode input (kecuali jika memilih bicubic spline interepolation).
Jika memilih input user, pengguna memasukkan matriks dalam format

m n        #ukuran matriks mxn
isi matriks
contoh
```
2 3
1 2 3
2 3 4
```
kecuali pada pilihan determinan dan balikan(pengguna hanya mengisi m, matriks akan berukuran mxm)
2 2
1 2
3 4

Jika memilih input text, pengguna memasukkan file txt yang ingin menjadi input di folder test.
Pilihan bicubic spline interpolation berarti memilih input text secara otomatis.
pengguna akan diarahkan untuk mengisi nama file txt yang diinginkan
pengguna menginput nama file beserta format nya (contoh: "file.txt")

Setelah menginput matriks, pengguna memilih metode input (print, txt, atau keduanya).
file hasil output akan ada di folder test.
