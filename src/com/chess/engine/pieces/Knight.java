package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	Knight(final int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
	}
	
	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		//Create an list legalMoves to store all possible candidate moves
		//Iterate through all possible coordinates for this piece 
		//If the coordinate is valid and tile is empty
			//Add that move to the legal moves list
		//ELSE
			//Check alliance of the piece and if opposite alliance add move to legalMoves

		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
               isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
               isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
               isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
			final int candidateDestCoordinate = this.piecePosition + currentCandidateOffset;
			if(BoardUtils.isValidCoordinate(candidateDestCoordinate)){
				final Tile candidateDestinationTile = board.getTile(candidateDestCoordinate);
				if(!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move());
				}else {
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new Move());
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	
	//Knight edge cases 
	 private static boolean isFirstColumnExclusion(final int currentPosition,
             final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) ||
		(candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
	}
		
	private static boolean isSecondColumnExclusion(final int currentPosition,
		              final int candidateOffset) {
		return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
	}
		
	private static boolean isSeventhColumnExclusion(final int currentPosition,
		               final int candidateOffset) {
		return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
	}
		
	private static boolean isEighthColumnExclusion(final int currentPosition,
		              final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
		(candidateOffset == 10) || (candidateOffset == 17));
	}
}
