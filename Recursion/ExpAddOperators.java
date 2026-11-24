// Expression Add Operators — insert +, -, * between digits of `num` so the
//   expression evaluates to target. Return every such expression.
//
// TWO CHOICES AT EACH STEP, not one:
//     1. WHERE DOES THIS OPERAND END?  (multi-digit allowed — "12+3" is valid)
//     2. WHICH OPERATOR goes in front of it?
//   The first is palindrome partitioning's "where to cut" — loop over end
//   positions j. The second is three separate recursive calls.
//
// LEFT-TO-RIGHT EVALUATION IS WRONG — multiplication binds tighter.
//   The problem's own example proves it:
//     num = "232", target = 8, output includes "2+3*2"
//     left to right: 2+3 = 5, 5*2 = 10   ✗ wouldn't be in the output
//     real maths:    3*2 = 6, 2+6 = 8    ✓
//
// THE KEY IDEA — CARRY `last` ALONGSIDE `total`.
//   `last` is the number most recently folded into the total. A * arriving
//   later has to reach back and undo it.
//
//     expression so far: 2 + 3     total = 5, last = 3
//     now *2 arrives, answer must be 8:
//         5 - 3    = 2      take the 3 back OUT of the total
//         3 * 2    = 6      multiply it by the new number
//         2 + 6    = 8      put the result back IN
//
//     total - last + (last * next)
//
//   THE THREE OPERATORS:
//     +   total + next                     last = next
//     -   total - next                     last = -next
//     *   total - last + last*next         last = last * next
//
//   ONLY * USES THE FORMULA, but ALL THREE must update `last` — a * might
//   turn up later and will need it. ("2 + 3 * 4": the + must leave last = 3,
//   or the * has nothing to grab.)
//
//   WHY last = -next FOR MINUS: a * after a subtraction must multiply the
//   NEGATIVE. 2 - 3 * 4 is 2 - 12, not -1 * 4.
//
//   AND last BECOMES THE PRODUCT after a *, so "2+3*2*5" multiplies 6 next,
//   not 3.
//
// THE FIRST OPERAND IS A SPECIAL CASE — no operator in front of it. Set total
//   and last directly, no formula:
//     if (i == 0) build(..., j+1, next, next, piece);
//
// LEADING ZEROS:
//     if (j > i && num.charAt(i) == '0') break;
//   j > i means the piece is longer than one digit; charAt(i) == '0' means it
//   starts with zero. "0" alone is a fine operand; "05" isn't.
//   BREAK, not continue — once "05" is rejected, every longer piece from the
//   same start ("05x", "05xy") also carries that zero, so abandon the loop.
//
// BASE CASE — both conditions, and mind where the return sits:
//     if (i == num.length()) {
//         if (total == target) result.add(exp);
//         return;                  // ← OUTSIDE the inner if
//     }
//   Put the return inside and a branch that ends on the wrong total falls
//   through and recurses past the end of the string.
//
// USE long, NOT int — num can be 10 digits and the intermediate products
//   overflow.
//
// `piece` is needed BOTH ways: as text for the expression string
//   (num.substring(i, j+1)) and as a number for the arithmetic
//   (Long.parseLong(piece)).
//
// No undo — exp is a String, so exp + "+" + piece builds a new one each time.
//
// j + 1 is the next start, not i + 1 — the piece can be several digits long.
//   (Same as palindrome partitioning.)
//
// MISTAKE: for (int j = i; j < num.length(); i++) — incremented i instead of
//   j, so j never moves. Infinite loop. THIRD TIME in two weeks (c++ in graph
//   colouring, the Sudoku digit loop). When a loop declares a new counter,
//   the increment must use THAT counter.
//
// Time: O(4^n), Space: O(n) recursion depth.
class Solution {
    public List<String> addOperators(String num, int target) {
       List<String> result=new ArrayList<>();
       build(result,num,target,0,0,0,"") ;
       return result;
    }
    public static void build(List<String> result,String num,int target,int i,long total,long last,String exp)
    {
        if(i==num.length())
        {
            if(total==target)
                result.add(exp);
            return ;
        }
        for(int j=i;j<num.length();j++)
        {
            if(j>i && num.charAt(i)=='0') break;
            String piece=num.substring(i,j+1);
            long next=Long.parseLong(piece);
            if(i==0)
            build(result,num,target,j+1,next,next,piece);
            else
            {
                build(result, num, target, j+1, total + next, next, exp + "+" + piece);
                build(result, num, target, j+1, total - next, -next, exp + "-" + piece);
                build(result, num, target, j+1, total - last + last * next,last * next,exp + "*" + piece);
            }
        }
    
    } 
}