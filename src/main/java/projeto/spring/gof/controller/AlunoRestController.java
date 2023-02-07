package projeto.spring.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.spring.gof.model.Aluno;
import projeto.spring.gof.service.AlunoService;

@RestController /* A anotação @RestController permite definir um controller com características REST */
@RequestMapping("alunos") /* A anotação @RequestMapping permite definir uma rota.
                             Caso não seja informado o método HTTP da rota, ela será
                             definida para todos os métodos.*/
public class AlunoRestController {

    @Autowired
    /* A anotação @ Autowired fornece controle sobre onde e como a
    ligação entre os beans deve ser realizada. Pode ser usado para
    em métodos setter, no construtor, em uma propriedade ou métodos
    com nomes arbitrários e / ou vários argumentos.*/
    private AlunoService alunoService;

    @GetMapping //@GetMapping é uma anotação composta que funciona como um atalho para @RequestMapping.
    public ResponseEntity<Iterable<Aluno>> buscarTodos(){
        return ResponseEntity.ok(alunoService.buscarTodos());
    }

    @GetMapping("/{matricula}") //@GetMapping é uma anotação composta que funciona como um atalho para @RequestMapping.
    public ResponseEntity<Aluno> buscarPorMat(@PathVariable Long matricula) { /* Esta anotação é usada para anotar os argumentos
                                                                                 do método do manipulador de solicitações. A anotação
                                                                                 @RequestMapping pode ser usada para manipular alterações dinâmicas no URI,
                                                                                 onde determinado valor de URI atua como um parâmetro. */
        return ResponseEntity.ok(alunoService.buscarPorMat(matricula));
    }

    @PostMapping //@PostMapping faz parte de um grupo predefinido de anotações compostas que internamente usam @RequestMapping.
    public ResponseEntity<Aluno> inserir(@RequestBody Aluno aluno) { /* @RequestBody: Esta anotação é usada para anotar os argumentos
                                                                        do método do manipulador de solicitações. A anotação @RequestBody indica
                                                                        que um parâmetro de método deve ser associado ao valor do corpo da solicitação HTTP.
                                                                        O HttpMessageConveter é responsável pela conversão da mensagem de solicitação HTTP para o objeto.*/
       alunoService.inserir(aluno);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{matricula}") /* @PutMapping: Essa anotação é usada para mapear solicitações HTTP PUT em métodos manipuladores específicos.
                                   @PutMapping é uma anotação composta que atua como um atalho para @ RequestMapping ( method = RequestMethod.
                                    PUT ) */
    public ResponseEntity<Aluno> atualizar(@PathVariable Long matricula, @RequestBody Aluno aluno) {
        alunoService.atualizar(matricula, aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{matricula}") /* @DeleteMapping: Esta anotação é usada para mapear solicitações HTTP DELETE em métodos manipuladores específicos.
                                      @DeleteMapping é uma anotação composta que atua como um atalho para @ RequestMapping ( method =RequestMethod .
                                      DELETE ) */
    public ResponseEntity<Void> deletar(@PathVariable Long matricula) {
        alunoService.deletar(matricula);
        return ResponseEntity.ok().build();
    }
}
