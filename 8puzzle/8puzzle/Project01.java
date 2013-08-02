// Written by Kevin Tuan
//
// CS420 --- Project 01 8 Puzzle
//
import java.util.Vector;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math;
import java.util.Date;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Puzzle
{
    private int[] pieces = new int[9];
    private boolean manhattan = true;       //Type of heuristic function
    private int depth;                                 //Number of moves made so far
    private int parentID=-1;                      //Parent ID for node tracking
//---------------------------------------------------------------------------------
    public Puzzle(int move) {generate(move);}
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan)
    {
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan, int depth)
    {
        this.depth = depth;
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan, int depth, int parentID)
    {
        this.depth = depth;
        this.parentID = parentID;
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(Puzzle puzzle)
    {
        pieces = puzzle.copyArray();
        manhattan = puzzle.isManhattan();
        depth = puzzle.getDepth();
        parentID = puzzle.getParentID();
    }
//---------------------------------------------------------------------------------
    // Generates a fresh set of squares by moving the blank x number of times
    public void generate(int move)
    {
        // create a correct puzzle
        for (int i=0; i<9; i++)
            pieces[i] = i;
        // randomly move the puzzle (25 times for this project)
        for (int j=0; j<move; j++)
        {
            // pick a random action
            Random rand = new Random();
            int choice = rand.nextInt(4)+1;
            switch (choice)
            {
                case 1:
                    pieces = up(pieces);
                    break;
                case 2:
                    pieces = down(pieces);
                    break;
                case 3:
                    pieces = left(pieces);
                    break;
                case 4:
                    pieces = right(pieces);
                    break;
            }
        }
    }
//---------------------------------------------------------------------------------
    private int[] up(int[] puzzleArray)
    {
        int i;
        int[] temp = new int[puzzleArray.length];
        int holder;
        for (i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (i=0; i<temp.length; i++)
        {
            if (temp[i] == 0)
                square = i;
        }
        // invalid option check
        if (square < 3)
            return temp;
        holder = temp[square];
        temp[square] = temp[square-3];
        temp[square-3] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] down(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square > 5)
            return temp;
        holder = temp[square];
        temp[square] = temp[square+3];
        temp[square+3] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] left(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square%3 == 0)
            return temp;
        holder = temp[square];
        temp[square] = temp[square-1];
        temp[square-1] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] right(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square%3 == 2)
            return temp;
        holder = temp[square];
        temp[square] = temp[square+1];
        temp[square+1] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    public String toString()
    {
        String out = new String("===============\n");
        for (int i=1; i<=9; i++) {
            if ((i%3) == 1)
                out += " | ";
            if (pieces[i-1] == 0)
                out += "  | ";
            else
                out += pieces[i-1]+" | ";
            if ((i%3) == 0)
                out += "\n===============\n";
        }
        return out;
    }
//---------------------------------------------------------------------------------
    public boolean equals(Puzzle p)
    {
		return (p.toString().equals(toString()));
	}
//---------------------------------------------------------------------------------
    public boolean isManhattan()  {return manhattan;}
//---------------------------------------------------------------------------------
    public int getDepth() {return depth;}
//---------------------------------------------------------------------------------
    public int getParentID() {return parentID;}
//---------------------------------------------------------------------------------
    public void setDepth(int depth) {this.depth = depth;}
//---------------------------------------------------------------------------------
    // Set Heuristic type
    public void setHeuristic(boolean isManhattan)
    {
        manhattan = isManhattan;
    }
//---------------------------------------------------------------------------------
    // Get Heuristic based on type
    public int getH()
    {
        if (manhattan)
           return getmanhattanH();
        else
           return getOtherH();
    }
//---------------------------------------------------------------------------------
    // Set Non-manhattan Heuristic
    private int getOtherH()
    {
        // count the number of squares in the wrong place
        int wrong = 0;
        for (int i=0; i<9; i++)
            if (pieces[i] != i)
                wrong++;
        return wrong;
    }
//---------------------------------------------------------------------------------
    // Set manhattan Heuristic
    private int getmanhattanH()
    {
        int h = 0;
        int xs, xg, ys, yg;
        for (int i=0; i<9; i++)
        {
            // breaks the game locations into a grid formation
            // measures the distance using grid length.
            xg = i%3;
            yg = i/3;
            xs = pieces[i]%3;
            ys = pieces[i]/3;
            h += Math.abs(xs-xg) + Math.abs(ys-yg);
        }
        return h;
    }
//---------------------------------------------------------------------------------
    // Add to the cost
    public void addMove() { depth++;}
//---------------------------------------------------------------------------------
    public int[] copyArray()
    {
        int[] temp = new int[9];
        for (int i=0; i<9; i++)
            temp[i] = pieces[i];
        return temp;
    }
//---------------------------------------------------------------------------------
    public int[] up() { return up(pieces);}
//---------------------------------------------------------------------------------
    public int[] down() { return down(pieces);}
//---------------------------------------------------------------------------------
    public int[] left() { return left(pieces);}
//---------------------------------------------------------------------------------
    public int[] right() {return right(pieces);}
