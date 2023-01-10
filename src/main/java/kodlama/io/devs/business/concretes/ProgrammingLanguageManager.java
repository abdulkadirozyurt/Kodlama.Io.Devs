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
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdProgrammingLanguageResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

	private ProgrammingLanguageRepository programmingLanguageRepository;
	private TechnologyRepository technologyRepository;

	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository,
			TechnologyRepository technologyRepository) {

		this.programmingLanguageRepository = programmingLanguageRepository;
		this.technologyRepository=technologyRepository;
	}

	@Override
	public List<GetAllProgrammingLanguageResponse> getAll() {

		List<ProgrammingLanguage> languages = programmingLanguageRepository.findAll();
		List<GetAllProgrammingLanguageResponse> languageResponse = new ArrayList<GetAllProgrammingLanguageResponse>();
		for (ProgrammingLanguage language : languages) {
			GetAllProgrammingLanguageResponse responseItem = new GetAllProgrammingLanguageResponse();
			responseItem.setId(language.getProgrammingLanguageId());
			responseItem.setName(language.getProgrammingLanguageName());
			languageResponse.add(responseItem);
		}
		return languageResponse;
	}

	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {

		if (isNameExist(createProgrammingLanguageRequest.getName())) {
			throw new Exception("Dil zaten mevcut.");
		}
		else {
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setProgrammingLanguageName(createProgrammingLanguageRequest.getName());
		this.programmingLanguageRepository.save(programmingLanguage);
		}
	}

	@Override
	public void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
		
		if (isNameExist(updateProgrammingLanguageRequest.getName())) {
			throw new Exception("Girilen bilgiler zaten mevcut");
		}
		else if(!updateProgrammingLanguageRequest.getName().isEmpty()){
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).get();
			programmingLanguage.setProgrammingLanguageName(updateProgrammingLanguageRequest.getName());
			programmingLanguageRepository.save(programmingLanguage);
		}
	
	}

	@Override
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception {
		if (isIdExist(deleteProgrammingLanguageRequest.getId())) {
			programmingLanguageRepository.deleteById(deleteProgrammingLanguageRequest.getId());
		}
		else {
			throw new Exception("Dil mevcut değil.");
		}
	}

	@Override
	public GetByIdProgrammingLanguageResponse getById(int id) {

		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id).get();
		GetByIdProgrammingLanguageResponse response = new GetByIdProgrammingLanguageResponse();
		response.setName(programmingLanguage.getProgrammingLanguageName());

		return response;
	}
	
	
	public boolean isNameExist(String name) {
		for (GetAllProgrammingLanguageResponse languageResponse : this.getAll()) {
			if (name.equals(languageResponse.getName())) {
				return true;
			}
			
		}
		return false;		
	}
	
	private boolean isIdExist(int id) {

		for (GetAllProgrammingLanguageResponse languageResponse : this.getAll()) {

			if (languageResponse.getId()==id) {
				return true;
			}
				
		}
		return false;
	}

}
