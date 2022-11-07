package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdTechnologyResponse;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {

    private TechnologyRepository technologyRepository;

    public TechnologyManager() {
        super();
    }

    public TechnologyManager(TechnologyRepository technologyRepository) {
        super();
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<GetAllTechnologiesResponse> getAll() {
        List<Technology> technologies = technologyRepository.findAll();
        List<GetAllTechnologiesResponse> technologyResponse = new ArrayList<GetAllTechnologiesResponse>();

        for (Technology technology : technologies) {
            GetAllTechnologiesResponse responseItem = new GetAllTechnologiesResponse();
            responseItem.setId(technology.getId());
            responseItem.setName(technology.getName());
        }
        return technologyResponse;
    }

    @Override
    public GetByIdTechnologyResponse getById(int id) {

        Technology technology = technologyRepository.findById(id).get();
        GetByIdTechnologyResponse response = new GetByIdTechnologyResponse();
        response.setName(technology.getName());
        return response;
    }

    @Override
    public void add(CreateTechnologyRequest createTechnologyRequest) {
        Technology technology = new Technology();
        technology.setName(createTechnologyRequest.getName());
        this.technologyRepository.save(technology);

    }

    @Override
    public void delete(DeleteTechnologyRequest deleteTechnologyRequest) {
        this.technologyRepository.deleteById(deleteTechnologyRequest.getId());
    }

    @Override
    public void update(int id, UpdateTechnologyRequest updateTechnologyRequest) {

        if (!updateTechnologyRequest.getName().isEmpty()) {
            Technology technology= technologyRepository.findById(id).get();
            technology.setName(updateTechnologyRequest.getName());
            technologyRepository.save(technology);
        }


    }

}
