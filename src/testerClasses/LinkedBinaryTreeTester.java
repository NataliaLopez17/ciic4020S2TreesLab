package testerClasses;

import labUtils.Utils;
import treeClasses.LinkedBinaryTree;
import treeInterfaces.Position;

public class LinkedBinaryTreeTester {

	public static void main(String[] args) throws CloneNotSupportedException {
		LinkedBinaryTree<String> t = new LinkedBinaryTree<>(), t1 = new LinkedBinaryTree<>();

		// add nodes and data to the tree
		Position<String> p = t.addRoot("ROOT");
		t.addLeft(p, "Rosa");
		p = t.addRight(p, "Maria");
		Position<String> ptd = p; // saved for future test of remove
		Position<String> p1 = t.addRight(p, "Juana");
		p1 = t.addLeft(p1, "Lola");
		t.addLeft(p1, "Pepote");
		p1 = t.addRight(p1, "Manolo");
		p1 = t.addLeft(p1, "Eligio");
		t.addRight(p1, "Eda");
		t.addLeft(p1, "Deborah");
		p1 = t.addLeft(p, "Pergamino");
		t.addLeft(p, "Bienvenido");
		t.addRight(p1, "Manolin");
		t.addLeft(p1, "Juaniquillo");
		t.addRight(p1, "Andres");
		p1 = t.addRight(t.root(), "Mariola");
		p = p1;
		p1 = t.addLeft(p1, "Leslo");
		p1 = t.addLeft(p1, "Papin");
		p1 = t.addLeft(p1, "Ana");
		t.addRight(p, "Elegancia");

		Utils.displayTree("Original t", t);

		// make a clone of t
		t1 = t.clone();
		Utils.displayTree("Clone of t", t1);

		String removedElement = t.remove(ptd);
		Utils.displayTree("Tree t after removing " + removedElement, t);
		Utils.displayTree("Clone of t", t1);

	}

}
