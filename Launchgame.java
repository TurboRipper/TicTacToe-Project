import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;
class TicTacToe
{
	static char[][] board;
	public TicTacToe()
	{
	board = new char[3][3];
	initValues();
	
		
	}
	
	void initValues()
	{
		for(int i=0;i < board.length;i++)
		{
			for(int j=0;j < board[i].length;j++)
			{
				board[i][j] = ' ';
			}
		}
	}
	
	static void dispBoard()
	{
		System.out.println("-------------");
		for(int i=0;i < board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j < board[i].length;j++)
			{
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	static void placeMark(int i, int j, char ch)
	{
		board[i][j] = ch;
		
	}
	
	static boolean checkColWin()
	{
		for(int i=0;i < board.length;i++)
		{
			for(int j=0;j < board[i].length;j++)
			{
				if((board[0][j] != ' ') && (board[0][j] == board[1][j]) && (board[1][j] == board[2][j]))
				{
					return true;
				}
			}
		}
		return false;
		
	}
		
	static boolean checkRowWin()
	{
		for(int i=0;i < board.length;i++)
		{
			for(int j=0;j < board[i].length;j++)
			{
				if((board[i][0] != ' ') && (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean checkDiaWin()
	{
		for(int i=0;i < board.length;i++)
		{
			for(int j=0;j < board[i].length;j++)
			{
				if(( board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
						|| (board[2][0] != ' ' && board[2][0] == board[1][1] && board[1][1] == board[0][2]))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean checkDraw()
	{
		for(int i=0;i < board.length;i++)
		{
			for(int j=0;j < board[i].length;j++)
			{
				if(board[i][j] == ' ')
				{
					return false;
				}
			}
		}
		return true;
	}
	
}


abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();

	
	boolean isValidMove(int row, int col)
	{
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2)
		{
			if(TicTacToe.board[row][col] == ' ')
			{
				return true;
			}
		
		}
		return false;
	}
	
}
class HumanPlayer extends Player
{
	
	HumanPlayer(String name, char mark) {
		
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter row and column: ");
			row = scan.nextInt();
			col = scan.nextInt();
					
		}while(!isValidMove(row, col));
		
		
		TicTacToe.placeMark(row, col, mark);
		
	}
	
	
	
}

class AIPlayer extends Player
{
	
	AIPlayer(String name, char mark) {
		
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
					
		}while(!isValidMove(row, col));
		
		
		TicTacToe.placeMark(row, col, mark);
		
	}
	
	
}
public class Launchgame {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TicTacToe l = new TicTacToe();
		
		HumanPlayer p1 = new HumanPlayer("Player 1", 'X');
		HumanPlayer n1 = new HumanPlayer("NormalPlayer", 'X');
		AIPlayer p2 = new AIPlayer("AI Player", 'O');
		//Current Reference is created to point the current player.
		System.out.println("Play with Normal Human enter 'Y' or AI enter 'N'");
		char ch = scan.next().charAt(0);
		
		
		Player cp;
		if(ch == 'Y')
		{
			cp = p1;
		}
		else {
			cp = p2;
			
		}
		
		
		while(true)
		{
			System.out.println(cp.name +" turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkColWin() || TicTacToe.checkDiaWin() ||
					TicTacToe.checkRowWin())
			{
				System.out.println(cp.name+" has won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game is a Draw");
				break;
			}
			else {
				if(cp == p1 || cp== p2)
				{
					cp = n1;
				}
				else if(cp == n1){
					cp = p2;
					
				}
				else {
					cp = p1;
				}
			}
		}
		
	}

}
