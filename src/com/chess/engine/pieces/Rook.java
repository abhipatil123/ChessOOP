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

public class Rook extends Piece {

	private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-1, -8, 1, 8};
	Rook(int piecePostion, Alliance pieceAlliance) {
		super(piecePostion, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		for(int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
			int candidateCordinateDestination = this.piecePosition;
			while(BoardUtils.isValidCoordinate(candidateCordinateDestination)) {
				if(isFirstColumnExclusion(candidateCordinateDestination, candidateCoordinateOffset)
				   || isEightColumnExclusion(candidateCordinateDestination, candidateCoordinateOffset)) {
					continue; //Should See??
				}
				candidateCordinateDestination += candidateCoordinateOffset;
				if(BoardUtils.isValidCoordinate(candidateCordinateDestination)) {
					final Tile candidateDestTile = board.getTile(candidateCordinateDestination);
					if(!candidateDestTile.isTileOccupied()) {
						legalMoves.add(new Move.StaticMove(board, this, candidateCordinateDestination));
					}else {
						final Piece pieceAtDestination = candidateDestTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new Move.AttackMove(board, this, candidateCordinateDestination, pieceAtDestination));
						}
						break; //Since encounters a piece on this offset direction cannot move further
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, 
												  final int candidateCoordinateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateCoordinateOffset==-1);
	}
			
	private static boolean isEightColumnExclusion(final int currentPosition, 
			  final int candidateCoordinateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateCoordinateOffset==1);
	}
}
