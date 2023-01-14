// �ܹ��� Linked List ���� in Java
// https://www.youtube.com/watch?v=C1SDkdPvQPA
class Node {
	int data;
	Node next = null;
	
	Node(int d) { // ��带 ������ �� data
		this.data = d;
	}
	
	void append(int d) { // boolean Ÿ������ ����, ���� Ȯ���ϴ°� �� �Ϲ���
		Node end = new Node(d); // �߰��� ���
		Node n = this;
		while (n.next != null) { // n = ������ ��尡 �ɶ�����
			n = n.next;
		}
		n.next = end; // ������ ����� next�� ���ο� ���(end)
	}
	
	void delete(int d) { // ���� ���������� ù ��° ��带 ���� �� ����
		Node n = this; // ������ ��(d)�� ã�� ���� ������ ������(n)
		while (n.next != null) { // ���������� �ι�° ������ ���鼭
			if (n.next.data == d) {
				n.next = n.next.next; // ���� d�� ��� ����
			} else {
				n = n.next;
			}
		}
	}
	
	void retrieve() { // linked list�� ��ȸ�ϸ鼭 �����͸� ���
		Node n = this;
		while (n.next != null) { // ���������� �ι�° ������ ���鼭
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data); // ������ ��� ���
	}
}

public class SinglyLinkedList {
	public static void main(String[] args) {
		Node head = new Node(1);
		head.append(2);
		head.append(3);
		head.append(4);
		head.retrieve(); // 1 -> 2 -> 3 -> 4
		head.delete(2);
		head.retrieve(); // 1 -> 3 -> 4
		head.delete(3);
		head.retrieve(); // 1 -> 4
		// head.delete(1);�� �۵����� ���� 
	}
}