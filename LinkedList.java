package prj5;

import list.ListInterface;

/**
 * This is a linked list class with an inner node class
 * 
 * @author Kevin Benson kbenson8
 * @version Apr 10, 2017
 * @param <T>
 *            the type of data in the list nodes
 */
public class LinkedList<T> implements ListInterface<T> {

    private Node<T> topNode;
    private int size;


    /**
     * Default constructor
     */
    public LinkedList() {
        topNode = null;
        size = 0;
    }


    /**
     * Adds an item to the front of the list
     * 
     * @param T
     *            the new data to be added
     */
    @Override
    public void add(T newData) {
        Node<T> newNode = new Node<T>(newData);
        if (isEmpty()) {
            topNode = newNode;
            size++;
            return;
        }
        newNode.setNextNode(topNode);
        topNode = newNode;
        size++;
    }


    /**
     * Adds an item to the given index
     * 
     * @param index
     *            the index where the new item is added
     * 
     * @param newData
     *            the new data to be added
     * 
     * @throws IndexOutOfBoundsException
     *             if index is invalid
     */
    @Override
    public void add(int index, T newData) {
        if (index <= 0 || index > size) { // check to see that index is valid
            throw new IllegalArgumentException(
                "index is not valid for a list this size");
        }

        Node<T> newNode = new Node<T>(newData);
        Node<T> nodeBefore = getNodeAt(index - 1);
        Node<T> nodeAfter = nodeBefore.getNextNode();
        newNode.setNextNode(nodeAfter);
        nodeBefore.setNextNode(newNode);
        size++;

    }


    /**
     * Empties all the items from the list and makes size 0
     */
    @Override
    public void clear() {
        topNode = null;
        size = 0;
    }


    /**
     * Sees if the list contains an item
     * 
     * @return true if the list contains the item
     */
    @Override
    public boolean contains(T anEntry) {
        Node<T> curr = topNode;
        while (curr != null) {
            if (curr.getData() == anEntry) {
                return true;
            }
            curr = curr.getNextNode();
        }
        return false;
    }


    /**
     * Gets the entry at the specified position
     * 
     * @param index
     *            the index to get
     * 
     * @return the entry at the given index
     * 
     * @throws IndexOutOfBoundsException
     *             if index is not valid
     */
    @Override
    public T getEntry(int index) {
        if (index < 0 || index > size || size == 0) { // check to see that index
                                                      // is valid
            throw new IllegalArgumentException(
                "index is not valid for a list this size");
        }

        return getNodeAt(index).getData();
    }


    /**
     * Gets the length of the list
     * 
     * @return the number of items in the list
     */
    @Override
    public int getLength() {
        return size;
    }


    /**
     * Sees if the list is empty or not;
     * 
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Removes the entry at the specified position and returns it
     * 
     * @param index
     *            the index of the entry to be removed
     * 
     * @return the removed entry
     * 
     * @throws IndexOutOfBoundsException
     *             if the index is invalid
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index > size || size == 0) { // check to see that index
                                                      // is valid
            throw new IllegalArgumentException(
                "index is not valid for a list this size");
        }

        if (index == 0) {
            Node<T> oldTop = topNode;
            topNode = topNode.getNextNode();
            return oldTop.getData();
        }
        Node<T> nodeBefore = getNodeAt(index - 1);
        Node<T> nodeToRemove = nodeBefore.getNextNode();
        nodeBefore.setNextNode(nodeToRemove.getNextNode());
        size--;
        return nodeToRemove.getData();
    }


    /**
     * Replaces an entry at an index with a new entry
     * 
     * @param index
     *            the index to be replaced
     * 
     * @param anEntry
     *            the entry replacing the old one
     * 
     * @return the entry that was replaced
     * 
     * @throws IndexOutOfBoundsException
     *             if the index is invalid
     */
    @Override
    public T replace(int index, T anEntry) {
        if (index < 0 || index > size || size == 0) { // check to see that index
                                                      // is valid
            throw new IllegalArgumentException(
                "index is not valid for a list this size");
        }

        if (index == 0) {
            Node<T> newNode = new Node<T>(anEntry);
            newNode.setNextNode(topNode.getNextNode());
            Node<T> oldTop = topNode;
            topNode = newNode;
            return oldTop.getData();
        }

        Node<T> nodeBefore = getNodeAt(index - 1);

        Node<T> newNode = new Node<T>(anEntry); // create node that is replacing

        Node<T> replacedNode = nodeBefore.getNextNode(); // node that is being
        // replaced
        Node<T> nextNode = replacedNode.getNextNode(); // node to set as
                                                       // replacement's next
                                                       // node
        nodeBefore.setNextNode(newNode); // replace old node with new one
        newNode.setNextNode(nextNode);

        return replacedNode.getData(); // return old node data
    }


    /**
     * Private helper method that gets nodes at indexes
     * 
     * @param index
     *            the position of the node to get
     * @return the node at index
     */
    private Node<T> getNodeAt(int index) {
        assert (topNode != null) && (0 <= index) && (index <= size);

        if (index == 0) {
            return topNode;
        }
        Node<T> curr = topNode;

        for (int i = 0; i < index; i++) {
            curr = curr.getNextNode();
        }
        return curr;
    }


    /**
     * Gets the list represented as an array
     * 
     * @return the lists elements in an array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> curr = topNode;
        int count = 0;
        while (curr != null) {
            array[count] = curr.getData();
            curr = curr.getNextNode();
            count++;
        }
        return array;
    }


    /**
     * This is the LinkedLists private inner node class
     * 
     * @author Kevin Benson kbenson8
     * @version Apr 10, 2017
     * 
     * @param <T>
     *            the type of data in the node
     */
    @SuppressWarnings("hiding")
    private class Node<T> {

        private Node<T> next;
        private T data;


        /**
         * Default constructor
         * Sets data and next node
         */
        public Node(T data) {
            this.data = data;
            next = null;
        }


        /**
         * Gets the next node in the chain
         * 
         * @return the next node
         */
        public Node<T> getNextNode() {
            return next;
        }


        /**
         * Gets the data contained in the node
         * 
         * @return the data of type T
         */
        public T getData() {
            return data;
        }


        /**
         * Sets the next node in the chain
         * 
         * @param nextNode
         *            the next node to be set
         */
        public void setNextNode(Node<T> nextNode) {
            next = nextNode;
        }

    }

}
