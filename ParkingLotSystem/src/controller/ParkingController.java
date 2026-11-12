package controller;

import model.*;
import java.util.*;
import service.ParkingService;

public class ParkingController {
	
	ParkingService service = new ParkingService();
	
	public boolean isFree(String type) {
		return service.isFree(type);
	}
	
	public boolean initializeSlots(int car, int bike, int truck) {
		return service.initializeSlots(car, bike, truck);
	}
	
	public String parkVehicle(String number, String type, int entry) {
		return service.parkVehicle(number, type, entry);
	}
	
	public List<Slot> getSlots(){
		return service.getSlots();
	}
	
	public boolean unparkVehicle(String number) {
		return service.unparkVehicle(number);
	}
	
	public int calculateFee(String num, int exitTime) {
		return service.calculateFee(num, exitTime);
	}
	
	public int generateFee(String type, int time) {
		return service.generateFee(type, time);
	}
}
