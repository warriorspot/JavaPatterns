
import java.util.List;
import java.util.LinkedList;

public class Model extends Object {

	protected List<ChangeListener> dependents;

	public void addDependent(ChangeListener dependent) {
		if(dependents == null) {
			dependents = new LinkedList<ChangeListener>();
		}

		if(!dependents.contains(dependent)) {
			dependents.add(dependent);
		}
	}

	public ChangeListener removeDependent(ChangeListener listener) {
		if(getDependents().remove(listener)) {
			return listener;
		}

		return null;
	}

	public void changed(Object value) {
		for(ChangeListener listener : getDependents()) {
			listener.update(value);
		}
	}

	public List<ChangeListener> getDependents() {
		return dependents;
	}
}

