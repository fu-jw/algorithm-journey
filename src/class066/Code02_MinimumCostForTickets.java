package class066;

import java.util.Arrays;

// 最低票价
// 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
// 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出
// 每一项是一个从 1 到 365 的整数
// 火车票有 三种不同的销售方式
// 一张 为期1天 的通行证售价为 costs[0] 美元
// 一张 为期7天 的通行证售价为 costs[1] 美元
// 一张 为期30天 的通行证售价为 costs[2] 美元
// 通行证允许数天无限制的旅行
// 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证
// 那么我们可以连着旅行 7 天(第2~8天)
// 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
// 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/
public class Code02_MinimumCostForTickets {

	// 最自然的暴力尝试
	public static int mincostTickets1(int[] days, int[] costs) {
		return f1(days, costs, 0);
	}

	public static int f1(int[] days, int[] costs, int i) {
		if (i == days.length) {
			return 0;
		}
		int j = i;
		while (j < days.length && days[i] + 1 > days[j]) {
			j++;
		}
		int p1 = costs[0] + f1(days, costs, j);
		while (j < days.length && days[i] + 7 > days[j]) {
			j++;
		}
		int p2 = costs[1] + f1(days, costs, j);
		while (j < days.length && days[i] + 30 > days[j]) {
			j++;
		}
		int p3 = costs[2] + f1(days, costs, j);
		return Math.min(Math.min(p1, p2), p3);
	}

	// 稍微在写法上进步了一些
	// 但还是暴力尝试
	public static int[] durations = { 1, 7, 30 };

	public static int mincostTickets2(int[] days, int[] costs) {
		return f2(days, costs, 0);
	}

	public static int f2(int[] days, int[] costs, int i) {
		if (i == days.length) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		for (int k = 0, j = i; k < 3; k++) {
			while (j < days.length && days[i] + durations[k] > days[j]) {
				j++;
			}
			ans = Math.min(ans, costs[k] + f2(days, costs, j));
		}
		return ans;
	}

	// 暴力尝试改记忆化搜索
	public static int mincostTickets3(int[] days, int[] costs) {
		int[] dp = new int[days.length];
		for (int i = 0; i < days.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		return f3(days, costs, 0, dp);
	}

	public static int f3(int[] days, int[] costs, int i, int[] dp) {
		if (i == days.length) {
			return 0;
		}
		if (dp[i] != Integer.MAX_VALUE) {
			return dp[i];
		}
		int ans = Integer.MAX_VALUE;
		for (int k = 0, j = i; k < 3; k++) {
			while (j < days.length && days[i] + durations[k] > days[j]) {
				j++;
			}
			ans = Math.min(ans, costs[k] + f3(days, costs, j, dp));
		}
		dp[i] = ans;
		return ans;
	}

	// 严格位置依赖的动态规划
	public static int MAXN = 401;

	public static int[] dp = new int[MAXN];

	public static int mincostTickets4(int[] days, int[] costs) {
		int n = days.length;
		Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
		dp[n] = 0;
		for (int i = n - 1; i >= 0; i--) {
			for (int k = 0, j = i; k < 3; k++) {
				while (j < days.length && days[i] + durations[k] > days[j]) {
					j++;
				}
				dp[i] = Math.min(dp[i], costs[k] + dp[j]);
			}
		}
		return dp[0];
	}

}
