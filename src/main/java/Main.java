import java.util.*;

public class Main {

	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {

		Queue<String> q = new LinkedList<>();
		q.add("c");
		q.add("c");
		q.add("a");
		q.add("c");

		Queue<Integer> q1 = new LinkedList<>();
		q1.add(11);
		q1.add(33);
		q1.add(2);
		q1.add(33);

		Queue<Integer> q1Copy = copyQueueInt(q1);

		System.out.println("ex1:");
		System.out.println(ex1(q));

		System.out.println("ex2:");
		System.out.println(ex2(q));

		System.out.println("ex3:");
		System.out.println(ex3(q1Copy));

		q1Copy = copyQueueInt(q1);
		System.out.println("ex4:");
		System.out.println(ex4(q1Copy));

		System.out.println("findMax:");
		System.out.println(findMax(q1));

		System.out.println("numOfDigits:");
		System.out.println(numOfDigits(4567));

		System.out.println("placeOfNum:");
		System.out.println(placeOfNum(1234, 0));

		BinNode<Integer> root = new BinNode<>(4);
		root.setLeft(new BinNode<>(3));
		root.setRight(new BinNode<>(5));
		root.getLeft().setLeft(new BinNode<>(1));
		root.getLeft().setRight(new BinNode<>(2));
		System.out.println(root);

		BinNode<Integer> t1 = new BinNode<>(10);

		t1.setLeft(new BinNode<>(5));
		t1.setRight(new BinNode<>(15));

		t1.getLeft().setLeft(new BinNode<>(3));
		t1.getLeft().setRight(new BinNode<>(7));

		t1.getRight().setRight(new BinNode<>(20));


		System.out.println("printPositiveNoEvenChildren:");
		printPositiveNoEvenChildren(root);

		System.out.println("printPositiveNoEvenChildren1:");
		System.out.println(printPositiveNoEvenChildren1(root, 0));

		System.out.println("countNodes:");
		System.out.println(countNodes(root));

		System.out.println("printPositiveNoEvenChildren2:");
		System.out.println(printPositiveNoEvenChildren2(root));

		System.out.println("ex12 " + ex12(root));
		System.out.println("ex16 " + ex16(root));
		System.out.println("ex17 " + ex17(root));
		System.out.println("ex18 " + ex18(root, t1));
        System.out.println("ex20 " + ex20(3, root));
	}

	public static Queue<Integer> ex1(Queue<String> q) {
		Queue<String> qCopy = copyQueueStr(q);
		Queue<Integer> c = new LinkedList<>();
		int count = 1;
		String current;
		String prev = null;

		while (!qCopy.isEmpty()) {
			current = qCopy.poll();
			if (current.equals(prev)) {
				count++;
			} else {
				if (prev != null) {
					c.add(count);
				}
				count = 1;
			}
			prev = current;
		}
		c.add(count);
		return c;
	}

	public static boolean ex2(Queue<String> q) {
		Queue<String> qCopy = copyQueueStr(q);
		Queue<String> qHelp = new LinkedList<>();

		while (!qCopy.isEmpty()) {
			String str = qCopy.poll();
			if (isInStr(qHelp, str)) {
				return true;
			}
			qHelp.add(str);
		}
		return false;
	}

	public static Queue<Integer> ex3(Queue<Integer> q) {
		Queue<Integer> result = new LinkedList<>();
		Set<Integer> seen = new HashSet<>();

		while (!q.isEmpty()) {
			int current = q.poll();
			if (!seen.contains(current)) {
				result.add(current);
				seen.add(current);
			}
		}
		return result;
	}

	public static Queue<Integer> ex4(Queue<Integer> q) {
		Queue<Integer> sortedQueue = new LinkedList<>();
		Queue<Integer> tempQueue = new LinkedList<>();

		while (!q.isEmpty()) {
			int min = q.poll();

			while (!q.isEmpty()) {
				int current = q.poll();
				if (current < min) {
					tempQueue.add(min);
					min = current;
				} else {
					tempQueue.add(current);
				}
			}

			sortedQueue.add(min);

			while (!tempQueue.isEmpty()) {
				q.add(tempQueue.poll());
			}
		}
		return sortedQueue;
	}

	public static Queue<Integer> copyQueueInt(Queue<Integer> q) {
		Queue<Integer> qNew = new LinkedList<>();
		Queue<Integer> temp = new LinkedList<>();

		while (!q.isEmpty()) {
			int num = q.poll();
			qNew.add(num);
			temp.add(num);
		}

		while (!temp.isEmpty()) {
			q.add(temp.poll());
		}
		return qNew;
	}

	public static Queue<String> copyQueueStr(Queue<String> q) {
		Queue<String> qNew = new LinkedList<>();
		Queue<String> temp = new LinkedList<>();

		while (!q.isEmpty()) {
			String num = q.poll();
			qNew.add(num);
			temp.add(num);
		}

		while (!temp.isEmpty()) {
			q.add(temp.poll());
		}
		return qNew;
	}

