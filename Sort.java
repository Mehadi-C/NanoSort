
package nanosort;

//imports the necessary functions
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sort extends JPanel {
    // constants for drawing rectangles
    private final int BAR_WIDTH = 1; // 1
    private final int BAR_OFFSET = 1; // 1
    private final int TRANSITION_DELAY = 0; // 0

    // instance variable
    private int recent;
    private int[] arr;
    private ArrayList<Integer> newArr;
    private int selected;
    private int swap_1;
    private int swap_2;
    private Timer timer;
    private boolean running = false;
    private int comparisons = 0;

    public Sort(int[] arr) {
        this.arr = arr;
        this.arr[arr.length - 1] = 50;
        this.newArr = new ArrayList<>();
        this.selected = 0;
        this.swap_1 = -1;
        simulate();
    }

    // draws array
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // keep track of coordinates
        int x = 0;
        int y = 500;

        // display the arraylist
        for (int i = 0; i < newArr.size(); i++) {
            int rect_height = newArr.get(i) * 4;

            // display rectangle
            g.drawRect(x, y - rect_height, BAR_WIDTH, rect_height);

            // show colour
            g.setColor(Color.BLACK);

            // fill special if needed
            if (this.swap_1 == i) {
                g.setColor(Color.GREEN);
            }

            // fill the rectangle
            g.fillRect(x, y - rect_height, BAR_WIDTH, rect_height);

            x += BAR_OFFSET; // move right
        }

        x = 0;
        y = 250;

        // display the array
        for (int i = 0; i < arr.length; i++) {
            // calculate height of rectangle
            int rect_height = arr[i] * 4;

            // display rectangle
            g.drawRect(x, y - rect_height, BAR_WIDTH, rect_height);

            // make blue or black depending on whether it's selected
            if (this.selected == i) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }

            // fill the rectangle
            g.fillRect(x, y - rect_height, BAR_WIDTH, rect_height);

            x += BAR_OFFSET; // move right
        }
    }

    // simulates the sort
    private void simulate() {
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//determines action
                timer.stop();//stops time
            }
        });

        timer.start();//starts timer

        ActionListener action = new ActionListener() {//new actionListener
            int x = 0;//declare as 0

            @Override
            public void actionPerformed(ActionEvent e) {//determines action
                if (timer == null || !timer.isRunning()) {//determines whether timer is equal to null or whether timer.isRunning()
                    if (x >= arr.length) {//determines whether x is greater than or equal to arr.length
                        return;//return
                    }

                    int temp = arr[x];//temp is equal to arr[x]
                    index(temp, x);//activate index(temp, x)
                    selected = x;//selected is equal to x
                    ++x;//add x by 1
                    System.out.println(comparisons);//print out comparisons
                }
            }
        };

        // creates the timer
        new Timer(TRANSITION_DELAY, action).start();
    }

    public void index(int insertKey, int x) {//method called index
        synchronized (this) {
            running = true;//declare as true

            timer = new Timer(TRANSITION_DELAY / 5, new ActionListener() {//creates new timer
                //declare as int
                int lowerBound = 0;
                int elements = newArr.size();
                int upperBound = elements - 1;
                int curIn;
                //declare as false
                boolean done = false;

                @Override
                public void actionPerformed(ActionEvent e) {//determines action
                    swap_1 = -1;//declare as equal to -1
                    if (!done) {//determines whether not done
                        curIn = (upperBound + lowerBound) / 2;//curIn is equal to avg of upperBound and lowerBound

                        if (elements == 0) {//determines whether elements is equal to 0
                            done = true;//done is equal to true
                        } else {//however, if not true then...

                            if (lowerBound == curIn) {//determines whether lowerBound is equal to curIn
                                if (newArr.get(curIn) > insertKey) {//determines whether newArr.get(curIn) is greater than insertKey
                                    done = true;//done is equal to true
                                }
                            }

                            if (newArr.get(curIn) < insertKey) {//determines whether newArr.get(curIn) is less than insertKey
                                lowerBound = curIn + 1;//lowerBound is equal to curIn added by 1
                                if (lowerBound > upperBound) {//determines whether lowerBound is greater than upperBound
                                    done = true;//done is equal to true
                                }
                            } else if (lowerBound > upperBound) {//determines whether lowerBound is greater than upperBound
                                done = true;//done is equal to true
                            } else {//however, if not true then...
                                upperBound = curIn - 1;//upperBound is equal to curIn subtracted by 1
                            }
                        }

                        swap_1 = curIn;
                    } else {
                        recent = calc(insertKey);
                        timer.stop();
                        arr[x] = -1;
                        newArr.add(calc(insertKey), insertKey);
                    }

                    repaint();
                }
            });

            timer.start();


        }
    }

    public int calc(int insertKey) {
        int lowerBound = 0;
        int elements = this.newArr.size();
        int upperBound = elements - 1;

        int curIn;

        while (true) {
            curIn = (upperBound + lowerBound) / 2;
            ++comparisons;
            if (elements == 0) {
                return 0;
            }

            if (lowerBound == curIn) {
                if (this.newArr.get(curIn) > insertKey) {
                    return curIn;
                }
            }

            if (this.newArr.get(curIn) < insertKey) {
                lowerBound = curIn + 1;
                if (lowerBound > upperBound) {
                    return curIn + 1;
                }
            } else if (lowerBound > upperBound) {
                return curIn;
            } else {
                upperBound = curIn - 1;
            }
        }
    }

}