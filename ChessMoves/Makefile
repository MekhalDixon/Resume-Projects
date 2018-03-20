#Makefile w/ macros
JAVASRC = ChessMoves.java Bishop.java bPawn.java wPawn.java Rook.java Queen.java Node.java King.java Knight.java LinkedList.java Chesspiece.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = ChessMoves
CLASSES = ChessMoves.class Bishop.class bPawn.class wPawn.class Rook.class Queen.class Node.class King.class Knight.class LinkedList.class Chesspiece.class
JARFILE = ChessMoves.jar

all:$(JARFILE)
$(JARFILE):$(CLASSES)
	echo Main-class: $(MAINCLASS)>Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES):$(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm$(CLASSES)$(JARFILE)
	
