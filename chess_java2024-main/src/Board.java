import Figures.*;

public class Board {

    boolean Mate = false;

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
      if (figure != null && figure.canMove(row, col, row1, col1) && this.figure_on_way(row, col, row1, col1) && this.fields[row1][col1] == null
              && figure.getColor() == this.colorGame && this.check_SHAH(row, col, row1, col1) && this.figure_on_way(row, col, row1, col1)){
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
        Figure temp = this.fields[row1][col1];
        if (figure != null && (figure.canAttack(row, col, row1, col1) || figure.canMove(row, col, row1, col1))
                && this.figure_on_way(row, col, row1, col1) && figure.getColor() == this.colorGame){
            for (int i=0; i<8; i++){ // ищем короля
                for (int q=0; q<8; q++){
                    if (this.fields[i][q] != null && this.fields[i][q].getName()=="K" && this.fields[i][q].getColor()==this.colorGame){
                        this.fields[row1][col1] = figure;
                        this.fields[row][col] = null;
                        for (int z=0; z<8; z++){ //ищем фигуру, которая может поставить шах
                            for (int x=0; x<8; x++){
                                if(this.fields[z][x].canAttack(z,x,i,q) && this.fields[z][x].getColor()!=this.colorGame){
                                    this.fields[row][col]=figure;
                                    this.fields[row1][col1]=temp;
                                    System.out.print("!!!!!!!!!!!Нельзя ходить под шах!!!!!!!!!!!");
                                    return false;
                                }
                            }
                        }
                        this.fields[row][col]=figure;
                        this.fields[row1][col1]=temp;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check_MATE (){ //если мата нет, то возвращает true
        int kingR = -1;
        int kingC = -1;
        for (int i=0; i<8; i++) { // ищем нашего короля
            for (int j = 0; j < 8; j++) {
                if (this.fields[i][j] != null && this.fields[i][j].getName() == "K" && this.fields[i][j].getColor() == this.colorGame) {
                    kingR = i;
                    kingC = j;
                    for (int p=0; p<8; p++){ //ищем фигуру, которая может поставить шах
                        for (int u=0; u<8; u++){
                            if (fields[p][u] != null && fields[p][u].getColor() != colorGame && fields[p][u].canAttack(p, u, i, j) && figure_on_way(p, u, i, j)){ // если кто-то угрожает нашему королю
                                for (int q = Math.max(i - 1, 0); q <= Math.min(i + 1, 7); q++) { //ищем ход для короля
                                    for (int w = Math.max(j - 1, 0); w <= Math.min(j + 1, 7); w++) {
                                        if (fields[q][w] == null || fields[q][w] != null && fields[q][w].getColor() !=colorGame) {

                                            boolean flag = false;
                                            Figure temp = fields[q][w];
                                            Figure king = fields[i][j];
                                            fields[q][w] = king;
                                            fields[i][j] = null;
                                            for (int k = 0; k < 8; k++) {
                                                for (int m = 0; m < 8; m++) { // ищем фигуру, которая может угрожать королю
                                                    if (this.fields[k][m] != null && this.fields[k][m].getColor() != this.colorGame && fields[k][m].canAttack(k, m, q, w) && figure_on_way(k, m, q, w)){
                                                        flag = true;
                                                    }
                                                    if (flag) {
                                                        break;
                                                    }
                                                }
                                                if (flag) {
                                                    break;
                                                }
                                            }
                                            fields[i][j] = king;
                                            fields[q][w] = temp;
                                            if (!flag) {
                                                return true; // король может убежать
                                            }
                                        }
                                    }
                                }
                                for (int q=0; q<8; q++){
                                    for (int w=0; w<8; w++){ // ищем фигуру для защиты короля
                                        if (fields[q][w] != null && fields[q][w].getColor() == colorGame && fields[q][w].getName() != "K") {
                                            for (int k = 0; k < 8; k++) {
                                                for (int m = 0; m < 8; m++) { // ищем ход для защиты короля
                                                    Figure figure = fields[q][w];
                                                    if  (figure != null && (figure.canAttack(q, w, k, m) && fields[k][m] == null || figure.canMove(q, w, k, m) && fields[k][m] != null && fields[k][m].getColor() != colorGame)
                                                            && this.figure_on_way(q, w, k, m) && figure.getColor() == this.colorGame) {
                                                        Figure temp = fields[k][m];
                                                        Figure new_figure = fields[q][w];
                                                        fields[k][m] = new_figure;
                                                        fields[q][w] = null;
                                                        boolean flag = false;
                                                        for (int a = 0; a < 8; a++) {
                                                            for (int s = 0; s < 8; s++) { // ищем фигуру, которая может угрожать королю
                                                                if (this.fields[a][s] != null && this.fields[a][s].getColor() != this.colorGame && !fields[a][s].canAttack(a, s, i, j) && figure_on_way(a, s, i, j)){
                                                                    flag = true;
                                                                }
                                                                if (flag) {
                                                                    break;
                                                                }
                                                            }
                                                            if (flag) {
                                                                break;
                                                            }
                                                        }
                                                        fields[q][w] = new_figure;
                                                        fields[k][m] = temp;
                                                        if (!flag) {
                                                            return true; // короля можно защитить
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j] != null && fields[i][j].getColor() != colorGame && fields[i][j].canAttack(i, j, kingR, kingC) && figure_on_way(i, j, kingR, kingC)){
                    System.out.println("_____ШАХ и МАТ_____"); // если не нашли ходов для защиты от мата
                    return false;
                }
            }
        }
        return true; // не нашли мат
    }
}
