package model.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.HTMLExportable;

/**
 * Represents the Vertex through a key, an generic element which refers to what
 * is contained by the Vertex and a map of outgoing vertices.
 *
 * @param <V> Generic element which refers to what is contained by the Vertex.
 * @param <E> Generic element which refers to what is considered as weight.
 */
public class Vertex<V extends HTMLExportable, E extends HTMLExportable>
        implements HTMLExportable {

    /**
     * Vertex ID.
     */
    private int key;

    /**
     * Vertex generic element.
     */
    private V element;

    /**
     * Map to the outgoing edges.
     */
    private List<Edge<V, E>> outEdges;

    /**
     * Creates an instance of vertex.
     */
    public Vertex() {
    }

    /**
     * Creates an instance of vertex, receiving a key and a generic element
     * which refers to what is contained by the Vertex.
     *
     * @param k Key of the vertex.
     * @param vInf Generic element which refers to what is contained by the
     * Vertex.
     */
    public Vertex(int k, V vInf) {
        this.key = k;
        this.element = vInf;
        this.outEdges = new ArrayList<>();
    }

    /**
     * Returns the key of the Vertex.
     *
     * @return Key of the Vertex.
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Returns the generic element which refers what the Vertex contains.
     *
     * @return Generic element which refers to what the Vertex contains.
     */
    public V getElement() {
        return this.element;
    }

    /**
     * Returns a list of the outgoing edges of the Vertex.
     * 
     * @return List of the outgoing edges of the Vertex.
     */
    public List<Edge<V, E>> getOutEdges() {
        return this.outEdges;
    }

    /**
     * Sets the key of the Vertex.
     *
     * @param k Key of the Vertex.
     */
    public void setKey(int k) {
        this.key = k;
    }

    /**
     * Sets the generic element which refers to what the vertex contains
     *
     * @param vInf Generic element which refers to what the vertex contains
     */
    public void setElement(V vInf) {
        this.element = vInf;
    }

    /**
     * Adds an edge to the vertex as an outgoing edge.
     * 
     * @param outgoingEdge Outgoing edge to be added.
     * @return True if the edge is added and false if not.
     */
    public boolean addOutgoingEdge(Edge<V, E> outgoingEdge) {
        return this.outEdges.add(outgoingEdge);
    }

    /**
     * Removes an outgoing edge of the vertex.
     * 
     * @param outgingEdge Outgoing edge to be removed.
     * @return True if the edge is removed and false if not.
     */
    public boolean removeOutgoingEdge(Edge<V, E> outgingEdge) {
        return this.outEdges.remove(outgingEdge);
    }

    /**
     * Returns the outgoing vertices.
     *
     * @return Map of the outgoing vertices.
     */
    public Map<Vertex<V, E>, Edge<V, E>> getOutgoing() {
        Map<Vertex<V, E>, Edge<V, E>> outGoing = new LinkedHashMap<>();

        for (Edge<V, E> edge : this.outEdges) {
            outGoing.put(edge.getVDest(), edge);
        }

        return outGoing;
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
        if (anotherObject == null || this.getClass() != anotherObject.getClass()) {
            return false;
        }
        Vertex<V, E> otherVertex = (Vertex<V, E>) anotherObject;

        return (this.key == otherVertex.key
                && this.element != null && otherVertex.element != null
                && this.element.equals(otherVertex.element));
    }

    /**
     * Alters the hash code of an instanced object, speeding up its storage or
     * retrieval.
     *
     * @return Hash code of the instanced object.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.key;
        hash = 89 * hash + Objects.hashCode(this.element);
        return hash;
    }

    /**
     * Clones a vertex and its respective edges.
     *
     * @return The cloned Vertex.
     */
    @Override
    public Vertex<V, E> clone() {

        Vertex<V, E> newVertex = new Vertex<>();

        newVertex.key = key;
        newVertex.element = element;

        List<Edge<V, E>> newListOutverts = new ArrayList<>();
        Iterator<Edge<V, E>> itMap = this.outEdges.iterator();
        
        while (itMap.hasNext()) {
            Edge<V, E> entry = itMap.next();
            newListOutverts.add(new Edge(entry.getElement(), entry.getWeight(),
                    entry.getVOrig(), entry.getVDest()));
        }
        
        newVertex.outEdges = newListOutverts;

        return newVertex;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * Vertex element info (key):
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        return this.element + " (" + this.key + "): ";
    }

    /**
     * Returns the textual description of the object in html format:
     *
     * Vertex element info (key):
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.element);

        return sb.toString();
    }

}
