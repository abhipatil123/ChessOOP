package com.chess.engine;

public enum Alliance {
	WHITE{
		@Override
		public int getDirection() {
			return -1;
		}
		
		public boolean isWhite() {
			return true;
		}
		
		public boolean isBlack() {
			return false;
		}
	},
	BLACK{
		@Override
		public int getDirection() {
			return 1;
		}
		
		public boolean isWhite() {
			return false;
		}
		
		public boolean isBlack() {
			return true;
		}
	};
	
	public abstract int getDirection();
	public abstract boolean isBlack();
	public abstract boolean isWhite();
}	
