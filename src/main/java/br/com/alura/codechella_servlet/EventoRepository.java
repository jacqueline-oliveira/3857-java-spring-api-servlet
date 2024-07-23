package br.com.alura.codechella_servlet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByTipo(TipoEvento tipoEvento);
}
