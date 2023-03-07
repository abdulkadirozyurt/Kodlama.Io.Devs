package kodlama.io.devs.business.rules;

import org.springframework.stereotype.Service;

import kodlama.io.devs.business.core.utilities.exceptions.BusinessException;
import kodlama.io.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgrammingLanguageBusinessRules {
	private ProgrammingLanguageRepository programmingLanguageRepository;
	
	public void checkIfProgrammingLanguageNameExists(String name) {
		if (this.programmingLanguageRepository.existsByName(name)) {
			throw new BusinessException("Programming language name already exists");
		}
	}
	
	
	
}
