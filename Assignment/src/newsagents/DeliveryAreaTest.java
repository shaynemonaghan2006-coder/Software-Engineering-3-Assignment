package newsagents;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeliveryAreaTest {
	 static DeliveryArea area = new DeliveryArea();
//	TC01
//	Verify area name is accepted when exactly 1 character long
//	area Name = "C"	
//	area name accepted
	@Test
	void validAreaNameMinBoundaryTC01() {
		assertTrue(area.verifyAreaName("C"));
	}
	
//	TC02
//	Verify area name is accepted when more than 1 character long
//	area Name = "Creggan"	
//	area name accepted
	@Test
	void validAreaNameTC02() {
		assertTrue(area.verifyAreaName("Creggan"));
	}
	
//	TC03
//	Verify area name is rejected when blank
//	area Name = ""	
//	area name rejected
	@Test
	void invalidAreaNameTC03() {
		assertFalse(area.verifyAreaName(""));
	}
	
//	TC04
//	Verify region name is accepted when exactly 1 character long
//	region Name = "S"	
//	region name accepted
	@Test
	void validRegionNameMinBoundaryTC04() {
		assertTrue(area.verifyRegionName("S"));
	}
	
//	TC05
//	Verify region name is accepted when more than 1 character long
//	region Name = "South Athlone"	
//	region name accepted
	@Test
	void validRegionNameTC05() {
		assertTrue(area.verifyRegionName("South Athlone"));
	}
	
//	TC06
//	Verify region name is rejected when blank
//	region Name = ""	
//	region name rejected
	@Test
	void invalidRegionNameTC06() {
		assertFalse(area.verifyRegionName(""));
	}

	
//	TC07
//	Verify driver id is accepted when integer greater than 0
//	driverId = 1
//	driver id accepted
	@Test
	void validDriverIdTC07() {
		assertTrue(area.verifyDriverId(1));
	}
	
//	TC08
//	Verify driver id is accepted only if the driver with the id exists
//	driverId = 1
//	driver id accepted
	@Test
	void validDriverIdExistsTC08() {
		assertTrue(area.verifyDriverId(1));
	}
	
//	TC09
//	Verify driver id is rejected when 0
//	driverId = 0
//	driver id rejected
	@Test
	void invalidDriverIdTC09() {
		assertFalse(area.verifyDriverId(0));
	}
	
//	TC10
//	Verify driver id is rejected is driver with the id doesn’t exist
//	driverId = 1
//	driver id rejected
	@Test
	void validDriverIdNotExistsTC10() {
		assertFalse(area.verifyDriverId(2));
	}
	
//	TC11
//	Verify delivery area is created when all details are valid
//	“Creggan”, “South Athlone”,1
//	Delivery Area created successfully
	@Test
	void createValidDeliveryAreaTC11() {
		assertTrue(area.createDeliveryArea("Creggan", "South Athlone", 1));
	}
	
//	TC12
//	Verify delivery area creation fails with invalid area name
//	“”, “South Athlone”,1
//	Delivery Area not created
	@Test
	void rejectInvalidAreaNameTC12() {
		assertFalse(area.createDeliveryArea("", "South Athlone", 1));
	}
	
//	TC13
//	Verify delivery area creation fails with invalid region name
//	“Creggan”, “”,1
//	Delivery Area not created
	@Test
	void rejectInvalidRegionNameTC13() {
		assertFalse(area.createDeliveryArea("Creggan", "",1));
	}
	
//	TC14
//	Verify delivery area creation fails with invalid driverId
//	“Creggan”, “South Athlone”,0
//	Delivery Area not created
	@Test
	void rejectInvalidDriverIdTC14() {
		assertFalse(area.createDeliveryArea("Creggan", "South Athlone", 0));
	}
}


