public class List {
    private Link head;
    private Link tail;
    private int nodeCount = 0;

    public List() {
        head = null;
        tail = null;
    }


    public void insert(int index, Object data) {
        if (index < 1 || index > nodeCount + 1) {
            System.out.println("Sorry could not insert due to index problems");
            return;
        }
        // insert head
        if (isEmpty() && index == 1) {
            head = tail = new Link(data, null);
            nodeCount++;
            return;
        }
        if (!isEmpty() && index == 1) {
            Link temp = head;
            head = new Link(data, temp);
            nodeCount++;
            return;
        }
        // insert middle
        if (index <= nodeCount) {
            int i = 1;
            Link current = head;
            Link previous = null;
            while (i != index) {
                previous = current;
                current = current.next;
                i++;
            }
            previous.next = new Link(data, current);
            nodeCount++;
            return;
        }
        // insert at end
        insertAtEnd(data);
    }

    public void insertAtEnd(Object data) {
        tail.next = new Link(data, null);
        tail = tail.next;
        nodeCount++;
    }

    public List find(Object toFind) {
        if (isEmpty()) {
            System.out.println("This List is empty");
            return null;
        }
        List listOfIndexes = new List();
        Link current = head;
        int index = 1;
        while (current != null) {
            if (isEmpty(listOfIndexes)) {
                if (current.data.equals(toFind)) {
                    listOfIndexes.insert(1, index);
                }
            } else {
                if (current.data.equals(toFind)) {
                    listOfIndexes.insertAtEnd(index);
                }
            }
            index++;
            current = current.next;
        }
        return listOfIndexes;
    }

    public void deleteRange(int start, int end) {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        if (start > end) {
            System.out.println("Your range is inverted");
            return;
        }
        if (start == end) {
            deleteAtIndex(start);
            System.out.println("Complete...");
            return;
        }
        int deleteCount = end - start + 1;
        for (int i = 0; i < deleteCount; i++) {
            deleteAtIndex(start);
        }
        System.out.println("Complete...");
    }

    public Object getSize() {
        return nodeCount;
    }

    public void deleteItem(Object item) {
        if (isEmpty()) {
            System.out.println("This List is empty");
            return;
        }
        Link current = head;
        int index = 1;
        while (current != null) {
            if (current.data.equals(item)) {
                deleteAtIndex(index);
                index--;
            }
            current = current.next;
            index++;
        }
        System.out.println("Complete...");
    }

    public Link retrieve(int index) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        if (index > nodeCount || index < 1) {
            System.out.println("Out of bound");
            return null;
        }
        if (index == 1) {
            return head;
        }
        if (index == nodeCount) {
            return tail;
        }
        Link current = head;
        int i = 1;
        while (current != null && i != index) {
            current = current.next;
            i++;
        }
        return current;
    }

    @Override
    public String toString() {
        String linkedList = "This is your list" +
                "\n[";
        Link node = head;
        while (node != null) {
            linkedList += (node);
            linkedList += ",";
            node = node.next;
        }
        int lastComma = linkedList.lastIndexOf(",");
        if (lastComma != -1) {
            linkedList = linkedList.substring(0, linkedList.length() - 1);
        }
        linkedList += ']';


        return linkedList;
    }


    private void deleteAtIndex(int index) {
        if (isEmpty()) {
            return;
        }
        if (index < 1) {
            return;
        }
        if (index > nodeCount) {
            return;
        }
        if (index == 1) {
            head = head.next;
            nodeCount--;
            return;
        }
        if (index == nodeCount) {
            Link currentLink = head;
            while (currentLink.next != tail) {
                currentLink = currentLink.next;
            }
            tail = currentLink;
            currentLink.next = null;
            nodeCount--;
            return;
        }
        int i = 1;
        Link current = head;
        Link previous = null;
        while (i != index) {
            previous = current;
            current = current.next;
            i++;
        }
        previous.next = current.next;
        nodeCount--;
    }

    private boolean isEmpty(List listOfIndexes) {
        return listOfIndexes.head == null;
    }

    private boolean isEmpty() {
        return head == null;
    }
}
