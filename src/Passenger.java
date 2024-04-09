public class Passenger {
    private long Aadhar;
    private String firstName;
    private String lastName;
    private String dob;
    private String sex;
    private long mobileNumber;
    private int flightId;

    public Passenger(long aadhar, String firstName, String lastName, String dob, String sex, long mobileNumber, int flightId) {
        Aadhar = aadhar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.sex = sex;
        this.mobileNumber = mobileNumber;
        this.flightId = flightId;
    }

    public long getAadhar() {
        return Aadhar;
    }

    public void setAadhar(long aadhar) {
        Aadhar = aadhar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
