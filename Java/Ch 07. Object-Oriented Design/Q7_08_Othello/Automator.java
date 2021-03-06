package Q7_08_Othello;

import java.util.ArrayList;

import CtCILibrary.AssortedMethods;

/* A helper class to automate this game. This is just used for testing purposes. */
public class Automator {
	private Player[] players;
	private Player lastPlayer = null;
	public ArrayList<Location> remainingMoves = new ArrayList<Location>();
	private static Automator instance;
	
	private Automator() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Location loc = new Location(i, j);
				remainingMoves.add(loc);
			}
		}
	}
	
	public static Automator getInstance() {
		if (instance == null) {
			instance = new Automator();
		}
		return instance;
	}
	
	public void initialize(Player[] ps) {
		players = ps;
		lastPlayer = players[1];
	}
	
	public void shuffle() {
		for (int i = 0; i < remainingMoves.size(); i++) {
			int t = AssortedMethods.randomIntInRange(i, remainingMoves.size() - 1);
			Location other = remainingMoves.get(t);
			Location current = remainingMoves.get(i);
			remainingMoves.set(t, current);
			remainingMoves.set(i, other); //It looks like remainingMoves is to place pieces in random locations.
		}
	}
	
	public void removeLocation(int r, int c) {//This function is not even used.  Good design though.
		for (int i = 0; i < remainingMoves.size(); i++) {
			Location loc = remainingMoves.get(i);
			if (loc.isSameAs(r, c)) {
				remainingMoves.remove(i);
			}
		}
	}
	
	public Location getLocation(int index) {
		return remainingMoves.get(index);
	}
	
	public boolean playRandom() {
		Board board = Game.getInstance().getBoard();
		shuffle(); //  It looks like the suffle at each turn is to keep the moves very random.  
hat's going on here.  Do we shuffle the moves at each turn?  Why? The lastPlayer variable is actually inicialized to player[1]. 		lastPlayer = lastPlayer == players[0] ? players[1] : players[0];//Not sure w
		String color = lastPlayer.getColor().toString();
		for (int i = 0; i < remainingMoves.size(); i++) {
			Location loc = remainingMoves.get(i);
			boolean success = lastPlayer.playPiece(loc.getRow(), loc.getColumn());
			
			if (success) {
				System.out.println("Success: " + color + " move at (" + loc.getRow() + ", " + loc.getColumn() + ")");
				board.printBoard();
				printScores();
				return true;
			}
		}
		System.out.println("Game over. No moves found for " + color);
		return false;
	}	
	
	public boolean isOver() {// I thought the game was over when no more moves can be made.  I suppose this is another indicator? Yes. 
		if (players[0].getScore() == 0 || players[1].getScore() == 0) {
			return true;
		}
		return false;
	}
	
	public void printScores() {
		System.out.println("Score: " + players[0].getColor().toString() + ": " + players[0].getScore() + ", " + players[1].getColor().toString() + ": " + players[1].getScore());
	}
}
