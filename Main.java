import java.io.*;
import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) throws IOException, ParserException, VariableException
	{   
		ArrayList<String> list = new ArrayList <String>();
		FileReader scan = new FileReader("C:\\David\\School\\prog3.txt");
        BufferedReader reader = new BufferedReader(scan);
        String line;
        while ((line = reader.readLine()) !=null)
            {
                list.add(line);
            }
        		
        ArrayList<String> alltokens = new ArrayList<String>();
        
        for(String s: list)
        {     	
        	String[] current = s.split(" ");
            for(int i = 0; i < current.length; i++)
            {
            	if(!current[i].equals(";") && current[i].length() > 0)
               		alltokens.add(current[i]);
            }
        }
        
        ProgramStatement program = new ProgramStatement(alltokens);
        program.execute();
	}
}
