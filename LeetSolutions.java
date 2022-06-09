import java.util.*;

/**
 * Compilation of my LeetCode solutions (mostly from Summer 2022)
 * 1. Date indicated for each sub-problem
 * 2. Currently incomplete solutions will be labeled #Working
 * 3. My thinking is described in the javadocs for a handful of solutions that where I deemed it necessary
 *
 * @author Zayn Khan
 */
public class LeetSolutions {

    public static void main(String[] args)
    {
        String s = "cat";
        String t = "tac";
        System.out.println(isAnagram(s,t));
    }


    /**
     * Assign Cookies (Easy) 6-3-2022
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s){

        int satisfied=0;
        Arrays.sort(s);
        for (Integer i:g)
        {
            for (int j=0;j<s.length;j++)
            {
                if (s[j]>=i) {
                    satisfied++;
                    s[j]=0;
                    break;
                }
            }
        }

        return satisfied;
    }
    /**
     * Maximum Subarray 6-3-2022 (Easy)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        int max = nums[0];
        int curr = 0;

        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];
            max=Math.max(max,curr);
            curr=curr<0 ? 0:curr;
        }

        return max;
    }

    /**
     * Merge K-sorted lists (Hard) - 6-3-2022
     * I'm surprised this approach was around the high 50th percentile for time-efficiency
     * I iterated through all elements in the list once, appending them to a priority queue,
     * then popping each item off the priority queue and adding it to a new linked-list in-order
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length==0)
            return null;
        PriorityQueue<Integer> result = new PriorityQueue<Integer>();
        for (int i=0;i<lists.length;i++)
        {
            if (lists[i]==null)
                continue;
            ListNode iterator=lists[i];
            while (iterator!=null)
            {
                result.add(iterator.val);
                iterator=iterator.next;
            }
        }
        ListNode head= !result.isEmpty() ? new ListNode(result.remove()): null;
            ListNode copy=head;
            while (!result.isEmpty()) {
                copy.next = new ListNode(result.remove());
                copy = copy.next;
            }

        return head;
    }

    /**
     * 6-1-2022 - Running Sum of 1D Array (Easy)
     *
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int runningSum=0;
        for (int i=0;i<nums.length;i++)
        {
            runningSum+=nums[i];
            result[i]=runningSum;
        }
        return result;
    }
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

      /* Saving this here in case I actually need it
      public class Pair<k,v>
      {
          k key;
          v value;
          public Pair(k k, v v)
          {
              this.key=k;
              this.value=v;
          }
          k getKey()
          {
              return key;
          }
          v getValue()
          {
              return value;
          }
      }
      */

