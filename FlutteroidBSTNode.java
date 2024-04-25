/*
 * 
 *
 * Note: DO NOT MODIFY!
 */

/**
 * A Node for a binary search tree that contains Flutteroid data.
 *
 * 
 *
 * @version 1.0 2024-03-06
 * Initial Version
 */
public final class FlutteroidBSTNode {

    /*
     The Flutteroid data held in the node.
     */
    private Flutteroid flutteroidData;
    
    /*
    The left child link for the node.
    */
    private FlutteroidBSTNode leftChild;
    
    /*
    The right child link for the node.
    */
    private FlutteroidBSTNode rightChild;

    /**
     * The default constructor for a Flutteroid Node object. All
     * references are set to null. 
     */
    public FlutteroidBSTNode() {
        flutteroidData = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * A constructor that allows setting the Flutteroid data to a specific 
     * starting value. This could be null. The left and right links are set
     * to null.
     * 
     * @param flutteroidData A reference to the Flutteroid data to set in the node.
     */
    public FlutteroidBSTNode(Flutteroid flutteroidData) {
        this.flutteroidData = flutteroidData;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Allows obtaining access to the Flutteroid data contained in the node.
     * 
     * @return A reference to the Flutteroid data in the node, which could be null.
     */
    public final Flutteroid getFlutteroidData() {
        return flutteroidData;
    }

    /**
     * Allows obtaining access to the left child of the node.
     * 
     * @return A FlutteroidBSTNode reference to the left child of the node, 
     * which could be null.
     */
    public final FlutteroidBSTNode getLeftChild() {
        return leftChild;
    }

    /**
     * Allows obtaining access to the right child of the node.
     * 
     * @return A FlutteroidBSTNode reference to the right child of the node, 
     * which could be null.
     */
    public final FlutteroidBSTNode getRightChild() {
        return rightChild;
    }

    /**
     * Allows setting the Flutteroid data in the node to a new value.
     * 
     * @param flutteroidData A FlutteroidBSTNode reference to set the node data
     * to, which could be null.
     */
    public final void setFlutteroidData(Flutteroid flutteroidData) {
        this.flutteroidData = flutteroidData;
    }

    /**
     * Allows setting the left child FlutteroidBSTNode reference to a new value.
     * 
     * @param leftChild A FlutteroidBSTNode reference to set the left child to,
     * which could be null.
     */
    public final void setLeftChild(FlutteroidBSTNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Allows setting the right child FlutteroidBSTNode reference to a new value.
     * 
     * @param rightChild A FlutteroidBSTNode reference to set the right child to,
     * which could be null.
     */
    public final void setRightChild(FlutteroidBSTNode rightChild) {
        this.rightChild = rightChild;
    }
    
}
