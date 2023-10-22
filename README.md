# NFA_TO_DFA

The goal of this project is to become a library which can be used (for educational purposes) to learn about Computational Theory topics like Regular Languages, and Context-Free languages. This `README.md` serves as the documentation for the library.

## Using the library

The library can be compiled with `make build`

## Contributing to the library

The library `.java` files are located in `nfa_to_dfa/`. 

### Tests

* To write tests, you can add test files to `test/`, or modify the existing tests. 
* To run tests, you can either run `make test` to run the pre-existing (hard-coded) test files, or write your own Makefile which runs your new test files. To add your test file to the hard-coded (I'm so sorry) tests, please file an Issue on this repository. 

### TODO

This project is in infancy, so there is still a lot to contribute. Here are some things off of the top of my head:

 * Automated testing, which automatically executes all of the files in the `test/` folder
 * Methods to read `.txt` files which can generate and perform operations on DFAs, NFAs, and be validated by a Regex
 * Methods to read `.txt` files which can generate and perform operations on PDAs, CFGs, and convert CFGs to CNF. 
 * Methods to set up and simulate a Turing Machine by utlizing the API for tape reads/writes as well as moving up and down the tape. 