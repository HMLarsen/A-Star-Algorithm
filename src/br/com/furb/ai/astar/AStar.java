package br.com.furb.ai.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A-Star algorithm.
 */
public class AStar {

	private Node[][] searchArea;
	private PriorityQueue<Node> openList;
	private Set<Node> closedSet;
	private Node initialNode;
	private Node finalNode;

	public AStar(int rows, int cols, Node initialNode, Node finalNode) {
		setInitialNode(initialNode);
		setFinalNode(finalNode);
		this.searchArea = new Node[rows][cols];
		this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node0, Node node1) {
				return Integer.compare(node0.getFinalCost(), node1.getFinalCost());
			}
		});
		setNodes();
		this.closedSet = new HashSet<>();
	}

	private void setNodes() {
		for (int i = 0; i < searchArea.length; i++) {
			for (int j = 0; j < searchArea[0].length; j++) {
				Node node = new Node(i, j);
				node.calculateHeuristic(getFinalNode());
				this.searchArea[i][j] = node;
			}
		}
	}

	public void setBlocks(int[][] blocksArray) {
		for (int i = 0; i < blocksArray.length; i++) {
			int row = blocksArray[i][0];
			int col = blocksArray[i][1];
			setBlock(row, col);
		}
	}

	public List<Node> findPath() {
		openList.add(initialNode);
		while (!isEmpty(openList)) {
			Node currentNode = openList.poll();
			closedSet.add(currentNode);
			if (isFinalNode(currentNode)) {
				return getPath(currentNode);
			} else {
				addAdjacentNodes(currentNode);
			}
		}
		return new ArrayList<Node>();
	}

	private List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();
		path.add(currentNode);
		Node parent;
		while ((parent = currentNode.getParent()) != null) {
			path.add(0, parent);
			currentNode = parent;
		}
		return path;
	}

	private void addAdjacentNodes(Node currentNode) {
		addAdjacentUpperRow(currentNode);
		addAdjacentMiddleRow(currentNode);
		addAdjacentLowerRow(currentNode);
	}

	private void addAdjacentLowerRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int lowerRow = row + 1;
		if (lowerRow < getSearchArea().length) {
			checkNode(currentNode, col, lowerRow);
		}
	}

	private void addAdjacentMiddleRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int middleRow = row;
		if (col - 1 >= 0) {
			checkNode(currentNode, col - 1, middleRow);
		}
		if (col + 1 < getSearchArea()[0].length) {
			checkNode(currentNode, col + 1, middleRow);
		}
	}

	private void addAdjacentUpperRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int upperRow = row - 1;
		if (upperRow >= 0) {
			checkNode(currentNode, col, upperRow);
		}
	}

	private void checkNode(Node currentNode, int col, int row) {
		Node adjacentNode = getSearchArea()[row][col];
		if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
			if (!getOpenList().contains(adjacentNode)) {
				adjacentNode.setNodeData(currentNode, 10);
				getOpenList().add(adjacentNode);
			} else {
				boolean changed = adjacentNode.checkBetterPath(currentNode, 10);
				if (changed) {
					// Remove and Add the changed node, so that the PriorityQueue can sort again its
					// contents with the modified "finalCost" value of the modified node
					getOpenList().remove(adjacentNode);
					getOpenList().add(adjacentNode);
				}
			}
		}
	}
	
	public int getTotalCost(List<Node> path) {
		Optional<Node> finalNode = path.stream()
				.filter(node -> node.equals(getFinalNode()))
				.findFirst();
		if (finalNode.isPresent()) {
			return finalNode.get().getFinalCost();
		}
		return -1;
	}

	/**
	 * Returns a graphic area based in Strings.
	 * Example:<br>
	 *    0  1  2  3  4  5  6<br>  
	 * 0  -  -  *  *  *  -  -<br>  
	 * 1  -  -  *  B  *  -  -<br>
	 * 2  -  I  *  B  *  F  -<br>
	 * 3  -  -  -  B  -  -  -<br>
	 * 4  -  -  -  -  -  -  -<br>
	 * 5  -  -  -  -  -  -  -<br>
	 * 
	 * @param path path found for the initial until the final node
	 * @return graphic area
	 */
	public String getGraphicArea(List<Node> path) {
		Node[][] matrix = getSearchArea();
		int rows = matrix.length;
		int cols = matrix[0].length;

		StringBuilder graphicArea = new StringBuilder("   ");
		for (int c = 0; c < cols; c++) {
			graphicArea.append(c).append("  ");
		}
		graphicArea.append("\n");
		for (int r = 0; r < rows; r++) {
			graphicArea.append(r).append("  ");
			for (int c = 0; c < cols; c++) {
				Node node = matrix[r][c];
				char character = '-';
				if (node.isBlock()) {
					character = 'B';
				} else if (getInitialNode().equals(node)) {
					character = 'I';
				} else if (getFinalNode().equals(node)) {
					character = 'F';
				} else {
					for (Node nodePath : path) {
						if (node.equals(nodePath)) {
							character = '*';
						}
					}
				}
				graphicArea.append(character).append("  ");
			}
			graphicArea.append("\n");
		}
		return graphicArea.toString();
	}

	private boolean isFinalNode(Node currentNode) {
		return currentNode.equals(finalNode);
	}

	private boolean isEmpty(PriorityQueue<Node> openList) {
		return openList.size() == 0;
	}

	private void setBlock(int row, int col) {
		this.searchArea[row][col].setBlock(true);
	}

	public Node getInitialNode() {
		return initialNode;
	}

	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}

	public Node getFinalNode() {
		return finalNode;
	}

	public void setFinalNode(Node finalNode) {
		this.finalNode = finalNode;
	}

	public Node[][] getSearchArea() {
		return searchArea;
	}

	public void setSearchArea(Node[][] searchArea) {
		this.searchArea = searchArea;
	}

	public PriorityQueue<Node> getOpenList() {
		return openList;
	}

	public void setOpenList(PriorityQueue<Node> openList) {
		this.openList = openList;
	}

	public Set<Node> getClosedSet() {
		return closedSet;
	}

	public void setClosedSet(Set<Node> closedSet) {
		this.closedSet = closedSet;
	}

}
