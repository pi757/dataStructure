package cn.pjs.tree;

public class ClueBinaryTreeTest {
    public static void main(String[] args) {
        ClueNode node1 = new ClueNode(1, "宋江");
        ClueNode node2 = new ClueNode(3, "卢俊义");
        ClueNode node3 = new ClueNode(6, "吴用");
        ClueNode node4 = new ClueNode(8, "公孙胜");
        ClueNode node5 = new ClueNode(10, "关胜");
        ClueNode node6 = new ClueNode(14, "林冲");
        ClueNode node7 = new ClueNode(7, "秦明");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ClueTree clueTree = new ClueTree(node1);

        //中序：8 3 10 1 14 6
//        clueTree.clueInfixOrder();
//        clueTree.infixOrder();

        //前序：1 3 8 10 6 14
//        clueTree.cluePrefixOrder();
//        clueTree.preOrder();
        //后序：8 10 3 14 6 1
        clueTree.clueSufOrder();
        clueTree.sufOrder();

//        System.out.println(node5.getLeft()+"=="+node5.getLeftType());
//        System.out.println(node5.getRight()+"=="+node5.getRightType());
//        System.out.println(node6.parents);
    }
}

class ClueNode {
    private int no;
    private String name;
    private ClueNode left;
    private ClueNode right;
    private int leftType;
    private int rightType;
    public ClueNode parents;

    @Override
    public String toString() {
        return "ClueNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public ClueNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClueNode getLeft() {
        return left;
    }

    public void setLeft(ClueNode left) {
        this.left = left;
    }

    public ClueNode getRight() {
        return right;
    }

    public void setRight(ClueNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

class ClueTree {
    private final ClueNode root;
    private ClueNode pre;

    public ClueTree(ClueNode root) {
        this.root = root;
    }

    /**
     * 中序
     */
    public void clueInfixOrder() {
        clueInfixOrder(root);
    }

    public void clueInfixOrder(ClueNode node) {
        if (node == null) {
            return;
        }
        //递归处理左子树
        if (node.getLeft() != null) {
            clueInfixOrder(node.getLeft());
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //递归处理右子树
        if (node.getRight() != null) {
            clueInfixOrder(node.getRight());
        }


    }

    public void infixOrder() {
        ClueNode node = root;
        while (node != null) {
            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 前序
     */
    public void cluePrefixOrder() {
        cluePrefixOrder(root);
    }

    public void cluePrefixOrder(ClueNode node) {
        if (node == null) {
            return;
        }
        //让左指前
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //让前一个的右指自己
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType() == 0 && node.getLeft() != null) {

            cluePrefixOrder(node.getLeft());
        }
        if (node.getRightType() == 0 && pre.getRight() == null) {
            cluePrefixOrder(node.getRight());
        }


    }

    public void preOrder() {
        ClueNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    /**
     * 后序
     */
    public void clueSufOrder() {
        clueSufOrder(root);
    }

    public void clueSufOrder(ClueNode node) {
        if (node == null) {
            return;
        }

        //处理左子树
        if (node.getLeftType() == 0 && node.getLeft() != null) {

            clueSufOrder(node.getLeft());
        }
        //处理右子树

        if (node.getRightType() == 0 && node.getRight() != null) {
            clueSufOrder(node.getRight());
        }
        //让左指前
        if (node.getLeft() == null) {


            node.setLeft(pre);
            node.setLeftType(1);
        }


        //让前一个的右指自己
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //创建父母指针
        if (node.getLeft() != null) {
            node.getLeft().parents = node;

        }
        if (node.getRight() != null) {

            node.getRight().parents = node;
        }
        pre = node;
    }

    public void sufOrder() {
        if (root == null) {
            System.out.println("空");
            return;
        }
        ClueNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            if (node.parents == null) {
                return;
            }
            node = node.parents.getRight();
        }
    }
}
