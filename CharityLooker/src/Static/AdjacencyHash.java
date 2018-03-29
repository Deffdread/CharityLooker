package Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyHash {

		private int m;
		private String[] keys;
		private ArrayList<LinkedList<Charity>> vals;
		
		public AdjacencyHash(int m) throws IOException{
			this.m = m;
			this.keys = new String[this.m];
		}
		
		public int numOfCities() {
			return this.m;
		}
		
		public String[] citynames() {
			return this.keys;
		}

		private int hash(String key) {
			int x = (key.hashCode() % 0x7fffffff) % this.m;
			if (x > 0)
				return x;
			else
				return x*-1;
		}

		public void put(String key, Charity val) {
			if (key == null)
				throw new IllegalArgumentException("first argument to put() is null");
			System.out.println(hash(key) + " " + key);
			int i;
			for (i = hash(key); this.keys[i] != null; i = (i + 1) % m) {
				if (this.keys[i].equals(key)) {
					this.vals.get(i).add(val);
					return;
				}
			}
			this.keys[i] = key;
			this.vals.get(i).add(val);
		}     
		
		private void resize(int capacity) {
			AdjacencyHash temp = new AdjacencyHash(capacity);
			for (int i = 0; i < this.m; i++) {
				if (this.keys[i] != null) {
					for (int j = 0; j < this.m; j++)
						temp.put(this.keys[i], this.vals.get(i).get(j));
				}
			}
			this.keys = temp.keys;
			this.vals = temp.vals;
			this.m = temp.m;
		}
		
		public LinkedList<Charity> get(String key) {
			if (key == null)
				throw new IllegalArgumentException("argument to get() is null");
			for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
				if (keys[i].equals(key)) {
					return this.vals.get(i);
				}
			}
			return null;
		}
		
		public void delete(String key) 
	    {
	        if (!contains(key)) 
	            return;
	        
	        int i = hash(key);
	        while (!key.equals(keys[i])) 
	            i = (i + 1) % this.m;        
	        keys[i] = null;
	        this.vals.get(i) = null;
	    
	        for (i = (i + 1) % this.m; keys[i] != null; i = (i + 1) % this.m)
	        {
	            String tmp1 = keys[i];
	            String tmp2 = vals[i];
	            keys[i] = null;
	            vals[i] = null;
	            this.n--;  
	            put(tmp1, tmp2);            
	        }
	        this.n--;        
	    }
		
		public String toString() {
			String temp = "";
			for (int i = 0; i < keys.length; i++) {
				temp+="\nThe Adjacency list for " + keys[i] + " is ";
				for (int j = 0; j < this.vals.get(i).size(); j++) {
					temp += this.vals.get(i).get(j) + ", ";
				}
			}
			return temp;
		}
}

}
