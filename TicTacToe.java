import java.util.*;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static char[] board = new char[9];
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Arrays.fill(board, EMPTY);
        System.out.println("Tic-Tac-Toe (Java)");
        System.out.println("Choose mode: 1 = Two-player, 2 = Single-player (vs Computer)");
        int mode = readIntInRange(1, 2);
        char current = 'X';
        while (true) {
            printBoard();
            if (mode == 2 && current == 'O') {
                System.out.println("Computer's turn (O)...");
                computerMove();
            } else {
                System.out.println("Player " + current + " - enter position (1-9):");
                int pos = readIntInRange(1, 9) - 1;
                if (board[pos] != EMPTY) {
                    System.out.println("Cell occupied. Try again.");
                    continue;
                }
                board[pos] = current;
            }
            if (isWin(current)) {
                printBoard();
                System.out.println("Player " + current + " wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            current = (current == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    private static int readIntInRange(int lo, int hi) {
        while (true) {
            try {
                int x = Integer.parseInt(sc.nextLine().trim());
                if (x >= lo && x <= hi) return x;
            } catch (Exception e) {}
            System.out.println("Enter a valid number between " + lo + " and " + hi + ":");
        }
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.printf(" %c | %c | %c \n", display(board[i], i+1), display(board[i+1], i+2), display(board[i+2], i+3));
            if (i < 6) System.out.println("---+---+---");
        }
        System.out.println();
    }

    private static char display(char c, int idx) {
        return c == EMPTY ? Character.forDigit(idx, 10) : c;
    }

    private static boolean isWin(char p) {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // cols
            {0,4,8},{2,4,6}          // diags
        };
        for (int[] L : lines) {
            if (board[L[0]] == p && board[L[1]] == p && board[L[2]] == p) return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (char c : board) if (c == EMPTY) return false;
        return true;
    }

    private static void computerMove() {
        // Simple random AI: pick a random empty cell
        List<Integer> empty = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (board[i] == EMPTY) empty.add(i);
        if (empty.isEmpty()) return;
        int choice = empty.get(new Random().nextInt(empty.size()));
        board[choice] = 'O';
    }
}
