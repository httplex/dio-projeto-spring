package projeto.spring.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.spring.gof.model.Aluno;
import projeto.spring.gof.model.AlunoRepository;
import projeto.spring.gof.model.Endereco;
import projeto.spring.gof.model.EnderecoRepository;
import projeto.spring.gof.service.AlunoService;
import projeto.spring.gof.service.ViaCepService;

import java.util.Optional;

@Service
/*
@Service: Esta anotação é usada em uma classe. O @Service marca uma classe Java
que executa algum serviço, como executar lógica de negócios, executar cálculos e
chamar APIs externas. Esta anotação é uma forma especializada da anotação @ Component
destinada a ser usada na camada de serviço.
 */
public class AlunoServiceImpl implements AlunoService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired /* @Autowired: Serve para marcar o ponto de injeção na sua classe.
                  Você pode colocar ela sobre atributos ou sobre o seu construtor com argumentos.  */
    private AlunoRepository alunoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Aluno> buscarTodos() {
        // Buscar todos os Alunos.
        return alunoRepository.findAll();
    }

    @Override
    public Aluno buscarPorMat(Long matricula) {
        // Buscar alunos pela matrícula.
        Optional<Aluno> aluno = alunoRepository.findById(matricula);
        return aluno.get();
    }

    @Override
    public void inserir(Aluno aluno) {
        salvarAlunoComCep(aluno);
    }

    @Override
    public void atualizar(Long matricula, Aluno aluno) {
        // Buscar aluno pela matrícula, caso exista:
        Optional<Aluno> alunoBd = alunoRepository.findById(matricula);
        if (alunoBd.isPresent()) {
            salvarAlunoComCep(aluno);
        }
}

    @Override
    public void deletar(Long matricula) {
        // Deletar aluno pela Matricula.
        alunoRepository.deleteById(matricula);
    }

    private void salvarAlunoComCep(Aluno aluno) {
        // Verificar se o endereço do aluno já existe (pelo CEP).
        String cep = aluno.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        aluno.setEndereco(endereco);
        // Inserir aluno, vinculando o endereço (novo ou existente).
        alunoRepository.save(aluno);
    }
}

