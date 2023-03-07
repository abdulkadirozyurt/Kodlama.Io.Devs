package kodlama.io.devs.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/programmingLanguages")
@AllArgsConstructor
public class ProgrammingLanguagesController {

    private ProgrammingLanguageService programmingLanguagesService;
   
    
    @GetMapping()
    public List<GetAllProgrammingLanguagesResponse> getAll() {

        return this.programmingLanguagesService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdProgrammingLanguageResponse getByID(@PathVariable int id) {
        return this.programmingLanguagesService.getById(id);
    }

    @PostMapping()
    public void add(@RequestBody @Valid CreateProgrammingLanguageRequest createProgrammingLanguageRequest)  {
    	this.programmingLanguagesService.add(createProgrammingLanguageRequest);
    }



    @DeleteMapping()
    public void deleteById(@RequestBody DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest)  {

    	this.programmingLanguagesService.delete(deleteProgrammingLanguageRequest);
    }

    @PutMapping()
    public void update(@RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {

        this.programmingLanguagesService.update(updateProgrammingLanguageRequest);

    }

}
