
---

# PA8: Heaps (open)
---

**This assignment is open to collaboration.**

This assignment will teach you how to implement a Heap using Dijkstra's Algorithm.

This PA is due on ** **Thursday, March 11 at 11:59pm** **  

Link to FAQ: https://piazza.com/class/kirvel5rj2337n?cid=1484

Link to PA Video: https://canvas.ucsd.edu/courses/18854/external_tools/82

Link to PA Test Cases: https://hackmd.io/@gadjoian/SJPgQRIz


## Getting the Code
The starter code is in the Github repository that you are currently looking at. If you are not familiar with Github, here are two easy ways to get your code.

1. Download as a ZIP folder

    If you scroll to the top of Github repository, you should see a green button that says *Code*. Click on that button. Then click on *Download ZIP*. This should download all the files as a ZIP folder. You can then unzip/extract the zip bundle and move it to wherever you would like to work. The code that you will be changing is in the folder called *pa8-starter*.

2. Using git clone (requires terminal/command line)

    If you scroll to the top of the Github repository, you should see a green button that says *Code*. Click on that button. You should see something that says *Clone with HTTPS*. Copy the link that is in that section. In terminal/command line, navigate to whatever folder/directory you would like to work. Type the command `git clone _` where the `_` is replaced with the link you copied. This should clone the repository on your computer and you can then edit the files on whatever IDE you see fit.

If you are unsure or have questions about how to get the starter code, feel free to make a Piazza post or ask a tutor for help.

## Part 1: An Implementation of `Heap` (18 points)

You will create a file named `Heap.java` where you will implment the `Heap` class. You will implement all the methods defined in the given interface `PriorityQueue.java` in the `Heap` class as well as additional methods described below.

### Import Statements
For `Heap.java`, you are allowed to use the following Java packages:
```
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;
```

*Do **NOT** import any additional packages!*

### `Entry` Class
Copy the following code into your `Heap.java` file. This will be an outer class that represents the entries for the heap. Note, the key represents the priority.

```
class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }
}
```


### `Heap` Class
#### Instance Variables
- `public List<Entry<K, V>> entries`: Instead of creating a tree of entries, we will use a list to maintain the heap structure.
- `public Comparator<K> comparator`: This comparator will be passed into the constructor and will be used to determine which entry objects have more priority based opn their keys. This comparator class has one method as follows:

`public int compare(Integer a, Integer b)`: takes in two integers and returns an integer value that represent whether a, takes priority over b. A return value of 0 signifies that the priorities of a and b are equal. A negative return value will signify that a has less priority than b and a positive return value will assume the opposite.



#### Constructor
Your heap implementation will have one constructor that takes a comparator as its argument.
` public Heap(Comparator<K> comparator)`: initializes the instance variables

#### Required Method Descriptions

| Method Name | Description |
|-------------|----------------------|
|`void add(K k, V v)`| Insert a new entry with the given key and value to the end of the heap. Then, `bubbleUp` so that the heap properties are not violated |
|`Entry<K, V> poll()`| Remove and return the root element in the heap. Set the last entry in the heap to the root. Use `bubbleDown` to fix the heap after the removal. If the size is zero, throw `NoSuchElementException()`|
|`Entry<K, V> peek()`| Return the root element of the heap. If the size is zero, throw `NoSuchElementException()`  |
|`List<Entry<K,V>> toArray()`| Return the list of entries. |
|`public boolean isEmpty()`| If the List of entries is empty, return true. Otherwise, return false. |



#### Additional Method Descriptions

| Method Name | Description |
|-------------|----------------------|
|`public int parent(int index)`| Return the parent index. |
|`public int left(int index)`| Return the left child index. |
|`public int right(int index)`| Return the right child index. |
|`public void swap(int i1, int i2)`| Takes the index of two entries and swaps them. |
|`public void bubbleUp(int index)`| A recursive method that moves the entry at the specified `index` to a smaller index (up the tree) while maintaining the heap structure. In the case where the element is equal to the parent, you should **not** swap. |
|`public void bubbleDown(int index)`| A recursive method that moves the entry at the specified `index` to a larger index (down the tree) while maintaining the heap structure. Swap with the <s>smaller child</s> child with higher priority. If both chilren are equal and swapping is needed, **swap with the left child**. In the case where the element is equal to the smaller child, you should **not** swap. |
|`public boolean existsAndGreater(int index1, int index2)`| Returns true if the entry at index1 is greater than that at index2 (Note: Both entries at the specified indicies must exists for this to be true). Return false otherwise.  |
|`public int size()`| Returns the number of elements in `entries`. |
|`public String toString()`| Returns a string representation of the elements in `entries` (this method is helpful for debugging) |


