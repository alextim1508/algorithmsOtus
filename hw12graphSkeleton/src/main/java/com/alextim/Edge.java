package com.alextim;

import java.util.Objects;

public class Edge {
    public int vertex1;
    public int vertex2;
    public int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public Edge(Edge edge) {
        copyBy(edge);
    }

    public void copyBy(Edge edge) {
        this.vertex1 = edge.vertex1;
        this.vertex2 = edge.vertex2;
        this.weight = edge.weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                " vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return ((vertex1 == edge.vertex1 && vertex2 == edge.vertex2) ||
                (vertex1 == edge.vertex2 && vertex2 == edge.vertex1))
                && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1, vertex2, weight);
    }
}
