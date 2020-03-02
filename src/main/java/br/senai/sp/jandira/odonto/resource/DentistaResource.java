package br.senai.sp.jandira.odonto.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.odonto.model.Dentista;
import br.senai.sp.jandira.odonto.repository.DentistaRepository;

//É um controller como no MVC 
@RestController //Explicando ao spring q ele (DentistaResource) é o controller
@RequestMapping("/odonto")//o que for colocado ele será executado, e sempre deve ser no plural mesmo tendo só 1
public class DentistaResource {
	
	@Autowired //
	private DentistaRepository dentistaRepository;
	
	//método para uma lista de dentistas
	@GetMapping("/dentistas") //ele retornará um json para o fornt pois o spring já faz isso //ele é o caminho e ele executa o metodo da linha abaixo
	public List<Dentista> getDentistas() {
		
		//retorna o array (lista) de desntistas (todos)
		return dentistaRepository.findAll();
	}
	
	@GetMapping("/dentistas/{codigo}") // o codigo deve ser passado como parametro dentro do getDentista() para q o java identifique o id do dado
	public ResponseEntity<?> getDentista(@PathVariable Long codigo) { // ele está iniciando nulo (objeto vazio) //@PathVariable ele manda seguir o url do banco para pegar o valor do id dos dados
		Optional<?> dentistaProcurado = dentistaRepository.findById(codigo);
		return dentistaProcurado.isPresent() ?
				ResponseEntity.ok(dentistaProcurado.get()) : 
				ResponseEntity.notFound().build(); 			//return dentistaRepository.findById(codigo).get();
	}
	
	@PostMapping("/dentistas")
	@ResponseStatus(HttpStatus.CREATED)
	public Dentista gravar(@Valid @RequestBody Dentista dentista) { // @Valid ele garante se o q foi digitado esta de acorodo com os padrões necessários
		return dentistaRepository.save(dentista);					// EX:(cadastrar min 3 e max 100 caso n seja asism ele anotará o erro)
	}
	
	@DeleteMapping("/dentistas/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void excluir(@PathVariable Long codigo) {
		dentistaRepository.deleteById(codigo);
	}
	
	@PutMapping("/dentistas")
	public void atualizar(@Valid @RequestBody Dentista dentista) {
		dentistaRepository.save(dentista);
	}
}
