<?php
//error_reporting(0);
include("connection.php");
$user_phone1 = $_POST['user_phone'];
if($_POST['user_phone']=""){
	echo $sql = "SELECT * FROM oaa_user_info";
}else{
	$sql = "SELECT * FROM oaa_user_info where user_phone = '$_POST[user_phone]'";
}

$result = $conn->query($sql);

if ($result->num_rows > 0) {
	while ($row = $result->fetch_assoc()) {
		$value[] = $row;
	}

	$post_data = array(
		'status_code' => '200',
		'msg' => 'Success',
		'values' => $value
	);
} else {
	$post_data = array(
		'status_code' => '400',
		'msg' => 'Failed',
		'values' => "Data not Found !!"
	);
}

    
print json_encode($post_data);
?>