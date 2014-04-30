import java.io.IOException;
import java.util.*;

/*
 * Cyril Thomas
 * */

import java.io.*;
import java.io.RandomAccessFile;

public class ExternalHashing {

	public static void main(String[] args) throws EOFException, IOException 
	{
		Scanner scan = new Scanner(System.in);
		int inputBlock = 0, inputRecord = 0;
		RandomAccessFile f = null;
		
		try
		{
			f = new RandomAccessFile("HashingEX", "rw");
			f.seek(0);
			
			System.out.print("Enter the Block Size: ");
			inputBlock = scan.nextInt();
			System.out.print("\nEnter the # of Records: ");
			inputRecord = scan.nextInt();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			scan.nextLine();
		}
		
		IndexHashTable data = new IndexHashTable(inputBlock, inputRecord);
				
		try 
		{
			do 
			{
				System.out.println("\nEnter: 1 to Insert ");
				System.out.println("Enter: 2 to Search ");
				System.out.println("Enter: 0 to Quit ");
				System.out.print("Enter: ");
				int input = scan.nextInt();
				
				switch (input) 
				{
					case 1:
					{	
						scan.nextLine();
						System.out.print("\nEnter Student's Name: ");
						String name = scan.nextLine();
						System.out.print("\nEnter Student's ID: ");
						String id = scan.nextLine();
						
						Record student = new Record(name, id);
						
						int success = data.insert(f, student);
						
						if(success == 0)
							System.out.println("\nWrite Successful");
						else if (success == -1)
							System.out.println("The Block is Full");
						else if (success == 1)
							System.out.println("The Student Record already Exists!!!");
						
						break;
					}
					
					case 2:
					{
						scan.nextLine();
						
						System.out.print("\nEnter Student ID: ");
						int key = scan.nextInt();
						
						String message = data.search(f, key);
						
						System.out.println(message);
						
						break;
					}
					default:
					{
						System.exit(0);
						break;
					}
				}
				
			} while (true);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
