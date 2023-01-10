package kodlama.io.devs.business.abstracts;

import java.util.List;

import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdTechnologyResponse;

public interface TechnologyService {

    List<GetAllTechnologiesResponse> getAll();

    GetByIdTechnologyResponse getById(int id);

    void add(CreateTechnologyRequest createTechnologyRequest) throws Exception;

    void delete(DeleteTechnologyRequest DeleteTechnologyRequest) throws Exception;

    void update(int id, UpdateTechnologyRequest updateTechnologyRequest) throws Exception;
    
    
    
    

}
