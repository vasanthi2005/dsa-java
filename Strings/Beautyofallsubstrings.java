// Sum of beauty of all substrings
//
// BEAUTY = (most frequent letter's count) - (least frequent letter's count),
//   counting ONLY letters that actually appear.
//     "aab" → a:2, b:1 → 2 - 1 = 1
//     "abc" → a:1, b:1, c:1 → 1 - 1 = 0
//   Zero beauty means every letter present appears equally often.
//
//   Zero-beauty substrings are still summed — they just add 0. No need to
//   filter them; the problem's explanation only LISTS the non-zero ones
//   because those are the interesting cases.
//
// THE DATA STRUCTURE: a 26-slot frequency array, one slot per letter.
//
// THE KEY MOVE — DON'T RECOUNT, EXTEND. Every substring needs its own counts,
//   and there are O(n²) substrings. So fix a START, then extend the END one
//   character at a time:
//     start 0:  "a" → "aa" → "aab" → "aabc" → "aabcb"
//   Each step adds ONE character, so you bump one slot and the counts are
//   current. freq resets for each new start.
//
//     for (int i = 0; i < n; i++) {
//         int[] freq = new int[26];          // fresh per start
//         for (int j = i; j < n; j++) {
//             freq[s.charAt(j) - 'a']++;     // extend by one
//             sum += beauty(freq);
//         }
//     }
//
//   This "extend instead of recount" is the standard way to make nested
//   substring loops affordable — worth remembering as a pattern.
//
// THE BEAUTY HELPER: scan all 26 slots for max and min, but
//     if (f > 0)
//   is the bit that matters — a letter NOT PRESENT has count 0 and must be
//   SKIPPED, not treated as the minimum. In "aab" there's no 'c', so the min
//   is b's 1, not c's 0. This is the most common bug in this problem.
//
// Time: O(n² * 26) — with n <= 500 that's ~6M operations, fine.
// Space: O(1) — the 26 slots are a fixed size.
//
// `s.charAt(j) - 'a'` turns a letter into a slot index: 'a'→0, 'z'→25.
public class Beautyofallsubstrings {
    public int beautySum(String s) {
       int sum=0;
       for(int i=0;i<s.length();i++)
       {
            int freq[]=new int[26];
            for(int j=i;j<s.length();j++)
            {
                freq[s.charAt(j)-'a']++;
                sum+=beauty(freq);
            }
       }
       return sum;
    }
    public static int beauty(int arr[])
    {
        int min=Integer.MAX_VALUE,max=0;
        for(int i:arr)
        {
            if(i>0)
            {
                if(min>i)
                min=i;
                if(max<i)
                max=i;
            }
        }
        return max-min;
    }
}
