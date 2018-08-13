<?php
    //open connection to mysql db
    $connection = mysqli_connect("localhost","root","","ganeshdarshan2018") or die("Error " . mysqli_error($connection));
	//$connection = mysqli_connect("localhost","root","","darshanbelgaum") or die("Error " . mysqli_error($connection));

    //fetch table rows from mysql db
    $sql = " SELECT * 
				FROM  images, place
					WHERE  images.ncategory_id =2 AND  images.ncity_id = 1 AND place.nplace_id=images.narea_id 
						ORDER BY images.nimg_id DESC";
    
	$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);

    //close the db connection
    mysqli_close($connection);
?>

