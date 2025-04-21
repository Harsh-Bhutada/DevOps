package com.example.calculator.controller;

import com.example.calculator.model.Calculation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculation", new Calculation());
        return "index";
    }

    @PostMapping("/calculate")
    @ResponseBody
    public Calculation calculate(@RequestBody Calculation calculation) {
        double result = switch (calculation.getOperation()) {
            case "+" -> calculation.getNum1() + calculation.getNum2();
            case "-" -> calculation.getNum1() - calculation.getNum2();
            case "*" -> calculation.getNum1() * calculation.getNum2();
            case "/" -> calculation.getNum1() / calculation.getNum2();
            default -> throw new IllegalArgumentException("Invalid operation");
        };
        calculation.setResult(result);
        return calculation;
    }
}