You may find the following link useful:
- [`Comparator`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html)

Your implementation of `Heap` will be graded automatically by tests that we
provide. We’ve provided a description of all of these tests as a PDF as a part of the starter code.

## Part 2: `MazeSolver` (Dijkstra's Algorithm 15 points)

In pa3 you completed an algorithm that would find the shortest possible path from a start square to a finish square using a queue structure. To extend this concept further, we have added a cost attribute to each square. This cost attribute is an integer value that denotes how costly it is to visit a particular square. The square also has an attribute, runningCost, which denotes the current total cost it took to get to that square from the start square.   

The goal now is not to find the shortest possible path, but rather to minimize the cost from the start square to the finish square. To do so you will use Dijkstra's algorithm along with the priority queue structure (heap) that you constructed in part 1. Follow the following psuedo code to implement the cost minimization algorithm in the `solve` method of `MazeSolver.java`.  

```
1. initialize pq to be a new empty heap // This will be done in the tester file
                                        // and passed into solve as a parameter.
2. add the start square's cost as the key
   and the start square itself as the value to pq
3. while pq is not empty:
4.     let current = remove the first entry from pq (poll)
5.     let currentSquare = current's value
6.     Mark currentSquare as visited
7.     if currentSquare is the finishing square
8.         return currentSquare
9.     else
10.         for each neighbor of currentSquare that isn't a wall and isn't visited
11.            let currentCost = current's key plus the neighbors cost
12.            if currentCost is less than neighbor's runningCost
13.                set the previous of the neighbor to currentSquare
14.                set the neighbors runningCost to currentCost
15.                add the currentCost as key and neighbor as value to the pq (add)
16. if the loop ended, return null (no path found)
```

## Testing
**All test case descriptions available here: https://hackmd.io/@gadjoian/SJPgQRIz**
### Heap - HeapTest.java
In the starter code, we provided the file HeapTest.java to unit test your implementation. **Note**: For this PA, your unit tests will be graded for completion only (i.e. at least 1 test written), however, we **strongly** encourage you to thoroughly test every public method in your class. You are required to have at least one unit test written by yourself. We have provided the descriptions of the tests we'll use to grade your code, so we recommend you incorporate those tests into your HeapTest.java.

### MazeSolver - TestSolver.java
Just like pa3 you will be creating different mazes that you will run your algorithm through to find a path from the start square to the finish square. However, this time there will also be a 2-D cost array that you will include when creating the Maze. We have included one testcase for you to demonstrate how to construct the costArray and create the corresponding Maze. This test should pass when you have finished implementing the solve algorithm. Similar to HeapTest.java, you should include at least one unit test as we will be grading for completion; however, we **strongly** recommend you to look at the test descriptions given and write as many tests as you can to ensure you'll pass all of the autograder tests.

## Style (5 points)

The following files will be graded on style:

* Heap.Java
* HeapTest.java
* MazeSolver.java
* TestSolvers.java

To find the full style guideline, follow this link: https://docs.google.com/document/d/1XCwv_vHrp1X4vmlmNJIiXnxPuRLPZQkQEGNrJHJ0Ong/edit?usp=sharing  

All guidelines that we will be following this quarter are marked in the Style Guidelines document. These are required and will be graded.

On this PA, **all guidelines must be followed**, they are summarized below:

- file headers
- method headers (not required for test methods)
- Lines cannot be longer than 100 characters
- Inconsistent indentation
- Lines **must not** be indented more than 6 times. If you have a need to
  indent more than 6 levels, build a helper method or otherwise reorganize your code
- Test method must have meaningful names
- Helper method must have meaningful names
- descriptive variable names
- magic numbers

## Submitting

#### Part 1 & 2
On the Gradescope assignment **Programming Assignment 8 - code** please submit the following file structure:

* Heap.java
* HeapTest.java
* Maze.java
* MazeSolver.java
* TestSolvers.java
* PriorityQueue.java

The easiest way to submit your files is to drag them individually into the submit box and upload that to Gradescope. You may submit as many times as you like till the deadline.



## Scoring (40 points total)

- 18 points: implementation of `Heap` [automatically graded]
- 15 points: Implementation of `MazeSolver` [automatically graded]
- 5 points: Style [manually graded]
- 1 point: `HeapTest` graded on completion [manually graded]
- 1 point: `TestSolvers` graded on completion [manually graded]
