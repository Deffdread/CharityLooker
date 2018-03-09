package Static;

import java.io.IOException;

public class DataPacker {
	
static Charity[] export; 
	
	public static void main(String[] args) throws IOException {
		dataToCharity();
		
		//Issues dealing  with commas in charity names, but sort works
		//Arrays.sort(charityArray, new nameComparator());
		
		//businessComparator or nameComparator
		Quick.sort(export, new businessComparator());
		//Quick.sort(export);
		for(int i=0;i<100;i++)
			System.out.println(export[i]+" ");

		
		
		
	}
	
	public static void dataToCharity() throws IOException{
		Stopwatch stopwatch = new Stopwatch();
		
		double begin=stopwatch.elapsedTime();

		System.out.println("----------");
		/*FileInterpreter.listHeaders("data/Charity_GeneralInfo.csv");
		System.out.println("----------");
		FileInterpreter.listHeaders("data/Charity_Compensation.csv");
		System.out.println("----------");
		FileInterpreter.listHeaders("data/Charity_Programs.csv");
		System.out.println("----------");
		FileInterpreter.listHeaders("data/Charity_Financial.csv");
		System.out.println("----------");*/
		
		
		//Note: Some errors
		//Identification
		/* Meanings
		 * BN - Business number
		 * Category - Type of charity
		 * Legal Name - Name of charity
		 * City - Mailing address city
		 * Country - mailing address ccountry
		 */
		String[] header1 = new String[] {"BN","Category","Legal Name","City","Country"};
		String[][] data1 = FileInterpreter.getDataFromFile("data/Charity_Identification.csv", header1);
		
		//General Info
		/* Meanings
		 * BN - Business Number
		 * Program #1 Code - Operating Area Code
		 * Program #1 - Program Area #1 Percentage
		 * Program #2,3 (Code) - Same as above
		 * 1600 - Is charity private
		 * 2400 - Has the charity carried any political activities
		 */
		String[] header2 = new String[] {"BN","Program #1 Code","Program #1","Program #2 Code","Program #2","Program #3 Code","Program #3","1600","2400"};
		String[][] data2 = FileInterpreter.getDataFromFile("data/Charity_GeneralInfo.csv", header2);
		
		//Compensation
		/* Meanings:
		 * BN - Business Number
		 * 300 - Number of permanent full time, compensated positions
		 * 370 - Number of part-time or part-year employees
		 * 390 - Expenditure on compensated positions
		 */
		String[] header3 = new String[] {"BN","300","370","390"};
		String[][] data3 = FileInterpreter.getDataFromFile("data/Charity_Compensation.csv", header3);
		
		//Programs
		/* Meanings:
		 * BN - Business Number
		 * Program Type - Program type code
		 * Description - Description of program
		 */
		String[] header4 = new String[] {"BN","Program Type","Description"};
		String[][] data4 = FileInterpreter.getDataFromFile("data/Charity_Programs.csv", header4);
		
		//Financial
		/* Meanings:
		 * BN - Business Number
		 * 4700 - Total revenue
		 * 5100 - Total expenditure
		 * 4200 - Total assets
		 * 4350 - Liabilities
		 * 4250 - Assets not used in charitable activities
		 * 4100 - Cash, bank accounts and short-term investments
		 * 4140 - Long term investments
		 */
		String[] header5 = new String[] {"BN","4700","5100","4200","4350","4250","4100","4140"};
		String[][] data5 = FileInterpreter.getDataFromFile("data/Charity_Financial.csv", header5);
		
		export = new Charity[data1.length];
		
		//If statement does not work properly
		for (int i=0; i<export.length; i++){
			//if ( sharedCharity(data1, data2, data3, data4, data5, i) ){
				String name = data1[i][2];
				String desc = data4[i][2];
				String BN = data1[i][0];
				String land = data1[i][4];
				String home = data1[i][3];
				String[] opcy = new String[] {data2[i][1], data2[i][3], data2[i][5]};
				String[] deta = new String[] {data2[i][2],data2[i][4],data2[i][6],};
				String[] fstat = new String[] {data5[i][1],data5[i][2],data5[i][3],data5[i][4],data5[i][5],data5[i][6],data5[i][7]};
 				String[] misc = new String[] {data1[i][2],data3[i][2],data2[i][7],data2[i][8],data3[i][1],data3[i][2],data3[i][3]};
				export[i]=new Charity(name, desc, BN, land, home, opcy, deta, misc, fstat);
				
				//System.out.println(export[i]);
				
			//}
		}
		
		
		
		double end=stopwatch.elapsedTime();
		System.out.println("Loaded in: "+( (end-begin)/1000000) );
		

	}
	
	private static boolean sharedCharity(String[][] d1, String[][] d2, String[][] d3, String[][] d4, String[][] d5, int i){
		if (d1[i][0].compareTo(d2[i][0])==0 && d1[i][0].compareTo(d3[i][0])==0 && d1[i][0].compareTo(d4[i][0])==0 && d1[i][0].compareTo(d5[i][0])==0){
			if (d2[i][0].compareTo(d3[i][0])==0 && d2[i][0].compareTo(d4[i][0])==0 && d2[i][0].compareTo(d5[i][0])==0){
				if (d3[i][0].compareTo(d4[i][0])==0 && d3[i][0].compareTo(d5[i][0])==0){
					if (d4[i][0].compareTo(d5[i][0])==0){
						return(true);
					}
					
				}
			}
		}
		return(false);
	}
	
	
}
