
public class CordinateNormalizer {
	public static int norm(int cord, int val) {
		return cord - (cord%val);
	}
}
