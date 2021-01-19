import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    static int[] dist;
    static int[] color;
    static boolean[] visited;

    private static int bipartite(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];
        color = new int[adj.length];
        dist = new int[adj.length];

        for(int i = 0; i< adj.length; i++){
            color[i] = -1;
            dist[i] = -1;
        }

        for(int l=0; l<adj.length; l++){
            if(!visited[l]){
                if(bfs(adj, l) == 0){
                    return 0;
                };
            }
        }

        return 1;
    }

    private static int bfs(ArrayList<Integer>[] graph, int s){
        int currentColor = 1;
        dist[s] = 0;
        color[s] = currentColor;
        visited[s] = true;
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
                    color[v] = color[u] == 1 ? -1 : 1;
                    visited[v] = true;
                }
                if(color[v] == color[u]){
                    return 0;
                }
            }
        }

        // for(int t=0; t < color.length; t++){
        //     System.out.println("v:" + (t + 1) + " c:" + color[t]);
        // }

        return 1;
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
        System.out.println(bipartite(adj));
    }
}

