package demo.demo.error.exception;

public class BalanceNotEnough extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -1644389661639394127L;
    
    public BalanceNotEnough(){ 
        super();
    };
    public BalanceNotEnough(String message){
        super(message);
    }
}