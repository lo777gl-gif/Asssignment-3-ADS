public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(50, "Apple");
        tree.put(30, "Banana");
        tree.put(70, "Cherry");
        tree.put(20, "Date");
        tree.put(40, "Elderberry");

        System.out.println("Tree size: " + tree.size());
        
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}