package com.example.crud_car_with_thymeleaf.controller;

import com.example.crud_car_with_thymeleaf.common.ICRUDService;
import com.example.crud_car_with_thymeleaf.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICRUDService<Car> carService;

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("display");
        ArrayList<Car> cars = carService.findAll();
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCarForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute("car") Car car, RedirectAttributes redirectAttributes) {
        carService.create(car);
        redirectAttributes.addFlashAttribute("message", "Create successfully!");
        return "redirect:http://localhost:8088/car/create";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateCarForm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update");
        Car car = carService.findById(id);
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateCar(@ModelAttribute Car car, RedirectAttributes redirectAttributes) {
        carService.update(car);
        redirectAttributes.addFlashAttribute("car", car);
        redirectAttributes.addFlashAttribute("message", "Update successfully!");
        return "redirect:http://localhost:8088/car/update/" + car.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        carService.deleteById(id);
        ArrayList<Car> cars = carService.findAll();
        redirectAttributes.addFlashAttribute("cars", cars);
        redirectAttributes.addFlashAttribute("message", "Delete successfully!");
        return "redirect:/car";
    }

    @PostMapping("/search")
    public ModelAndView findAllByName(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("display");
        ArrayList<Car> cars = carService.findAllByName(name);
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("search", name);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Car car = carService.findById(id);
        modelAndView.addObject("car", car);
        return modelAndView;
    }
}
