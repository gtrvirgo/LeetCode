package com.leetcode;

import org.junit.Test;

import com.leetcode.Solutions.ListNode;
import com.leetcode.Solutions.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;

public class TestSolutions {

	private static Solutions solutions;

	@BeforeClass
	public static void init() {
		solutions = new Solutions();
	}

	@Test
	public void TestIsPalindrome() {
		String s = "race a car";
		assertFalse(solutions.isPalindrome(s));
	}
	
	@Test
	public void TestConvert() {
		assertThat(solutions.convert("PAYPALISHIRING", 3), is("PAHNAPLSIIGYIR"));
		assertThat(solutions.convert("PAYPALISHIRING", 4), is("PINALSIGYAHRPI"));
	}

	@Test
	public void TestSingleNumber() {
		int[] nums = { 4, 1, 2, 1, 2 };
		assertThat(4, is(solutions.singleNumber(nums)));
	}

	@Test
	public void TestRemoveDuplicates() {
		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		assertThat(5, is(solutions.removeDuplicates(nums)));
	}

	@Test
	public void TestIsValid() {
		String s = "()";
		assertTrue(solutions.isValid(s));
		s = "()[]{}";
		assertTrue(solutions.isValid(s));
		s = "(]";
		assertFalse(solutions.isValid(s));
		s = "([)]";
		assertFalse(solutions.isValid(s));
		s = "{[]}";
		assertTrue(solutions.isValid(s));
	}

	@Test
	public void TestFindSubString() {
		String s = "wordgoodgoodgoodbestword";
		String[] words = { "word", "good", "best", "good" };
		assertThat(new Object[] { 8 }, equalTo(solutions.findSubstring(s, words).toArray()));
		s = "";
		words = new String[] {};
		assertThat(new Object[] {}, equalTo(solutions.findSubstring(s, words).toArray()));
		s = "barfoothefoobarman";
		words = new String[] {"foo", "bar"};
		Set<Integer> expecteds = new HashSet<>(Arrays.asList(0, 9));
		assertThat(new HashSet<Integer>(solutions.findSubstring(s, words)), is(expecteds));
	}

	@Test
	public void TestMoveZeroes() {
		int nums[] = { 0, 1, 0, 3, 12 };
		solutions.moveZeroes(nums);
		assertThat(new int[] { 1, 3, 12, 0, 0 }, equalTo(nums));
		nums = new int[] { 1, 0 };
		solutions.moveZeroes(nums);
		assertThat(new int[] { 1, 0 }, equalTo(nums));
	}

