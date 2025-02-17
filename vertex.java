public class Vertex {

    char label;
    boolean flagVisited;
 
    public Vertex(char label) {
        this.label = label;
        this.flagVisited = false;
    }

    public char getLabel() {
        return label;
    }

    public void setVisited(boolean visited) {
        this.flagVisited = visited;
    }

    public boolean isVisited() {
        return flagVisited;
    }
}
