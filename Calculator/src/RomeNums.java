public enum RomeNums {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    private int value;
    private RomeNums(int num){
        this.value = num;
    }

    public int getValue() {
        return value;
    }
}
