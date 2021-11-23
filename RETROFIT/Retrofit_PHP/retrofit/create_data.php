<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $nim = $_POST["nim"];
    $nama = $_POST["nama"];
    $prodi = $_POST["prodi"];
    $fakultas = $_POST["fakultas"];

    $query = "INSERT INTO mahasiswa (nim,nama,prodi,fakultas) VALUES ('$nim','$nama','$prodi','$fakultas')";
    $eksekusi = mysqli_query($konek,$query);
    $cek = mysqli_affected_rows($konek);

    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Berhasil";
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);
?>