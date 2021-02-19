import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {

    private static double clustering(int[] x, int[] y, int k) {
        int n = x.length;

        Node[] nodes = new Node[n];
        for(int j = 0; j < n; j++){
            nodes[j] = new Node(x[j], y[j], j);
        }
        PriorityQueue<Edge> Edges = new PriorityQueue<Edge>(new EdgeComparator());
        for(int l = 0; l < n; l++){
            for(int m = l + 1; m < n; m++){
                Double distance = calculateDistance(l, m, x, y);
                Edges.offer(new Edge(l, m, distance));
            }
        }

        int unions = 0;
        while (!Edges.isEmpty()) {
            Edge e = Edges.poll();
            int srtartFind = findNode(e.start, nodes);
            int endFind = findNode(e.end, nodes);

            if(srtartFind != endFind){
                unions++;
                if(nodes[srtartFind].rank > nodes[endFind].rank){
                    nodes[endFind].parent = srtartFind;
                }else{
                    nodes[srtartFind].parent = endFind;
                    if(nodes[srtartFind].rank == nodes[endFind].rank){
                        nodes[endFind].rank++;
                    }
                }
            }
            
            if(unions > n - k) return e.distance;
        }

        return -1;
    }

    public static int findNode(int node, Node[] nodes){
        if(node != nodes[node].parent){
            nodes[node].parent = findNode(nodes[node].parent, nodes);
        }

        return nodes[node].parent;
    }

    private static Double calculateDistance(int initPoint, int endPoint, int[] x, int[] y){
        Double result = Math.sqrt(Math.pow(x[initPoint] - x[endPoint], 2) + Math.pow(y[initPoint] - y[endPoint], 2));
        return result;
    }

    private static class EdgeComparator implements Comparator<Edge>{ 
        public int compare(Edge s1, Edge s2) {
            return s1.distance < s2.distance ? -1 : 1;
        } 
    } 

    private static class Node{
        int x;
        int y;
        int parent;
        int rank;

        public Node(int x, int y, int parent){
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.rank = 0;
        }

        @Override public String toString() {
            return "Node: x=" + x + " y=" + y + " parent='" + parent + "', rank=" + rank;
        }
    }

    private static class Edge{
        public int start, end;
        public Double distance;
        Edge(int start, int end, Double distance){
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}


// How to sort a list
// ArrayList<Edge> edges = new ArrayList<Edge>();
// edges.add(new Edge(l, m, distance));
// Collections.sort(edges, new Comparator<Edge>() {
//     @Override public int compare(Edge bo1, Edge bo2) {
//         return (bo1.getDistance() >  bo2.getDistance() ? 1:-1); 
//     }
// });
