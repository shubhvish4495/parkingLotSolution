import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import Model.Car;
import Model.ParkingLot;
import Model.ParkingSlot;
import Operations.ParkingOperations;

@RunWith(MockitoJUnitRunner.class)
public class ParkingOperationsTest {
	
	@InjectMocks
	ParkingOperations parkingOperations;
	
	@Mock
	ParkingLot  parkingLot;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void basicConfig() {
		parkingLot = new ParkingLot(6);
	} 
	
	@Test
	public void checkGetLastVacantIndex() throws Exception {
		String regNo = "Registration Number 1";
		String color = "Red";
		int expectedSlotNumber = 1;
		List<ParkingSlot> parkingLotList = new ArrayList<ParkingSlot>();
		parkingLotList.add(new ParkingSlot(new Car(regNo,color), false, 1));
		parkingLot.setParkingLotList(parkingLotList);
		int foundSlotNumber = parkingOperations.getSlotNumberForCarRegNo(regNo, parkingLot);
		assertEquals(expectedSlotNumber, foundSlotNumber);
	}
	
	@Test
	public void checkErrorForGetLastVacantIndex() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
		parkingLot = null;
		String regNo = "Registration Number 1";
		parkingOperations.getSlotNumberForCarRegNo(regNo, parkingLot);
	}

	@Test
	public void checkErrorForParkCarMethod() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
	    parkingOperations.parkCar(null, new Car());
	}
	
	@Test
	public void checkParkCarMethod() throws Exception{
		String expectedOutput = "Allocated Slot Number : 1";
		String actualOutput = parkingOperations.parkCar(parkingLot, new Car("regNo","color"));
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void checkParkCarParkingLotFull() throws Exception{
		ParkingLot parkingLot = new ParkingLot(1);
		parkingOperations.parkCar(parkingLot, new Car("regNo","color"));
		String expectedOutput = "Sorry, parking lot is full";
		String actualOutput = parkingOperations.parkCar(parkingLot, new Car("regNo","color"));
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test 
	public void checkForExceptionRemoveCar() throws Exception{
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
	    parkingOperations.removeCar(null, 1);
		
	}
	
	@Test
	public void checkForExceptionOutOfRangeRemoveCar() throws Exception{
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking spot outside range of parking lot provided to remove");
	    
	    int outOfBoundsParkingSpace = 7;
	    parkingOperations.removeCar(parkingLot, outOfBoundsParkingSpace);
	}
	
	@Test
	public void checkRemoveCarOperation() throws Exception{
		String expectedOutput = "Slot number 1 is free";
		parkingOperations.parkCar(parkingLot, new Car("regNo","color"));
		String actualOutput = parkingOperations.removeCar(parkingLot, 0);
		assertEquals(expectedOutput, actualOutput);	
	}
	
	@Test
	public void checkStatusException() throws Exception{
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
	    parkingOperations.getStatus(null);
	}
	
	@Test
	public void checkStatus() throws Exception{
		ParkingSlot parkingSlotTest =  new ParkingSlot(new Car("regNo","color"), false, 1);
		List<ParkingSlot> parkingSlotTestList = new ArrayList<ParkingSlot>();
		parkingSlotTestList.add(parkingSlotTest);
		parkingOperations.parkCar(parkingLot, new Car("regNo", "color"));
		List<ParkingSlot> actualList = parkingOperations.getStatus(parkingLot);
		assertEquals(actualList, parkingSlotTestList);
	}
	
	@Test
	public void checkGetSlotNoForCarColorException() throws Exception{
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
		parkingLot = null;
		parkingOperations.getSlotNumberForCarColor("color1", parkingLot);
	}
	
	@Test
	public void checkGetSlotNoForCarColor() throws Exception{
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(1);
		expectedList.add(2);
		
		parkingOperations.parkCar(parkingLot, new Car("regNo","color1"));
		parkingOperations.parkCar(parkingLot, new Car("regNo2","color1"));
		
		List<Integer> actual = parkingOperations.getSlotNumberForCarColor("color1", parkingLot);
		assertArrayEquals(expectedList.toArray(), actual.toArray());
		
	}
	
	@Test
	public void checkGetRegNoByCarColorException() throws Exception{
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Parking lot has not been initialized");
	    
		parkingLot = null;
		parkingOperations.getRegNoByCarColor("color", null);
	}
	
	@Test
	public void checkGetRegNoByCarColor() throws Exception{
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("regNo");
		expectedList.add("regNo2");
		
		parkingOperations.parkCar(parkingLot, new Car("regNo","color1"));
		parkingOperations.parkCar(parkingLot, new Car("regNo2","color1"));
		
		List<String> actual = parkingOperations.getRegNoByCarColor("color1", parkingLot);
		assertArrayEquals(expectedList.toArray(), actual.toArray());
		
	}
	
}
