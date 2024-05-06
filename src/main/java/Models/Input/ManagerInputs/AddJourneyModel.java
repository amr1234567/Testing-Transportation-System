package Models.Input.ManagerInputs;

public class AddJourneyModel {
    public String destinationId;
    public String startBusStopId;
    public String leavingTime;
    public String arrivalTime;
    public String busId;
    public int ticketPrice;
    public AddJourneyModel( String destinationId, String startBusStopId,String leavingTime,String arrivalTime,String busId,int ticketPrice){

        this.destinationId = destinationId;
        this.startBusStopId = startBusStopId;
        this.leavingTime = leavingTime;
        this.arrivalTime = arrivalTime;
        this.busId = busId;
        this.ticketPrice = ticketPrice;
    }
}
