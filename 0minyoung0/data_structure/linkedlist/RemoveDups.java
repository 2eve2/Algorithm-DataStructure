// ���ĵǾ� ���� ���� linked list�� �ߺ��Ǵ� ���� ����
// https://www.youtube.com/watch?v=Ce4baygLMz0
// LinkedList Ŭ�����ȿ� removeDups() �����Լ��� ����
class LinkedList {
	Node header;
	
	static class Node {
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
	
	void removeDups() {
		Node n = header;
		while (n != null && n.next != null) { // ������ �� ��尡 �ߺ��� ��츦 ����Ͽ� n != null ������ �ʿ���
			Node r = n; // ������ ���� ��ġ
			while (r.next != null) { // ���ʴ� ���������� �� ��° ������ ��
				if (n.data == r.next.data) {
					r.next = r.next.next;
				} else {
					r = r.next;
				}
			}
			n = n.next;
		}
	}
}

public class RemoveDups {
	public static void main (String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(2);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(4);
		ll.append(2);
		ll.append(2);
		ll.retrieve(); // 2 -> 1 -> 2 -> 3 -> 4 -> 4 -> 2 -> 2
		ll.removeDups();
		ll.retrieve(); // 2 -> 1 -> 3 -> 4
	}
}
