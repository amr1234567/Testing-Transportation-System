package Models.Response;

public class ResponseModel<T> {
    public String message;
    public int statusCode;
    public T body;
    public ResponseModel(String Message,int StatusCode,T body){
        this.body=body;
        this.message =Message;
        this.statusCode =StatusCode;
    }
    public ResponseModel(){

    }
}
