import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph { 

    private int maxVertex = 10;
    private Vertex[] vertexList;
    private int[][] adjacencyMatrix;
    private int countVertex = 0;

    public Graph() {
        vertexList = new Vertex[maxVertex];
        adjacencyMatrix = new int[maxVertex][maxVertex];
        countVertex = 0;

        for (int i = 0; i < maxVertex; i++) {
            for (int j = 0; j < maxVertex; j++) {
                adjacencyMatrix[i][j] = -1;
            }
        }
    }

    public void addVertex(char label) {
        vertexList[countVertex++] = new Vertex(label);
    }

    public void addEdge(int a, int b, int c) {
        adjacencyMatrix[a][b] = c;
        adjacencyMatrix[b][a] = c;
    }

    public int indexVertex(char label) {
        for (int i = 0; i < countVertex; i++) {
            if (vertexList[i].label == label) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(char a, char b, int c) {
        int source = indexVertex(a);
        int dest = indexVertex(b);
        addEdge(source, dest, c);
    }

    public void printAdjacencyMatrix() {
        for (int i = 0; i < countVertex; i++) {
            System.out.print("  " + vertexList[i].getLabel() + " ");
        }
        System.out.println();

        for (int i = 0; i < countVertex; i++) {
            System.out.print(vertexList[i].getLabel() + " ");
            for (int j = 0; j < countVertex; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs() {
        if (countVertex > 0) {
            dfs(0);
        }
    }

    public void dfs(int seed) {
        Stack<Integer> stack = new Stack<>();
        stack.push(seed);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!vertexList[current].isVisited()) {
                System.out.print(vertexList[current].getLabel() + " ");
                vertexList[current].setVisited(true);

                for (int i = countVertex - 1; i >= 0; i--) {
                    if (adjacencyMatrix[current][i] != -1 && !vertexList[i].isVisited()) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    public void bfs() {
        bfs(vertexList[0]); 
    }

    public void bfs(Vertex seed) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(seed);
        seed.setVisited(true);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();  
            System.out.print(current.getLabel() + " ");

            for (int i = 0; i < countVertex; i++) {
                if (adjacencyMatrix[indexVertex(current.getLabel())][i] != -1 && !vertexList[i].isVisited()) {
                    queue.add(vertexList[i]);
                    vertexList[i].setVisited(true);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(countVertex).append(" vertices.\n");
        for (int i = 0; i < countVertex; i++) {
            sb.append(vertexList[i].getLabel()).append(" ");
        }
        return sb.toString();
    }
}
