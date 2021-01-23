import java.util.*;	 
import java.awt.*;   

public class Sudoku 
{					
	
   static Random r = new Random();
   
   public static void main (String[]args)
   {
      //step1: initialize a 2D char array (only to display the puzzle),
      //the output will be the solved sudoku puzzle as a 2D int array
      System.out.println("\n"+"\n" + "        The puzzel :");
      
      char puzzel [][] = {
            				{' ','6','1',' ',' ',' ','4','2','9'},
            				{'2','9',' ','8',' ','1',' ','5',' '},
            				{'5',' ',' ','4','2','9','1',' ',' '},
            				{'1',' ',' ','7','5',' ','6',' ','2'},
            				{'9',' ',' ',' ','1','8','3',' ','7'},
            				{'6','7','3','2',' ',' ',' ',' ','5'},
            				{' ','5','2',' ','8','6','9',' ',' '},
            				{' ',' ','6','9',' ','2',' ','7','1'},
            				{' ','1','9',' ','3',' ',' ','6','8'}
                   		}; 
                   			
      //for loop to print the puzzle 
      for(int i=0;i<9;i++)
      {
         if (i % 3 == 0)
         System.out.println(" -----------------------");
         for(int j=0;j<9;j++)
         {
         if (j % 3 == 0) 
         System.out.print("| ");
         System.out.print(puzzel[i][j] + " ");
         }
      System.out.println("|");
      }
      System.out.println(" -----------------------"+"\n"+"\n");
           
     //for the input: initializing a 2D int array 
     int board [][] = {
            			{0,6,1,0,0,0,4,2,9},
            			{2,9,0,8,0,1,0,5,0},
            			{5,0,0,4,2,9,1,0,0},
            			{1,0,0,7,5,0,6,0,2},
            			{9,0,0,0,1,8,3,0,7},
            			{6,7,3,2,0,0,0,0,5},
            			{0,5,2,0,8,6,9,0,0},
            			{0,0,6,9,0,2,0,7,1},
            			{0,1,9,0,3,0,0,6,8}
        			  };
        			
     print(board);
     
    }
     
     //a method to print the solution
     private static void printSolution(int sol [][] ) 
     {
        System.out.println("\n"+"\n" + "        Solution :" );
        for(int i=0;i<9;i++)
        {
        	if (i % 3 == 0)
         	System.out.println(" -----------------------");
         	for(int j=0;j<9;j++)
         	{
         		if (j % 3 == 0) 
         		System.out.print("| ");
         		System.out.print(sol[i][j] + " ");
         	}
      		System.out.println("|");
       }
      System.out.println(" -----------------------");
    }
     
