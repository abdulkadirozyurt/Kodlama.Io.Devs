package kodlama.io.devs.business.abstracts;

import java.util.List;

import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguageResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;

public interface ProgrammingLanguageService {

	List<GetAllProgrammingLanguageResponse> getAll();

	void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception;

	void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest);

	void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest);

	GetByIdProgrammingLanguageResponse getById(int id);

}
