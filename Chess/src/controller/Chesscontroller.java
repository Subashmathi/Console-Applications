package controller;
import service.Chessservice;
import model.*;

public class Chesscontroller {
	Chessservice service = new Chessservice();
	
	public int getRandom() {
		return service.getRandom();
	}
	
	public void printBoard() {
		service.printBoard();
	}
	
	public void initializePieces() {
		service.initializePieces();
	}
	
	public String getSymbol(PieceType type) {
		return  Pieces.getSymbol(type);
	}
	
	public boolean checkMate(String from, String to) {
		return service.checkMate(from, to);
	}
	
	public boolean isValidMove(boolean isWhite, String from, String to) {
		return service.isValidMove(isWhite, from, to);
	}
	
	public void updatePiece(String from, String to, boolean isWhite) {
		service.updatePiece(from, to, isWhite);
	}
}
