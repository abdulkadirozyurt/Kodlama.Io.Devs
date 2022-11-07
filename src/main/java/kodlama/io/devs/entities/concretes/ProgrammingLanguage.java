package kodlama.io.devs.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "programming_Languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pL_id")
    private int id;

    @Column(name = "pL_name")
    private String name;

//    @OneToMany(mappedBy = "technology")
//    private List<Technology> technologies;

}
