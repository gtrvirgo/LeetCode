package com.leetcode;

import java.util.*;

public class Solutions {

	public int[] twoSum(int[] nums, int target) { // #1
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer prev = map.get(target - nums[i]);
			if (prev != null) {
				return new int[] { prev, i };
			}
			map.put(nums[i], i);
		}
		return new int[] {};
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

//	public int lengthOfLongestSubstring(String s) {
//	if (s == null) {
//		return 0;
//	}
//	char[] chars = s.toCharArray();
//	if (chars.length == 0) {
//		return 0;
//	}
//	Map<Character, Integer> lastOccur = new HashMap<>();
//	lastOccur.put(chars[0], 0);
//	int li = 0; // the beginning of the substring i - 1 ends up
//	int longest = 1;
//	for (int i = 1; i < chars.length; i++) {
//		//Integer pi = lastOccur.getOrDefault(chars[i], -1); // the last occurence of i character
//		Integer pi = lastOccur.get(chars[i]);
//		if (pi != null && li <= pi) {
//			li = pi + 1;
//		}
//		lastOccur.put(chars[i], i);
//		longest = Math.max(longest, i - li + 1);
//	}
//	return longest;
//}
	public int lengthOfLongestSubstring(String s) { // #3
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

	public String convert(String s, int numRows) { // #6
		if (numRows == 1) {
			return s;
		}
		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++) {
			rows.add(new StringBuilder());
		}
		int curRow = 0;
		boolean goingDown = false;
		for (int i = 0; i < s.length(); i++) {
			rows.get(curRow).append(s.charAt(i));
			if (curRow == 0 || curRow == numRows - 1) {
				goingDown = !goingDown;
			}
			curRow += goingDown ? 1 : -1;
		}
		StringBuilder ans = new StringBuilder();
		for (StringBuilder row : rows) {
			ans.append(row);
		}
		return ans.toString();
	}

