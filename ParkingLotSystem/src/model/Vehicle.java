package model;

public class Vehicle {
	private String number;
	private VehicleType type;
	private double fee;
	private int entryTime;
	private int exitTime;
	
	public Vehicle(String number, VehicleType type, int entryTime) {
		this.number = number;
		this.type = type;
		this.fee = 0;
		this.entryTime = entryTime;
		this.exitTime = 0;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(int entryTime) {
		this.entryTime = entryTime;
	}

	public int getExitTime() {
		return exitTime;
	}

	public void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}
	
	@Override
	public String toString() {
		return String.format("%-11s | %-6s | %-3s", number, type, entryTime);
	}
}
