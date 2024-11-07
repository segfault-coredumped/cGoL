package pkgSlUtils;

import java.util.Random;

public class SlPingPongManager {
    // 2 arrays for live and next
    private int[][] liveArr;
    private int[][] nextArr;

    private final int rows;
    private final int cols;

    private final Random random;

    public void liveOrDie() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // for each value in PP get the NNN count
                int nnnValue = countNNN(i,j);

                // rules for alive squares
                // populate the next array based on the NNN of live array then swap at the end
                if(liveArr[i][j] == 1) {
                    if(nnnValue < 2 || nnnValue > 3) {
                        // dies
                        nextArr[i][j] = 0;
                    }
                    else {
                        // continues to live...
                        nextArr[i][j] = 1;
                    }
                }
                // rules for dead squares
                else {
                    if (nnnValue == 3) {
                        // resurrect
                        nextArr[i][j] = 1;
                    }
                    else {
                        // stay dead
                        nextArr[i][j] = 0;
                    }
                }
            }
        }
        // uncomment for debugging with pause
        //System.out.println("Live ARRAY");
        //showLiveArr();
        swapArr();
        // uncomment for debugging with pause
        //System.out.println("NNN-Of-Live ARRAY");
        //showLiveArr();
    }

    // constructors
    // A
    // A constructor that takes two integer arguments: for the number of rows
    // and number of columns. This should initialize the cells with a random
    // integer between {0, 1}.
    public SlPingPongManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.liveArr = new int[rows][cols];
        this.nextArr = new int[rows][cols];
        this.random = new Random();

        // build board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // fill with 0 or 1
                liveArr[i][j] = random.nextInt(2);
            }
        }
    }
    // Getter for rows
    public int getRows() {
        return rows;
    }

    // Getter for columns
    public int getCols() {
        return cols;
    }

    // D
    // void set(int my_arg) and int get() methods for the specified elements of the Live-array
    protected void setLiveArr(int rowNum, int colNum, int cellNum) {
        liveArr[rowNum][colNum] = cellNum;
    }

    public int get(int x, int y) {
        return liveArr[x][y];
    }

    public void setNextArr(int rowNum, int colNum, int cellNum) {
        nextArr[rowNum][colNum] = cellNum;
    }

    // E swap live and next
    public void swapArr() {
        // tmp for swap
        int[][] tmp = liveArr;
        liveArr = nextArr;
        nextArr = tmp;
    }

    // F
    // count nearest neighbor of specific cell
    public int countNN(int x, int y) {
        int count = 0;
        // we need to account for all cardinal directions

        // up
        if (get((x - 1 + rows) % rows, y) != 0) {
            count++;
        }
        // down
        if (get((x + 1) % rows, y) != 0) {
            count++;
        }
        // left
        if (get(x, (y - 1 + cols) % cols) != 0) {
            count++;
        }
        // right
        if (get(x, (y + 1) % cols) != 0) {
            count++;
        }

        return count;
    }


    // redundant for this assignment delete when needed
    public void fillNNNextArr() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setNextArr(i, j, countNN(i, j));
            }
        }
    }
    // count next nearest of a specific cell
    // 8 NNN so we need left right up down and up/left up/right down/left down/right
    // since NN covers the first 4 we can just call that in the method
    public int countNNN(int rowNum, int colNum) {
        // get the first 4
        int count = countNN(rowNum,colNum);

        // up/left
        if (get((rowNum - 1 + rows) % rows, (colNum - 1 + cols) % cols) != 0) {
            count++;
        }
        // up/right
        if (get((rowNum - 1 + rows) % rows, (colNum + 1) % cols) != 0) {
            count++;
        }
        // down/left
        if (get((rowNum + 1) % rows, (colNum - 1 + cols) % cols) != 0) {
            count++;
        }
        // down/right
        if (get((rowNum + 1) % rows, (colNum + 1) % cols) != 0) {
            count++;
        }
        return count;
    }

    // liveOrDie decides how to fill next array
    // redundant for this assignment delete when needed
    public void fillNNNNextArr() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setNextArr(i, j, countNNN(i, j));
            }
        }
    }

    // might need this for keystroke implimentations
    // reset board to random number between 2 integer values
    public void resetBoard(int lowerBound, int upperBound) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setLiveArr(i, j, random.nextInt(upperBound - lowerBound + 1) + lowerBound);
            }
        }
    }


    // keep for debugging
    public void showLiveArr() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(get(i, j) + " ");
            }
            System.out.println();
        }
    }
}
