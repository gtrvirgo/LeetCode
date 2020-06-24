package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Deque;

public class Solutions {

	public boolean isPalindrome(String s) {
		s = s.replaceAll("\\W", "");
		return s.equalsIgnoreCase(new StringBuilder(s).reverse().toString());
	}

	public int singleNumber(int[] nums) {
		int result = 0;
		for (int i : nums) {
			result ^= i;
		}
		return result;
	}

	public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {
				nums[++i] = nums[j];
			}
		}
		return i + 1;
	}

	public List<Integer> findSubstring(String s, String words[]) {
		List<Integer> results = new ArrayList<>();
		if (words.length == 0) {
			return results;
		}
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		Map<String, Integer> found = new HashMap<>();
		int n = words[0].length();
		for (int i = 0; i <= s.length() - words.length * n; i++) {
			found.clear();
			int j = 0;
			for (; j < words.length; j++) {
				if (i + j * n + n > s.length()) {
					break;
				}
				String str = s.substring(i + j * n, i + j * n + n);
				if (map.containsKey(str)) {
					found.put(str, found.getOrDefault(str, 0) + 1);
				} else {
					break;
				}
				if (map.get(str) < found.get(str)) {
					break;
				}
			}
			if (j == words.length) {
				results.add(i);
			}
		}
		return results;
	}

	public int[] moveZeroes(int nums[]) {
		if (nums.length == 0) {
			return nums;
		}
		int i = 0;
		int j = 0;
		while (i < nums.length && j < nums.length) {
			while (i < nums.length && nums[i] != 0) {
				i++;
			}
			j = Math.max(i, j);
			while (j < nums.length && nums[j] == 0) {
				j++;
			}
			if (i < nums.length && j < nums.length) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
			}
		}
		return nums;
	}

	public int removeDuplicatesII(int nums[]) {
		if (nums.length == 0) {
			return 0;
		}
		int i = 0;
		int j = 1;
		int duplicates = 0;
		while (j < nums.length) {
			if (nums[i] == nums[j]) {
				if (++duplicates < 2) {
					nums[++i] = nums[j];
				}
			} else {
				nums[++i] = nums[j];
				duplicates = 0;
			}
			j++;
		}
		return i + 1;
	}

//	public int lengthOfLongestSubstring(String s) {
//		if (s == null) {
//			return 0;
//		}
//		char[] chars = s.toCharArray();
//		if (chars.length == 0) {
//			return 0;
//		}
//		Map<Character, Integer> lastOccur = new HashMap<>();
//		lastOccur.put(chars[0], 0);
//		int li = 0; // the beginning of the substring i - 1 ends up
//		int longest = 1;
//		for (int i = 1; i < chars.length; i++) {
//			//Integer pi = lastOccur.getOrDefault(chars[i], -1); // the last occurence of i character
//			Integer pi = lastOccur.get(chars[i]);
//			if (pi != null && li <= pi) {
//				li = pi + 1;
//			}
//			lastOccur.put(chars[i], i);
//			longest = Math.max(longest, i - li + 1);
//		}
//		return longest;
//	}
	public int lengthOfLongestSubstring(String s) {
		if (s == null) {
			return 0;
		}
		char[] chars = s.toCharArray();
		if (chars.length == 0) {
			return 0;
		}
		int[] lastOccur = new int[128]; // only contains ascii character
		for (int i = 0; i < lastOccur.length; i++) {
			lastOccur[i] = -1;
		}
		lastOccur[chars[0]] = 0;
		int li = 0; // the beginning of the substring i - 1 ends up
		int longest = 1;
		for (int i = 1; i < chars.length; i++) {
			// Integer pi = lastOccur.getOrDefault(chars[i], -1); // the last occurence of i
			// character
			int pi = lastOccur[chars[i]];
			if (li <= pi) {
				li = pi + 1;
			}
			lastOccur[chars[i]] = i;
			longest = Math.max(longest, i - li + 1);
		}
		return longest;
	}

