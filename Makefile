JC = javac -cp .:/usr/share/java/junit4-4.12.jar

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
	DAG.java \
	Graph.java \
	BST.java \
	TestBST.java \
	TestDAG.java \
	SuiteTests.java \
	TestRunner.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
