# Somministrazione di vaccini
Una Regione deve gestire la somministrazione di vaccini alla popolazione per una gamma di diverse patologie.
Per ciascuna patologia, ci sono 1 o più farmaci che possono essere usati come vaccini.
Ciascuna vaccinazione a una persona avviene a una certa data; il dosaggio del farmaco dipende dall’età della persona. Per ciascuna patologia, ogni persona può essere soggetta a 1 o più vaccinazioni successive, con lo stesso o farmaci diversi.
Per ciascuna patologia, ci sono varie regole relative alle campagne vaccinali.
1. Una regola riguarda l’età minima sotto la quale non è ammissibile la somministrazione di uno specifico farmaco.
2. Un’altra regola riguarda
    * la distanza minima e massima tra vaccinazioni successive alla stessa persona
    *  i dosaggi che dipendono dall’età (<6 anni, tra 6 e 12, da 12 a 18, > 18).
3. Una regola determina le possibili compatibilità tra farmaci diversi da una
   vaccinazione alla successiva.
4. Una regola determina anche la durata ufficiale dell’efficacia di una sequenza di vaccinazioni a una persona.

Si necessita di un'API che consenta d'implementare queste operazioni principali (segnature solo indicative):
* **elencaPersoneVaccinate()**, che consente di estrarre le persone e produrre i dati delle vaccinazioni effettuate (patologia, date, farmaco, dosaggio)
* **copertura()**, che data una persona e una patologia, consente di dire quale sia la durata della copertura vaccinale e quando sarebbe opportuno che la persona dovrebbe sottoporsi a una vaccinazione
* **configuraRegola()**, per poter specificare i dettagli di ciascuna delle regole esistenti (es. per l’età minima, dosaggi, durate, …).

Scrivere un main che contenga chiamate a ciascuno di questi metodi.

Considerare che nuove regole possono essere frequentemente introdotte.

# Definizione della struttura
## Classi principali
* Persona
  * nome
  * cognome
  * data di nascita (da cui calcoliamo l'età) --> sarebbe meglio inserire il codice fiscale e fare gli opportuni calcoli con quello
    * età (la calcoliamo noi)
  * patologia (forse)
  * vaccinazione (forse)
  * Farmaco < Vaccino
    * nome
    * list< Patologia > che cura
    * dosaggio (array o lista di 4 elementi per indicare le fasce di età)
    * durataEffetto
    * list< Farmaco > con i farmaci incompatibili con quel farmaco
    * disponibilità
  * Vaccinazione
    * Persona
    * Vaccino
    * Luogo
    * data
    * //dose
  * Patologia
    * nome
  * Luogo
    * nome
    * List< Disponibilità > del farmaco che possono essere somministrati in quel luogo
  * Disponibilità
    * Farmaco
    * Quantità
  * registroVaccinazioni: una persona dopo aver prenotato una vaccinazione è inserita nel registro
    * list di persone
    * 
    * ci sono degli stati per la persona
      * vaccinata
      * in attesa di vaccinazione (prenotata)
      * ---
  * Regola

## Metodi API
* **elencaPersoneVaccinate(registroVaccinazioni)**
    * Accede al registro delle persone e riproduce una lista solo delle persone che hanno già effettuato la vaccinazione
* **copertura(persona, patologia)**
  * torna la copertura vaccinale della persona (devo in qualche modo fare verifiche sull'età e sul fatto che possa essere già vaccinata o meno)
  * verifiche sui farmaci assunti dalla persona per controllare la compatibilità con la vaccinazione
  * info su una possibile data di vaccinazione (posso tornare un oggetto di tipo vaccinazione con le possibili informazioni) --> builder
* **configuraRegola(patologia.regola, etàMin, intervalloVaccinazioni, dosaggi, incompatibilitàFarmaci, durata)**
  * potrebbe essere un builder che modifica le informazioni della regola --> la regola può essere un oggetto in tal modo uso un builder per modificarla
  * anche il dosaggio può essere un oggetto e ho un campo per età
