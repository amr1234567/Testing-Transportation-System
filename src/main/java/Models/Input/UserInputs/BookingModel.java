package Models.Input.UserInputs;

public class BookingModel {
    public String seatId;
    public String journeyId;

    public BookingModel(String seatId,String journeyId){

        this.seatId = seatId;
        this.journeyId = journeyId;
    }
}
