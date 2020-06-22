package List;

public class List {
    public static void main(String[] args) {
        LinkList list = new LinkList();
        ListReverse listReverse = new ListReverse();
        //list = list.tail_create(10);
        //list = listReverse.recur_reverse(list);
        list = listReverse.list_reverse(list);
        list.display(list);
    }
}