	@Test
	public void TestRemoveDuplicatesII() {
		int nums[] = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
		int len = solutions.removeDuplicatesII(nums);
		int[] actuals = Arrays.copyOf(nums, len);
		int[] expecteds = { 0, 0, 1, 1, 2, 3, 3 };
		assertThat(expecteds, equalTo(actuals));
	}
	@Test
	public void testLengthOfLongestSubstring() {
		assertThat(solutions.lengthOfLongestSubstring("abcabcbb"), is(3));
	}
	@Test
	public void testMaxValue() {
		int[][] grid = {
				{ 1, 3, 1, 2 },
				{ 1, 5, 1, 3 },
				{ 4, 2, 1, 4 },
				{ 3, 2, 6, 5 }
		};
		assertThat(solutions.maxValue(grid), is(24));
		grid = new int[][] {
			{1,2},
			{5,6},
			{1,1}
		};
		assertThat(solutions.maxValue(grid), is(13));
	}
	@Test
	public void testMerge() {
		int[] nums1 = { 1, 2, 3, 0, 0, 0 };
		int[] nums2 = { 2, 5, 6 };
		int m = 3;
		int n = 3;
		solutions.merge(nums1, m, nums2, n);
	}
	@Test
	public void testSortColors() {
		int[] nums = { 2, 0, 2, 1, 1, 0 };
		solutions.sortColors(nums);
		int[] expecteds = { 0, 0, 1, 1, 2, 2 };
		assertArrayEquals(expecteds, nums);
		nums = new int[] { 2, 0, 1 };
		expecteds = new int[] { 0, 1, 2 };
		solutions.sortColors(nums);
		assertArrayEquals(expecteds, nums);
	}
	@Test
	public void testSubSort() {
		int[] nums = { 1, 5, 4, 3, 2, 6, 7 };
		int[] expecteds = { 1, 4 };
		assertArrayEquals(solutions.subSort(nums), expecteds);
		nums = new int[] {};
		expecteds = new int[] { -1, -1 };
		assertArrayEquals(solutions.subSort(nums), expecteds);
		nums = new int[] { 1,2,4,7,10,11,7,12,6,7,16,18,19 };
		expecteds = new int[] { 3, 9 };
		assertThat(solutions.subSort(nums), is(expecteds));
	}
	@Test
	public void testRemoveElements() {
		int nums[] = { 3, 4, 2, 4, 5, 5 };
		ListNode head = new ListNode(nums[0]);
		ListNode node = head;
		for (int i = 1; i < nums.length; i++) {
			node.next = new ListNode(nums[i]);
			node = node.next;
		}
		ListNode ans = solutions.removeElements(head, 4);
		int[] expecteds = { 3, 2, 5, 5 };
		for (int num : expecteds) {
			assertThat(ans.val, is(num));
			ans = ans.next;
		}
		assertNull(ans);
	}
	@Test
	public void testAddTwoNumbers() {
		int nums1[] = { 4, 5, 9 };
		int nums2[] = { 3, 8 };
		ListNode l1 = new ListNode(nums1[0]);
		ListNode l2 = new ListNode(nums2[0]);
		ListNode p1 = l1;
		ListNode p2 = l2;
		for (int i = 1; i < nums1.length; i++) {
			p1.next = new ListNode(nums1[i]);
			p1 = p1.next;
		}
		for (int i = 1; i < nums2.length; i++) {
			p2.next = new ListNode(nums2[i]);
			p2 = p2.next;
		}
		ListNode ans = solutions.addTwoNumbers(l1, l2);
		int[] expecteds = { 7, 3, 0, 1 };
		for (int num : expecteds) {
			assertThat(ans.val, is(num));
			ans = ans.next;
		}
		assertNull(ans);
	}
	@Test
	public void testPartition() {
		int[] values = { 1, 4, 3, 2, 5, 2 };
		int[] expecteds = { 1, 2, 2, 4, 3, 5 };
		int x = 3;
		ListNode head = new ListNode(values[0]);
		ListNode cur = head;
		for (int i = 1; i < values.length; i++) {
			cur.next = new ListNode(values[i]);
			cur = cur.next;
		}
		ListNode ans = solutions.partition(head, x);
		for (int i = 0; i < expecteds.length; i++) {
			assertThat(ans.val, is(expecteds[i]));
			ans = ans.next;
		}
		assertNull(ans);
	}
	@Test
	public void testIsPalindrome() {
		int[] values = { 1, 2, 3, 2, 1 };
		ListNode head = new ListNode(values[0]);
		ListNode node = head;
		for (int i = 1; i < values.length; i++) {
			node.next = new ListNode(values[i]);
			node = node.next;
		}
		assertTrue(solutions.isPalindrome(head));
	}
	@Test
	public void testMaxSlidingWindow() {
		int[] nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		int[] expecteds = { 3, 3, 5, 5, 6, 7 };
		assertThat(solutions.maxSlidingWindow(nums, k), is(expecteds));
	}
	@Test
	public void testIsSubtree() {
		TreeNode s = Utils.createBinaryTree(new Integer[] { 3, 4, 5, 1, 2 });
		TreeNode t = Utils.createBinaryTree(new Integer[] { 4, 1, 2 });
		assertTrue(solutions.isSubtree(s, t));
		s = Utils.createBinaryTree(new Integer[] { 3, 4, 5, 1, 2, null, null, null, null, 0, null });
		assertFalse(solutions.isSubtree(s, t));
	}
	@Test
	public void testMaxProfit() {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		assertThat(solutions.maxProfit(prices), is(5));
		prices = new int[] { 7, 6, 4, 3, 1 };
		assertThat(solutions.maxProfit(prices), is(0));
	}
	@Test
	public void testMaxProfitII() {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		assertThat(solutions.maxProfitII(prices), is(7));
		prices = new int[] { 1, 2, 3, 4, 5 };
		assertThat(solutions.maxProfitII(prices), is(4));
		prices = new int[] { 7, 6, 4, 3, 1 };
		assertThat(solutions.maxProfitII(prices), is(0));
	}
	@Test
	public void testMinDistance() {
		assertThat(solutions.minDistance("horse", "ros"), is(3));
		assertThat(solutions.minDistance("intention", "execution"), is(5));
	}
	@Test
	public void testLongestPalindrome() {
		assertThat(solutions.longestPalindrome("babad"), is("bab"));
		assertThat(solutions.longestPalindrome("cbbd"), is("bb"));
	}
	@Test
	public void testSolveNQueens() {
		Set<List<String>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(
			".Q..",
			"...Q",
			"Q...",
			"..Q."
		));
		expecteds.add(Arrays.asList(
			"..Q.",
			"Q...",
			"...Q",
			".Q.."
		));
		assertThat(new HashSet<List<String>>(solutions.solveNQueens(4)), is(expecteds));
	}
	@Test
	public void testLargestBSTSubtree() {
		Integer[] nums = { 10, 5, 15, 1, 8, null, 7 };
		assertThat(solutions.largestBSTSubtree(Utils.createBinaryTree(nums)),
				is(3));
	}
	@Test
	public void testletterCombinations() {
		List<String> expecteds = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
		assertThat(solutions.letterCombinations("23"), is(expecteds));
	}
	@Test
	public void testPermute() {
		int[] nums = { 1, 2, 3 };
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(1, 2, 3));
		expecteds.add(Arrays.asList(1, 3, 2));
		expecteds.add(Arrays.asList(2, 1, 3));
		expecteds.add(Arrays.asList(2, 3, 1));
		expecteds.add(Arrays.asList(3, 1, 2));
		expecteds.add(Arrays.asList(3, 2, 1));
		assertThat(new HashSet<List<Integer>>(solutions.permute(nums)), is(expecteds));
	}
	@Test
	public void testPermuteUnique() {
		int[] nums = { 1, 1, 2 };
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(1, 1, 2));
		expecteds.add(Arrays.asList(1, 2, 1));
		expecteds.add(Arrays.asList(2, 1, 1));
		assertThat(new HashSet<List<Integer>>(solutions.permuteUnique(nums)), is(expecteds));
	}
	@Test
	public void testGenerateParenthesis() {
		Set<String> expecteds = new HashSet<>(Arrays.asList(
			"((()))",
	        "(()())",
	        "(())()",
	        "()(())",
	        "()()()"
	    ));
		assertThat(new HashSet<String>(solutions.generateParenthesis(3)), is(expecteds));
	}
	@Test
	public void testPathSum() {
		Integer[] nums = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 };
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new ArrayList<>();
		expecteds.add(Arrays.asList(5, 4, 11, 2));
		expecteds.add(Arrays.asList(5, 8, 4, 5));
		assertThat(solutions.pathSum(root, 22), is(expecteds));
	}
	@Test
	public void testHasPathSum() {
		Integer[] nums = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 };
		TreeNode root = Utils.createBinaryTree(nums);
		assertTrue(solutions.hasPathSum(root, 22));
	}
	@Test
	public void testLevelOrder() {
		Integer[] nums = { 3, 9, 20, null, null, 15, 7 };
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new ArrayList<>();
		expecteds.add(Arrays.asList(3));
		expecteds.add(Arrays.asList(9, 20));
		expecteds.add(Arrays.asList(15, 7));
		assertThat(solutions.levelOrder(root), is(expecteds));
	}
	@Test
	public void testReverse() {
		assertThat(solutions.reverse(123), is(321));
		assertThat(solutions.reverse(-123), is(-321));
		assertThat(solutions.reverse(120), is(21));
		assertThat(solutions.reverse(1534236469), is(0));
	}
	@Test
	public void testPalindromeNumber() {
		assertTrue(solutions.isPalindrome(121));
		assertFalse(solutions.isPalindrome(-121));
		assertFalse(solutions.isPalindrome(10));
	}
	@Test
	public void testRemoveElement() {
		int[] nums = { 3,2,2,3 };
		assertThat(solutions.removeElement(nums, 3), is(2));
		nums = new int[] { 0,1,2,2,3,0,4,2 };
		assertThat(solutions.removeElement(nums, 2), is(5));
	}
	@Test
	public void testTwoSum() {
		int[] nums = { 2, 7, 11, 5 }; 
		int target = 9;
		int[] expecteds = { 0, 1 };
		assertThat(solutions.twoSum(nums, target), is(expecteds));
	}
	@Test
	public void testThreeSum() {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(-1, 0, 1));
		expecteds.add(Arrays.asList(-1, -1, 2));
		assertThat(new HashSet<List<Integer>>(solutions.threeSum(nums)), is(expecteds));
	}
	@Test
	public void testSearchInsert() {
		int[] nums = { 1, 3, 5, 6 };
		assertThat(solutions.searchInsert(nums, 5), is(2));
		assertThat(solutions.searchInsert(nums, 2), is(1));
		assertThat(solutions.searchInsert(nums, 7), is(4));
		assertThat(solutions.searchInsert(nums, 0), is(0));
	}
	@Test
	public void testStrStr() {
		assertThat(solutions.strStr("hello", "ll"), is(2));
		assertThat(solutions.strStr("aaaaa", "bba"), is(-1));
		assertThat(solutions.strStr("aaa", "aaa"), is(0));
	}
	@Test
	public void testMyPow() {
		assertThat(solutions.myPow(2.00000, 10), is(1024.00000));
		assertThat((double) Math.round(solutions.myPow(2.10000, 3) * 100000) / 100000, is(9.26100));
		assertThat(solutions.myPow(2.00000, -2), is(0.25000));
	}
	@Test
	public void testLongestCommonPrefix() {
		String[] strs = { "flower", "flow", "flight" };
		assertThat(solutions.longestCommonPrefix(strs), is("fl"));
		strs = new String[] { "dog", "racecar", "car" };
		assertThat(solutions.longestCommonPrefix(strs), is(""));
	}
	@Test
	public void testCountAndSay() {
		assertThat(solutions.countAndSay(1), is("1"));
		assertThat(solutions.countAndSay(4), is("1211"));
		assertThat(solutions.countAndSay(5), is("111221"));
	}
	@Test
	public void testCombinationSum() {
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(7));
		expecteds.add(Arrays.asList(2, 2, 3));
		int[] candidates = { 2, 3, 6, 7 };
		int target = 7;
		assertThat(new HashSet<List<Integer>>(solutions.combinationSum(candidates, target)), is(expecteds));
		
		expecteds.clear();
		expecteds.add(Arrays.asList(3, 5));
		expecteds.add(Arrays.asList(2, 3, 3));
		expecteds.add(Arrays.asList(2, 2, 2, 2));
		candidates = new int[] { 2, 3, 5 };
		target = 8;
		assertThat(new HashSet<List<Integer>>(solutions.combinationSum(candidates, target)), is(expecteds));
	}
	@Test
	public void testCombinationSum2() {
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(1, 7));
		expecteds.add(Arrays.asList(1, 2, 5));
		expecteds.add(Arrays.asList(2, 6));
		expecteds.add(Arrays.asList(1, 1, 6));
		assertThat(new HashSet<List<Integer>>(solutions.combinationSum2(candidates, target)), is(expecteds));
	}
	@Test
	public void testPlusOne() {
		int[] digits = { 1, 2, 3 };
		assertThat(solutions.plusOne(digits), is(new int[] { 1, 2, 4 }));
		digits = new int[] { 4, 3, 2, 1 };
		assertThat(solutions.plusOne(digits), is(new int[] { 4, 3, 2, 2 }));
	}
	@Test
	public void testAddBinary() {
		assertThat(solutions.addBinary("11", "1"), is("100"));
		assertThat(solutions.addBinary("1010", "1011"), is("10101"));
	}
	@Test
	public void testSingleNumberII() {
		int[] nums = { 2, 2, 3, 2 };
		assertThat(solutions.singleNumberII(nums), is(3));
		nums = new int[] { 0, 1, 0, 1, 0, 1, 99 };
		assertThat(solutions.singleNumberII(nums), is(99));
	}
	@Test
	public void testMySqrt() {
		assertThat(solutions.mySqrt(4), is(2));
		assertThat(solutions.mySqrt(8), is(2));
	}
	@Test
	public void testIsPerfectSquare() {
		assertTrue(solutions.isPerfectSquare(16));
		assertFalse(solutions.isPerfectSquare(14));
		assertTrue(solutions.isPerfectSquare(808201));
	}
	@Test
	public void testMaxSubArray() {
		int[] nums = { -2,1,-3,4,-1,2,1,-5,4 };
		assertThat(solutions.maxSubArray(nums), is(6));
		nums = new int[] { 1 };
		assertThat(solutions.maxSubArray(nums), is(1));
	}
	@Test
	public void testGenerate() {
		List<List<Integer>> expecteds = new ArrayList<>();
		expecteds.add(Arrays.asList(1));
		expecteds.add(Arrays.asList(1, 1));
		expecteds.add(Arrays.asList(1, 2, 1));
		expecteds.add(Arrays.asList(1, 3, 3, 1));
		expecteds.add(Arrays.asList(1, 4, 6, 4, 1));
		assertThat(solutions.generate(5), is(expecteds));
	}
	@Test
	public void testGetRow() {
		assertThat(solutions.getRow(3), is(Arrays.asList(1, 3, 3, 1)));
		assertThat(solutions.getRow(0), is(Arrays.asList(1)));
		assertThat(solutions.getRow(1), is(Arrays.asList(1, 1)));
	}
	@Test
	public void testReverseString() {
		char[] s = { 'h', 'e', 'l', 'l', 'o' };
		char[] expecteds = { 'o', 'l', 'l', 'e', 'h' };
		solutions.reverseString(s);
		assertThat(s, is(expecteds));
		s = new char[] { 'H', 'a', 'n', 'n', 'a', 'h' };
		expecteds = new char[] { 'h', 'a', 'n', 'n', 'a', 'H' };
		solutions.reverseString(s);
		assertThat(s, is(expecteds));
	}
	@Test
	public void testLengthOfLastWord() {
		assertThat(solutions.lengthOfLastWord("Hello World"), is(5));
	}
	@Test
	public void testLevelOrderBottom() {
		Integer[] nums = { 3, 9, 20, null, null, 15, 7 };
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new LinkedList<>();
		expecteds.add(Arrays.asList(15, 7));
		expecteds.add(Arrays.asList(9, 20));
		expecteds.add(Arrays.asList(3));
		assertThat(solutions.levelOrderBottom(root), is(expecteds));
	}
	@Test
	public void testIsSameTree() {
		TreeNode p = Utils.createBinaryTree(new Integer[] { 1, 2, 3 });
		TreeNode q = Utils.createBinaryTree(new Integer[] { 1, 2, 3 });
		assertTrue(solutions.isSameTree(p, q));
		p = Utils.createBinaryTree(new Integer[] { 1, 2 });
		q = Utils.createBinaryTree(new Integer[] { 1, null, 2 });
		assertFalse(solutions.isSameTree(p, q));
	}
	@Test
	public void testLRUCache() {
		Solutions.LRUCache cache = new Solutions.LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		assertThat(cache.get(1), is(1));
		cache.put(3, 3);    // evicts key 2
		assertThat(cache.get(2), is(-1));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		assertThat(cache.get(1), is(-1));       // returns -1 (not found)
		assertThat(cache.get(3), is(3));       // returns 3
		assertThat(cache.get(4), is(4));       // returns 4
	}
	@Test
	public void testMinMeetingRooms() {
		int[][] intervals = {
				{ 0, 6 },
				{ 4, 14 },
				{ 8, 24 },
				{ 16, 22 },
				{ 20, 26 }
		};
		assertThat(solutions.minMeetingRooms(intervals), is(3));
	}
	@Test
	public void testMaxArea() {
		int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		assertThat(solutions.maxArea(height), is(49));
	}
	@Test
	public void testTrap() {
		int[] height = { 0,1,0,2,1,0,1,3,2,1,2,1 };
		assertThat(solutions.trap(height), is(6));
	}
	@Test
	public void testMyAtoi() {
		assertThat(solutions.myAtoi("+1"), is(1));
		assertThat(solutions.myAtoi("42"), is(42));
		assertThat(solutions.myAtoi("   -42"), is(-42));
		assertThat(solutions.myAtoi("4193 with words"), is(4193));
		assertThat(solutions.myAtoi("words and 987"), is(0));
		assertThat(solutions.myAtoi("-91283472332"), is(-2147483648));
	}
}