//---------------------------------------------------------------------------------
} // end class Pazzle
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Solver
{
    private int cost = 0;
    private Vector usedList;
    private Vector fringeList;
    private Puzzle p;
//---------------------------------------------------------------------------------
    public Solver(int depth)
    {
        usedList = new Vector();
        fringeList = new Vector();
        p = new Puzzle(depth);
        fringeList.add(p);
    }
//---------------------------------------------------------------------------------
    public Solver(Puzzle puzzle)
    {
        usedList = new Vector();
        fringeList = new Vector();
        p = new Puzzle(puzzle);
        fringeList.add(p);
    }
//---------------------------------------------------------------------------------
    public void solve()  {while (expandNodes(p)) {p=findBestNode();}}
//---------------------------------------------------------------------------------
    public int getCost() {return (usedList.size() + fringeList.size());}
//---------------------------------------------------------------------------------
    // Finds the best node in the list
    private Puzzle findBestNode()
    {
        Puzzle best;
        Puzzle test;
        // If the fringe is empty, grab the only node
        if (fringeList.size()==0)
            best = (Puzzle)usedList.elementAt(0);
        // Otherwise grab the first fringe node, set it for comparison.
        else
            best = (Puzzle)fringeList.elementAt(0);
        for (int i=1; i<fringeList.size();i++)
        {
            test = (Puzzle)fringeList.get(i);
            // Get Heuristic Value for Node
            int bestH = best.getDepth() + best.getH();
            int testH = test.getDepth() + test.getH();
            // If it is lower, change "Best"
            if (testH < bestH)
                best = test;
        }
        return best;
    }
//---------------------------------------------------------------------------------
    private boolean expandNodes(Puzzle p)
    {
        int[] test;
        Puzzle temp;
        // take Node off of fringe
        fringeList.remove(p);
        // add it to used list, for GRAPH-SEARCH
        int parentID = usedList.size();
        usedList.add(p);
        // if Heuristic is 0, done
        if (p.getH() == 0)
            return false;
        // else test all directions
        test = p.up();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.down();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.left();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.right();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        return true;
    }
//---------------------------------------------------------------------------------
    private void addToFringe(Puzzle p)
    {
        // no repeat nodes
        if (!nodeRepeated(p))
        {
            p.addMove();
            fringeList.add(p);
        }
    }
//---------------------------------------------------------------------------------
    private boolean nodeRepeated (Puzzle node)
    {
        for (int i=0; i<usedList.size(); i++)
        {
            if (node.equals((Puzzle)usedList.get(i)))
                return true;
        }
        return false;
    }
//---------------------------------------------------------------------------------
    public void print()
    {
        Vector list = new Vector();
        // get solution
        Puzzle node = getPuzzleSolution();
        // add to answer list
        list.add(node);
        // find parent node
        int parentID = node.getParentID();
        while (parentID != -1)
        {
            node = (Puzzle)usedList.elementAt(parentID);
            list.add(node);
            parentID = node.getParentID();
        }

        for (int i = 1; i <= list.size();  i++)
        {
			if (i ==1)
			{
				System.out.println("Original:");
				System.out.println((Puzzle)list.elementAt(list.size()-i));
			}
			else
			{
				System.out.println("Step " + ( i -1)+ ":");
				System.out.println((Puzzle)list.elementAt(list.size()-i));
			}
	    }
        System.out.println("\nSolution with "+getPuzzleSolution().getDepth()+" step(s).");
    }
//---------------------------------------------------------------------------------
    public Puzzle getPuzzleSolution()
    {
        return (Puzzle)usedList.lastElement();
    }
//---------------------------------------------------------------------------------
} // end class Solver
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Project01
{
//---------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        Solver puzzle = new Solver(25);
        puzzle.solve();
        puzzle.print();
    }
//---------------------------------------------------------------------------------
} // end class Project01
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//For complexity Computation

class Computation
{
    private int[] counter;                   // Array to track each depth count
    private int[] timer;
    private int[] cost;
    private int max;                          // max depth
    private int min = 0;                    // min depth
    private int num = 10;                 // # of each solution to get
    private int size = 75;                 // Base size to randomize by
//---------------------------------------------------------------------------------
    public Computation(int max)
    {
        this.max = max;
        counter = new int[max];
        timer = new int[max];
        cost = new int[max];
    }
//---------------------------------------------------------------------------------
    public Computation(int min, int max)
    {
        this.max = max;
        this.min = min;
        counter = new int[max];
        timer = new int[max];
        cost = new int[max];
    }
//---------------------------------------------------------------------------------
    // Runs enough puzzles to meet requirements
    public void run()
    {
        int i;
        do
        {
            Solver p = new Solver(size);
            Timer t = new Timer();
            t.start();
            p.solve();
            long time = t.end();
            Puzzle solution = p.getPuzzleSolution();

            if (solution.getDepth() <= max && (solution.getDepth()-min) > 0)
            {
                if (counter[(solution.getDepth()-1)-min] < num)
                {
                    counter[(solution.getDepth()-1)-min]++;
                    timer[(solution.getDepth()-1)-min] += time;
                    cost[(solution.getDepth()-1)-min] += p.getCost();
                }
            }
        } while (!checkCounter());
        System.out.println("Depth\tCost\tTime to Execute");
        for (i=0; i<(max-min); i++)
            System.out.println((i+1)+min+"\t"+(cost[i]/num)+"\t"+timer[i]/num);
    }
//---------------------------------------------------------------------------------
    // Returns true if Data is complete
    public boolean checkCounter()
    {
        int count = 0;
        for (int i=0; i<(max-min); i++)
        {
            count += counter[i];
        }
        return (count == ((max-min)*num));
    }
} // end class Computation
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Timer
{
    Date now;
    long start;
//---------------------------------------------------------------------------------
    public Timer() {}
//---------------------------------------------------------------------------------
    // Start timer
    public void start()
    {
        now = new Date();
        start = now.getTime();
    }
//---------------------------------------------------------------------------------
// End Timer
    public long end()
    {
        now = new Date();
        long end = now.getTime();
        return ((end-start));
    }
} // end class Timer
///////////////////////////////////////////////////////////////////////////////////////////////////////////////