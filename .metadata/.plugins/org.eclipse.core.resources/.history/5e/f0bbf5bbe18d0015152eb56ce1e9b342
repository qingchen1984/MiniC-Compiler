package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DFAState {
	
	public int id ;
	public ArrayList<LRDerivation> set = new ArrayList<LRDerivation>();
	
	public DFAState(int id){
		this.id = id;
	}
	
	public boolean addNewDerivation(LRDerivation d){
		if(set.contains(d)){
			return false;
		} else {
			set.add(d);
			return true;
		}
	}
	
	public void print(){
		Iterator<LRDerivation> iter = set.iterator();
		while(iter.hasNext()){
			iter.next().print();
		}
	}
	
	public boolean contains(LRDerivation lrd){
		return set.contains(lrd);
	}
	
	public boolean equals(DFAState state){
		if(set.equals(state.set)){
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<String> getGotoPath(){
		ArrayList<String> result = new ArrayList<String>();
		for(LRDerivation lrd:set){
			if(lrd.d.list.size()==lrd.index){
				continue;
			}
			String s = lrd.d.list.get(lrd.index);
			if(!result.contains(s)){
				result.add(s);
			}
		}
		return result;
	}
	
	public ArrayList<LRDerivation> getLRDs(String s){
		ArrayList<LRDerivation> result = new ArrayList<LRDerivation>();
		for(LRDerivation lrd:set){
			String s1 = lrd.d.list.get(lrd.index);
			if(s1.equals(s)){
				result.add(lrd);
			}
		}
		return result;
	}

}
