import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    static ArrayList<Integer> order;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];
        graph = adj;
        order = new ArrayList<Integer>();
        
        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                explore(i);
            }
        }

        return order;
    }

    private static void explore(int v) {
        visited[v] = true;
        ArrayList<Integer> adjNodes = graph[v];
        for(int k=0; k<adjNodes.size(); k++){
            if(!visited[adjNodes.get(k)]){
                explore(adjNodes.get(k));
            }
        }

        order.add(v);
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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x = order.size(); x > 0; x--) {
            System.out.print((order.get(x - 1) + 1) + " ");
        }
    }
}

