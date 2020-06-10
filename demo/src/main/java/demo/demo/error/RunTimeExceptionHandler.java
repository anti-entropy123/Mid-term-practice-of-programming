package demo.demo.error;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import demo.demo.error.exception.AlreadyHasOpinion;
import demo.demo.error.exception.BadPassword;
import demo.demo.error.exception.BalanceNotEnough;
import demo.demo.error.exception.EarlyThanCurrent;
import demo.demo.error.exception.TimeCoincidence;


@ControllerAdvice
public class RunTimeExceptionHandler{
    
    @ExceptionHandler(value= BalanceNotEnough.class)
    public String balanceNotEnough(BalanceNotEnough e){
        return "申请失败, 原因: " + e.getMessage();
    }

    @ExceptionHandler(value= TimeCoincidence.class)
    public String timeCoincidence(TimeCoincidence e){
        return "申请失败, 原因: " + e.getMessage();
    }

    @ExceptionHandler(value= EarlyThanCurrent.class)
    public String earlyThanCurrent(EarlyThanCurrent e){
        return "申请失败, 原因: " + e.getMessage();
    }
    
    @ExceptionHandler(value= IllegalStateException.class)
    public String errorExcelFormat(IllegalStateException e) {
        return "上传失败, 原因: Excel文件格式不正确";
    }
    
    @ExceptionHandler(value= BadPassword.class)
    public String errorPassword(BadPassword e) {
        return "用户ID或密码错误";
    }

    @ExceptionHandler(value= AlreadyHasOpinion.class)
    public String hasOpinion(AlreadyHasOpinion e){
        return "审批失败, 原因: " + e.getMessage();
    }

    @ExceptionHandler(value= RuntimeException.class)
    public String unknownError(RuntimeException e){
        return "服务器错误, 原因: " + e.getMessage();
    }
}