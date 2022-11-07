package kodlama.io.devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;

@RestController
@RequestMapping("/api/programmingLanguages")
public class ProgrammingLanguagesController {

    private ProgrammingLanguageService programmingLanguageService;


    public ProgrammingLanguagesController() {
        super();
    }


    @Autowired
    public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {

        this.programmingLanguageService = programmingLanguageService;
    }

    @GetMapping("/getall")
    public List<GetAllProgrammingLanguageResponse> getAll() {

        return programmingLanguageService.getAll();
    }

    @GetMapping("/getById")
    public GetByIdProgrammingLanguageResponse getByID(int id) {
        return programmingLanguageService.getById(id);
    }

    @PostMapping("/add")
    public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
        programmingLanguageService.add(createProgrammingLanguageRequest);
    }



    @DeleteMapping("/delete")
    public void deleteById(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {

        programmingLanguageService.delete(deleteProgrammingLanguageRequest);
    }

    @PutMapping("/update")
    public void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {

        programmingLanguageService.update(id, updateProgrammingLanguageRequest);

    }

}
