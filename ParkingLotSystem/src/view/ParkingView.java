package view;

import java.util.*;
import controller.ParkingController;
import model.*;

public class ParkingView {
	public void start() {
		Scanner scan = new Scanner(System.in);
		ParkingController cont = new ParkingController();
		System.out.println("Initializing Parking Slots....");
		System.out.println("Enter number of car slots: ");
		int car = scan.nextInt();
		System.out.println("Enter number of bike slots: ");
		int bike = scan.nextInt();
		System.out.println("Enter number of truck slots: ");
		int truck = scan.nextInt();
		if(cont.initializeSlots(car, bike, truck)) {
			System.out.println("Slots Initialized Successfully!");
			List<Slot> slots = cont.getSlots();
			if(slots.isEmpty()) {
				System.out.println("No vehicle Parked right now");
			}else {
				int i = 0;
				for(Slot s : slots) {
					System.out.printf("%-4s",s.getName());
					i++;
					if(i % 5 == 0) {
						System.out.println();
					}
				}
			}
		}else {
			System.out.println("Slot Initialization Error");
		}
		while(true) {
			System.out.println("\n\n1. Park Vehicle\r\n"
					+ "2. Unpark Vehicle\r\n"
					+ "3. View Parking Status\r\n"
					+ "4. Search Vehicle Location\r\n"
					+ "5. Parking Fee Calculation\r\n"
					+ "6. Exit\r\n");
			int input = scan.nextInt();
			switch(input) {
			case 1 -> {
				System.out.println("Enter Vehicle Type: ");
				String type = scan.next();
				if(!(type.equalsIgnoreCase("car") || type.equalsIgnoreCase("bike") || type.equalsIgnoreCase("truck"))) {
					System.out.println("Invalid Type!");
					return;
				}
				if(!cont.isFree(type)) {
					System.out.println("No Parking Lot Available Now!");
					return;
				}
				System.out.println("Enter Vehicle Number: ");
				String number = scan.next();
				System.out.println("Enter Entry Time: ");
				int entry = scan.nextInt();
				String slot = cont.parkVehicle(number, type, entry);
				if(slot != null) {
					System.out.println("Vehicle Parked at: "+slot);
				}else if(slot == " ") {
					System.out.println("Check Number type or Entry time");
				}
			}
			case 2 -> {
			    System.out.println("Enter Vehicle Number: ");
			    String num = scan.next();
			    System.out.println("Enter Exit Time: ");
			    int exitTime = scan.nextInt();
			    int fee = cont.calculateFee(num, exitTime);
			    if(fee == -1) {
			        System.out.println("Invalid vehicle number or exit time");
			        break;
			    }
			    if(cont.unparkVehicle(num)) {
			        System.out.println("Fee: " + fee);
			        System.out.println("Vehicle Unparked Successfully");
			    } else {
			        System.out.println("Vehicle Not Found");
			    }
			}
			case 3 ->{
				List<Slot> slots = cont.getSlots();
				System.out.println("FREE SLOTS");
				int i = 0;
				for(Slot s : slots) {
					i++;
					if(s.getStatus() == SlotStatus.OCCUPIED) {
						System.out.printf("%-4s", " ");
					}else {
						System.out.printf("%-4s",s.getName());	
					}
					if(i % 5 == 0) {
						System.out.println();
					}
				}
				System.out.println("\n\nOCCUPIED SLOTS");
				int j = 0;
				for(Slot s : slots) {
					j++;
					if(s.getStatus() == SlotStatus.FREE) {
						System.out.printf("%-4s", " ");
					}else {
						System.out.printf("%-4s",s.getName());
					}
					if(j % 5 == 0) {
						System.out.println();
					}
				}
				outer:
				while(true) {
				System.out.println("\n1.View Slot Detail  2.Exit");
				int in = scan.nextInt();
				switch(in) {
				case 1 -> {
					System.out.println("Enter Slot number.");
					String slot = scan.next();
					for(Slot s : slots) {
						if(s.getName().equalsIgnoreCase(slot)) {
							System.out.print(s.getVehicle());
						}
					}
				}
				case 2 -> {
					System.out.println("Exiting....");
					break outer;
				}
				}
				}
			}
			case 4 ->{
				List<Slot> slots = cont.getSlots();
				System.out.println("Enter Vehicle Number: ");
				scan.nextLine();
				boolean found = false;
				String num = scan.next();
				for(Slot s : slots) {
					if(s.getVehicle() != null && s.getVehicle().getNumber().equals(num)) {
						System.out.println("Vehicle parked at: "+s.getName());
						found = true;
					}
				}
				if(!found) {
					System.out.println("No vehicle in this number exist!");
				}
			}
			case 5 ->{
				System.out.println("Enter vehicle type: ");
				scan.nextLine();
				String type = scan.nextLine();
				System.out.println("Enter park time: ");
				int time = scan.nextInt();
				int fee = cont.generateFee(type, time);
				if(fee == -1) {
					System.out.println("Check credentials");
				}else {
					System.out.println("Fee: "+fee);
				}
			}
			case 6 ->{
				System.exit(0);
			}
			default ->{
				System.out.println("Invalid Choice!");
			}
			}
		}
	}
}
