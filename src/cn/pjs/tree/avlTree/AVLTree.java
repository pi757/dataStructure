package cn.pjs.tree.avlTree;


public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVL avl = new AVL();
        for (int value : arr) {
            avl.add(new Node(value));
        }
        avl.infixOrder();
        System.out.println(avl.leftHeight(avl.getRoot()) + "左");
        System.out.println(avl.rightHeight(avl.getRoot()) + "右");
        System.out.println(avl.height(avl.getRoot()) + "跟");
        System.out.println(avl.getRoot().right);
    }
}

class AVL {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public int leftHeight(Node left) {
        if (left != null && left.left != null) {
            return left.left.height();
        } else {
//            System.out.println("空");
            return 0;
        }
    }

    public int rightHeight(Node right) {
        if (right != null && right.right != null) {
            return right.right.height();
        } else {
//            System.out.println("空");
            return 0;
        }
    }

    public int height(Node node) {
        if (node != null) {
            return node.height();
        } else {
//            System.out.println("空");
            return 0;
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
        //左旋
        while (rightHeight(root) - leftHeight(root) > 1) {
            if (rightHeight(root.right.left) > rightHeight(root.right.right)) {
                rotateRight(root.right);
            } else {
                rotateLeft(root);
            }
        }
        //右旋
        while (leftHeight(root) - rightHeight(root) > 1) {
            if (rightHeight(root.left.right) > rightHeight(root.left.left)) {
                rotateLeft(root.left);
            } else {
                rotateRight(root);
            }
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.infixOrder(root);
        }
    }

    public void del(int value) {
        if (root == null) {
            System.out.println("空树");
        } else if (root.value == value) {
            root = null;
        } else {
            Node parent = root.getParent(value);
            Node targetNode = root.search(value);
            //没有这个元素
            if (targetNode == null) {
                return;
            }
            //叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //左叶子
                if (parent.left != null && parent.left == targetNode) {
                    parent.left = null;
                }
                //右叶子
                if (parent.right != null && parent.right == targetNode) {
                    parent.right = null;
                }
                //两个子树
            } else if (targetNode.right != null && targetNode.left != null) {
                int min = delRightTreeMin(targetNode);
                targetNode.value = min;
                //一个子树
            } else {
                //删除得元素是父元素左子数
                if (parent.left != null && parent.left.value == value) {
                    //删除的元素有左子数
                    if (targetNode.left != null) {
                        parent.left = targetNode.left;
                        //删除的元素有右子数
                    } else {
                        parent.left = targetNode.right;
                    }
                    //删除得元素是父元素右子数
                } else {
                    if (targetNode.left != null) {
                        parent.right = targetNode.left;
                    } else {
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }

    public int delRightTreeMin(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        del(temp.value);
        return temp.value;
    }

    public int delLeftTreeMax(Node node) {
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        del(temp.value);
        return temp.value;
    }

    public void rotateLeft(Node node) {
        Node newNode = new Node(node.value);
        newNode.left = node.left;
        newNode.right = node.right.left;
        node.value = node.right.value;
        node.right = node.right.right;
        node.left = newNode;
    }

    public void rotateRight(Node node) {
        Node newNode = new Node(node.value);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.value = node.left.value;
        node.left = node.left.left;
        node.right = newNode;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    //找到父
    public Node getParent(int value) {
        //找到了 等于他的左或者右
        if ((this.left != null && value == this.left.value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //左不为空 就向左递归 并返回
            if (value < this.value && this.left != null) {
                return this.left.getParent(value);
                //右不为空
            } else if (value >= this.value && this.right != null) {
                return this.right.getParent(value);
                //没找到
            } else {
                return null;

            }
        }
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.search(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.search(value);
            } else {

                return null;
            }

        }
    }

    public Node(int value) {
        this.value = value;
    }

    //添加
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(Node node) {
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            infixOrder(node.right);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "Value=" + value +
                '}';
    }
}