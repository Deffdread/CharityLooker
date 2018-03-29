package Static;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

// fuzzy search for hashmap

public class ClientExperimental {

	public static void main(String[] args) throws IOException {
		DataPacker.dataToCharity();

		// Issues dealing with commas in charity names, but sort works
		// Arrays.sort(charityArray, new nameComparator());
		
		// if a person wants to search for all countries in "

		// businessComparator or nameComparator
		Quick.sort(DataPacker.export,"bnum");
		// Quick.sort(export);
		for (int i = 0; i < 100; i++)
			System.out.println(DataPacker.export[i] + " ");
		
		LinearProbing businesshash = new LinearProbing(DataPacker.export.length);
		LinearProbing namehash = new LinearProbing(DataPacker.export.length);
		for (int i = 0; i < DataPacker.export.length; i++) {
			businesshash.put(DataPacker.export[i].getBnum(), DataPacker.export[i].toString());
			namehash.put(DataPacker.export[i].getName().toUpperCase(), DataPacker.export[i].toString());
		}
		
		String g;
		do {
			// use numbers for choices
			System.out.println("What would you like to search by:\n1. Name\n2. Number\n>");
			// are multiple scanners needed
			Scanner sc = new Scanner(System.in);
			String i = sc.nextLine();
			
			if (i.equals("1")) {
				System.out.println("Please enter name of charity:\n>");
				Scanner input = new Scanner(System.in);
				String l = (input.nextLine()).toUpperCase();
				System.out.print("What would you like to know about the charity:\n1. Business Number\n2. Operating Country\n3. Financial Statistics\n4. Program\n5. Description");
				input = new Scanner(System.in);
				String k = (input.nextLine());
				String n;
				String[] x;
				String j = "";
				switch(k) {
				case "1": n = namehash.get(l); x = n.split(" ,"); j = x[0];
				case "2": n = namehash.get(l); x = n.split(" ,"); j = x[2];
				case "3": n = namehash.get(l); x = n.split(" ,"); j = x[7];
				case "4": n = namehash.get(l); x = n.split(" ,"); j = x[7];
				}
				if (j == null){
					System.out.println("The name has either been misspelled or the charity is currently not covered. Did you mean:");
					
					Locale language = new Locale("English");
					FuzzyScore fs = new FuzzyScore(language);
					
					int max = fs.fuzzyScore(l.toUpperCase(),l.toUpperCase());
					int cutOff = (int) (max*.4);
					
					
					for (int cur=0; cur<DataPacker.names.length; cur++){
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
				String k = businesshash.get(h);
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
		}while (g.equals("1"));
	}
}

