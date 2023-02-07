package projeto.spring.gof.service;

import projeto.spring.gof.model.Aluno;

public interface AlunoService {

    Iterable<Aluno> buscarTodos();

    Aluno buscarPorMat(Long matricula);

    void inserir(Aluno aluno);

    void atualizar(Long matricula, Aluno aluno);

    void deletar(Long matricula);
}
