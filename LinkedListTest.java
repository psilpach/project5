package prj5;

import java.util.Arrays;
import student.TestCase;

/**
 * This is a test class for ListInterface
 * 
 * @author Kevin Benson kbenson8
 * @version Apr 10, 2017
 */
public class LinkedListTest extends TestCase {

    private LinkedList<String> list;
    private LinkedList<String> emptyList;


    /**
     * sets up for testing
     */
    public void setUp() {
        list = new LinkedList<String>();
        list.add("Four");
        list.add("Three");
        list.add("Two");
        list.add("One");

        emptyList = new LinkedList<String>();
    }


    /**
     * tests the add() methods
     */
    public void testAdd() {
        // add with no index (beginning)
        emptyList.add("Lonely");
        assertEquals(1, emptyList.getLength());
        // add to middle
        list.add(2, "hello there");
        assertEquals("hello there", list.getEntry(2));
        assertEquals(5, list.getLength());
        // add to end
        list.add(list.getLength(), "Last");
        assertEquals("Last", list.getEntry(list.getLength() - 1));
        assertEquals(6, list.getLength());

        // index < 0
        Exception exception = null;
        try {
            list.add(-1, "lmao nice try");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // index > size
        exception = null;
        try {
            list.add(10, "lmao nice try");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
    }


    /**
     * tests the clear() method
     */
    public void testClear() {
        list.clear();
        assertTrue(list.isEmpty());

        emptyList.clear();
        assertTrue(emptyList.isEmpty());
    }


    /**
     * tests the contains() method
     */
    public void testContains() {
        // front
        assertTrue(list.contains("One"));
        // middle
        assertTrue(list.contains("Three"));
        // back
        assertTrue(list.contains("Four"));
        // not in list
        assertFalse(list.contains("Not in list"));
        // empty list
        assertFalse(emptyList.contains("anything"));
    }


    /**
     * tests the getEntry() method
     */
    public void testGetEntry() {
        // Front
        assertEquals("One", list.getEntry(0));
        // Middle
        assertEquals("Three", list.getEntry(2));
        // Back
        assertEquals("Four", list.getEntry(3));

        // index < 0
        Exception exception = null;
        try {
            list.getEntry(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // index > size
        exception = null;
        try {
            list.getEntry(10);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // size == 0
        exception = null;
        try {
            emptyList.getEntry(0);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);

    }


    /**
     * tests the getLength() method
     */
    public void testGetLength() {
        assertEquals(4, list.getLength());
        assertEquals(0, emptyList.getLength());
    }


    /**
     * tests the isEmpty() method
     */
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(emptyList.isEmpty());
    }


    /**
     * tests the remove() method
     */
    public void testRemove() {
        // front
        assertEquals("One", list.remove(0));
        // middle
        assertEquals("Three", list.remove(1));
        // back
        assertEquals("Four", list.remove(1));

        // index < 0
        Exception exception = null;
        try {
            list.remove(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // index > size
        exception = null;
        try {
            list.remove(10);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // size == 0
        exception = null;
        try {
            emptyList.remove(0);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
    }


    /**
     * tests the replace() method
     */
    public void testReplace() {
        // front
        assertEquals("One", list.replace(0, "Uno"));
        assertEquals("Uno", list.getEntry(0));
        // middle
        assertEquals("Two", list.replace(1, "Dos"));
        assertEquals("Dos", list.getEntry(1));
        // back
        assertEquals("Four", list.replace(3, "Cuatro"));
        assertEquals("Cuatro", list.getEntry(3));

        // index < 0
        Exception exception = null;
        try {
            list.replace(-1, "lmao");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
        // index > size
        exception = null;
        try {
            list.replace(10, "lmao");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("index is invalid for a list this size",
            exception instanceof IllegalArgumentException);
    }


    /**
     * tests the toArray() method
     */
    public void testToArray() {
        Object[] listArray = { "One", "Two", "Three", "Four" };
        Object[] emptyArray = new Object[0];

        assertTrue(Arrays.equals(listArray, list.toArray()));
        assertTrue(Arrays.equals(emptyArray, emptyList.toArray()));

    }

}