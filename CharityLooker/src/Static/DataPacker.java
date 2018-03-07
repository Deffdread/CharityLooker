package Static;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class DataPacker {
	
static Charity[] charityArray; 
	
	public static void main(String[] args) throws IOException {
		dataToCharity();
		
		//Issues dealing  with commas in charity names, but sort works
		//Arrays.sort(charityArray, new nameComparator());
		
		//businessComparator or nameComparator
		Quick.sort(charityArray, new businessComparator());
		for(int i=0;i<100;i++)
			System.out.println(charityArray[i]+" ");
		
		
		
		
		
	}
	
	public static void dataToCharity() throws IOException{
		Stopwatch stopwatch = new Stopwatch();
		
		double begin=stopwatch.elapsedTime();

		FileInterpreter.listHeaders("data/Charity_Identification.csv");
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
		String[] header1 = new String[] {"BN","Category","Legal Name","City","Country"};
		String[][] data1 = FileInterpreter.getDataFromFile("data/Charity_Identification.csv", header1);
		
		//General Info
		String[] header2 = new String[] {"BN","Program #1","Program #1 Desc (Eng)","Program #2","Program #2 Desc (Eng)","Program #3 ","Program #3 Desc (Eng)"};
		String[][] data2 = FileInterpreter.getDataFromFile("data/Charity_GeneralInfo.csv", header2);
		
		//Compensation
		String[] header3 = new String[] {"BN"};
		String[][] data3 = FileInterpreter.getDataFromFile("data/Charity_Compensation.csv", header3);
		
		//Programs
		String[] header4 = new String[] {"BN","Program Type","Description"};
		String[][] data4 = FileInterpreter.getDataFromFile("data/Charity_Programs.csv", header4);
		
		//Financial
		String[] header5 = new String[] {"BN","Section Used"};
		String[][] data5 = FileInterpreter.getDataFromFile("data/Charity_Financial.csv", header5);
		
		Charity[] export = new Charity[data1.length];
		charityArray = new Charity[data1.length]; // New
		
		//Not sure
		for (int i=0; i<export.length; i++){
			if ( sharedCharity(data1, data2, data3, data4, data5, i) ){
				export[i]=new Charity();  //Note: Unsure


			
			}
		}
		
		
		for(int i = 0; i < charityArray.length;i++) {
			charityArray[i] = new Charity(data1[i][4],data4[i][2],data1[i][0],null,null,null, null, null, null); //Note: not sure about operating countries,Services
			
			/*	For Reference
			private String   name; //name of charity
			private String   desc; //description (category number)
			private String   bnum; //business number/ ID
			private String   land; //parent country
			private String   home; //'hometown'/ where HQ is
			private String[] oper; //operating countries
			private String[] prog; //current programs
			private String[] serv; //current services
			private int[] stat; //financial statistics
		*/
		}
		
		double end=stopwatch.elapsedTime();
		System.out.println("Loaded in: "+( (end-begin)/1000000) );
		
		System.out.println(export.length);
		System.out.println(data1[0][3]); //Note: Wrong
		
		
		Charity test = new Charity(data1[0][4],data4[0][2],data4[0][0], null, null, null, null, null, null);
		System.out.println(test);

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
