package org.matrix;

public class MatrixElementImpl<Value> implements MatrixElement<Value> {
    private Value value;
    public MatrixElementImpl(Value value) {
        this.value = value;
    }
    @Override
    public MatrixElement<Value> add(MatrixElement<Value> b) {
        Value val = null;
        if (value instanceof Integer && b.getValue() instanceof Integer) {
            val = (Value)Integer.valueOf(((Integer)value).intValue() + ((Integer)b.getValue()).intValue());
        } else if (value instanceof Double && b.getValue() instanceof Double) {
            val = (Value)Double.valueOf(((Double)value).doubleValue() + ((Double)b.getValue()).doubleValue());
        } else if (value instanceof String && b.getValue() instanceof String) {
            val = (Value)String.valueOf(((String)value) + ((String)b.getValue()));
        }
        if (val == null) { return null; }
        return new MatrixElementImpl<Value>(val);
    }

    @Override
    public String getString() {
        if (value == null) { return "null"; }
        try {
            return value.toString();
        } catch (Exception e) {
            return "None";
        }
    }

    public Value getValue() {
        return value;
    }
}
