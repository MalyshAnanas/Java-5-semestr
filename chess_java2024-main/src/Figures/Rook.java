package Figures;

public class Rook extends Figure{
    public Rook (String name, char color) {
        super(name, color);
    }
    @Override
    public boolean canMove(int row, int col, int row1, int col1) { // строка, солбец
        if (Math.abs(col - col1) == 0 || Math.abs(row - row1) == 0) {
            return true;
        }
        return false;
    }
}
