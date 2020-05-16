package br.com.furb.ai.astar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Tests for the algorithm.
 */
public class AStarTest {

	public static void main(String[] args) {
		try {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				// initialize variables to use below
				int rows = 0, cols = 0;
				Node initialNode = null, finalNode = null;
				List<int[]> blocks = new ArrayList<>();

				// get and read file's lines
				File file = chooser.getSelectedFile();
				try (Scanner scanner = new Scanner(file)) {
					int lineNumber = 1;
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						// based on lineNumber get the rows, cols
						// initial and final node
						// and by the default are the blocks
						switch (lineNumber) {
						case 1:
							String[] parts = line.split(",");
							rows = Integer.parseInt(parts[0].trim());
							cols = Integer.parseInt(parts[1].trim());
							break;
						case 2:
							initialNode = getNodeByInput(line);
							break;
						case 3:
							finalNode = getNodeByInput(line);
							break;
						default:
							parts = line.split("-");
							int row = Integer.parseInt(parts[0].trim());
							int col = Integer.parseInt(parts[1].trim());
							int[] object = { row, col };
							blocks.add(object);
						}
						lineNumber++;
					}
				}

				// create algorithm object based in above informations
				AStar aStar = new AStar(rows, cols, initialNode, finalNode);
				int[][] blocksArray = new int[blocks.size()][];
				blocks.toArray(blocksArray);
				aStar.setBlocks(blocksArray);

				// get and print the area with the path
				List<Node> path = aStar.findPath();
				System.out.println("Area (cost: " + aStar.getTotalCost(path) + "):\n");
				System.out.println(aStar.getGraphicArea(path));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Bad input: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
	}

	/**
	 * Split @param line with "-" and gets row and column respectively.
	 * 
	 * @param line line read by the file
	 * @return Node object based in the @param line
	 */
	private static Node getNodeByInput(String line) {
		String[] parts = line.split("-");
		int row = Integer.parseInt(parts[0].trim());
		int col = Integer.parseInt(parts[1].trim());
		return new Node(row, col);
	}

}
