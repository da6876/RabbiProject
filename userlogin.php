<?php
//error_reporting(0);

include("connection.php");

$user_phone = $_POST['user_phone'];
$user_password = md5($_POST['user_password']);

$checkSQL1 = "SELECT count(user_info_id) as User FROM oaa_user_info where
		user_phone='$user_phone' and user_password = '$user_password'";

$result1 = $conn->query($checkSQL1);

if ($result1->num_rows > 0) {
	while ($row = $result1->fetch_assoc()) {
		$UserPhoneExt = $row["User"];
	}
}

if ($UserPhoneExt == 1) {

	$sql = "SELECT * FROM oaa_user_info where user_phone = '$user_phone' and user_password = '$user_password'";

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
			'values' => "User Login Failed !!"
		);
	}
} else {
	$post_data = array(
		'status_code' => '400',
		'msg' => 'Failed',
		'values' => "User Login Failed !!"
	);
}
    
	print json_encode($post_data);
?>