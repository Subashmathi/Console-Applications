package MazeRunner;
import java.util.*;
public class shortestPath {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter matrix dimension nxn: ");
		int  n = scan.nextInt();
		char[][] matrix = new char[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j] = '0';
			}
		}
		System.out.println("Enter adventurer position: ");
		int arow = scan.nextInt();
		int acol = scan.nextInt();
		matrix[arow][acol] = 'A';
		System.out.println("Enter destination position: ");
		int drow = scan.nextInt();
		int dcol = scan.nextInt();
		matrix[drow][dcol] = 'D';
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		int shortestPath = findShortestPath(arow, acol, drow, dcol);
		System.out.println(shortestPath);
	}
	static int findShortestPath(int startx, int starty, int endx, int endy) {
		return Math.max(Math.abs(endx-startx),Math.abs(endy-starty));
	}
}
