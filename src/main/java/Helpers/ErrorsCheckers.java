package Helpers;

import Models.Response.SingleErrorModel;

public class ErrorsCheckers {
    public static boolean CheckOnBodyExist(SingleErrorModel[] model){
        return  model != null && model.length > 0;
    }
    public static boolean CheckOnErrorsFields(String[] errors,SingleErrorModel[] model){
        boolean hasErrors = false;
        if (CheckOnBodyExist(model)){
            for (String s : errors) {
                for (SingleErrorModel errorModel : model) {
                    if (s.equalsIgnoreCase(errorModel.fieldName)) {
                        hasErrors = true;
                        break;
                    }
                    hasErrors =false;
                }
            }
        }
        return  hasErrors;
    }
}
