## REDO LIST

-Ship capacity-23rd sept
-Kth Missing Number-23rd Sept
-Split Array Largest Sum -24th sept (Painter's Partition)
-Aggressive cows-25th sept

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

# Merge Sort as a Counting Tool

Two problems, same technique, one important difference.

## The shared idea

Both count pairs (i, j) where i < j and some condition holds between
nums[i] and nums[j]. Brute force is O(n²). Merge sort gets it to
O(n log n) because sortedness lets you count whole blocks at once
instead of checking pairs one by one.

Merge sort specifically, because it hands you two SORTED HALVES side
by side at every level. And every pair falls into exactly one merge
where i is in the left half and j is in the right — so nothing is
double counted or missed.

Quicksort wouldn't work: it partitions around a pivot and never
produces two sorted halves.

The halves aren't given by the problem. Merge sort manufactures them
on the way back up.

---

## Count Inversions

**Condition:** nums[i] > nums[j]

**Counting happens INSIDE the merge loop:**

    while (left <= mid && right <= high) {
        if (arr[left] <= arr[right]) {
            list.add(arr[left]); left++;
        } else {
            count += mid - left + 1;      // <-- here
            list.add(arr[right]); right++;
        }
    }

**Why it fits there:** the merge already compares arr[left] against
arr[right], and that comparison IS the inversion condition. When the
else branch fires you've just found arr[left] > arr[right] — and since
the left half is sorted, everything from left to mid is bigger too.
All of them are inversions with this right element.

**Fixed on a RIGHT element, counting LEFT elements remaining.**

Formula: `mid - left + 1` — positions left through mid, both endpoints
included, hence the +1.

---

## Reverse Pairs

**Condition:** nums[i] > 2 \* nums[j]

**Counting happens in a SEPARATE pass, BEFORE the merge:**

    int r = mid + 1;
    for (int i = low; i <= mid; i++) {
        while (r <= high && arr[i] > 2L * arr[r]) r++;
        count += r - (mid + 1);
    }

    // then the merge, with NO counting in it

**Why a separate pass:** the merge compares arr[left] vs arr[right],
but the condition here is arr[left] > 2\*arr[right]. Different test, so
counting can't ride along with the merge.

**Why BEFORE:** the merge overwrites arr[low..high] with the combined
result, destroying the two separate halves the counting depends on.

**Fixed on a LEFT element, counting RIGHT elements passed.**

Formula: `r - (mid + 1)` — no +1, because r stops one PAST the last
match (the while exits when the condition fails).

---

## The two mistakes I made

**Used the wrong formula.** Reached for `mid - i + 1` in reverse pairs,
which is the inversions formula. Check which half you're counting:

    left half  = low .. mid
    right half = mid+1 .. high

    Fixed on a LEFT element  → count right elements passed  → r - (mid+1)
    Fixed on a RIGHT element → count left elements remaining → mid - left + 1

The tell is the loop variable's range. `for (i = low; i <= mid)` walks
the left half. A count involving mid+1 measures the right half.

**Shared the pointer.** Used one `right` for both the counting pass and
the merge. The counting pass leaves it partway through the right half,
so the merge starts from the wrong place. The counting pass needs its
own variable.

---

## +1 or not

+1 when both endpoints are included in the range.
No +1 when the upper bound is exclusive — i.e. the pointer stopped one
past the last item you want to count.

Inversions counts an inclusive range (left..mid). Reverse pairs counts
distance travelled, where the endpoint is one past.

Check this every time rather than pattern-matching.

---

## Shared mechanics

**Why `r` isn't reset per iteration of i (reverse pairs):** the left
half is sorted too, so a larger arr[i] pairs with at least as many
right elements as the one before it. r only moves forward — that's
what keeps the pass O(n) rather than O(n²).

**count must be a class field**, not a local — the counting happens
inside merge(), several recursion levels below the entry point.

**Reset count = 0** at the top of the entry method. Static fields
persist between calls and the judge runs many test cases in one
session; without the reset, test 2 inherits test 1's total.

**Use long.** With n = 10^5 the inversion count can reach ~5×10^9,
past int range.

**2L not 2** in reverse pairs — `2 * arr[right]` overflows int for
large values and wraps negative, producing false matches. (Fifth time
this overflow rule has bitten me: nCr, 4-Sum, missing-and-repeating,
and here.)

THE RULE: the variable's type doesn't change how the expression is
evaluated. Java computes the right-hand side in int arithmetic and
widens afterwards. Cast an OPERAND, not the result.

---

## How to recognise a binary search problem

The sheet groups problems by topic, so the technique is always obvious.
In an interview nobody labels them. Recognising WHEN to use a technique
is a separate skill from executing it.

### Signals

**"Sorted"** — a sorted array plus "find something." Search, lower
bound, first/last occurrence.

**"Find the minimum X such that..." / "the maximum X such that..."**
The biggest tell for answer-space problems.

- Koko: MINIMUM speed such that she finishes in h hours
- Smallest divisor: SMALLEST divisor such that the sum fits the threshold

**An O(log n) requirement** — find-peak-element stated it outright. If
log time is demanded, it's almost always binary search.

**A huge answer range with a cheap check** — the answer could be up to
10^9, so trying every value is hopeless, but checking ONE candidate is
just a loop. That combination is exactly what binary search is for.

### The real test underneath

1. If I pick a value, can I easily check whether it works?
2. If it works, does everything on ONE SIDE of it also work?

Both yes → binary search applies.

The second is the crucial one. Koko finishes at speed 5 if she did at
speed 4 — faster never hurts. That one-directional property (monotonic)
is what lets you throw away half the range each step.

### When it does NOT apply

When working values are scattered rather than grouped together.
"Find a subarray summing to k" has no such property — a longer subarray
isn't more or less likely to work. That's why it needed prefix sums.

### Binary search does NOT require sorted data

It requires a condition that eliminates half the range with certainty.

- Find peak element: unsorted, but "uphill means a peak is that way"
- Answer-space problems: no array being searched at all — just a range
  of possible answers and a feasibility test

### Practice note

After finishing this section, try problems from a mixed list where the
topic isn't labelled.

---

# Binary Search on the Answer Space — the four problems

## The shape they share

You're given a LIMIT. You search for something else.

| Problem          | Given (the limit)     | Searching for    |
| ---------------- | --------------------- | ---------------- |
| Smallest divisor | threshold             | the divisor      |
| Koko bananas     | h hours               | the eating speed |
| Bouquets         | m bouquets, k flowers | the day          |
| Ship packages    | days                  | the capacity     |

**You can't compute the answer directly.** There's no formula that turns
"3 days" into a capacity. But you CAN take a guessed capacity and count
the days. So you guess and check — and binary search makes the guessing
fast.

### The shop picture

You're buying a ship. You can't say "give me the ship for 3 days" —
nobody can answer that. You point at a ship size and the shopkeeper tells
you how many days. Then YOU decide if it's good enough.

    mid     = the thing you point at (a guess at the answer)
    helper  = the shopkeeper (guess → some number)
    compare = your job (is that number within the limit?)
    ans     = best guess so far

Works for all four:

    point at a capacity → shopkeeper says days
    point at a speed    → says hours
    point at a divisor  → says the sum
    point at a day      → says bouquets

---

## Finding the range

Two questions, every time. Don't memorise the answers — derive them.

**What's the smallest value that isn't IMPOSSIBLE?**
**What's the largest value that could still HELP?**

| Problem  | low              | why                                                 | high          | why                                              |
| -------- | ---------------- | --------------------------------------------------- | ------------- | ------------------------------------------------ |
| Divisor  | 1                | smallest meaningful divisor                         | max(nums)     | every element rounds to 1, smallest possible sum |
| Koko     | 1                | one banana an hour                                  | max(piles)    | each pile takes 1 hour, fastest possible         |
| Bouquets | min(bloomDay)    | before that, nothing has bloomed at all             | max(bloomDay) | everything bloomed, waiting changes nothing      |
| Ships    | **max(weights)** | a smaller ship can NEVER carry the heaviest package | sum(weights)  | everything in one trip                           |

**Ships is the odd one.** Its low is a MAX, because anything below it is
impossible rather than just slow. Koko's low is 1 because a speed below
the smallest pile is still valid — just slower.

The lower bound always comes from what the value MEANS.

---

## The helpers — all one pass with a resetting total

    // divisor: sum of ceilings
    for (int i : nums) sum += (int) Math.ceil((double) i / divisor);

    // Koko: sum of ceilings (SAME as divisor — different story, same code)
    for (int p : piles) hours += (int) Math.ceil((double) p / speed);

    // bouquets: count runs of k
    if (d <= day) { run++; if (run == k) { bouquets++; run = 0; } }
    else          { run = 0; }

    // ships: count trips
    if (load + w > capacity) { day++; load = w; }
    else                       load += w;

### Bouquets vs ships — nearly the same, two differences

|                   | bouquets                         | ships                                 |
| ----------------- | -------------------------------- | ------------------------------------- |
| counter starts at | 0                                | **1**                                 |
| on reset          | `run = 0` — the flower is WASTED | `load = w` — the package CARRIES OVER |

Ships starts at 1 because you're loading a ship from the very first
package. No bouquet exists yet at the start, but a trip does.

A flower at a gap goes nowhere. A package that didn't fit isn't thrown
away — it's the first item on the next ship.

---

## Direction — always the same reasoning

Ask: **if this value works, does the next one up also work?**

    bigger divisor  → smaller sum     → still fits
    faster speed    → fewer hours     → still in time
    later day       → more bloomed    → still enough bouquets
    bigger ship     → fewer trips     → still in time

Yes every time. So the working values form ONE BLOCK and you want where
it starts — lower bound shape:

    works    → record mid, search LEFT  (high = mid - 1)
    fails    → search RIGHT             (low  = mid + 1)

---

## Mistakes I made across these

**Recorded the wrong thing.** `ans = val` instead of `ans = mid` in
smallest divisor — that returns the SUM, not the divisor. Always record
the thing you're searching for.
(Same slip as returning the count instead of the element in
highest-occurring-element.)

**Comparison backwards.** In bouquets: `day <= d` should be `d <= day`,
and `<= m` should be `>= m`.
**Check by reading it aloud as a sentence** — "this plant blooms by the
day I'm checking", "at least m bouquets". If the sentence doesn't match
what you mean, it's the wrong way round.

**Rounding up.** Integer division truncates, so 7/3 is already 2 before
`Math.ceil` sees it — and ceil(2) is 2. Cast an operand FIRST:

    (int) Math.ceil((double) nums[i] / divisor)

Same rule as the overflow bugs: operand types decide how arithmetic
happens, not what you do afterwards.

**`min` starting at 0.** Values are positive so nothing is ever smaller
and it never updates. Start at `Integer.MAX_VALUE`.

**Overflow.** `(long) m * k` in bouquets — both can be large.
(Seventh time.)

**Helper taking too many parameters.** My ship helper took `days`. It
shouldn't — its only job is capacity → days. The comparison against the
limit belongs in the search.

**Naming.** Called the divisor helper `divisor` when it returns a SUM.
That's exactly the confusion that caused the `ans = val` bug. Name it
`sumOfDivisions`. Names are cheap insurance against your own recurring
mistakes.

---

## ans's starting value

    divisor / Koko / ships : ans = high
    bouquets               : ans = high (after the -1 impossible check)
    lower bound            : ans = nums.length ("nothing found")

When an answer is GUARANTEED to exist, start at the largest valid value —
worst case, that's the answer. When it might not exist, the default has
to mean "none".

Bouquets needs the impossible check FIRST (`m*k > length` → -1); after
that an answer is guaranteed.

INDICES (searching the array):
kth missing, lower/upper bound, rotated array, peak element
low = 0, high = arr.length - 1

ANSWER SPACE (no array being searched):
Koko, ships, divisor, bouquets, split array
low and high are possible ANSWERS — speeds, capacities, sums, days

---

# Binary Search — the five questions to ask every time

## Q0: Am I searching INDICES or VALUES?

**The test:** could the answer be a number that isn't in the array?

    YES → answer space (values)
    NO  → array indices

**Or:** what does `mid` mean here — a place to LOOK, or a value to TRY?

| Problem                            | mid is       | which   |
| ---------------------------------- | ------------ | ------- |
| search / lower bound / upper bound | a position   | indices |
| find peak element                  | a position   | indices |
| find min in rotated                | a position   | indices |
| kth missing positive               | a position   | indices |
| ships                              | a capacity   | values  |
| Koko                               | a speed      | values  |
| smallest divisor                   | a divisor    | values  |
| bouquets                           | a day        | values  |
| books / split array                | a page limit | values  |
| aggressive cows                    | a distance   | values  |

**Books:** [12,34,67,90] → answer 113. That's 12+34+67. It isn't in the
array. Answer space.

**Cows:** the answer might COINCIDENTALLY appear in the array (3 is a
stall in [0,3,4,7,9,10]) but you're searching distances, not stalls.
Still answer space. Note `high` is computed by SUBTRACTING two values —
that's a span, not a location.

**Indices** → `low = 0, high = length - 1`, always.
**Values** → work out the range (Q2).

---

## Q1: What am I searching FOR?

Name the thing the question asks you to return. Not the array, not the
limit given to you — the number you hand back.

    ships    → a capacity
    Koko     → a speed
    divisor  → a divisor
    bouquets → a day
    books    → a page limit
    cows     → a distance

**`mid` is a guess at that thing. `ans` holds the best guess. You return
`ans`.**

Getting this wrong caused a real bug: `ans = val` in smallest-divisor
recorded the SUM instead of the divisor.

---

## Q2: What's the range?

Two questions. Derive them — don't memorise the table.

**What's the smallest value that isn't IMPOSSIBLE?**
**What's the largest value that could still HELP?**

| Problem  | low           | why                                                          | high          | why                                        |
| -------- | ------------- | ------------------------------------------------------------ | ------------- | ------------------------------------------ |
| ships    | max(weights)  | below it, the heaviest package NEVER fits                    | sum(weights)  | one trip; nothing bigger helps             |
| books    | max(pages)    | the biggest book must go to SOMEONE, and they read that many | sum(pages)    | one student reads everything               |
| Koko     | 1             | speed 1 is slow but VALID — she still eats                   | max(piles)    | each pile takes one hour, fastest possible |
| divisor  | 1             | smallest meaningful divisor                                  | max(nums)     | every element rounds to 1                  |
| bouquets | min(bloomDay) | before that, nothing has bloomed at all                      | max(bloomDay) | all bloomed; waiting changes nothing       |
| cows     | 1             | smallest meaningful gap                                      | last - first  | the whole span                             |

### The rule behind low

**Is a tiny value IMPOSSIBLE, or just SLOW/BAD?**

    IMPOSSIBLE → low is a max
      books at limit 1: the 90-page book fits nowhere. No allocation exists.
      ships at capacity 1: the heavy package can never be carried.

    SLOW BUT VALID → low is 1
      Koko at speed 1: she eats everything, just takes forever.
      An answer exists; it might fail the time limit.

### The rule behind high

**Follow the MEANING of what you're measuring:**

    a SPAN  → subtract   (cows: last - first)
    a TOTAL → add        (books, ships: sum of everything)

---

## Q3: Do I want the answer BIG or SMALL?

**Read the LAST word of the phrase:**

    "minimise the MAXIMUM pages"         → answer should be SMALL
    "MAXIMUM possible minimum distance"  → answer should be BIG

**Or just say it out loud:** books — I want the biggest pile to be as
small as possible → small. Cows — I want the closest pair as far apart
as possible → big.

**That decides ONE line:**

    // want SMALL (ships, books, Koko, divisor, bouquets)
    if (works) { ans = mid; high = mid - 1; }   // try smaller
    else       { low = mid + 1; }

    // want BIG (cows)
    if (works) { ans = mid; low = mid + 1; }    // try bigger
    else       { high = mid - 1; }

**These two problems read almost identically and differ in one line.**
Cows and books are the pair to watch.

---

## Q4: What does the helper do?

**Give it a guess, get back a number to compare against the limit.**

THE SHOP PICTURE: you point at a ship size, the shopkeeper tells you how
many days. Then YOU decide if that's good enough. You can't walk in and
say "give me the ship for 3 days" — nobody can answer that.

    mid     = the thing you point at
    helper  = the shopkeeper (guess in, number out)
    compare = your job

    guess a capacity → helper says days
    guess a speed    → says hours
    guess a limit    → says students
    guess a distance → says cows placed
    guess a day      → says bouquets

**The helper never takes the limit as a parameter.** Its only job is
guess → number. The comparison belongs in the search.

### The helper shapes

    // ships / books / split array — IDENTICAL code
    if (running + item > limit) { count++; running = item; }
    else                          running += item;
    // count starts at 1, `>` not `>=` (hitting the limit exactly is fine)

    // cows — greedy placement
    if (nums[i] - last >= mid) { count++; last = nums[i]; }
    // count starts at 1, loop starts at i=1, `>=` (bigger gaps are fine)

    // bouquets
    if (d <= day) { run++; if (run == k) { bouquets++; run = 0; } }
    else            run = 0;
    // bouquets starts at 0

    // Koko / divisor — no reset, just a sum
    total += (int) Math.ceil((double) item / guess);

### Why some counters start at 1 and one starts at 0

The first ship, first student, first cow all EXIST from the very first
item. `count++` only fires when a new one STARTS, and nothing pushed the
first one into being.

A bouquet doesn't exist yet at the start — nothing is tied until k
flowers are in hand. So 0.

### Resets: discard vs carry over

    bouquets: run = 0     the flower at a gap is WASTED
    ships:    load = w    the package that didn't fit CARRIES OVER

---

## The three that are literally the same code

    ships = split array largest sum = book allocation

Different stories, rename the variables, identical. Book allocation is
marked Hard; ships is Medium. **Recognising the disguise is most of the
difficulty.**

---

## Recurring mistakes

**Recorded the wrong thing** — `ans = val` (the sum) instead of
`ans = mid` (the divisor). Always record the thing from Q1.

**Comparison backwards** — `day <= d` should be `d <= day`; `<= m` should
be `>= m`. **Read it aloud as a sentence.** "This plant blooms by the day
I'm checking." If the sentence doesn't match your meaning, flip it.

**Sorted when I shouldn't have** — books must stay in order (contiguous
allocation). Cows MUST be sorted (distance needs a line). Ask whether
order carries meaning.

**Rounding up** — integer division truncates, so 7/3 is already 2 before
`Math.ceil` sees it. Cast an operand FIRST:
`(int) Math.ceil((double) x / y)`

**`min` starting at 0** — values are positive so it never updates. Start
at `Integer.MAX_VALUE`.

**Overflow** — `(long) m * k`. Cast an OPERAND, not the result.

**Naming** — called a helper `maxPages` when it returns a STUDENT count,
and `divisor` when it returns a SUM. That's exactly what caused the
`ans = val` bug. Name helpers for what they RETURN.

---

## ans's starting value

    answer GUARANTEED to exist → ans = high (or low for maximise)
                                 worst case, that IS the answer
    might not exist            → default means "none" (e.g. nums.length, -1)

Bouquets needs the impossible check FIRST (`m*k > length` → -1).
Books needs `m > nums.length` → -1.
After the impossible check, an answer is guaranteed.

---

## The one-page version

    Q0. Indices or values?  → could the answer be outside the array?
    Q1. Searching for what? → that's mid and ans
    Q2. Smallest? Largest?  → that's low and high
    Q3. Big or small?       → which pointer moves on success
    Q4. Helper: guess → number, compared against the given limit
