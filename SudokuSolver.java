// Author: Jake Lawrence
// 6-18-17

import java.util.*;

public class SudokuSolver {
	public static int[][] uss;
	public static void main(String[] args){
		
		uss = new int[][]{ 
			{1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}};
		
		if(solveSudoku())
			printS();
		else
			System.out.println("unsolvable");
		
	
	}
	
	public static boolean solveSudoku(){
		int row, col;
		// coordinates of blank location
		int[] bc = findBlankLocation();
		row = bc[0];
		col = bc[1];
		// if location not found, solved
		if(row  == -1){
			return true;
		}
		// cycles through each number at blaank location
		for(int i = 1; i < 10; i++){
			if(isSafe(col, row, i)){
				//sets value
				uss[row][col] = i;
				//if solved
				if(solveSudoku()){
					return true;
				}
			}
			// undoes mistake
			uss[row][col] = 0;
		}
		//backtracks
		return false;
	}
	
	public static int[] findBlankLocation(){
		int[] cell = {-1,-1};
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(uss[i][j] == 0){
					cell[0] = i;
					cell[1] = j;
					break;
				}
			}
		}
		return cell;
	}

	public static boolean isSafe(int c, int r, int i){
		return colSafe(c,i) && rowSafe(r, i) && !boxSafe(r - (r % 3), c - (c % 3), i);
	}
	
	public static boolean colSafe(int c, int i){
		for(int row = 0; row < 9; row++){
			if(uss[row][c] == i)
				return false;
		}
		return true;
	}
	
	public static boolean rowSafe(int r, int i){
		for(int col = 0; col < 9; col++){
			if(uss[r][col] == i)
				return false;
		}
		return true;
	}
	
	public static boolean boxSafe(int sr, int sc, int i){
		for (int row = 0; row < 3; row++){
	        for (int col = 0; col < 3; col++){
	            if (uss[row + sr][col + sc] == i)
	                return true;
	        }
		}
	    return false;
	}
	
	public static void printS(){
		System.out.println("---------------------------------");
		for(int i = 1; i < 10; i++){
			for(int j = 1; j < 10; j++){
				System.out.print("[" + uss[i - 1][j - 1] + "]");
				if(j % 3 == 0 && j < 7)
					System.out.print(" | ");
			}
			System.out.println();
			if(i % 3 == 0)
				System.out.println("---------------------------------");
		}
	}
			

}
