package Static;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInterpreter {
	
	/* EXAMPLE USE
		String   file = "data/Charity_Identification.csv"; - The file to extract from
		String[] toExtract = new String[] {"BN", "Country"}; - The columns (headers) you want to extract; use 'listHeaders' to learn what the file contains
		
		String[][] data = getDataFromFile(file,toExtract); - The method to extract the chosen headers from the chosen file

		- Then put data into Charity list
	*/
	
	public static String[][] getDataFromFile(String file, String[] toExtract) throws IOException{
		String[][] raw = readFile(file);
		String[][] data = extractElements(raw, getHeaders(file), toExtract);
		return(data);
	}
	
	private static String[][] readFile(String s) throws IOException{
		
		List<String[]> list = new ArrayList<String[]>(); // will store the raw data outputted from the .csv file
		
		String line = "";
		String delimiter=","; // what the line strings will be seperated by
		
		BufferedReader br = new BufferedReader(new FileReader(s));
		while ( (line = br.readLine() ) != null) // while there is a line
			list.add(line.split(delimiter)); // add the raw line to the list
		br.close();
		
		String[][] export = list.toArray(new String[0][0]);
		
		return(export);
	}
	
	private static String[] getHeaders(String s) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(s));
		
		String[] dirtyheader = (br.readLine().split(",")); // will store english and french name seperated by '/'
		String[] header = new String[dirtyheader.length]; // will store only english
		
		for (int i=0; i<dirtyheader.length; i++){
			String Header = dirtyheader[i].split("/")[0]; // remove french name if it is there
			header[i]=Header;
		}
		
		br.close();
		
		return(header);
	}
	
	public static void listHeaders(String s) throws IOException{
		// same as getHeaders but will print instead
		
		BufferedReader br = new BufferedReader(new FileReader(s));
		
		String[] headers = (br.readLine().split(","));
		
		for (int i=0; i<headers.length; i++){
			String Header = headers[i].split("/")[0];
			System.out.println(Header);
		}
		
		br.close();
	}
	
	private static String[][] extractElements(String[][] data, String[] headers, String[] find){
		int[] index = new int[find.length];
		boolean[] found = new boolean[find.length];
		for (int i=0; i<find.length; i++){
			for (int j=0; j<headers.length; j++){
				if ( (find[i].compareTo(headers[j])) == 0 ){
					index[i]=j;
					found[i]=true;
					break;
				}		
			}
			if (found[i]==false)
				throw new RuntimeException("Header index not found");
		}
		
		String[][] export = new String[data.length][index.length];
		
		for (int property=0; property<index.length; property++){
			for (int node=0; node<data.length; node++)
				export[node][property]=data[node][property];
		}
		
		return(export);
	}
}