	public int reverse(int x) { // #7
		int rev = 0;
		int pop = 0;
		while (x != 0) {
			pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10 || rev == Integer.MAX_VALUE / 10 && pop > 7) {
				return 0;
			}
			if (rev < Integer.MIN_VALUE / 10 || rev == Integer.MIN_VALUE / 10 && pop < -8) {
				return 0;
			}
			rev = rev * 10 + pop;
		}
		return rev;
	}

	static final int START = 0;
	static final int SIGNED = 1;
	static final int NUMBER = 2;
	static final int ERR = 3;

	static final int WHITE_SPACE = 0;
	static final int SIGN = 1;
	static final int NUMBER_OR_ID = 2;
	static final int UNKNOWN = 3;

	int state;
	int sign;
	long value;

	int[][] machine = { { START, SIGNED, NUMBER, ERR }, { ERR, ERR, NUMBER, ERR }, { ERR, ERR, NUMBER, ERR },
			{ ERR, ERR, ERR, ERR } };

	public int myAtoi(String str) { // #8
		state = 0;
		sign = 1;
		value = 0;
		int i = 0;
		while (i < str.length()) {
			parse(str.charAt(i));
			i++;
		}
		return (int) (value * sign);
	}

	void parse(char c) {
		state = machine[state][lex(c)];
		if (state == SIGNED) {
			sign = c == '-' ? -1 : 1;
		} else if (state == NUMBER) {
			value = value * 10 + (c - '0');
			value = sign > 0 ? Math.min(Integer.MAX_VALUE, value) : Math.min(-1 * (long) Integer.MIN_VALUE, value);
		}
	}

	int lex(char c) {
		if (' ' == c) {
			return WHITE_SPACE;
		}
		if (Character.isDigit(c)) {
			return NUMBER_OR_ID;
		}
		if ('-' == c || '+' == c) {
			return SIGN;
		}
		return UNKNOWN;
	}

	public boolean isPalindrome(int x) { // #9
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}
		int halfX = 0;
		while (x > halfX) {
			halfX = halfX * 10 + x % 10;
			x /= 10;
		}
		return x == halfX || x == halfX / 10;
	}
	
	public boolean isRegExpMatch(String s, String p) { // #10
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2];
					if (matches(s, p, i, j - 1)) {
						dp[i][j] = dp[i][j] | dp[i - 1][j];
					}
				} else {
					if (matches(s, p, i, j)) {
						dp[i][j] = dp[i - 1][j - 1];
					}
				}
			}
		}
		return dp[s.length()][p.length()];
		
	}
	boolean matches(String s, String p, int i, int j) {
		if (i == 0) {
			return false;
		}
		if (p.charAt(j - 1) == '.') {
			return true;
		}
		return s.charAt(i - 1) == p.charAt(j - 1);
	}

	public int maxArea(int[] height) { // #11
		int left = 0;
		int right = height.length - 1;
		int max = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				int shorter = height[left];
				max = Math.max(max, (right - left) * shorter);
				while (left < right && height[left] <= shorter) {
					left++;
				}
			} else {
				int shorter = height[right];
				max = Math.max(max, (right - left) * shorter);
				while (left < right && height[right] <= shorter) {
					right--;
				}
			}
		}
		return max;
	}

	public String intToRoman(int num) { // #12
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] ints = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < romans.length && num >= 0; i++) {
			while (num >= ints[i]) {
				num -= ints[i];
				sb.append(romans[i]);
			}
		}
		return sb.toString();
	}

	public int romanToInt(String s) { // #13
		int sum = 0;
		int previous = helper(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			int cur = helper(s.charAt(i));
			if (previous < cur) {
				sum -= previous;
			} else {
				sum += previous;
			}
			previous = cur;
		}
		sum += previous;
		return sum;
	}

	int helper(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	public String longestCommonPrefix(String[] strs) { // #14
		if (strs.length == 0 || strs == null) {
			return "";
		}
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			prefix = updatePrefix(prefix, strs[i]);
			if (prefix.length() == 0) {
				break;
			}
		}
		return prefix;
	}

	String updatePrefix(String prefix, String str2) {
		int len = Math.min(prefix.length(), str2.length());
		int i = 0;
		while (i < len) {
			if (prefix.charAt(i) != str2.charAt(i)) {
				break;
			}
			i++;
		}
		return prefix.substring(0, i);
	}

	public List<List<Integer>> threeSum(int[] nums) { // #15
		List<List<Integer>> solutions = new ArrayList<>();
		if (nums.length > 2) {
			Arrays.sort(nums);
			for (int i = 0; i < nums.length - 2; i++) {
				if (i > 0 && nums[i - 1] == nums[i]) {
					continue;
				}
				int right = nums.length - 1;
				int left = i + 1;
				while (left < right) {
					if (nums[i] + nums[left] + nums[right] < 0) {
						left++;
					} else if (nums[i] + nums[left] + nums[right] > 0) {
						right--;
					} else {
						solutions.add(Arrays.asList(nums[i], nums[left], nums[right]));
						while (left < right && nums[right] == nums[right - 1]) {
							right--;
						}
						while (left < right && nums[left] == nums[left + 1]) {
							left++;
						}
						right--;
						left++;
					}
				}
			}
		}
		return solutions;
	}
	
	public int threeSumClosest(int[] nums, int target) { // #16
		long closest = Integer.MAX_VALUE;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			int j = i + 1, k =  nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == target) {
					return target;
				}
				if (Math.abs(sum - target) < Math.abs(closest - target)) {
					closest = sum;
				}
				if (sum > target) {
					int k0 = k - 1;
					while (j < k0 && nums[k] == nums[k0]) {
						k0--;
					}
					k = k0;
				}
				if (sum < target) {
					int j0 = j + 1;
					while (j0 < k && nums[j] == nums[j0]) {
						j0++;
					}
					j = j0;
				}
			}
		}
		return (int) closest;
	}
	
	char[][] dialer = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
			{ 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

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
	
	public List<List<Integer>> fourSum(int[] nums, int target) { // #18
		List<List<Integer>> solutions = new ArrayList<>();
		Arrays.sort(nums);
		for (int a = 0; a <= nums.length - 4; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			for (int b = a + 1; b <= nums.length - 3; b++) {
				if (b > a + 1 && nums[b] == nums[b - 1]) {
					continue;
				}
				int c = b + 1, d = nums.length - 1;
				while (c < d) {
					int sum = nums[a] + nums[b] + nums[c] + nums[d];
					if (sum > target) {
						d--;
					} else if (sum < target) {
						c++;
					} else {
						solutions.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
						while (c < d && nums[c] == nums[c + 1]) {
							c++;
						}
						while (c < d && nums[d] == nums[d - 1]) {
							d--;
						}
						c++;
						d--;
					}
				}
			}
		}
		return solutions;
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

	public List<String> generateParenthesis(int n) { // #22
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

	public int removeDuplicates(int[] nums) { // #26
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {
				nums[++i] = nums[j];
			}
		}
		return i + 1;
	}

	public int removeElement(int[] nums, int val) { // #27
		int cur = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val) {
				continue;
			}
			if (cur != i) {
				nums[cur] = nums[i];
			}
			cur++;
		}
		return cur;
	}

	public int strStr(String haystack, String needle) { // #28
		if (needle.length() == 0) {
			return 0;
		}
		int[] next = getNext(needle);
		int pHayStack = 0;
		int pNeedle = 0;
		while (pHayStack < haystack.length() && pNeedle < needle.length()) {
			if (pNeedle == -1 || haystack.charAt(pHayStack) == needle.charAt(pNeedle)) {
				pHayStack++;
				pNeedle++;
			} else {
				pNeedle = next[pNeedle];
			}
		}
		if (pNeedle == needle.length()) {
			return pHayStack - pNeedle;
		}
		return -1;
	}

	int[] getNext(String needle) {
		int[] next = new int[needle.length()];
		next[0] = -1;
		int front = -1;
		int rear = 0;
		while (rear < needle.length() - 1) {
			if (front == -1 || needle.charAt(front) == needle.charAt(rear)) {
				front++;
				rear++;
				next[rear] = needle.charAt(front) == needle.charAt(rear) ? next[front] : front;
			} else {
				front = next[front];
			}
		}
		return next;
	}

	public List<Integer> findSubstring(String s, String words[]) { // #30
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
			int j = 0;
			while (j < words.length) {
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
				j++;
			}
			if (j == words.length) {
				results.add(i);
			}
			found.clear();
		}
		return results;
	}
	
	public void nextPermutation(int[] nums) { // #31
		int i = nums.length - 2;
		while (i >=0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (j > 0 && nums[j] <= nums[i]) {
				j--;
			}
			Utils.swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}
	void reverse(int[] nums, int start) {
		int end = nums.length - 1;
		while (start < end) {
			Utils.swap(nums, start, end);
			start++;
			end--;
		}
	}
	
	public int longestValidParentheses(String s) { // #32
		Stack<Integer> stack = new Stack<>();
		int len = 0;
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					len = Math.max(len, i - stack.peek());
				}
			}
		}
		return len;
	}

	public int searchInsert(int[] nums, int target) { // #35
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (nums[mid] > target) {
				right = mid;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}

	public boolean isValidSudoku(char[][] board) { // #36
		List<Map<Integer, Integer>> rows = new ArrayList<>(9);
		List<Map<Integer, Integer>> cols = new ArrayList<>(9);
		List<Map<Integer, Integer>> boxes = new ArrayList<>(9);
		for (int i = 0; i < 9; i++) {
			rows.add(new HashMap<Integer, Integer>());
			cols.add(new HashMap<Integer, Integer>());
			boxes.add(new HashMap<Integer, Integer>());
		}
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == '.') {
					continue;
				}
				int n = (int) board[row][col];
				int boxIndex = (row / 3) * 3 + col / 3;
				rows.get(row).put(n, rows.get(row).getOrDefault(n, 0) + 1);
				cols.get(col).put(n, cols.get(row).getOrDefault(n, 0) + 1);
				boxes.get(boxIndex).put(n, boxes.get(boxIndex).getOrDefault(n, 0) + 1);
				if (rows.get(row).get(n) > 1 || cols.get(col).get(n) > 1 || boxes.get(boxIndex).get(n) > 1) {
					return false;
				}
			}
		}
		return true;
	}

