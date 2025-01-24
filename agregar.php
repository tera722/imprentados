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
$costo = isset($data["costo"]) ? $data["costo"] : '';
$cantidad = isset($data["cantidad"]) ? $data["cantidad"] : '';
$color = isset($data["color"]) ? $data["color"] : '';
$fechaentrega = isset($data["fechaentrega"]) ? $data["fechaentrega"] : '';
$fechapedido = isset($data["fechapedido"]) ? $data["fechapedido"] : '';
$cliente = isset($data["cliente"]) ? $data["cliente"] : '';
$producto = isset($data["producto"]) ? $data["producto"] : '';
$empleado = isset($data["empleado"]) ? $data["empleado"] : '';
$detalles = isset($data["detalles"]) ? $data["detalles"] : '';

// Verificar que los campos no estén vacíos
if (empty($costo) || empty($cantidad) || empty($color) || empty($fechaentrega) || empty($fechapedido) || empty($cliente) || empty($producto) || empty($empleado)) {
    echo json_encode(["success" => false, "message" => "Todos los datos son obligatorios"]);
    exit();
}

// Validar que las claves foráneas existan (opcional pero recomendable)
try {
    $queries = [
        "SELECT 1 FROM cliente WHERE id = :id LIMIT 1" => $cliente,
        "SELECT 1 FROM producto WHERE id = :id LIMIT 1" => $producto,
        "SELECT 1 FROM empleado WHERE id = :id LIMIT 1" => $empleado
    ];
    foreach ($queries as $query => $value) {
        $stmt = $pdo->prepare($query);
        $stmt->bindParam(":id", $value);
        $stmt->execute();
        if ($stmt->rowCount() === 0) {
            echo json_encode(["success" => false, "message" => "Valor no válido en una clave foránea"]);
            exit();
        }
    }
} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Error al validar claves foráneas"]);
    exit();
}

// Insertar nuevo pedido
try {
    $stmt = $pdo->prepare("INSERT INTO pide (Costo_total, Cantidad, Color, Fecha_entrega, Fecha_pedido, Detalles, Cliente, Producto, Empleado) 
                           VALUES (:costo, :cantidad, :color, :fechaentrega, :fechapedido, :detalles, :cliente, :producto, :empleado)");
    $stmt->bindParam(":costo", $costo);
    $stmt->bindParam(":cantidad", $cantidad);
    $stmt->bindParam(":color", $color);
    $stmt->bindParam(":fechaentrega", $fechaentrega);
    $stmt->bindParam(":fechapedido", $fechapedido);
    $stmt->bindParam(":detalles", $detalles);
    $stmt->bindParam(":cliente", $cliente);
    $stmt->bindParam(":producto", $producto);
    $stmt->bindParam(":empleado", $empleado);
    $stmt->execute();

    echo json_encode([
        "success" => true, 
        "message" => "Pedido registrado exitosamente"
    ]);
} catch (PDOException $e) {
    echo json_encode([
        "success" => false, 
        "message" => "Error al registrar pedido: " . $e->getMessage()
    ]);
}
