import java.util.*;

public class Dijkstra {
    static int[] dist;
    static int[] prev;
    static PriorityQueue<Node> H;
    static int maxValue = Integer.MAX_VALUE;

    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        dist = new int[adj.length];
        prev = new int[adj.length];

        for(int k = 0; k < adj.length; k++){
            dist[k] = maxValue;
            prev[k] = -1;
        }
        H = new PriorityQueue<Node>(adj.length, (a,b) -> a.distance - b.distance);
        H.add(new Node(s, 0));
        dist[s] = 0;

        while(!H.isEmpty()){
            Node node = H.poll();
            int u = node.number;
            ArrayList<Integer> adjNodes = adj[u];
            ArrayList<Integer> wNodes = cost[u];
            for(int j=0; j < adjNodes.size(); j++){
                int v = adjNodes.get(j);
                int w = wNodes.get(j);
                if(dist[v] > (dist[u] + w) && dist[u] != maxValue){
                    dist[v] = (dist[u] + w);
                    prev[v] = u;
                    changePriority(v, dist[v]);
                }
            }
        }

        return dist[t] == maxValue || dist[t] < 0 ? -1 : dist[t];
    }

    private static class Node{
        public int distance, number;
        Node(int number, int distance){
            this.number = number;
            this.distance = distance;
        }
    }

    private static void changePriority(int v, int distValue){
        H.add(new Node(v, distValue));
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}