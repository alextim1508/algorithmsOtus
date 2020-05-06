package com.alextim;

public class AvlTree extends BalanceTree {

    public AvlTree(AvlNode root) {
        super(root);
    }

    @Override
    Node rotateRight(Node root) {
        Node node = super.rotateRight(root);
        fixHeight((AvlNode)root);
        fixHeight((AvlNode)node);
        return node;
    }

    @Override
    Node rotateLeft(Node root) {
        Node node = super.rotateLeft(root);
        fixHeight((AvlNode)root);
        fixHeight((AvlNode)node);
        return node;
    }

    public int height(AvlNode node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(AvlNode node) {
        node.height = Math.max(height((AvlNode) node.left), height((AvlNode) node.right)) + 1;
    }

    private int balanceFactor(AvlNode node) {
        return height((AvlNode) node.left) - height((AvlNode) node.right);
    }

    private AvlNode balance(AvlNode p) {
        fixHeight(p);

        if(balanceFactor(p) == -2) {
            if(balanceFactor((AvlNode) p.right) <= 0)
                return (AvlNode) rotateLeft(p);
            else
                return (AvlNode)bigRotateLeft(p);
        }
        if(balanceFactor(p) == 2) {
            if(balanceFactor((AvlNode) p.left) >= 0)
                return (AvlNode)rotateRight(p);
            else
                return (AvlNode)bigRotateRight(p);
        }
        return p;
    }

    @Override
    public void insert(Node newNode) {
        root = insert(newNode, root, node -> balance((AvlNode)node));
    }

    @Override
    public void remove(int key) {
        root = remove(key, root, node -> balance((AvlNode)node));
    }

    public static class AvlNode extends Node {
        int height;

        public AvlNode(int key, Object value) {
            super(key, value);
        }

        @Override
        public String toString() {
            return "AvlNode{ height=" + height + ", key=" + key + ", value=" + value + '}';
        }
    }
}
