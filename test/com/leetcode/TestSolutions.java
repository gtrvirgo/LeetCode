package com.leetcode;

import com.leetcode.Solutions.BSTIterator;
import com.leetcode.Solutions.Codec;
import com.leetcode.Solutions.ListNode;
import com.leetcode.Solutions.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class TestSolutions {

	private static Solutions solutions;

	@BeforeClass
	public static void init() {
		solutions = new Solutions();
	}

	@Test
	public void testIsPalindrome4String() {
		String s = "race a car";
		assertFalse(solutions.isPalindrome(s));
	}

	@Test
	public void testConvert() {
		assertThat(solutions.convert("PAYPALISHIRING", 3), is("PAHNAPLSIIGYIR"));
		assertThat(solutions.convert("PAYPALISHIRING", 4), is("PINALSIGYAHRPI"));
	}

	@Test
	public void testSingleNumber() {
		int[] nums = {4, 1, 2, 1, 2};
		assertThat(4, is(solutions.singleNumber(nums)));
	}

	@Test
	public void testRemoveDuplicates() {
		int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		assertThat(5, is(solutions.removeDuplicates(nums)));
	}

	@Test
	public void testIsValid() {
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
	public void testFindSubString() {
		String s = "wordgoodgoodgoodbestword";
		String[] words = {"word", "good", "best", "good"};
		assertThat(new Object[]{8}, equalTo(solutions.findSubstring(s, words).toArray()));
		s = "";
		words = new String[]{};
		assertThat(new Object[]{}, equalTo(solutions.findSubstring(s, words).toArray()));
		s = "barfoothefoobarman";
		words = new String[]{"foo", "bar"};
		Set<Integer> expecteds = new HashSet<>(Arrays.asList(0, 9));
		assertThat(new HashSet<Integer>(solutions.findSubstring(s, words)), is(expecteds));
	}

	@Test
	public void testMoveZeroes() {
		int nums[] = {0, 1, 0, 3, 12};
		solutions.moveZeroes(nums);
		assertThat(new int[]{1, 3, 12, 0, 0}, equalTo(nums));
		nums = new int[]{1, 0};
		solutions.moveZeroes(nums);
		assertThat(new int[]{1, 0}, equalTo(nums));
	}

	@Test
	public void testRemoveDuplicatesII() {
		int nums[] = {0, 0, 1, 1, 1, 1, 2, 3, 3};
		int len = solutions.removeDuplicatesII(nums);
		int[] actuals = Arrays.copyOf(nums, len);
		int[] expecteds = {0, 0, 1, 1, 2, 3, 3};
		assertThat(expecteds, equalTo(actuals));
	}

	@Test
	public void testLengthOfLongestSubstring() {
		assertThat(solutions.lengthOfLongestSubstring("abcabcbb"), is(3));
	}

	@Test
	public void testMaxValue() {
		int[][] grid = {{1, 3, 1, 2}, {1, 5, 1, 3}, {4, 2, 1, 4}, {3, 2, 6, 5}};
		assertThat(solutions.maxValue(grid), is(24));
		grid = new int[][]{{1, 2}, {5, 6}, {1, 1}};
		assertThat(solutions.maxValue(grid), is(13));
	}

	@Test
	public void testMerge() {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		int[] nums2 = {2, 5, 6};
		int m = 3;
		int n = 3;
		solutions.merge(nums1, m, nums2, n);
	}

	@Test
	public void testSortColors() {
		int[] nums = {2, 0, 2, 1, 1, 0};
		solutions.sortColors(nums);
		int[] expecteds = {0, 0, 1, 1, 2, 2};
		assertArrayEquals(expecteds, nums);
		nums = new int[]{2, 0, 1};
		expecteds = new int[]{0, 1, 2};
		solutions.sortColors(nums);
		assertArrayEquals(expecteds, nums);
	}

	@Test
	public void testSubSort() {
		int[] nums = {1, 5, 4, 3, 2, 6, 7};
		int[] expecteds = {1, 4};
		assertArrayEquals(solutions.subSort(nums), expecteds);
		nums = new int[]{};
		expecteds = new int[]{-1, -1};
		assertArrayEquals(solutions.subSort(nums), expecteds);
		nums = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		expecteds = new int[]{3, 9};
		assertThat(solutions.subSort(nums), is(expecteds));
	}

	@Test
	public void testRemoveElements() {
		int nums[] = {3, 4, 2, 4, 5, 5};
		ListNode head = new ListNode(nums[0]);
		ListNode node = head;
		for (int i = 1; i < nums.length; i++) {
			node.next = new ListNode(nums[i]);
			node = node.next;
		}
		ListNode ans = solutions.removeElements(head, 4);
		int[] expecteds = {3, 2, 5, 5};
		for (int num : expecteds) {
			assertThat(ans.val, is(num));
			ans = ans.next;
		}
		assertNull(ans);
	}

	@Test
	public void testAddTwoNumbers() {
		int nums1[] = {4, 5, 9};
		int nums2[] = {3, 8};
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
		int[] expecteds = {7, 3, 0, 1};
		for (int num : expecteds) {
			assertThat(ans.val, is(num));
			ans = ans.next;
		}
		assertNull(ans);
	}

	@Test
	public void testPartition() {
		int[] values = {1, 4, 3, 2, 5, 2};
		int[] expecteds = {1, 2, 2, 4, 3, 5};
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
		int[] values = {1, 2, 3, 2, 1};
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
		int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
		int k = 3;
		int[] expecteds = {3, 3, 5, 5, 6, 7};
		assertThat(solutions.maxSlidingWindow(nums, k), is(expecteds));
	}

	@Test
	public void testIsSubtree() {
		TreeNode s = Utils.createBinaryTree(new Integer[]{3, 4, 5, 1, 2});
		TreeNode t = Utils.createBinaryTree(new Integer[]{4, 1, 2});
		assertTrue(solutions.isSubtree(s, t));
		s = Utils.createBinaryTree(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0, null});
		assertFalse(solutions.isSubtree(s, t));
	}

	@Test
	public void testMaxProfit() {
		int[] prices = {7, 1, 5, 3, 6, 4};
		assertThat(solutions.maxProfit(prices), is(5));
		prices = new int[]{7, 6, 4, 3, 1};
		assertThat(solutions.maxProfit(prices), is(0));
	}

	@Test
	public void testMaxProfitII() {
		int[] prices = {7, 1, 5, 3, 6, 4};
		assertThat(solutions.maxProfitII(prices), is(7));
		prices = new int[]{1, 2, 3, 4, 5};
		assertThat(solutions.maxProfitII(prices), is(4));
		prices = new int[]{7, 6, 4, 3, 1};
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
		expecteds.add(Arrays.asList(".Q..", "...Q", "Q...", "..Q."));
		expecteds.add(Arrays.asList("..Q.", "Q...", "...Q", ".Q.."));
		assertThat(new HashSet<List<String>>(solutions.solveNQueens(4)), is(expecteds));
	}

	@Test
	public void testLargestBSTSubtree() {
		Integer[] nums = {10, 5, 15, 1, 8, null, 7};
		assertThat(solutions.largestBSTSubtree(Utils.createBinaryTree(nums)), is(3));
	}

	@Test
	public void testletterCombinations() {
		List<String> expecteds = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
		assertThat(solutions.letterCombinations("23"), is(expecteds));
	}

	@Test
	public void testPermute() {
		int[] nums = {1, 2, 3};
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
		int[] nums = {1, 1, 2};
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(1, 1, 2));
		expecteds.add(Arrays.asList(1, 2, 1));
		expecteds.add(Arrays.asList(2, 1, 1));
		assertThat(new HashSet<List<Integer>>(solutions.permuteUnique(nums)), is(expecteds));
	}

	@Test
	public void testGenerateParenthesis() {
		Set<String> expecteds = new HashSet<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
		assertThat(new HashSet<String>(solutions.generateParenthesis(3)), is(expecteds));
	}

	@Test
	public void testPathSum() {
		Integer[] nums = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new ArrayList<>();
		expecteds.add(Arrays.asList(5, 4, 11, 2));
		expecteds.add(Arrays.asList(5, 8, 4, 5));
		assertThat(solutions.pathSum(root, 22), is(expecteds));
	}

	@Test
	public void testHasPathSum() {
		Integer[] nums = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
		TreeNode root = Utils.createBinaryTree(nums);
		assertTrue(solutions.hasPathSum(root, 22));
	}

	@Test
	public void testLevelOrder() {
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
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
		int[] nums = {3, 2, 2, 3};
		assertThat(solutions.removeElement(nums, 3), is(2));
		nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
		assertThat(solutions.removeElement(nums, 2), is(5));
	}

	@Test
	public void testTwoSum() {
		int[] nums = {2, 7, 11, 5};
		int target = 9;
		int[] expecteds = {0, 1};
		assertThat(solutions.twoSum(nums, target), is(expecteds));
	}

	@Test
	public void testThreeSum() {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		Set<List<Integer>> expecteds = new HashSet<>();
		expecteds.add(Arrays.asList(-1, 0, 1));
		expecteds.add(Arrays.asList(-1, -1, 2));
		assertThat(new HashSet<List<Integer>>(solutions.threeSum(nums)), is(expecteds));
	}

	@Test
	public void testSearchInsert() {
		int[] nums = {1, 3, 5, 6};
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
		String[] strs = {"flower", "flow", "flight"};
		assertThat(solutions.longestCommonPrefix(strs), is("fl"));
		strs = new String[]{"dog", "racecar", "car"};
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
		int[] candidates = {2, 3, 6, 7};
		int target = 7;
		assertThat(new HashSet<List<Integer>>(solutions.combinationSum(candidates, target)), is(expecteds));

		expecteds.clear();
		expecteds.add(Arrays.asList(3, 5));
		expecteds.add(Arrays.asList(2, 3, 3));
		expecteds.add(Arrays.asList(2, 2, 2, 2));
		candidates = new int[]{2, 3, 5};
		target = 8;
		assertThat(new HashSet<List<Integer>>(solutions.combinationSum(candidates, target)), is(expecteds));
	}

	@Test
	public void testCombinationSum2() {
		int[] candidates = {10, 1, 2, 7, 6, 1, 5};
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
		int[] digits = {1, 2, 3};
		assertThat(solutions.plusOne(digits), is(new int[]{1, 2, 4}));
		digits = new int[]{4, 3, 2, 1};
		assertThat(solutions.plusOne(digits), is(new int[]{4, 3, 2, 2}));
	}

	@Test
	public void testAddBinary() {
		assertThat(solutions.addBinary("11", "1"), is("100"));
		assertThat(solutions.addBinary("1010", "1011"), is("10101"));
	}

	@Test
	public void testSingleNumberII() {
		int[] nums = {2, 2, 3, 2};
		assertThat(solutions.singleNumberII(nums), is(3));
		nums = new int[]{0, 1, 0, 1, 0, 1, 99};
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
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		assertThat(solutions.maxSubArray(nums), is(6));
		nums = new int[]{1};
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
		char[] s = {'h', 'e', 'l', 'l', 'o'};
		char[] expecteds = {'o', 'l', 'l', 'e', 'h'};
		solutions.reverseString(s);
		assertThat(s, is(expecteds));
		s = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
		expecteds = new char[]{'h', 'a', 'n', 'n', 'a', 'H'};
		solutions.reverseString(s);
		assertThat(s, is(expecteds));
	}

	@Test
	public void testLengthOfLastWord() {
		assertThat(solutions.lengthOfLastWord("Hello World"), is(5));
	}

	@Test
	public void testLevelOrderBottom() {
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new LinkedList<>();
		expecteds.add(Arrays.asList(15, 7));
		expecteds.add(Arrays.asList(9, 20));
		expecteds.add(Arrays.asList(3));
		assertThat(solutions.levelOrderBottom(root), is(expecteds));
	}

	@Test
	public void testIsSameTree() {
		TreeNode p = Utils.createBinaryTree(new Integer[]{1, 2, 3});
		TreeNode q = Utils.createBinaryTree(new Integer[]{1, 2, 3});
		assertTrue(solutions.isSameTree(p, q));
		p = Utils.createBinaryTree(new Integer[]{1, 2});
		q = Utils.createBinaryTree(new Integer[]{1, null, 2});
		assertFalse(solutions.isSameTree(p, q));
	}

	@Test
	public void testLRUCache() {
		Solutions.LRUCache cache = new Solutions.LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		assertThat(cache.get(1), is(1));
		cache.put(3, 3); // evicts key 2
		assertThat(cache.get(2), is(-1)); // returns -1 (not found)
		cache.put(4, 4); // evicts key 1
		assertThat(cache.get(1), is(-1)); // returns -1 (not found)
		assertThat(cache.get(3), is(3)); // returns 3
		assertThat(cache.get(4), is(4)); // returns 4
	}

	@Test
	public void testMinMeetingRooms() {
		int[][] intervals = {{0, 6}, {4, 14}, {8, 24}, {16, 22}, {20, 26}};
		assertThat(solutions.minMeetingRooms(intervals), is(3));
	}

	@Test
	public void testMaxArea() {
		int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		assertThat(solutions.maxArea(height), is(49));
	}

	@Test
	public void testTrap() {
		int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
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

	@Test
	public void testIsPathCrossing() {
		assertFalse(solutions.isPathCrossing("NES"));
		assertTrue(solutions.isPathCrossing("NESWW"));
	}

	@Test
	public void testRomanToInt() {
		assertThat(solutions.romanToInt("III"), is(3));
		assertThat(solutions.romanToInt("IV"), is(4));
		assertThat(solutions.romanToInt("IX"), is(9));
		assertThat(solutions.romanToInt("LVIII"), is(58));
		assertThat(solutions.romanToInt("MCMXCIV"), is(1994));
	}

	@Test
	public void testIntToRoman() {
		assertThat(solutions.intToRoman(3), is("III"));
		assertThat(solutions.intToRoman(4), is("IV"));
		assertThat(solutions.intToRoman(9), is("IX"));
		assertThat(solutions.intToRoman(58), is("LVIII"));
		assertThat(solutions.intToRoman(1994), is("MCMXCIV"));
	}

	@Test
	public void testIsValidSudoku() {
		char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		assertTrue(solutions.isValidSudoku(board));
		board = new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		assertFalse(solutions.isValidSudoku(board));
	}

	@Test
	public void testNextPermutation() {
		int[] nums = {1, 2, 3};
		solutions.nextPermutation(nums);
		assertThat(nums, is(new int[]{1, 3, 2}));
		nums = new int[]{3, 2, 1};
		solutions.nextPermutation(nums);
		assertThat(nums, is(new int[]{1, 2, 3}));
		nums = new int[]{1, 1, 5};
		solutions.nextPermutation(nums);
		assertThat(nums, is(new int[]{1, 5, 1}));
		nums = new int[]{1, 2};
		solutions.nextPermutation(nums);
		assertThat(nums, is(new int[]{2, 1}));
	}

	@Test
	public void testLongestValidParentheses() {
		assertThat(solutions.longestValidParentheses("(()"), is(2));
		assertThat(solutions.longestValidParentheses(")()())"), is(4));
	}

	@Test
	public void testFirstBadVersion() {
		assertThat(solutions.firstBadVersion(5), is(4));
	}

	@Test
	public void testNumJewelsInStones() {
		assertThat(solutions.numJewelsInStones("aA", "aAAbbbb"), is(3));
		assertThat(solutions.numJewelsInStones("z", "ZZ"), is(0));
	}

	@Test
	public void testCanConstruct() {
		assertFalse(solutions.canConstruct("a", "b"));
		assertFalse(solutions.canConstruct("aa", "ab"));
		assertTrue(solutions.canConstruct("aa", "aab"));
	}

	@Test
	public void testMergeIntervals() {
		int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		int[][] output = {{1, 6}, {8, 10}, {15, 18}};
		assertThat(solutions.merge(intervals), is(output));
		intervals = new int[][]{{1, 4}, {4, 5}};
		output = new int[][]{{1, 5}};
		assertThat(solutions.merge(intervals), is(output));
		intervals = new int[][]{};
		assertThat(solutions.merge(intervals), is(intervals));
	}

	@Test
	public void testIsSymmetric() {
		Integer[] nums = {1, 2, 2, 3, 4, 4, 3};
		TreeNode root = Utils.createBinaryTree(nums);
		assertTrue(solutions.isSymmetric(root));
		nums = new Integer[]{1, 2, 2, null, 3, null, 3};
		root = Utils.createBinaryTree(nums);
		assertFalse(solutions.isSymmetric(root));
	}

	@Test
	public void testMinDepth() {
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		assertThat(solutions.minDepth(root), is(2));
		nums = new Integer[]{1, 2};
		root = Utils.createBinaryTree(nums);
		assertThat(solutions.minDepth(root), is(2));
	}

	@Test
	public void testFirstMissingPositive() {
		int[] nums = {1, 2, 0};
		assertThat(solutions.firstMissingPositive(nums), is(3));
		nums = new int[]{3, 4, -1, 1};
		assertThat(solutions.firstMissingPositive(nums), is(2));
		nums = new int[]{7, 8, 9, 11, 12};
		assertThat(solutions.firstMissingPositive(nums), is(1));
		nums = new int[]{-1, 4, 2, 1, 9, 10};
		assertThat(solutions.firstMissingPositive(nums), is(3));
	}

	@Test
	public void testIsMatch() {
		assertFalse(solutions.isMatch("aa", "a"));
		assertTrue(solutions.isMatch("aa", "*"));
		assertFalse(solutions.isMatch("cb", "?a"));
		assertTrue(solutions.isMatch("adceb", "a*b"));
		assertFalse(solutions.isMatch("acdcb", "a*c?b"));
		assertTrue(solutions.isMatch("adceb", "*a*b"));
	}

	@Test
	public void testMaxDepth() {
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		assertThat(solutions.maxDepth(root), is(3));
	}

	@Test
	public void testBuildTree() {
		int[] preorder = {3, 9, 20, 15, 7};
		int[] inorder = {9, 3, 15, 20, 7};
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		assertTrue(solutions.isSameTree(root, solutions.buildTree(preorder, inorder)));
	}

	@Test
	public void testBuildTreeII() {
		int[] inorder = {9, 3, 15, 20, 7};
		int[] postorder = {9, 15, 7, 20, 3};
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		assertTrue(solutions.isSameTree(root, solutions.buildTreeII(inorder, postorder)));
	}

	@Test
	public void testZigZagLevelOrder() {
		Integer[] nums = {3, 9, 20, null, null, 15, 7};
		TreeNode root = Utils.createBinaryTree(nums);
		List<List<Integer>> expecteds = new ArrayList<>();
		expecteds.add(Arrays.asList(3));
		expecteds.add(Arrays.asList(20, 9));
		expecteds.add(Arrays.asList(15, 7));
		assertThat(solutions.zigzagLevelOrder(root), is(expecteds));
	}

	@Test
	public void testJump() {
		int[] nums = {2, 3, 1, 1, 4};
		assertThat(solutions.jump(nums), is(2));
	}

	@Test
	public void testThreeSumClosest() {
		int[] nums = {-1, 2, 1, -4};
		int target = 1;
		assertThat(solutions.threeSumClosest(nums, target), is(2));
		nums = new int[]{-3, -2, -5, 3, -4};
		target = -1;
		assertThat(solutions.threeSumClosest(nums, target), is(-2));
	}

	@Test
	public void testFourSum() {
		int[] nums = {1, 0, -1, 0, -2, 2};
		int target = 0;
		Set<List<Integer>> expected = new HashSet<>();
		expected.add(Arrays.asList(-1, 0, 0, 1));
		expected.add(Arrays.asList(-2, -1, 1, 2));
		expected.add(Arrays.asList(-2, 0, 0, 2));
		assertThat(new HashSet<List<Integer>>(solutions.fourSum(nums, target)), is(expected));
		nums = new int[]{0, 0, 0, 0};
		target = 0;
		expected = new HashSet<>();
		expected.add(Arrays.asList(0, 0, 0, 0));
		assertThat(new HashSet<List<Integer>>(solutions.fourSum(nums, target)), is(expected));
	}

	@Test
	public void testIsRegExpMatch() {
		assertTrue(solutions.isRegExpMatch("", ".*"));
		assertFalse(solutions.isRegExpMatch("aa", "a"));
		assertTrue(solutions.isRegExpMatch("aa", "a*"));
		assertTrue(solutions.isRegExpMatch("ab", ".*"));
		assertTrue(solutions.isRegExpMatch("aab", "c*a*b"));
		assertFalse(solutions.isRegExpMatch("mississippi", "mis*is*p*."));
	}

	@Test
	public void testCanJump() {
		assertTrue(solutions.canJump(new int[]{2, 3, 1, 1, 4}));
		assertFalse(solutions.canJump(new int[]{3, 2, 1, 0, 4}));
	}

	ListNode array2ListNode(int[] nums) {
		ListNode dummyHead = new ListNode(0);
		ListNode cur = dummyHead;
		for (int i = 0; i < nums.length; i++) {
			cur.next = new ListNode(nums[i]);
			cur = cur.next;
		}
		return dummyHead.next;
	}

	Integer[] listNode2Array(ListNode head) {
		List<Integer> res = new ArrayList<>();
		ListNode cur = head;
		while (cur != null) {
			res.add(cur.val);
			cur = cur.next;
		}
		Integer[] output = new Integer[res.size()];
		return res.toArray(output);
	}

	@Test
	public void testMergeTwoLists() {
		Integer[][] expecteds = {
				{1, 1, 2, 3, 4, 4},
				{},
				{0},
				{-9, 3, 5, 7}
		};
		int[][] nums1 = {
				{1, 2, 4},
				{},
				{},
				{-9, 3}
		};
		int[][] nums2 = {
				{1, 3, 4},
				{},
				{0},
				{5, 7}
		};
		for (int i = 0; i < expecteds.length; i++) {
			assertThat(listNode2Array(solutions.mergeTwoLists(
					array2ListNode(nums1[i]),
					array2ListNode(nums2[i]))), is(expecteds[i]));
		}
	}

	@Test
	public void testMergerKLists() {
		ListNode[] lists = new ListNode[]{
				array2ListNode(new int[]{1, 4, 5}),
				array2ListNode(new int[]{1, 3, 4}),
				array2ListNode(new int[]{2, 6})
		};
		Integer[] expecteds = {1, 1, 2, 3, 4, 4, 5, 6};
		assertThat(listNode2Array(solutions.mergeKLists(lists)), is(expecteds));
	}

	@Test
	public void testProductExceptSelf() {
		int[][] nums = {
				{1, 2, 3, 4},
				{0, 1}
		};
		int[][] outputs = {
				{24, 12, 8, 6},
				{1, 0}
		};
		for (int i = 0; i < nums.length; i++) {
			assertThat(solutions.productExceptSelf(nums[i]), is(outputs[i]));
		}
	}

	@Test
	public void testTopKFrequent() {
		int[][] nums = {
				{1, 1, 1, 2, 2, 3},
				{1}
		};
		int[] k = {2, 1};
		Integer[][] expecteds = {
				{1, 2},
				{1}
		};
		for (int i = 0; i < k.length; i++) {
			Set<Integer> expected = new HashSet<>(Arrays.asList(expecteds[i]));
			assertThat(IntStream.of(solutions.topKFrequent(nums[i], k[i])).boxed().collect(Collectors.toSet()), is(expected));
		}
	}

	@Test
	public void testSearch() {
		int[][] nums = {
				{4, 5, 6, 7, 0, 1, 2},
				{4, 5, 6, 7, 0, 1, 2},
				{1},
				{3, 1}
		};
		int[] targets = {0, 3, 0, 3};
		int[] expecteds = {4, -1, -1, 0};
		for (int i = 0; i < nums.length; i++) {
			assertThat(solutions.search(nums[i], targets[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testFindMin() {
		int[][] nums = {
				{3, 4, 5, 1, 2},
				{4, 5, 6, 7, 0, 1, 2}
		};
		int[] expecteds = {1, 0};
		for (int i = 0; i < nums.length; i++) {
			assertThat(solutions.findMin(nums[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testRob() {
		int[][] nums = {
				{1, 2, 3, 1},
				{2, 7, 9, 3, 1},
				{1, 3, 4, 3, 0, 2, 7}
		};
		int[] expecteds = {4, 12, 13};
		for (int i = 0; i < expecteds.length; i++) {
			assertThat(solutions.rob(nums[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testCodec() {
		Integer[][] inputs = {
				{1, 2, 3, null, null, 4, 5},
				{},
				{1},
				{1, 2}
		};
		TreeNode root;
		String data;
		Codec codec = new Solutions.Codec();
		for (int i = 0; i < inputs.length; i++) {
			root = Utils.createBinaryTree(inputs[i]);
			data = codec.serialize(root);
			assertTrue(solutions.isSameTree(root, codec.deserialize(data)));
		}
	}

	@Test
	public void testPreorderTraversal() {
		Integer[][] inputs = {
				{1, null, 2, 3},
				{},
				{1},
				{1, null, 2},
				{1, 2}
		};
		Integer[][] expecteds = {
				{1, 2, 3},
				{},
				{1},
				{1, 2},
				{1, 2}
		};
		for (int i = 0; i < expecteds.length; i++) {
			assertThat(solutions.preorderTraversal(Utils.createBinaryTree(inputs[i])),
					is(Arrays.asList(expecteds[i])));
		}
	}

	@Test
	public void testInorderTraversal() {
		Integer[][] inputs = {
				{1, null, 2, 3},
				{},
				{1},
				{1, 2},
				{1, null, 2}
		};
		Integer[][] expecteds = {
				{1, 3, 2},
				{},
				{1},
				{2, 1},
				{1, 2}
		};
		for (int i = 0; i < expecteds.length; i++) {
			assertThat(solutions.inorderTraversal(Utils.createBinaryTree(inputs[i])),
					is(Arrays.asList(expecteds[i])));
		}
	}

	@Test
	public void testMinWindow() {
		String[] s = {
				"ADOBECODEBANC",
				"cabwefgewcwaefgcf",
				"a"
		};
		String[] t = {
				"ABC",
				"cae",
				"a"
		};
		String[] expected = {
				"BANC",
				"cwae",
				"a"
		};
		for (int i = 0; i < expected.length; i++) {
			assertThat(solutions.minWindow(s[i], t[i]), is(expected[i]));
		}
	}

	@Test
	public void testCoinChange() {
		int[][] coins = {
				{1, 2, 5},
				{2},
				{1},
				{1},
				{1}
		};
		int[] amounts = {11, 3, 0, 1, 2};
		int[] expecteds = {3, -1, 0, 1, 2};
		for (int i = 0; i < coins.length; i++) {
			assertThat(solutions.coinChange(coins[i], amounts[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testFindMinArrowShots() {
		int[][][] points = {
				{{10, 16}, {2, 8}, {1, 6}, {7, 12}},
				{{1, 2}, {3, 4}, {5, 6}, {7, 8}},
				{{1, 2}, {2, 3}, {3, 4}, {4, 5}},
				{{1, 2}},
				{{2, 3}, {2, 3}},
				{{-2147483648, 2147483647}},
				{{-2147483646, -2147483645}, {2147483646, 2147483647}}
		};
		int[] expecteds = {2, 4, 2, 1, 1, 1, 2};
		for (int i = 0; i < points.length; i++) {
			assertThat(solutions.findMinArrowShots(points[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testSumNumbers() {
		Integer[][] nums = {
				{1, 2, 3},
				{4, 9, 0, 5, 1}
		};
		int[] expecteds = {25, 1026};
		TreeNode root;
		for (int i = 0; i < nums.length; i++) {
			root = Utils.createBinaryTree(nums[i]);
			assertThat(solutions.sumNumbers(root), is(expecteds[i]));
		}
	}

	@Test
	public void testCombine() {
		int[][][] expecteds = {
				{
						{2, 4},
						{3, 4},
						{2, 3},
						{1, 2},
						{1, 3},
						{1, 4},
				},
				{{1}}
		};
		int[] n = {4, 1};
		int[] k = {2, 1};
		for (int i = 0; i < n.length; i++) {
			List<List<Integer>> expected = Stream.of(expecteds[i]).map(e -> IntStream.of(e).boxed().collect(Collectors.toList())).collect(Collectors.toList());
			assertThat(new HashSet<List<Integer>>(solutions.combine(n[i], k[i])),
					is(new HashSet<List<Integer>>(expected)));
		}
	}

	@Test
	public void testShortestWordDistanceII() {
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		String[] word1 = {"coding", "makes"};
		String[] word2 = {"practice", "coding"};
		int[] expecteds = {3, 1};
		for (int i = 0; i < word1.length; i++) {
			assertThat(solutions.shortestWordDistanceII(words, word1[i], word2[i]), is(expecteds[i]));
		}
	}

	@Test
	public void testBSTIterator() {
		Integer[] nums = {7, 3, 15, null, null, 9, 20};
		TreeNode root = Utils.createBinaryTree(nums);
		Iterator<Integer> expecteds = Arrays.asList(3, 7, 9, 15, 20).iterator();
		BSTIterator iterator = new Solutions.BSTIterator(root);
		while (true) {
			assertThat(iterator.hasNext(), is(expecteds.hasNext()));
			if (!iterator.hasNext()) {
				break;
			}
			assertThat(iterator.next(), is(expecteds.next()));
		}
	}

	@Test
	public void testFindRepeatedDnaSequences() {
		String[] s = {
				"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
				"AAAAAAAAAAAAA"
		};
		String[][] expected = {
				{"AAAAACCCCC", "CCCCCAAAAA"},
				{"AAAAAAAAAA"}
		};
		for (int i = 0; i < s.length; i++) {
			assertThat(solutions.findRepeatedDnaSequences(s[i]), is(Arrays.asList(expected[i])));
		}
	}

	@Test
	public void testWiggleSort() {
		int[] nums = {3, 5, 2, 1, 6, 4};
		int[] expected = {1, 3, 2, 5, 4, 6};
		solutions.wiggleSort(nums);
		assertThat(nums, is(expected));
	}

	@Test
	public void testLongestZigZag() {
		Integer[][] inputs = {
				{1, null, 1, 1, 1, null, null, 1, 1, null, 1, null, null, null, 1, null, 1},
				{1, 1, 1, null, 1, null, null, 1, 1, null, 1},
				{1}
		};
		int[] expecteds = {3, 4, 0};
		for (int i = 0; i < inputs.length; i++) {
			TreeNode root = Utils.createBinaryTree(inputs[i]);
			Solutions.Solution solution = new Solutions.Solution();
			assertThat(solution.longestZigZag(root), is(expecteds[i]));
		}
	}

	@Test
	public void testCountOrders() {
		int[] inputs = {1, 2, 3, 8};
		int[] outputs = {1, 6, 90, 729647433};
		for (int i = 0; i < inputs.length; i++) {
			assertThat(solutions.countOrders(inputs[i]), is(outputs[i]));
		}
	}
	@Test
	public void testRightSideView() {
		Integer[] nums = {1,2,3,null,5,null,4};
		Integer[] expected = {1,3,4};
		TreeNode root = Utils.createBinaryTree(nums);
		assertThat(solutions.rightSideView(root), is(Arrays.asList(expected)));
	}
	@Test
	public void testMissingNumber() {
		int[][] nums = {
			{3,0,1},
			{0,1},
			{9,6,4,2,3,5,7,0,1},
			{0}
		};
		int[] expecteds = {2,2,8,1};
		for (int i = 0; i < nums.length; i++) {
			assertThat(solutions.missingNumber(nums[i]), is(expecteds[i]));
		}
	}
}