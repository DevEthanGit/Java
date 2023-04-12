package stack_v1;
public class tester{
	public static void main(String[] args) {
		Queue que = new Queue(10);
		System.out.println(que);
		for(int i = 0; i < 10; i++) {
			 que.enqueue((int)(Math.random() *100));
		}
		System.out.println(que);
		
	}
}