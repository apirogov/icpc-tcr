/* Trie
 * Efficient string set management - add and find
 */
import java.util.HashMap;

class TrieNode {
	public HashMap<Character, TrieNode> next = new HashMap<Character, TrieNode>();

	public void add(String s) { add(s.toCharArray(), 0, s.length()-1); }
	public void add(char[] s, int start, int end) {
		char curr = '\0';
		if (start<=end)
			curr = s[start];

		TrieNode n = next.get(curr);
		if (n==null)
			n = new TrieNode();

		if (start <= end) {
			n.add(s, start+1, end);
		}
		next.put(curr, n);
	}
	public boolean find(String s){ return find(s.toCharArray(), 0, s.length()-1); }
	public boolean find(char[] s, int start, int end) {
		if (start>end) {
			if (next.containsKey('\0'))
				return true;
			else
				return false;
		}
		char c = s[start];
		if (next.containsKey(c))
			return next.get(c).find(s, start+1, end);
		return false;
	}
}
