package br.senai.sp.jandira.odonto.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_dentista") //ele procurará uma tabela com esse nome caso ele n achar ele cria uma table com esse nome
public class Dentista {

	@Id //ele só funciona tendo o comentario com "@" encima do codigo q será efeito
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment do banco de dados.
	private Long codigo;  //auto_increment e tbm é o id pois o "@id" está encima dele
	
	@NotNull // define que o nome não pode ser nulo ou seja n pode estar vazio
	@Size(min = 3, max = 100, message = "O nome deve conter entre 3 há 100 caracteres") // definindo quantos caracteres no minino e o maximo vc pode cadastrar no BD
	private String nome;
	
	private String cro;
	private String email;
	private String telefone;
//	Lista de especialidades ou seja, ele vai pegar as especialidades q estiverem na classe "Especialidade"
//	@JoinTable Nome da tabela q fará os relacionamentos de desntista e Especialidade
//	@ManyToMany - é uma asociação q liga duas entidades q seria dentista e especialidade, pois ela é a tabela link
//	JoinColunms - relacionar os campos de cada tabela do banco
//	inverseJoinColumns - A outra tabela (seria o inner join q irá relacionar as tabelas) ele pede com qual campo será relacionado
//	@JoinColumn - coloca o nome do campo e muda para especificar e relacionar com o codigo da tabela
//	referencedColumnName - pede a referencia do campo da tabela
	@ManyToMany
	@JoinTable(name = "tbl_dentista_especialidade", joinColumns = @JoinColumn(name = "dentista_codigo", referencedColumnName = "codigo"),
	inverseJoinColumns = @JoinColumn(name = "especialidade_codigo", referencedColumnName = "codigo"))
	private List<Especialidade> especialidades; 
	
//	GETTERS E SETTERS

	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	

//	toString formato json

	@Override
	public String toString() {
		return "Dentista [codigo=" + codigo + ", nome=" + nome + ", cro=" + cro + ", email=" + email + ", telefone="
				+ telefone + "]";
	}

}
