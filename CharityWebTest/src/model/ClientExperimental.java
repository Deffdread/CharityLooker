package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ClientExperimental {

	
	static Charity current = null;
	static boolean nullCur=false;	
	static int choice; 
	static LinearProbing hashBnum;
	static LinearProbing hashName;
	static Charity[] charities;
	
	public static void main(String[] args) throws IOException {
		
		//File Interpreter
		DataPacker DP = new DataPacker();	
		Charity[] charities = DP.getData();
		Quick.sort(DP.getData(),"bnum");
		
		//Setting up hash tables
		LinearProbing hashBnum = new LinearProbing(charities.length);
		LinearProbing hashName = new LinearProbing(charities.length);
		AdjacencyHash hashProg = new AdjacencyHash(16);
		for (int i = 0; i < charities.length; i++) {
			hashBnum.put(charities[i].getBnum(), charities[i]);
			hashName.put(charities[i].getName().toUpperCase(), charities[i]);
			for (int j = 0; j < charities[i].getProg().length; j++) {
				hashProg.put(charities[i].getProg()[j], charities[i]);
			}
		
		}
	
		//Checking Charities
		LinkedList<Charity> charity = hashProg.get("I1");
		for (int i = 0; i < charity.size(); i++) {
			System.out.println(charity.get(i));
		}
				
		System.out.println("Welcome to CharityLooker, the best one stop shop for charity data!");
		String choice = "";
		Scanner inputStr = new Scanner(System.in);
		Locale language = new Locale("English");
		FuzzyScore fs = new FuzzyScore(language);
								

			
/*			if (nullCur==true){
				String lookFor=mode;
				
				ArrayList<int[]> closeL = new ArrayList<int[]>();
				
				double factor=0;
				if (lookFor.equals("1"))
					factor=0.6;
				else if (lookFor.equals("2"))
					factor=0.1;
				
				int max = fs.fuzzyScore(choice,choice.toUpperCase());
				int cutOff = (int) (max*factor);
				int score = 0;
				for (int cur=0; cur<DP.getNames().length; cur++){
					if (lookFor.equals("1"))
						score = fs.fuzzyScore(choice,DP.getNames()[cur][1].toUpperCase());
					else if (lookFor.equals("2"))
						score = fs.fuzzyScore(choice,DP.getNames()[cur][0].toUpperCase());
					if (score>=cutOff){
						closeL.add(new int[] {cur,score});
					}
				}
				int[][] close =closeL.toArray(new int[0][0]);
				
				for (int i=0; i<close.length-1; i++){
					for (int j=0; j<close.length-1; j++){
						if (close[i][1]<close[j][1]){
							int[] temp = close[i];
							close[i]=close[j];
							close[j]=temp;
						}
					}
				}
				System.out.println("("+close.length+" return(s): Currently sorting for \""+choice+"\")");
				for (int i = 0; i < close.length; i++){
					if (lookFor.equals("1"))
						System.out.println(DP.getNames()[close[i][0]][1]);
					else if (lookFor.equals("2"))
						System.out.println(charities[i].getBnum()+" - "+charities[i].getName());
					
				}
				
				System.out.print("Would you like to try again?\n1. Yes\n2. No\n>");
				choice = inputStr.nextLine();
				if (choice.equals("1"))
					hold= true;
				else
					hold=false;
			}
			
			if (current != null){ //explore data
				
			}
			
		}while(!choice.contentEquals("9"));
		inputStr.close();
	*/	
	}
	
	
	
	
	
	
	
	
	
	public static List all() {
		
		List allCharities = new ArrayList();
		for(int i = 0; i < charities.length; i++) {
			allCharities.add(charities[i]);
		}
		return allCharities;
	}
	
	
	
	public static Charity name(String in) {
		in = in.toUpperCase();
		current = hashName.get(in);
		if (current==null){
			System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these names if so:");
			nullCur=true;
			choice = 1; 
			
			return current; //Temp
		}
		else {
			return current;
		}
		
		
	}
	
	public static Charity bnum(String in) {
		in = in.toUpperCase();
		current = hashBnum.get(in);
		if (current==null){
			System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these business numbers if so:");
			nullCur=true;
			choice = 2;
			
			return current; //Temp 
		}
		else {
			return current;
		}
		
	}
	
	
	
	
	
	
}

