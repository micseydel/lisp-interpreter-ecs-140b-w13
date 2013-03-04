import java.util.Map;

// Abstract class for handling Lists, Atoms and in the future, Functions.
//   List cars eventually reduce to Atoms
public interface Expression {
    public Expression evaluate(Map<String,Expression> variables);
    public Expression call(List list, Map<String,Expression> variables);
}
