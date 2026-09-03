package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement2 {
    // ============================================================
    // Majority Element II — elements appearing more than n/3 times
    // ============================================================
    // KEY OBSERVATION: at most TWO elements can qualify. Three would each need
    // more than n/3 occurrences, totalling more than n elements — impossible.
    // That bound is what makes the O(1) space solution possible.

    // ---------- Version 1: frequency map ----------
    // Approach: count every value, then collect keys whose count exceeds n/3.
    // Time: O(n), Space: O(n) — the map holds every distinct value
    // Note: nums.length / 3 is integer division. For n = 7 it gives 2, and > 2
    // means at least 3 occurrences. Correct, since 7/3 = 2.33 and we need
    // strictly more than that.

    public List<Integer> majorityElementTwoMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length / 3;

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > n) {
                list.add(e.getKey());
            }
        }
        return list;
    }

    // ---------- Version 2: Boyer-Moore voting, two candidates ----------
    // Approach: two candidates, two counters. Each element either matches a
    // candidate (increment it), fills an empty slot (claim it), or
    // cancels one vote from BOTH candidates. Three distinct values
    // annihilate each other — the n/3 analogue of pairwise cancellation
    // in the n/2 version.
    // Time: O(n), two passes. Space: O(1) — four ints regardless of input size.
    // A million distinct values would cost the HashMap ~40MB; this costs 16
    // bytes. Paid for with a second pass, which is cheap on an array.
    // Note: BRANCH ORDER MATTERS. Match existing candidates first, then fill empty
    // slots, then decrement. Checking empty slots first would let one value
    // occupy both candidate positions.
    // The verification pass is REQUIRED here, unlike the n/2 version — the
    // voting pass always yields two candidates whether or not anything
    // actually exceeds n/3.
    // Verification uses else-if so a value can't be counted into both
    // counters if the candidates ever coincide.

    public List<Integer> majorityElementTwo(int[] nums) {
        int cand1 = Integer.MIN_VALUE, cand2 = Integer.MIN_VALUE;
        int count1 = 0, count2 = 0;

        for (int i : nums) {
            if (i == cand1) {
                count1++;
            } else if (i == cand2) {
                count2++;
            } else if (count1 == 0) {
                cand1 = i;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = i;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i : nums) {
            if (i == cand1)
                count1++;
            else if (i == cand2)
                count2++;
        }

        List<Integer> list = new ArrayList<>();
        if (count1 > nums.length / 3)
            list.add(cand1);
        if (count2 > nums.length / 3)
            list.add(cand2);
        return list;
    }
}
