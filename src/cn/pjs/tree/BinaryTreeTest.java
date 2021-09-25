package cn.pjs.tree;


public class BinaryTreeTest {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "卢俊义");
        HeroNode hero3 = new HeroNode(3, "吴用");
        HeroNode hero4 = new HeroNode(4, "公孙胜");
        HeroNode hero5 = new HeroNode(5, "大刀关胜");
        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);
        BinaryTree binaryTree = new BinaryTree(hero1);
        //遍历
//        System.out.println("前序");
//        binaryTree.preOrder();
//        System.out.println("中序");
//        binaryTree.inOrder();
//        System.out.println("后序");
//        binaryTree.sufOrder();
        //查找
        HeroNode result;
//         result= binaryTree.preFind( 5);
//        result= binaryTree.inFind( 5);
//        result= binaryTree.sufFind( 5);
//        System.out.println(result);
        //删除
        binaryTree.del(1);
        binaryTree.preOrder();


    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

class BinaryTree {
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    /**
     * 重载
     */
    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void sufOrder() {
        sufOrder(root);
    }

    public HeroNode preFind(int no) {
        return preFind(root, no);
    }

    public HeroNode inFind(int no) {
        return inFind(root, no);
    }

    public HeroNode sufFind(int no) {
        return sufFind(root, no);
    }

    public void del(int no) {
        del(root, no);
    }

    /**
     * 前序
     *
     * @param node:根节点
     */
    public void preOrder(HeroNode node) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        System.out.println(node);
        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    public void inOrder(HeroNode node) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        if (node.getLeft() != null) {
            inOrder(node.getLeft());
        }
        System.out.println(node);
        if (node.getRight() != null) {
            inOrder(node.getRight());
        }
    }

    public void sufOrder(HeroNode node) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        if (node.getLeft() != null) {
            sufOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            sufOrder(node.getRight());
        }
        System.out.println(node);
    }

    /**
     * 前序查找
     *
     * @param node:根接节点
     * @param no：查找的编号
     */
    public HeroNode preFind(HeroNode node, int no) {
        System.out.println("pre");
        if (node.getNo() == no) {
            return node;
        }
        HeroNode result = null;
        if (node.getLeft() != null) {
            result = preFind(node.getLeft(), no);
        }
        if (result != null) {
            return result;
        }
        if (node.getRight() != null) {
            result = preFind(node.getRight(), no);
        }
        return result;
    }

    public HeroNode inFind(HeroNode node, int no) {

        HeroNode result = null;
        if (node.getLeft() != null) {
            result = inFind(node.getLeft(), no);
        }
        if (node.getNo() == no) {
            return node;
        }
        System.out.println("in");
        if (result != null) {
            return result;
        }
        if (node.getRight() != null) {
            result = inFind(node.getRight(), no);
        }
        return result;
    }

    public HeroNode sufFind(HeroNode node, int no) {

        HeroNode result = null;
        if (node.getLeft() != null) {
            result = sufFind(node.getLeft(), no);
        }
        if (result != null) {
            return result;
        }
        if (node.getRight() != null) {
            result = sufFind(node.getRight(), no);
        }
        if (result != null) {
            return result;
        }
        System.out.println("suf");//前面有进入看是否为空但是没比较
        if (node.getNo() == no) {
            return node;
        }
        return result;
    }

    /**
     * 删除
     */
    public void del(HeroNode node, int no) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        if (root.getNo() == no) {
            root = null;
            return;
        }
        if (node.getLeft() != null) {
            if (node.getLeft().getNo() == no) {
                node.setLeft(null);
                return;
            } else {
                del(node.getLeft(), no);
            }

        }
        if (node.getRight() != null) {

            if (node.getRight().getNo() == no) {
                node.setRight(null);
                return;
            } else {
                del(node.getRight(), no);
            }
        }

    }
}
