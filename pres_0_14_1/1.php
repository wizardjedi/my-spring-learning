<?php
try {
    $dbh = new PDO('mysql:host=127.0.0.1;port=1234;dbname=test', 'root', 'fakePass');
    while (true)
	    foreach($dbh->query('SELECT * from FOO where name like "%Привет Мир%";') as $row) {
        	print_r($row);
	    }
    $dbh = null;
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
}

