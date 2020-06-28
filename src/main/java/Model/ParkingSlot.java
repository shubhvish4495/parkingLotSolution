package Model;

import java.util.Objects;

/**
 * @author Shubham Saurav
 * ParkingSlot with attributes
 */
public class ParkingSlot {
	
	private Car car;
	
	private Boolean isVacant;
	
	private int slotNo;
	
	/**
	 * @param car
	 * @param isVacant
	 * @param slotNo
	 */
	public ParkingSlot(Car car, Boolean isVacant, int slotNo) {
		this.car = car;
		this.isVacant = isVacant;
		this.slotNo = slotNo;
	}

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the isVacant
	 */
	public Boolean getIsVacant() {
		return isVacant;
	}

	/**
	 * @param isVacant the isVacant to set
	 */
	public void setIsVacant(Boolean isVacant) {
		this.isVacant = isVacant;
	}

	/**
	 * @return the slotNo
	 */
	public int getSlotNo() {
		return slotNo;
	}

	/**
	 * @param slotNo the slotNo to set
	 */
	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot parkingSlot = (ParkingSlot) o;
        return isVacant == parkingSlot.isVacant &&
                Objects.equals(car, parkingSlot.car) && 
                slotNo == parkingSlot.slotNo;
    }


}
