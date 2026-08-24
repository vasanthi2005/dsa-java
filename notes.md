## REDO LIST

.Next permutation — 21 Aug
.Quicksort partition — 19 Aug
.kadane's-21 aug
.Longest consecutive sequence-22 aug
.Set matrix zeroes-22 aug
. Spiral matrix — 22 Aug
. Subarray sum equals k — 22 Aug

---

## Time & Space Complexity

**Definition** — rate at which runtime grows with input size.
Not actual seconds; machine-independent.

**The three rules**

1. Always calculate for the worst case
2. Drop constant terms — O(3N + 1) → O(3N) → O(N)
3. Drop lower-order terms — O(4N³ + 3N²) → O(N³)

**Common cases**

- Single loop over n → O(n)
- Nested loops, both n → O(n²)
- Nested where inner runs i times → 1+2+...+n = n(n+1)/2 → still O(n²)
- Fixed count, independent of input → O(1)

**The 10⁸ rule** — online judges do ~10⁸ operations/second.
Estimate BEFORE coding:

- n = 10⁵ and O(n²) → 10¹⁰ ops → too slow, find a better approach
- n = 10⁵ and O(n log n) → fine

**Space complexity**

- Auxiliary space (what you allocate) + input space
- Most interviews/LeetCode quote auxiliary only — state which you mean
- Array of size n → O(n); a few variables → O(1)

**Interview rule** — don't modify the input to save space unless
asked. Extra space is fine; destroying the caller's data isn't.

**Big-O measures growth, not speed** — two O(n) solutions can
differ 10× in practice. Optimising within a class still counts.

**Frequency counting** — `map.put(c, map.getOrDefault(c, 0) + 1)`.
The single most-used pattern in DSA. Any "how many times does X
appear" problem starts here. O(n) time.

**HashMap has no order.** LinkedHashMap = insertion order,
TreeMap = sorted by key.

## Java Collections

**ArrayList** — resizable list. `add()`, `get(i)`, `size()`.
Prints readably: `[5, 3]`

**HashMap** — key -> value lookup, O(1) average.
`put(k, v)`, `get(k)`, `containsKey(k)`
`getOrDefault(k, default)` — returns default if key absent

**Frequency counting** — `map.put(c, map.getOrDefault(c, 0) + 1)`
Most-used pattern in DSA. Any "how many times does X appear" starts here.

**HashSet** — unique values only, duplicates silently ignored.
`add()` on an existing value does nothing. `contains()` is O(1).

**No ordering in HashMap/HashSet.** LinkedHashMap = insertion order,
TreeMap = sorted by key.

## Sorting

`Arrays.sort(arr)` — for arrays
`Collections.sort(list)` / `list.sort(...)` — for lists

**Printing arrays** — `System.out.println(arr)` gives a memory address,
not contents. Use `Arrays.toString(arr)`, or `Arrays.deepToString(arr)`
for 2D. Arrays don't override toString(); ArrayList does.

## Comparator

Answers one question: given two items, which comes first?

- negative -> a first
- positive -> b first
- zero -> tied

  list.sort((a, b) -> a.length() - b.length()); // ascending
  list.sort((a, b) -> b.length() - a.length()); // descending

Subtraction works because it naturally produces the right sign.

The sort algorithm is Java's; the comparator is just the rule it
consults, called many times during one sort. Passing behaviour into
a function, not just data.

Lambda `(a, b) -> ...` is shorthand for the older
`new Comparator<String>() { public int compare(...) }` form — same thing.

**Euclidean algorithm** — gcd(a,b) = gcd(b, a%b) until b is 0. O(log n). Brute force divisor-checking is O(min(a,b)) and times out around 10⁹.

## Recursion

**Definition** — a function that calls itself, breaking a problem into
smaller subproblems until it hits a base case that stops the calls.

**Two required parts**

1. **Base case** — the condition that stops the recursion and returns
   without calling again
2. **Recursive case** — a call on a smaller input, moving toward the base

Missing base case, or a call that doesn't shrink the input → infinite
recursion → StackOverflowError.

**The call stack** — every call is stored on a stack while it waits for
the calls below it to finish. Nothing returns until the base case is
reached; then values return back up one by one. That stack is why
recursion costs O(n) space where a loop would cost O(1).

**How to reason about it** — don't trace every level. Assume the
recursive call returns the correct answer for the smaller input, then
ask what you do with it. Trusting the smaller call is the whole skill.

