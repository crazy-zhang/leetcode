package List;


public class LinkList{
        public  int data;
        public LinkList next;
        //头插法
        public LinkList top_create(int len) {
            LinkList head = null;

            for(int i = 0; i < len; i++) {
                LinkList temp = new LinkList();
                temp.data = i;
                if(head == null){
                    head = temp;
                }
                else{
                    temp.next = head;
                    head = temp;
                   // System.out.println(i);
                }
            }
            return head;
        }

        //尾插法  一定要加一个尾指针，遍历的时候需要从头到尾，不然一直插到尾
        public LinkList tail_create(int len) {
            LinkList head = null;
            LinkList tail = null;

            for(int i = 0; i < len; i++) {
                LinkList temp = new LinkList();
                temp.data = i;

                if(head == null) {
                    head = tail = temp;
                }
                else {
                    tail.next = temp;
                    tail = temp;
                    //System.out.println(temp.data);
                }
            }
            return head;
        }
        public void display(LinkList head) {
            LinkList list = head;
            while(list != null) {
                System.out.println(list.data);
                list = list.next;
            }
        }
}

