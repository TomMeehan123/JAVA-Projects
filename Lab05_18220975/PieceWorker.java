
/**
 * Write a description of class PieceWorker here.
 *
 * @author (Tom Meehan)
 * @version (18/02/19)
 * ID: 18220975
 */
public class PieceWorker extends Employee
{
  private double rate;
  private double pieces;

  public PieceWorker(String first, String last, String ssn, 
      double rate, double pieces )
  {
    super( first, last, ssn );
    this.rate = rate;
    this.pieces = pieces;
    
    
  } // end five-argument PiecesRate constructor
   
  public void setRate(double rate)
  {
    this.rate = rate; 
    
    if ( this.rate >= 0.0 )
         rate = this.rate;
      else
         throw new IllegalArgumentException( 
            "Rate must be >= 0.0" );
   
  } // End of setRate method
   
  public double getRate()
  {
    return rate;
  } // End of getRate method
  
  public void setPieces()
  {
    this.pieces = pieces;
  
    if ( this.pieces >= 0.0 )
         pieces = this.pieces;
      else
         throw new IllegalArgumentException( 
            "Pieces must be >= 0.0" );
   
  
  } // End of setPieces method
  
  public double getPieces()
  {
    return pieces;
  } // End of getPieces method
  
  public double earnings()
  {
    return pieces*rate;
    
  } // End of earnings method

  @Override
   public String toString()
   {
      return String.format( "Piece rate employee: %s\n%s: %,.2f; %s: %,.2f",  
         super.toString(), "rate", getRate(), 
         "pieces", getPieces() );
   } // end method toString
}



