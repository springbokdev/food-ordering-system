package space.springbok.ordering.system.domain.valueobject;

public abstract class BaseId<T> {

    private final T value;

    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId<?> baseId)) return false;

        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
