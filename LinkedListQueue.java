//Coded by John Esco
public class LinkedListQueue <T> {
    private class ListNode{
        private T data; //generic T data type
        private ListNode link;
        //default constructor
        public ListNode(){
            data = null;
            link = null;
        }
        //parameterized constructor
        public ListNode(T aData, ListNode aLink){
            data = aData;
            link = aLink;
        }
    }
    //Instance variables for LinkedListQueue
    private ListNode head;
    private ListNode tail;
    //constructor for LLQueue
    public LinkedListQueue()
    {
        head = tail = null;
    }
    public LinkedListQueue(T aData){
        enqueue(aData);
    }
    //method to add items to the queue
    public void enqueue(Object aData){
        ListNode newNode = new ListNode((T)aData, null); //creates the new node making sure to typecast
        if(head == null){//queue is empty
            head = newNode;
            tail = head; //only one item in list
        }
        else{ //not empty so added at the end
            tail.link = newNode; //last item points to new last item
            tail = tail.link; //moved up so newNode is tail
        }
    }
    //dequeue takes the head returns its data and removes it
    public T dequeue(){
        if(head == null) //cannot return what is not there
            return null;
        else{
            ListNode returnData = head; //stores what is in head
            head = head.link; //moves head up
            return returnData.data;
        }
    }
    //Peek returns data from head without removing it
    public T peek(){
        if(head == null)
            return null;
        else
            return head.data;
    }
    //Print shows the queue
    public void printQueue(){
        ListNode temp = head; //temp node to iterate through queue
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.link;
        }
        System.out.println();// clear kb buffer
    }
    //method to show the amount of nodes in the queue
    public int queueSize(){
        ListNode temp = head;
        int counter = 0;
        while (temp!= null){
            counter++; //initially counts the head
            temp=temp.link; //moves node forward
        }
        return counter;
    }
}
