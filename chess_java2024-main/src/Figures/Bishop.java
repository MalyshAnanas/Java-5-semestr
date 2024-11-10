package Figures;

public class Bishop extends Figure{
    public Bishop (String name, char color) {
        super(name, color);
    }
    @Override
    public boolean canMove(int row, int col, int row1, int col1) { // строка, солбец
        if (Math.abs(col - col1) == Math.abs(row - row1)) {
            return true;
        }
        return false;
    }
}
