# A-Star-Algorithm

A* or A Star algorithm implementation for A.I. class at FURB.

Authors
> [Eduardo Z. Feller](https://github.com/eduardofz12) <br>
> [Hugo Marcel Larsen](https://github.com/HMLarsen) <br>
> [Lucas Vanderlinde](https://github.com/LucasVander) <br>

## Tests
Run AStarTest.java and select a input for create the algorithm area. The correct syntax of the text file is:
- Line 1: quantity of rows and columns of the area separated by comma.
- Line 2: row and column position of the initial node in the area separated by "-".
- Line 3: row and column position of the final node in the area separated by "-".
- Line 4+: row and column position of a block node in the area separated by "-".

Example inputs are in the "test inputs" folder.
Test input 3 example:
```
Area (cost: 60):

   0  1  2  3  
0  -  -  -  -  
1  I  *  *  -  
2  -  B  *  -  
3  -  B  *  -  
4  -  B  *  -  
5  -  B  F  -  
6  -  -  -  - 
```
