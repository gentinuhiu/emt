package emt.lab.web.rest;

import emt.lab.model.enumeration.CATEGORY;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:5173")
public class CategoryController {
    @GetMapping
    public CATEGORY[] getAllCategories(){
        return CATEGORY.values();
    }
}
