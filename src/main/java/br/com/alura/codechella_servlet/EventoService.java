package br.com.alura.codechella_servlet;

import com.deepl.api.DeepLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;

    public List<EventoDto> obterTodos() {
        return repositorio.findAll().stream()
                .map(EventoDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EventoDto> obterPorId(Long id) {
        Optional<Evento> evento = repositorio.findById(id);
        if (evento.isPresent()) {
            return Optional.of(EventoDto.toDto(evento.get()));
        }
        return Optional.empty();
    }

    public EventoDto cadastrar(EventoDto dto) {
        Evento evento = repositorio.save(dto.toEntity());
        return EventoDto.toDto(evento);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }

    public EventoDto alterar(Long id, EventoDto dto) {
        Optional<Evento> evento = repositorio.findById(id);
        if (evento.isPresent()) {
            EventoDto eventoDto = new EventoDto(id, dto.tipo(), dto.nome(),
                    dto.data(), dto.descricao());
            return EventoDto.toDto(repositorio.save(eventoDto.toEntity()));
        }
        return null;
    }

    public List<EventoDto> obterPorTipo(String tipo) {
        TipoEvento tipoEvento = TipoEvento.valueOf(tipo.toUpperCase());
        return repositorio.findByTipo(tipoEvento).stream()
                .map(EventoDto::toDto)
                .collect(Collectors.toList());
    }

    public String obterTraducao(Long id, String idioma) {
        String texto = null;
        Optional<Evento> evento = repositorio.findById(id);
        if (evento.isPresent()) {
            try {
                texto = TraducaoDeTextos.obterTraducao(evento.get().getDescricao(), idioma);
            } catch (DeepLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return texto;
    }
}
