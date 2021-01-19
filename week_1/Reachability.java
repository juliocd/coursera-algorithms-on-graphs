import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        graph = adj;
        visited = new boolean[adj.length];
        explore(0);

        return visited[x] && visited[y] ? 1 : 0;
    }

    private static void explore(int v) {
        visited[v] = true;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

