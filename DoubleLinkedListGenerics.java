//coded by John Esco
//create it as a generic class
public class DoubleLinkedListGenerics <T>{ //generic linked list of type T (any type)
    //create internal class to hold info ..This will only be used internally to group objects
    private class ListNode{
        //Attributes
        private T data; //data is the data of a node but of any type T
        private ListNode link; //link that points to next node
        //default constructor
        public ListNode(){
            data = null;
            link = null;
        }
        //parameterized constructor
        public ListNode(T aData, ListNode aLink){ //takes in data of any type
            data = aData;
            link = aLink;
        }
    }
    private ListNode head;//First element in the list
    private ListNode curr; //Element of interested used externally. an iterator node but not a temp iterator node.
    private ListNode prev; //one ListNode behind Current;
    //constructor for GenList...
    public DoubleLinkedListGenerics(){//only need the angle brackets when defining the class
        head = curr = prev = null;
    }
    //insert method: inseerts node at the end of the list
    public void insert(T aData){  //inserts data of T type
        //create the node
        ListNode newNode = new ListNode(aData,null); //at end. last node points to nothing
        //need a head otherwise no data
        if(head == null){ //empty list
            head = newNode; //head points to newNode
            curr = head; //current is the head at the moment but it cannot be moved at this point
            return;
        }
        else{ //List is not empty
            ListNode temp = head; //temp node is pointing to head or start
            while(temp.link != null){ //for loop structure for linkedList
                temp = temp.link;  //increments temp node and stops when pointing at nothing aka the end
            }
            temp.link = newNode; //Adds the new node at the end.
        }
    }
    public void print(){
        ListNode temp = head;
        while(temp != null){//not pointing to null but isn't null so it goes to the end
            System.out.println(temp.data); //inherited toString method from base object class
            temp = temp.link;
        }
    }
    //Sort of like an accessor
    public T getCurrent(){  //gets the value of type T
        if( curr != null){
            return curr.data;
        }
        else
            return null;
    }
    //sort of like a mutator
    public void setCurrent(T aData){
        if(curr != null){
            curr.data = aData;
        }
        else{
            System.out.println("Cannot access what does not exist");
        }
    }
    //method to move current
    public void moveCurrentForward(){
        if(curr != null){
            prev = curr; //previous gets moved up
            curr = curr.link; // current gets moved up
        }
        else{
            System.out.println("You are at the end bruh");
        }
    }
    //reset the current
    public void resetCurrent(){
        curr = head;  //moves the current to the beginning
        prev = null;   //one before the beginning is nothing
    }
    //insertAfterCurrent method
    public void insertAfterCurrent(T aData){
        ListNode newNode = new ListNode(aData,null); //creates the new node being inserted
        if(curr != null){ //inside list
            newNode.link = curr.link; //the new node points to what current is pointing to
            curr.link = newNode;
        }
        //current is null but head isn't? it's outside of the list
        else if(head != null){
            System.out.println("Current is outside of the list");
        }
        else{
            System.out.println("The list is empty"); //if current is null and head is null it must be empty
        }
    }
    public void deleteCurrent(){
        //current is somewhere in the middle or end of the list
        if(curr != null && prev != null){
            prev.link = curr.link;  //previous is pointing to currennt. now points over current to what current pointed to
            curr = curr.link;
        }
        else if(curr != null && prev == null){
            //current is the head in this case
            head = curr.link; //head = head.link
            curr = head;
        }
        else{
            System.out.println("Current was null bruh");
        }
    }
    public boolean moreToIterate(){
        return curr != null;   //if current isn't null then there is more to iterate
    }



}

























