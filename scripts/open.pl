#!/usr/bin/perluse strict;

use warnings;
use diagnostics;
use open ':std', ':encoding(UTF-8)';
use Spreadsheet::ParseExcel;
use Spreadsheet::ParseXLSX;

my $parser = Spreadsheet::ParseExcel->new();
my $workbook = $parser->parse('ECGL 5.0 for PPC Registration - P204x.xls');   

my $avl_p1=1;
my $avl_p2=1;
my $index_p1=1;
my $index_p2=1;


#Worksheet Requirements
my $worksheet = $workbook->worksheet(1);


my $id = $worksheet->get_cell( 1, 0 );
my $i = 1;
my $aux = substr $id->value(), 0, 3;
my @p1 = ();
my @p2 = ();

#AVL - Availability Requirements
while ( $aux eq "AVL") {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();
		$index_p2=$index_p2+1;	
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $cls_p1=$index_p1;
my $cls_p2=$index_p2;


#CLS - Clustering Requirements
while ( ( $aux eq "CFH" ) || ( $aux eq "CSM" ) || ( $aux eq "CCM" ) || ( $aux eq "CAF" ) || ( $aux eq "CDI" ) ) {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();
		$index_p2=$index_p2+1;	
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $srv_p1=$index_p1;
my $srv_p2=$index_p2;

#SRV - Serviceabilitu Requirements
while ( ( $aux eq "SMM" ) || ( $aux eq "SPM" ) || ( $aux eq "SFA" ) ) {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();
		$index_p2=$index_p2+1;	
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $prf_p1=$index_p1;
my $prf_p2=$index_p2;

#PRF - Performance Requirements
while ( $aux eq "PRF" ) {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();
		$index_p2=$index_p2+1;	
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $std_p1=$index_p1;
my $std_p2=$index_p2;

#STD - Standards Requirements
while ( $aux eq "STD" ) {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();		
		$index_p2=$index_p2+1;
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $sec_p1=$index_p1;
my $sec_p2=$index_p2;

#SEC - Security Requirements
while ( $aux eq "SEC" ) {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();		
		$index_p2=$index_p2+1;
	}

	$i=$i+1;
	$id = $worksheet->get_cell( $i, 0 );
	$aux = substr $id->value(), 0, 3;

}

my $pms_p1=$index_p1;
my $pms_p2=$index_p2;

#HW - Hardware Requirements
my ( $row_min, $row_max ) = $worksheet->row_range();
while ( $aux eq "PMS") {
	$priority = $worksheet->get_cell( $i, 2 );

	if ( $priority->value() eq "P1") {
		$p1[1][$index_p1] = $id->value();
		get_package_name_p1();
		$index_p1=$index_p1+1;
	}
	else {
		$p2[1][$index_p2] = $id->value();
		get_package_name_p2();
		$index_p2=$index_p2+1;	
	}
	$i=$i+1;
	if ( $i != $row_max+1 ) {
		$id = $worksheet->get_cell( $i, 0 );
		$aux = substr $id->value(), 0, 3;
	}

}

#Extragerea numelor pachetelor pentru P1.
sub get_package_name_p1{

   	$id = $worksheet->get_cell( $i, 3 );
   	$aux = $id->value();
   	$name=substr $aux, 0, 8;

   	if ( $aux eq "not" ) {
   		$p1[2][$index_p1] ="not";
   	}
   	else {
  		if ( $name eq "Kernel (" ) {
  			$p1[2][$index_p1] ="kernel";
  		}
  		else {
   			if ( $aux eq "Kernel / Package Combination") {
   				$id = $worksheet->get_cell( $i, 4 );
   				$aux = $id->value();
   				$p1[2][$index_p1] = $aux . "_k";
   			}
   			else {
   				$id = $worksheet->get_cell( $i, 4 );
   				$aux = $id->value();
   				$p1[2][$index_p1] = $aux;
   			}
   		}
   	}	
}

#Extragerea numelor pachetelor pentru P2.
sub get_package_name_p2{

   	$id = $worksheet->get_cell( $i, 3 );
   	$aux = $id->value();
   	$name=substr $aux, 0, 8;

   	if ( $aux eq "Not" ) {
   		$p2[2][$index_p2] ="Not";
   	}
   	else {
  		if ( $name eq "Kernel (" ) {
  			$p2[2][$index_p2] ="kernel";
  		}
  		else {
   			if ( $aux eq "Kernel / Package Combination") {
   				$id = $worksheet->get_cell( $i, 4 );
   				$aux = $id->value();
   				$p2[2][$index_p2] = $aux . "_k";
   			}
   			else {
   				$id = $worksheet->get_cell( $i, 4 );
   				$aux = $id->value();
   				$p2[2][$index_p2] = $aux;
   			}
   		}
   	}	
}

#Gaps
my @g = ();

$worksheet = $workbook->worksheet(2);

$id = $worksheet->get_cell( 1, 0 );
my $index_g = 1;

( $row_min, $row_max ) = $worksheet->row_range(); 

while ( $index_g <= $row_max ) {
	$g[$index_g] = $id->value();
	$index_g=$index_g+1;
	if ( $index_g != $row_max+1 ) {
		$id = $worksheet->get_cell( $index_g, 0 );
	}
}