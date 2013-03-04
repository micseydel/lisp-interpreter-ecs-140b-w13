import java.util.Map;

class Number implements Atom {
    private final String string;
    public final Integer value;

    public static final Number t = new Number(-999);

    Number(int value) {
        this.value = value;
        string = this.value.toString();
    }

    public Number evaluate(Map variables) {
        return this;
    }

    public Expression call(List list, Map<String,Expression> variables) {
        System.out.println("can't use number as function name");
        return List.nil;
    }

    public Number add(Number other) {
        return new Number(value + other.value);
    }

    public Number subtract(Number other) {
        return new Number(value - other.value);
    }

    public Number mult(Number other) {
        return new Number(value * other.value);
    }

    public Number div(Number other) {
        if (other.value == 0) {
            System.out.println("divide by zero");
            return new Number(-9999999);
        }
        return new Number(value / other.value);
    }

    public Expression equal(Number other) {
        if (value.equals(other.value))
            return t;
        return List.nil;
    }

    public Expression notEqual(Number other) {
        if (value != other.value)
            return t;
        return List.nil;
    }

    public Expression lessThan(Number other) {
        if (value < other.value)
            return t;
        return List.nil;
    }

    public Expression greaterThan(Number other) {
        if (value > other.value)
            return t;
        return List.nil;
    }

    public Expression lessThanOrEqual(Number other) {
        if (value <= other.value)
            return t;
        return List.nil;
    }

    public Expression greaterThanOrEqual(Number other) {
        if (value >= other.value)
            return t;
        return List.nil;
    }

    public String toString() {
        return value.toString();
    }
}