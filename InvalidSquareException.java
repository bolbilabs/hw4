/**
* I changed this to an unchecked exception since the
* exceptions are more logic based and I don't have to worry
* about throws declaration and catch blocks.
*
* @author dfriedman32
* @version 1.1
*/
public class InvalidSquareException extends RuntimeException {
    /**
     * Creates a RuntimeException for out of bounds squares.
     * @param  square        out of bounds toString Square
     */
    public InvalidSquareException(String square) {
        super(square);
    }
}
