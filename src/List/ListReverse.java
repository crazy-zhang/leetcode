package List;



public class ListReverse {
    public LinkList list_reverse(LinkList list) {
        list = list.tail_create(10);
        LinkList temp = null;
        LinkList pre = null;
        while(list != null) {
            temp = list.next;
            list.next = pre;
            pre = list;
            list = temp;
        }
        return pre;
    }


//递归方式
    public LinkList recur_reverse(LinkList head) {
        if(head.next == null || head == null) return head;

        LinkList temp = head.next;
        //System.out.println(temp.data);
        LinkList cur = recur_reverse(head.next);

        temp.next = head;
        head.next = null;
        return cur;
    }
}
