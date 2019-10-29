public class SimpleRecursion {
    public static void main(String[] args) {
    countDown(5);
    System.out.println(factorial(5));
    }
    public static void countDown(int num){
        System.out.println(num);
        if(num <=0)//halt condition
            return;
        else
            countDown(num-1); //Recursive call
    }
    public static int factorial(int num){
        if(num == 0 ){
            return 1;
        }
        else
            return num*factorial(num-1); // recursive call
        //not the same thing
        //return num*factorial(num--);//DONT DO THIS  this wont happen until everything else is completed
    }
    public static int gcd(int num1, int num2){
        if(num2 == 0)
            return num1;
        else
            return gcd(num2, num1%num2);
    }
    public static int fibbo(int pos){
        if(pos ==1 || pos == 2){
            return 1;
        }
        else
            return fibbo(pos-1)+fibbo(pos-2);
    }

}
