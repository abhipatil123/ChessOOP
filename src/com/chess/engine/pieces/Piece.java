package com.chess.engine.pieces;
import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;


public abstract class Piece {
	
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	
	Piece(final int piecePostion, final Alliance pieceAlliance){
		this.piecePosition = piecePostion;
		this.pieceAlliance = pieceAlliance;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);

	public Alliance getPieceAlliance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isFirstMove() {
		return true;
	}
}
