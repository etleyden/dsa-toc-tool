.PHONY: build test clean
build:
	touch build/manifest.txt
	javac nfa_to_dfa/*.java && jar cvfm build/NFA_TO_DFA.jar build/manifest.txt nfa_to_dfa/*.class

test: build
	javac -cp ./build/NFA_TO_DFA.jar test/GraphTest.java
	java -cp ./test GraphTest

clean:
	rm -f test/*.class
	rm -f nfa_to_dfa/*.class
	rm -f build/*