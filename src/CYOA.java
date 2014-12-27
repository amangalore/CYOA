import java.io.*;
import java.util.*;

public class CYOA {

    public static void main(String[] args) throws IOException {

        char restartName = 0;

        int room = 0;

        char userchoice = 0;


        Calc myStack = new Calc();

        while (restartName != 'q')
        {
            if( userchoice != 'z' )
            {
            	room = 0;
            }

            restartName = 0;
            int h = -1;
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 1;
            int m = 0;
            int n = 0;
            int o = 0;
            int p = 0;
            String checker;
            String line;
            String line2;
            String navigator;
            String nullchecker;
            String nullchecker2;
            String nullchecker3;
            String roomdisplay;
            String optiondisplay;
            String[][] messages = new String[10][10];
            String[][] options = new String[10][12];
            String[][] director = new String[10][12];

            if (args[0].isEmpty())
                System.exit(0);
            File file = new File(args[0]);

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                line = scan.next();
                line2 = scan.nextLine();

                if (line.equals("r")) {
                    h++;
                    i = 0;
                    j = 0;
                    k = 0;
                    messages[h][0] = line2;
                    options[h][0] = line2;
                    director[h][0] = line2;
                }
                if (line.equals("d")) {
                    i++;
                    messages[h][i] = line2;
                }
                if (line.equals("o")) {
                    j++;
                    options[h][j] = line2;
                }
                if (line.equals("t")) {
                    k++;
                    director[h][k] = line2;
                }
            }

            while (true) {
            	myStack.push(room);
                l = 1;
                while (true) {
                    nullchecker = messages[room][l];
                    if (nullchecker == null)
                        break;
                    System.out.println(messages[room][l]);
                    l++;
                }
                l = 1;
                while (true) {
                    nullchecker = options[room][l];
                    if (nullchecker == null)
                        break;
                    System.out.println((char) (l + 96) + "." + options[room][l]);
                    l++;
                }
                Scanner scan2 = new Scanner(System.in);
                userchoice = scan2.next().charAt(0);

                if(userchoice == 'z')
                {
                	room = myStack.pop();
                	room = myStack.pop();
                    break;
                }
                if (userchoice == 'y')
                {
                	String tempArr[] = new String[10];
                	o = 0;
                	p = 0;
                	while (true)
                	{
                		/*for(int a = 0; a < 10; a++)
                		{
                		nullchecker2 = director[a][0];
                		if (nullchecker2 == null)
                		{
                			break;
                		}
                		for (int a)
                		tempArr[a][o] = director[a][o];
                		//sortmethind(tempArr[a]);
                		}*/
                		
                		for (int a = 0; a < 10; a++ )
                		{
         
               				tempArr[a] = director[a][0];
                			
                		}
                			
                		mergeSort(tempArr);
                		/*compare tempArr[x] to director [y][0]. 
                		 * if x is the same then print our all the 0 to null values of options. 
                		 */
                		
                		System.out.print("Room" + tempArr[o] + " : ");
                		o++;
                		p = 0;
                		/*
                		while (true)
                		{
                			
                			nullchecker3 = director[o][p];
                			if (nullchecker3 == null)
                			{
                				break;
                			}
                			System.out.print(director[o][p] + " ");
                			p++;
                		}*/
                		System.out.println();
                		o++;
                	}
                	
                	//break;
                }
                if (userchoice == 'q') {
                	myStack.deleteStack();
                	return;
                }
                if (userchoice == 'r') {
                    System.out.println("Restarting the game in one second");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                m = (int) userchoice;
                m = m - 96;

                if((int) userchoice< 96 || (int)userchoice > 109 )
                {
                    System.out.println("Option not possible, going to the first room");
                    room = 0;
                    m = 0;
                }
                navigator = director[room][m];

                while (navigator == null)
                {
                    System.out.println("Option not possible, going to the first room");
                    room = 0;
                    m = 0;
                    navigator = director[room][m];
                }

                n = 0;
                while (true) {
                    checker = messages[n][0];
                    if (navigator.equals(checker))
                        break;
                    n++;
                }
                room = n;
                
            }
        }
    }
    
    public static void mergeSort(String[] sort) {
        if (sort.length >= 2) {
            String[] first = new String[sort.length / 2];
            String[] second = new String[sort.length-(sort.length/2)];

            for (int i = 0; i < first.length; i++)
            {
                first[i] = sort[i];
            }
            for (int i = 0; i < second.length; i++)
            {
                second[i] = sort[i + sort.length / 2];
            }

            mergeSort(first);
            mergeSort(second);

            merge(sort, first, second);
        }
    }
	//portion of mergesort that will merge the arrays back together into alphabetical order
    public static void merge(String[] finish, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < finish.length; i++) {
        	 if (b >= right.length || (a < left.length &&
                     left[a].compareToIgnoreCase(right[b])<0)) {
                      finish[i] = left[a];
                      a++;
                 } else {
                      finish[i] = right[b];
                      b++;
                 }
            }
    }
	
}



class Calc {
private int count;
private int[] stacker = new int[101];
    // Constructor
    public Calc() {
    	count = 0;
    }

    // Push a number
    public void push(int x) {
        if (count > 101)
        {
            throw new RuntimeException();
        }
        count++;
        stacker[count] = x;
       }

    public void deleteStack()
    {
    	int temp = count;
    	for (int i = 0; i < temp; i++)
    	{
    		--count;
    	}
    	
    }
    // Pop top number (removes)
    public int pop() {
        if (count == 0)
        {
            throw new RuntimeException();
        }
    	int temppop = stacker[count];
    	stacker[count] = 0;
    	count--;
        return temppop;        
    }

    public int peek() {
        if (count == 0)
        {
            throw new RuntimeException();
        }
        return stacker[count];
    }

    // Add top two numbers
    public void add() {
    	 if (count < 2)
         {
             throw new RuntimeException();
         }
       int tempresult1;
       int tempadd1 = stacker[count];
       int tempadd2 = stacker[count-1];
       tempresult1 = tempadd1 + tempadd2;
       stacker[count] = 0;
       count--;
       stacker[count] = tempresult1;
    }

    // Subtract top two numbers (top on right side)
    public void subtract() {
        int tempsub1;
        int tempsub2;
        int tempresult2;
        if (count < 2)
        {
            throw new RuntimeException();
        }
        tempsub1 = stacker[count];
        tempsub2 = stacker[count-1];
        tempresult2 = tempsub2 - tempsub1;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult2;
    }

    // Multiply top two numbers
    public void multiply() {
        int tempmult1;
        int tempmult2;
        int tempresult3;
        if (count < 2)
        {
        	throw new RuntimeException();
        }
        tempmult1 = stacker[count];
        tempmult2 = stacker[count-1];
        tempresult3 = tempmult1 * tempmult2;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult3;
    }

    // Divide top two numbers (top on bottom)
    public void divide() {
        int tempdiv1;
        int tempdiv2;
        int tempresult4;
        if (count < 2)
        {
        	throw new RuntimeException();
        }
        tempdiv1 = stacker[count];
        tempdiv2 = stacker[count-1];
        tempresult4 = tempdiv2/tempdiv1;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult4;
    }

    // Return how many numbers are in the stack
    public int depth() {
        return count;
    }
    
    //Return the reciprocal of the top element in the stack
    public void reciprocal() {
    	int temptop1 = stacker[count];
    	int temptop2 = 1/temptop1;
    	stacker[count] = temptop2;
    }
}