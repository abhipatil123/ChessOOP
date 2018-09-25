package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class King extends Piece{
	
	final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};
	
	King(final int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			final int candidateDestCoordinate = this.piecePosition + currentCandidateOffset;

			//Consider Edge case for King
			if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
               isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
			
			if(BoardUtils.isValidCoordinate(candidateDestCoordinate)) {
				final Tile candidateDestinationTile = board.getTile(candidateDestCoordinate);
				if(!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move.StaticMove(board, this, candidateDestCoordinate));
				}else {
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new Move.AttackMove(board, this, candidateDestCoordinate, pieceAtDestination));
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	//King edge cases 
	 private static boolean isFirstColumnExclusion(final int currentPosition,
             final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -9) ||
									(candidateOffset == -1) || (candidateOffset == 7));
	 }
		
	 private static boolean isEighthColumnExclusion(final int currentPosition,
		              final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 9) || 
									(candidateOffset == 1) || (candidateOffset == -7));
	 }

}
