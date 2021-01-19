import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Collections;

public class BFS {
    static int[] dist;
    static int[] prev;

    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        bfs(adj, s);
        // reconstructPath(s, t, adj);
        return dist[t];
    }

    private static void bfs(ArrayList<Integer>[] graph, int s){
        dist = new int[graph.length];
        prev = new int[graph.length];
        for(int i = 0; i< graph.length; i++){
            dist[i] = -1;
            prev[i] = -1;
        }
        dist[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(q.size() > 0){
            int u = q.poll();
            ArrayList<Integer> adjNodes = graph[u];
            for(int k=0; k < adjNodes.size(); k++){
                int v = adjNodes.get(k);
                if(dist[v] == -1){
                    q.add(v);
                    dist[v] = dist[u] + 1;
                    prev[v] = u;
                }
            }
        }
    }

    private static ArrayList<Integer> reconstructPath(int s, int t, ArrayList<Integer>[] graph){
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(t != s){
            result.add(t);
            if(prev[t] == -1){
                return null;
            }
            t = prev[t];
        }

        Collections.reverse(result);
        System.out.print(s + 1);
        for(int j=0; j < result.size(); j++){
            System.out.print(">" + (result.get(j) + 1));
        }
        System.out.println();
        return result;
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
        System.out.println(distance(adj, x, y));
    }
}

