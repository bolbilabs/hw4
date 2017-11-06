/**
* Represents a square on the chess board.
*
* @author dfriedman32
*/
public class Square {
    private char file;
    private char rank;
    private String name;
    /**
    * Creates a Square with all required parameters.
    *
    * @param file the horizontal location on a chess board, a-h
    * @param rank the vertical location on a chess board, 1-8
    */
    public Square(char file, char rank) throws InvalidSquareException {
        if (file < 'a' || file > 'h'
            ||
            rank < '1' || rank > '8') {
            throw new InvalidSquareException("" + file + rank);
        } else {
            this.file = file;
            this.rank = rank;
            name = "" + file + rank;
        }
    }
    /**
    * Creates a Square with all required parameters.
    *
    * @param name the combination of both file and rank, respectively.
    */
    public Square(String name) throws InvalidSquareException {
        if (name.charAt(0) < 'a' || name.charAt(0) > 'h'
            ||
            name.charAt(1) < '1' || name.charAt(1) > '8') {
            throw new InvalidSquareException(name);
        } else {
            this.name = name;
            file = name.charAt(0);
            rank = name.charAt(1);
        }
    }
    /**
    * @return Square's rank and file combined as a string.
    */
    public String toString() {
        return name;
    }
    /**
    * @return Square's file as a char.
    */
    public char getFile() {
        return file;
    }
    /**
    * @return Square's rank as a char.
    */
    public char getRank() {
        return rank;
    }

    /**
    * Takes in parameter and removes all moves outside of board boundaries
    * a-h and 1-8 for rank and file respectively.
    *
    * @param allSquares all squares, including illegal squares
    *
    * @return array containing only legal squares
    */
    public Square[] legalCheck(Square[] allSquares) {
        //Initializes Array Size
        int counter =  0;
        for (int i = 0; i < allSquares.length; i++) {
            if (allSquares[i].file < 'a'
                ||
                allSquares[i].file > 'h'
                ||
                allSquares[i].rank < '1'
                ||
                allSquares[i].rank > '8') {
                counter++;
            }
        }
        //Places each legal Square into Array
        int counter2 =  0;
        Square[] squareList =  new Square[allSquares.length - counter];
        for (int i = 0; i < allSquares.length; i++) {
            if (!(allSquares[i].file < 'a'
                ||
                allSquares[i].file > 'h'
                ||
                allSquares[i].rank < '1'
                ||
                allSquares[i].rank > '8')) {
                squareList[i - counter2] = allSquares[i];
                //System.out.println(squareList[i-counter2]);
            } else {
                counter2++;
            }
        }
        return squareList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square that = (Square) other;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return (int) this.rank * 13 + this.file * 17;
    }
}
