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