**When it's worth it**

- Naturally recursive structures: trees, graphs, nested data
- Divide and conquer: mergesort, quicksort, binary search
- Foundation for backtracking and DP

**Costs** — extra memory per call, slower than an equivalent loop,
harder to debug. Anything recursive can be written iteratively.

### Patterns from the problems

**Print N times / print 1 to N** — base case at the boundary, recursive
call with n-1. Order of the print statement relative to the call decides
whether output runs forwards or backwards.

**Sum of first N** — `sum(n) = n + sum(n-1)`, base case `sum(0) = 0`.
The shape: combine the current value with the result of the smaller call.

**Factorial** — same shape with multiplication. `fact(n) = n * fact(n-1)`,
base `fact(0) = 1`.

**Reverse an array** — two pointers moving inward, recursing until they
meet. Same logic as the iterative two-pointer version; O(n) space here
because of the stack.

**Palindrome check** — compare outer characters, recurse inward. Base
case when the pointers cross.

**Fibonacci** — `fib(n) = fib(n-1) + fib(n-2)`, two base cases (0 and 1).
Naive version is O(2^n) because it recomputes the same values repeatedly.
This is the motivating example for memoisation and DP later.

## Hashing

**Precompute then lookup** — build a frequency structure in one pass,
then answer each query in O(1). Without it, every query rescans the
input: O(n×q) becomes O(n+q).

**Array vs HashMap** — use an array when keys are a small known range
(26 letters: `hash[c - 'a']`, since chars are numbers underneath).
Use a HashMap for arbitrary keys — any integer, large or negative.

**`while (q-- > 0)`** — post-decrement returns the current value then
subtracts 1, so the check uses the old value. Same as
`for (int i = 0; i < q; i++)`, just shorter.

**Iterating a map**

    for (Map.Entry<Integer,Integer> e : map.entrySet()) {
        e.getKey();    // the key of THIS entry
        e.getValue();  // its value
    }

`map` is the container, `e` is one item from it. `map.getKey()` doesn't
exist — the map has many keys, the entry has one.

**Building a list of lists** — create the inner list INSIDE the loop.
Declaring it outside means every pair points at the same list, and you
end up with all values in one. (Opposite of the while-loop scope bug,
where the variable had to be outside.)

**Tracking a max needs two variables** — the best count AND the element
that achieved it. Returning the count when the question asks for the
element is easy to miss, because they sometimes coincide by chance.

**Tie-breaks matter with HashMap** — iteration order is arbitrary, so
"first one wins" is unpredictable. Handle ties explicitly.

**The three O(n²) sorts**

|           | Best  | Worst | Stable | Notes                                         |
| --------- | ----- | ----- | ------ | --------------------------------------------- |
| Selection | O(n²) | O(n²) | No     | Fewest swaps (n-1)                            |
| Bubble    | O(n)  | O(n²) | Yes    | O(n) only with the swapped flag               |
| Insertion | O(n)  | O(n²) | Yes    | Best on nearly-sorted; used inside real sorts |

All O(1) space, all in-place. None used in practice except insertion,
which library sorts fall back to for small subarrays.

**What made mine wrong twice** — I wrote the same forward-scan-and-swap
loop for all three. Each algorithm has a distinct movement pattern:
selection finds the min then swaps once; bubble compares adjacent pairs;
insertion shifts backward with early exit. Sorting correctly isn't the
same as implementing the named algorithm.
**Merge sort** — recursion divides, merge conquers. The recursive calls sort nothing; every merge is where sorting happens. Guaranteed O(n log n) unlike quicksort. Needs O(n) extra space.
Count vs index — arr.length is a count, the last index is length - 1. Ask which one a bound needs, every time.

Three-line swap: the last line is always temp. Wrote arr[x] = arr[y] there three separate times — insertion sort, partition, and the final pivot swap. By line 3 the first array slot has already been overwritten.

Merge sort vs quicksort — merge splits blindly and does the work when combining; quicksort does the work when splitting and nothing on the way back. Merge is O(n log n) guaranteed but needs O(n) space; quicksort is in-place but O(n²) in the worst case.

## Arrays — patterns

**Two pointers, read/write** — one pointer scans forward through everything,
a second lags behind marking where to write. The gap between them is what
you're discarding. Used for in-place modification: remove duplicates, move
zeros. Safe because the writer never overtakes the reader.

