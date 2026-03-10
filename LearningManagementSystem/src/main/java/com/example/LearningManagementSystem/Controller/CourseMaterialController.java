package com.example.LearningManagementSystem.Controller;

import com.example.LearningManagementSystem.Entity.CourseMaterial;
import com.example.LearningManagementSystem.Service.CourseMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CourseMaterials")
public class CourseMaterialController {

    private CourseMaterialService courseMaterialService;
    public CourseMaterialController(CourseMaterialService courseMaterialService){
        this.courseMaterialService=courseMaterialService;
    }

    @GetMapping
    public List<CourseMaterial> getAllMaterials(){
        return courseMaterialService.getAllMaterials();
    }

    @PostMapping
    public ResponseEntity<CourseMaterial> addMaterial(@RequestBody CourseMaterial material,@RequestParam String course_name){
        CourseMaterial addedMaterial= courseMaterialService.addMaterial(material,course_name);
        return new ResponseEntity<>(addedMaterial,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CourseMaterial>> getCourseMaterialById(@PathVariable long id){
        List<CourseMaterial> courseMaterials=courseMaterialService.getMaterialById(id);
        return new ResponseEntity<>(courseMaterials,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseMaterial(@PathVariable int id){
        courseMaterialService.deleteMaterial(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Course material :"+id+" successfully deleted");
    }

    @GetMapping("/getCount")
    public ResponseEntity<List<?>> getCourseMaterialCount(){
       return courseMaterialService.getMaterialCount();
    }

}
