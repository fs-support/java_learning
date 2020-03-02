package jp.co.FStest08;

public class Beginner_3_01 {
	public static void main(String[] args) {
		String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
		//		String text = encode("AAB");  p1 : i =0  j=2  len=3 source=A  target=B count=2  =>   A2B
		//		String text = encode("AAA");	p2 : i =0  j=2 len=3 source=A target=A count=2  => count+=1 => print A3
		System.out.println(text);
	}

	static String encode(String stringNumber) {
		int count = 1;
		String ret = "";
		all: for (int i = 0; i < stringNumber.length(); i++) {
			String source = stringNumber.substring(i, i + 1);
			for (int j = i + 1; j < stringNumber.length(); j = j + 1) {
				String target = stringNumber.substring(j, j + 1);
				int len = stringNumber.length();
				if (len == (j + 1)) {
					if (source.equals(target)) {
						count++;
						ret = ret + (source + String.valueOf(count));
					} else {
						ret = ret + source + count + target;
					}
					break all;
				}
				if (source.equals(target)) {
					count++;
				} else {
					if (count <= 1) {
						ret = ret + source;
					} else {
						ret = ret + (source + String.valueOf(count));
					}
					count = 1;
					i = j - 1;
					break;
				}
			}
		}
		return ret;
	}
}
