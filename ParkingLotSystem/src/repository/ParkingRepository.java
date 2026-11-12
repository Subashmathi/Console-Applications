package repository;

import java.util.*;
import model.*;

public class ParkingRepository {
	private List<Slot> slots = new ArrayList<>();
	
	public void addSlot(Slot slot) {
		slots.add(slot);
	}
	
	public List<Slot> getSlots(){
		return new ArrayList<>(slots);
	}
}
