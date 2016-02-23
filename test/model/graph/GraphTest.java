package model.graph;

import java.util.Iterator;
import model.Junction;
import model.Section;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class GraphTest {

    Graph<Junction, Section> instance = new Graph<>(true);

    public GraphTest() {
    }

    /**
     * Test of getNumVertices method, of class Graph.
     */
    @Test
    public void testGetNumVertices() {
        System.out.println("getNumVertices");
        assertTrue("Result should be zero", (instance.getNumVertices() == 0));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("s2");

        Vertex<Junction, Section> vert = instance.insertVertex(j1);
        assertTrue("Result should be one", (instance.getNumVertices() == 1));
        vert = instance.insertVertex(j2);
        assertTrue("Result should be two", (instance.getNumVertices() == 2));

        instance.removeVertex(j1);
        assertTrue("Result should be one", (instance.getNumVertices() == 1));
        instance.removeVertex(j2);
        assertTrue("Result should be zero", (instance.getNumVertices() == 0));
    }

    /**
     * Test of getVertices method, of class Graph.
     */
    @Test
    public void testGetVertices() {
        System.out.println("getVertices");

        Iterator<Vertex<Junction, Section>> itVerts = instance.getVertices().iterator();

        assertTrue("Vertices should be empty", itVerts.hasNext() == false);

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("s2");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);

        itVerts = instance.getVertices().iterator();

        assertTrue("First vertice should be vert1", (itVerts.next().equals(vert1) == true));
        assertTrue("Second vertice should be vert2", (itVerts.next().equals(vert2) == true));

        instance.removeVertex(j1);

        itVerts = instance.getVertices().iterator();
        assertTrue("First vertice should now be vert2", (itVerts.next().equals(vert2)) == true);

        instance.removeVertex(j2);

        itVerts = instance.getVertices().iterator();
        assertTrue("Vertices should now be empty", (itVerts.hasNext() == false));
    }

    /**
     * Test of getNumEdges method, of class Graph.
     */
    @Test
    public void testGetNumEdges() {
        System.out.println("getNumEdges");

        assertTrue("Result should be zero", (instance.getNumEdges() == 0));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        assertTrue("Result should be one", (instance.getNumEdges() == 1));

        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        assertTrue("Result should be two", (instance.getNumEdges() == 2));

        instance.removeEdge(edge1);
        assertTrue("Result should be one", (instance.getNumEdges() == 1));

        instance.removeEdge(edge2);
        assertTrue("Result should be zero", (instance.getNumEdges() == 0));
    }

    /**
     * Test of getEdges method, of class Graph.
     */
    @Test
    public void testGetEdges() {
        System.out.println("getEdges");

        Iterator<Edge<Junction, Section>> itEdge = instance.getEdges().iterator();

        assertTrue("Edges should be empty", (itEdge.hasNext() == false));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        itEdge = instance.getEdges().iterator();

        itEdge.next();
        itEdge.next();
        assertTrue("Third edge should be edge3", (itEdge.next().equals(edge3) == true));
        itEdge.next();
        itEdge.next();
        assertTrue("Sixth edge should be edge6", (itEdge.next().equals(edge6) == true));

        instance.removeEdge(edge1);

        itEdge = instance.getEdges().iterator();
        assertTrue("First edge should now be edge2", (itEdge.next().equals(edge2) == true));

        instance.removeEdge(edge2);
        instance.removeEdge(edge3);
        instance.removeEdge(edge4);
        instance.removeEdge(edge5);
        instance.removeEdge(edge6);
        instance.removeEdge(edge7);
        instance.removeEdge(edge8);
        itEdge = instance.getEdges().iterator();
        assertTrue("Vertices should now be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("getEdge");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        assertTrue("Edge should be null", instance.getEdge(vert2, vert5) == null);

        assertTrue("Edge should be edge3", instance.getEdge(vert2, vert4).equals(edge3) == true);
        assertTrue("Edge should be null", instance.getEdge(vert4, vert2) == null);

        instance.removeEdge(edge6);
        assertTrue("Edge should be null", instance.getEdge(vert4, vert1) == null);

        assertTrue("Edge should be edge8", instance.getEdge(vert5, vert5).equals(edge8) == true);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("endVertices");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Edge<Junction, Section> edge0 = new Edge<>();

        Vertex<Junction, Section>[] vertices = instance.endVertices(edge0);

        assertTrue("The array should be null", vertices == null);

        vertices = instance.endVertices(edge5);

        Junction v1 = vertices[0].getElement();
        Junction v2 = vertices[1].getElement();

        assertTrue("First vertex should be C", v1.equals(j3));
        assertTrue("Second vertex should be E", v2.equals(j5));
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Vertex<Junction, Section> vert = instance.opposite(vert1, edge5);
        assertTrue("Opposite should be null", vert == null);

        vert = instance.opposite(vert1, edge1);
        assertTrue("Opposite should be vert2", vert.equals(vert2) == true);

        vert = instance.opposite(vert5, edge8);
        assertTrue("Opposite should be vert5", vert.equals(vert5) == true);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("outDegree");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Vertex<Junction, Section> vert = new Vertex<>();
        int outdeg = instance.outDegree(vert);
        assertTrue("Degree should be -1", outdeg == -1);

        outdeg = instance.outDegree(vert1);
        assertTrue("Degree should be 2", outdeg == 2);

        outdeg = instance.outDegree(vert2);
        assertTrue("Degree should be 1", outdeg == 1);

        outdeg = instance.outDegree(vert5);
        assertTrue("Degree should be 2", outdeg == 2);
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("inDegree");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Vertex<Junction, Section> vert = new Vertex<>();
        int indeg = instance.inDegree(vert);
        assertTrue("In degree should be -1", indeg == -1);

        indeg = instance.inDegree(vert1);
        assertTrue("In degree should be 1", indeg == 1);

        indeg = instance.inDegree(vert4);
        assertTrue("In degree should be 3", indeg == 3);

        indeg = instance.inDegree(vert5);
        assertTrue("In degree should be 2", indeg == 2);
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println("outgoingEdges");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Iterator<Edge<Junction, Section>> itEdge = instance.outgoingEdges(vert3).iterator();
        Edge<Junction, Section> first = itEdge.next();
        Edge<Junction, Section> second = itEdge.next();
        assertTrue("Outgoing Edges of vert3 should be edge4 and edge5",
                ((first.equals(edge4) == true) && (second.equals(edge5) == true))
                || ((first.equals(edge5) == true) && (second.equals(edge4) == true)));

        itEdge = instance.outgoingEdges(vert5).iterator();
        first = itEdge.next();
        second = itEdge.next();
        assertTrue("Outgoing Edges of vert5 should be edge7 and edge8",
                ((first.equals(edge7) == true || second.equals(edge8) == true)
                || (second.equals(edge7) == true || first.equals(edge8) == true)));

        instance.removeEdge(edge8);

        itEdge = instance.outgoingEdges(vert5).iterator();

        assertTrue("First edge should be edge7", (itEdge.next().equals(edge7) == true));

        instance.removeEdge(edge7);

        itEdge = instance.outgoingEdges(vert5).iterator();
        assertTrue("Edges iterator should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        System.out.println("incomingEdges");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Iterator<Edge<Junction, Section>> itEdge = instance.incomingEdges(vert4).iterator();

        assertTrue("First edge should be edge3", (itEdge.next().equals(edge3) == true));
        assertTrue("Second edge should be edge4", (itEdge.next().equals(edge4) == true));
        assertTrue("Third edge should be edge7", (itEdge.next().equals(edge7) == true));

        itEdge = instance.incomingEdges(vert5).iterator();

        assertTrue("First edge should be edge5", (itEdge.next().equals(edge5) == true));
        assertTrue("Second edge should be edge8", (itEdge.next().equals(edge8) == true));

        instance.removeEdge(edge8);

        itEdge = instance.incomingEdges(vert5).iterator();

        assertTrue("First edge should be edge5", (itEdge.next().equals(edge5) == true));

        instance.removeEdge(edge5);

        itEdge = instance.incomingEdges(vert5).iterator();
        assertTrue("Edges iterator should be empty", (itEdge.hasNext() == false));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("insertVertex");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");

        assertTrue("Number of vertices should be zero", (instance.getNumVertices() == 0));
        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        assertTrue("Number of vertices should be one", (instance.getNumVertices() == 1));
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        assertTrue("Number of vertices should be two", (instance.getNumVertices() == 2));
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        assertTrue("Number of vertices should be three", (instance.getNumVertices() == 3));
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        assertTrue("Number of vertices should be four", (instance.getNumVertices() == 4));
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);
        assertTrue("Number of vertices should be five", (instance.getNumVertices() == 5));

        Iterator<Vertex<Junction, Section>> itVert = instance.getVertices().iterator();

        assertTrue("First vertex should be vert1", (itVert.next().equals(vert1) == true));
        assertTrue("Second vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("Third vertex should be vert3", (itVert.next().equals(vert3) == true));
        assertTrue("Fourth vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("Fifth vertex should be vert5", (itVert.next().equals(vert5) == true));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("insertEdge");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        assertTrue("Number of edges should be zero", (instance.getNumEdges() == 0));

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        assertTrue("Number of edges should be 1", (instance.getNumEdges() == 1));

        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        assertTrue("Number of edges should be 2", (instance.getNumEdges() == 2));

        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        assertTrue("Number of edges should be 3", (instance.getNumEdges() == 3));

        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        assertTrue("Number of edges should be 4", (instance.getNumEdges() == 4));

        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        assertTrue("Number of edges should be 5", (instance.getNumEdges() == 5));

        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        assertTrue("Number of edges should be 6", (instance.getNumEdges() == 6));

        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        assertTrue("Number of edges should be 7", (instance.getNumEdges() == 7));

        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);
        assertTrue("Number of edges should be 8", (instance.getNumEdges() == 8));

        Iterator<Edge<Junction, Section>> itEd = instance.getEdges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("Third edge should be edge3", (itEd.next().equals(edge3) == true));
        itEd.next();
        itEd.next();
        assertTrue("Sixth edge should be edge6", (itEd.next().equals(edge6) == true));
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        instance.removeVertex(j3);
        assertTrue("Num vertices should be 4", (instance.getNumVertices() == 4));
        assertTrue("Num edges should be 0", (instance.getNumEdges() == 0));

        Iterator<Vertex<Junction, Section>> itVert = instance.getVertices().iterator();
        assertTrue("First vertex should be vert1", (itVert.next().equals(vert1) == true));
        assertTrue("Second vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("Third vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("Fourth vertex should be vert5", (itVert.next().equals(vert5) == true));

        itVert = instance.getVertices().iterator();
        assertTrue("First vertex key should be 0", itVert.next().getKey() == 0);
        assertTrue("Second vertex key should be 1", itVert.next().getKey() == 1);
        assertTrue("Third vertex key should be 2", itVert.next().getKey() == 2);
        assertTrue("Fourth vertex key should be 3", itVert.next().getKey() == 3);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 0.0);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j4, s2, 0.0);
        Edge<Junction, Section> edge3 = instance.insertEdge(j4, j2, s3, 0.0);
        Edge<Junction, Section> edge4 = instance.insertEdge(j5, j1, s4, 0.0);

        assertTrue("Num edges should be 4", (instance.getNumEdges() == 4));

        instance.removeVertex(j1);

        assertTrue("Num vertices should be 3", (instance.getNumVertices() == 3));
        assertTrue("Num edges should be 1", (instance.getNumEdges() == 1));
        itVert = instance.getVertices().iterator();

        assertTrue("First vertex should be vert2", (itVert.next().equals(vert2) == true));
        assertTrue("Second vertex should be vert4", (itVert.next().equals(vert4) == true));
        assertTrue("Third vertex should be vert5", (itVert.next().equals(vert5) == true));

        instance.removeVertex(j5);
        assertTrue("Num vertices should be 2", (instance.getNumVertices() == 2));

        itVert = instance.getVertices().iterator();

        assertTrue("First vertex should be vert2 but its key should be 0",
                (itVert.next().equals(vert2) == true && vert2.getKey() == 0));
        assertTrue("Second vertex should be vert4 but its key should be 1",
                (itVert.next().equals(vert4) == true && vert4.getKey() == 1));
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("removeEdge");

        assertTrue("Number of edges should be zero", (instance.getNumEdges() == 0));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        assertTrue("Number of edges should be 8", (instance.getNumEdges() == 8));

        instance.removeEdge(edge8);
        assertTrue("Number of edges should be 7", (instance.getNumEdges() == 7));

        Iterator<Edge<Junction, Section>> itEd = instance.getEdges().iterator();

        itEd.next();
        itEd.next();
        assertTrue("Third edge should be edge3", (itEd.next().equals(edge3) == true));
        itEd.next();
        itEd.next();
        assertTrue("Sixth edge should be edge6", (itEd.next().equals(edge6) == true));

        instance.removeEdge(edge4);
        assertTrue("Number of edges should be 6", (instance.getNumEdges() == 6));

        itEd = instance.getEdges().iterator();
        itEd.next();
        itEd.next();
        assertTrue("Third edge should be edge3", (itEd.next().equals(edge3) == true));
        assertTrue("Fourth edge should be edge5", (itEd.next().equals(edge5) == true));
        assertTrue("Fifth edge should be edge6", (itEd.next().equals(edge6) == true));
        assertTrue("Last edge should be edge7", (itEd.next().equals(edge7) == true));
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_GenericType() {
        System.out.println("getVertex_generic");

        assertTrue("Vert should be null", (instance.getVertex(new Junction()) == null));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        assertTrue("Vert should be vert1", (instance.getVertex(j1) == vert1));
        assertTrue("Vert should be vert2", (instance.getVertex(j2) == vert2));
        assertTrue("Vert should be vert5", (instance.getVertex(j5) == vert5));
        assertTrue("Vert should be vert3", (instance.getVertex(j3) == vert3));
        assertTrue("Vert should be vert4", (instance.getVertex(j4) == vert4));
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_int() {
        System.out.println("getVertex_int");

        assertTrue("Vert should be null", (instance.getVertex(1) == null));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        assertTrue("Vert should be vert1", (instance.getVertex(0) == vert1));
        assertTrue("Vert should be vert2", (instance.getVertex(1) == vert2));
        assertTrue("Vert should be vert5", (instance.getVertex(4) == vert5));
        assertTrue("Vert should be vert3", (instance.getVertex(2) == vert3));
        assertTrue("Vert should be vert4", (instance.getVertex(3) == vert4));
    }

    /**
     * Test of clone method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        Graph<Junction, Section> instClone = instance.clone();

        assertTrue("Number of vertices should be equal", instance.getNumVertices() == instClone.getNumVertices());
        assertTrue("Number of edges should be equal", instance.getNumEdges() == instClone.getNumEdges());

        //vertices should be equal
        Iterator<Vertex<Junction, Section>> itvertClone = instClone.getVertices().iterator();
        Iterator<Vertex<Junction, Section>> itvertSource = instance.getVertices().iterator();
        while (itvertSource.hasNext()) {
            assertTrue("Vertices should be equal", (itvertSource.next().equals(itvertClone.next()) == true));
        }

        // And edges also
        Iterator<Edge<Junction, Section>> itedgeSource = instance.getEdges().iterator();
        while (itedgeSource.hasNext()) {
            Iterator<Edge<Junction, Section>> itedgeClone = instClone.getEdges().iterator();
            boolean exists = false;
            while (itedgeClone.hasNext()) {
                if (itedgeSource.next().equals(itedgeClone.next())) {
                    exists = true;
                }
            }
            assertTrue("Edges should be equal", (exists == true));
        }
    }

    /**
     * Test of equals method, of class Graph.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Junction j3 = new Junction();
        j3.setName("j3");
        Junction j4 = new Junction();
        j4.setName("j4");
        Junction j5 = new Junction();
        j5.setName("j5");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");
        Section s2 = new Section();
        s2.setRoadName("s2");
        s2.setTypology("Highway");
        Section s3 = new Section();
        s3.setRoadName("s3");
        s3.setTypology("Highway");
        Section s4 = new Section();
        s4.setRoadName("s4");
        s4.setTypology("Highway");
        Section s5 = new Section();
        s5.setRoadName("s5");
        s5.setTypology("Highway");
        Section s6 = new Section();
        s6.setRoadName("s6");
        s6.setTypology("Highway");
        Section s7 = new Section();
        s7.setRoadName("s7");
        s7.setTypology("Highway");
        Section s8 = new Section();
        s8.setRoadName("s8");
        s8.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);
        Vertex<Junction, Section> vert3 = instance.insertVertex(j3);
        Vertex<Junction, Section> vert4 = instance.insertVertex(j4);
        Vertex<Junction, Section> vert5 = instance.insertVertex(j5);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);
        Edge<Junction, Section> edge2 = instance.insertEdge(j1, j3, s2, 1);
        Edge<Junction, Section> edge3 = instance.insertEdge(j2, j4, s3, 3);
        Edge<Junction, Section> edge4 = instance.insertEdge(j3, j4, s4, 4);
        Edge<Junction, Section> edge5 = instance.insertEdge(j3, j5, s5, 1);
        Edge<Junction, Section> edge6 = instance.insertEdge(j4, j1, s6, 2);
        Edge<Junction, Section> edge7 = instance.insertEdge(j5, j4, s7, 1);
        Edge<Junction, Section> edge8 = instance.insertEdge(j5, j5, s8, 1);

        assertFalse("Should not be equal to null", instance.equals(null));

        assertTrue("Should be equal to itself", instance.equals(instance));

        assertTrue("Should be equal to a clone", instance.equals(instance.clone()));

        Graph<Junction, Section> other = instance.clone();
        Edge<Junction, Section> edge = other.getEdge(other.getVertex(j5), other.getVertex(j5));
        other.removeEdge(edge);
        assertFalse("Instance should not be equal to other", instance.equals(other));

        other.insertEdge(j5, j5, s8, 1);
        assertTrue("Instance should be equal to other", instance.equals(other));

        other.removeVertex(j4);
        assertFalse("Instance should not be equal to other", instance.equals(other));
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "\nRoadmap not defined!";
        String result = instance.toString();

        assertTrue("Output should be the same", result.equals(expResult));

        Junction j1 = new Junction();
        j1.setName("j1");
        Junction j2 = new Junction();
        j2.setName("j2");
        Section s1 = new Section();
        s1.setRoadName("s1");
        s1.setTypology("Highway");

        Vertex<Junction, Section> vert1 = instance.insertVertex(j1);
        Vertex<Junction, Section> vert2 = instance.insertVertex(j2);

        Edge<Junction, Section> edge1 = instance.insertEdge(j1, j2, s1, 6);

        expResult = "Roadmap: 2 intersections, 1 roads\n"
                + "j1 (0): \n"
                + "\tRoad name: s1\n"
                + "\tTypology: Highway\n"
                + "\tToll: 0.0\n"
                + "\tWind speed: 0.0m/s\n"
                + "\tWind orientation: 0.0º\n"
                + "\tList of segments:\n"
                + "\n"
                + "j2 (1): \n"
                + "\n";
        result = instance.toString();
        System.out.println(result);

        assertEquals(expResult, result);
        //assertTrue("Output should be the same", result.equals(expResult));
    }

}
