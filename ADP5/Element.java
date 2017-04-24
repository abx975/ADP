public class Element<T> {

	private long key;

	private T data;

	public Element(long key, T data) {
		this.data = data;
		this.key = key;
	}

	public Element(long key) {
		this(key, null);
	}

	public Element(T data) {
		this((long) (Math.random() * 10), data);
	}

	public long getKey() {
		return key;
	}

	public T getData() {
		return data;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public void setData(T data) {
		this.data = data;
	}
}