package LeetCode;

//dfs, 
class LC200_NumberOfIsland_DFS {
	public int numIslands(char[][] grid) {
		int numIslands = 0;
		if(grid==null || grid.length==0 || grid[0].length==0) {
			return numIslands;
		}
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length;j++) {
				if(grid[i][j]!='1') {
					continue;
				} 
				
				numIslands++;
				dfs(grid, i, j);
			}
		}
		return numIslands;
	}
	public void dfs(char[][] grid, int i, int j) {
		grid[i][j] ='x'; 
		
		//right
		if(j+1<grid[0].length && grid[i][j]=='1') dfs(grid, i, j+1);
		//left 
		if(j-1>=0 && grid[i][j-1]=='1') dfs(grid, i, j-1);
		//up
		if(i-1>=0 && grid[i-1][j]=='1') dfs(grid, i-1, j);
		//down
		if(i+1<grid[0].length && grid[i+1][j]=='1') dfs(grid, i+1, j+1);
	}


}
