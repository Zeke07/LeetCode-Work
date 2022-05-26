import java.util.*;

/**
 * Compilation of my LeetCode solutions
 * 1. Date indicated for each sub-problem
 * 2. Currently incomplete solutions will be labeled #Working
 *
 * @author Zayn Khan
 */
public class LeetSolutions {

    /**
     * 5-26-2022
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
     * 5-25-2022
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
     * 5-23-2022
     * 3 methods below are helpers
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
     * 3-18-2022
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