	public static boolean isInStr(Queue<String> q, String num) {
		Queue<String> qCopy = copyQueueStr(q);

		while (!qCopy.isEmpty()) {
			if (qCopy.poll().equals(num)) {
				return true;
			}
		}
		return false;
	}

	public static int findMax(Queue<Integer> q) {
		Queue<Integer> qCopy = copyQueueInt(q);
		int max = qCopy.poll();

		while (!qCopy.isEmpty()) {
			int num = qCopy.poll();
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

	public static int numOfDigits(int num) {
		return (int) Math.log10(num) + 1;
	}

	public static int placeOfNum(int num, int place) {
		for (int i = 0; i < place; i++) {
			num /= 10;
		}
		return num % 10;
	}

	public static void printPositiveNoEvenChildren(BinNode<Integer> node) {
		if (node == null) {
			return;
		}

		boolean leftIsEven = node.hasLeft() && node.getLeft().getValue() % 2 == 0;
		boolean rightIsEven = node.hasRight() && node.getRight().getValue() % 2 == 0;

		if (node.getValue() > 0 && node.getValue() % 2 == 0 && !leftIsEven && !rightIsEven) {
			System.out.println(node.getValue());
		}

		printPositiveNoEvenChildren(node.getLeft());
		printPositiveNoEvenChildren(node.getRight());
	}

	public static int printPositiveNoEvenChildren1(BinNode<Integer> node, int count) {
		if (node == null) {
			return count;
		}

		boolean leftIsEven = node.hasLeft() && node.getLeft().getValue() % 2 == 0;
		boolean rightIsEven = node.hasRight() && node.getRight().getValue() % 2 == 0;

		if (node.getValue() > 0 && node.getValue() % 2 == 0 && !leftIsEven && !rightIsEven) {
			count++;
		}

		count = printPositiveNoEvenChildren1(node.getLeft(), count);
		count = printPositiveNoEvenChildren1(node.getRight(), count);
		return count;
	}

	public static int countNodes(BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}
		return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public static boolean printPositiveNoEvenChildren2(BinNode<Integer> node) {
		int num = printPositiveNoEvenChildren1(node, 0);
		int total = countNodes(node);
		return num == total;
	}

	public static int countLeaves(BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}
		int num = 0;
		if (!node.hasRight() && !node.hasLeft()) {
			num = 1;
		}
		return num + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public static int countDualLeaves(BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}
		int num = 0;
		if (!node.hasRight() && !node.hasLeft()) {
			if (node.getValue() % 2 == 0) {
				num = 1;
			}
		}
		return num + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public static int count_bintTree(BinNode<Integer> node, int count) {
		if (node == null) {
			return 0;
		}
		int num = 0;
		if (node.hasRight()) {
			if (!node.hasLeft()) {
				num = 1;
			}
		}
		return num + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public static boolean count_bintTree_help(BinNode<Integer> node) {
		int num = count_bintTree(node, 0);
		if(num == 0) {
			return true;
		}
		return false;
	}

	public static int ex12(BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}
		if (node.hasLeft() || node.hasRight()) {
			if (node.getValue() >= 10 && node.getValue() < 100) {
				return 1 + ex12(node.getLeft()) + ex12(node.getRight());
			}
			else {
				return 0 + ex12(node.getLeft()) + ex12(node.getRight());
			}
		} else {
			return 0;
		}

	}

	public static int ex16(BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}

		int num = 0;
		if(node.hasRight() && node.hasLeft()) {
			num = node.getValue();
		}

		return num + ex16(node.getRight()) + ex16(node.getLeft());
	}



	public static int ex17(BinNode<Integer> node) {
		if(node == null) {
			return 0;
		}
		int num = 0;
		if(node.hasRight() && node.hasLeft()) {
			if (checkIfLeaf(node.getLeft()) && checkIfLeaf(node.getRight())) {
				num = 1;
			}

		}

		return num + ex17(node.getRight()) + ex17(node.getLeft());
	}

	public static boolean checkIfLeaf(BinNode<Integer> node) {
		if (node.hasLeft() || node.hasRight()) {
			return true;
		}
		return false;
	}


	public static boolean ex18(BinNode<Integer> t1, BinNode<Integer> t2) {
		if (t2 == null) {
			return true;
		}

		return (isInTree(t2.getValue(), t1) && ex18(t1, t2.getLeft()) && ex18(t1, t2.getRight()));
	}



	public static boolean isInTree(int num, BinNode<Integer> node) {
		if (node == null) {
			return false;
		}

		if (num == node.getValue()) {
			return true;
		}

		return isInTree(num, node.getLeft()) || isInTree(num, node.getRight());
	}

	public static boolean ex20(int n, BinNode<Integer> node) {
		for (int i = 1; i <= n; i++) {
			if (countInTree(i, node) != 1) {
				return false;
			}
		}
		return true;
	}


	public static int countInTree(int num, BinNode<Integer> node) {
		if (node == null) {
			return 0;
		}

		if (node.getValue() == num) {
			return 1 + countInTree(num, node.getLeft()) + countInTree(num, node.getRight());
		}

		return countInTree(num, node.getLeft()) + countInTree(num, node.getRight());
	}



}
