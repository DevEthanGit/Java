package lab05;

public class Tester {
    public static void main(String[] args){
        SortedBinarySet sbs1 = new SortedBinarySet();
        System.out.println("SBS1: "+sbs1.toString());
        System.out.println("Is Empty: " + sbs1.empty());
        System.out.println("Size: " + sbs1.size());
        sbs1.grow();
        System.out.println("Grow: " + sbs1);
        sbs1.clear();
        System.out.println("Clear: " + sbs1);
        System.out.println("---------------------------------------------------------------");
        SortedBinarySet sbs2 = new SortedBinarySet();
        sbs2.insert(3.0);
        sbs2.insert(3.0);
        System.out.println("SBS2: "+sbs2);
        System.out.println("Empty: " + sbs2.empty());
        System.out.println("Size: " + sbs2.size());
        System.out.println("---------------------------------------------------------------");

        SortedBinarySet sbs3 = new SortedBinarySet();
        double[] nums = {2.2,3.4,4.1,5.6,6.4,0.1,1.1,5.0,4.3,1.3,101.130,140.21};
        double[] num2 = {2.2,1.1,5.0};
        for(double num: nums){
            sbs3.insert(num);
        }
        System.out.println("SBS3: "+sbs3);
        System.out.println("Is Empty: " + sbs3.empty());
        System.out.println("Size: " + sbs3.size());
        System.out.println("has num2: "+ sbs3.containsAll(num2));
        System.out.println("Remove: " + sbs3.remove(5));
        System.out.println("does not have 5.0: "+sbs3.containsAll(num2));
        System.out.println("SBS3: "+sbs3);
        SortedBinarySet sbs4 = new SortedBinarySet(num2);
        System.out.println("_________________");
        System.out.println(sbs4);




    }


}