**Two pointers, opposite ends** — a different arrangement of the same family:
pointers start at both ends and move inward. Used for reversal and quicksort's
partition.

**Track-two-variables pass** — second largest keeps max1 and max2 in one pass.
When a new max is found, the old max1 shifts down to max2. Beats sorting
(O(n) vs O(n log n)). Same shape as tracking maxCount + maxKey in the
hashing problem.

**Adjacent comparison only works on sorted input** — remove duplicates
compares nums[i] with nums[i-1] and that's enough, because sorting puts
duplicates next to each other. Unsorted would need a HashSet and O(n) space.

## Gotchas

**Sentinel values leak.** Initialising with Integer.MIN_VALUE works as an
internal marker, but if nothing ever displaces it, it escapes into the return
value. Always check for the sentinel before returning.

**Index vs count.** A pointer marking the last filled position (from 0) means
the count is pointer + 1. Same distinction as arr.length vs the last index.

**Length syntax differs by type:**

- `array.length` (field, no parens)
- `string.length()` (method)
- `list.size()` (method, different name)

**Shift direction decides loop direction.** Shifting left, loop forward.
Shifting right, loop backward — otherwise you overwrite each element before
reading it.

**Read the problem statement for left vs right.** GFG's rotate-by-one wanted
right rotation; I wrote left. Identical logic, mirrored, and the descriptions
look nearly the same.

**Read the constraints before adding guards.** They tell you whether empty
arrays are possible and whether an O(n²) approach will time out.

## Arrays (cont.)

**Rotate by k — reversal trick.** Reverse the first k, reverse the rest,
then reverse the whole array. Reversing a section flips both position and
internal order; reversing each block first means the final full reversal
un-reverses each block while swapping their positions.

    [1,2,3,4,5] k=2
    reverse 0..1  → [2,1,3,4,5]
    reverse 2..4  → [2,1,5,4,3]
    reverse 0..4  → [3,4,5,1,2]

O(n), each element touched exactly 3 times regardless of k. Brute force
(rotate by one, k times) is O(n×k) and times out on large k.

**`k = k % n` first** on any rotation problem. Rotating by more than the
length wraps around — skipping it wastes work and can go out of bounds.
Right rotation splits at n-k instead of k.

**Move zeros — reframe the problem.** Don't move the zeros; they're
interchangeable, so there's nothing to relocate. Collect the NON-zeros at
the front, then pad the rest with zeros. Same read/write pointer shape as
remove-duplicates.

**Sortedness is what makes O(1) duplicate checks possible.** Remove
duplicates compares against nums[i-1]; union compares against the last
value added. Both work because sorted input puts duplicates adjacent.
Unsorted would need a HashSet and O(n) space.

**`list.contains()` inside a loop is O(n²).** Scans the whole list every
call. A set's add() is O(1) for HashSet, O(log n) for TreeSet (which also
keeps things sorted). Two pointers on sorted input beats both at O(n+m).

**Linear search is optimal on unsorted data.** No way to skip elements
without ordering. The moment data is sorted, binary search gives O(log n)
— which is why it gets its own section.

## Recurring mistakes

**Index vs value — the most frequent one so far.** When a variable holds
a position, you almost always want `arr[variable]`, not `variable`.
Hit it in quicksort (`int start = pivot + 1`), remove duplicates
(`int start = nums[0]`), and merge sort (comparing and adding indices
instead of elements).

**One counter, not two.** In move-zeros I incremented both i and start in
the padding loop. They moved in lockstep so it worked — but redundant
counters become bugs the moment something shifts.

**Increments go outside the inner condition.** Advancing a pointer means
"processed this element"; adding means "worth keeping". Two separate
decisions. Put the increment inside the add-check and a skipped element
never advances — infinite loop.

**Guard clauses come first in `&&` / `||`.** Short-circuit evaluation runs
left to right, so `list.isEmpty() || list.get(size-1) != x` is safe and the
reverse crashes. Same as `start <= high && arr[start] <= pivot`.

## Reuse

The array-reversal solution became the helper inside rotate-by-k. The merge
function's structure became the two-pointer union. Earlier problems turn into
building blocks — that happens more from here on.

->One variable, one meaning. Stock buy-sell needed a price and a profit. The Kadane's extension needed a tentative start and a confirmed start. Majority element needed a count and the element. When a maximum depends on something else, you need a variable for each — trying to store both in one is where the bug lives.
