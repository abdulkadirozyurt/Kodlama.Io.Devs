package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdTechnologyResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgrammingLanguage;
import kodlama.io.devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {

	private TechnologyRepository technologyRepository;

	private ProgrammingLanguageRepository programmingLanguageRepository;

	public TechnologyManager() {
		super();
	}

	@Autowired
	public TechnologyManager(TechnologyRepository technologyRepository,
			ProgrammingLanguageRepository programmingLanguageRepository) {

		super();
		this.technologyRepository = technologyRepository;
		this.programmingLanguageRepository = programmingLanguageRepository;
	}

	@Override
	public List<GetAllTechnologiesResponse> getAll() {

		List<Technology> technologies = technologyRepository.findAll(); 
		List<GetAllTechnologiesResponse> technologyResponse = new ArrayList<GetAllTechnologiesResponse>(); // bizden return edilmesi istenen farklı bir tür olduğu için o türde yeni bir liste oluşturduk

		for (Technology technology : technologies) { // list <Technology> listesini dolaşmaya başladık
			GetAllTechnologiesResponse responseItem = new GetAllTechnologiesResponse(); // dolaşırken her bir elemanda, istenen türde bir responseItem oluşturduk, çünkü bunu döneceğiz.
			responseItem.setId(technology.getId()); // bu responseItem'in fieldlarını doldurmuş oluruz
			responseItem.setName(technology.getName());
			technologyResponse.add(responseItem); // en sonunda birinci listenin her bir elemanını yeni listeye her
												// dolaşmada eklemiş olduk.
		}

		return technologyResponse; // burda da yeni istenen listeyi döndürdük.

	}

	@Override
	public GetByIdTechnologyResponse getById(int id) {
				
		Technology technology = technologyRepository.findById(id).get();
		GetByIdTechnologyResponse response = new GetByIdTechnologyResponse();
		response.setName(technology.getName());
		return response;
	}

	@Override
	public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
		Technology technology = new Technology();

		if (createTechnologyRequest.getName().isEmpty()) {
			throw new Exception("Teknoloji ismi bos gecilemez");
		} else if (isNameExist(createTechnologyRequest.getName())) {
			throw new Exception("Teknoloji mevcut.");
		} else {
			technology.setName(createTechnologyRequest.getName());
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.getReferenceById(createTechnologyRequest.getLanguageId());
			technology.setProgrammingLanguage(programmingLanguage);
			technologyRepository.save(technology);
			throw new Exception("Teknoloji, ilgili programlama diline eklendi.");
		}

	}

	@Override
	public void delete(DeleteTechnologyRequest deleteTechnologyRequest) throws Exception {
		
		if (isIdExist(deleteTechnologyRequest.getId())) {
			this.technologyRepository.deleteById(deleteTechnologyRequest.getId());
		}
		else {
			throw new Exception("Teknoloji mevcut değil.");
		}
		
		
	}

	@Override
	public void update(int id, UpdateTechnologyRequest updateTechnologyRequest) throws Exception {

		if (isNameExist(updateTechnologyRequest.getName())) {
			throw new Exception("Girilen Bilgiler zaten Mevcut");
		}
		
		
		else if (!updateTechnologyRequest.getName().isEmpty()) {
			Technology technology = technologyRepository.findById(id).get();
			technology.setName(updateTechnologyRequest.getName());
			technologyRepository.save(technology);
		}
		
		
				
	}

	
	

	private boolean isNameExist(String name) {

		for (GetAllTechnologiesResponse getTechnologiesResponse : this.getAll()) {

			if (name.equals(getTechnologiesResponse.getName())) {
				return true;
			}

		}
		return false;

	}

	private boolean isIdExist(int id) {

		for (GetAllTechnologiesResponse getTechnologiesResponse : this.getAll()) {

			if (getTechnologiesResponse.getId()==id) {
				return true;
			}
				
		}
		return false;
	}

	/*
	 * private boolean isNameEmpty(String name) {
	 * 
	 * if (name.isEmpty()) return true;
	 * 
	 * return false; }
	 */

}

/*
 * 
 * List<Technology> technologies = technologyRepository.findAll();
 * List<GetAllTechnologiesResponse> technologyResponse = new
 * ArrayList<GetAllTechnologiesResponse>();
 * 
 * for (Technology technology : technologies) { GetAllTechnologiesResponse
 * responseItem = new GetAllTechnologiesResponse();
 * responseItem.setId(technology.getId());
 * responseItem.setName(technology.getName()); } return technologyResponse;
 * 
 * 
 */
