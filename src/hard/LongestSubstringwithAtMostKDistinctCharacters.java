/*
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 */

package hard;

import java.util.HashMap;

public class LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> hMap = new HashMap<Character, Integer>();
        int result = 0;
        if(s == null || s.length() == 0 || k < 1) return result;
        if(s.length() == 1){
        	if(k < 1) return result;
        	return 1;
        }
        
        int start = 0, end = 1;
        if(s.charAt(start) == s.charAt(end)) hMap.put(s.charAt(start), 2);
        else{
            hMap.put(s.charAt(start), 1);
            hMap.put(s.charAt(end), 1);
        }
        result = 1;
        while(end < s.length()){

            if(hMap.size() <= k){
            	int newLength = end - start + 1;
            	if(newLength > result) result = newLength;
            }else{
            	
            	while(start < end && hMap.size() > k){
            	    char startChar = s.charAt(start);
            		if(hMap.get(startChar) == 1) hMap.remove(startChar);
            		else{
            			hMap.put(startChar, hMap.get(startChar) - 1);
            		}
            		start++;
            	}
            }
            end++;
            if(end < s.length()){
                char endChar = s.charAt(end);
                if(hMap.containsKey(endChar)){
            	    hMap.put(endChar, hMap.get(endChar) + 1);
                }else{
            	    hMap.put(endChar, 1);
                }
            }
        }
        
        return result;
    }
}

/*
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
 */