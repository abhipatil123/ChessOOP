package com.chess.engine.board;

public class BoardUtils {
	
	public static final boolean[] FIRST_COLUMN = null;
	public static final boolean[] SECOND_COLUMN = null;
	public static final boolean[] SEVENTH_COLUMN = null;
	public static final boolean[] EIGHTH_COLUMN = null;

	private BoardUtils() {
		throw new RuntimeException("You cannot instantiate this");
	}
	
	public static boolean isValidCoordinate(int candidateDestCoordinate) {
		return candidateDestCoordinate>=0 && candidateDestCoordinate<64;
	}
}
