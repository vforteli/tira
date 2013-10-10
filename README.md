tira
====
Miten ohjelma kääntyy:
Riippuen hakemistosta
javac.exe -d class -s src src/myproject/astar/*.java 

Miten ohjelma ajetaan:
java -jar "Astar.jar" tai java astar/AstarGUI
Tai sitten vain klikkaamalla jar-tiedostoa…
Ohjelma tarvitsee myös bitmapin joka toimii karttana. Tummempi väri tarkoittaa suurempaa painoa.
Klikkaamalla karttaa vasemmalla hiirinapilla, ohjelma etsii reitin uudestaan samasta aloitussolmusta. Klikkaamalla oikealla napilla ohjelma etsii reitin edellisen reitin lopusta.
