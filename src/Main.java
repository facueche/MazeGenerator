import java.util.ArrayList;


public class Main {

	public final static char OUT = 'O';
	public final static char FRONTIER = 'F';
	public final static char IN = ' ';
	public final static char BLOCK = 'B';
	
	public static void main(String[] args) {
		//Se generan M y N para el tamaño de la matriz
				int M = (int) (Math.random()*20+10);
				if (M % 2 == 0)
					M += 1;
				int N = (int) (Math.random()*20+10);
				if (N % 2 == 0)
					N += 1;
				
				//Se crea el laberinto
				char[][] maze = new char[M][N];
				//Se crea la lista de Prioridad
				ArrayList<Vector2> priorityQueue = new ArrayList();

				InitializeMaze (maze, priorityQueue, M, N);


				int randomInitialPosition = (int) (Math.random()*priorityQueue.size());
				System.out.println(randomInitialPosition);
				Vector2 posPri = (Vector2)priorityQueue.get(randomInitialPosition);
				int i = posPri.x;
				int j = posPri.y;

				//Se marca el primer IN al azar
				maze [i][j] = IN;
				System.out.println("I: "+i+" , J: "+j);

				//Se crea la lista minima
				ArrayList<Vector2> lowestPriorityList = new ArrayList ();
				Vector2 posLowPri;
				//Se procede a asignar a los vecinos
				CheckNeighbor (maze, lowestPriorityList, i, j, M, N);
				int randomLowPri;

				
				while (lowestPriorityList.size() != 0) {
					//Selecciona una celda FRONTIER al azar
//					System.out.println("TAMAÑO : "+lowestPriorityList.size());
					randomLowPri = (int)(Math.random()*lowestPriorityList.size());
//					System.out.println("RANDOM : "+randomLowPri);
					posLowPri = (Vector2)lowestPriorityList.get(randomLowPri);
					i = posLowPri.x;
					j = posLowPri.y;
					maze[i][j] = IN;

					//Se conectan los caminos
					if(i-2 > 0){
						if(maze[i-2][j] == IN && maze[i-1][j] == BLOCK){
							maze[i-1][j] = IN;}}
					else if (i+2 < N){
						if(maze[i+2][j] == IN && maze[i+1][j] == BLOCK){
							maze[i+1][j] = IN;}}
					else if (j-2 > 0){
						if(maze[i][j-2] == IN && maze[i][j-1] == BLOCK){
							maze[i][j-1] = IN;}}
					else if (j+2 < M){
						if(maze[i][j+2] == IN && maze[i][j+1] == BLOCK){
							maze[i][j+1] = IN;}}

					CheckNeighbor (maze, lowestPriorityList, i, j, M, N);
					System.out.println(lowestPriorityList.size());
					lowestPriorityList.remove(posLowPri);
					System.out.println(lowestPriorityList.size());
					
				}
				MostrarLab (maze, M, N);
	}
	
	static void MostrarLab(char[][] maze, int m, int n){
		System.out.println("M: " + m + " , N: " + n);
		for (int i = 0; i<m; i++){
			String cad = "";
			for (int j = 0; j<n; j++) {
				cad += maze[i][j];
			}
			System.out.println(cad);
		}
		System.out.println();
	}

	static void InitializeMaze(char[][] maze, ArrayList<Vector2> list, int M, int N){
		for (int i = 0; i<M; i++)
			for (int j = 0; j<N; j++) {
				maze[i][j] = BLOCK;
				if((i+1)%2==0 && (j+1)%2==0){
					maze[i][j] = OUT;
					list.add(new Vector2(i,j));
				}
			}
	}

	static void CheckNeighbor (char[][] maze, ArrayList<Vector2> list, int i, int j, int M, int N){
		if (i - 2 > 0) {
			if(maze [i - 2][j] != IN){
				maze [i - 2][j] = FRONTIER;
				list.add(new Vector2(i-2,j));
			}
		}
		if (i + 2 < M-1){
			if(maze [i + 2][j] != IN){
				maze [i + 2][j] = FRONTIER;
				list.add(new Vector2(i+2,j));
			}
		}
		if (j - 2 > 0) {
			if(maze [i][j - 2] != IN){
				maze [i][j - 2] = FRONTIER;
				list.add(new Vector2(i,j-2));
			}
		}
		if (j + 2 < N-1) {
			if(maze [i][j + 2] != IN){
				maze [i][j + 2] = FRONTIER;
				list.add(new Vector2(i,j+2));
			}
		}
	}

}
