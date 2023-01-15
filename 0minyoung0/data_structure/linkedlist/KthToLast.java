// �ܹ��� linked list �ں��� ����
// https://www.youtube.com/watch?v=Vb24scNDAVg
// �ܹ��� linked list�� ������ k��° ��带 ã�� �˰������� �����Ͻÿ�
class LinkedList {
	Node header;
	
	public class Node {
		int data;
		Node next = null;
	}
	
	LinkedList() {
		header = new Node();
	}
	
	void append(int d) {
		Node end = new Node();
		end.data = d;
		Node n = header;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}
	
	void delete(int d) {
		Node n = header;
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
			} else {
				n = n.next;
			}
		}
	}
	
	void retrieve() {
		Node n = header.next;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
}

class Reference{ // solution2���� count�� ������ ���� ��ü ����
	public int count = 0;
}

public class KthToLast {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		for (int k = 1; k <= 4; k++) {
			LinkedList.Node kth = KthToLast1(ll.header, k);
			System.out.println("Last k(" + k + ")th data is " + kth.data);
		}
		for (int k = 1; k <= 4; k++) {
			Reference r = new Reference();
			LinkedList.Node found = KthToLast2(ll.header, k, r);
			System.out.println(found.data);
		}
		for (int k = 1 ; k <= 4; k++) {
			LinkedList.Node found = KthToLast3(ll.header, k);
			System.out.println(found.data);
		}
	}
	// solution1 : list�� ������ ��ȸ�Ͽ� ��ü ���̸� ���� �� �ٽ� header�� ���ƿ� total - k��° ����� ���� ��ȯ�ϴ� ���
	private static LinkedList.Node KthToLast1(LinkedList.Node first, int k){
		LinkedList.Node n = first;
		int total = 1;
		while (n.next != null) {
			total++;
			n = n.next;
		}
		n = first;
		for (int i = 1; i < total - k + 1; i++) {
			n = n.next;
		}
		return n;
	}
	// solution2 : ��� ȣ���� ����Ͽ� return �ɶ� count�� ������Ű�� ���
	// Node�� ��ȯ�ϱ� ���� count�� ��ü �ȿ� �־ ��ü�� �ּҸ� ����
	// �ð����⵵ O(N), �������⵵ O(N)
	private static LinkedList.Node KthToLast2(LinkedList.Node n, int k, Reference r){
		if (n == null) {
			return null;
		}
		LinkedList.Node found = KthToLast2(n.next, k, r);
		r.count++;
		if (r.count == k) {
			return n;
		}
		return found;
	}
	// solution3 : 2���� �����͸� Ȱ���Ͽ� p1�� p2���� k��ŭ �ռ� ������ �ϴ� ���
	// �ð����⵵ O(N), �������⵵ O(1)
	private static LinkedList.Node KthToLast3(LinkedList.Node first, int k){
		LinkedList.Node p1 = first;
		LinkedList.Node p2 = first;
		
		for (int i = 0 ; i < k; i++) {
			if (p1 == null) return null;
			p1 = p1.next;
		}
		
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}