//	public int maxValue(int[][] grid) {
//		int[][] dp = new int[grid.length][grid[0].length];
//		dp[0][0] = grid[0][0];
//		for (int i = 1; i < grid[0].length; i++) {
//			dp[0][i] = dp[0][i - 1] + grid[0][i];
//		}
//		for (int i = 1; i < grid.length; i++) {
//			dp[i][0] = dp[i - 1][0] + grid[i][0];
//		}
//		for (int i = 1; i < grid.length; i++) {
//			for (int j = 1; j < grid[0].length; j++) {
//				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
//			}
//		}
//		return dp[grid.length - 1][grid[0].length - 1];
//	}

	public int[] subSort(int[] nums) {
		if (nums.length == 0) {
			return new int[] { -1, -1 };
		}
		int max = nums[0];
		int li = -1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < max) {
				li = i;
			} else {
				max = nums[i];
			}
		}
		if (li == -1) {
			return new int[] { -1, -1 };
		}
		int min = nums[nums.length - 1];
		int ri = -1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] > min) {
				ri = i;
			} else {
				min = nums[i];
			}
		}
		return new int[] { ri, li };
	}

	public ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		if (head != null) {
			ListNode cur = head.next;
			ListNode last = head;
			while (cur != null) {
				if (cur.val != val) {
					last.next = cur;
					last = last.next;
				}
				cur = cur.next;
			}
			last.next = null;
		}
		return head;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // #2
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummyHead = new ListNode(0);
		ListNode cur = dummyHead;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int v1 = 0;
			int v2 = 0;
			if (l1 != null) {
				v1 = l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				v2 = l2.val;
				l2 = l2.next;
			}
			int sum = v1 + v2 + carry;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
		}
		if (carry != 0) {
			cur.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

//	public String longestPalindrome(String s) { // #5
//		boolean[][] dp = new boolean[s.length()][s.length()];
//		int maxLen = 0;
//		int begin = 0;
//		for (int i = s.length() - 1; i >= 0; i--) {
//			for (int j = i; j < s.length(); j++) {
//				if ((j - i + 1) > 2) {
//					dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
//				} else {
//					dp[i][j] = s.charAt(i) == s.charAt(j);
//				}
//				if (dp[i][j] && (j - i + 1) >= maxLen) {
//					maxLen = j - i + 1;
//					begin = i;
//				}
//			}
//		}
//		return s.substring(begin, begin + maxLen);
//	}
	public String longestPalindrome(String s) {
		char[] chars = helper(s.toCharArray());
		int[] M = new int[chars.length];
		int c = 1;
		int r = 1;
		int maxLen = 0;
		int tBegin = 0;
		for (int i = 2; i < M.length - 2; i++) {
			if (r > i) {
				int li = 2 * c - i;
				if (i + M[li] <= r) {
					M[i] = M[li];
				} else {
					M[i] = r - i;
				}
			}
			while (chars[i + M[i] + 1] == chars[i - M[i] - 1]) {
				M[i]++;
			}
			if (i + M[i] > r) {
				c = i;
				r = i + M[i];
			}
			if (M[i] > maxLen) {
				maxLen = M[i];
				tBegin = i;
			}
		}
		int begin = (tBegin - maxLen) / 2;
		return s.substring(begin, begin + maxLen);
	}
	char[] helper(char[] chars) {
		char[] ans = new char[chars.length * 2 + 3];
		ans[0] = '^';
		ans[1] = '#';
		ans[ans.length - 1] = '$';
		for (int i = 0; i < chars.length; i++) {
			ans[(i + 1) * 2] = chars[i];
			ans[(i + 1) * 2 + 1] = '#';
		}
		return ans;
	}
	
	char[][] dialer = {
			{ 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
			{ 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' },
			{ 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' }
	};
	public List<String> letterCombinations(String digits) { // #17
		List<String> combinations = new ArrayList<>();
		helper(combinations, digits, new char[digits.length()], 0);
		return combinations;
	}
	void helper(List<String> combinations, String digits, char[] combination, int level) {
		if (level == digits.length()) {
			combinations.add(new String(combination));
			return;
		}
		for (char c : dialer[digits.charAt(level) - '2']) {
			combination[level] = c;
			helper(combinations, digits, combination, level + 1);
		}
	}
	
	public boolean isValid(String s) { // #20
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		char[] chars = s.toCharArray();
		for (Character c : chars) {
			switch (c) {
			case '(':
			case '[':
			case '{':
				stack.push(c);
				break;
			case ')':
			case ']':
			case '}':
				if (stack.isEmpty() || stack.pop() != map.get(c)) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	public List<String> generateParenthesis(int n) {
		List<String> solutions = new ArrayList<>();
		if (n >= 0) {
			helper(n, n, solutions, new char[n * 2]);
		}
		return solutions;
	}
	void helper(int left, int right, List<String> solutions, char[] solution) {
		if (left == 0 && right == 0) {
			solutions.add(new String(solution));
			return;
		}
		if (left > 0) {
			solution[solution.length - left - right] = '(';
			helper(left - 1, right, solutions, solution);
		}
		if (right > left) { // right > 0 && right != left
			solution[solution.length - left - right] = ')';
			helper(left, right - 1, solutions, solution);
		}
	}
	public List<List<Integer>> permute(int[] nums) { // #46
		List<List<Integer>> solutions = new ArrayList<>();
		if (nums.length > 0) {
			helper(nums, solutions, 0);
		}
		return solutions;
	}
	void helper(int[] nums, List<List<Integer>> solutions, int level) {
		if (level == nums.length) {
			List<Integer> solution = new ArrayList<>();
			for (int num : nums) {
				solution.add(num);
			}
			solutions.add(solution);
			return;
		}
		for (int i = level; i < nums.length; i++) {
			Utils.swap(nums, i, level);
			helper(nums, solutions, level + 1);
			Utils.swap(nums, i, level);
		}
	}
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> solutions = new ArrayList<>();
		if (nums.length > 0) {
			boolean[] used = new boolean[nums.length];
			helper(nums, solutions, new LinkedList<Integer>(), used, 0);
		}
		return solutions;
	}
	void helper(int[] nums, List<List<Integer>> solutions, LinkedList<Integer> solution,
			boolean[] used, int level) {
		if (level == nums.length) {
			solutions.add(new LinkedList<>(solution));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i] && (i == 0 || nums[i - 1] != nums[i] || used[i - 1])) {
				used[i] = true;
				solution.addLast(nums[i]);
				helper(nums, solutions, solution, used, level + 1);
				solution.removeLast();
				used[i] = false;
			}
		}
	}
	public int maxValue(int[][] grid) { // #47
		int[] dp = new int[grid[0].length];
		dp[0] = grid[0][0];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = dp[i - 1] + grid[0][i];
		}
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
			}
		}
		return dp[grid[0].length - 1];
	}

	public List<List<String>> solveNQueens(int n) { // #51
		int[] board = new int[n];
		List<List<String>> solutions = new ArrayList<>();
		helper(solutions, board, 0);
		return solutions;
	}

	void helper(List<List<String>> solutions, int[] board, int n) {
		if (n == board.length) {
			List<String> solution = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < n; j++) {
					if (j == board[i]) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				solution.add(sb.toString());
			}
			solutions.add(solution);
			return;
		}
		for (int i = 0; i < board.length; i++) {
			if (canPlace(n, i, board)) {
				board[n] = i;
				helper(solutions, board, n + 1);
			}
		}
	}

	boolean canPlace(int row, int col, int[] board) {
		for (int i = 0; i < row; i++) {
			if (board[i] == col) {
				return false;
			}
			if (row - i == Math.abs(col - board[i])) {
				return false;
			}
		}
		return true;
	}

	public int minDistance(String word1, String word2) { // #72
//		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
//		for (int j = 0; j <= word2.length(); j++) {
//			dp[0][j] = j;
//		}
//		for (int i = 1; i <= word1.length(); i++) {
//			dp[i][0] = i;
//		}
//		for (int i = 1; i <= word1.length(); i++) {
//			for (int j = 1; j <= word2.length(); j++) {
//				int min = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
//				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
//					min = Math.min(min, dp[i - 1][j - 1]);
//				} else {
//					min = Math.min(min, dp[i - 1][j - 1] + 1);
//				}
//				dp[i][j] = min;
//			}
//		}
//		return dp[word1.length()][word2.length()];
		if (word2.length() == 0) {
			return word1.length();
		}
		int[] dp = new int[word2.length() + 1];
		for (int j = 0; j <= word2.length(); j++) {
			dp[j] = j;
		}
		for (int i = 0; i < word1.length(); i++) {
			dp[0] = i;
			int cur = dp[0];
			for (int j = 1; j <= word2.length(); j++) {
				int leftTop = cur;
				cur = dp[j];
				dp[j] = Math.min(word1.charAt(i) == word2.charAt(j - 1) ? leftTop : leftTop + 1,
						Math.min(dp[j] + 1, dp[j - 1] + 1));
			}
		}
		return dp[word2.length()];
	}

	public void sortColors(int nums[]) { // #75
		int left = 0;
		int cur = 0;
		int right = nums.length - 1;
		int tmp;
		while (cur <= right) {
			if (nums[cur] == 0) {
				tmp = nums[cur];
				nums[cur] = nums[left];
				nums[left] = tmp;
				cur++;
				left++;
			} else if (nums[cur] == 2) {
				tmp = nums[cur];
				nums[cur] = nums[right];
				nums[right] = tmp;
				right--;
			} else {
				cur++;
			}
		}
	}

	public ListNode partition(ListNode head, int x) { // #86
		ListNode lHead = new ListNode(0);
		ListNode rHead = new ListNode(0);
		ListNode lTail = lHead;
		ListNode rTail = rHead;
		while (head != null) {
			if (head.val < x) {
				lTail.next = head;
				lTail = lTail.next;
			} else {
				rTail.next = head;
				rTail = rTail.next;
			}
			head = head.next;
		}
		rTail.next = null;
		lTail.next = rHead.next;
		return lHead.next;
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) { // #88
		int cur = m + n - 1;
		int i = m - 1;
		int j = n - 1;
		while (j >= 0) {
			if (i >= 0 && nums1[i] > nums2[j]) {
				nums1[cur--] = nums1[i--];
			} else {
				nums1[cur--] = nums2[j--];
			}
		}
	}
	
	TreeNode prev;
	TreeNode bigger;
	TreeNode smaller;
