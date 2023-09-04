package memseqdb;

public interface SeqGraphNode<T extends GraphNode> extends GraphNode {

    T getChild(int sequenceNumber);

    T[] children();
}