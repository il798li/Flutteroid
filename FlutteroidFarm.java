import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;

public class FlutteroidFarm {

    private FlutteroidBSTNode bst; // entire farm
    private int time; //time passed since starting

    private ArrayList<Flutteroid> escapedFlutteroids;
    private ArrayList<Integer> escapedTimes;
    private ArrayList<FlutteroidBSTNode> queue;

    public static final String indent = "   ";

    public FlutteroidFarm () {
        this.bst = new FlutteroidBSTNode();
        this.time = 0;

        this.escapedFlutteroids = new ArrayList<Flutteroid>();
        this.escapedTimes = new ArrayList<Integer>();

        this.queue = new ArrayList<FlutteroidBSTNode>();
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
        return null;
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
            return rightLargeFlut; // il798li and codeCook
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
        this.time += 1;
        for (int index = 0; index < escapedTimes.size(); index++) {
            int time = escapedTimes.get(index);
            time -= 1;
            escapedTimes.set(index, time);
        }
        System.out.println("Updating Farm Status By One Time Unit (Total Time: " + time + "):");
        if (isFarmEmpty() == false) {
            moveFlutteroids();
            updateEscapees();
        }
    }

    public void moveFlutteroids() {
        queue.add(bst);
        for (int index = 0; index < queue.size(); index++) {
            FlutteroidBSTNode node = queue.get(index);
            Flutteroid flut = node.getFlutteroidData();
            if (flut != null) {
                flut.move();
            }
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

    public void updateEscapees() {
        updateEscapees(bst);
    }

    private void updateEscapees(FlutteroidBSTNode localRoot) {
        Flutteroid flut = localRoot.getFlutteroidData();
        Point2D point = flut.getLocation();

        boolean x = -100 <= point.getX() && point.getX() <= 100;
        boolean y = -100 <= point.getY() && point.getY() <= 100;

        int captureTime = (int) (Math.random() * 10 + 1);

        if (x == false || y == false) {
            System.out.println ("Flutteroid " + flut.getNameID() + " has escaped to " + point.toString() + " and must be captured within " + captureTime + " seconds!");
            flut.increaseEscapes();

            // saving the escaped flutteroid and time, to be used later by accessing both arrays at the same index
            escapedFlutteroids.add(flut);
            escapedTimes.add(captureTime);
            removeFlutteroid(flut.getNameID());
        }
    }

    public void attemptCapture() {
        for (int index = 0; index < escapedFlutteroids.size(); index++) {
            Flutteroid flut = escapedFlutteroids.get(index);
            int captureTime = escapedTimes.get(index);
            if (captureTime <= 0) {
                flut.setXLocation(0);
                flut.setYLocation(0);
            }
            escapedFlutteroids.remove(index);
            escapedTimes.remove(index);
        }
    }

    public void displayEscapedFlutteroids() {
        for (int index = 0; index <= escapedFlutteroids.size(); index++) {
            Flutteroid flut = escapedFlutteroids.get(index);
            if (flut != null) {
                System.out.print("{{");
                System.out.print(flut.getNameID());
                System.out.print(" : ");
                System.out.print(flut.getLocation().toString());
                System.out.print("} Capture Time: ");
                System.out.print(escapedTimes.get(index));
                System.out.println("}");
            }
        }
    }
}
