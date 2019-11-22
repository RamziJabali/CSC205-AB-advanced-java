public class Link {
    public Link next;
    public Object data;


    public Link(Object _data, Link node) {
        data = _data;
        next = node;
    }

    @Override
    public String toString() {
        return "Links data:" + data;
    }
}