public class StringSList {                    // Scheme-Like Lists of int


  // ----- Costante lista vuota (condivisa)
  
  public static final StringSList NULL_INTLIST = new StringSList();
  
  
  // ----- Rappresentazione interna di una lista: private!
  
  private final boolean empty;             // oggetti immutabili:
  private final String first;              // variabili di istanza "final"
  private final StringSList rest;
  
  
  // ----- Operazioni di base sulle liste, mutuate da Scheme
  
  public StringSList() {                      // creazione di una lista vuota
                                           // Scheme: null
    empty = true;
    first = "";                             // valore irrilevante in questo caso
    rest = null;
  }
  
  public StringSList( String e, StringSList sl ) {  // creazione di una lista non vuota:
                                           // Scheme: cons
    empty = false;
    first = e;
    rest = sl;
  }
  
  
  public boolean isNull() {                // verifica se una lista e' vuota
                                           // Scheme: null?
    return empty;
  }
  

  public String car() {                       // primo elemento di una lista
                                           // Scheme: car
    return first;                          // si assume: lista non vuota
  }
  
  
  public StringSList cdr() {                  // resto di una lista
                                           // Scheme: cdr
    return rest;                           // si assume: lista non vuota
  }
  
  
  // ----- Realizzazione alternativa (sostanzialmente equivalente) del "cons"
  
  public StringSList cons( String e ) {          // costruzione di nuove liste
                                           // Scheme: cons
    return new StringSList( e, this );
  }
  
  
  // ----- Operazioni aggiuntive, definite in termini dei precedenti metodi
  
  public int length() {                    // lunghezza di una lista
                                           // Scheme: length
    if ( isNull() ) {                      // oppure: this.isNull()
      return 0;
    } else {
      return ( 1 + cdr().length() );       // oppure:
    }                                      //   ( this.cdr() ).isNull()
  }
  
  
  public String listRef( int k ) {            // elemento in posizione k
                                           // Scheme: list-ref
    if ( k == 0 ) {
      return car();                        // oppure: this.car()
    } else {
      return ( cdr().listRef(k-1) );       // oppure:
                                           //   ( this.cdr() ).listRef(k-1)
    }
  }                                        // etc.: this. implicito
  
  
  public boolean equals( StringSList sl ) {   // contronto di liste
                                           // Scheme: equal?
    if ( isNull() || sl.isNull() ) {
      return ( isNull() && sl.isNull() ); //return true;
    } else if ( car() == sl.car() ) {
      return cdr().equals( sl.cdr() );
    } else {
      return false;
    }
  }
  
  
  public StringSList append( StringSList sl ) {  // fusione di liste
                                           // Scheme: append
    if ( isNull() ) {
      return sl;
    } else {
      // return new StringSList( car(), cdr().append(sl) );
      return ( cdr().append(sl) ).cons( car() ); //CHIEDERE SPIEGAZIONE
    }
  }
  
  
  public StringSList reverse() {              // rovesciamento di una lista
                                           // Scheme: reverse
        return reverseRec( new StringSList() ); //CHIEDERE SPIEGAZIONE
  }
  
  private StringSList reverseRec( StringSList re ) {
  
    if ( isNull() ) {                      // metodo di supporto: private!
      return re;
    } else {
      // return cdr().reverseRec( new IntSList(car(),re) );
      return cdr().reverseRec( re.cons(car()) );
    }
  }
  
  
  // ----- Rappresentazione testuale (String) di una lista
  
  public String toString() {               // ridefinizione del metodo generale
                                           // per la visualizzazione testuale
    if ( isNull() ) {
      return "()";
    } else {
      String rep = "(" + car();
      StringSList r = cdr();
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }


}  // class StringSList
