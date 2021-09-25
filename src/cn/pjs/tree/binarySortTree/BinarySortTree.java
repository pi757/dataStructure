package cn.pjs.tree.binarySortTree;

public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0, 10};
        BinarySort binarySort = new BinarySort();
        for (int value : arr) {
            binarySort.add(new Node(value));
        }
        binarySort.infixOrder();
        binarySort.del(10);
        System.out.println("==========");
        binarySort.infixOrder();
    }

}

class BinarySort {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
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
}

class Node {
    public int value;
    public Node left;
    public Node right;

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
