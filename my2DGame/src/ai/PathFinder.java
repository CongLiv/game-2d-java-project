package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	
	GamePanel gPanel; 
	Node[][] node;

	ArrayList<Node> openList = new ArrayList<>(); 
	public ArrayList<Node> pathList = new ArrayList<>(); 
	Node startNode, goalNode, currentNode; 
	boolean goalReached = false;
	int step = 0;

	public PathFinder(GamePanel gPanel) {

		this.gPanel = gPanel; 
		instantiateNodes();
	}
	
	
	public void instantiateNodes() {
	
		node = new Node[gPanel.maxWorldCol][gPanel.maxWorldRow];
	

		for (int row = 0; row < gPanel.maxWorldRow; row++)
		{
			for (int col = 0; col < gPanel.maxWorldCol; col++) {
				node[col][row] = new Node(col, row);
			}
		}
			
	}
	
	public void resetNodes() {
		
		
		// RESET
		for (int row = 0; row < gPanel.maxWorldRow; row++)
		{
			for (int col = 0; col < gPanel.maxWorldCol; col++) {
				node[col][row].open = false;
				node[col][row].checked = false;
				node[col][row].solid = false;
			}
		}
		
		
		// RESET OTHER SETTING
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
		
	}
	
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {

		resetNodes();

		//Set Start and Goal node 
		startNode = node[startCol][startRow];
		currentNode = startNode; 
		goalNode = node[goalCol][goalRow]; 
		openList.add(currentNode);
		 
 
		for (int col = 0; col < gPanel.maxWorldRow; col++) {
			for (int row = 0; row < gPanel.maxWorldCol; row++) {
				
				int tileNum = gPanel.tileManager.mapTile[gPanel.currentMap][row][col];
				if (gPanel.tileManager.tile[tileNum].collision == true) {
					node[col][row].solid = true;
				}
				else node[col][row].solid = false;
				
				// SET COST
				getCost(col, row);
				
			}
		}
		
	}
	
	public void getCost(int col, int row) {
		
		
		// G cost
		int xDistance = Math.abs(node[col][row].col - startNode.col);
		int yDistance = Math.abs(node[col][row].row - startNode.row);
		node[col][row].gCost = xDistance + yDistance;
		
		// H cost
		xDistance = Math.abs(node[col][row].col - goalNode.col);
		yDistance = Math.abs(node[col][row].row - goalNode.row);
		node[col][row].hCost  = xDistance + yDistance;
		
		// F cost
		node[col][row].fCost = node[col][row].gCost + node[col][row].hCost;
		
	}
	
	
	public boolean search() {
		while (goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			// CHECK CURRENT NODE
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			
			// OPEN NODE
			if (row - 1 >= 0) {
				openNode(col, row - 1);
			}
			
			if (col - 1 >= 0) {
				openNode(col - 1, row);
			}
			
			if (row + 1 < gPanel.maxWorldRow) {
				openNode(col, row + 1);
			}
			
			if (col + 1 < gPanel.maxWorldCol) {
				openNode(col + 1, row);
			}
			
			
			// FIND BEST NODE
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for (int i = 0; i < openList.size(); i++)
			{
				if (openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				
				else if (openList.get(i).fCost == bestNodefCost) {
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			if (openList.size() == 0) break;
			
			
			// After the loop, openList[bestNodeIndex] is the next step
			
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			
			step++;
		}
		
		return goalReached;
	}
	
	public void openNode(int col, int row) {
		if (node[col][row].open == false && node[col][row].checked == false && node[col][row].solid == false) {
			node[col][row].open = true;
			node[col][row].parent = currentNode;
			openList.add(node[col][row]);
		}
	}
	
	public void trackThePath() {
		Node current = goalNode;
		while(current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}
	
}
