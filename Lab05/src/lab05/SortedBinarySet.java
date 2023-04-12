package lab05;

public class SortedBinarySet {
	/** represent the simple array that holds the list values */
	public double[] theData;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** constant for initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** keep this FALSE for lab; we will play with this in the assignment **/
	public boolean usesBinarySearch = false;

	/** default constructor */
	public SortedBinarySet(){
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.size = 0;

	}

	public SortedBinarySet(double[] input){
		this.theData = input;
		this.size = input.length-1;
		for(int i = 0; i < size;i++){
			insert(this.theData[i]);
		}
	}

	public boolean empty(){
		return size==0; //placeholder
	}

	public int size(){
		return size; //placeholder
	}

	public void grow(){
		//keep data make 2x
		capacity *=2;
		double[] theData2x = new double[capacity];
		for(int i=0;i<size;i++){
			theData2x[i] = theData[i];
		}
		theData = theData2x;
	}

	public String toString(){
		String cap = "Capcity: "+this.capacity;
		String siz = "Size: "+this.size;
		String dat = "Data: {";
		for(int i = 0; i < size; i++){
			if(i==size-1){
				dat+=theData[i];
			}
			else{
				dat+=theData[i]+",";
			}

		}
		return cap +", " + siz +", "+ dat + "}"; // placeholder
	}

	public void clear() {
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.size = 0;
	}

	public boolean insert(double newVal){
		int loc = findIndex(newVal);
		if(loc >= 0){return false;}
		if(size==capacity){
			this.grow();
		}
		loc = -(loc+1);
		for(int i = size; i>loc;i--){
			theData[i]=theData[i-1];
		}
		theData[loc] = newVal;
		size+=1;
		return true;
	}

	public boolean remove(double element){
		int loc = findIndex(element);
		if(loc < 0){return false;}
		double[] theDataCopy = new double[theData.length-1];
		for (int i = 0, j = 0; i < size; i++) {
			if (i != loc) {
				theDataCopy[j++] = theData[i];
			}
		}
		size--;
		theData = theDataCopy;
		return true;
	}


	private int sequentialFind(double target) {
		for(int i = 0; i < size; i++){
			if(theData[i]==target){
				return i;
			}
			if(theData[i]>target){
				 return -i-1;
			}
		}
		/*
		[1,2,3,5,6,7]
		 0 1 2 3 4 5
		find(4) => -3-1=-4
		find(3) => 2
		find(8) => -6-1 =-7
		find(0) => -0-1=-1

		 */
		return -size-1; //placeholder
	}

	private int binaryFind(double target) {
		int min = 0;
		int max = size-1;
		while(min <=max) {
			int mid = ((max+min)/2);
			if(theData[mid] == target){
				return mid;}
			else if(theData[mid] < target){
				min = mid -1;}
			else{
				max = mid - 1;
			}
		}
		return -min - 1; // placeholder
	}

	public int findIndex(double target) {
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);

		}
	}

	public boolean containsAll(double[] elements){
		for(int i = 0; i < elements.length;i++){
			 if(!(findIndex(elements[i]) >= 0)){
				 return false;
			 }
		}
		return true; // placeholder
	}

	public boolean contains(double element){
		int loc = findIndex(element);
		if(loc >=0){return true;}
		return false; // placeholder
	}

}
