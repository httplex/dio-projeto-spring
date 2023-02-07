package projeto.spring.gof.model;

import javax.persistence.*;

@Entity
/*A anotação @Entity é utilizada para informar que uma classe também é uma entidade.
A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome
no banco de dados, onde os dados de objetos desse tipo poderão ser persistidos.*/
public class Aluno {

    @Id
    /*A anotação @Id é utilizada para informar ao JPA qual campo/atributo de uma
	entidade estará relacionado à chave primária da respectiva tabela no banco de dados.
	Essa é uma anotação obrigatória e um erro será gerado em tempo de execução caso ela
	não esteja presente.*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*Quando declaramos apenas essa anotação(@Id), a responsabilidade de criar o identificador
	será da nossa aplicação, isto é, do nosso código, o que, em muitos casos, não é indicado.
	Para que a geração de ids seja controlada pelo provedor de persistência (Hibernate, por exemplo),
	fazemos uso da anotação @GeneratedValue logo após @Id.*/
    private Long matricula;
    private String nome;

    @ManyToOne //Muitos clientes para um mesmo endereço.
	/*Relação ManyToOne entre entidades: onde uma entidade é referenciada
	com outra entidade que contém valores únicos.
	Em bancos de dados relacionais, esses relacionamentos são aplicáveis
	usando chave estrangeira/chave primária entre as tabelas.*/
    private Endereco endereco;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
