package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.*;
import ie.gmit.sw.ai.sprites.NeuralSprite;
public class RecursiveDFSTraversator implements Traversator{
	private Node[][] maze;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	private int visitCount = 0;
	private NeuralSprite sprite;
	
	
	public void traverse(Node[][] maze, Node node, NeuralSprite sprite) {
		this.maze = maze;
		this.sprite = sprite;
		dfs(node);
	}
	public void traverse(Node[][] maze, Node node) {
		this.maze = maze;
		dfs(node);
	}
	
	private void dfs(Node node){
		if (!keepRunning) return;
		
		node.setVisited(true);	
		visitCount++;
		
		if (node.isGoalNode()){
	        time = System.currentTimeMillis() - time; //Stop the clock
	        TraversatorStats.printStats(node, time, visitCount);
	        keepRunning = false;
			return;
		}
		
		try { //Simulate processing each expanded node
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Node[] children = node.children(maze);
		Node[] children = node.adjacentNodes(maze);
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null && !children[i].isVisited()){
				children[i].setParent(node);
				
				try {
					if(node.getNodeType() != '0'){
						if(sprite.getId() != -1){
							System.out.println(sprite.getId());
							sprite.moveSprite(node.getRow(), node.getCol());
						}
						else
							Thread.currentThread().stop();
							break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dfs(children[i]);
			}
		}
	}
}