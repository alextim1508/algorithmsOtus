package com.alextim;

import java.util.Random;
import java.util.function.Function;

public class RandomizedTree  extends BalanceTree {

    public RandomizedTree(Node root) {
        super(root);
    }

    @Override
    Node rotateRight(Node root) {
        Node node = super.rotateRight(root);
        ((RandomizedNode)node).size = ((RandomizedNode)root).size;
        fixSize((RandomizedNode) root);
        return node;
    }

    @Override
    Node rotateLeft(Node root) {
        Node node = super.rotateLeft(root);
        ((RandomizedNode)node).size = ((RandomizedNode)root).size;
        fixSize((RandomizedNode)root);
        return node;
    }

    private int getSize(RandomizedNode node) {
        return node == null ? 0 : node.size;
    }

    private void fixSize(RandomizedNode node) {
        node.size = getSize((RandomizedNode)node.left) + getSize((RandomizedNode) node.right) + 1;
    }

    RandomizedNode insertRoot(RandomizedNode newNode, RandomizedNode root) {
       if(root ==null)
            return newNode;

       if(newNode.key == root.key) {
           root.value = newNode.value;
           return root;
       }

       if(newNode.key<root.key) {
           root.left = insertRoot(newNode, (RandomizedNode)root.left);
           return (RandomizedNode) rotateRight(root);
       }
       else {
           root.right = insertRoot(newNode, (RandomizedNode)root.right);
           return (RandomizedNode) rotateLeft(root);
       }
    }

    @Override
    Node insert(Node newNode, Node node, Function<Node, Node> handler) {
        if(node == null)
            return newNode;

        if(lottery((RandomizedNode)node))
            return insertRoot((RandomizedNode)newNode, (RandomizedNode)node);

        Node root = super.insert(newNode, node, handler);
        fixSize((RandomizedNode)root);
        return root;
    }

    @Override
    Node remove(int key, Node node, Function<Node, Node> handler) {
        if(node == null)
            return null;

        if(key < node.key )
            node.left = remove(key, node.left,  handler);
        else if(key > node.key)
            node.right = remove(key, node.right, handler);
        else {
            return join(node.left, node.right,
                   currentNode -> fixSize((RandomizedNode)currentNode),
                   (node1, node2) -> lottery((RandomizedNode)node1, (RandomizedNode)node2));
        }
        return node;
    }

    boolean lottery(RandomizedNode p, RandomizedNode q) {
        return new Random().nextInt() % (p.size + q.size) < p.size;
    }

    boolean lottery(RandomizedNode node) {
        return new Random().nextInt() % (node.size + 1) == 0;
    }

    public static class RandomizedNode extends Node {
        int size = 1;

        public RandomizedNode(int key, Object value) {
            super(key, value);
        }

        @Override
        public String toString() {
            return "RandomNode{ size=" + size + ", key=" + key + ", value=" + value + '}';
        }
    }
}
