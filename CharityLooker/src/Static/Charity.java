package Static;

import java.util.Comparator;

/**
 * 
 * 
 * @author Jason Nagy, 400055130
 * @since February 26/18
 * @version 1.0
 */
public class Charity implements Comparable<Charity>{
	private String   name; //name of charity
	private String   desc; //description (category number)
	private String   bnum; //business number/ ID
	private String   land; //parent country
	private String   home; //'hometown'/ where HQ is
	private String[] oper; //operating countries
	private String[] prog; //current programs
	private String[] serv; //current services
	private int[] stat; //financial statistics
	
	public Charity(){ //Catch "Void" charity
		this.name="Empty";
		this.desc="Void";
		this.bnum="00000";
		this.land="Nowhere";
		this.home="Nowhere";
		this.oper=new String[] {"Nowhere"};
		this.prog=new String[] {"Nothing"};
		this.serv=new String[] {"Nothing"};
		this.stat=new int[] {0};
	}
	
	public Charity(String name, String description, String ID, String pcountry, String hometown, String[] ocountry, String[] programs, String[] services, int[] financial){
		this.name=name;
		this.desc=description;
		this.bnum=ID;
		this.land=pcountry;
		this.home=hometown;
		this.oper=ocountry;
		this.prog=programs;
		this.serv=services;
		this.stat=financial;
	}
	
	/* compareTo()
	 * s1 == s2 :0
	 * s1 > s2  :positive value
	 * s1 < s2  :negative value
	 */
	
	public int compareTo(Charity o, String property){
		if (property.compareTo("name")==0){
			return(this.name.compareTo(o.getName()));
		}else if (property.compareTo("desc")==0){
			return(this.desc.compareTo(o.getDesc()));
		}else if (property.compareTo("bnum")==0){
			return(this.bnum.compareTo(o.getBnum()));
		}else if (property.compareTo("land")==0){
			return(this.land.compareTo(o.getHland()));
		}else if (property.compareTo("home")==0){
			return(this.home.compareTo(o.getHome()));
		}else if (property.compareTo("oper")==0){ //used to determine if they share a operating location
			for (int i=0; i<this.oper.length; i++){
				for (int j=0; j<o.getOland().length; j++){
					if (this.oper[i]==o.getOland(j)){
						return(1); //yes, they share a country
					}
				}
			}
			return(0); //no they dont
		}else if (property.compareTo("prog")==0){ //who has more programs
			return( (this.prog.length > o.getProg().length) ? 1 : -1 );
		}else if (property.compareTo("serv")==0){ //who has more services
			return( (this.serv.length > o.getServ().length) ? 1 : -1 );
		}else{
			return(0);
		}
	}
	
	public int compareTo(Charity o, int index){ //to compare financial statistics
		if (this.stat[index] > o.getStats(index)){
			return(1);
		}else if (this.stat[index] < o.getStats(index)){
			return(-1);
		}
		return(0);
	}
	
	public void setName(String s){
		this.name=s;
	}
	public String getName(){
		return (this.name);
	}
	
	public void setDesc(String s){
		this.desc=s;
	}
	public String getDesc(){
		return (this.desc);
	}
	
	public void setBnum(String s){
		this.bnum=s;
	}
	public String getBnum(){
		return (this.bnum);
	}
	
	public void setHland(String s){
		this.land=s;
	}
	public String getHland(){
		return (this.land);
	}
	
	public void setHome(String s){
		this.home=s;
	}
	public String getHome(){
		return (this.home);
	}
	
	public void setOland(String[] s){
		this.oper=s;
	}
	public String[] getOland(){
		return (this.oper);
	}
	public String getOland(int i){
		return (this.oper[i]);
	}
	
	public void setProg(String[] s){
		this.prog=s;
	}
	public String[] getProg(){
		return (this.prog);
	}
	public String getProg(int i){
		return (this.prog[i]);
	}
	
	public void setServ(String[] s){
		this.serv=s;
	}
	public String[] getServ(){
		return (this.serv);
	}
	public String getServ(int i){
		return (this.serv[i]);
	}
	
	public void setStats(int[] x){
		this.stat=x;
	}
	public int[] getStats(){
		return (this.stat);
	}
	public int getStats(int i){
		return (this.stat[i]);
	}
	
	
	//Instead of printing a memory location, prints name and bnum for object prints 
	  @Override
	  public String toString() {
	    return this.name+" "+this.bnum;
	  }

	//Because we use comparable, the default comparable must be included. (This is not suppose to do anything) 
	@Override
	public int compareTo(Charity o) {
		// TODO Auto-generated method stub
		return 0;
	}
}








class nameComparator implements Comparator<Charity> 
{


	public int compare(Charity o1, Charity o2) {
    	String name1 = o1.getName();
    	String name2 = o2.getName(); 
    	
    	return name1.compareTo(name2);
	}

}


class businessComparator implements Comparator<Charity> 
{


	public int compare(Charity o1, Charity o2) {
    	String name1 = o1.getBnum();
    	String name2 = o2.getBnum(); 
    	
    	return name1.compareTo(name2);
	}

}
