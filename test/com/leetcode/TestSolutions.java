package com.leetcode;

import org.junit.Test;

import com.leetcode.Solutions.ListNode;
import com.leetcode.Solutions.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
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
	public void testReverseWords() {
		System.out.println("666_" + solutions.reverseWords(" ") + "_666");
		System.out.println("666_" + solutions.reverseWords("  hello world!     ") + "_666");
		System.out.println("666_" + solutions.reverseWords("a good   example") + "_666");
		System.out.println("666_" + solutions.reverseWords("are you ok") + "_666");
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
		System.out.println(Arrays.toString(nums));
		int[] expecteds = { 0,0,1,1,2,2 };
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
		System.out.println(ans.val);
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
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
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
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
		TreeNode s = new TreeNode(3);
		s.left = new TreeNode(4);
		s.right = new TreeNode(5);
		s.left.left = new TreeNode(1);
		s.left.right = new TreeNode(2);
		TreeNode t = new TreeNode(4);
		t.left = new TreeNode(1);
		t.right = new TreeNode(2);
		assertTrue(solutions.isSubtree(s, t));
	}
	@Test
	public void testMaxProfit() {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		assertThat(solutions.maxProfit(prices), is(5));
		prices = new int[] { 7, 6, 4, 3, 1 };
		assertThat(solutions.maxProfit(prices), is(0));
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
		System.out.println(solutions.solveNQueens(4));
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
		System.out.println(solutions.permute(new int[] { 1, 2, 3 }));
	}
	@Test
	public void testPermuteUnique() {
		System.out.println(solutions.permuteUnique(new int[] { 1, 1, 2 }));
	}
	@Test
	public void testGenerateParenthesis() {
		System.out.println(solutions.generateParenthesis(3));
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
		List<List<Integer>> actuals = solutions.threeSum(nums);
		assertThat(actuals, hasItem(Arrays.asList(-1, 0, 1)));
		assertThat(actuals, hasItem(Arrays.asList(-1, -1, 2)));
	}
}
