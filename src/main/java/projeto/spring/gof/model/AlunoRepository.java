package projeto.spring.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Anotação que serve para definir uma classe como pertencente à camada de persistência.
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
