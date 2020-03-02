package br.senai.sp.jandira.odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.jandira.odonto.model.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
	//é um banco de dados no contexto q estamos fazendo <generic> a classe é dentista e o indentificador é o Long
	//Ele faz crud insert lá dentro, ele identifica em qual tabela deve ser colocado o dado q for inserido
	
	Dentista findByNome(String nome);

}
