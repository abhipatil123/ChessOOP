package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

//Move class contains board to getTile
//Piece being moved
//Piece getting attacked
//Destination coordinate of the move
public abstract class Move {
	
	final Board board;
	final Piece movePiece;
	final int destCoordinate;
	
	private Move(final Board board,
		 final Piece movePiece,
		 final int destCoordinate){
		this.board = board;
		this.movePiece = movePiece;
		this.destCoordinate = destCoordinate;
	}
	
	public static final class StaticMove extends Move{
		public StaticMove(final Board board,
				   final Piece movePiece,
				   final int destCoordinate) {
			super(board, movePiece,destCoordinate);
		}
	}
	
	public static final class AttackMove extends Move{
		final Piece attackedPiece;
		
		public AttackMove(final Board board,
				   final Piece movePiece,
				   final int destCoordinate,
				   final Piece attackedPiece) {
			super(board, movePiece,destCoordinate);
			this.attackedPiece = attackedPiece;
		}
	}
}
