package code;

// 普通二叉树上寻找两个节点的最近公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class Video_037_2_LowestCommonAncestor {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交如下的方法
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode l = lowestCommonAncestor(root.left, p, q);
		TreeNode r = lowestCommonAncestor(root.right, p, q);
		if (l == null && r == null) {
			return null;
		}
		if (l != null && r != null) {
			return root;
		}
		return l != null ? l : r;
	}

}
