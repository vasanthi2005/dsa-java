// For loop
//Approach : optimal approach for sumrange by using arthematic formula
// time: O(1) and space: O(1)
package basics;

class Forloop {
    public static int sumRange(int low, int high) {
        return high * (high + 1) / 2 - (low - 1) * low / 2;
    }
}