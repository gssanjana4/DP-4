// TC: O(mn) where m and n is length and breadth of matrix.
// SC: O(mn) for storing values in dp.

public class maximalSquare {

	public int maxSquare(char[][] matrix) {
		
		if(matrix == null || matrix.length == 0)
			return 0;
		
		int m = matrix.length;
		int n = matrix[0].length;
		int maxsq = 0;
		int[][] dp = new int[m+1][n+1];
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(matrix[i-1][j-1]=='1') {
					// if the matrix value is 1, update the dp value by comparing the minimum of row and column and add 1 to it
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
					// this will ensure that we are storing only length of the matrix if both the diagonal values are 1, then our result will be 2, 
					// if one of the side values are , we will update it to 1, this will ensure that we are not updating only if one value in row/ column is 1
					maxsq = Math.max(maxsq, dp[i][j]); // update the max square possible
				}
			}
		}
		return maxsq *maxsq;
	}
	
	// TC: O(mn) where m and n is length and breadth of matrix.
	// SC: O(n) where n is the columns in the matrix used for storing values in dp.
	public int maxSquareII(char[][] matrix) {
		
		if(matrix == null || matrix.length == 0)
			return 0;
		
		int m = matrix.length;
		int n = matrix[0].length;
		int maxsq = 0, prev = 0;
		int[] dp = new int[n+1];
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				// keep track of previous row, dp of column value because we need to compare with the previous row and previous column values
				int temp = dp[j];
				if(matrix[i-1][j-1]=='1') {
					// if the values in the matrix is 1, update the dp values with comparing minimum of the previous row and column values +1
					dp[j] = Math.min(Math.min(dp[j-1], prev), dp[j]) + 1;
					// update the max square that is possible
					maxsq = Math.max(maxsq, dp[j]);
				}else
					dp[j] = 0;  // update dp[j] as 0 because we do not want to keep the previous values
				// which might change our result even though we have not encountered 1
				// update the previous with temp which we stored before updating dp[j]
				prev = temp;
			}
		}
		return maxsq * maxsq;
	}
}
