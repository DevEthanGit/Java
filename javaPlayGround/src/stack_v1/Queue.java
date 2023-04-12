
package stack_v1;
public class Queue{
		int front,rear,size;
		int capacity;
		int array[];
		
		public Queue(int capacity) {
			this.capacity = capacity;
			front = this.size = 0;
			rear = capacity-1;
			array = new int[this.capacity];
		}
		//que is full when size becomes equal to the capacity
		boolean isFull(Queue queue) {
			return (queue.size == queue.capacity);
		}
		//queue is empty when size is equal to zero
		boolean isEmpty(Queue queue) {
			return (queue.size ==0);
		}
		
		void enqueue(int item) {
			if(isFull(this)) {
				return;
			}
			this.rear = (this.rear+1)%this.capacity;
			this.array[this.rear] = item;
			this.size = this.size+1;
			System.out.println(item+ " enqueued to queue");
		}
		int dequeue() {
			if(isEmpty(this)) {
				return Integer.MIN_VALUE;
			}
			int item = this.array[this.front];
			this.front = (this.front +1)%this.capacity;
			this.size = this.size -1;
			return item;
		}
		int front() {
			if(isEmpty(this)) {
				return Integer.MIN_VALUE;
			}
			return this.array[this.front];
		}
		int rear() {
			if(isEmpty(this)) {
				return Integer.MIN_VALUE;
			}
			return this.array[this.rear];
		}
		public String toString() {
			String str="[";
			for(int i : array) {
				str+= i + " ";
			}
	
			return str + "]";
		}
}
