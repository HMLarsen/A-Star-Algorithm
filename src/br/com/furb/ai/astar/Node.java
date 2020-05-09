package br.com.furb.ai.astar;

/**
 * Representation of a subject (position) in the algorithm area.<br>
 * It can be a block node, empty node or initial/final node of the area.
 */
public class Node {

	private int cost;
	private int finalCost;
	private int heuristic;
	private int row;
	private int col;
	private boolean isBlock;
	private Node parent;

	/**
	 * Default constructor.
	 * 
	 * @param row row in the area
	 * @param col col in the are
	 */
	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	/**
	 * Heuristic calculated by the final node.<br>
	 * Difference by the rows + difference by the cols.
	 * 
	 * @param finalNode final node of the algorithm
	 */
	public void calculateHeuristic(Node finalNode) {
		heuristic = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
	}

	public void setNodeData(Node currentNode, int cost) {
		int gCost = currentNode.getCost() + cost;
		setParent(currentNode);
		setCost(gCost);
		calculateFinalCost();
	}

	public boolean checkBetterPath(Node currentNode, int cost) {
		int gCost = currentNode.getCost() + cost;
		if (gCost < getCost()) {
			setNodeData(currentNode, cost);
			return true;
		}
		return false;
	}

	/**
	 * Final cost set by cost + heuristic.
	 */
	private void calculateFinalCost() {
		int finalCost = getCost() + getHeuristic();
		setFinalCost(finalCost);
	}

	/**
	 * The equality is based in same row and column.
	 */
	@Override
	public boolean equals(Object arg0) {
		Node other = (Node) arg0;
		return this.getRow() == other.getRow() && this.getCol() == other.getCol();
	}

	/**
	 * Example: Node [row=X, col=X].
	 */
	@Override
	public String toString() {
		return "Node [row=" + row + ", col=" + col + "]";
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(int finalCost) {
		this.finalCost = finalCost;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
