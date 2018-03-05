package Static;

import java.io.IOException;

public class DataPacker {
	
	public static void main(String[] args) throws IOException {
		dataToCharity();
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
		
		for (int i=0; i<export.length; i++){
			if ( sharedCharity(data1, data2, data3, data4, data5, i) ){
				export[i]=new Charity();
			}
		}
		
		double end=stopwatch.elapsedTime();
		System.out.println("Loaded in: "+( (end-begin)/1000000) );
		
		System.out.println(export.length);
		
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
