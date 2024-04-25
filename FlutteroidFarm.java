public class FlutteroidFarm {

    private FlutteroidBSTNode bst;

    public FlutteroidFarm () {
        this.bst = new FlutteroidBSTNode();
    }

    private int getNumberOfFlutteroids (FlutteroidBSTNode node) { //
        FlutteroidBSTNode leftNode = node.getLeftChild();
        FlutteroidBSTNode rightNode = node.getRightChild();
        if (leftNode == null) {
            return 1;
        }
        return 1 + getNumberOfFlutteroids(leftNode) + getNumberOfFlutteroids(rightNode);
    }

    public int getNumberOfFlutteroids() {
        return getNumberOfFlutteroids(this.bst);
    }

    public boolean isFarmEmpty() {
        return getNumberOfFlutteroids() == 0;
    }

    public void clearFarm() {
        this.bst = new FlutteroidBSTNode();
    }

    public boolean addFlutteroid(Flutteroid flutteroid) {
        if (flutteroid == null) {
            return false;
        }
        String name = flutteroid.getNameID();
        if (findFlutteroid(name) != null) {
            return false;
        }
        addFlutteroid(bst, flutteroid);
        return true;
    }

    private FlutteroidBSTNode addFlutteroid(FlutteroidBSTNode localRoot, Flutteroid flutteroid) {
        FlutteroidBSTNode leftNode = localRoot.getLeftChild();
        FlutteroidBSTNode rightNode = localRoot.getRightChild();

        if (leftNode == null && rightNode == null) {
            localRoot.setFlutteroidData(flutteroid);
            return localRoot;
        }
        if (leftNode != null && rightNode == null) {
            FlutteroidBSTNode node = new FlutteroidBSTNode();
            node.setFlutteroidData(flutteroid);
            localRoot.setRightChild (node);
            return node;
        }
        if (leftNode == null && rightNode != null) {
            FlutteroidBSTNode node = new FlutteroidBSTNode();
            node.setFlutteroidData(flutteroid);
            localRoot.setLeftChild (node);
            return node;
        }

        String leftName = leftNode.getFlutteroidData().getNameID();
        String rightName = rightNode.getFlutteroidData().getNameID();
        String flutName = flutteroid.getNameID();

        int compareLeft = Math.abs(flutName.compareTo(leftName));
        int compareRight = Math.abs(flutName.compareTo(rightName));

        if (compareLeft < compareRight) {
            return addFlutteroid (leftNode, flutteroid);
        } else {
            return addFlutteroid(rightNode, flutteroid);
        }
    }

    public Flutteroid findFlutteroid(String flutteroidName) {
        if (flutteroidName == null) {
            return null;
        }
        return findFlutteroid(bst, flutteroidName);
    }

    private Flutteroid findFlutteroid(FlutteroidBSTNode localRoot, String flutteroidName) {
        Flutteroid localFlut = localRoot.getFlutteroidData();
        if (localFlut == null) {
            return null;
        } 
        String localName = localFlut.getNameID();
        if (localName.equals(flutteroidName)) {
            return localFlut;
        }

        FlutteroidBSTNode leftNode = localRoot.getLeftChild();
        FlutteroidBSTNode rightNode = localRoot.getRightChild();

        Flutteroid leftFlut = findFlutteroid(leftNode, flutteroidName);
        Flutteroid rightFlut = findFlutteroid(rightNode, flutteroidName);

        if (leftFlut == null) {
            return rightFlut;
        }
        return leftFlut;
    }

    public Flutteroid removeFlutteroid(String flutteroidName) {
        if (flutteroidName == null || findFlutteroid(flutteroidName) == null) {
            return null;
        }
        return removeFlutteroid(bst, flutteroidName).getFlutteroidData();
    }

    private FlutteroidBSTNode removeFlutteroid (FlutteroidBSTNode localRoot, String flutteroidName) {
        if (localRoot == null) {
            return null;
        }

        int compare = flutteroidName.compareTo (localRoot.getFlutteroidData().getNameID());

        FlutteroidBSTNode leftNode = null;
        FlutteroidBSTNode rightNode = null;
        if (compare < 0) {
            leftNode = localRoot.getLeftChild();
            leftNode = removeFlutteroid(leftNode, flutteroidName);
            localRoot.setLeftChild(leftNode);
            return localRoot;
        } else if (compare > 0) {
            rightNode = localRoot.getRightChild();
            rightNode = removeFlutteroid(rightNode, flutteroidName);
            localRoot.setLeftChild(rightNode);
            return localRoot;
        } else {
            if (leftNode == null) {
                return rightNode;
            }
            if (rightNode == null) {
                return leftNode;
            }

            if (localRoot.getRightChild() == null) {
                localRoot = localRoot.getLeftChild();
                leftNode = leftNode.getLeftChild();
            } else {
                localRoot.setFlutteroidData(findLargestChild(leftNode));;
                return localRoot;
            }
        }
    }

    private Flutteroid findLargestChild(FlutteroidBSTNode localRoot) {
        FlutteroidBSTNode leftNode = localRoot.getLeftChild();
        FlutteroidBSTNode rightNode = localRoot.getRightChild();

        if (leftNode == null && rightNode == null) {return localRoot.getFlutteroidData();}
        else if (leftNode == null && rightNode != null) {return findLargestChild(rightNode);}
        else if (leftNode != null && rightNode == null) {return findLargestChild(leftNode);}
        else {
            Flutteroid leftLargeFlut = findLargestChild(leftNode);
            Flutteroid rightLargeFlut = findLargestChild(rightNode);

            if (leftLargeFlut.getNameID().compareTo (rightLargeFlut.getNameID()) > 0) {
                return leftLargeFlut;
            }
            return rightLargeFlut;
        }
    }
}
