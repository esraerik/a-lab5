package aýlab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tabu {
	static ArrayList<Integer> state= new ArrayList<Integer>();
	static int isler[][] = new int[3][3];
	static int makTam[][] = new int[2][3];
	public static void main(String[] args) {
		
		state.add(1);
		state.add(-1);
		state.add(3);
		state.add(2);
		isler[0][0] = 0;
		isler[0][1] = 2;
		isler[0][2] = 4;
		isler[1][0] = 5;
		isler[1][1] = 0;
		isler[1][2] = 6;
		isler[2][0] = 7;
		isler[2][1] = 8;
		isler[2][2] = 0;
		makTam[0][0] = 4;
		makTam[0][1] = 5;
		makTam[0][2] = 3;
		makTam[1][0] = 8;
		makTam[1][1] = 9;
		makTam[1][2] = 3;
		ArrayList<Integer> s = tabuSearch(state);
		for (int i = 0; i < s.size(); i++) {
			System.out.println("s " + s.get(i));
		}
		//
		// }


	}
	private static ArrayList<Integer> tabuSearch(ArrayList<Integer> state) {
		int tabusize=3;
		int maliyet=0;
		ArrayList<Integer> best_sol=state;
		int mak=0;
		int yenimaliyet=0;
		int eniyimaliyet=cost(state,mak);
		ArrayList<Integer>  adaycozum = new ArrayList<Integer>();
		Random r=new Random();
		ArrayList<ArrayList<Integer>> tabuList=new ArrayList<ArrayList<Integer>>();//??
		tabuList.add(state);
		//System.out.println("ilk "+tabuList.get(0)[1]);
		ArrayList<ArrayList<Integer>> adaylist=new ArrayList<ArrayList<Integer>>();
		adaylist.add(state);
		int i=0;//durdurma kriteri
		boolean control=false;
		
		while(i<500){
			
			for(int j=0;j<2;j++){
				adaycozum=neighbor(state);
				
				System.out.println(tabuList.containsAll(adaycozum));
				if(!tabuList.containsAll(adaycozum))
					adaylist.add(adaycozum);
				}
			int a=r.nextInt(adaylist.size()-1);
			adaycozum=adaylist.get(a);
			
			if(cost(adaycozum, mak)<=cost(state, mak)){
				maliyet=cost(adaycozum, mak);
				best_sol=adaycozum;
				tabuList.add(state);
				while(tabuList.size()>tabusize)
					tabuList.remove(0);
			}
			i++;
		}
		
		System.out.println("Maliyet="+maliyet);
		return state;
	}
	private static int cost(ArrayList<Integer> state, int mak) {
		
		int cost = 0;
		int x;
		for (int i = 0; i < state.size(); i++) {
			if (state.get(i) != -1) {
				cost += makTam[mak][state.get(i) - 1];
				if (i < state.size() - 1 && state.get(i+1) != -1) {
					cost += isler[state.get(i) - 1][state.get(i+1) - 1];
					//i++;
				}
			}
			else{
				
				if(state.get(i)==-1){
					i++;
				}
				//System.out.println("i "+i);
				if (i < state.size() -1){
				cost += makTam[mak + 1][state.get(i) - 1];}
				if (i < state.size() -1 && state.get(i+1) != -1) {
					cost += isler[state.get(i) - 1][state.get(i+1) - 1];
				}
			
			}
		}
		//System.out.println("cost" + cost + "\n");
		return cost;
	}
	private static ArrayList<Integer> neighbor(ArrayList<Integer> solution2) {
		Random r = new Random();
		int a1 = r.nextInt(4);
		int a2 = r.nextInt(4);
		if(a1==a2)
		{
			 a2 = r.nextInt(4);
		}
		int temp = solution2.get(a1);
		int temp2=solution2.get(a2);
		solution2.remove(a1);
		solution2.add(a1,temp2);
		solution2.remove(a2);
		solution2.add(a2, temp);
		
		for (int j = 0; j < solution2.size(); j++) {
			System.out.println(solution2.get(j));
		}
		
		return solution2;
		
	}

}
