<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Conexión a la base de datos
$host = "localhost";
$dbname = "mi_base_datos";
$username = "root";
$password = "";

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Error al conectar a la base de datos"]);
    exit();
}

// Obtener los datos enviados
$data = json_decode(file_get_contents("php://input"), true);
$usuario = isset($data["usuario"]) ? $data["usuario"] : '';
$contraseña = isset($data["contraseña"]) ? $data["contraseña"] : '';

// Verificar que los campos no estén vacíos
if (empty($usuario) || empty($contraseña)) {
    echo json_encode(["success" => false, "message" => "Usuario y contraseña son obligatorios"]);
    exit();
}

// Verificar si el usuario ya existe
$stmt = $pdo->prepare("SELECT * FROM usuarios WHERE user = :usuario");
$stmt->bindParam(":usuario", $usuario);
$stmt->execute();

if ($stmt->rowCount() > 0) {
    echo json_encode(["success" => false, "message" => "El usuario ya existe"]);
    exit();
}

// Insertar nuevo usuario
try {
    $stmt = $pdo->prepare("INSERT INTO usuarios (user, contrasena) VALUES (:usuario, :contrasena)");
    $stmt->bindParam(":usuario", $usuario);
    $stmt->bindParam(":contrasena", $contraseña); // Cambiado para que coincida
    $stmt->execute();
    

    echo json_encode([
        "success" => true, 
        "message" => "Usuario registrado exitosamente"
    ]);
} catch (PDOException $e) {
    echo json_encode([
        "success" => false, 
        "message" => "Error al registrar usuario: " . $e->getMessage()
    ]);
}
?>