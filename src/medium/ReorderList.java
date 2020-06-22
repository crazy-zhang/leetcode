package medium;

public class ReorderList {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}

      public static ListNode create(ListNode head) {
          ListNode temp = null;
          ListNode tail = null;
          for(int i = 0; i < 10; i++) {
              temp = new ListNode();
              temp.val = i;
              if(head == null) {
                  head = tail = temp;
              }
              tail.next = temp;
              tail = temp;
          }
          return head;
      }

      public static void reorderList(ListNode head) {

          while(head != null) {
              System.out.println(head.val);
              head = head.next;
          }
      }
  }

  public static void main(String[] args) {
        ListNode listNode = null;
        listNode = ListNode.create(listNode);
        ListNode.reorderList(listNode);
  }
}
