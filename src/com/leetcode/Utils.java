package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import com.leetcode.Solutions.TreeNode;

public class Utils {
	public static TreeNode createBinaryTree(Integer[] nums) {
		TreeNode root = new TreeNode(nums[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int i = 1;
		while (!queue.isEmpty() && i < nums.length) {
			TreeNode node = queue.poll();
			if (nums[i] != null) {
				node.left = new TreeNode(nums[i]);
				queue.offer(node.left);
			}
			i++;
			if (i < nums.length && nums[i] != null) {
				node.right = new TreeNode(nums[i]);
				queue.offer(node.right);
			}
			i++;
		}
		return root;
	}
	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
