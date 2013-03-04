import java.util.Map;

abstract class Function implements Expression {
    public String name;
    public int arity;

    public Function(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }

    public Expression evaluate(Map variables) {
        return this;
    }

    public boolean checkArity(List args, Map variables) {
        if (arity == -1)
            return true;

        if (args == null) {
            if (arity == 0)
                return true;
            System.out.println(name + " given 0 args, but needs " + arity + " args");
            return false;
        }

        if (args.length() != arity) {
            System.out.print(name + " given " + args.length());
            System.out.println(" args, but needs " + arity + " args");
            return false;
        }
        return true;
    }

    // recursive cond'er
    public static Expression chain_cond(List list, Map<String,Expression> variables) {
        List car = (List) list.getCar();
        List cdr = list.getCdr();

        if (car.getCar().evaluate(variables) != List.nil) {
            // reuse this declared variable, we don't care about
            //   the remaining conditions anymore
            cdr = car.getCdr().evaluate_elements(variables);
            if (cdr == List.nil)
                return Number.t;
            while (cdr.getCdr() != List.nil)
                cdr = cdr.getCdr();
            return cdr.getCar();
        }
        else {
            if (cdr == List.nil)
                return List.nil;
            return chain_cond(cdr, variables);
        }
    }

    public String toString() {
        return name;
    }
}