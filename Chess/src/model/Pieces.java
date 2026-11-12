package model;

public abstract class Pieces {
	private PieceType type;
	private boolean isWhite;
	private int row, col;
	
	Pieces(PieceType type, boolean isWhite, int row, int col){
		this.type = type;
		this.isWhite = isWhite;
	}
	
	public PieceType getType() {
		return this.type;
	}
	public static String getSymbol(PieceType type) {
		String symbol = "-";
		switch(type) {
		case KING -> symbol = "KI";
		case QUEEN -> symbol = "Q";
		case BISHOP -> symbol = "B";
		case KNIGHT -> symbol = "K";
		case ROOK -> symbol = "R";
		case PAWN -> symbol = "P";
		}
		return symbol;
	}
	public boolean isWhite() {
		return this.isWhite;
	}
}
