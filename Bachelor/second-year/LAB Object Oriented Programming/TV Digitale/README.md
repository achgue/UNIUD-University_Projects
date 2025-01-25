# TV digitale
***
Una TV digitale consente di poter vedere trasmissioni A/V rese disponibili da
diversi **canali** (ciascuno con un nome, un numero, e un elenco di trasmissioni);
nella TV un canale può essere associato a un **programma** (selezionato con un
numero sul telecomando).

Ogni **trasmissione**, _esclusiva di un canale_, ha un titolo, un eventuale genere 
(informazioni, film, documentario, show, sport, arte), un’eventuale scheda informativa, 
una lingua di default, un eventuale elenco di altre lingue, ciascuna lingua con eventuali
sottotitoli e infine date e ore in cui viene trasmessa.

La TV ha dei settaggi relativi al volume prescelto, alla luminosità, alla lingua
prescelta, al mostrare i sottotitoli se disponibili, alla visione 2-in-1 (in una parte
in grande dello schermo vedo e sento un programma, e in un riquadro ne vedo
un altro).
I canali disponibili, e le loro trasmissioni, vengono periodicamente aggiornati.

La TV dispone di un videoregistratore incorporato che funziona in automatico,
sulla base di un elenco di trasmissioni settate per essere registrate.

In una API per manipolare queste informazioni ci devono essere i metodi per le
seguenti operazioni:
- poter visionare un determinato programma (eventualmente con un secondo
programma se si attiva la modalità 2-in-1);
- poter cambiare o aggiungere le trasmissioni di un canale (solo quelle future)
- poter cercare una trasmissione (per titolo, canale, giorno, ora) tra quelle
future, e poterla settare per la registrazione in automatico, o poter verificare
se è già “prenotata” per la registrazione, o poter annullare la richiesta di
registrazione.

### Classi

- Canale:
  - nome
  - numero
  - elenco di trasmissioni
  - ######nella TV un canale può essere associato a un **programma** (selezionato con un numero sul telecomando)
- Trasmissione:
  - titolo
  - eventuale genere
    (informazioni, film, documentario, show, sport, arte)
  - eventuale scheda informativa
  - lingua di default
  - eventuale elenco di altre lingue
    - ciascuna lingua con eventuali sottotitoli
  - date e ore in cui viene trasmessa


- TV
    - Settaggi

- Settaggi 
  - volume prescelto
  - luminosità
  - lingua prescelta
  - mostrare i sottotitoli se disponibili
  - visione 2-in-1
  - ###### Con timer: I canali disponibili, e le loro trasmissioni, vengono periodicamente aggiornati.
  
- Videoregistratore
  - elenco di trasmissioni settate per essere registrate

### API
- Scegliere un programma da vedere(anche 2 in 1)
- cambiare/aggiungere trasmissioni di un canale **(solo quelle future)**
- cercare una trasmissione (per titolo, canale, giorno, ora) tra quelle
  future e:
  - settare per la registrazione in automatico;
  - verificare se è già “prenotata” per la registrazione;
  - poter annullare la richiesta di registrazione.
