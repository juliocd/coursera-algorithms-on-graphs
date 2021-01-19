import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static boolean[] path;

    private static int acyclic(ArrayList<Integer>[] adj) {
        graph = adj;
        visited = new boolean[adj.length];

        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                path = new boolean[adj.length];
                if(explore(i) == 1){
                    return 1;
                };
            }
        }

        return 0;
    }

    private static int explore(int v) {
        visited[v] = true;
        path[v] = true;
        ArrayList<Integer> adjNodes = graph[v];
        for(int k=0; k<adjNodes.size(); k++){
            if(!visited[adjNodes.get(k)]){
                if(explore(adjNodes.get(k)) == 1){
                    return 1;
                };
            }
            if(path[adjNodes.get(k)]){
                return 1;
            }
        }
        path[v] = false;

        return 0;
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
        System.out.println(acyclic(adj));
    }
}

