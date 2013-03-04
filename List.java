import java.util.Map;

public class List implements Expression {
    private final Expression car;
    private List cdr;
    // useful singleton
    public static final List nil = new List((Expression) null);

    static class AppendGivenNonListException extends Exception {
        public AppendGivenNonListException(String msg) {
            System.out.println("append given non-list");
        }
    }

    public List(Atom car) {
        this.car = car;
        this.cdr = null;
    }

    public List(Expression car) {
        this.car = car;
        this.cdr = null;
    }

    public List(Expression car, List cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public Expression getCar() {
        return car != null ? car : nil;
    }

    public List getCdr() {
        return cdr != null ? cdr : nil;
    }

    public Expression evaluate(Map<String,Expression> variables) {
        return (car != null) ? car.call(cdr, variables) : nil;
    }

    public List evaluate_elements(Map<String,Expression> variables) {
        if (car == null)
            return nil;
        return new List(car.evaluate(variables),
            cdr == null ? null : cdr.evaluate_elements(variables));
    }

    public Expression call(List list, Map<String,Expression> variables) {
        if (this == nil)
            System.out.println("null car in eval");
        else // if (car.evaluate(variables) == nil)
            System.out.println("bad cons'ed object as function/lambda");
        return nil;
    }

    // add to this List another List. this is how we make a list
    public void append(List list) {
        if (car == null) {
            System.out.println("You tried to append " + list.toString() + " to nil! BAD!");
            assert car != null;
        }
        if (cdr == null)
            cdr = list;
        else if (cdr != nil)
            cdr.append(list);
    }

    public void append(Atom atom) {
        append(new List(atom));
    }

    public static List chain_append(List list) throws AppendGivenNonListException {
        if (list == List.nil)
            return List.nil;

        Expression car = list.getCar();
        List cdr = list.getCdr();

        if (car instanceof List) {
            if (cdr == List.nil)
                return (List) car;
        }
        else
            throw new AppendGivenNonListException("");

        try {
            // re-using this declared variable *carefully*
            cdr = chain_append(cdr);
            if (car == nil)
                return cdr;
            if (cdr == nil)
                return (List) car;
            ((List) car).append(cdr);
            return (List) car;
        }
        catch (AppendGivenNonListException e) {
            return nil;
        }
    }

    public int length() {
        if (car == null)
            return 0;
        if (cdr == null)
            return 1;
        return 1 + cdr.length();
    }

    public String toString() { // this is O(n**2) currently
        // this is pretty much only called for prints on the car,
        //   which need an open paren. this function is overloaded
        //   to handle the common case of no open paren
        return toString("(");
    }

    private String toString(String start) {
        if (car == null) // nil!
            return "nil";

        // start is always "(" or ""
        String ret_val = start;
        if (car != null) {
            // the List to a string is always composed of its car
            //   and cdr turned to strings
            ret_val = ret_val + car.toString();
            // if (cdr != null)
            //     ret_val = ret_val + " " + cdr.toString("");

            // remove (use above)
            if (cdr != null) {
                String temp = cdr.toString("");
                assert !temp.equals("");
                ret_val = ret_val + " " + temp;
            }
        }
        if (cdr == null) {
            // a null cdr indicates a close paren, and
            //   does so exclusively. the car better be null too.
            assert car == null;
            ret_val = ret_val + ")";
        }
        return ret_val;
    }
}