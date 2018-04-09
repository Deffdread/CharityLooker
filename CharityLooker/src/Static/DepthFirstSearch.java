package Static;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class DepthFirstSearch {
	
	private LinkedList<Charity> raw;
	private HashMap<Charity,Boolean> marked; //indexed by business number
	
	public DepthFirstSearch(LinkedList<Charity> data){
		raw = data;
		
		for (int i=1; i<raw.size(); i++){
			int connections = ThreadLocalRandom.current().nextInt(1,6); //not inclusive
			for (int t=0; t<connections; t++){
				int toAdd = ThreadLocalRandom.current().nextInt(0,raw.size()-1);
				System.out.println(raw.get(i)+"|"+raw.get(toAdd));
				raw.get(i).addEdge(raw.get(toAdd));
			}
		}
		
		marked = new HashMap<Charity, Boolean>(); //indexed by business number
		for (Charity c : raw) {
			marked.put(c, false);
		}
		
		dfs(raw.get(0));
	}
	
	private void dfs(Charity curr){
		marked.put(raw.get(raw.indexOf(curr)), true);
		for (Charity e : raw.get(raw.indexOf(curr)).getEdge()){
			if (!marked.get(e)){
				dfs(e);
			}
		}
	}
	
	public void print(){
		System.out.println("Some charities that offer this program are:");
		for (int i=0; i<10; i++){
			int toShow = ThreadLocalRandom.current().nextInt(0,raw.size());
			System.out.println(raw.get(toShow));
		}
	}
	
}
