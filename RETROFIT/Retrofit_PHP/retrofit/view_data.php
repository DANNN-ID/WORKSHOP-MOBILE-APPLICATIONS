<?php

require("koneksi.php");
$query = "SELECT * FROM mahasiswa";
$exequery = mysqli_query($konek,$query);
$cek = mysqli_affected_rows($konek);

if($cek>0){
    $response["kode"] = 1;
    $response["pesan"] = "Data tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($exequery)){
       $F['nim'] = $ambil->nim;
       $F['nama'] = $ambil->nama;
       $F['prodi'] = $ambil->prodi;
       $F['fakultas'] = $ambil->fakultas;

       array_push($response["data"],$F);
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($konek);

?>