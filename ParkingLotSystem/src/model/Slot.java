package model;

public class Slot {
	private String name;
	private Vehicle vehicle;
	private SlotStatus status;
	private VehicleType type;
	
	public Slot(String name, VehicleType type) {
		this.name = name;
		this.status = SlotStatus.FREE;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public SlotStatus getStatus() {
		return status;
	}

	public void setStatus(SlotStatus status) {
		this.status = status;
	}
	
	public VehicleType getType() {
		return this.type;
	}
}
