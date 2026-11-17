// M Coloring Problem — can the graph be coloured with at most m colours so
//   that no two connected vertices share a colour?
//
// THE PICTURE: dots joined by lines. Paint every dot using at most m colours,
//   so no two dots joined by a line match.
//       0
//      / \      triangle: with 2 colours IMPOSSIBLE (dot 2 touches both and
//     1───2     both colours are taken). With 3, fine.
//
// THIS IS N-QUEENS:
//     N-Queens: one queen per COLUMN  → walk columns forward, loop over ROWS
//     Here:     one colour per VERTEX → walk vertices forward, loop over COLOURS
//
// INPUT IS AN EDGE LIST, which is awkward to check against. Convert it to an
//   ADJACENCY LIST first — a row of boxes, one per vertex, holding its
//   neighbours:
//     for (int i = 0; i < n; i++) adj.add(new ArrayList<>());   // make boxes
//     for (int[] e : edges) {                                   // THEN fill
//         adj.get(e[0]).add(e[1]);
//         adj.get(e[1]).add(e[0]);      // undirected — BOTH directions
//     }
//   adj.get(3) opens box 3. Both directions because an edge {0,1} means they
//   are neighbours OF EACH OTHER — write only one and vertex 1 won't know
//   about vertex 0, so a clash gets missed.
//   TWO SEPARATE LOOPS, not nested. Nested, the edge loop runs n times and on
//   the first pass most boxes don't exist yet. (Got this wrong twice.)
//
// TWO DIFFERENT STRUCTURES, don't blur them:
//     adj.get(3)   →  who dot 3 TOUCHES    — fixed, built once
//     colours[3]   →  what colour dot 3 IS — changes constantly
//   colours is INDEX = dot, VALUE = its colour. new int[n] zero-fills, and
//   0 means UNPAINTED — which is why colours run 1..m, not 0..m-1. An
//   unpainted neighbour then never matches a real colour.
//
// `c` vs `colours[]`: c is the colour being TRIED right now on one dot — a
//   loop counter, temporary. colours[] is the RECORD of what's actually
//   painted, and it's what isSafe reads to spot clashes. Without the record a
//   call would know its own trial colour and nothing else.
//
// THE FIVE LINES:
//     for (int c = 1; c <= m; c++)          try each colour
//         if (isSafe(vertex, c, adj, colours)) {    THE GATE — clash? skip,
//             colours[vertex] = c;                  paint never comes out
//             if (solve(vertex+1,...)) return true; can the REST be painted?
//             colours[vertex] = 0;                  no → wipe, try next colour
//         }
//     return false;                         ran out of colours
//
//   Without the gate the problem disappears — you'd paint everything colour 1
//   and always report success.
//
//   TWO WAYS A COLOUR IS REJECTED, both ending in "try the next":
//     at the gate   → neighbour already has it, never painted
//     after exploring → painted, everything below failed, wipe
//
//   `if (solve(...)) return true;` — the DECIDING shape. Catch the answer or
//   every path falls through to the wipe and the method always returns false.
//
// WHY THE WIPE, given the next iteration overwrites anyway: because sometimes
//   THERE IS NO NEXT ITERATION. When the loop runs out of colours and returns
//   false, nothing overwrites — the abandoned attempt's paint just sits there.
//   Triangle m=2: dot 1 paints itself 2, fails, has no colour 3, returns false
//   STILL PAINTED. Back at dot 0, isSafe(0,2) sees dot 1 as colour 2 and
//   rejects — but dot 1 isn't really 2. The check is reading a ghost.
//   A dot is painted only while it's part of the attempt being explored.
//
// isSafe — walk everyone this dot touches; if any already has colour c, no.
//     if (colours[neighbour] == c)     ← the NEIGHBOUR's colour
//     if (colours[vertex] == c)        ← WRONG, compares the dot to itself
//   return false exits the moment it finds a problem; return true only after
//   the loop finishes clean.
//
// Vertex 0 always passes the gate (nothing painted yet), but it CAN still
//   exhaust its colours if every subtree fails — and then there's nobody above
//   to back up to, so that false is the final answer.
//
// Java slips I made: `int colors` where `int[] colors` was needed; `i++`
//   instead of `c++` (infinite loop); `c < m` instead of `c <= m` (loses the
//   last colour); `for (int[] neighbour : adj.get(v))` when the list holds
//   plain ints.
//
// Time: O(m^n), Space: O(n + edges).
class Solution {
    boolean graphColoring(int[][] edges, int m, int n) {
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }
        for(int [] e:edges)
        {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        int colors[]=new int[n];
        return solve(0,adj,colors,m,n);
    }
    public static boolean solve(int vertices,List<List<Integer>> adj,int colors[],int m,int n)
    {
        if(vertices==n) return true;
        for(int c=1;c<=m;c++)
        {
            if(isSafe(vertices,c,adj,colors))
            {
                colors[vertices]=c;
                if(solve(vertices+1,adj,colors,m,n)) return true;
                colors[vertices]=0;
            }
        }
        return false;
    }
    public static boolean isSafe(int vertex,int c,List<List<Integer>> adj,int colors[])
    {
        for(int neighbour:adj.get(vertex))
        {
            if(colors[neighbour]==c) return false;
        }
        return true;
    }
}