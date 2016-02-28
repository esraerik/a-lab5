package aılab5;

import java.util.Random;

public class NewSimuAnneal {

	static int state[] = new int[4];
	static int isler[][] = new int[3][3];
	static int makTam[][] = new int[2][3];

	public static void main(String[] args) {
		state[0] = 1;
		state[1] = 3;
		state[2] = -1;
		state[3] = 2;
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
		int[] s = anneal(state);
		for (int i = 0; i < s.length; i++) {
			System.out.println("s " + s[i]);
		}
		//
		// }

	}

	private static int[] anneal(int[] state) {
		Random r = new Random();

		int mak = 0;
		int energy = cost(state, mak);
		int[] new_sol = state;
		int new_energy = energy;
		int[] adjState = state;
		int bestEnergy = energy;
		float ap;
		float T = 1;
		float T_min = (float) 0.0001;
		float alpha = (float) 0.99;
		int i;
		//while (T > T_min) {
			i = 1;
			while (i <= 50000) {
				// mak=r.nextInt(2);
				adjState = neighbor(state);
				// System.out.println("new_sol"+new_sol+"mak"+mak);
				new_energy = cost(new_sol, mak);
				if (new_energy <= bestEnergy) {
					bestEnergy=new_energy;
				}
				// ap=acceptane(old_cost,new_cost,T);
				float a = r.nextFloat();
				int delta = cost(adjState, mak) - cost(state, mak);
				if (delta < 0 || acceptane(energy, new_energy, T) < a) {
					state = adjState;
					energy = new_energy;
				}
				// T=T*alpha;
				i++;
				 T=T*alpha;
			}
			
		
		 System.out.println("cost"+new_energy);
		return state;

	}

	private static float acceptane(int energy, int adjEnergy, float t) {
		if (adjEnergy < energy)
			return 0;
		else {
			return (float) Math.exp((energy - adjEnergy) / t);
		}
	}

	private static int[] neighbor(int[] solution2) {
		
		Random r = new Random();
		
		int a1 = r.nextInt(4);
		int a2 = r.nextInt(4);
		if(a1==4)
			a1=3;
		if(a2==4)
			a2=3;
		while(a1==a2){
			a2 = r.nextInt(4);}
//		System.out.println("a1"+a1);
//		System.out.println("a2"+a2);
		int temp = solution2[a1];
		solution2[a1] = solution2[a2];
		solution2[a2] = temp;
		
		return solution2;
	}

	private static int cost(int[] state, int mak) {
		int cost = 0;
		int x;
		for (int i = 0; i < state.length; i++) {
			if (state[i] != -1) {
				cost += makTam[mak][state[i] - 1];
				if (i < state.length -1 && state[i + 1] != -1) {
					cost += isler[state[i] - 1][state[i + 1] - 1];
				}
				//i++;
			}

			else{
				
				// if(mak==0)
				if(state[i]==-1){
					i++;
				}
				//System.out.println("i "+i);
				if (i < state.length -1){
				cost += makTam[mak + 1][state[i] - 1];}
				if (i < state.length -1 && state[i + 1] != -1) {
					cost += isler[state[i] - 1][state[i + 1] - 1];
				}
			//i++;
			}
		}
		//System.out.println("cost" + cost + "\n");
		return cost;
	}
}
