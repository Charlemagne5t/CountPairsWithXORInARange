public class Solution {
    public int countPairs(int[] nums, int low, int high) {
        Trie trie = new Trie();
        int countLesserThanLow = 0;
        int countLesserThanHigh = 0;
        int max = high;
        for(int x : nums){
            max = Math.max(x, max);
        }
        int maxH = (int)(Math.log(max) / Math.log(2)) + 1;
        for (int i = 0; i < nums.length; i++) {
            countLesserThanLow += trie.countSmallerXORThenK(nums[i], low, maxH);
            countLesserThanHigh += trie.countSmallerXORThenK(nums[i], high + 1, maxH);
            trie.insert(nums[i], maxH);
        }

        return countLesserThanHigh - countLesserThanLow;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(int num, int maxH) {
        TrieNode temp = root;
        for (int i = maxH; i >= 0; i--) {
            int bit = (num >> i ) & 1;
            if(bit == 0){
                if(temp.zero == null){
                    temp.zero = new TrieNode();
                }
                temp.zero.count++;
                temp = temp.zero;
            }else {
                if(temp.one == null){
                    temp.one = new TrieNode();
                }
                temp.one.count++;
                temp = temp.one;
            }
        }
    }

    int countSmallerXORThenK(int num, int k, int maxH) {
        TrieNode temp = root;
        int countPairs = 0;
        for (int i = maxH; i >= 0 && temp != null; i--) {
            int x = (num >> i) & 1;
            int y = (k >> i) & 1;
            if (y == 1) {
                if (x == 1 && temp.one != null) {
                    countPairs += temp.one.count;
                } else if (x == 0 && temp.zero != null) {
                    countPairs += temp.zero.count;
                }
                temp = x == 0 ? temp.one : temp.zero;
            } else {
                temp = x == 0 ? temp.zero : temp.one;
            }

        }
        return countPairs;
    }

}

class TrieNode {
    TrieNode one;
    TrieNode zero;

    int count = 0; // number of elements stored in this node

}


