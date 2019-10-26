
package nanosort;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIDemo {
    
     public static void main(String[] args) {
        // initialize an array of size 20
        
        NanoSort na= new NanoSort(100000);//call array

        int [] N= na.getArray();//create array by getting array from NanoSort Class
        
        int[] arr = new int[20];//create a new array called arr with 20 cells



        // display on gui
        Sort grayBoard = new Sort(N);

        // display the gui
        JFrame frame = new JFrame("Sorting Assignment");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(grayBoard);
        frame.setPreferredSize(new Dimension(1000, 540));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
}
