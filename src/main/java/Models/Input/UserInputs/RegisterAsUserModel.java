package Models.Input.UserInputs;

import Models.Input.RegisterModel;

public class RegisterAsUserModel extends RegisterModel {
    public String PhoneNumber;
    public RegisterAsUserModel(String email, String password, String phoneNumber, String confirmPassword, String Name) {
        super(email, password, confirmPassword, Name);
        this.PhoneNumber =phoneNumber;
    }
}
