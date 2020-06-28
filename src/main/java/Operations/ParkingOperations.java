package Operations;
import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Model.ParkingLot;
import Model.ParkingSlot;

/**
 * @author Shubham Saurav
 * Class to handle operations related to parking spots
 */
public class ParkingOperations {
	
	//Error Constants
	private static final String ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED = "Parking lot has not been initialized";
	private static final String ERR_PARKING_SPOT_OUTSIDE_RANGE_OF_PARKING_LOT_PROVIDED_TO_REMOVE = "Parking spot outside range of parking lot provided to remove";
	
	//Message Constants
	private static final String IS_FREE = " is free";
	private static final String SLOT_NUMBER = "Slot number ";
	private static final String ALLOCATED_SLOT_NUMBER = "Allocated Slot Number : ";
	private static final String SORRY_PARKING_LOT_IS_FULL = "Sorry, parking lot is full";

	/**
	 * @param parkingLot
	 * @param start
	 * @param end
	 * @return Next vacant space in Parking Lot. -1 if no space is found in parking lot
	 */
	private int getNextVacantSpace(ParkingLot parkingLot, int start, int end) {
		for (int i = start; i<end; i++) {
			if (parkingLot.getParkingLotList().get(i).getIsVacant()) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param parkingLot
	 * @param car
	 * @return Message after successful park car operation
	 * @throws Exception 
	 */
	public String parkCar(ParkingLot parkingLot,Car car) throws Exception {
		try {
			int lastVacantIndex = parkingLot.getLastVacantIndex();
			if (lastVacantIndex == -1) {
				return SORRY_PARKING_LOT_IS_FULL;
			}
			ParkingSlot parkingSlot = parkingLot.getParkingLotList().get(lastVacantIndex);
			parkingSlot.setCar(car);
			parkingSlot.setIsVacant(false);
			parkingLot.setLastVacantIndex(getNextVacantSpace(parkingLot, lastVacantIndex,parkingLot.getSizeOfParkingLot()));
			return (ALLOCATED_SLOT_NUMBER + parkingSlot.getSlotNo());
		}
		catch (NullPointerException e) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);
		}
		
	}
	
	/**
	 * @param parkingLot
	 * @param parkingSpcaeNumber
	 * @return Message after successful remove car operation
	 * @throws Exception 
	 */
	public String removeCar(ParkingLot parkingLot, int parkingSpcaeNumber) throws Exception {
		try {
			int lastVacantIndex = parkingLot.getLastVacantIndex();
			ParkingSlot parkingSlot = parkingLot.getParkingLotList().get(parkingSpcaeNumber);
			parkingSlot.setIsVacant(true);
			parkingSlot.setCar(null);
			if(lastVacantIndex == -1) {
				parkingLot.setLastVacantIndex(parkingSpcaeNumber);
			} else {
				parkingLot.setLastVacantIndex(getNextVacantSpace(parkingLot,0,lastVacantIndex+1));
			}
			return SLOT_NUMBER + parkingSlot.getSlotNo() + IS_FREE;	
		} catch(NullPointerException e) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);	
		} catch(IndexOutOfBoundsException e) {
			throw new Exception(ERR_PARKING_SPOT_OUTSIDE_RANGE_OF_PARKING_LOT_PROVIDED_TO_REMOVE);
			
		}
		
	}
	
	/**
	 * @param parkingLot
	 * @return List of Parking Spots which have been occupied
	 * @throws Exception 
	 */
	public List<ParkingSlot> getStatus(ParkingLot parkingLot) throws Exception{
		try {
			List<ParkingSlot> statusParkingSlot = new ArrayList<ParkingSlot>();
			List<ParkingSlot> parkingSlotList = parkingLot.getParkingLotList();
			for (ParkingSlot parkingSlot: parkingSlotList) {
				if(!parkingSlot.getIsVacant()) {
					statusParkingSlot.add(parkingSlot);
				}
			}
			return statusParkingSlot;
		} catch(NullPointerException e ) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);
		}
	}
	
	/**
	 * @param color
	 * @param parkingLot
	 * @return List of Parking Slot Numbers for Car with particular color.
	 * @throws Exception 
	 */
	public List<Integer> getSlotNumberForCarColor(String color, ParkingLot parkingLot) throws Exception{
		try {
			List<Integer> slotNumberList = new ArrayList<Integer>();
			List<ParkingSlot> parkingSlotList = parkingLot.getParkingLotList();
			for (ParkingSlot parkingSlot : parkingSlotList) {
				if ((!parkingSlot.getIsVacant()) && parkingSlot.getCar().getColor().equals(color)) {
					slotNumberList.add(parkingSlot.getSlotNo());
				}
			}
			return slotNumberList;
		} catch(NullPointerException e ) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);
		}
	}
	
	/**
	 * @param regNo
	 * @param parkingLot
	 * @return Slot Number for Car with specific registration number.
	 * @throws Exception 
	 */
	public int getSlotNumberForCarRegNo(String regNo, ParkingLot parkingLot) throws Exception{
		try {
			int slotNumber = -1;
			List<ParkingSlot> parkingSlotList = parkingLot.getParkingLotList();
			for (ParkingSlot parkingSlot : parkingSlotList) {
				if ((!parkingSlot.getIsVacant()) && parkingSlot.getCar().getRegistrationNumber().equals(regNo)) {
					slotNumber = parkingSlot.getSlotNo();
				}
			}
			return slotNumber;
		} catch(NullPointerException e ) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);
		}
	}

	/**
	 * @param color
	 * @param parkingLot
	 * @return List of Registration Numbers of Car found by color.
	 * @throws Exception 
	 */
	public List<String> getRegNoByCarColor(String color, ParkingLot parkingLot) throws Exception {
		try {
			List<String> carRegNoList = new ArrayList<String>();
			List<ParkingSlot> parkingSlotList = parkingLot.getParkingLotList();
			for (ParkingSlot parkingSlot : parkingSlotList) {
				if ((!parkingSlot.getIsVacant()) && parkingSlot.getCar().getColor().equals(color)) {
					carRegNoList.add(parkingSlot.getCar().getRegistrationNumber());
				}
			}
			return carRegNoList;
		} catch(NullPointerException e ) {
			throw new Exception(ERR_PARKING_LOT_HAS_NOT_BEEN_INITIALIZED);
		}
	}

}
