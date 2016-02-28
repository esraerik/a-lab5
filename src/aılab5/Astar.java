package aýlab5;

import java.util.ArrayList;

public class Astar {

	static ArrayList<Integer> openList = new ArrayList<Integer>(6);
	static ArrayList<Integer> closedList = new ArrayList<Integer>(6);
	static int[] h1 = new int[6];
	static int[] f = new int[6];
	static int graph[][] = new int[6][6];
	static int goal = 5;
	static int[] actual = new int[6];
	public static void main(String[] args) {
		h1[0] = 4;// heuristic
		h1[1] = 2;
		h1[2] = 6;
		h1[3] = 2;
		h1[4] = 3;
		h1[5] = 0;
		
		openList.add(0);// baþlangýç node openliste eklendi

		graph[0][0] = 0;// dizi ile graph oluþturuldu.
		graph[0][1] = 1;
		graph[0][2] = 0;
		graph[0][3] = 0;
		graph[0][4] = 0;
		graph[0][5] = 12;
		graph[1][0] = 0;
		graph[1][1] = 0;
		graph[1][2] = 3;
		graph[1][3] = 1;
		graph[1][4] = 0;
		graph[1][5] = 0;
		graph[2][0] = 0;
		graph[2][1] = 0;
		graph[2][2] = 0;
		graph[2][3] = 0;
		graph[2][4] = 3;
		graph[2][5] = 0;
		graph[3][0] = 0;
		graph[3][1] = 0;
		graph[3][2] = 0;
		graph[3][3] = 0;
		graph[3][4] = 1;
		graph[3][5] = 2;
		graph[4][0] = 0;
		graph[4][1] = 0;
		graph[4][2] = 0;
		graph[4][3] = 0;
		graph[4][4] = 0;
		graph[4][5] = 3;
		graph[5][0] = 0;
		graph[5][1] = 0;
		graph[5][2] = 0;
		graph[5][3] = 0;
		graph[5][4] = 0;
		graph[5][5] = 0;
		System.out.println("baþladý\n");
		aStar();
		for (int i = 0; i < closedList.size(); i++) {
			System.out.println(closedList.get(i)+"-->");
		}
		
	}

	public static void aStar() {
		int current;
		f[0] = 0 + h1[0];// start node un f i hesaplandý
		
		while (openList.size()!=0) {
			
			current = compareF();
			
			System.out.println("current en küçük f "+current);
			openList.remove(current);
			// f i en küçük olaný seçtik ve open list
					
			System.out.println("size of open"+ openList.size());// den kaldýrýp closed list e ekledik.
			closedList.add(current);
			
			ArrayList<Integer>komsu=new ArrayList<Integer>();
			komsu = komsuBul(current);
			
			if (komsu != null) {
				
				for (int i = 0; i < komsu.size(); i++) {
					int g=0;
						if(closedList.contains(komsu.get(i)))
						{
							System.out.println("Bu komþu closed listte\n"+komsu.get(i));
							
						}
						else if (!openList.contains(komsu.get(i))){ 
							openList.add(komsu.get(i));
							System.out.println("komsu"+komsu.get(i)+"open list"+openList.get(openList.indexOf(komsu.get(i))));
							g=findG(komsu.get(i))+graph[current][komsu.get(i)];
							f[komsu.get(i)]=g+h1[komsu.get(i)];
							System.out.println("\nf "+f[komsu.get(i)]);
						}
					}
				}
			
			else{
				System.out.println("Komsu yok\n");
				break;
			}
		}

	}

	private static int findG(int current) {
	int g=0;
	for (int i = 0; i <closedList.size()-1; i++) 
	{
			g+=graph[closedList.get(i)][closedList.get(i+1)];
			}
		
	
	return g;
		
	}

	private static ArrayList<Integer> komsuBul(int current) {
		
	ArrayList<Integer>komsular=new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {

			if (graph[current][i] != 0) {
				komsular.add(i);
			}

		}
		return komsular;

	}

	private static int compareF() {
	int minNode =0;
		for (int i = 0; i < openList.size(); i++) {
			if (f[openList.get(i)] < f[minNode])
				minNode =openList.get(i) ;
		}
		return minNode;
	}

}