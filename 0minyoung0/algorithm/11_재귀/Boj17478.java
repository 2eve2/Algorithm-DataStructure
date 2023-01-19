// boj 17478
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17478 {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		sb.append("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.\n");
		recursive(n, n);
		System.out.println(sb);
	}
	private static void recursive(int n, int m) {
		for (int i=0; i<n-m; i++) sb.append("____");
		sb.append("\"����Լ��� ������?\"\n");
		if (m==0) {
			for (int i=0; i<n-m; i++) sb.append("____");
			sb.append("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"\n");
		}
		else {
			for (int i=0; i<n-m; i++) sb.append("____");
			sb.append("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.\n");
			for (int i=0; i<n-m; i++) sb.append("____");
			sb.append("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.\n");
			for (int i=0; i<n-m; i++) sb.append("____");
			sb.append("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"\n");
		}
		if (m==0) {
			for (int i=0; i<n-m; i++) sb.append("____");
			sb.append("��� �亯�Ͽ���.\n");
			return;
		}
		recursive(n, m-1);
		for (int i=0; i<n-m; i++) sb.append("____");
		sb.append("��� �亯�Ͽ���.\n");
	}
}