import java.nio.file.FileSystemNotFoundException;

public class FlutteroidFarm {

    private FlutteroidBSTNode bst;
    private int time;

    public FlutteroidFarm () {
        this.bst = new FlutteroidBSTNode();
    }

    private int getNumberOfFlutteroids (FlutteroidBSTNode node) { //
        FlutteroidBSTNode lNode = node.getLeftChild();
        FlutteroidBSTNode rNode = node.getRightChild();
        if (lNode == null) {
            return 1;
        }
        return 1 + getNumberOfFlutteroids(lNode) + getNumberOfFlutteroids(rNode);
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
        FlutteroidBSTNode lNode = localRoot.getLeftChild();
        FlutteroidBSTNode rNode = localRoot.getRightChild();

        if (lNode == null && rNode == null) {
            localRoot.setFlutteroidData(flutteroid);
            return localRoot;
        }
        if (lNode != null && rNode == null) {
            FlutteroidBSTNode node = new FlutteroidBSTNode();
            node.setFlutteroidData(flutteroid);
            localRoot.setRightChild (node);
            return node;
        }
        if (lNode == null && rNode != null) {
            FlutteroidBSTNode node = new FlutteroidBSTNode();
            node.setFlutteroidData(flutteroid);
            localRoot.setLeftChild (node);
            return node;
        }

        String leftName = lNode.getFlutteroidData().getNameID();
        String rightName = rNode.getFlutteroidData().getNameID();
        String flutName = flutteroid.getNameID();

        int compareLeft = Math.abs(flutName.compareTo(leftName));
        int compareRight = Math.abs(flutName.compareTo(rightName));

        if (compareLeft < compareRight) {
            return addFlutteroid (lNode, flutteroid);
        } else {
            return addFlutteroid(rNode, flutteroid);
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

        FlutteroidBSTNode lNode = localRoot.getLeftChild();
        FlutteroidBSTNode rNode = localRoot.getRightChild();

        Flutteroid leftFlut = findFlutteroid(lNode, flutteroidName);
        Flutteroid rightFlut = findFlutteroid(rNode, flutteroidName);

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

        FlutteroidBSTNode lNode = null;
        FlutteroidBSTNode rNode = null;
        if (compare < 0) {
            lNode = localRoot.getLeftChild();
            lNode = removeFlutteroid(lNode, flutteroidName);
            localRoot.setLeftChild(lNode);
            return localRoot;
        } else if (compare > 0) {
            rNode = localRoot.getRightChild();
            rNode = removeFlutteroid(rNode, flutteroidName);
            localRoot.setLeftChild(rNode);
            return localRoot;
        } else {
            if (lNode == null) {
                return rNode;
            }
            if (rNode == null) {
                return lNode;
            }

            if (localRoot.getRightChild() == null) {
                localRoot = localRoot.getLeftChild();
                lNode = lNode.getLeftChild();
            } else {
                localRoot.setFlutteroidData(findLargestChild(lNode));;
                return localRoot;
            }
        }
    }

    private Flutteroid findLargestChild(FlutteroidBSTNode localRoot) {
        FlutteroidBSTNode lNode = localRoot.getLeftChild();
        FlutteroidBSTNode rNode = localRoot.getRightChild();

        if (lNode == null && rNode == null) {return localRoot.getFlutteroidData();}
        else if (lNode == null && rNode != null) {return findLargestChild(rNode);}
        else if (lNode != null && rNode == null) {return findLargestChild(lNode);}
        else {
            Flutteroid leftLargeFlut = findLargestChild(lNode);
            Flutteroid rightLargeFlut = findLargestChild(rNode);

            if (leftLargeFlut.getNameID().compareTo (rightLargeFlut.getNameID()) > 0) {
                return leftLargeFlut;
            }
            return rightLargeFlut;
        }
    }

    public void displayFlutteroidFarmAscending() {
        displayFlutteroidFarmAscending(bst);
    }

    private void displayFlutteroidFarmAscending(FlutteroidBSTNode localRoot) {
        FlutteroidBSTNode lNode = localRoot.getLeftChild();
        displayFlutteroidFarmAscending(lNode);

        Flutteroid flut = localRoot.getFlutteroidData();
        if (flut != null) {
            System.out.println(flut.getNameID());
        } else {
            return;
        }

        FlutteroidBSTNode rNode = localRoot.getRightChild();
        displayFlutteroidFarmAscending(rNode);
    }

    public void displayFlutteroidFarmDescending() {
        displayFlutteroidFarmDescending(bst);
    }

    private void displayFlutteroidFarmDescending(FlutteroidBSTNode localRoot) {
        FlutteroidBSTNode lNode = localRoot.getRightChild();
        displayFlutteroidFarmAscending(lNode);

        Flutteroid flut = localRoot.getFlutteroidData();
        if (flut != null) {
            System.out.println(flut.getNameID());
        } else {
            return;
        }

        FlutteroidBSTNode rNode = localRoot.getLeftChild();
        displayFlutteroidFarmAscending(rNode);
    }

    public void displayFlutteroidFarmStructure() {
        displayFlutteroidFarmStructure(bst, 0, "ROOT ");
    }

    private void displayFlutteroidFarmStructure(FlutteroidBSTNode localRoot, int depth, String nodeLabel) {
        String indent = "   ";
        System.out.print(indent);
        if (depth == 0) {
            System.out.print(nodeLabel);
        }

        for (int i = 0; i < depth; i++) {
            System.out.print(indent);
        }


        Flutteroid localFlut = localRoot.getFlutteroidData();
        if (localFlut != null) {
            System.out.println(localFlut.getNameID());

            FlutteroidBSTNode lNode = localRoot.getLeftChild();
            displayFlutteroidFarmStructure(lNode, depth + 1, "LEFT  ");

            FlutteroidBSTNode rNode = localRoot.getRightChild();
            displayFlutteroidFarmStructure(rNode, depth + 1, "RIGHT ");
        } else {
            System.out.println ("null");
        }
    }

    public void updateFarm() {
        time += 1;
        System.out.println("Updating Farm Status By One Time Unit (Total Time: " + time + "):");
        if (isFarmEmpty() == false) {
            
        }
    }
}
