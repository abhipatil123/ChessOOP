package com.chess.engine.board;
import java.util.HashMap;
import java.util.Map;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile { //Cannot instantiate this Class 
	protected final int tileCoordinates;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i=0; i < 64; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile createTile(final int titleCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(titleCoordinate, piece) : EMPTY_TILES_CACHE.get(titleCoordinate);
	}
	
	Tile(int tileCoordinates){
		this.tileCoordinates = tileCoordinates;
	}
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	//Final class cannot be extended, but it can extend a superClass
	//Nested static class in Java
	//Only the nested classes can be static
	//Nested static class doesn’t need reference of Outer class
	public static final class EmptyTile extends Tile{ 
		private EmptyTile(int coordiante) {
			super(coordiante); //Calls parent constructor
		}
		
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile{
		private final Piece pieceOnTile;
		
		private OccupiedTile(int coordiante, Piece pieceOnTile) {
			super(coordiante);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}
}
