package Models.Input.UserInputs;

public class ConfirmAccountModel
{
    public String phoneNumber;
    public String email;
    public String verificationCode;
    public ConfirmAccountModel(String verificationCode, String email, String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }
}
