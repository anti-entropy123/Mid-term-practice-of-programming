package demo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.demo.entity.TestDO;
import demo.demo.service.TestService;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    String index(){
        return "hello world";
    }

    @GetMapping("/test/")
    public List<TestDO> getDo() {
        List<TestDO> result = new ArrayList<TestDO>();
        result.add(testService.getDo());
        return result;
    }
    
}