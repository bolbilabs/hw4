/**
* I used a checked Exception because the
* InvalidSquareException class is-a Throwable. In addition,
* try-catch blocks are used in the homework tester, so
* using a checked Exception seemed appropriate.
*
* @author dfriedman32
*/
public class InvalidSquareException extends Throwable {
    public InvalidSquareException(String square) {
        super(square);
    }
}
