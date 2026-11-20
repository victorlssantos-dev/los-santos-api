package projeto_1.Los.Santos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "cpf", length = 100, nullable = false, unique = true)
    private String cpf;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    // OneToMany ele faz ter um para muito, então um usuario pode ter vários endereços.
    // O CascadeType.ALL faz com que salvar, atualizar ou excluir o Usuario replique a
    // mesma ação automaticamente em todos os objetos da lista enderecos.
    @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "usuario_id", referencedColumnName = "id"):
    // Informa ao JPA como ligar as tabelas no banco de dados. Ele cria ou utiliza a coluna usuario_id dentro da tabela Endereço,
    // apontando para a coluna id da tabela Usuário.
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefone;
}
