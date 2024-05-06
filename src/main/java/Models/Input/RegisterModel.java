package Models.Input;

public class RegisterModel {
    public String Email;
    public String Password;
    public String ConfirmPassword;
    public String Name;
    public  RegisterModel(String email,String password,String confirmPassword,String Name){
        this.Email = email;
        this.Password = password;
        this.ConfirmPassword = confirmPassword;
        this.Name = Name;
    }
}
