package com.alextim;

import java.util.Random;

public class SplayTree extends BalanceTree {

    public SplayTree(Node root) {
        super(root);
    }

    Node splay(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key > key) {
            if (root.left == null)
                return root;

            if (root.left.key > key) {
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            } else if (root.left.key < key) {
                root.left.right = splay(root.left.right, key);

                if (root.left.right !=  null)
                    root.left = rotateLeft(root.left);
            }
            return (root.left == null) ? root: rotateRight(root);
        }
        else {
            if (root.right == null)
                return root;

            if (root.right.key > key) {
                root.right.left = splay(root.right.left, key);

                if (root.right.left != null)
                    root.right = rotateRight(root.right);
            } else if (root.right.key < key) {
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            }
            return (root.right == null)? root: rotateLeft(root);
        }
    }

    @Override
    Node search(int key, Node node) {
        return this.root = splay(node, key);
    }

    @Override
    public void insert(Node newNode) {
        root = insert(newNode, root);
    }

    Node insert(Node newNode, Node node) {
        node = splay(node, newNode.key);

        if (node.key == newNode.key)
            return node;

        if (node.key > newNode.key) {
            newNode.right = node;
            newNode.left = node.left;
            node.left = null;
        } else {
            newNode.left = node;
            newNode.right = node.right;
            node.right = null;
        }
        return newNode;
    }

    @Override
    public void remove(int key) {
        Node removed = search(key, root);
        this.root = join(removed.left, removed.right, node -> { }, (node1, node2) -> new Random().nextInt(2) == 1);
    }
}
