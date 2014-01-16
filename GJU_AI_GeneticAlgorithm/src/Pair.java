public class Pair<First, Second> {
    // members
    private First first;
    private Second second;

    public Pair() {}
    /**
    * @param first
    * @param second
    */
   public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    /**
    * @param first
    */
   public void setFirst(First first) {
        this.first = first;
    }

    /**
    * @param second
    */
   public void setSecond(Second second) {
        this.second = second;
    }

    /**
    * @return
    */
   public First getFirst() {
        return first;
    }

    /**
    * @return
    */
   public Second getSecond() {
        return second;
    }

    /**
    * @param first
    * @param second
    */
   public void set(First first, Second second) {
        setFirst(first);
        setSecond(second);
    }
}