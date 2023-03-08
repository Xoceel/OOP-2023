package ie.tudublin;
import java.security.Key;

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

            System.out.println("asd");
            System.out.println("daslf");
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
        System.out.println("asdfl");
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
    
}
