import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotApplicationTest {
	
	//Error constants
		private static final String ERR_WILL_NEED_SOME_COMMAND_TO_OPERATE = "Will need some command to operate";
		private static final String ERR_NUMBER_IS_EXPECTED_AS_A_PARAMETER_IN_THIS_COMMAND = "Number is expected as a parameter in this command";
		private static final String ERR_COMMAND_NOT_PROVIDED_WITH_CORRECT_PARAMETERS = "Command not provided with correct parameters";
		private static final String ERR_COMMAND_NOT_SUPPORTED = "Command not supported";
		private static final String ERR_NOT_FOUND = "Not found";

		//Commands supported constants
		private static final String EXIT = "exit";
		private static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour";
		private static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";
		private static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour";
		private static final String STATUS_VAR = "status";
		private static final String LEAVE = "leave";
		private static final String PARK = "park";
		private static final String CREATE_PARKING_LOT = "create_parking_lot";

	
	@Before
	public void basicSetup() {
		String commandInput = CREATE_PARKING_LOT+" 6";
		ParkingLotTicketSystem.performOperations(commandInput);
	}
	
	@Test
	public void noCommandInput() {
		String commandInput = "";
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		
		assertEquals(ERR_WILL_NEED_SOME_COMMAND_TO_OPERATE+"\n", actualOutput);
	}
	
	@Test
	public void insufficientParameterInput() {
		String commandInput = "park";
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(ERR_COMMAND_NOT_PROVIDED_WITH_CORRECT_PARAMETERS+"\n", actualOutput);
	}
	
	@Test
	public void unsupportedCommandInput() {
		String commandInput = "pardffdk";
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(ERR_COMMAND_NOT_SUPPORTED+"\n", actualOutput);
	}
	
	@Test
	public void wrongParamterTypeCommandInput() {
		String commandInput = "leave x";
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(ERR_NUMBER_IS_EXPECTED_AS_A_PARAMETER_IN_THIS_COMMAND+"\n", actualOutput);
	}
	
	@Test
	public void createParkingLot() {
		String commandInput = CREATE_PARKING_LOT+" 6";
		String expectedOutput = "Created a parking lot with 6 slots\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void parkCar() {
		String commandInput = PARK + " regNo" + " color1" ;
		String expectedOutput = "Allocated Slot Number : 1\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void leaveCar() {
		String commandInputSetup = PARK + " regNo" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		
		String commandInput = LEAVE + " 1";
		String expectedOutput = "Slot number 1 is free\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void status() {
		String commandInputSetup = PARK + " regNo" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		
		String commandInput = STATUS_VAR;
		StringBuilder sb = new StringBuilder();
		sb.append("Slot No.	Registration No.	Color\n");
		sb.append(1 + "	" + "regNo" + "	"  + "color1" + "\n");
		String expectedOutput = sb.toString();
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void checkSlotNoForCarColor() {
		String commandInputSetup = PARK + " regNo" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		commandInputSetup = PARK + " regNo2" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		
		String commandInput = SLOT_NUMBERS_FOR_CARS_WITH_COLOUR + " color1";
		String expectedOutput = "1,2\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void checkSlotNoForRegistrationNo() {
		String commandInputSetup = PARK + " regNo" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		commandInputSetup = PARK + " regNo2" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		
		String commandInput = SLOT_NUMBER_FOR_REGISTRATION_NUMBER + " regNo2";
		String expectedOutput = "2\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void checkRegNoForCarsWithColor() {
		String commandInputSetup = PARK + " regNo" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		commandInputSetup = PARK + " regNo2" + " color1" ;
		ParkingLotTicketSystem.performOperations(commandInputSetup);
		
		String commandInput = REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR + " color1";
		String expectedOutput = "regNo,regNo2\n";
		
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void checkForErrorFromOperationsClass() {
		String commandInput = LEAVE +" 7";
		String expectedOutput = "Parking spot outside range of parking lot provided to remove\n";
		String actualOutput = ParkingLotTicketSystem.performOperations(commandInput);
		
		assertEquals(expectedOutput, actualOutput);
	}
	
	
	
}
