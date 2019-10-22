package signpost;

import java.util.LinkedList;
import java.util.List;

public class Node {
    protected List<Node> out;
    protected List<Node> in;

    public Node() {
        out = new LinkedList<>();
        in = new LinkedList<>();
    }

    public int outNumber() {
        return out.size();
    }

    public int inNumber() {
        return in.size();
    }

    public void canConnectTo(Node n) {
        this.addOut(n);
        n.addIn(this);
    }

    public boolean addIn(Node n) {
        return in.add(n);
    }

    public boolean addOut(Node n) {
        return out.add(n);
    }


}
