package apidiasfestivos.apidiasfestivos.core.interfaces.servicio;

import java.time.LocalDate;


public interface IFestivoServicio {

//metodo de comprobar si es festivo//
public String EsFestivo(int year, int month, int day);
//metodo de obtener el domingo de pascua//
public LocalDate obtenerDomingoDePascua(int year);

}
