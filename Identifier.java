import java.util.Map;

class Identifier implements Atom {
    private final String string;
    public Number value;

    Identifier(String string) {
        this.string = string;
        value = null;
    }

    public Expression evaluate(Map<String,Expression> variables) {
        // later, this will be changed to allow getting variables
        //   just gets built-in functions right now
        if (variables.containsKey(string))
            return variables.get(string);
        System.out.println("\"" + string + "\" is not bound as a parameter");
        return List.nil;
    }

    public Expression call(List list, Map<String,Expression> variables) {
        if (variables.containsKey(string))
            return evaluate(variables).call(list, variables);
        System.out.println("\"" + string + "\" is not bound as a function");
        return List.nil;
    }

    public String toString() {
        return string;
    }
}