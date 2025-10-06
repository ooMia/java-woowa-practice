package oncall.model.in;

record WorkDate(int month, String weekDay) {
    WorkDate(int month, String weekDay) {
        this.month = month;
        this.weekDay = weekDay; // TODO: use enum
    }

    public static WorkDate of(String line) {
        var args = line.split("\\s*,\\s*");
        return new WorkDate(Integer.parseInt(args[0]), args[1]);
    }
}
