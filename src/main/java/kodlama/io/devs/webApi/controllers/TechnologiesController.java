package kodlama.io.devs.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdTechnologyResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/technologies")
@AllArgsConstructor

public class TechnologiesController {   

    private TechnologyService technologyService;

    
    @GetMapping("/getall")
    public List<GetAllTechnologiesResponse> getAll() {

        return technologyService.getAll();
    }

    @GetMapping("/getById")
    public GetByIdTechnologyResponse getById(int id) {

        return technologyService.getById(id);

    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateTechnologyRequest createTechnologyRequest)  {

        this.technologyService.add(createTechnologyRequest);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestBody DeleteTechnologyRequest deleteTechnologyRequest)  {

        technologyService.delete(deleteTechnologyRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateTechnologyRequest updateTechnologyRequest) {

        technologyService.update(updateTechnologyRequest);

    }
}
