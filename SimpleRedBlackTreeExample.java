package seminar_algorythms;

enum Color {
    RED,
    BLACK
}

class SimpleNode {
    int value;
    Color color;
    SimpleNode leftKid;
    SimpleNode rightKid;

    SimpleNode(int value, Color color) {
        this.value = value;
        this.color = color;
        this.leftKid = null;
        this.rightKid = null;
    }
}

public class SimpleRedBlackTreeExample {
    public SimpleNode root;

    public static void printTree(SimpleNode node, String prefix) {
        if (node != null) {
            System.out.println(prefix + node.value + "(" + node.color + ")");
            printTree(node.leftKid, prefix + "-");
            printTree(node.rightKid, prefix + "-");
        }
    }
    public static SimpleNode add(SimpleNode root, int value) {
        root = addRecursive(root, value);
        root.color = Color.BLACK;
        return root;
    }
    private static SimpleNode addRecursive(SimpleNode node, int value) {
        if (node == null) {
            return new SimpleNode(value, Color.RED); // Вставляем новую красную ноду
        }

        if (value < node.value) {
            node.leftKid = addRecursive(node.leftKid, value);
        } else if (value > node.value) {
            node.rightKid = addRecursive(node.rightKid, value);
        }

        if (isRed(node.rightKid) && !isRed(node.leftKid)) {
            node = leftSwap(node);
        }
        if (isRed(node.leftKid) && isRed(node.leftKid.leftKid)) {
            node = rightSwap(node);
        }
        if (isRed(node.leftKid) && isRed(node.rightKid)) {
            colorSwap(node);
        }

        return node;
    }

    private static boolean isRed(SimpleNode node) {
        return node != null && node.color == Color.RED;
    }

//private boolean add (int value){
//        if (root != null){
//            boolean result = addNode(root, value);
//            root = reBalance(root);
//            root.color = Color.BLACK;
//            return result;
//        }else{
//            root = new SimpleNode();
//            root.color = Color.BLACK;
//            root.value = value;
//            return true;
//        }
//
//    private boolean addNode(SimpleNode node, int value) {
//        if (node.value == value) {
//            return false;
//        } else {
//            if (node.value > value) {
//                if (node.leftKid != null) {
//                    boolean result = addNode(node.leftKid, value);
//                    node.leftKid = reBalance(node.leftKid);
//                    return result;
//                } else {
//                    node.leftKid = new SimpleNode();
//                    node.leftKid.color = Color.RED;
//                    node.leftKid.value = value;
//                    return true;
//                }
//            } else {
//                if (node.rightKid != null) {
//                    boolean result = addNode(node.rightKid, value);
//                    node.rightKid = reBalance(node.rightKid);
//                    return result;
//                } else {
//                    node.rightKid = new SimpleNode();
//                    node.rightKid.color = Color.RED;
//                    node.rightKid.value = value;
//                    return true;
//
//                }
//            }
//        }
//    }
//    private static SimpleNode reBalance(SimpleNode node) {
//        SimpleNode result = node;
//        boolean needRebalance;
//        do {
//            needRebalance = false;
//            if (result.rightKid != null && result.rightKid.color == Color.RED && result.leftKid == null || result.leftKid.color == Color.BLACK) {
//                needRebalance = true;
//                result = rightSwap(result);
//            }
//            if (result.leftKid != null && result.leftKid.color == Color.RED && result.leftKid.leftKid != null || result.leftKid.leftKid.color == Color.RED) {
//                needRebalance = true;
//                result = leftSwap(result);
//            }
//            if (result.leftKid != null && result.leftKid.color == Color.RED && result.rightKid != null && result.rightKid.color == Color.RED) {
//                needRebalance = true;
//                colorSwap(result);
//            }
//        } while (needRebalance);
//        return result;
//    }

    private static SimpleNode leftSwap(SimpleNode parent) {
        SimpleNode rightKid = parent.rightKid;
        parent.rightKid = rightKid.leftKid;
        rightKid.leftKid = parent;
        rightKid.color = parent.color;
        parent.color = Color.RED;
        return rightKid;
    }

    private static SimpleNode rightSwap(SimpleNode parent) {
        SimpleNode leftKid = parent.leftKid;
        parent.leftKid = leftKid.rightKid;
        leftKid.rightKid = parent;
        leftKid.color = parent.color;
        parent.color = Color.RED;
        return leftKid;
    }

    private static void colorSwap(SimpleNode node){
        node.rightKid.color = Color.BLACK;
        node.leftKid.color = Color.BLACK;
        node.color = Color.RED;

    }

    // • Каждая нода имеет цвет (красный или черный)
    // • Корень дерева всегда черный
    // • Новая нода всегда красная
    // • Красные ноды могут быть только левым ребенком
    // • У краной ноды все дети черного цвета

    public static void main(String[] args) {
            SimpleNode root = null;

            root = add(root,10);
            root = add(root, 5);
            root = add(root, 15);
            root = add(root, 3);
            root = add(root, 7);
            root = add(root, 13);
            root = add(root, 17);
            root = add(root, 1);
            root = add(root, 9);
            root = add(root, 12);
            root = add(root, 14);

            printTree(root, "");
        }
    }

