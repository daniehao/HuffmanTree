/**
 * Class for the non-leaf Node of Huffman tree which implements the Node interface.
 */
public class trieElementNode implements Node {
  private Node[] children;
  private int symbolNum;

  /**
   * Constructor for trieElementNode.
   *
   * @param symbolNum
   */
  public trieElementNode(int symbolNum) {
    if (symbolNum <= 1) {
      throw new IllegalArgumentException("The number of symbol cannot be less or equals 1.");
    }
    children = new Node[symbolNum];
    this.symbolNum = symbolNum;
  }

  @Override
  public Node add(Character symbol, String code, int position) {
    Node node;
    System.out.println(code);
    if (children[ConvertToDecimal.convert(code.charAt(position))] == null) {
      node = new trieLeafNode(null, symbolNum);
    } else {
      node = children[ConvertToDecimal.convert(code.charAt(position))];
    }
    children[ConvertToDecimal.convert(code.charAt(position))] = node.add(symbol, code,
            position + 1);
    return this;
  }

  @Override
  public void addChildren(int index, Node child) {
    children[index] = child;
  }

  @Override
  public Node getChildNode(int index) {
    return children[index];
  }

  @Override
  public char getData() {
    return (char)0;
  }

  @Override
  public void dfs(StringBuilder sb, StringBuilder currPath) {
    for (int i = 0; i < symbolNum; i++) {
      if (children[i] != null) {
        children[i].dfs(sb, currPath.append(ConvertToDecimal.convertToChar(i)));
        currPath.delete(currPath.length() - 1, currPath.length());
      }
    }
  }
}