    //in order to solve we should make a random guess by initializing
    //each square 3*3 using distinct numbers from 1 to 9
    private static int [][] swap(int [][] guess, int [][] board)
    {
    	int I = r.nextInt(3)*3; //3*3 block //generate the next number,assign it to I
        int J = r.nextInt(3)*3;
        
        ArrayList <Point> point = new ArrayList<Point>(); 
        
        //for loop to fill the empty cells,if empty
        for(int i=I ; i<I+3 ; i++)                      
        {                                            
        	for (int j=J ; j<J+3 ; j++)              
        	{                                        
        		if (board [i][j] == 0)
        		{
        			point.add(new Point(i,j)); 
        		}
        	}
        }
        
    	//point.size() will give us the number of elements in the list
    	int Index1 = r.nextInt(point.size()); 
    	int Index2 = r.nextInt(point.size()); 
    
    	while(Index2 == Index1)
    	{
    		Index2 = r.nextInt(point.size()); //in the square,if two index have the same number, change
    	}
    
    	int X1  = point.get(Index1).x; //get(int index) returns the object of the list at specific index,and axis
    	int Y1  = point.get(Index1).y;
    	int X2  = point.get(Index2).x;
    	int Y2  = point.get(Index2).y;
   
    	int out [][] = new int[guess.length][]; 
    											
    	
    	//for loop to copy the guess										
    	for(int i=0 ; i<guess.length ; i++)
    	{
    		out[i] = guess[i].clone(); //clone() method to create a copy of the object
    	}
    	
    	//swapping
    	int temp = out[X1][Y1];
    	out[X1][Y1] = out[X2][Y2];
    	out[X2][Y2] = temp;
    
    	return out;
    
    }
    
    
    //heuristic function to calculate the number of conflict
     private static int heuristic(int[][] guess)
    {
    	int heuristic=162; //we want the heuristic to be descread so we will start from the max
    					   //number of conflict in 9*9 sudoku which is 162 then heuristic--
    	for(int row=0 ; row<9 ; row++)
    	{
    		boolean[] ROW = new boolean[9]; //row conflict
            boolean[] rowConflict = new boolean[9];
            boolean[] colConflict = new boolean[9];
            boolean[] COL = new boolean[9];
            
            for(int col=0 ; col<9 ; col++)
            {
            	if (!ROW[guess[row][col]-1])
            	{
            		ROW[guess[row][col]-1] = true; 
            	}
            	else
            	{
                    rowConflict[guess[row][col]-1] = true;//there is a conflict in row
        		}
        		if (!COL[guess[col][row]-1]) 
        		{
                    COL[guess[col][row]-1] = true;
                } 
                else 
                {
                    colConflict[guess[col][row]-1] = true;//there is a conflict in col
                }
                
            	
            }
            
            for(boolean b:rowConflict)
            {
            	if (!b) 
            	{
                    heuristic--; //if there is a conflict in row ,heuristic--
                }
            }
            
            for(boolean b:colConflict)
            {
            	if (!b) 
            	{
                    heuristic--; //if there is a conflict in col ,heuristic--
                }
            }
    		
    	}
    	
    	return heuristic; //We will find the solution if the heuristic is 0	
    }
    
    
    
    //print method 
    private static void print(int board [][])
    {
    	int current [][] = swap(swap(getGuess(board), board), board); //to make a random guess
        int Cost = heuristic(current);
        
        int count = 0;
        
        while (Cost>0) //keep switching as long as the cost is not zero
        {
        	int newGuess [][] = swap(current,board); //make new random guesse
            int newCost = heuristic(newGuess);	
            	
            int cost3 = newCost - Cost;
            
             //in Hill Climbing, if newCost>oldCost keep the old configuration
             //if newCost<=oldCost keep the new configuration
             if (cost3>0)         
             {
                Cost=Cost;
             	current=current;
             }
             else
             {
             	Cost = newCost;
                current = newGuess;  
             }
             count++;
             System.out.println("Iteration: " + count + "\t" + "Cost: " + Cost);
        }
		printSolution(current); 
		System.out.println("\n"+"\n"+"Solution found at " +"Iteration:" + count + "\t" + "Cost:" + Cost); 
    }
    
    //getGuess method
     private static int [][] getGuess(int board [][])
    {
    	int guess [][] = new int[board.length][]; //2D int array
    	
    	//for loop to copy board array 
    	for (int i=0 ; i<board.length ; i++)
    	{
    		guess[i] = board[i].clone();
    	}
    	
    	//nested loop for the final answer 
    	for (int I=0 ; I<9 ; I+=3)
    	{
    		for (int J=0 ; J<9 ; J+=3)
    		{
    			 boolean ans [] = new boolean[10];
    			 for(int i=I ; i<I+3 ; i++)
    			 {
    			 	for (int j=J ; j<J+3 ; j++)
    			 	{
    			 		ans[board[i][j]] = true;	
    			 	}
    			 }
    			 
    			 for(int i=I ; i<I+3 ; i++)
    			 {
    			 	for(int j=J ; j<J+3 ; j++)
    			 	{
    			 		for (int n=1 ; n<10 ; n++)
    			 		{
    			 			if(!ans[n]&&guess[i][j]==0)
    			 			{
    			 				guess[i][j]=n;
                                ans[n]=true;
                                break;
    			 			}
    			 		}
    			 	}
    			 }
    		}	
    	}	
    	return guess;		
    }    
} 