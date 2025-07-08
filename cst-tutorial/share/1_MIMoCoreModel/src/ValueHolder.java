public class ValueHolder {

    private Object value;

    public ValueHolder(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }

    public void set(Object value) {
        this.value = value;
    }
}