package MyHelperClasses;

public class DoubleNode<T> {
    public T data;
    public DoubleNode prev, next;

    public DoubleNode(T data) {
        this.data = data;
    }

    public void addNext(T new_data) {
        DoubleNode<T> doubleNode = new DoubleNode<>(new_data);
        doubleNode.next = this.next;
        if (this.next != null)
            this.next.prev = doubleNode;
        this.next = doubleNode;
        doubleNode.prev = this;
    }

    @Override
    public String toString() {
        return "-Node("+data+")-";
    }
}
