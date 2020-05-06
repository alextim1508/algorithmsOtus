package com.alextim;

public abstract class BalanceTree extends Tree {

    public BalanceTree(Node root) {
        super(root);
    }

    Node rotateRight(Node root) {
        Node node = root.left;
        root.left = node.right;
        node.right = root;
        return node;
    }

    Node rotateLeft(Node root) {
        Node node = root.right;
        root.right = node.left;
        node.left = root;
        return node;
    }

    Node bigRotateLeft(Node root) {
        root.right = rotateRight(root.right);
        return rotateLeft(root);
    }

    Node bigRotateRight(Node root) {
        root.left = rotateLeft(root.left);
        return rotateRight(root);
    }
}
