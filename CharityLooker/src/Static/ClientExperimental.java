package Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ClientExperimental {

	public static void main(String[] args) throws IOException {
		
		DataPacker DP = new DataPacker();
		
		Charity[] charities = DP.getData();

		Quick.sort(DP.getData(),"bnum");
		
		LinearProbing hashBnum = new LinearProbing(charities.length);
		LinearProbing hashName = new LinearProbing(charities.length);
		for (int i = 0; i < charities.length; i++) {
			hashBnum.put(charities[i].getBnum(), charities[i]);
			hashName.put(charities[i].getName().toUpperCase(), charities[i]);
		}
		
		//for (int i=0; i<100; i++)
		//	System.out.println(DP.getData()[i]);
		
		System.out.println("Welcome to CharityLooker, the best one stop shop for charity data!");
		String choice = "";
		Scanner inputStr = new Scanner(System.in);
		Locale language = new Locale("English");
		FuzzyScore fs = new FuzzyScore(language);
		
		Charity current = null;
		boolean hold = false;
		boolean nullCur=false;
		do {
			if (!hold){
				current = null;
				System.out.print("Would you like to:\n1. Search by name\n2. Search by business number\n9. Quit\n>");
				choice = inputStr.nextLine();
			}
			hold=false;
			nullCur=false;
			
			if (choice.equals("1")){ //search by name
				System.out.print("Please enter the name of the charity:\n>");
				choice = inputStr.nextLine().toUpperCase();
				if (choice.contentEquals("1"))
					choice="fekjbwkbfkbkfbkb";
				current = hashName.get(choice);
				if (current==null){
					System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these names if so:");
					nullCur=true;
				}
				
			}else if (choice.equals("2")){ //search by business number
				System.out.print("Please enter the business number of the charity:\n>");
				choice = inputStr.nextLine().toUpperCase();
				current = hashBnum.get(choice);
				if (current==null){
					System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these buesiness numbers if so:");
					nullCur=true;
				}
				
			}else if (choice.equals("9")){
				System.out.println("Exiting now...");
			}else{
				System.out.println("Invalid Input - Please try again");
			}
			
			if (nullCur=true){
				String lookFor=choice;
				
				ArrayList<int[]> closeL = new ArrayList<int[]>();
				
				int max = fs.fuzzyScore(choice,choice.toUpperCase());
				int cutOff = (int) (max*.55);
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
				for (int i = 0; i < close.length; i++){
					if (lookFor.equals("1"))
						System.out.println(DP.getNames()[close[i][0]][1]);
					else if (lookFor.equals("2"))
						System.out.println(charities[i].getBnum()+" - "+charities[i].getName());
					
				}
				System.out.println("("+close.length+" return(s))");
				
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
		
	
					
		/*			for (int cur=0; cur<DataPacker.names.length; cur++){
						if (fs.fuzzyScore(l.toUpperCase(),DataPacker.names[cur][0].toUpperCase())>=cutOff){
							System.out.println(DataPacker.names[cur][0]);
							System.out.println(fs.fuzzyScore(l.toUpperCase(),DataPacker.names[cur][0].toUpperCase()));
						}
					}
					
				}else
					System.out.println(j);
				//TODO: ask to show charity data and stats
			}
			
			else if (i.equals("2")) {
				System.out.println("What is the buisness number of desired charity\n>");
				Scanner input3 = new Scanner(System.in);
				String h = input3.next();
				String k = hashBnum.get(h);
				if (k == null)
					System.out.println("The number has either been misspelled or the charity is currently not covered");
				else
					System.out.println(k);
				//TODO: ask to show charity data and stats
			}
			else {
				System.out.println("Invalid Input");
			}
			
			System.out.println("Press 1 to continue:\n>");
			Scanner input4 = new Scanner(System.in);
			g = input4.nextLine();
		}while (g.equals("1"));*/
	}
}

