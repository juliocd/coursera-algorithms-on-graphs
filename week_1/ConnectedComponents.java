import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] ccnum;
    static int cc = 1;

    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        graph =adj;
        visited = new boolean[adj.length];
        ccnum = new int[adj.length];

        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                explore(i);
                cc++;
            }
        }

        return cc - 1;
    }

    private static void explore(int v) {
        visited[v] = true;
        ccnum[v] = cc;
        ArrayList<Integer> adjNodes = graph[v];
        for(int k=0; k<adjNodes.size(); k++){
            if(!visited[adjNodes.get(k)]){
                explore(adjNodes.get(k));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

