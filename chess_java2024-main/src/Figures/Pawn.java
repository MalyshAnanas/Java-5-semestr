package Figures;

public class Pawn extends Figure {
    public Pawn(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (row==1 && this.getColor()=='w' || row==6 && this.getColor()=='b'){ //проверяем на первый ход
            if (((((row+2==row1)||(row+1==row1)) && this.getColor()=='w') ||
                    (((row-2==row1)||(row-1==row1)) && this.getColor()=='b') ) && (col==col1)){
                return true;
            }
        }
        else{
            if (((((row+1==row1)) && this.getColor()=='w') || (((row-1==row1)) && this.getColor()=='b'))&& (col==col1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if (Math.abs(row - row1) ==1 && Math.abs(col-col1)==1){
            return true;
        }
        return false;
    }
}