package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shubham Saurav
 * ParkingLot Class with attributes
 */
public class ParkingLot {
	
	private List<ParkingSlot> parkingLotList;
	
	private int lastVacantIndex;
	
	private int sizeOfParkingLot;
	
	/**
	 * @param sizeOfParkingLot
	 */
	public ParkingLot(int sizeOfParkingLot) {
		this.sizeOfParkingLot = sizeOfParkingLot;
		parkingLotList = new ArrayList<ParkingSlot>(sizeOfParkingLot);
		for (int m = 0; m < sizeOfParkingLot; m++) {
			ParkingSlot parkingSlot = new ParkingSlot(new Car(), true, m+1);
			parkingLotList.add(parkingSlot);
		}
		this.lastVacantIndex = 0;
	}

	/**
	 * @return the parkingLotList
	 */
	public List<ParkingSlot> getParkingLotList() {
		return parkingLotList;
	}

	/**
	 * @param parkingLotList the parkingLotList to set
	 */
	public void setParkingLotList(List<ParkingSlot> parkingLotList) {
		this.parkingLotList = parkingLotList;
	}

	/**
	 * @return the lastVacantIndex
	 */
	public int getLastVacantIndex() {
		return lastVacantIndex;
	}

	/**
	 * @param lastVacantIndex the lastVacantIndex to set
	 */
	public void setLastVacantIndex(int lastVacantIndex) {
		this.lastVacantIndex = lastVacantIndex;
	}

	/**
	 * @return the sizeOfParkingLot
	 */
	public int getSizeOfParkingLot() {
		return sizeOfParkingLot;
	}

	/**
	 * @param sizeOfParkingLot the sizeOfParkingLot to set
	 */
	public void setSizeOfParkingLot(int sizeOfParkingLot) {
		this.sizeOfParkingLot = sizeOfParkingLot;
	}
}
