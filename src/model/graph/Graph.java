package model.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import model.HTMLExportable;

/**
 * Represents the Graph through a number of vertices, a number of edges, a
 * specification about if it is oriented or and a list of vertices.
 *
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public class Graph<V extends HTMLExportable, E extends HTMLExportable>
        implements GraphInterface<V, E>, HTMLExportable {

    /**
     * Number of the vertices of the graph.
     */
    private int numVert;

    /**
     * Number of the edges of the graph.
     */
    private int numEdge;

    /**
     * Specifies if the graph is oriented.
     */
    private final boolean isDirected;

    /**
     * List of the vertices of the graph.
     */
    private ArrayList<Vertex<V, E>> listVert;

    /**
     * Constructs an empty graph recieving a boolean stating if the same is
     * oriented or not.
     *
     * @param directed Boolean value which states if the graph is oriented or
     * not.
     */
    public Graph(boolean directed) {
        this.numVert = 0;
        this.numEdge = 0;
        this.isDirected = directed;
        this.listVert = new ArrayList<>();
    }

    /**
     * Returns the number of vertices of the graph.
     *
     * @return Number of vertices of the graph.
     */
    @Override
    public int getNumVertices() {
        return this.numVert;
    }

    /**
     * Returns all the vertices of the graph as an iterable collection.
     *
     * @return Vertices of graph as an iterable collection.
     */
    @Override
    public Iterable<Vertex<V, E>> getVertices() {
        return this.listVert;
    }

    /**
     * Returns the number of edges of the graph.
     *
     * @return Number of edges of the graph.
     */
    @Override
    public int getNumEdges() {
        return this.numEdge;
    }

    /**
     * Returns all the edges of the graph as an iterable collection.
     *
     * @return Edges of the graph as an iterable collection.
     */
    @Override
    public Iterable<Edge<V, E>> getEdges() {
        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        for (Vertex<V, E> vertex : this.listVert) {
            edges.addAll(vertex.getOutgoing().values());
        }

        return edges;
    }

    /**
     * Returns the edge from vOrig to vDest, or null if the vertices are not
     * adjacent.
     *
     * @param vOrig Vertex of origin.
     * @param vDest Vertex of destination.
     * @return Edge from vOrig to vDest or null if vertices are not adjacent or
     * don't exist.
     */
    @Override
    public Edge<V, E> getEdge(Vertex<V, E> vOrig, Vertex<V, E> vDest) {
        if (this.listVert.contains(vOrig) && this.listVert.contains(vDest)) {
            return vOrig.getOutgoing().get(vDest);
        }

        return null;
    }

    /**
     * Returns the vertices of the edge as an array of two vertices. If the
     * graph is directed, the first vertex is the origin, and the second is the
     * destination. If the graph is undirected, the order is arbitrary.
     *
     * @param edge Edge.
     * @return Array of two vertices or null if the edge doesn't exist
     */
    @Override
    public Vertex<V, E>[] endVertices(Edge<V, E> edge) {
        for (Vertex<V, E> vertex : this.listVert) {
            Map<Vertex<V, E>, Edge<V, E>> outVerts = vertex.getOutgoing();

            for (Edge<V, E> e : outVerts.values()) {
                if (edge.equals(e)) {
                    return e.getEndpoints();
                }
            }
        }

        return null;
    }

    /**
     * Returns the vertex that is the opposite of the other vertex on the edge.
     *
     * @param vertex Vertex.
     * @param edge Edge.
     * @return Opposite vertex, or null if the vertex or the edge don't exist.
     */
    @Override
    public Vertex<V, E> opposite(Vertex<V, E> vertex, Edge<V, E> edge) {
        for (Vertex<V, E> v : this.listVert) {
            if (v.equals(vertex)) {
                Map<Vertex<V, E>, Edge<V, E>> outVerts = vertex.getOutgoing();

                for (Edge<V, E> e : outVerts.values()) {
                    if (edge.equals(e)) {
                        return e.getVDest();
                    }
                }

                return null;
            }
        }

        return null;
    }

    /**
     * Returns the number of edges leaving the vertex. For an undirected graph,
     * this is the same result returned by inDegree.
     *
     * @param vertex Vertex.
     * @return Number of edges leaving the vertex, -1 if the vertex doesn't
     * exist.
     */
    @Override
    public int outDegree(Vertex<V, E> vertex) {
        if (this.listVert.contains(vertex)) {
            return vertex.getOutgoing().size();
        } else {
            return -1;
        }
    }

    /**
     * Returns the number of edges for which the vertex is the destination. For
     * an undirected graph, this is the same result returned by outDegree.
     *
     * @param vertex Vertex.
     * @return Number of edges leaving the vertex, -1 if the vertex doesn't
     * exist.
     */
    @Override
    public int inDegree(Vertex<V, E> vertex) {
        int counter = -1;

        if (!this.listVert.contains(vertex)) {
            return counter;
        }

        if (!this.isDirected) {
            counter = this.outDegree(vertex);
        } else {
            counter = 0;

            for (Vertex<V, E> v : this.listVert) {
                if (v.getOutgoing().containsKey(vertex)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    /**
     * Returns an iterable collection of edges for which the vertex is the
     * origin. For an undirected graph, this is the same result returned by
     * incomingEdges.
     *
     * @param vertex Vertex.
     * @return Iterable collection of edges, null if the vertex doesn't exist.
     */
    @Override
    public Iterable<Edge<V, E>> outgoingEdges(Vertex<V, E> vertex) {
        if (!this.listVert.contains(vertex)) {
            return null;
        }

        return vertex.getOutEdges();
    }

    /**
     * Returns an iterable collection of edges for which the vertex is the
     * destination. For an undirected graph this is the same result as returned
     * by incomingEdges.
     *
     * @param vertex Vertex.
     * @return Iterable collection of edges reaching the vertex, null if the
     * vertex doesn't exist.
     */
    @Override
    public Iterable<Edge<V, E>> incomingEdges(Vertex<V, E> vertex) {
        if (this.listVert.contains(vertex)) {
            if (!this.isDirected) {
                return this.outgoingEdges(vertex);
            } else {
                ArrayList<Edge<V, E>> incomingEdges = new ArrayList<>();

                for (Vertex<V, E> v : this.listVert) {
                    if (v.getOutgoing().containsKey(vertex)) {
                        incomingEdges.add(v.getOutgoing().get(vertex));
                    }
                }

                return incomingEdges;
            }
        }

        return null;
    }

    /**
     * Inserts and returns a new vertex with some specific comparable type.
     *
     * @param vInf The vertex content.
     * @return New vertex.
     */
    @Override
    public Vertex<V, E> insertVertex(V vInf) {
        Vertex<V, E> vert = getVertex(vInf);

        if (vert == null) {
            Vertex<V, E> newvert = new Vertex<>(this.numVert, vInf);
            this.listVert.add(newvert);
            this.numVert++;

            return newvert;
        }

        return null;
    }

    /**
     * Adds and returns a new edge between vertices vOrigInf and vDestInf, with
     * some specific comparable type. If getVertices vOrigInf and vDestInf don't
     * exist in the graph they are inserted.
     *
     * @param vOrigInf Information of vertex source.
     * @param vDestInf Information of vertex destination.
     * @param eInf Edge information.
     * @param eWeight Edge weight.
     * @return New edge, or null if an edge already exists between the two
     * verts.
     */
    @Override
    public Edge<V, E> insertEdge(V vOrigInf, V vDestInf, E eInf, double eWeight) {
        Vertex<V, E> vOrig = getVertex(vOrigInf);
        Vertex<V, E> vDest = getVertex(vDestInf);

        if (vOrig != null && vDest != null) {
            Edge<V, E> newEdge = new Edge<>(eInf, eWeight, vOrig, vDest);

            for (Edge<V, E> edge : this.getEdges()) {
                // If an equal edge already exists we don't add it.
                if (edge.equals(newEdge)) {
                    return null;
                }
            }

            vOrig.addOutgoingEdge(newEdge);
            this.numEdge++;

            //if graph is not direct insert other edge in the opposite direction 
            if (!this.isDirected) {
                Edge<V, E> otherEdge = new Edge<>(eInf, eWeight, vDest, vOrig);
                vDest.addOutgoingEdge(otherEdge);
                this.numEdge++;
            }

            return newEdge;
        }

        return null;
    }

    /**
     * Removes a vertex and all its incident edges from the graph.
     *
     * @param vInf Information of vertex source.
     */
    @Override
    public void removeVertex(V vInf) {
        Vertex<V, E> vertex = getVertex(vInf);

        if (vertex != null) {
            for (Edge edge : getEdges()) {
                if (edge.getVDest().equals(vertex) || edge.getVOrig().equals(vertex)) {
                    removeEdge(edge);
                }
            }

            int index = this.listVert.indexOf(vertex);
            this.listVert.remove(vertex);
            this.numVert--;

            for (int i = index; i < this.listVert.size(); i++) {
                this.listVert.get(i).setKey(this.listVert.get(i).getKey() - 1);
            }
        }
    }

    /**
     * Removes the edge.
     *
     * @param edge Edge to remove.
     */
    @Override
    public void removeEdge(Edge<V, E> edge) {

        Vertex<V, E>[] endpoints = endVertices(edge);

        Vertex<V, E> vorig = endpoints[0];
        Vertex<V, E> vdest = endpoints[1];

        if (vorig != null && vdest != null) {
            if (edge.equals(getEdge(vorig, vdest))) {
                vorig.removeOutgoingEdge(edge);
                this.numEdge--;
            }
        }
    }

    /**
     * Returns the vertex with the information of vInf. Returns null if it
     * doesn't exist.
     *
     * @param vInf Information of the vertex.
     * @return Vertex or null if it doesn't exist.
     */
    public Vertex<V, E> getVertex(V vInf) {
        
        for (Vertex<V, E> vert : this.listVert) {
            if (vInf.equals(vert.getElement())) {
                return vert;
            }
        }

        return null;
    }

    /**
     * Returns the vertex with the key. Returns null if it doesn't exists.
     *
     * @param vKey Key of the vertex.
     * @return the vertex or null if it doesn't exists.
     */
    public Vertex<V, E> getVertex(int vKey) {

        if (vKey < this.listVert.size()) {
            return this.listVert.get(vKey);
        }

        return null;
    }

    /**
     * Returns a clone of the graph.
     *
     * @return Clone of the graph.
     */
    @Override
    public Graph<V, E> clone() {

        Graph<V, E> newObject = new Graph<>(this.isDirected);

        newObject.numVert = this.numVert;

        newObject.listVert = new ArrayList<>();
        for (Vertex<V, E> v : this.listVert) {
            newObject.listVert.add(new Vertex(v.getKey(), v.getElement()));
        }

        for (Vertex<V, E> v1 : this.listVert) {
            for (Edge<V, E> e : this.outgoingEdges(v1)) {
                if (e != null) {
                    Vertex<V, E> v2 = this.opposite(v1, e);

                    newObject.insertEdge(v1.getElement(), v2.getElement(),
                            e.getElement(), e.getWeight());
                }
            }
        }

        return newObject;
    }

    /**
     * Compares two objects, first looking at their memory position, following
     * their content and class of origin and in the end compares a set of
     * attributes.
     *
     * @param anotherObject Object used in the comparison.
     * @return True if both objects are equal and false if not.
     */
    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || !(anotherObject instanceof Graph<?, ?>)) {
            return false;
        }

        Graph<?, ?> anotherGraph = (Graph<?, ?>) anotherObject;

        if (this.numVert != anotherGraph.numVert
                || this.numEdge != anotherGraph.numEdge) {
            return false;
        }

        // Graph must have same getVertices
        boolean eqvertex;
        for (Vertex<V, E> v1 : this.getVertices()) {
            eqvertex = false;
            for (Vertex<?, ?> v2 : anotherGraph.getVertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }

        // Graph must have same getEdges
        boolean eqedge;
        for (Edge<V, E> e1 : this.getEdges()) {
            eqedge = false;
            for (Edge<?, ?> e2 : anotherGraph.getEdges()) {
                if (e1.equals(e2)) {
                    eqedge = true;
                }
            }

            if (!eqedge) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * Graph: 8 vertices, 7 edges
     *
     * Vertex info
     *
     * @return Textual description of the graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // If the graph has no vertices it isn't defined.
        if (this.numVert == 0) {
            sb.append("\nRoadmap not defined!");
        } else {
            sb.append("Roadmap: ").append(this.numVert).append(" intersections, ")
                    .append(this.numEdge).append(" roads\n");

            // Foreach vertex
            for (Vertex<V, E> vert : this.listVert) {
                sb.append(vert).append("\n");

                // If it has edges
                if (!vert.getOutgoing().isEmpty()) {
                    for (Map.Entry<Vertex<V, E>, Edge<V, E>> entry
                            : vert.getOutgoing().entrySet()) {
                        sb.append(entry.getValue()).append("\n");
                    }
                } else {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }

    /**
     * Returns the textual description of the object in html format:
     *
     *
     * @return Textual description of the graph.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        // If the graph has no vertices it isn't defined.
        if (this.numVert == 0) {
            sb.append("Roadmap not defined!");
        } else {
            sb.append("Roadmap: ").append(this.numVert).append(" intersections, ")
                    .append(this.numEdge).append(" roads.<br/>\n");

            sb.append("<ul>\n");

            // Foreach vertex
            for (Vertex<V, E> vertex : this.listVert) {
                sb.append("<li>").append(vertex.toStringHTML());

                // If it has edges
                if (!vertex.getOutgoing().isEmpty()) {
                    for (Map.Entry<Vertex<V, E>, Edge<V, E>> entry
                            : vertex.getOutgoing().entrySet()) {
                        sb.append("<ul>").append(entry.getValue().toStringHTML())
                                .append("</ul>");
                    }
                } else {
                    sb.append("<br/>\n");
                }

                sb.append("</li>\n");
            }

            sb.append("</ul>\n");
        }

        return sb.toString();
    }
}
