<?php
	header('X-Frame-Options: GOFORIT'); 
	$ConUrl = "http://congress.api.sunlightfoundation.com/";
	// $ConUrl = "http://104.198.0.197:8080/";
	$Legislator = "legislators?";
	$apikey = "apikey=2604449d386949c086c6b12faa3479f6";
	$perpage = "&per_page=all";
	$house = "chamber=house&";
	$senate = "chamber=senate&";

	$BillsActive = "bills?history.active=true&";
	$BillsNew = "bills?history.active=false&";
	$pdfExist = "last_version.urls.pdf__exists=true&";
	$perpage50 = "&per_page=50";

	$CommHouse = "committees?chamber=house&";
	$CommSenate = "committees?chamber=senate&";
	$CommJoint = "committees?chamber=joint&";

	$bill = "bills?sponsor_id=";
	$perpage5 = "&per_page=5";

	$comm = "committees?member_ids=";


	if (isset($_GET['lt'])) 
	{
		if ($_GET['lt']==1) 
		{
			// echo "1";
			$response = file_get_contents($ConUrl . $Legislator . $apikey . $perpage);
			echo $response;
		}
		else if ($_GET['lt']==2) 
		{
			$response = file_get_contents($ConUrl . $Legislator . $house . $apikey . $perpage);
			echo $response;
		}
		else if ($_GET['lt']==3) 
		{
			$response = file_get_contents($ConUrl . $Legislator. $senate . $apikey . $perpage);
			echo $response;
		}
	}
	else if (isset($_GET['bt'])) 
	{
		if ($_GET['bt']==1) 
		{
			$response = file_get_contents($ConUrl . $BillsActive . $pdfExist . $apikey . $perpage50);
			echo $response;
		}
		else if ($_GET['bt']==2) 
		{
			$response = file_get_contents($ConUrl . $BillsNew . $pdfExist .$apikey . $perpage50);
			echo $response;
		}
	}
	else if (isset($_GET['ct']))
	{
		if ($_GET['ct']==1) 
		{
			$response = file_get_contents($ConUrl. $CommHouse . $apikey . $perpage);
			echo $response;
		}
		else if ($_GET['ct']==2) 
		{
			$response = file_get_contents($ConUrl. $CommSenate . $apikey . $perpage);
			echo $response;
		}
		else if ($_GET['ct']==3) 
		{
			$response = file_get_contents($ConUrl. $CommJoint .$apikey . $perpage);
			echo $response;
		}
	}
	else if (isset($_GET['bID'])) 
	{
		$response = file_get_contents($ConUrl . $bill . $_GET['bID'] . "&" . $pdfExist . $apikey . $perpage5);
		echo $response;
	}
	else if (isset($_GET['cID']))
	 {
		$response = file_get_contents($ConUrl . $comm . $_GET['cID'] . "&" . $pdfExist . $apikey . $perpage5);
		echo $response;
	}
	else
	{
		// $response = file_get_contents($ConUrl . $BillsActive . $apikey . $perpage50);
		// echo $response;
		$response = file_get_contents($ConUrl. $CommHouse . $apikey . $perpage);
		echo $response;
		echo "NO!";
	}

?>