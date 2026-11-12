package view;
import java.util.*;
import controller.Chesscontroller;

public class ChessView {
	Chesscontroller cont = new Chesscontroller();
	public void start() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Chaturanga");
		System.out.println("1.Single Player  2.Double Player");
		int i = scan.nextInt();
		scan.nextLine();
		switch(i) {
		case 1 ->{
			List<Integer> list = new ArrayList<>();
			Iterator<Integer> itr = list.iterator();
		}
		case 2 -> {
		    boolean w = false;
		    System.out.println("Enter Player 1 name: ");
		    String p1 = scan.nextLine();
		    System.out.println("Enter Player 2 name: ");
		    String p2 = scan.nextLine();
		    if(p1.equals("")) p1 = "Player 1";
		    if(p2.equals("")) p2 = "Player 2";
		    String white = (cont.getRandom() == 1 ? p2 : p1);
		    String black = (white.equals(p1) ? p2 : p1);
		    System.out.println("White : " + white);
		    System.out.println("Black : " + black + "\n");
		    cont.initializePieces();
		    cont.printBoard();
		    while(true) {
		        System.out.println(white + " move (example: e2 e4): ");
		        String[] input = scan.nextLine().trim().toLowerCase().split(" ");
		        while(input.length != 2 || !cont.isValidMove(true, input[0], input[1])) {
		            System.out.println("Invalid Move. Try again.");
		            System.out.println(white + " move (example: e2 e4): ");
		            input = scan.nextLine().trim().toLowerCase().split(" ");
		        }
		        String from = input[0];
		        String to   = input[1];
		        cont.updatePiece(from, to, true);
		        cont.printBoard();
		        if(cont.checkMate(from, to)) {
		            w = true;
		            System.out.println(white + " Wins!");
		            break;
		        }
		        System.out.println(black + " move (example: e7 e5): ");
		        input = scan.nextLine().trim().toLowerCase().split(" ");
		        while(input.length != 2 || !cont.isValidMove(false, input[0], input[1])) {
		            System.out.println("Invalid Move. Try again.");
		            System.out.println(black + " move (example: e7 e5): ");
		            input = scan.nextLine().trim().toLowerCase().split(" ");
		        }
		        String from1 = input[0];
		        String to1   = input[1];
		        cont.updatePiece(from1, to1, false);
		        cont.printBoard();
		        if(cont.checkMate(from1, to1)) {
		            System.out.println(black + " Wins!");
		            break;
		        }
		    }
		    if(w) {
		        System.out.println(
		            "Well played " + black +
		            "\nEvery grandmaster was once defeated more times than beginners dare to play."
		        );
		    } else {
		        System.out.println(
		            "Well played " + white +
		            "\nEvery grandmaster was once defeated more times than beginners dare to play."
		        );
		    }
		}
		}
	}
}
