package labUtils;

import treeClasses.LinkedBinaryTree;
import treeClasses.LinkedTree;
import treeInterfaces.Position;
import treeInterfaces.Tree;

public class Utils {
	public static <E> void displayTree(String msg, Tree<E> t) {
		System.out.println("\n\n" + msg);
		t.display();
	}

	public static <E> void displayTreeElements(String msg, Tree<E> t) {
		System.out.println("\n\n" + msg);
		for (E element : t)
			System.out.println(element);

	}

	public static LinkedTree<Integer> buildExampleTreeAsLinkedTree() {
		LinkedTree<Integer> t = new LinkedTree<>();
		// ADD CODE AS SPECIFIED IN EXERCISE 1
		t.addRoot(4);

		Position<Integer> p = t.addChild(t.root(), 9);
		t.addChild(p, 7);
		t.addChild(p, 10);

		Position<Integer> p2 = t.addChild(t.root(), 20);
		Position<Integer> p3 = t.addChild(p2, 15);
		Position<Integer> p4 = t.addChild(p2, 21);

		t.addChild(p3, 12);
		Position<Integer> p5 = t.addChild(p3, 17);
		t.addChild(p5, 19);

		Position<Integer> p6 = t.addChild(p4, 40);
		t.addChild(p6, 30);
		t.addChild(p6, 45);

		return t;
	}

	public static LinkedBinaryTree<Integer> buildExampleTreeAsLinkedBinaryTree() {
		LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();
		// ADD CODE AS SPECIFIED IN EXERCISE 2
		t.addRoot(4);

		Position<Integer> nine = t.addLeft(t.root(), 9);
		Position<Integer> twenty = t.addRight(t.root(), 20);

		t.addLeft(nine, 7);
		t.addRight(nine, 10);

		Position<Integer> fifteen = t.addLeft(twenty, 15);
		Position<Integer> twentyOne = t.addRight(twenty, 21);

		t.addLeft(fifteen, 12);
		Position<Integer> seventeen = t.addRight(fifteen, 17);
		t.addLeft(seventeen, 19);

		Position<Integer> forty = t.addRight(twentyOne, 40);
		t.addLeft(forty, 30);
		t.addRight(forty, 45);

		return t;
	}

}
