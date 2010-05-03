.PHONY: all clean

JCC=javac
RUN=java
JFLAG=-d
RFLAG=-cp $(CLASSPATH)
SRCDIR=./src
BINDIR=./bin
EXE=RunGnutella
CLASSPATH=$(BINDIR)
SRC= $(SRCDIR)/Files.java \
	 $(SRCDIR)/GenerateGraph.java \
	 $(SRCDIR)/NodeIds.java \
	 $(SRCDIR)/Node.java \
	 $(SRCDIR)/Query.java \
	 $(SRCDIR)/RunGnutella.java


all:
	$(JCC) $(SRC) $(JFLAG) $(BINDIR)
#javac `find src -name "*.java"` -d ./bin

clean:
	rm -f `find -name "*.class"`

run:
	$(RUN) $(RFLAG) $(EXE)
