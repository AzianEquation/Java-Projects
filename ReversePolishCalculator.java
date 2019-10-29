//Coded by John Esco
import java.util.*;
import java.io.*;
public class ReversePolishCalculator {
    public static final String INPUT_FILE = "src/revPol.txt"; //file to be read in
    public static void main(String[] args) throws FileNotFoundException{
        try{
            LinkedListStack<Integer> stack = new LinkedListStack(); //creates the stack that will be used for calculations
            LinkedListQueue<Character> queue = new LinkedListQueue(); //queue that will hold the information to be parsed
            Scanner fileScanner = new Scanner(new File(INPUT_FILE));
            String fileLine = "";
            while(fileScanner.hasNextLine()){
                boolean illegalFormat = false; //initialize boolean
                fileLine = fileScanner.nextLine(); //each line is an expression
                System.out.println("Calculating " + fileLine);
                //loop to go through the whole line
                for(int i = 0; i<fileLine.length();i++){
                    //need to valid post-fix expressions in the queue
                    char character = fileLine.charAt(i); //variable that hold character of interest
                    if(Character.isDigit(character)){
                        //if the character is a number
                        queue.enqueue(character); //adds digit to queue as a string
                    }
                    //use == since this is a character not a string also use single quotes
                    else if(character == '+' || character =='-' || character == '*' || character == '/'){
                        queue.enqueue(character); //adds the operand to the queue
                    }
                    else if(character != '+' && character !='-' && character != '*' && character != '/' && !Character.isDigit(character) && !Character.isWhitespace(character)){
                        //checks for any character that is not an operator, number, or blankspace
                        System.out.println("Exception! For input string: " + character);
                    }
                }
                //Expression has been added to the queue... now its time to calculate
                //queue should only contain character digits and operands
                int tempSize = queue.queueSize();
                for(int i=0; i<tempSize;i++) { //goes through each item in the queue
                    char temp = queue.dequeue(); //removes item from queue and stores it temporarily
                    if (Character.isDigit(temp)) {
                        stack.push(Character.getNumericValue(temp)); //if it is a number pushes it onto stack
                    }

                    else if (temp == '+' || temp =='-' || temp == '*' || temp == '/'){ //characters other than digits
                        if(stack.stackSize()>=2){ //only able to do operations when two items on stack
                            int x = stack.pop(); //first item popped off
                            int y = stack.pop(); //second item popped off
                            //System.out.println(x);
                            //System.out.println(y);
                            switch(temp){
                                case '+':
                                    stack.push(y+x); //adds the two and pushes it back
                                    break;
                                case '-':
                                    stack.push(y-x); //subtracts and pushes result
                                    break;
                                case '*':
                                    stack.push(y*x); //multiplies and pushes result
                                    break;
                                case '/':
                                    if(x==0) {
                                        illegalFormat = true;
                                        System.out.println("Cannot divide by zero");
                                    }
                                    else
                                        stack.push(y/x); //divides and pushes result
                                    break;
                                default:
                                    System.out.println("Exception! For input string: " + temp);//there are only 4 operands. any other will be an exception
                            }
                        }
                    }
                }
                int result = 0; //initialize variable
                if(stack.stackSize()==1) {
                    //System.out.println(stack.stackSize()); testing
                   // if(stack.peek() == 0)
                  //      System.out.println("Result is: " + stack.peek());
                    result = stack.pop();
                    System.out.println("Result is: " + result);
                }
                else if (stack.stackSize() != 0 || illegalFormat) {
                    System.out.println("Ill formatted expression");
                    System.out.println("Result is 0");
                    stack.clearStack(); //clears the stack for the next expression
                }
                else if(stack.stackSize() == 0){
                    System.out.println("Ill formatted expression");
                    System.out.println("Result is 0");
                }
            }
            fileScanner.close();//closes the input stream
        }
        catch(FileNotFoundException e){
            System.out.println("Could not find the file");
        }
        catch(IOException e){
            System.out.println("The file could not be read.");
        }
    }
}