    /**
     *
     * 5-31-2022 - 61. Rotate List (Medium)
     * Getting the size of the linked-list through n-iterations already increases time-complexity
     * My thinking was to determine the new position of each node using circular-indexing
     * then inserting into a new list based on the new order which I store in a hashmap
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k==0 || head==null) return head;

        HashMap<Integer, ListNode> arr = new HashMap<Integer,ListNode>();
        ListNode iterator = head;
        int size=0;
        int position=0;

        while (iterator!=null)
        {
            size++;
            iterator=iterator.next;
        }
        iterator=head;
        while (iterator!=null)
        {
            arr.put((position+k)%size,iterator);
            position++;
            iterator=iterator.next;
        }
        ListNode result=new ListNode(arr.get(0).val);
        int count=1;
        ListNode iterate=result;
        while (count<size)
        {
            iterate.next=new ListNode(arr.get(count).val);
            iterate=iterate.next;
            count++;
        }
        return result;
    }

    /**
     * 41. First Missing Positive (Hard) - 5-31-2022
     * The only thing that ups the difficulty of this seemingly simple problem are the complexity constraints:
     * O(1) space complexity and O(n) time-complexity at best
     * I allocated a single counter variable which updates while it sees the next logical # in the sequence
     * (this works fully if I pre-sort the array in ascending order)
     * The counter works ahead of each iteration, if result is not equal to the current iteration,
     * then the current result is the missing number
     *
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int result=1;
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++)
        {
            if (result==nums[i])
                result++;
        }
        return result;
    }
    /**
     * 5-30-2022 - Valid Anagram (Easy)
     * If the strings in sorted order are not equal,
     * there is no possible arrangement of the chars that can make them similar
     * 
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) return false;
        char[] sortedS = s.toCharArray();
        char[] sortedT = t.toCharArray();
        Arrays.sort(sortedS);
        Arrays.sort(sortedT);
        for (int i=0; i<sortedS.length;i++)
        {
            if (sortedS[i]!=(sortedT[i])) return false;
        }
        return true;

    }
    /**
     * 5-27-2022 - 1855. Max Distance Between Pair of Values (Medium)
     *
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        {
            int i=0;
            int j=0;
            int maxDist=0;
            while (i<nums1.length&&j<nums2.length)
            {
                if (nums1[i]>nums2[j])
                    i++;
                else
                {
                    maxDist=Math.max(j-i, maxDist);
                    j++;
                }
            }
            return maxDist;
        }
    }
    /**
     * 5-27-2022 - 2078. Farthest distance between two houses of different color (Easy)
     *
     * @param colors
     * @return
     */
    public int maxDistance(int[] colors) {
        int maxDist=0;
        for (int i=0; i<colors.length-1;i++)
        {
            for (int j=i+1;j<colors.length;j++)
            {
                if (colors[i]!=colors[j])
                    maxDist=Math.max(maxDist,j-i);
            }
        }
        return maxDist;
    }

