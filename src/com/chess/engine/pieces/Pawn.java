package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.StaticMove;

public class Pawn extends Piece{
	
	private static final int[] CANDIDATE_COORDINATE_MOVES = {8, 16};
	Pawn(final int piecePostion, final Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
	}

	@Override
	//Pawn has two directions for white and black (Should include direction in Alliance)
	//Can move one step forward
	//Can move two steps forward intially
	//Can move one step diagonally when attacking
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		
		for(int currentCoordinateOffset : CANDIDATE_COORDINATE_MOVES) {
			final int candidateCordinateDestination = this.piecePosition + this.getPieceAlliance().getDirection() * (currentCoordinateOffset); 
			if(!BoardUtils.isValidCoordinate(candidateCordinateDestination)) {
				continue;
			}
			
			if(currentCoordinateOffset == 8 && (!board.getTile(candidateCordinateDestination).isTileOccupied())) {
				legalMoves.add(new Move.StaticMove(board, this, candidateCordinateDestination));
			}else if(currentCoordinateOffset == 16 && this.isFirstMove() &&
					 (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
					 (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
				final int behindCandidateDestinationCoordinate =
                        this.piecePosition + (this.pieceAlliance.getDirection() * 8);
				if (board.getTile(candidateCordinateDestination) == null &&
                    board.getTile(behindCandidateDestinationCoordinate) == null) {
                    legalMoves.add(new Move.StaticMove(board, this, candidateCordinateDestination));
                }
			}
		}
		return legalMoves;
	}
	
}
