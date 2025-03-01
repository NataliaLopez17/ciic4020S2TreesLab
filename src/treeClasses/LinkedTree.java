package treeClasses;

import java.util.ArrayList;

import treeInterfaces.Position;

/**
 * 
 * @author pedroirivera-vega
 *
 * @param <E> Generic type of the elements to be stored in the nodes
 */
public class LinkedTree<E> extends AbstractTree<E> implements Cloneable {

	private Node<E> root;
	private int size;

	public LinkedTree() {
		root = null;
		size = 0;
	}

	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node<?>))
			throw new IllegalArgumentException("Invalid position type for this implementation.");
		Node<E> np = (Node<E>) p;
		if (np.getParent() == np)
			throw new IllegalArgumentException("Target position is not part of a tree.");

		// the following validates that p is a position in this tree
		if (np.getOwnerTree() != this)
			throw new IllegalArgumentException("Target position is not part of the tree.");
		return np;
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> np = this.validate(p);
		return np.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		Node<E> np = this.validate(p);
		ArrayList<Position<E>> result = new ArrayList<Position<E>>();
		if (np.getChildren() != null)
			for (Position<E> cp : np.getChildren())
				result.add(cp);
		return result;
	}

	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		Node<E> np = validate(p);
		if (np.getChildren() != null)
			return np.getChildren().size();
		else
			return 0;
	}

	@Override
	public int size() {
		return size;
	}

	// some tree update operations.....
	public Position<E> addRoot(E element) throws IllegalStateException {
		if (this.root != null)
			throw new IllegalStateException("Tree must be empty to add a root.");
		root = new Node<>(element, null, null, this);
		size++;
		return root;
	}

	/**
	 * Add a new element as a child to a given position in the tree
	 * 
	 * @param p       the position to be the parent of the new element position
	 * @param element the new element to add to the tree
	 * @return the Position<E> of where the new element is stored
	 * @throws IllegalArgumentException if the position is not valid.....
	 */
	public Position<E> addChild(Position<E> p, E element) throws IllegalArgumentException {
		Node<E> np = validate(p);
		Node<E> nuevo = new Node<>(element, np, null, this);
		if (np.getChildren() == null)
			np.setChildren(new ArrayList<Node<E>>());
		np.getChildren().add(nuevo);
		size++;
		return nuevo;
	}

	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> nodeToRemove = validate(p);
		E elementToRemove = nodeToRemove.getElement();
		Node<E> parent = nodeToRemove.getParent();
		if (parent == null) { // then nodeToRemove is the root
			if (numChildren(nodeToRemove) > 1) {
				throw new IllegalArgumentException("Cannot remove a root having more than one child.");
			} else if (numChildren(nodeToRemove) == 0) {
				root = null;
			} else {
				root = nodeToRemove.getChildren().get(0); // the only child
				root.setParent(null);
			}
		} else {
			for (Node<E> childNTD : nodeToRemove.children) {
				parent.getChildren().add(childNTD);
				// Exercise: why can't we use addChild method here????
				childNTD.setParent(parent);
			}
		}

		/*******************************************************/
		// SOME MISSING CODE HERE -- DISCOVER IT AND ADD...
		// AS SPECIFIED IN EXERCISE 4.

		for (int i = 0; i < nodeToRemove.getParent().getChildren().size(); i++) {
			if (nodeToRemove.getParent() != null
					&& nodeToRemove.getParent().getChildren().get(i).equals(nodeToRemove)) {
				nodeToRemove.getParent().getChildren().remove(i);
			}
		}
		// discard the removed node
		nodeToRemove.discard();

		size--; // adjust size
		return elementToRemove; // return removed value
	}

	// Creating a CLONE
	public LinkedTree<E> clone() throws CloneNotSupportedException {
		LinkedTree<E> other = new LinkedTree<>();
		other.addRoot(root().getElement());
		if (other.root() == null) {
			throw new CloneNotSupportedException();
		}
		cloneSubtree(root(), other, other.root());
		return other;
	}

	private void cloneSubtree(Position<E> rThis, LinkedTree<E> other, Position<E> rOther) {
		for (Position<E> pThis : children(rThis)) {
			Position<E> pOther = other.addChild(rOther, pThis.getElement());
			cloneSubtree(pThis, other, pOther);
		}
	}

	/**
	 * 
	 * @author pedroirivera-vega
	 *
	 * @param <E>
	 */
	@SuppressWarnings("unused")
	private static class Node<E> implements Position<E> {

		private E element;
		private Node<E> parent;
		private ArrayList<Node<E>> children;
		private LinkedTree<E> ownerTree; // the tree the node belongs to

		public Node(E element, Node<E> parent, ArrayList<Node<E>> c, LinkedTree<E> ownerTree) {
			this.element = element;
			this.parent = parent;
			this.children = c;
			this.ownerTree = ownerTree;
		}

		public Node(E element) {
			this.element = element;
			this.parent = this;
			this.children = null;
		}

		public void clear() {
			this.element = null;
			this.parent = this;
			this.children = null;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public ArrayList<Node<E>> getChildren() {
			return children;
		}

		public void setChildren(ArrayList<Node<E>> children) {
			this.children = children;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E e) {
			element = e;
		}

		public LinkedTree<E> getOwnerTree() {
			return ownerTree;
		}

		public void setOwnerTree(LinkedTree<E> t) {
			ownerTree = t;
		}

		public void discard() {
			parent = this;
			element = null;
			children = null;
			ownerTree = null;
		}
	}

}
