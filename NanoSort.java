
package nanosort;

import java.awt.*;
import javax.imageio.*; // allows image loading
import java.io.*; // allows file access
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import java.util.Random;
import java.awt.Button;
import java.util.* ;

public class NanoSort {
    
    //initialize the datafields with the respective data types
    protected int [] array;
    protected ArrayList<Integer> arr;
    protected int nElems;                                  // number of dataitems 
    protected int curIn;

    
    public NanoSort( int len )//constructor called NanoSort
    {
        Random r= new Random();//applies Random
        array= new int[len];//creates array of length len in int
        
        for( int x= 0; x < len; x ++ )//loops so long as x is less than len
            array[x]= r.nextInt(100);//array[x] is equal to random value between 0 to 99
    }
    
    public int [] getArray()//method called getArray()
    {
        return array;//return array
    }
    
    public String toString()//method called toString()
    {
        String arr= "{ ";//declares arr in string
        for(int x= 0; x < array.length - 1; x ++ )//loops so long as x is less than array.length - 1
            arr += "" + array[x] + ", ";//display array[x]
        arr += "" + array[array.length - 1] + " }";//display last value in array
        
        return arr;//return arr
    }
    
    public void remove()
    {
        int [] temp= new int[array.length - 1];//declare as an array of data fields Cards, of size deck.length - 1
        for( int x= 0; x < temp.length; x ++ )//declare as int; loop so long as x is less than temp.length; add x by 1
            temp[x]= array[x + 1];//temp[x] is equal to deck[x + 1]
        array= temp;//deck is equal to temp
    }
    
    public ArrayList<Integer> getArr()//method called getArr()
    {
        return arr;//return arr
    }
    
    public int index(int insertKey) {//method called index
        //declare as int
        int lowerBound = 0;
        int elements = this.arr.size();
        int upperBound = elements - 1;

        int curIn;

        while (true) {//loops so long as true
            curIn = (upperBound + lowerBound) / 2;//declare curIn as average of upperBound and lowerBound

            if (elements == 0) {//determines whether elements is equal to 0
                return 0;//return 0
            }

            if (lowerBound == curIn) {//determines whether lowerBound is equal to curIn
                if (this.arr.get(curIn) > insertKey) {//determines whether this.arr.get(curIn) is greater than insertKey
                    return curIn;//return curIn
                }
            }

        if (this.arr.get(curIn) < insertKey) {//determines whether this.arr.get(curIn) is less than insertKey
            lowerBound = curIn + 1;//lowerBound is equal to curIn added by 1
            if (lowerBound > upperBound) {//determines whether lowerBound is greater than upperBound
                return curIn + 1;//return curIn added by 1
            }
        } else if (lowerBound > upperBound) {//determines whether lowerBound is greater than upperBound
            return curIn;//return curIn
        } else {//however, if not true...
            upperBound = curIn - 1;//upperBound is equal to curIn minus 1
        }
    }
}
    
    public ArrayList<Integer> nanosort()//determines nanosorot()
    {
        //declare as int
        int length= array.length;
        int tem;
        arr= new ArrayList<Integer>();//declare as arr as an ArrayList of datatype integer
        arr.add(array[0]);//append array[0] to arr
        
        for( int x= 1; x < length; x ++ )//declare as 1; loop so long as x is less than length; add x by 1
        {
            tem= array[x];//tem is equal to array[x]
            arr.add(index(tem), tem);//insert tem as index(tem) in arr
        }
        
        return arr;//return arr
    }
    
    public int index( ArrayList<Integer> num, int val )//method called index
    {
       //declare as int
       int index;
       for( int x= 0; x < num.size(); x ++ )//declare as 0; loop so long as x is less than num.size(); add x by 1
       {
           if( num.get(x) > val )//determines whether num.get(x) is greater than val
               return x;//return x
       }
       
       return num.size();//return num.size()
    }
    
    public void convert( ArrayList<Integer> arr )//method called convert
    {
        array= new int[arr.size()];//create array of size arr.size() of datatype int
        for( int x= 0; x < arr.size(); x ++ )//declare as 0; x is less than arr.size(); add x by 1
            array[x]= arr.get(x);//array[x] is equal to arr.get(x)
    }
    
    public int [] selectionSort()//method called selectionSort()
    {
        for ( int j = 0; j < array.length-1; j ++ )//declare as 0; loop so long as j is less than deck.length - 1; add j by 1
      {
        // Find min: the index of the integer that should go into cell j.
        // Look through the unsorted integers (those at j or higher)
        int min = j;//declare as j
        for ( int k = j+1; k < array.length; k ++ )//declare as j + 1; loop so long as k is less than deck.length; add k by 1
        {
          if ( array[min] > array[k])//determines whether deck[min].CsrdNum() is greater than deck[k].CardNum()
              min = k;//min is equal to k
        }
        // Swap the int at j with the int at min 
        int temp = array[j];//temp is equal to deck[j]
        array[j]= array[min];//deck[j] is equal to deck[min]
        array[min]= temp;//deck[min] is equal to temp
      }
        
        return array;
   }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        NanoSort na= new NanoSort(1000);//constructor to initialize class NanoSort as na
        System.out.println(na.toString());//display array
        ArrayList<Integer> n= na.nanosort();//configure and activate nanosort
        int [] N= na.getArray();//N is equal to na.getArray()
        System.out.println(n.toString());//display sorted array
    }
    
}
