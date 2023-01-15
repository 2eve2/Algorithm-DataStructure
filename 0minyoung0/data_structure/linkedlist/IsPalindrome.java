// Linked List ȸ��ã��(Palindrome) in Java
// https://youtu.be/34zpNdrgnpA
import java.util.Stack;

class Node {
	char data;
	Node next = null;
}
class LinkedList {
	Node header;
	LinkedList() {
		header = new Node();
	}
	void append(char d) {
		Node end = new Node();
		end.data = d;
		Node n = header;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}
	void retrieve() {
		Node n = header.next;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
	Node get(int k) {
		Node n = header;
		for (int i = 0; i < k; i++) n = n.next;
		return n;
	}
}
// solution3���� ����� ���� ����� ������ ��ü
class Storage{
	public Node node;
	public boolean result;
	
	Storage (Node n, boolean r){
		node = n;
		result = r;
	}
}
public class IsPalindrome {
	public static void main (String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append('m');
		l1.append('a');
		l1.append('d');
		l1.append('a');
		l1.append('m');
		l1.retrieve(); // m -> a -> d -> a -> m
		System.out.println(isPalindrome1(l1.get(1))); // true
		System.out.println(isPalindrome2(l1.get(1))); // true
		System.out.println(isPalindrome3(l1.get(1))); // true
		l1.append('m');
		l1.retrieve(); // m -> a -> d -> a -> m -> m
		System.out.println(isPalindrome1(l1.get(1))); // false
		System.out.println(isPalindrome2(l1.get(1))); // false
		System.out.println(isPalindrome3(l1.get(1))); // false
	}
	// solution1 : LinkedList�� �Ųٷ� �����Ͽ� ������ ���ϴ� ���
	private static boolean isPalindrome1(Node head) {
		Node reversed = reverseAndClone(head); // �Ųٷ� �����ϴ� �Լ��� reversed ��带 ����
		return isEqual(head, reversed); // �� LinkedList�� ������ Ȯ���ϴ� �Լ��� ����� ����
	}
	private static Node reverseAndClone(Node node){
		Node head = null; // ��ȯ�� ���ο� ��� ����
		while (node != null) { // ���� ����Ʈ�� ����������
			Node n = new Node();
			n.data = node.data; // ���� �����͸� �޾Ƽ�
			n.next = head; // ��� ����� �տ� ���̱�
			head = n; // �׸��� ��� �����͸� �߰��� ���� ����
			node = node.next; // ���� ���� �̵�
		}
		return head; // �Ųٷ� ���ĵ� ����Ʈ�� ��ȯ
	}
	private static boolean isEqual(Node one, Node two) {
		while (one != null && two != null) {
			if (one.data != two.data) return false;
			one = one.next;
			two = two.next;
		}
		return one == null && two == null; // �� Linked List�� ���ÿ� ���� ��쿡�� true�̹Ƿ� ���� Ȯ���Ͽ� ����
	}
	// solution2 : �� ���� ������ '�䳢'�� '�ź���'�� ����ϴ� ���
	// �䳢�� �ѹ��� ��ĭ, �ź��̴� �ѹ��� ��ĭ�� �����Ͽ� �䳢�� ����Ʈ�� ���� �����ϸ� �ź��̰� ����Ʈ�� �߰��� �ְ� ��
	// �ź��̴� ���ÿ� �����͸� �����ϸ� �����ϰ� �߰����ʹ� ���ÿ��� �����͸� ������ ��
	// ����Ʈ ��ü ���� Ȧ/¦ ��� �ʿ�
	private static boolean isPalindrome2(Node head) {
		Node fast = head;
		Node slow = head;
		Stack<Character> stack = new Stack<Character>();
		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) slow = slow.next; // ����Ʈ�� ���̰� Ȧ���� ��� slow �����͸� 1 ������Ŵ (��� ��� ����)
		while (slow != null) {
			char c = stack.pop();
			if (slow.data != c) return false;
			slow = slow.next;
		}
		return true;
	}
	// solution3 : ���ȣ���� �̿��ϴ� ���
	private static boolean isPalindrome3(Node head) {
		int length = getListLength(head);
		Storage storage = isPalindromeRecursive(head, length);
		return storage.result;
	}
	// Linked List�� ���̸� ��ȯ�ϴ� �Լ�
	private static int getListLength(Node l) {
		int total = 0;
		while (l != null) {
			total++;
			l = l.next;
		}
		return total;
	}
	// Linked List�� ���̿� ��� �ּҸ� ���ڷ� �޾Ƽ� storage ��ü�� �ּҸ� ��ȯ
	private static Storage isPalindromeRecursive(Node head, int length) {
		if (head == null || length <= 0) return new Storage(head, true); // ¦�����϶�
		else if (length == 1) return new Storage(head.next, true); // Ȧ�����϶�
		
		Storage storage = isPalindromeRecursive(head.next, length - 2);
		
		if (!storage.result || storage.node == null) return storage; // result�� �ϳ��� false�̸� �� ���� �ʿ䰡 �����Ƿ� ��ȯ
		storage.result = (head.data == storage.node.data);
		storage.node = storage.node.next; // storage ���� ���������� ��� ����
		return storage;
	}
}