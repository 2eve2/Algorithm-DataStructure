// Linked List Digit �ջ� �˰��� in Java
// https://youtu.be/vuJk2JZd3fI
// ����
// � ���ڸ� �ڸ��� ���� �Ѱ��� Linked List�� ��Ҵ�
// �׷��� 1���ڸ��� ����� ������ �Ųٷ� ��Ҵ�
// �̷����� Linked List �ΰ��� �޾Ƽ� �ջ��ϰ�, ���� ������ Linked List�� ��Ƽ� ��ȯ�϶�
// ����) l1 : 9->1->4, l2 : 6->4->3, ��� : 5->6->7
// ��ȭ����
// Linked List�� ���ڰ� �Ųٷΰ� �ƴ϶��?
// ����) l1 : 4->1->9, l2 : 3->4, ��� : 4->5->3
class Node {
	int data;
	Node next = null;
}
class LinkedList {
	Node header;
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
// ��ȭ solution�� ���� ���� carry�� ������ ��ü
class Storage{
	int carry = 0;
	Node result = null;
}

public class SumLists {
	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append(9);
		l1.append(1);
		l1.append(4);
		l1.retrieve(); // 9 -> 1 -> 4
		
		LinkedList l2 = new LinkedList();
		l2.append(6);
		l2.append(4);
		l2.append(3);
		l2.retrieve(); // 6 -> 4 -> 3
		
		Node n1 = sumLists1(l1.get(1), l2.get(1), 0);
		while (n1.next != null) {
			System.out.print(n1.data + " -> ");
			n1 = n1.next;
		}
		System.out.println(n1.data); // 5 -> 6 -> 7
		
		LinkedList l3 = new LinkedList();
		l3.append(9);
		l3.append(1);
		l3.retrieve(); // 9 -> 1
		
		LinkedList l4 = new LinkedList();
		l4.append(1);
		l4.append(1);
		l4.retrieve(); // 1 -> 1
		
		Node n2 = sumLists2(l3.get(1), l4.get(1));
		while (n2.next != null) {
			System.out.print(n2.data + " -> ");
			n2 = n2.next;
		}
		System.out.println(n2.data); // 1 -> 0 -> 2
	}
	// solution : ���ȣ���� �̿��� ���
	private static Node sumLists1(Node l1, Node l2, int carry){
		if (l1 == null && l2 == null && carry == 0) return null; // ���̻� ����Ұ� ������ null ��ȯ
		Node result = new Node(); // ��� ��� ����
		int value = carry; // �ø��Ͽ� �Ѿ�� �� carry�� value�� �ο�
		
		if (l1 != null) value += l1.data; // 1�� Linked List�� �����Ͱ� �ִٸ� value�� �ջ�
		if (l2 != null) value += l2.data; // 2�� Linked List�� �����Ͱ� �ִٸ� value�� �ջ�
		result.data = value % 10; // value�� 1�� �ڸ��� result�� �����ͷ� ����
		
		if (l1 != null || l2 != null) { // ��� �ϳ��� Linked List�� �����Ͱ� �ִٸ� (�����ڸ� ����� �ʿ��ϴٸ�)
			Node next = sumLists1(l1 == null? null : l1.next, // l1�� null�� �ƴϸ� l1�� ���� ����
											 l2 == null? null : l2.next, // l2�� null�� �ƴϸ� l2�� ���� ����
											 value >= 10? 1 : 0);		 // value�� 10�̻��̶�� (�ڸ��� �ø��� �ִٸ�) carry�� 1��
																		 // �ϴ� ���ڸ� ������ sumList1()�� ȣ���Ͽ� next ��带 ����
			result.next = next; // result�� ���� ��带 next ���� ����
		}
		return result;
	}
	// ��ȭ solution : ���̸� �켱������ ���� �� ª�� ����Ʈ�� ���� 0���� ä�� �� �ڿ������� ���
	// �ڿ������� ����ؾ��ϹǷ� ����� ���� ���� carry�� ���� ������ ��ü�� ��ȯ�ϴ� ���·� ����
	private static Node sumLists2(Node l1, Node l2){
		int len1 = getListLength(l1);
		int len2 = getListLength(l2);
		
		if (len1 < len2) l1 = LPadList(l1, len2 - len1);
		else l2 = LPadList(l2, len1 - len2);
		
		Storage storage = addLists(l1, l2);
		// ����Ʈ�� ���̸� �Ѿ������ carry�� ���� ��츦 ����ó�� (���� : 91 + 10 = 101)
		if (storage.carry != 0) storage.result = insertBefore(storage.result, storage.carry);
		return storage.result;
	}
	// �� Linked List�� ���� �� storage�� ��ȯ�ϴ� �Լ�
	private static Storage addLists(Node l1, Node l2) {
		if (l1 == null && l2 == null) { // ��������
			Storage storage = new Storage();
			return storage;
		}
		Storage storage = addLists(l1.next, l2.next); // �������� �ɶ����� �� ȣ���ؼ� ��
		int value = storage.carry + l1.data + l2.data; // �ش� �Լ� �������� value ���
		int data = value % 10; // �ش� �Լ� �������� data ���
		storage.result = insertBefore(storage.result, data); // ������ result �տ� ���� data�� ������ ��� �߰�
		storage.carry = value / 10; // carry�� 10���� ���� ��
		return storage;
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
	// Linked List �տ� ���ο� ��带 �߰��ϴ� �Լ�
	private static Node insertBefore(Node node, int data){
		Node before = new Node();
		before.data = data;
		if (node != null) before.next = node;
		return before;
	}
	// ���̸� �޾Ƽ� ���̸�ŭ Linked List �տ� 0��带 ä���ִ� �Լ�
	private static Node LPadList(Node l, int length){
		Node head = l;
		for (int i = 0; i < length; i++) head = insertBefore(head, 0);
		return head;
	}
}