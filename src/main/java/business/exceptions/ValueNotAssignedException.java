package business.exceptions;

public class ValueNotAssignedException extends Exception {

    public ValueNotAssignedException(String value, String setter, String method) {
        super(String.format("Value %s not assigned. Please use %s before %s", value, setter, method));
    }

}
