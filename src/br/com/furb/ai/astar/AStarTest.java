package br.com.furb.ai.astar;

import java.util.List;

/**
 * Tests for the algorithm.
 */
public class AStarTest {

	public static void main(String[] args) {
		AStar aStar = getExample1();
		List<Node> path = aStar.findPath();
		System.out.println("Example 1 (cost: " + aStar.getTotalCost(path) + "):\n");
		System.out.println(aStar.getGraphicArea(path));

		aStar = getExample2();
		path = aStar.findPath();
		System.out.println("----------------------");
		System.out.println("Example 2 (cost: " + aStar.getTotalCost(path) + "):\n");
		System.out.println(aStar.getGraphicArea(path));

		aStar = getExample3();
		path = aStar.findPath();
		System.out.println("----------------------");
		System.out.println("Example 3 (cost: " + aStar.getTotalCost(path) + "):\n");
		System.out.println(aStar.getGraphicArea(path));
	}

	/**
	 * Gera um cenário com 6 linhas, 7 colunas e 3 blocos.
	 * 
	 * @return cenário gerado
	 */
	public static AStar getExample1() {
		int rows = 6;
		int cols = 7;
		Node initialNode = new Node(2, 1);
		Node finalNode = new Node(2, 5);
		AStar aStar = new AStar(rows, cols, initialNode, finalNode);
		int[][] blocksArray = new int[][] { { 1, 3 }, { 2, 3 }, { 3, 3 } };
		aStar.setBlocks(blocksArray);
		return aStar;
	}

	/**
	 * Gera um cenário com 7 linhas, 4 colunas e 3 blocos.
	 * 
	 * @return cenário gerado
	 */
	public static AStar getExample2() {
		int rows = 7;
		int cols = 4;
		Node initialNode = new Node(1, 0);
		Node finalNode = new Node(5, 2);
		AStar aStar = new AStar(rows, cols, initialNode, finalNode);
		int[][] blocksArray = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 1 } };
		aStar.setBlocks(blocksArray);
		return aStar;
	}

	/**
	 * Gera um cenário com 7 linhas, 4 colunas e 4 blocos.
	 * 
	 * @return cenário gerado
	 */
	public static AStar getExample3() {
		int rows = 7;
		int cols = 4;
		Node initialNode = new Node(1, 0);
		Node finalNode = new Node(5, 2);
		AStar aStar = new AStar(rows, cols, initialNode, finalNode);
		int[][] blocksArray = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 } };
		aStar.setBlocks(blocksArray);
		return aStar;
	}

}
