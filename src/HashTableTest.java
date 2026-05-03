import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(100);
        Random rand = new Random();

        // 1. Add random 10000 elements
        for (int i = 0; i < 10000; i++) {
            // Generating random data to ensure variance
            int randomId = rand.nextInt(100000);
            String randomString = "Data-" + rand.nextInt(500);

            MyTestingClass key = new MyTestingClass(randomId, randomString);
            Student value = new Student("Student " + i);

            table.put(key, value);
        }

        table.printBucketSizes();
    }
}