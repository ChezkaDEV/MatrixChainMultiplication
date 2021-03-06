// Matrix Chain Multiplication

import java.io.*;
import java.util.*;
import java.lang.StringBuffer;

public class MCM
{
	public int N[][];
	public int d[];
	public int SIZE = 6;

	public static void main( String [ ] args ) throws Exception
    {
    	MCM mcm = new MCM();

    	// Initialize N and d
    	mcm.init();

    	// Compute the minimum number of element multiplications required for the matrices defined in init.
    	int result = mcm.minMCM();

    	System.out.println("result = " + result);
    }

	// This method initializes N and d
	// The dimensions of d are hard-coded
	public void init()
	{
		N = new int[SIZE][SIZE];
    	d = new int[SIZE+1];

		// N is initialized with everything at infinity, except the diagonals.
		for (int i = 0; i < SIZE; i++)
    	{
    		for (int j = 0; j < SIZE; j++)
    		{
    			if (i == j)
    				N[i][j] = 0;
    			else
    				N[i][j] = 100000;
    		}
    	}

		// d stores the dimensions of the arrays, as defined in the notes A0 is a d0xd1 matrix, A1 is a d1xd2 matrix, etc.
		// which is why d has SIZE + 1 dimensions.
    	d[0] = 25;
		d[1] = 45;
		d[2] = 15;
		d[3] = 5;
		d[4] = 10;
		d[5] = 25;
		d[6] = 30;
	}

	// This method calculates the minimum number of element multiplications required
	// to multiply the number of matrices given and their given dimensions.
    public int minMCM()
    {
    	// Algorithm defined in Arup's CS II notes.
    	// Basically we are looking for the optimal way to "split" the matrices, where k defines the "split".
    	for (int i = 1; i <= SIZE; i++)
    	{
    		for (int j = 0; j <= SIZE-i; j++)
    		{
    			for (int k = j; k < j+i-1; k++)
    			{
    				if (N[j][j+i-1] > N[j][k]+N[k+1][j+i-1] + d[j]*d[k+1]*d[i+j])
    				{
    					N[j][j+i-1] = N[j][k]+N[k+1][j+i-1] + d[j]*d[k+1]*d[i+j];
    				}
    			}
    		}
    	}

		// Output the final N matrix
    	System.out.println("N = ");
    	for (int i = 0; i < SIZE; i++)
    	{
    		for (int j = 0; j < SIZE; j++)
    		{
    			System.out.print(N[i][j] + "\t");
    		}
    		System.out.println();
    	}

		System.out.println();

		// The result is stored in the first row, last column of N
		return N[0][SIZE-1];
    }
}