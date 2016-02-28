package aýlab5;

import java.util.Random;

public class SimuAnneal {
	static int isler[][] = new int[3][3];
	static int makTam[][] = new int[2][3];
	static int solution;
	static Boolean gidildi[] = new Boolean[3];

	public static void main(String[] args) {
		for (int j = 0; j < 3; j++) {
			gidildi[j] = false;
		}

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
		Random r = new Random();
		solution = r.nextInt(3);
		// System.out.println(solution);
		// farklý bir random sayý da üretilirdi

		for (int i = 0; i < 3; i++) {
			int s = anneal(solution);
			System.out.println("s " + s);
		}

	}

	private static int anneal(int solution) {
		Random r = new Random();
		int mak = r.nextInt(2);
		int old_cost = cost(solution, mak);
		int new_sol;
		int new_cost = 0;
		float ap;
		float T = 1;
		float T_min = (float) 0.00001;
		float alpha = (float) 0.9;
		int i;
		while (T > T_min) {
			System.out.println("T" + T);
			i = 1;
			while (i <= 100) {
				mak = r.nextInt(2);
				new_sol = neighbor(solution);
				// System.out.println("new_sol"+new_sol+"mak"+mak);
				new_cost = cost(new_sol, mak);
				new_cost += isler[solution][new_sol];
				ap = acceptane(old_cost, new_cost, T);
				float a = r.nextFloat();
				if (ap > a) {
					solution = new_sol;
					old_cost = new_cost;
				}
				i++;
			}
			T = T * alpha;
		}
		System.out.println("cost" + new_cost);
		gidildi[solution] = true;
		return solution;

	}

	private static float acceptane(int old_cost, int new_cost, float t) {
		float x = (new_cost - old_cost) / (10 * t);
		float e = (float) 2.71828;

		return (float) Math.pow(e, x);
	}

	private static int neighbor(int solution2) {
		int i;
		while (true) {
			Random r = new Random();
			i = r.nextInt(3);
			if (isler[solution2][i] != 0 && gidildi[i] == false)
				break;
			else
				continue;
		}
		// System.out.println("i "+i);
		return i;
	}

	private static int cost(int sol2, int mak) {
		int cost = makTam[mak][sol2];
		// System.out.println(sol2+" "+mak);
		return cost;
	}
}