//	public void recoverTree(TreeNode root) { // #99
//		helper(root);
//		int tmp = bigger.val;
//		bigger.val = smaller.val;
//		smaller.val = tmp;
//	}
//	void helper(TreeNode root) {
//		if (root == null) {
//			return;
//		}
//		helper(root.left);
//		if (prev != null && prev.val > root.val) {
//			smaller = root;
//			if (bigger == null) {
//				bigger = prev;
//			}
//		}
//		prev = root;
//		helper(root.right);
//	}
	public void recoverTree(TreeNode root) { // #99
		TreeNode node = root;
		while (node != null) {
			if (node.left != null) {
				TreeNode predecessor = node.left;
				while (predecessor.right != null && predecessor.right != node) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = node;
					node = node.left;
				} else {
					predecessor.right = null;
					helper(node);
					node = node.right;
				}
				
			} else {
				helper(node);
				node = node.right;
			}
		}
		int tmp = bigger.val;
		bigger.val = smaller.val;
		smaller.val = tmp;
	}
	void helper(TreeNode node) {
		if (prev.val > node.val) {
			smaller = node;
			if (bigger == null) {
				bigger = prev;
			}
		}
		prev = node;
	}
	public boolean hasPathSum(TreeNode root, int sum) { // #112
		if (root == null) {
			return false;
		}
		sum -= root.val;
		if (root.left == null && root.right == null) {
			return sum == 0;
		}
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}
	public List<List<Integer>> pathSum(TreeNode root, int sum) { // #113
		List<List<Integer>> paths = new ArrayList<>();
		helper(root, sum, paths, new LinkedList<Integer>());
		return paths;
	}
	void helper(TreeNode root, int sum, List<List<Integer>> paths, LinkedList<Integer> path) {
		if (root == null) {
			return;
		}
		sum -= root.val;
		path.addLast(root.val);
		if (root.left == null && root.right == null) {
			if (sum == 0) {
				paths.add(new LinkedList<>(path));
			}
		} else {
			helper(root.left, sum, paths, path);
			helper(root.right, sum, paths, path);
		}
		path.removeLast();
	}
	public int maxProfit(int[] prices) { // #121
		if (prices.length == 0) {
			return 0;
		}
		int minPrice = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > minPrice) {
				maxProfit = Math.max(maxProfit, prices[i] - minPrice);
			} else {
				minPrice = prices[i];
			}
		}
		return maxProfit;
	}

	public String reverseWords(String s) { // #151
		if (s == null) {
			return "";
		}
		char[] chars = s.toCharArray();
		int cur = 0;
		boolean space = true;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				chars[cur++] = chars[i];
				space = false;
			} else if (space == false) {
				chars[cur++] = ' ';
				space = true;
			}
		}
		int len = space ? cur - 1 : cur;
		len = Math.max(len, 0);
		reverse(chars, 0, len);
		int prevSpace = -1;
		for (int i = 0; i < len; i++) {
			if (chars[i] != ' ') {
				continue;
			}
			reverse(chars, prevSpace + 1, i);
			prevSpace = i;
		}
		reverse(chars, prevSpace + 1, len);
		return new String(chars, 0, len);
	}

	void reverse(char[] chars, int l, int r) {
		r--;
		while (l < r) {
			char tmp = chars[l];
			chars[l] = chars[r];
			chars[r] = tmp;
			l++;
			r--;
		}
	}

	static class MinStack { // #155
		Stack<Integer> regular;
		Stack<Integer> min;

		MinStack() {
			regular = new Stack<>();
			min = new Stack<>();
		}

		void push(int x) {
			regular.push(x);
			if (min.isEmpty()) {
				min.push(x);
			} else {
				min.push(Math.min(x, min.peek()));
			}
		}

		void pop() {
			regular.pop();
			min.pop();
		}

		int top() {
			return regular.peek();
		}

		int getMin() {
			return min.peek();
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) { // #160
		if (headA == null || headB == null) {
			return null;
		}
		ListNode curA = headA;
		ListNode curB = headB;
		while (curA != curB) {
			curA = curA == null ? headB : curA.next;
			curB = curB == null ? headA : curB.next;
		}
		return curA;
	}

	public boolean isPalindrome(ListNode head) { // #234
		if (head == null || head.next == null) {
			return true;
		}
		if (head.next.next == null) {
			return head.val == head.next.val;
		}
		ListNode mid = middleNode(head);
		ListNode rHead = reverseList(mid.next);
		ListNode lHead = head;
		while (rHead != null) {
			if (lHead.val != rHead.val) {
				return false;
			}
			lHead = lHead.next;
			rHead = rHead.next;
		}
		return true;
	}

	ListNode middleNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = newHead;
			newHead = head;
			head = tmp;
		}
		return newHead;
	}
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == root || q == root) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}
	public int[] maxSlidingWindow(int[] nums, int k) { // #239
		if (nums.length == 0 || k < 1) {
			return new int[] {};
		}
		int[] max = new int[nums.length - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}
			deque.offerLast(i);
			int w = i - k + 1;
			if (w >= 0) {
				if (deque.peekFirst() < w) {
					deque.pollFirst();
				}
				max[w] = nums[deque.peekFirst()];
			}
		}
		return max;
	}

	public boolean isAnagram(String s, String t) { // #242
		if (s.length() != t.length()) {
			return false;
		}
		int[] counts = new int[26];
		for (int i = 0; i < s.length(); i++) {
			counts[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			if (--counts[t.charAt(i) - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}

	public int lengthOfLIS(int[] nums) { // #300
		int[] dp = new int[nums.length];
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(dp[i], max);
		}
		return max;
	}

	public int coinChange(int[] coins, int amount) { // #322
		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			int min = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (i < coin) {
					continue;
				}
				if (dp[i - coin] < 0 || dp[i - coin] >= min) {
					continue;
				}
				min = dp[i - coin];
			}
			if (min == Integer.MAX_VALUE) {
				dp[i] = -1;
			} else {
				dp[i] = min + 1;
			}
		}
		return dp[amount];
	}
	
	public int largestBSTSubtree(TreeNode root) { // #333
		if (isBST(root)) {
			return getCount(root);
		}
		int left = largestBSTSubtree(root.left);
		int right = largestBSTSubtree(root.right);
		return Math.max(left, right);
	}
	boolean isBST(TreeNode root) {
		return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}
	boolean isBST(TreeNode root, int max, int min) {
		if (root == null) {
			return true;
		}
		return root.val < max && root.val > min && isBST(root.left, root.val, min) &&
				isBST(root.right, max, root.val);
	}
	int getCount(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + getCount(root.left) + getCount(root.right);
	}
	
	public boolean isSubtree(TreeNode s, TreeNode t) { // #572
		return serialize(s, new StringBuilder()).contains(serialize(t, new StringBuilder()));
	}

	String serialize(TreeNode root, StringBuilder sb) {
		if (root.left == null) {
			sb.append("#!");
		} else {
			serialize(root.left, sb);
		}
		if (root.right == null) {
			sb.append("#!");
		} else {
			serialize(root.right, sb);
		}
		sb.append(root.val).append("!");
		return sb.toString();
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) { // #654

		return findRoot(nums, 0, nums.length);
	}

	TreeNode findRoot(int[] nums, int l, int r) {
		if (l == r) {
			return null;
		}
		int maxIndex = l;
		for (int i = l + 1; i < r; i++) {
			if (nums[i] > nums[maxIndex]) {
				maxIndex = i;
			}
		}
		TreeNode root = new TreeNode(nums[maxIndex]);
		root.left = findRoot(nums, l, maxIndex);
		root.right = findRoot(nums, maxIndex + 1, r);
		return root;
	}

	public int[] dailyTemperatures(int[] T) { // #739
		int[] values = new int[T.length];
		for (int i = T.length - 2; i >= 0; i--) {
			int j = i + 1;
			while (true) {
				if (T[j] > T[i]) {
					values[i] = j - i;
					break;
				} else if (values[j] == 0) {
					values[i] = 0;
					break;
				}
				j += values[j];
			}
		}
		return values;
	}

	public int longestCommonSubsequence(String text1, String text2) { // #1143
		int[] dp = new int[text2.length() + 1];
		for (int i = 1; i <= text1.length(); i++) {
			int cur = 0;
			for (int j = 1; j <= text2.length(); j++) {
				int leftTop = cur;
				cur = dp[j];
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j - 1], dp[j]);
				}
			}
		}
		return dp[text2.length()];
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}
}
