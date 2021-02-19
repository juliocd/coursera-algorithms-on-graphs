import java.util.*;

public class ShortestPaths {
    static int[] dist;
    static boolean[] visited;
    static int maxValue = Integer.MAX_VALUE;

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        dist = new int[adj.length];
        visited = new boolean[adj.length];

        for(int i=0; i < adj.length; i++){
            dist[i] = maxValue;
        }
        dist[s] = 0;
        hasNegativeCycle(adj, cost);

        System.out.println(Arrays.toString(dist));
    }

    static private boolean hasNegativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost){
        boolean hasChanged = false;
        for(int m = 0; m < adj.length; m++){
            for(int k = 0; k < adj.length; k++){
                ArrayList<Integer> adjNodes = adj[k];
                ArrayList<Integer> wNodes = cost[k];
                for(int j=0; j < adjNodes.size(); j++){
                    int v = adjNodes.get(j);
                    int w = wNodes.get(j);
                    if(dist[v] > (dist[k] + w) && dist[k] != maxValue){
                        dist[v] = (dist[k] + w);
                        if(m == adj.length - 1){
                            return true;
                        }
                        hasChanged = true;
                        visited[v] = true;
                    }
                }
            }

            if(hasChanged == false){
                return false;
            }
            hasChanged = false;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

