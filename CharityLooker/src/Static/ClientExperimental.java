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
		
		/*System.out.println("started");
		AdjacencyHash hashProg = new AdjacencyHash(16);
		System.out.println("ended");*/
		
		for (int i = 0; i < charities.length; i++) {
			hashBnum.put(charities[i].getBnum(), charities[i]);
			hashName.put(charities[i].getName().toUpperCase(), charities[i]);
			/*for (int j = 0; j < charities[i].getProg().length; j++) {
				hashProg.put(charities[i].getProg()[j], charities[i]);
			}*/
		
		}
		
		/*System.out.println("started2");
		LinkedList<Charity> charity = hashProg.get("I1");
		for (int i = 0; i < charity.size(); i++) {
			System.out.println(charity.get(i));
		}
		System.out.println("ended2");*/
		
		
		System.out.println("Welcome to CharityLooker, the best one stop shop for charity data!");
		String choice = "";
		Scanner inputStr = new Scanner(System.in);
		Locale language = new Locale("English");
		FuzzyScore fs = new FuzzyScore(language);
		
		Charity current = null;
		boolean hold = false;
		boolean nullCur=false;
		String mode="0";
		do {
			if (!hold){
				current = null;
				System.out.print("Would you like to:\n1. Search by name\n2. Search by business number\n9. Quit\n>");
				choice = inputStr.nextLine();
			}
			hold=false;
			nullCur=false;
			mode="0";
			
			if (choice.equals("1")){ //search by name
				mode="1";
				System.out.print("Please enter the name of the charity:\n>");
				choice = inputStr.nextLine().toUpperCase();
				current = hashName.get(choice);
				if (current==null){
					System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these names if so:");
					nullCur=true;
				}
				
			}else if (choice.equals("2")){ //search by business number
				mode="2";
				System.out.print("Please enter the business number of the charity:\n>");
				choice = inputStr.nextLine().toUpperCase();
				current = hashBnum.get(choice);
				if (current==null){
					System.out.println("The entered charity was not found. Did you mean one of the following? Please retry using one of these business numbers if so:");
					nullCur=true;
					choice="2";
				}
				
			}else if (choice.equals("9")){
				System.out.println("Exiting now...");
			}else{
				System.out.println("Invalid Input - Please try again");
			}
			
			if (nullCur==true){
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
				System.out.println("You have selected: "+current.getName());
				
				do{
					System.out.print("Would you like to:\n1. View basic data\n2. View operating data\n3. View financial data\n4. Miscellaneous data\n5. Find similar charities\n9. Return to charity selection\n>");
					choice = inputStr.nextLine();
					
					if (choice.equals("1")){
						System.out.println("Description: "+current.getDesc());
						System.out.println("Business number: "+current.getBnum());
					}else if (choice.equals("2")){
						System.out.print("Would you like to see: \n1. Home country and HQ location\n2. Operating country\n3. Programs\n>");
						choice = inputStr.nextLine();
						if (choice.equals("1")){
							System.out.println("Home country: "+current.getHland());
							System.out.println("HQ location: "+current.getHome());
						}else if (choice.equals("2")){
							System.out.println("Operating country: "+current.getOland());
						}else if (choice.equals("3")){
							for (int i=0; i<current.getProg().length/2; i++){
								if (!current.getProg(i).equals(""))
									System.out.println(current.getProg(i)+": "+current.getProg(i+3));
							}
						}else{
							System.out.println("Invalid input");
						}
						// Financial
						/*
						 * Meanings: BN - Business Number 4700 - Total revenue 5100 - Total expenditure
						 * 4200 - Total assets 4350 - Liabilities 4250 - Assets not used in charitable
						 * activities 4100 - Cash, bank accounts and short-term investments 4140 - Long
						 * term investments
						 */
					}else if (choice.equals("3")){
						System.out.print("Would you like to see:\n1. Accounting information\n2. Cash and Investment amounts\n3. Private ownership\n>");
						choice = inputStr.nextLine();
						if (choice.equals("1")){
							System.out.println("Total revenue: $"+current.getStats(0));
							System.out.println("Total expenditure: $"+current.getStats(1));
							System.out.println("Total assets: $"+current.getStats(2));
							System.out.println("Liabilities: $"+current.getStats(3));
							System.out.println("Assets not used towards programs: $"+current.getStats(4));
						}else if (choice.equals("2")){
							System.out.println("Cash and short term investment: "+current.getStats(5));
							System.out.println("Long term investment: "+current.getStats(6));
						}else if (choice.equals("3")){
							System.out.println("Is the charity privately owned: "+current.getServ(7));
						}
					}
					
				} while (!choice.equals("9"));
				choice="0";
			}
			
		}while(!choice.contentEquals("9"));
		inputStr.close();
	}
}

