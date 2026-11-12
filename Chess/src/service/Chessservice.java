package service;

import java.util.*;
import model.*;
import controller.*;

public class Chessservice {
	
    private static Pieces[][] board;

    public int getRandom() {
        return new Random().nextInt(2);
    }

    static Pieces[][] initializeBoard() {
        return new Pieces[8][8];
    }

    public void initializePieces() {
        board = initializeBoard();

        for(int i=0;i<8;i++)
            board[6][i] = new Pawn(true,0,0);

        board[7][0] = new Rook(true,0,0);
        board[7][1] = new Knight(true,0,0);
        board[7][2] = new Bishop(true,0,0);
        board[7][3] = new King(true,0,0);
        board[7][4] = new Queen(true,0,0);
        board[7][5] = new Bishop(true,0,0);
        board[7][6] = new Knight(true,0,0);
        board[7][7] = new Rook(true,0,0);

        for(int i=0;i<8;i++)
            board[1][i] = new Pawn(false,0,0);

        board[0][0] = new Rook(false,0,0);
        board[0][1] = new Knight(false,0,0);
        board[0][2] = new Bishop(false,0,0);
        board[0][3] = new King(false,0,0);
        board[0][4] = new Queen(false,0,0);
        board[0][5] = new Bishop(false,0,0);
        board[0][6] = new Knight(false,0,0);
        board[0][7] = new Rook(false,0,0);
    }

    public void printBoard() {
        Chesscontroller cont = new Chesscontroller();

        System.out.print(" ");
        for(char c='a'; c<='h'; c++)
            System.out.printf("%3c", c);
        System.out.println();

        for(int i=0;i<8;i++) {
            System.out.print(8-i);
            for(int j=0;j<8;j++) {
                PieceType type = null;
                if(board[i][j] != null)
                    type = board[i][j].getType();
                String symbol = (type != null ? cont.getSymbol(type) : "-");
                if(board[i][j] != null && !board[i][j].isWhite())
                    symbol = symbol.toLowerCase();
                System.out.printf("%3s", symbol);
            }
            System.out.println();
        }
    }

    private int row(String pos){
        return 8 - (pos.charAt(1) - '0');
    }

    private int col(String pos){
        return pos.charAt(0) - 'a';
    }

    private boolean validCoord(String s){
        return s.length()==2 && s.charAt(0)>='a' && s.charAt(0)<='h' && s.charAt(1)>='1' && s.charAt(1)<='8';
    }

    public boolean isValidMove(boolean isWhite, String from, String to) {
        if(!validCoord(from) || !validCoord(to))
            return false;

        int fr=row(from), fc=col(from);
        int tr=row(to),   tc=col(to);

        Pieces piece = board[fr][fc];
        if(piece==null || piece.isWhite()!=isWhite)
            return false;
        switch(piece.getType()) {
            case ROOK:
                return canRook(fr,fc,tr,tc,isWhite);
            case BISHOP:
                return canBishop(fr,fc,tr,tc,isWhite);
            case KNIGHT:
                return canKnight(fr,fc,tr,tc,isWhite);
            case QUEEN:
                return canRook(fr,fc,tr,tc,isWhite)
                        || canBishop(fr,fc,tr,tc,isWhite);
            case KING:
                return canKing(fr,fc,tr,tc,isWhite);
            case PAWN:
                return canPawn(fr,fc,tr,tc,isWhite);
        }
        return false;
    }

    private static boolean blocked(int fr,int fc,int tr,int tc){
        int rs=Integer.compare(tr,fr);
        int cs=Integer.compare(tc,fc);
        int r=fr+rs,c=fc+cs;
        while(r!=tr || c!=tc){
            if(board[r][c]!=null) return true;
            r+=rs;
            c+=cs;
        }
        return false;
    }

    private static boolean canRook(int fr,int fc,int tr,int tc,boolean w){
        if(fr==tr && fc==tc) return false;
        if(fr!=tr && fc!=tc) return false;

        if(blocked(fr,fc,tr,tc)) return false;
        return board[tr][tc]==null ||
               board[tr][tc].isWhite()!=w;
    }

    private static boolean canBishop(int fr,int fc,int tr,int tc,boolean w){
        if(fr==tr && fc==tc) return false;
        if(Math.abs(fr-tr)!=Math.abs(fc-tc)) return false;

        if(blocked(fr,fc,tr,tc)) return false;
        return board[tr][tc]==null ||
               board[tr][tc].isWhite()!=w;
    }

    private static boolean canKnight(int fr,int fc,int tr,int tc,boolean w){
        int dr=Math.abs(fr-tr);
        int dc=Math.abs(fc-tc);

        if(!((dr==1&&dc==2)||(dr==2&&dc==1)))
            return false;

        return board[tr][tc]==null ||
               board[tr][tc].isWhite()!=w;
    }

    private static boolean canKing(int fr,int fc,int tr,int tc,boolean w){
        int dr=Math.abs(fr-tr);
        int dc=Math.abs(fc-tc);

        if(dr==0 && dc==0) return false;
        if(dr>1 || dc>1) return false;

        return board[tr][tc]==null ||
               board[tr][tc].isWhite()!=w;
    }

    private static boolean canPawn(int fr,int fc,int tr,int tc,boolean w){
        int dir = w ? -1 : 1;
        int rd  = tr-fr;
        int cd  = Math.abs(tc-fc);

        if(cd==0){
            if(rd==dir && board[tr][tc]==null)
                return true;
            int start=w?6:1;
            if(fr==start && rd==2*dir && board[fr+dir][fc]==null && board[tr][tc]==null)
                return true;
        }

        if(cd==1 && rd==dir && board[tr][tc]!=null && board[tr][tc].isWhite()!=w)
            return true;

        return false;
    }

    public void updatePiece(String from,String to,boolean isWhite){
        int fr=row(from), fc=col(from);
        int tr=row(to),   tc=col(to);
        Pieces piece=board[fr][fc];
        if(piece==null || piece.isWhite()!=isWhite)
            return;
        board[tr][tc]=piece;
        board[fr][fc]=null;
        checkPromotion(tr, tc);
    }
    
    public static void checkPromotion(int r, int c) {
    	Pieces piece = board[r][c];
    	if(piece.getType() != PieceType.PAWN) return;
    	if(piece.isWhite() && r == 0) promotePawn(r, c, true);
    	if(!piece.isWhite() && r == 7) promotePawn(r, c, false);
    }
    
    public static void promotePawn(int r, int c, boolean isWhite) {
    	Scanner scan = new Scanner(System.in);
    	System.out.println(isWhite ? "White" : "Black" + " Pawn promotion! Choose Piece(R, B, K, Q): ");
    	String choice = scan.nextLine().toUpperCase();
    	switch(choice) {
    	case "R": board[r][c] = new Rook(isWhite, r, c); break;
    	case "B": board[r][c] = new Bishop(isWhite, r, c); break;
    	case "K": board[r][c] = new Knight(isWhite, r, c); break;
    	default: board[r][c] = new Queen(isWhite, r, c); break;
    	}
    }
    
    public boolean checkMate(String from, String to) {
    	return false;
    }
}