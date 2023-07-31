package com.bci.userbci.commons.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ErrorHttp {

    private int status;
    private LocalDateTime fecha;
    private List<DetailError> errores;
    private String ruta;

    public ErrorHttp(int status, LocalDateTime fecha, List<DetailError> errores, String ruta){
        this.status = status;
        this.fecha = fecha;
        this.errores = errores;
        this.ruta = ruta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<DetailError> getErrores() {
        return errores;
    }

    public void setErrores(List<DetailError> errores) {
        this.errores = errores;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorHttp errorHttp = (ErrorHttp) o;
        return status == errorHttp.status && Objects.equals(fecha, errorHttp.fecha) && Objects.equals(errores, errorHttp.errores) && Objects.equals(ruta, errorHttp.ruta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, fecha, errores, ruta);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorHttp{");
        sb.append("status=").append(status);
        sb.append(", fecha=").append(fecha);
        sb.append(", errores=").append(errores);
        sb.append(", ruta='").append(ruta).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
