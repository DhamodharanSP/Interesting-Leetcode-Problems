package Sliding_Window;

import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int q = queries.length;
        // Sorting the indices based on Array elements and use these indices to retrieve the array elements in sorted order
        Integer[] queryIdx = new Integer[q];
        for(int i = 0; i < q; ++i)
            queryIdx[i] = i;

        Arrays.sort(queryIdx, (i, j) -> Integer.compare(queries[i], queries[j]));

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] count = new int[n+1];

        int[] res = new int[q];

        // sliding window to compute total inActive servers in a sorted Monotonic order of queries
        int left = 0, right = 0, active = 0;
        for(int qId : queryIdx)
        {
            int query = queries[qId];
            // First expand right
            while(right < logs.length && logs[right][1] <= query)
            {
                count[logs[right][0]]++;
                if(count[logs[right][0]] == 1) active++;
                right++;
            }
            // Then shrink left
            while(left < right && logs[left][1] < query - x)
            {
                count[logs[left][0]]--;
                if(count[logs[left][0]] == 0) active--;
                left++;
            }
            res[qId] = n - active; // Total servers - Active servers = inActive servers
        }
        return res;
    }
}

public class LC2747
{
    void main()
    {
        Solution solution = new Solution();
        int n = 3;
        int[][] logs = {{1,3}, {2,6}, {1,5}};
        int x = 5;
        int[] queries = {10,11};
        int[] res = solution.countServers(n, logs, x, queries);
        for(int num : res)
            System.out.print(num + ", ");
    }
}
