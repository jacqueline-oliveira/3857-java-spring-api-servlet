package br.com.alura.codechella_servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService servico;

    @GetMapping
    public List<EventoDto> obterTodos() {
        return servico.obterTodos();
    }

    @GetMapping("/categoria/{tipo}")
    public List<EventoDto> obterPorTipo(@PathVariable String tipo) {
        return servico.obterPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public Optional<EventoDto> obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/traduzir/{idioma}")
    public String obterTraducao(@PathVariable Long id, @PathVariable String idioma) {
        return servico.obterTraducao(id, idioma);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoDto cadastrar(@RequestBody EventoDto dto) {
        return servico.cadastrar(dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);

    }

    @PutMapping("/{id}")
    public EventoDto alterar(@PathVariable Long id, @RequestBody EventoDto dto){
        return servico.alterar(id, dto);
    }
}
