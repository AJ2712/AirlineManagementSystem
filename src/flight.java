public class flight {
    private int flightId;
    private String name;
    private int seats;

    public flight(int flightId, String name, int seats) {
        super();
        this.flightId = flightId;
        this.name = name;
        this.seats = seats;
    }

    public int getFlightid() {
        return flightId;
    }

    public void setFlightid(int flightid) {
        this.flightId = flightid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
