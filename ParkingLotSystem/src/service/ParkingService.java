package service;

import repository.ParkingRepository;
import model.Vehicle;
import model.Slot;
import model.SlotStatus;
import model.VehicleType;
import java.util.*;

public class ParkingService {
	
	ParkingRepository repo = new ParkingRepository();
	
	public String parkVehicle(String number, String type, int entryTime) {
		if(number != null && entryTime > 0) {
			VehicleType t = getType(type);
			Vehicle vehicle = new Vehicle(number, t, entryTime);
			for(Slot s : repo.getSlots()) {
				if(t == s.getType() && s.getStatus() == SlotStatus.FREE) {
					s.setStatus(SlotStatus.OCCUPIED);
					s.setVehicle(vehicle);
					return s.getName();
				}
			}
			return null;
		}
		return " ";
	}
	
	public boolean initializeSlots(int car, int bike, int truck) {
		if(car > 0 && bike > 0 && truck > 0) {
			VehicleType c = VehicleType.CAR;
			VehicleType b = VehicleType.BIKE;
			VehicleType t = VehicleType.TRUCK;
			for(int i=1;i<=car;i++) {
				repo.addSlot(new Slot("C"+i, c));
			}
			for(int i=1;i<=bike;i++) {
				repo.addSlot(new Slot("B"+i, b));
			}
			for(int i=1;i<=truck;i++) {
				repo.addSlot(new Slot("T"+i, t));
			}
			return true;
		}
		return false;
	}
	
	public boolean isFree(String type) {
		VehicleType t = getType(type);
		for(Slot s : repo.getSlots()) {
			if(s.getType() == t) {
				return true;
			}
		}
		return false;
	}
	
	static VehicleType getType(String type) {
		VehicleType t = null;
		if(type.equalsIgnoreCase("car")) {
			t = VehicleType.CAR;
		}else if(type.equalsIgnoreCase("bike")) {
			t = VehicleType.BIKE;
		}else if(type.equalsIgnoreCase("Truck")) {
			t = VehicleType.TRUCK;
		}
		return t;
	}
	
	public List<Slot> getSlots(){
		return repo.getSlots();
	}
	
	public boolean unparkVehicle(String number) {
		for(Slot s : repo.getSlots()) {
			if(s.getVehicle() != null && s.getVehicle().getNumber().equalsIgnoreCase(number)) {
				s.setStatus(SlotStatus.FREE);
				s.setVehicle(null);
				return true;
			}
		}
		return false;
	}
	
	public int calculateFee(String num, int exitTime) {
	    if(exitTime < 0) return -1;
	    Vehicle v = null;
	    for(Slot s : repo.getSlots()) {
	        if(s.getVehicle() != null &&
	           s.getVehicle().getNumber().equalsIgnoreCase(num)) {
	            v = s.getVehicle();
	            break;
	        }
	    }
	    if(v == null) return -1;
	    int hours = exitTime - v.getEntryTime();
	    if(hours <= 0) return -1;
	    int baseFee = 0;
	    int extraRate = 0;
	    switch(v.getType()) {
	        case BIKE:
	            baseFee = 20;
	            extraRate = 10;
	            break;

	        case CAR:
	            baseFee = 50;
	            extraRate = 30;
	            break;

	        case TRUCK:
	            baseFee = 100;
	            extraRate = 60;
	            break;
	    }
	    if(hours <= 1) {
	        return baseFee;
	    }
	    return baseFee + (hours - 1) * extraRate;
	}
	
	public int generateFee(String type, int time) {
		VehicleType t = getType(type);
		if(t == VehicleType.CAR) {
			return 50+((time-1)*30);
		}else if(t == VehicleType.BIKE) {
			return 20 + ((time-1)*10);
		}else if(t == VehicleType.TRUCK) {
			return 100 + ((time-1)*60);
		}
		return -1;
	}
}
