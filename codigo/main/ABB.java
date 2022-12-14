import java.util.TreeMap;

public class ABB<T> {

    private TreeMap<String, T> data;

    public ABB() {
        this.data = new TreeMap<>();
    }

    public T find(String key) {
        if ((this.data.get(key)) == null) {
            throw new RuntimeException("Objeto não existe!");
        } else {
            return this.data.get(key);
        }
    }

    public boolean add(String key, T newElement) {
        boolean result = false;
        if (!this.data.containsKey(key)) {
            this.data.put(key, newElement);
            result = true;
        }
        return result;
    }

    public boolean remove(String key) {
        boolean result = false;
        if (!this.data.containsKey(key)) {
            this.data.remove(key);
            result = true;
        }
        return result;
    }

    public int size() {
        return this.data.size();
    }

    public T[] allElements(T[] array) {
        T[] allData = this.data.values().toArray(array);
        return allData;
    }
}
