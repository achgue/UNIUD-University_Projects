int majority(vector<int> a){
  int v;
  int c;
  int i;
  for (i = 0, c = 0, v=INT_MIN; i < a.size(); i++) { //int_min intermpretato come indefinito
    if (c==0) { //so anche che v = INT_MIN
      v = a[i];
      c=1;
    }else if(a[i] == v){ //so che v != INT_MIN
      c++;
    }else{ //so che v != a[i]
      c--;
      if(c==0)
        v = INT_MIN;
    }
  }
  //v è il candidato magioritario, verifico che effettivamente ci sia
  for (i = 0; i < a.size(); i++) {
    if (a[i] == v) {
      c++;
    }
    if(c > a.size()/2)
      return v;
    else
      return INT_MIN;
  }
}


/* L'idea per l'invariante su a,v,c
 *---------------------------------
 *v = maj(b) e c = #ricorrenzeV(b) per un opportuno vettore b ottenuto dall'eliminazione da a dei valori
 *
 *Es siamìno a e b due vettori di interi
 *b ç a quando b può essere ottenuto da a eliminando da una qualunque coppia di occorenze di valori diversi ed eventualmente iterando
 * a = 1 2 3 1 5 e b = 5 1 allora posso dire b ç a
 * Quello che può succedere è che a non ha un elemento magioritario ma che b con opportune eliminazioni lo abbia
 *
 *INVARIANTE
 *
 *Definiti a,v,c,i
 *∃b ç a tale che
 *   -maj(b) = v
 *   -#numeroRicVinB = c
*/
