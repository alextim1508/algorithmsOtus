package com.alextim;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Tree {

    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public void insert(Node newNode) {
        root = insert(newNode, root, node-> node);
    }

    Node insert(Node newNode, Node node, Function<Node, Node> handler) {
        if(node == null) {
            return newNode;
        }

        if(node.key == newNode.key) {
            node.value = newNode.value;
            return node;
        }

        if(newNode.key < node.key) {
            node.left = insert(newNode, node.left, handler);
        } else {
            node.right = insert(newNode, node.right, handler);
        }
        return handler.apply(node);
    }

    public void remove(int key) {
        root = remove(key, root, node -> node);
    }

    public Node findMin(Node node) {
        return node.left != null ? findMin(node.left) : node;
    }

    Node removeMin(Node node, Function<Node, Node> handler) {
        if(node.left == null )
            return node.right;

        node.left = removeMin(node.left, handler);
        return handler.apply(node);
    }

    Node remove(int key, Node node, Function<Node, Node> handler) {
        if(node == null)
            return null;

        if(key < node.key)
            node.left = remove(key, node.left, handler);
        else if(key > node.key)
            node.right = remove(key, node.right, handler);
        else {
            Node left =  node.left;
            Node right = node.right;

            if(right == null)
                return left;

            Node min = findMin(right);
            min.right = removeMin(right, handler);
            min.left = left;
            return handler.apply(min);
        }
        return handler.apply(node);
    }

    public Node search(int key) {
        return search(key, root);
    }

    Node search(int key, Node node) {
        if(node == null)
            return null;

        if(node.key == key)
            return node;

        if(key > node.key)
            return search(key, node.right);
        else
            return search(key, node.left);
    }

    Node join(Node p, Node q, Consumer<Node> joinedNodeHandler, BiFunction<Node, Node, Boolean> lottery) {
        if(p==null)
            return q;
        if(q==null)
            return p;

        if(lottery.apply(p, q)) {
            p.right = join(p.right, q, joinedNodeHandler, lottery);
            joinedNodeHandler.accept(p);
            return p;
        } else {
            q.left = join(p, q.left, joinedNodeHandler, lottery);
            joinedNodeHandler.accept(q);
            return q;
        }
    }

    public void preOrder(Consumer<Node> consumer) {
        preOrder(root, consumer);
    }

    void preOrder(Node node, Consumer<Node> consumer) {
        if(node.left != null)
            preOrder(node.left, consumer);

        if(node.right != null)
            preOrder(node.right, consumer);

        consumer.accept(node);
    }

    public void iterativePreOrder(Consumer<Node> consumer) {
        iterativePreOrder(root, consumer);
    }

    void iterativePreOrder(Node node, Consumer<Node> consumer) {
        Stack<Node> stack = new Stack<> ();
        stack.push(node);

        while(!stack.empty()) {
            node=stack.pop();
            consumer.accept(node);

            if (node.right!=null)
                stack.push(node.right);
            if (node.left!=null)
                stack.push(node.left);
        }
    }

    public void levelOrder(Consumer<Node> consumer) {
        levelOrder(root, consumer);
    }

    void levelOrder(Node node, Consumer<Node> consumer) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.poll();
            consumer.accept(node);

            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    public static class Node {
        int key;
        Object value;
        Node left, right;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{ key=" + key + ", value=" + value + '}';
        }
    }
}
