package kodlama.io.devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {

    public TechnologiesController() {
        super();
    }

    private TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {

        this.technologyService = technologyService;
    }

    @GetMapping("/getall")
    public List<GetAllTechnologiesResponse> getAll() {

        return technologyService.getAll();
    }

    @GetMapping("/getById")
    public GetByIdTechnologyResponse getById(int id) {

        return technologyService.getById(id);

    }

    @PostMapping("/add")
    public void add( CreateTechnologyRequest createTechnologyRequest) throws Exception {

        this.technologyService.add(createTechnologyRequest);
    }

    @DeleteMapping("/delete")
    public void deleteById( DeleteTechnologyRequest deleteTechnologyRequest) throws Exception {

        technologyService.delete(deleteTechnologyRequest);
    }

    @PutMapping("/update")
    public void update( int id, UpdateTechnologyRequest updateTechnologyRequest) throws Exception {

        technologyService.update(id, updateTechnologyRequest);

    }
}
