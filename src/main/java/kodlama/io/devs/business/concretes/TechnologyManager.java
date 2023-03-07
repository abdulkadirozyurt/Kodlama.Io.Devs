package kodlama.io.devs.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.core.utilities.mappers.ModelMapperService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.GetAllTechnologiesResponse;
import kodlama.io.devs.business.responses.GetByIdTechnologyResponse;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.Technology;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnologyManager implements TechnologyService {

	private TechnologyRepository technologyRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllTechnologiesResponse> getAll() {
		List<Technology> technologies = this.technologyRepository.findAll();
		List<GetAllTechnologiesResponse> technologiesResponse = technologies.stream().map(
				technology -> this.modelMapperService.forResponse().map(technology, GetAllTechnologiesResponse.class))
				.toList();

		return technologiesResponse;
	}

	@Override
	public GetByIdTechnologyResponse getById(int id) {
		Technology technology = this.technologyRepository.findById(id).orElseThrow();
		GetByIdTechnologyResponse response = this.modelMapperService.forResponse().map(technology, GetByIdTechnologyResponse.class);
		return response;
	}

	@Override
	public void add(CreateTechnologyRequest createTechnologyRequest) {
		Technology technology = this.modelMapperService.forRequest().map(createTechnologyRequest, Technology.class);
		this.technologyRepository.save(technology);

	}

	@Override
	public void delete(DeleteTechnologyRequest DeleteTechnologyRequest) {
		Technology technology = this.modelMapperService.forRequest().map(DeleteTechnologyRequest, Technology.class);
		this.technologyRepository.delete(technology);

	}

	@Override
	public void update(UpdateTechnologyRequest updateTechnologyRequest) {
		Technology technology = this.modelMapperService.forRequest().map(updateTechnologyRequest, Technology.class);
		this.technologyRepository.save(technology);

	}

}
