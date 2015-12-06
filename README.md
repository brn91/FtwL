# FtwL

# Regeln
##Organisatorisches:
  1.	Das Biel ist 6 X 6 Felder groß.
  2.	Es gibt helle und dunkle Steine
  3.	Die ersten beiden Reihen der Spielseiten werden mit Steinen besetzt, aber nur die dunklen Felder.
  4.	Jeder Spieler besitzt 5 normale Spielsteine und eine Lady.
  5.	Der Spieler der dran ist kann das Spiel sofort beenden, verliert dann aber.
  6.	Es gibt 2 Modi: Human vs. Human und Human vs. Computer
    6.1.	Bei Human vs. Human wird das Spielfeld jede Runde für den jeweiligen Spieler gedreht. 
  7.	Die Eingabe Besteht aus Quelle zu Ziel (XYXY)
    7.1.	X= A-F, Y= 1-6 (Hier Spielfeld einfügen zum Verständnis)
    7.2.	Kleinbuchstaben werden Akzeptiert

###Spielablauf:
  1.	Es wird abwechselnd Gespielt
  2.	Der Spieler mit den dunklen Steinen beginnt
  3.	Es wird vorwärts diagonal gezogen

  4.	Es besteht Schlagpflicht:
    4.1.1.	Stehen 2 Steine diagonal gegenüber und das Feld dahinter ist frei, so muss 	geschlagen werden. Kann man anschließend noch              einen Stein schlagen, muss man 	dies auch machen.
    4.1.2.	Man muss auch rückwärts schlagen, sofern man das kann.
       4.2.	Für den Modus Human vs. Human gilt zusätzlich:
         4.2.1.	Wird die Schlagpflicht missachtet, so wird der Stein, der hätte 	
                schlagen müssen entfernt.
           4.2.2.	Der Computer weißt die Eingabe lediglich ab.
  5.	Man muss einen Zug durchführen und kann nicht passen.
    5.1.	Es gibt zusätzlich zur Zugpflicht ein Zeitlimit. Ist dies überschritten, so wird ein zufälliger Spielstein gelöscht.
  6.	Beim Erreichen der gegnerischen Grundlinie wird ein Spielstein zur Lady.
    6.1.	Die Lady kann ein oder mehrere Felder diagonal ziehen.
      6.2.	Die Lady zieht direkt hinter den geschlagenen Spielstein.
  7.	Gewonnen hat man, wenn der Gegner keine Spielsteine mehr besitzt oder er nicht mehr ziehen kann.
    7.1.	Wenn beide Spieler nur noch eine Lady besitzen, gibt es ein Unentschieden.
	
