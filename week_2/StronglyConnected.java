import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjR) {
        ArrayList<Integer> postorder = dfs(adjR);

        int cc = 0;
        visited = new boolean[adj.length];
        ArrayList<Integer> orderV = new ArrayList<Integer>();
        for(int k = postorder.size(); k > 0; k--){
            int v = postorder.get(k - 1);
            if(!visited[v]){
                explore(v, adj, orderV);
                cc++;
            }
        }

        return cc;
    }

    private static ArrayList<Integer> dfs(ArrayList<Integer>[] graph){
        visited = new boolean[graph.length];
        ArrayList<Integer> order = new ArrayList<Integer>();

        for(int i=0; i<graph.length; i++){
            if(!visited[i]){
                explore(i, graph, order);
            }
        }

        return order;
    }

    private static void explore(int v, ArrayList<Integer>[] graph, ArrayList<Integer> order) {
        visited[v] = true;
        ArrayList<Integer> adjNodes = graph[v];
        for(int k=0; k<adjNodes.size(); k++){
            if(!visited[adjNodes.get(k)]){
                explore(adjNodes.get(k), graph, order);
            }
        }

        order.add(v);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjR = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            adjR[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adjR[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adjR));
    }
}

