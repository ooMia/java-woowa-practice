package racingcar.car;

class RacingCarImpl implements RacingCar {

    private final String name;
    private int position;

    /**
     * 모든 Car 객체의 생성은 팩토리의 검증 절차를 거쳐야 한다.
     *
     * @see CarFactory
     */
    RacingCarImpl(String name) {
        this.name = name;
    }

    @Override
    public void move(int distance) {
        try {
            // allow negative distance
            position = Math.addExact(distance, position);
        } catch (ArithmeticException e) {
            // throws on addition overflow
            throw new IllegalStateException(e);
        }
        // prevent negative position
        position = Math.max(0, position);
    }

    @Override
    public RacingCar.Status getStatus() {
        return new Status(name, position);
    }

    private record Status(String name, int position) implements RacingCar.Status {
        @Override
        public int compareTo(RacingCar.Status o) {
            return Integer.compare(this.position, o.position());
        }
    }
}
