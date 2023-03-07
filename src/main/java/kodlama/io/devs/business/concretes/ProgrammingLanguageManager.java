package kodlama.io.devs.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.business.core.utilities.mappers.ModelMapperService;
import kodlama.io.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.DeleteProgrammingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.business.responses.GetAllProgrammingLanguagesResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;
import kodlama.io.devs.business.rules.ProgrammingLanguageBusinessRules;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

	private ProgrammingLanguageRepository programmingLanguageRepository;	
	private ModelMapperService modelMapperService;
	private ProgrammingLanguageBusinessRules programmingLanguageBusinessRules; 

	@Override
	public List<GetAllProgrammingLanguagesResponse> getAll() {
		List<ProgrammingLanguage> programmingLanguages = this.programmingLanguageRepository.findAll();
		List<GetAllProgrammingLanguagesResponse> programmingLanguagesResponse = programmingLanguages.stream()
				.map(programmingLanguage -> this.modelMapperService.forResponse().map(programmingLanguage,
						GetAllProgrammingLanguagesResponse.class)).collect(Collectors.toList());
				

		return programmingLanguagesResponse;
	}

	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) {
		this.programmingLanguageBusinessRules.checkIfProgrammingLanguageNameExists(createProgrammingLanguageRequest.getName());
		ProgrammingLanguage programmingLanguage = this.modelMapperService.forRequest()
				.map(createProgrammingLanguageRequest, ProgrammingLanguage.class);
		this.programmingLanguageRepository.save(programmingLanguage);
	}

	@Override
	public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = this.modelMapperService.forRequest()
				.map(updateProgrammingLanguageRequest, ProgrammingLanguage.class);
		this.programmingLanguageRepository.save(programmingLanguage);
	}

	@Override
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) {
		ProgrammingLanguage programmingLanguage = this.modelMapperService.forRequest()
				.map(deleteProgrammingLanguageRequest, ProgrammingLanguage.class);
		this.programmingLanguageRepository.delete(programmingLanguage);
	}

	@Override
	public GetByIdProgrammingLanguageResponse getById(int id) {

		ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository.findById(id).orElseThrow();
		GetByIdProgrammingLanguageResponse getByIdProgrammingLanguageResponse = this.modelMapperService.forResponse()
				.map(programmingLanguage, GetByIdProgrammingLanguageResponse.class);
		return getByIdProgrammingLanguageResponse;
	}

}
