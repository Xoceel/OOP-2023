package ie.tudublin;

import processing.core.PApplet;

public class LifeBoard {
    boolean[][] board;
    boolean[][] next;
    
    
    private int size;
    PApplet p;


    float cellWidth;

    public boolean getCell(int row, int col)
    {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            return board[row][col];
        }
        else
        {
            return false;
        }
    }

    public int countCells(int row, int col)
    {
        int count = 0 ;
        for(int i = -1 ; i <= 1 ; i ++)
        {
            for (int j = -1 ; j <= 1 ; j ++)
            {
                if(j == 0 && i == 0){
                    continue;
                }

                if (getCell(row + i, col + j))
                {
                    count ++;
                }
            }
        } 
        return count;
    }

    public void applyRules()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                int count = countCells(row, col);
                if (board[row][col])
                {
                    if (count == 2 || count == 3)
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                    
                }
                else
                {
                    if (count == 3)
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                }
                // < 2 > 3 dies
                // 2-3 survices
                // dead with 3 neighboiurs comes to life
            }
        }
        boolean[][] temp;
        temp = board;
        board = next;
        next = temp;
    }

    public LifeBoard(int size, PApplet p)
    {
        this.size = size;
        board = new boolean[size][size];
        next = new boolean[size][size];
        this.p = p;
        cellWidth = p.width / (float) size;
    }

    public void randomise()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float dice = p.random(0.0f, 1.0f);
                board[row][col] = (dice <= 0.5f);
            }
        }
    }

    public void render()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float x = col * cellWidth;
                float y = row * cellWidth;

                if (board[row][col])
                {
                    p.fill(0, 255, 0);
                }
                else
                {
                    p.fill(0);
                }
                p.rect(x, y, cellWidth, cellWidth);
                //p.textSize(cellWidth - (size / (cellWidth/2)));

                //p.text("KYS", row*cellWidth, col*cellWidth);
            }
        }
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    } 

    public void kill() {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++) {
                board[row][col] = false;
            }
        }
    }

    public void cross() {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++) {
                if (row == size/2 || col == size/2) {
                    board[row][col] = true;
                }
            }
        }

    }

    public void dragDraw(int y, int x) {
        board[(int)(x/cellWidth)][(int)(y/cellWidth)] = true;
    }

    public void gliderDraw(int y, int x) {
        int rowPos = (int)(x / cellWidth);
        int colPos = (int)(y / cellWidth);

        for(int i = -1 ; i <= 1 ; i ++)
        {
            for (int j = -1 ; j <= 1 ; j ++)
            {
                if ((i == -1 && j == 0) || (i == 0 && j == 1) || i == 1) {
                    board[i + rowPos][j + colPos] = true;
                }
            }
        }
        
    }

    public void gosperGunDraw(int x, int y) {
        int rowPos = (int)(x / cellWidth);
        int colPos = (int)(y / cellWidth);
        int[] xCoords = {0, 0, 1, 1, 10, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 14, 15, 15, 16, 16, 16, 17, 20, 20, 20, 21, 21, 21, 22, 22, 24, 24, 24, 24, 34, 34, 35, 35};
        int[] yCoords = {4, 5, 4, 5, 4, 5, 6, 3, 7, 2, 8, 2, 8, 1, 9, 6, 3, 7, 4, 5, 6, 5, 2, 3, 4, 2, 3, 4, 1, 5, 0, 1, 5, 6, 2, 3, 2, 3};

        if(((size - rowPos) > 36) && ((size - colPos) > 9)) {
            for(int i = 0; i<=36; i++) {
                board[((int)((yCoords[i] * cellWidth) + y) / (int)cellWidth)][(int)(((xCoords[i] * cellWidth) + x) / (int) cellWidth)] = true;
            }
        }
    } 

    public boolean pausePlay(boolean looping) {
        if (looping) {
            p.noLoop();
            return false;
        } else p.loop(); return true;
    }

    public void displayFunctions() {
        System.out.println("Press Keys To Use:\n1 - Rerandomise Board\n2 - Clear Board\n3 - Draw Cross\n\ne - Cycle Mouse Effects:\nClick For Glider\nClick For Gosper Gun\nRessurection At Cursor");
    }
}
