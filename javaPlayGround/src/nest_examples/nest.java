package nest_examples;
public class nest {
    public static void main(String[] args) {
        int n=10;
        sum(n);
    }
    public static int sum(int n){
        System.out.println(n);
        if(n==1){
            return 1;
        }
        return sum(n-1) + n;
    }
}