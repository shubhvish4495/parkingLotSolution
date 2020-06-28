import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import Model.Car;
import Model.ParkingLot;
import Model.ParkingSlot;
import Operations.ParkingOperations;

/**
 * @author Shubham Saurav
 * Main class to handle all the commands related to ParkingLotTicketSystem App.
 */
public class ParkingLotTicketSystem {

	//Error constants
	private static final String ERR_WHILE_READING_COMMANDS_FROM_COMMAND_LINE = "Error while reading commands from command line";
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


	//ParkingOperations instance created
	private static ParkingOperations parkingOpertaions = new ParkingOperations();

	//ParkingLot instance created
	private static ParkingLot parkingLot = null;

	//List with supported commands
	private static List<String> supportedCommads = 
			Arrays.asList(CREATE_PARKING_LOT,PARK,LEAVE,STATUS_VAR,SLOT_NUMBERS_FOR_CARS_WITH_COLOUR,SLOT_NUMBER_FOR_REGISTRATION_NUMBER,REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR,EXIT);


	/**
	 * @param args
	 *
	 */
	public static void main(String args[]) {
		
		System.out.println
		("##################### Welcome to Parking Lot Application #####################"
		+ "\n <------- Commands Usage(Case Sensitive) ------->" 
		+ "\n 1. Create Parking Lot --> create_parking_lot 6 (6 = size of parking lot)"
		+ "\n 2. Park car --> park registrationNumber colour "
		+ "\n 3. Remove car from parking space --> remove 1 (1 = slot  number in parking lot)"
		+ "\n 4. Status of parking lot --> status"
		+ "\n 5. Get slot numbers having cars of same colour --> slot_numbers_for_cars_with_colour red"
		+ "\n 6. Get slot number of car via registration number --> slot_number_for_registration_number registrationNumber"
		+ "\n 7. Get registration number of all the cars having given color --> registration_numbers_for_cars_with_colour green"
		+ "\n##################### Start Typing Commands #####################\n");
		BufferedReader reader =  
				new BufferedReader(new InputStreamReader(System.in)); 
		
		while(true) {
			try {
				String commandInput = reader.readLine();
				System.out.println(performOperations(commandInput));
			}
			//Exception for reading commands
			catch(IOException e) {
				System.out.println(ERR_WHILE_READING_COMMANDS_FROM_COMMAND_LINE+"\n");
			} 
			

		}
	}

	public static String performOperations(String commandInput) {
		try {

			//check command input is empty
			if (!commandInput.isEmpty()) {

				String[] variableList = commandInput.split(" ");

				String command = variableList[0];

				//check if command is supported
				if (!supportedCommads.contains(command)) {
					return (ERR_COMMAND_NOT_SUPPORTED+"\n");
				}

				//create_parking_lot command handle
				if (variableList[0].equals(CREATE_PARKING_LOT)) {
					parkingLot = new ParkingLot(Integer.parseInt(variableList[1]));
					return("Created a parking lot with " + variableList[1] + " slots\n");
				}

				//park command handle
				if (variableList[0].equals(PARK)) {
					Car car = new Car(variableList[1], variableList[2]);
					return(parkingOpertaions.parkCar(parkingLot, car)+"\n");
				}

				//leave command handle
				if (variableList[0].equals(LEAVE)) {
					return(parkingOpertaions.removeCar(parkingLot,
							Integer.parseInt(variableList[1]) - 1)+"\n");
				}

				//status command handle
				if (variableList[0].equals(STATUS_VAR)) {
					List<ParkingSlot> statusParkingLot = parkingOpertaions.getStatus(parkingLot);
					StringBuilder sb = new StringBuilder();
					sb.append("Slot No.	Registration No.	Color\n");
//					System.out.println("Slot No.	Registration No.	Color");
					for (ParkingSlot parkingSlot : statusParkingLot) {
						Car car = parkingSlot.getCar();
						sb.append(parkingSlot.getSlotNo() 
								+ "	" + car.getRegistrationNumber() 
								+ "	"  + car.getColor() + "\n");
					}
					return sb.toString();
				}

				//slot_numbers_for_cars_with_colour command handle
				if (variableList[0].equals(SLOT_NUMBERS_FOR_CARS_WITH_COLOUR)) {
					List<Integer> slotList = parkingOpertaions
							.getSlotNumberForCarColor(variableList[1], parkingLot);
					String delim = "";
					StringBuilder sb = new StringBuilder();
					for (Integer i : slotList) {
						sb.append(delim).append(Integer.toString(i));
						delim = ",";
					}
					return(sb.toString()+"\n");
				}

				//slot_number_for_registration_number command handle
				if (variableList[0].equals(SLOT_NUMBER_FOR_REGISTRATION_NUMBER)) {
					int slotNumber = parkingOpertaions.getSlotNumberForCarRegNo(variableList[1], parkingLot);
					//case if car with registration number is not there in parking lot
					if (slotNumber == -1) {
						return(ERR_NOT_FOUND+"\n");
					} else {
						return(slotNumber+"\n");
					}
				}

				//registration_numbers_for_cars_with_colour command handle
				if (variableList[0].equals(REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR)) {
					List<String> carRegNoList = parkingOpertaions.getRegNoByCarColor(variableList[1], parkingLot);
					String delim = "";
					StringBuilder sb = new StringBuilder();
					for (String i : carRegNoList) {
						sb.append(delim).append(i);
						delim = ",";
					}
					return(sb.toString()+"\n");
				}

				//exit command handle
				if (variableList[0].equals(EXIT)) {
					System.exit(0);
				}
			} 
			//case where empty command is provided
			else {
				return(ERR_WILL_NEED_SOME_COMMAND_TO_OPERATE+"\n");
			}

		}
		//Exception for commands not provided with correct parameters length
		catch(ArrayIndexOutOfBoundsException e) {
			return(ERR_COMMAND_NOT_PROVIDED_WITH_CORRECT_PARAMETERS+"\n");
		} 
		//Exception for incorrect command variable type
		catch(NumberFormatException e) {
			return(ERR_NUMBER_IS_EXPECTED_AS_A_PARAMETER_IN_THIS_COMMAND+"\n");
		} 
		//Handle exceptions coming from operations class
		catch(Exception e) {
			return(e.getMessage()+"\n");
		}
		return "";
	}
}
