package apidiasfestivos.apidiasfestivos.core.interfaces.servicio;

import java.time.LocalDate;
import java.util.List;

import apidiasfestivos.apidiasfestivos.dominio.entidades.Festivo;

public interface IFestivoServicio {
public List<Festivo> listar();

//metodo de comprobar si es festivo//
public String EsFestivo(int year, int month, int day);
//metodo de obtener el domingo de pascua//
public LocalDate obtenerDomingoDePascua(int year);

}
