# How to write JSON for dsa_toc_tool

Creating input example data structures for this library are really easy
as we have provided a JSON specification for each data structure, described below:

## DFA

The DFA JSON spec closely follows the formal description for a DSA. 
To write a JSON object for a DFA just set `dsa_type` to `dfa`, and fill out 
the following states:

| key               | data type | required | description                                                                                                                                                                                                                                              |
|-------------------|-----------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `num_states`      | `int`     | yes      | The number of states in the state diagram                                                                                                                                                                                                                |
| `start_numbering` | `int`     | no       | The number the nodes labels start with. Default is 0. You may want to set it to 1 instead                                                                                                                                                                |
| `input_alphabet`  | `char[]`  | yes      | The accepted characters on the input alphabet                                                                                                                                                                                                            |
| `start_state`     | `int`     | yes      | The state where computation starts.                                                                                                                                                                                                                      |
| `accept_states`   | `int[]`   | yes      | The state where computation decides if the string can be accepted or not.                                                                                                                                                                                |
| `transitions`     | `int[][]` | yes      | Transition functions. Can be read as a table where the index of each column corresponds to an index in the `input_alphabet`, and the transition is *from* the state with the same label as the index of the row, *to* the state recorded in the array.   |

## NFA

The NFA JSON spec also closely follows the formal description of an NFA. As such, it is also very similar to the 
specification for a DFA. The only difference is obviously that `dsa_type` needs to be `nfa`, and the `transitions` entry
will reflect the more complex nature of NFAs in general.

#### `transitions`

The entry in the transitions table is going to be a 3D array, where `transitions[i][j][k]` represents a transition from 
state `i`, (`i` has `j` total transitions -- transitions between identical states with different input count as separate here)
where `k == 0` is the input character and `k == 1` is the destination state. This means each `transitions[i][j]` will 
have the types: `[char, int]`.

You can view examples of this in `example_json/`.