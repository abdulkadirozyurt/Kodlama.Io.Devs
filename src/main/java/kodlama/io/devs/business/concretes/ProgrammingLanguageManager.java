package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository programmingLanguageRepository;
    

    @Autowired
    public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository,
                                      TechnologyRepository technologyRepository) {

        this.programmingLanguageRepository = programmingLanguageRepository;

    }

    @Override
    public List<GetAllProgrammingLanguageResponse> getAll() {

        List<ProgrammingLanguage> languages = programmingLanguageRepository.findAll();
        List<GetAllProgrammingLanguageResponse> plResponse = new ArrayList<GetAllProgrammingLanguageResponse>();
        for (ProgrammingLanguage language : languages) {
            GetAllProgrammingLanguageResponse responseItem = new GetAllProgrammingLanguageResponse();
            responseItem.setId(language.getId());
            responseItem.setName(language.getName());
            plResponse.add(responseItem);
        }
        return plResponse;
    }

    @Override
    public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName(createProgrammingLanguageRequest.getName());
        this.programmingLanguageRepository.save(programmingLanguage);

    }

    @Override
    public void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {

        ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).get();
        programmingLanguage.setName(updateProgrammingLanguageRequest.getName());

    }

    @Override
    public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {

        programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
    }

    @Override
    public GetByIdProgrammingLanguageResponse getById(int id) {

        ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).get();
        GetByIdProgrammingLanguageResponse response = new GetByIdProgrammingLanguageResponse();
        response.setName(programmingLanguage.getName());

        return response;
    }

}
