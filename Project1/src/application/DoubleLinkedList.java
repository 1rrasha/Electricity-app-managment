package application;
//1210773-Rasha Mansour
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;

	private static class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;

		Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	// add
	public void add(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
	}

	public void addAtIndex(int index, T data) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is negative: " + index);
		}

		Node<T> newNode = new Node<>(data);

		if (index == 0) {
			newNode.next = head;
			head = newNode;
			return;
		}

		Node<T> current = head;
		int currentIndex = 0;

		while (current != null) {
			if (currentIndex == index - 1) {
				newNode.next = current.next;
				current.next = newNode;
				return;
			}
			current = current.next;
			currentIndex++;
		}

		throw new IndexOutOfBoundsException("Index out of bounds: " + index);
	}

	public void addFirst(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
	}

	public void addLast(T data) {
		add(data);
	}

	// remove
	public void removeFirst() {
		if (head != null) {
			head = head.next;
			if (head != null) {
				head.prev = null;
			} else {
				tail = null; // List is empty
			}
		}
	}

	public void removeLast() {
		if (tail != null) {
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			} else {
				head = null; // List is empty
			}
		}
	}

	public void remove(T data) {
		if (head == null) {
			return;
		}

		if (head.data.equals(data)) {
			head = head.next;
			return;
		}

		Node<T> current = head;
		while (current.next != null) {
			if (current.next.data.equals(data)) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
		}
	}

	public void removeAt(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is negative: " + index);
		}

		if (index == 0) {
			head = head.next;
			return;
		}

		Node<T> current = head;
		int currentIndex = 0;
		while (current != null) {
			if (currentIndex == index - 1) {
				if (current.next != null) {
					current.next = current.next.next;
					return;
				} else {
					throw new IndexOutOfBoundsException("Index out of bounds: " + index);
				}
			}
			current = current.next;
			currentIndex++;
		}

		throw new IndexOutOfBoundsException("Index out of bounds: " + index);
	}

	// get
	public T get(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is negative: " + index);
		}

		Node<T> current = head;
		int currentIndex = 0;
		while (current != null) {
			if (currentIndex == index) {
				System.out.println("Element at index " + index + ": " + current.data);
				return current.data;
			}
			current = current.next;
			currentIndex++;
		}

		throw new IndexOutOfBoundsException("Index out of bounds: " + index);
	}

	public T getFirst() {
		if (head == null) {
			throw new NoSuchElementException("List is empty");
		}
		return head.data;
	}

	public T getLast() {
		if (head == null) {
			throw new NoSuchElementException("List is empty");
		}
		Node<T> current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current.data;
	}

	// find
	public Year find(T data) {
		Node<T> current = head;
		while (current != null) {
			if (((Year) current.data).compareTo((Year) data) == 0) {
				return (Year) current.data;
			}
			current = current.next;
		}
		return null;
	}

	public int findIndex(T data) {
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			if (current.data.equals(data)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	// print
	public void print() {
		Node<T> current = head;
		while (current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.println("null");
	}

	// clear
	public void clear() {
		head = null;
	}

	// size
	public int size() {
		int count = 0;
		Node<T> current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	// sorted
	public void addSorted(T element) {
		Comparable<T> comparable = (Comparable<T>) element;

		if (head == null) {
			addFirst(element);
		} else if (comparable.compareTo(head.data) <= 0) {
			addFirst(element);
		} else {
			Node<T> current = head;
			Node<T> previous = null;

			while (current != null && comparable.compareTo(current.data) > 0) {
				previous = current;
				current = current.next;
			}
			Node<T> newNode = new Node<>(element);
			newNode.next = current;
			previous.next = newNode;
		}
	}

	// print
	public void printReverse() {
		Node<T> current = tail;
		while (current != null) {
			System.out.print(current.data + " <-> ");
			current = current.prev;
		}
		System.out.println("null");
	}

	public LinkedList<T> getList() {
		LinkedList<T> list = new LinkedList<>();
		Node<T> current = head;
		while (current != null) {
			list.addLast(current.data);
			current = current.next;
		}
		return list;
	}

	public Node<T> addAndGetNode(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		return newNode;
	}
}
