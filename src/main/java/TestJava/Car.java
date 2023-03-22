package TestJava;

import BFS_DFS.Utils.Pair;
import Trees.Utils.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Car{

    // Attributes
    private int numOfDoors;
    private double price;
    private long serialNum;
    private static long serialNumCtr = 3000;

    // Constructors
    public Car()	// default constructor
    {
        numOfDoors = 4;
        price = 10000;
        serialNum = serialNumCtr++;
    }

    public Car(int nd, double pr)
    {
        numOfDoors = nd;
        price = pr;

        serialNum = serialNumCtr++;
    }

    public Car(Car c)
    {
        setNumOfDoors(c.numOfDoors);
        setPrice(c.price);

        serialNum = serialNumCtr++;
    }

    public int getNumOfDoors()
    {
        return numOfDoors;
    }

    public void setNumOfDoors(int nd)
    {
        numOfDoors = nd;
    }

    public double getPrice()
    {
        return price;
    }

    public long getserialNumber()
    {
        return serialNum;
    }

    public void setPrice(double pr)
    {
        price = pr;
    }


    public String toString()
    {
        return ("Car with serial number: " + serialNum + " has " + numOfDoors + " doors and its price is: " + price + "$.");
    }


    public Car clone()
    {
        return new Car(this);	// Create and return a new Car using the copy constructor
    }

    public void findMultiples(int arr[],int n, int x){
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(arr[i]%x==0)
                queue.add(i);
        }
        while (queue.size()>0){
            int index= queue.poll();
            System.out.println("Index "+index+" with value "+arr[index]);
        }
    }

    public int[] findDepths(List<List<Integer>> adj, int n){
        int[] depths=new int[n];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        int depth=0;
        while (!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int node= queue.poll();
                depths[node]=depth;
                for (int child: adj.get(node)){
                    queue.add(child);
                }
            }
            depth++;
        }
        return depths;
    }


    public static void main(String[] args) {
        Car car1=new Car();
        Car car2=car1.clone();
        car1.setNumOfDoors(5);
        System.out.println(car1);
        System.out.println(car2);
    }
}   // end of Car class