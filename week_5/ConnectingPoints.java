import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        int nPoints = x.length;
        double[] cost = new double[nPoints];
        int[] parent = new int[nPoints];
        boolean[] visited = new boolean[nPoints];
        
        for(int l = 0; l < nPoints; l++){
            cost[l] = Double.MAX_VALUE;
            parent[l] = -1;
        }
        cost[0] = 0;
        PriorityQueue<Node> PriorQ = new PriorityQueue<Node>(nPoints, (a,b) -> {
            if(a.distance > b.distance){
                return 1;
            }else if (a.distance < b.distance){
                return -1;
            }else{
                return 0;
            }
        });
        PriorQ.add(new Node(0, 0));

        while(!PriorQ.isEmpty()){
            Node v = PriorQ.poll();
            visited[v.getNumber()] = true;
            for(int z = 0; z < nPoints; z++){
                if(visited[z]){
                    continue;
                }

                double distance = calculateDistance(v.getNumber(), z, x, y);
                if(cost[z] > distance){
                    cost[z] = distance;
                    parent[z] = v.getNumber();
                    PriorQ.add(new Node(z, distance));
                }
            }
        }

        // System.out.println(Arrays.toString(parent));
        // System.out.println(Arrays.toString(cost));

        for(int s = 0; s < cost.length; s++){
            result += cost[s];
        }

        return result;
    }

    private static double calculateDistance(int initPoint, int endPoint, int[] x, int[] y){
        double result = Math.sqrt(Math.pow(x[initPoint] - x[endPoint], 2) + Math.pow(y[initPoint] - y[endPoint], 2));
        return result;
    }

    // private static boolean containsNode(final PriorityQueue<Node> priorityQueue, final int number){
    //     Iterator<Node> it = priorityQueue.iterator();
    //     while(it.hasNext()) {
    //         Node node = it.next();
    //         if(node.getNumber() == number){
    //             return true;
    //         }
    //     }

    //     return false;
    // }

    private static class Node{
        public double distance;
        public int number;
        Node(int number, double distance){
            this.number = number;
            this.distance = distance;
        }

        public int getNumber(){
            return this.number;
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
        System.out.println(minimumDistance(x, y));
    }
}

