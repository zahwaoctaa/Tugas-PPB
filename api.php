<?php

$file_db = "kasir.db";

try {
    $pdo = new PDO("sqlite:$file_db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

    $sql_create = "CREATE TABLE IF NOT EXISTS `user`(
        `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
        `username` text NOT NULL, 
        `password` text NOT NULL,
        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    $pdo->exec($sql_create);

    // Uncomment kode dibawah untuk buat user baru
    $create_user = "INSERT INTO user (username, password) VALUES ('admin', 'admin')";
    $pdo->exec($create_user);
} catch (PDOException $e) {
    throw new PDOException($e->getMessage(), (int)$e->getCode());
}

header('Content-Type: application/json');

/** 
 * Method REST:
 * 
 * - GET: untuk mendapatkan data dari server
 * - POST: untuk menginputkan data baru
 * - PUT: untuk mengupdate data yang sudah ada
 * - DELETE: untuk menghapus data
 * 
*/

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    # fungsi login
    $username = $_POST['username'];
    $password = $_POST['password'];
    $query = "select * from user where username = ? and password = ?";
    $stmt = $pdo->prepare($query);
    $res = $stmt->execute([$username, $password]);
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    if ($data){
        $data = ['username'=>$username, 'password'=>$password];
        echo json_encode($data);
    } else {
        echo json_encode(['error'=>$stmt->errorCode()]);
    }
} 