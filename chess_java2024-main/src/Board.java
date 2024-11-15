import Figures.*;

public class Board {

    private char colorGame;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public  char getColorGame(){
        return colorGame;
    }


    private Figure fields[][] = new Figure[8][8];


    public void init(){
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),
                new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),new Pawn("P", 'w'),
        };
        this.fields[6] = new Figure[] {
                new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),
                new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b'),new Pawn("P", 'b')
        };
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("k", 'w'), new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'), new Knight("k", 'w'), new Rook("R", 'w')
        };
        this.fields[7]= new Figure[]{
                new Rook("R", 'b'), new Knight("k", 'b'), new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'), new Knight("k", 'b'), new Rook("R", 'b')
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure ==null){
            return "    ";
        }
        return  " "+figure.getColor()+figure.getName()+" ";
    }
    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1 ; row --){
            System.out.print(row);
            for (int col=0; col<8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col=0; col< 8; col++){
            System.out.print("    "+col);
        }
    }

    public boolean move_figure(int row, int col, int row1, int col1){
      Figure figure = this.fields[row][col];
      if (figure != null && figure.canMove(row, col, row1, col1) && this.figure_on_way(row, col, row1, col1)
              && this.fields[row1][col1] == null && figure.getColor() == this.colorGame && this.check_SHAH(row, col, row1, col1)){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }
      else  if (figure.canAttack(row, col, row1, col1) && this.figure_on_way(row, col, row1, col1) && this.fields[row1][col1] != null
              && this.fields[row1][col1].getColor() != this.fields[row][col].getColor() && this.check_SHAH(row, col, row1, col1)){
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }
      return false;
    }

    private boolean figure_on_way(int row, int col, int row1, int col1){
        Figure figure = this.fields[row][col];
        if(figure != null && figure.canMove(row, col, row1, col1) && figure.getColor() == this.colorGame){
            if (figure.getName()=="B" || figure.getName()=="Q"){
                for (int i=Math.min(col, col1)+1; i<Math.max(col, col1); i++){
                    for(int j=Math.min(row, row1)+1; j<Math.max(row, row1); j++){
                        if(Math.abs(i-col)==Math.abs(j-row) && this.fields[j][i] != null){
                            System.out.print("Мешает фигура!!!!!");
                            return false;
                        }
                    }
                }
            }
            if (figure.getName()=="R" || figure.getName()=="Q"){
                if (col==col1){
                    for(int j=Math.min(row, row1)+1; j<Math.max(row, row1); j++){
                        if(this.fields[j][col] != null){
                            System.out.print("Мешает фигура!!!!!");
                            return false;
                        }
                    }
                }
                if (row==row1){
                    for(int i=Math.min(col, col1)+1; i<Math.max(col, col1); i++){
                        if(this.fields[row][i] != null){
                            System.out.print("Мешает фигура!!!!!");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean check_SHAH (int row, int col, int row1, int col1){ //если шаха нет, то возвращает true
        Figure figure = this.fields[row][col];
        if (figure != null && (figure.canAttack(row, col, row1, col1) || figure.canMove(row, col, row1, col1))
                && this.figure_on_way(row, col, row1, col1) && figure.getColor() == this.colorGame){
            for (int i=0; i<8; i++){ // ищем короля
                for (int q=0; q<8; q++){
                    if (this.fields[i][q] != null && this.fields[i][q].getName()=="K" && this.fields[i][q].getColor()==this.colorGame){
                        for (int w=0; w<8; w++){ // ищем фигуры, которые могут поставить шах
                            for (int e=0; e<8; e++){
                                if(this.fields[w][e].canAttack(w, e, i, q) && this.fields[w][e].getColor()!=this.colorGame){
                                    System.out.print("======ШАХ======");
                                    this.fields[row1][col1] = figure;
                                    this.fields[row][col] = null;
                                    for (int z=0; z<8; z++){
                                        for (int x=0; x<8; x++){
                                            if(this.fields[z][x].canAttack(z,x,i,q) && this.fields[z][x].getColor()!=this.colorGame){
                                                this.fields[row][col]=figure;
                                                this.fields[row1][col1]=null;
                                                System.out.print("!!!!!!!!!!!Нельзя ходить под шах!!!!!!!!!!!");
                                                return false;
                                            }
                                        }
                                    }
                                    this.fields[row][col]=figure;
                                    this.fields[row1][col1]=null;
                                }
                                else {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean check_MATE (int row, int col, int row1, int col1){ //если мата нет, то возвращает true
        Figure figure = this.fields[row][col];
        if (!this.check_SHAH(row, col, row1, col1) && figure != null && this.fields[row1][col1] == null){
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if (this.fields[i][j] != null && this.fields[i][j].getName()=="K" && this.fields[i][j].getColor()==this.colorGame){ //ищем короля
                        for (int q = Math.max(i - 1, 0); q <= Math.min(i + 1, 7); q++) {
                            for (int w = Math.max(j - 1, 0); w <= Math.min(j + 1, 7); w++) {
                                if (this.check_SHAH(i, j, q, w)){
                                    return true;
                                }
                            }
                        }
                    }
                    if (this.fields[i][j] != null && this.fields[i][j].getName()!="K" && this.fields[i][j].getColor()==this.colorGame){ //ищем фигуру "защитника"
                        for (int q=0; q<8; q++){
                            for (int w=0; w<8; w++){ //ищем ход для защиты короля
                                if (this.check_SHAH(i,j,q,w)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

        }
        System.out.print("_____ШАХ и МАТ_____");
        return false;
    }
}
