package Model;

import java.util.Objects;

/**
 * @author Shubham Saurav
 * Car Class with attributes
 */
public class Car {
	
	private String registrationNumber;
	
	private String color;
	
	public Car() {}
	
	/**
	 * @param registrationNumber
	 * @param color
	 */
	public Car(String registrationNumber, String color) {
		this.registrationNumber = registrationNumber;
		this.color = color;
	}

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return color.equals(car.color) && 
                registrationNumber.equals(car.registrationNumber);
    }

	

}