    /**
     * 2016. Maximum Difference Between Increasing Elements (Easy)
     * 5-27-2022
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        int currNum=nums[0];
        int maxDiff=0;
        for (int i=0; i<nums.length;i++)
        {
            if (currNum<nums[i])
                maxDiff=Math.max(maxDiff,nums[i]-currNum);
            else currNum=nums[i];
        }
        return maxDiff<=0 ? -1: maxDiff;
    }
    /**
     * 5-27-2022 - Buying and selling stock (Easy)
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices)
    {
        int currPrice=prices[0];
        int profit=0;
        for (int i=1; i<prices.length;i++)
        {
            if (prices[i]>currPrice)
                profit = Math.max(profit,prices[i]-currPrice);
            else currPrice=prices[i];
        }
        return profit<=0 ? 0:profit;
    }

    /**
     * 5-27-2022: Remove from Array, in-place (Easy)
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val)  {
        int inplace=0;
        for (int i=0;i<nums.length;i++)
        {
            if (val!=nums[i])
            {
                nums[inplace]=nums[i];
                inplace++;
            }

        }
        return inplace;

    }

    /**
     * 5-27-2022 - Longest Uncommon Subsequence II (Medium), I do not recommend this problem, there were issues upon
     * issues with this one for me, personally
     *
     * Incomplete (#Working)
     * @param strs
     * @return
     */
    public static int findLUSlength(String[] strs) {
        int maximum=0;
        String maxStr="";
        String longestSequence="";
        if (isDupe(strs)) return -1;
        for (int i=0; i<strs.length;i++)
        {
            if (strs[i].length()>maxStr.length())
            {
                maxStr=strs[i];
                longestSequence=cleanString(maxStr);
                maximum=maxStr.length();
                continue;
            }
            String currSequence=cleanString(strs[i]);

            if (!maxStr.equals(strs[i]))
            {
                int a = maxStr.contains(strs[i]) ? -1: Math.max(strs[i].length(),maxStr.length());
                if (strs[i].length()>=maxStr.length()) maximum=a;
                else if (!longestSequence.contains(currSequence)&&maximum==-1) maximum=strs[i].length();
            }
            else
                maximum=-1;
        }

        return maximum;
    }
    public static String cleanString(String s)
    {
        String result="";
        for (int i=0;i<s.length();i++)
        {
            result+= !result.contains(s.substring(i,i+1)) ? s.substring(i,i+1):"";
        }
        return result;
    }
    public static boolean isDupe(String[] str)
    {
        HashMap<String,String> set = new HashMap<String,String>();
        int maxLength=0;
        int tally=0;
        boolean length=true;
        List<Integer> tallies = new ArrayList<Integer>();
        for (int i=0;i<str.length;i++)
        {
            if (set.containsKey(str[i])) tallies.add(2);
            else set.put(str[i],str[i]);
            maxLength = Math.max(maxLength, str[i].length());
            if (str[i].length() != maxLength&&i!=0) length = false;
        }
        for (Integer i: tallies)
            tally+=i;
    return tally==str.length&&length;

    }
    /**
     * 5-26-2022 - 66. Plus One (Easy)
     * Fix carry-overs recursively, while the number at position 'x' + 1 is >= 10
     * 
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        return fixCarry(digits, digits.length-1);
    }
    public int[] fixCarry(int[] c, int index)
    {
        if (c[index]+1<10) c[index]++;
        else
        {
            c[index]=0;
            if (index-1<0)
            {
                int[] extended = new int[c.length+1];
                extended[0]=1;
                int count=1;
                for (Integer i:c)
                {
                    extended[count]=i;
                    count++;
                }
                return extended;
            }
            else  c=fixCarry(c,index-1);
        }
        return c;
    }

    /**
     * 5-25-2022 str() - Easy
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.equals(needle))
            return 0;
        for (int i=0;i<haystack.length();i++)
        {
            int savePos=i;
            boolean contains=true;
            if (i+needle.length()<=haystack.length())
            {
                for (int j=0; j<needle.length();j++)
                {
                    if (!haystack.substring(i,i+1).equals(needle.substring(j,j+1)))
                    {
                        contains=false;
                        break;
                    }
                    i++;
                }
            }
            else break;
            if (contains) return savePos;
            i=savePos;
        }
        return -1;

    }


    /**
     * 5-25-2022
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i=0; i<nums.length;i++)
        {
            if (map.containsKey((nums[i])) )return true;
            map.put(nums[i],nums[i]);
        }
        return false;
    }

    /**
     * 5-25-2022
     * Store each element and it's index as key-value pairs in a H.M.
     * If the same element is found within index-range (k) of the current
     * return true
     * 
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i=0; i<nums.length;i++)
        {
            if (map.containsKey(nums[i]) && i-map.get(nums[i])<=k)return true;
            map.put(nums[i],i);
        }
        return false;
    }

    /**
     * 5-23-2022
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {

        int maximum = 0;
        int j = height.length-1;
        int i = 0;
        while (j>=i)
        {
            int temp= Math.min(height[i],height[j])*(j-i);
            maximum=Math.max(temp,maximum);
            if (height[i]<height[j])i++;
            else j--;
        }
        return maximum;
    }

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    /**
     * 669. 5-23-2022 - Trim BST (Medium)
     * 3 methods below are helpers
     * Approaching this in a similar manner as the recursive in-order, pre-order, or post-order traversals,
     * but slightly different.  I put some good work into this problem and managed to get the optimal solution
     * One of the bigger sub-problems for this one was re-structuring the tree if the main root had to be omitted
     * Well? Just cut off either the left or right subtree while the root is still invalid 
     * (ex: the root is less than the low-high range, so anything to the left/less can also be cut right away)
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root==null)
            return null;
        TreeNode toCall = null;
        toCall = fixRoot(root,low,high, toCall);
        search(toCall, low, high, false, toCall);
        return toCall;
    }
    public TreeNode fixRoot(TreeNode root, int low, int high, TreeNode temp)
    {
        temp = root;
        if (root==null)
            return null;
        else if (root.val==low && low==high)
            return root;
        else if (root.val>high)
            temp = fixRoot(root.left,low,high, temp);
        else if (root.val<low)
            temp = fixRoot(root.right,low,high, temp);

        return temp;
    }
    public void search(TreeNode curr, int low, int high, boolean left, TreeNode parent)
    {
        if (curr==null)
            return;

        search(curr.left, low, high, true, curr);
        search(curr.right, low, high, false, curr);
        if (curr.val<low || curr.val>high)
        {
            if (left)
                parent.left = availableChild(curr);
            else
                parent.right = availableChild(curr);

            curr=null;
            return;
        }


    }
    public TreeNode availableChild(TreeNode main)
    {
        if (main.left!=null)
            return main.left;
        else if (main.right!=null)
            return main.right;
        else return null;
    }

    /**
     * 5-17-2022
     * #Unoptimal
     * This is quite messy
     * 
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String>[] results = new ArrayList[list1.length + list2.length];
        Hashtable<Integer, String> l1 = new Hashtable<Integer, String>(list1.length);
        Hashtable<Integer, String> l2 = new Hashtable<Integer, String>(list2.length);
        Hashtable<String, Integer> twoStringz = new Hashtable<String, Integer>(list2.length);
        for (int i = 0; i < list1.length; i++)
            l1.put(i, list1[i]);
        for (int j = 0; j < list2.length; j++)
        {
            l2.put(j, list2[j]);
            twoStringz.put(list2[j], j);
        }
        for (int z = 0; z < results.length; z++)
            results[z] = new ArrayList<String>();
        for(int k = 0; k < list1.length; k++)
        {
            if(l2.contains(list1[k]))
            {
                results[twoStringz.get(list1[k]) + k].add(list1[k]);
            }
        }


        for (List<String> s: results)
        {

            if (s.size() != 0)
            {
                int count = 0;
                String[] toReturn = new String[s.size()];
                for (String r: s)
                {
                    toReturn[count] = r;
                    count++;
                }
                return toReturn;
            }
        }



        return new String[1];
    }
    /**
     * 5-16-2022
     * #Unoptimal
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        List<String> inputs = new ArrayList<String>();
        List<String> validInputs = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++)
        {
            for (int j = 0; j < strs[i].length(); j++)
            {
                inputs.add(strs[i].substring(0,j+1));
            }

        }
        for (int i = 0; i < inputs.size(); i++)
        {
            boolean valid = true;
            for (String s: strs)
            {
                if (!s.startsWith(inputs.get(i)))
                {
                    valid = false;
                    break;
                }
            }
            if (valid)
                validInputs.add(inputs.get(i));

        }
        for (String s: validInputs)
            System.out.println(s);
        String maximal = "";
        if (validInputs.size() == 0)
            return "";
        if (validInputs.size() == 1)
            return validInputs.get(0);

        for (int i = 0; i < validInputs.size() - 1; i++)
        {
            maximal = validInputs.get(i);
            if (validInputs.get(i).length() < validInputs.get(i+1).length())
                maximal = validInputs.get(i+1);
        }

        return maximal;
    }

    /**
     * 5-15-2022
     * Length of Last Word in a sentence (Easy)
     * 
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] items = s.split(" ");
        return items[items.length-1].length();
    }

    /**
     * 5-15-2022
     * #Suboptimal
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0.0;
        PriorityQueue<Integer> d = new PriorityQueue<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for (Integer i: nums1)
            d.add(i);
        for (Integer j: nums2)
            d.add(j);
        while (!d.isEmpty())
            list.add(d.remove());

        int mid = (list.size()-1)/2;
        if (list.size() % 2 == 0)
            median = ((double) list.get(mid) + (double) list.get(mid+1))/2;
        else
            median = list.get(mid);
        for (Integer s: list)
            System.out.println(s + ":");
        return median;
    }

    /**
     * 3-18-2022 Two Sum (Easy)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++)
        {
            map.put(nums[i], i);
        }
        int[] toReturn = new int[2];
        for (int i = 0; i < nums.length; i++)
        {
            int searchKey = target-nums[i];
            if (map.containsKey(searchKey) && map.get(searchKey) != i)
            {
                toReturn[0] = i;
                toReturn[1] = map.get(searchKey);
                break;
            }
        }

        return toReturn;
    }
}
