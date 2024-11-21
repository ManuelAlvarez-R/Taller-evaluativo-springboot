package apidiasfestivos.apidiasfestivos.core.interfaces.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apidiasfestivos.apidiasfestivos.dominio.entidades.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer>{
    @Query("SELECT COUNT(f) > 0 FROM Festivo f WHERE f.tipo.tipos = 'Fijo' AND f.dia = :dia AND f.mes = :mes")
    boolean esFestivoTipo1(@Param("dia") int dia, @Param("mes") int mes);
    
    @Query("SELECT f FROM Festivo f WHERE f.tipo.tipos = :tipoNombre")
    List<Festivo> obtenerFestivosPorTipo(@Param("tipoNombre") String tipoNombre);
    
    
}
