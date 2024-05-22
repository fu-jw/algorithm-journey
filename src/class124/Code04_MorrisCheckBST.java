package class124;

// 测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/
public class Code04_MorrisCheckBST {

	// 不提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 提交如下的方法
	public static boolean isValidBST(TreeNode head) {
		TreeNode cur = head;
		TreeNode mostRight = null;
		TreeNode pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre.val >= cur.val) {
				ans = false;
			}
			pre = cur;
			cur = cur.right;
		}
		return ans;
	}

}
