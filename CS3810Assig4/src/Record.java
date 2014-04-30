/*
 * Cyril Thomas
 * */
import java.util.*;
import java.io.*;

public class Record 
{
	private String name;
	private String id;
	private int NAMELENGTH=16;
	private int IDLENGTH=4;
	
	
	public Record()
	{
		name=" ";
		id=" ";
	}
	
	public Record(String newName, String newId)
	{
		name= newName;
		id= newId;
	}
	
	public void read(RandomAccessFile file) //throws IOException
	{
		name= readString(file, NAMELENGTH);
		id = readString(file, IDLENGTH);
	}
	
	public void write(RandomAccessFile file) //throws IOException
	{
		try {
			writeStr(file, name, NAMELENGTH);
			writeStr(file, id, IDLENGTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	private String readString(RandomAccessFile file, int strLength) //throws IOException
	{
		char[] chs = new char[strLength];
		
		for (int i = 0; i < chs.length; i++)
		{
			try {
				chs[i] = file.readChar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String theStr = new String(chs);
		return theStr;
	}
	
	private void writeStr(RandomAccessFile file, String str, int strLength) throws IOException
	{
		StringBuffer buffer = null;
		
		if(str != null)
			buffer = new StringBuffer(str);
		else
			buffer = new StringBuffer(strLength);
		
		buffer.setLength (strLength);
		file.writeChars(buffer.toString());
	}
	
	public String toString()
	{
		return "\nName: " + name + "\nID: " + id;
	}
	
	
}
