
public class ValueHolder extends Model {

	private Object value;

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object newValue) {
		if(value == null || value.equals(newValue) == false) {
			value = newValue;
			changed(newValue);
		}
	}
}
