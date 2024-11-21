package apidiasfestivos.apidiasfestivos.aplicacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.stereotype.Service;

import apidiasfestivos.apidiasfestivos.core.interfaces.repositorio.IFestivoRepositorio;
import apidiasfestivos.apidiasfestivos.core.interfaces.servicio.IFestivoServicio;
import apidiasfestivos.apidiasfestivos.dominio.entidades.Festivo;

@Service
public class FestivosServicio implements IFestivoServicio {
    private IFestivoRepositorio repositorio;

    public FestivosServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @Override
    public String EsFestivo(int year, int month, int day) {
        try {
            if (year<0){
                return "la fecha no es valida";
            }
            LocalDate fecha = LocalDate.of(year, month, day);
            boolean esTipo1 = repositorio.esFestivoTipo1(day, month);
            LocalDate domingoDePascua = this.obtenerDomingoDePascua(year);

            if (esTipo1) {
                return "El dia es festivo";
            }

            List<Festivo> festivosLeyPuente = repositorio.obtenerFestivosPorTipo("Ley Puente Festivo");
    
            for (Festivo festivo : festivosLeyPuente) {
                LocalDate fechaFestivo = LocalDate.of(year, festivo.getMes(), festivo.getDia());    
                if (fechaFestivo.getDayOfWeek() == DayOfWeek.MONDAY) {
                    if (fechaFestivo.equals(fecha)) {
                        return "El dia es festivo";
                    }
                } else {
                    LocalDate siguienteLunes = fechaFestivo.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
                    if (siguienteLunes.equals(fecha)) {
                        return "El dia es festivo";
                    }
                }
            }
            List<Festivo> festivosDomingoPascua = repositorio.obtenerFestivosPorTipo("Basado en Pascua");
            for (Festivo festivo : festivosDomingoPascua) {
                LocalDate fechaFestivoDomingoPascua=domingoDePascua.plusDays(festivo.getDiasPascua());
                if (fechaFestivoDomingoPascua.equals(fecha)){

                    return "El dia es festivo";
                }
            }
            List<Festivo> festivosDomingoPascuaYPuenteFestivo = repositorio.obtenerFestivosPorTipo("Basado en Pascua y Ley Puente Festivo");
            for (Festivo festivo : festivosDomingoPascuaYPuenteFestivo) {
                LocalDate fechaFestivoPascuaYLeyPuente = domingoDePascua.plusDays(festivo.getDiasPascua());
                if (fechaFestivoPascuaYLeyPuente.getDayOfWeek()==DayOfWeek.MONDAY){
                    if (fechaFestivoPascuaYLeyPuente.equals(fecha)){
                        return "El dia es festivo";
                    }
                }
                else{
                    LocalDate siguienteLunes = fechaFestivoPascuaYLeyPuente.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
                    if (siguienteLunes.equals(fecha)) {
                        return "El dia es festivo";
                    }
                }
            }
            
            return "No es un dia festivo";
        } catch (DateTimeParseException e) {
            return "La fecha ingresada no es válida. Por favor verifica los valores proporcionados.";
        }
    }
    
    @Override
    public LocalDate obtenerDomingoDePascua(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31; 
        int dia = ((h + l - 7 * m + 114) % 31) + 1;
    
        return LocalDate.of(year, mes, dia);
    }
    

}
