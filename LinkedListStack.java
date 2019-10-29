//Coded by John Esco
public class LinkedListStack <T>{
    private class ListNode{
        private T data;
        private ListNode link;
        //constructor
        public ListNode(T aData, ListNode aLink){
            data = aData;
            link = aLink;
        }
    }
    //instance variable
    private ListNode head; // first element and last one pushed
    //constructor
    public LinkedListStack(){
        head = null;
    }
    //push method
    public void push(T aData){
        ListNode newNode = new ListNode(aData, head); //stacks add to the top
        head = newNode; //head is moved to newNode
    }
    //Pop method
    public T pop(){
        if(head == null)
            return null;
        else{
            T returnData = head.data; //temporarily stores data from head
            head = head.link; //move head down stack
            return returnData;
        }
    }
    //peek method shows data at head without removing
    public T peek(){
        if(head == null)
            return null;
        else
            return head.data;
    }
    //printStack method shows the stack
    public void printStack(){
        ListNode temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.link;
        }
    }
    public int stackSize(){
        ListNode temp = head;
        int counter = 0;
        while (temp!= null){
            counter++; //initially counts the head
            temp=temp.link; //moves node forward
        }
        return counter;
    }
    //method to clear the stack
    public void clearStack(){
        head = null;
    }
}