//	public String countAndSay(int n) { // #38
//		if (n == 1) {
//			return "1";
//		}
//		String previous = countAndSay(n - 1);
//		StringBuilder sb = new StringBuilder();
//		Integer count = 1;
//		for (int i = 0; i < previous.length(); i++) {
//			if (i + 1 < previous.length() && previous.charAt(i) == previous.charAt(i + 1)) {
//				count++;
//			} else {
//				sb.append(count.toString()).append(previous.charAt(i));
//				count = 1;
//			}
//		}
//		return sb.toString();
//	}

	public String countAndSay(int n) { // #38
		String previous = "1";
		while (n > 1) {
			StringBuilder sb = new StringBuilder();
			Integer count = 1;
			for (int i = 0; i < previous.length(); i++) {
				if (i + 1 < previous.length() && previous.charAt(i) == previous.charAt(i + 1)) {
					count++;
				} else {
					sb.append(count.toString()).append(previous.charAt(i));
					count = 1;
				}
			}
			previous = sb.toString();
			n--;
		}
		return previous;
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) { // #39
		List<List<Integer>> solutions = new ArrayList<>();
		Arrays.sort(candidates);
		helper(candidates, target, 0, new LinkedList<Integer>(), solutions);
		return solutions;
	}

	void helper(int[] candidates, int remain, int begin, LinkedList<Integer> solution, List<List<Integer>> solutions) {
		if (remain == 0) {
			solutions.add(new LinkedList<>(solution));
			return;
		}
		for (int i = begin; i < candidates.length; i++) {
			if (remain - candidates[i] < 0) {
				break;
			}
			solution.addLast(candidates[i]);
			helper(candidates, remain - candidates[i], i, solution, solutions);
			solution.removeLast();
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) { // #40
		List<List<Integer>> solutions = new ArrayList<>();
		if (candidates.length > 0) {
			Arrays.sort(candidates);
			helper2(candidates, target, 0, new LinkedList<Integer>(), solutions);
		}
		return solutions;
	}

	void helper2(int[] candidates, int remain, int begin, LinkedList<Integer> solution, List<List<Integer>> solutions) {
		if (remain == 0) {
			solutions.add(new LinkedList<>(solution));
			return;
		}
		for (int i = begin; i < candidates.length; i++) {
			if (remain - candidates[i] < 0) {
				break;
			}
			if (i > begin && candidates[i] == candidates[i - 1]) {
				continue;
			}
			solution.addLast(candidates[i]);
			helper2(candidates, remain - candidates[i], i + 1, solution, solutions);
			solution.removeLast();
		}
	}
	
	public int firstMissingPositive(int[] nums) { // #41
		int pos = 1;
		while (pos <= nums.length) {
			if (nums[pos - 1] > 0 && nums[pos - 1] < nums.length && nums[pos - 1] != pos && nums[nums[pos - 1] - 1] != nums[pos - 1]) {
				Utils.swap(nums, pos - 1, nums[pos - 1] - 1);
			} else {
				pos++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums.length + 1;
	}

//	public int trap(int[] height) { // #42
//		int[] left = new int[height.length];
//		int[] right = new int[height.length];
//		for (int i = 1; i < height.length - 1; i++) {
//			left[i] = Math.max(left[i - 1], height[i - 1]);
//		}
//		for (int i = height.length - 2; i > 0; i--) {
//			right[i] = Math.max(right[i + 1], height[i + 1]);
//		}
//		int water = 0;
//		for (int i = 1; i < height.length - 1; i++) {
//			int lower = Math.min(left[i], right[i]);
//			if (lower <= height[i]) {
//				continue;
//			}
//			water += lower - height[i];
//		}
//		return water;
//	}
	public int trap(int[] height) { // #42
		int left = 0;
		int right = height.length - 1;
		int maxLower = 0;
		int water = 0;
		while (left < right) {
			int lower = height[height[left] < height[right] ? left++ : right--];
			maxLower = Math.max(maxLower, lower);
			water += maxLower - lower;
		}
		return water;
	}
	
	public boolean isMatch(String s, String p) { // #44
		boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
		dp[0][0] = true;
		for (int i = 1; i <= p.length(); i++) {
			if (p.charAt(i - 1) != '*') {
				break;
			}
			dp[i][0] = true;
		}
		for (int i = 1; i <= p.length(); i++) {
			for (int j = 1; j <= s.length(); j++) {
				if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(i - 1) == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[p.length()][s.length()];
	}
	
//	public int jump(int nums[]) { // #44
//		int cur = nums.length - 1;
//		int steps = 0;
//		while (cur > 0) {
//			for (int i = 0; i < cur; i++) {
//				if (i + nums[i] >= cur) {
//					cur = i;
//					steps++;
//					break;
//				}
//			}
//		}
//		return steps;
//	}
	
	public int jump(int[] nums) { // #44
		int maxReachable = 0;
		int edge = 0;
		int steps = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			maxReachable = Math.max(maxReachable, i + nums[i]);
			if (i == edge) {
				edge = maxReachable;
				steps++;
			}
		}
		return steps;
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

	public List<List<Integer>> permuteUnique(int[] nums) { // #47
		List<List<Integer>> solutions = new ArrayList<>();
		if (nums.length > 0) {
			boolean[] used = new boolean[nums.length];
			helper(nums, solutions, new LinkedList<Integer>(), used, 0);
		}
		return solutions;
	}

	void helper(int[] nums, List<List<Integer>> solutions, LinkedList<Integer> solution, boolean[] used, int level) {
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

//	public double myPow(double x, int n) { // #50
//		if (n == 0) {
//			return 1;
//		}
//		boolean isOdd = (n & 1) == 1;
//		double half = myPow(x, n / 2);
//		half *= half;
//		x = n > 0 ? x : 1 / x;
//		return isOdd ? half * x : half;
//	}
	public double myPow(double x, int n) { // #50
		long exp = n > 0 ? n : -1 * (long) n;
		double ans = 1.0;
		while (exp != 0) {
			if ((exp & 1) == 1) {
				ans *= x;
			}
			x *= x;
			exp >>= 1;
		}
		return n > 0 ? ans : 1 / ans;
	}

	public List<List<String>> solveNQueens(int n) { // #51
		int[] board = new int[n];
		List<List<String>> solutions = new ArrayList<>();
		helper(solutions, board, 0);
		return solutions;
	}

	void helper(List<List<String>> solutions, int[] board, int row) {
		if (row == board.length) {
			List<String> solution = new ArrayList<>();
			for (int i = 0; i < row; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < row; j++) {
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
		for (int col = 0; col < board.length; col++) {
			if (canPlace(row, col, board)) {
				board[row] = col;
				helper(solutions, board, row + 1);
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

	int solutions = 0;

	public int totalNQueens(int n) { // #52
		DFS(n, 0, 0, 0, 0);
		return solutions;
	}

	void DFS(int n, long rows, long hills, long dales, int row) {
		if (row == n) {
			solutions++;
			return;
		}
		long vacant = ((1L << n) - 1) & ~(rows | hills | dales);
		while (vacant != 0) {
			long pick = vacant & -vacant;
			vacant ^= pick;
			DFS(n, rows ^ pick, (hills ^ pick) >> 1, (dales ^ pick) << 1, row + 1);
		}
	}

	public int maxSubArray(int[] nums) { // #53
		int ans = Integer.MIN_VALUE;
		if (nums.length > 0) {
			int cur = -1;
			for (int i = 0; i < nums.length; i++) {
				cur = cur < 0 ? nums[i] : cur + nums[i];
				ans = Math.max(ans, cur);
			}
		}
		return ans;
	}
	
	public boolean canJump(int[] nums) { // #55
		int farest = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i <= farest) {
				farest = Math.max(farest, i + nums[i]);
			}
			if (farest >= nums.length - 1) {
				return true;
			}
		}
		return false;
	}

	public int lengthOfLastWord(String s) { // #58
		int length = 0;
		if (s.length() > 0) {
			int end = s.length() - 1;
			while (end >= 0 && s.charAt(end) == ' ') {
				end--;
			}
			int begin = end;
			while (begin >= 0 && s.charAt(begin) != ' ') {
				begin--;
			}
			length = end - begin;
		}
		return length;
	}

	public int[] plusOne(int[] digits) { // #66
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i]++;
			digits[i] %= 10;
			if (digits[i] != 0) {
				return digits;
			}
		}
		digits = new int[digits.length + 1];
		digits[0] = 1;
		return digits;
	}

	public String addBinary(String a, String b) { // #67
		int len = Math.max(a.length(), b.length());
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = 0; i < len; i++) {
			carry += i < a.length() ? a.charAt(a.length() - i - 1) - '0' : 0;
			carry += i < b.length() ? b.charAt(b.length() - i - 1) - '0' : 0;
			sb.append(carry % 2);
			carry /= 2;
		}
		if (carry != 0) {
			sb.append(carry);
		}
		sb.reverse();
		return sb.toString();
	}

	public int mySqrt(int x) { // #69
		int l = 0;
		int r = x;
		int ans = -1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if ((long) mid * mid <= x) {
				ans = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return ans;
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

	public int removeDuplicatesII(int nums[]) { // #80
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

	public boolean isSameTree(TreeNode p, TreeNode q) { // #100
		Queue<TreeNode> pQueue = new LinkedList<>();
		Queue<TreeNode> qQueue = new LinkedList<>();
		pQueue.offer(p);
		qQueue.offer(q);
		while (!pQueue.isEmpty()) {
			p = pQueue.poll();
			q = qQueue.poll();
			if (!check(p, q)) {
				return false;
			}
			if (p != null) {
				pQueue.offer(p.left);
				qQueue.offer(q.left);
				pQueue.offer(p.right);
				qQueue.offer(q.right);
			}
		}
		return pQueue.size() == qQueue.size();
	}

	boolean check(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		return true;
	}
	
	public boolean isSymmetric(TreeNode root) { // #101
		if (root == null) {
			return true;
		}
		return DFS(root.left, root.right);
	}
	boolean DFS(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null || p.val != q.val) {
			return false;
		}
		return DFS(p.left, q.right) && DFS(p.right, q.left);
	}

	public List<List<Integer>> levelOrder(TreeNode root) { // #102
		List<List<Integer>> solutions = new ArrayList<>();
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			List<Integer> solution = new ArrayList<>();
			queue.offer(root);
			int count = queue.size();
			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				count--;
				solution.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				if (count == 0) {
					count = queue.size();
					solutions.add(new ArrayList<>(solution));
					solution.clear();
				}
			}
		}
		return solutions;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (root != null) {
			queue.offer(root);
		}
		List<List<Integer>> solutions = new ArrayList<>();
		int level = 0;
		int count = queue.size();
		List<Integer> solution = new ArrayList<>();
		while (!queue.isEmpty())  {
			TreeNode node = queue.poll();
			count--;
			solution.add(node.val);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
			if (count == 0) {
				count = queue.size();
				if (level++ % 2 != 0) {
					Collections.reverse(solution);
				}
				solutions.add(new ArrayList<>(solution));
				solution.clear();

			}
		}
		return solutions;
	}

	public int maxDepth(TreeNode root) { // #104
		if (root == null) {
			return 0;
		}
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}

	int preIndex = 0;
	public TreeNode buildTree(int[] preorder, int[] inorder) { // #105
		return helper(preorder, inorder, 0, inorder.length - 1);
	}
	TreeNode helper(int[] preorder, int[] inorder, int inStart, int inEnd) {
		if (inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preIndex++]);
		if (inStart == inEnd) {
			return root;
		}
		int inIndex = find(inorder, inStart, inEnd, root.val);
		root.left = helper(preorder, inorder, inStart, inIndex - 1);
		root.right = helper(preorder, inorder, inIndex + 1, inEnd);
		return root;
	}
	int find(int[] inorder, int start, int end, int val) {
		int i;
		for (i = start; i <= end; i++) {
			if (inorder[i] == val) {
				break;
			}
		}
		return i;
	}
	
	int postIndex;
	public TreeNode buildTreeII(int[] inorder, int[] postorder) { // #106
		postIndex = postorder.length - 1;
		return buildTree(inorder, postorder, 0, inorder.length - 1);
	}
	TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd) {
		if (inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postIndex--]);
		if (inStart == inEnd) {
			return root;
		}
		int inIndex = find(inorder, inStart, inEnd, root.val);
		root.right = buildTree(inorder, postorder, inIndex + 1, inEnd);
		root.left = buildTree(inorder, postorder, inStart, inIndex - 1);
		return root;
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) { // #107
		LinkedList<List<Integer>> solutions = new LinkedList<>();
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			List<Integer> l = new ArrayList<>();
			queue.offer(root);
			int count = queue.size();
			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				count--;
				l.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				if (count == 0) {
					count = queue.size();
					solutions.addFirst(new ArrayList<>(l));
					l.clear();
				}
			}
		}
		return solutions;
	}
	
	public int minDepth(TreeNode root) { // #111
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int minDepth = Integer.MAX_VALUE;
		if (root.left != null) {
			minDepth = Math.min(minDepth, minDepth(root.left));
		}
		if (root.right != null) {
			minDepth = Math.min(minDepth, minDepth(root.right));
		}
		return minDepth + 1;
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

	public List<List<Integer>> generate(int numRows) { // #118
		List<List<Integer>> solutions = new ArrayList<>();
		if (numRows > 0) {
			solutions.add(Arrays.asList(1));
			if (numRows > 1) {
				int level = 1;
				while (level < numRows) {
					List<Integer> cur = new ArrayList<>();
					List<Integer> pre = solutions.get(level - 1);
					cur.add(1);
					for (int i = 1; i < level; i++) {
						cur.add(pre.get(i - 1) + pre.get(i));
					}
					cur.add(1);
					solutions.add(cur);
					level++;
				}
			}
		}
		return solutions;
	}

	public List<Integer> getRow(int rowIndex) { // #119
		Integer[] row = new Integer[rowIndex + 1];
		if (rowIndex >= 0) {
			row[0] = 1;
			if (rowIndex >= 1) {
				int level = 1;
				while (level <= rowIndex) {
					for (int i = level - 1; i > 0; i--) {
						row[i] = row[i - 1] + row[i];
					}
					row[level] = 1;
					level++;
				}
			}
		}
		return Arrays.asList(row);
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

	public int maxProfitII(int[] prices) { // #122
		int profits = 0;
		for (int i = 1; i < prices.length; i++) {
			int tmp = prices[i] - prices[i - 1];
			if (tmp > 0) {
				profits += tmp;
			}
		}
		return profits;
	}

	public boolean isPalindrome(String s) { // #125
		s = s.replaceAll("\\W", "");
		return s.equalsIgnoreCase(new StringBuilder(s).reverse().toString());
	}

	public int singleNumber(int[] nums) { // #136
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}

	public int singleNumberII(int[] nums) { // #137
		int once = 0;
		int twice = 0;
		for (int num : nums) {
			once = once ^ num & ~twice;
			twice = twice ^ num & ~once;
		}
		return once;
	}

	public static class LRUCache { // #146
		int capacity;
		Map<Integer, Node> map;
		Node head;
		Node tail;

		public LRUCache(int capacity) {
			this.capacity = capacity;
			map = new HashMap<>();
			head = new Node();
			tail = new Node();
			head.next = tail;
			tail.previous = head;
		}

		public int get(int key) {
			Node node = map.get(key);
			if (node == null) {
				return -1;
			}
			remove(node);
			insertHead(node);
			return node.value;
		}

		public void put(int key, int value) {
			Node node = map.get(key);
			if (node != null) {
				node.value = value;
				remove(node);
			} else {
				node = new Node(key, value);
				if (map.size() == capacity) {
					remove(map.remove(tail.previous.key));
				}
				map.put(key, node);
			}
			insertHead(node); // make this node hot :)
		}

		void remove(Node node) {
			node.previous.next = node.next;
			node.next.previous = node.previous;
		}

		void insertHead(Node node) {
			node.next = head.next;
			head.next.previous = node;
			node.previous = head;
			head.next = node;
		}

		public static class Node {
			int key;
			int value;
			Node next;
			Node previous;

			Node(int key, int value) {
				this.key = key;
				this.value = value;
			}

			Node() {
			}
		}
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

	public ListNode removeElements(ListNode head, int val) { // #203
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

	public boolean canAttendMeetings(int[][] intervals) { // #252
//		intervals = {
//				{5, 15}, meeting 1, starts 5 ends 15 
//				{25, 40} meeting 2, starts 25 ends 40
//		};
		if (intervals.length > 0) {
			Arrays.sort(intervals, (meeting1, meeting2) -> meeting1[0] - meeting2[0]);
			for (int i = 1; i < intervals.length; i++) {
				if (intervals[i][0] < intervals[i - 1][1]) {
					return false;
				}
			}
		}
		return true;
	}

//	public int minMeetingRooms(int[][] intervals) { // #253
//		PriorityQueue<Integer> convenings = new PriorityQueue<>();
//		if (intervals.length > 0) {
//			Arrays.sort(intervals, (meeting1, meeting2) -> meeting1[0] - meeting2[0]);
//			for (int[] meeting : intervals) {
//				if (!convenings.isEmpty() && meeting[0] >= convenings.peek()) {
//					convenings.remove();
//				}
//				convenings.add(meeting[1]);
//			}
//		}
//		return convenings.size();
//	}

	public int minMeetingRooms(int[][] intervals) {
		int rooms = 0;
		if (intervals.length > 0) {
			int[] starts = new int[intervals.length];
			int[] ends = new int[intervals.length];
			for (int i = 0; i < intervals.length; i++) {
				starts[i] = intervals[i][0];
				ends[i] = intervals[i][1];
			}
			Arrays.sort(starts);
			Arrays.sort(ends);
			int ending = 0;
			for (int start : starts) {
				if (start < ends[ending]) {
					rooms++;
				} else {
					ending++;
				}
			}
		}
		return rooms;
	}

	public void moveZeroes(int nums[]) { // #283
		for (int i = 0, cur = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				continue;
			}
			if (cur != i) {
				nums[cur] = nums[i];
				nums[i] = 0;
			}
			cur++;
		}
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
		return root.val < max && root.val > min && isBST(root.left, root.val, min) && isBST(root.right, max, root.val);
	}

	int getCount(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + getCount(root.left) + getCount(root.right);
	}

	public void reverseString(char[] s) { // #344
		if (s.length > 1) {
			int l = 0;
			int r = s.length;
			while (l < r) {
				char tmp = s[l];
				s[l] = s[r - 1];
				s[r - 1] = tmp;
				l++;
				r--;
			}
		}
	}

	public boolean isPerfectSquare(int num) { // #367
		int odd = 1;
		while (num > 0) {
			num -= odd;
			odd += 2;
		}
		return num == 0;
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

	public boolean isPathCrossing(String path) { // #1496
		HashSet<String> set = new HashSet<>();
		int x = 0, y = 0;
		set.add(x + " " + y);
		char[] chs = path.toCharArray();
		for (char ch : chs) {
			switch (ch) {
			case 'N':
				x--;
				break;
			case 'S':
				x++;
				break;
			case 'W':
				y--;
				break;
			case 'E':
				y++;
				break;
			}
			String point = x + " " + y;
			if (set.contains(point)) {
				return true;
			}
			set.add(point);
		}
		return false;
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

	// Interviews

	/*
	 * https://leetcode-cn.com/problems/sub-sort-lcci/
	 */
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

	/*
	 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
	 */
	public int maxValue(int[][] grid) {
		int[] dp = new int[grid[0].length];
		dp[0] = grid[0][0];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = dp[i - 1] + grid[0][i];
		}
		for (int i = 1; i < grid.length; i++) {
			dp[0] += grid[i][0];
			for (int j = 1; j < grid[0].length; j++) {
				dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
			}
		}
		return dp[dp.length - 1];
	}
//	public int maxValue(int[][] grid) {
//	int[][] dp = new int[grid.length][grid[0].length];
//	dp[0][0] = grid[0][0];
//	for (int i = 1; i < grid[0].length; i++) {
//		dp[0][i] = dp[0][i - 1] + grid[0][i];
//	}
//	for (int i = 1; i < grid.length; i++) {
//		dp[i][0] = dp[i - 1][0] + grid[i][0];
//	}
//	for (int i = 1; i < grid.length; i++) {
//		for (int j = 1; j < grid[0].length; j++) {
//			dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
//		}
//	}
//	return dp[grid.length - 1][grid[0].length - 1];
//}
}
