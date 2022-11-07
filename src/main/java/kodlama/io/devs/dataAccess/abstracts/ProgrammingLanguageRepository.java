package kodlama.io.devs.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {

	/*
	 * List<ProgrammingLanguage> getAll();
	 *
	 * void add(ProgrammingLanguage programmingLanguage);
	 *
	 * void update(int id, ProgrammingLanguage programmingLanguage);
	 *
	 * void delete(int id);
	 *
	 * ProgrammingLanguage getById(int id);
	 */
}
