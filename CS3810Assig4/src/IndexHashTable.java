import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.*;

/*
 * Cyril Thomas
 * */
public class IndexHashTable 
{
	private Integer[] hashArray;
	private int arraySize;
	private int maxRecord;
	
	public IndexHashTable(int newSize, int newMaxRecord)
	{
		arraySize = newSize;
		maxRecord = newMaxRecord;
		
		hashArray = new Integer[arraySize];
		
		for (int i = 0; i < arraySize; i++)
		{
			hashArray[i] = i;
		}
		
		List<Integer> hashArrayList = Arrays.asList(hashArray);
		Collections.shuffle(hashArrayList);
		hashArray = hashArrayList.toArray(new Integer[hashArrayList.size()]);
		for(int i = 0; i<hashArray.length;i++)
			System.out.println("\t" + hashArray[i]);
	}
	
	public int hashFunc(int key)
	{
		return key % arraySize;
	}
	
	public int insert (RandomAccessFile file, Record rec) throws FileNotFoundException
	{
		int orgKey = Integer.parseInt(rec.getId());
		
		int hashValue = hashFunc(orgKey);
		
		int startOffset = hashArray[hashValue] * maxRecord * 40;
		
		Record tempCompareRec = new Record();
		
		/*for (int i =0 ; i<=(arraySize*maxRecord); i++)
			tempCompareRec.write(file);*/
		
		for(int i = 0; i < maxRecord + 1; i++)
		{
			try 
			{
				System.out.println("Pointer: "+startOffset);
				file.seek(startOffset);
				tempCompareRec.read(file);
			

				/*if(i==maxRecord)
				{
					return -1;
				}
				
				else if(tempCompareRec.getId().equals(rec.getId()))
				{
					return 1;
				}
				
				else if(tempCompareRec.getId().equals(""))
				{
					
						rec.write(file);
						return 0;
				}
				else
					startOffset += 40;*/
				
				System.out.println("Temp ID: "+tempCompareRec.getId());
				if(tempCompareRec.getId()=="nul")
				{
					rec.write(file);
					return 0;
				}
				else if (tempCompareRec.getId().equals(rec.getId()))
					return 1;
				else if (i == maxRecord)
					return -1;
				else if (!(tempCompareRec.getId().trim().equals(rec.getId())))
					startOffset += 40;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return 0;		
	}
	
	public String search(RandomAccessFile file, int newId)
	{
		int hashValue = hashFunc(newId);
		int startOffset = hashArray[hashValue] * maxRecord * 40;
		
		Record tempCompareRec = new Record();
		
		for(int i = 0; i < maxRecord + 1 ; i++)
		{
			try 
			{
				file.seek(startOffset);
				tempCompareRec.read(file);
				
				if(tempCompareRec.getId().equals(newId))
				{
					return tempCompareRec.toString();
				}
				else 
					startOffset += 40;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return "Not Found";
	}
}
