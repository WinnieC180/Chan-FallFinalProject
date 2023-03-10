public class Board {

    private static String[][] board = new String[][] { // 2d array [][] the first bracket is the row and the second bracket is column
            {" _", "0_", "__", "1_", "__", "2_", "__", "3_", "_", "_4", "_ "},
            {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 0"},
            {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
            {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 1"},
            {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
            {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 2"},
            {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
            {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 3"},
            {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
            {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 4"},
            {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
            {" »»", "——", "——", "——", "", " ✼ ", "", "——", "——", "——", "«« "},
    };

    public Board() {
    }

    public static void clearBoard() { // clears the board before the next X moves.
        board = new String[][] {
                {" _", "0_", "__", "1_", "__", "2_", "__", "3_", "_", "_4", "_ "},
                {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 0"},
                {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
                {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 1"},
                {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
                {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 2"},
                {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
                {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 3"},
                {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
                {"| ", " ", " | ", " ", " | ", " ", " | ", " ", " | ", " ", " | 4"},
                {"| ", "_", " | ", "_", " | ", "_", " | ", "_", " | ", "_", " | "},
                {" »»", "——", "——", "——", "", " ✼ ", "", "——", "——", "——", "«« "},
        };
    }

    public static void move(int x, int y) {
        int indexX = 0;
        int indexY = 0;

        switch(x) {
            case 0:
                indexX = 1;
                break;
            case 1:
                indexX = 3;
                break;
            case 2:
                indexX = 5;
                break;
            case 3:
                indexX = 7;
                break;
            case 4:
                indexX = 9;
                break;
        } // The switch method will determine where the user (represented by X) will go on the board
        switch(y) {
            case 0:
                indexY = 1;
                break;
            case 1:
                indexY = 3;
                break;
            case 2:
                indexY = 5;
                break;
            case 3:
                indexY = 7;
                break;
            case 4:
                indexY = 9;
                break;
        }

        board[indexX][indexY] = "X";
    }

    public static void printBoard() { //prints out the board row by row
        System.out.println();
        for (String[] row : board) {
            for (String coord : row) {
                System.out.print(coord);
            }
            System.out.println();
        }
    }
}