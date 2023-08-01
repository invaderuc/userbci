package com.bci.userbci.commons.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailError {

    private String objeto;
    private String atributo;
    private String valorRecibido;
    private String mensaje;

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailError that = (DetailError) o;
        return Objects.equals(objeto, that.objeto) && Objects.equals(atributo, that.atributo) && Objects.equals(valorRecibido, that.valorRecibido) && Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objeto, atributo, valorRecibido, mensaje);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetalleError{");
        sb.append("objeto='").append(objeto).append('\'');
        sb.append(", atributo='").append(atributo).append('\'');
        sb.append(", valorRecibido='").append(valorRecibido).append('\'');
        sb.append(", mensaje='").append(mensaje).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static List<DetailError> mapearError(List<FieldError> fieldErrors){
        List<DetailError> detailsErrores = new ArrayList<>();

        for (FieldError error: fieldErrors){
            DetailError detail = new DetailError();
            detail.setAtributo(error.getField());
            detail.setObjeto(error.getObjectName());
            detail.setValorRecibido(error.getRejectedValue().toString());
            detail.setMensaje(error.getDefaultMessage());

            detailsErrores.add(detail);
        }
        return detailsErrores;
    }

    public static List<DetailError> mapKeyError(DataIntegrityViolationException ex){

        List<DetailError> detailsErrores = new ArrayList<>();
        DetailError detail = new DetailError();
        String valMessage = extractValueMessage(ex.getMessage().toString());
        detail.setValorRecibido(valMessage);
        detail.setMensaje(valMessage + " ya registrado");

        detailsErrores.add(detail);

        return detailsErrores;
    }

    public static List<DetailError> mapFoundError(){

        List<DetailError> detailsErrores = new ArrayList<>();
        DetailError detail = new DetailError();
        detail.setMensaje("No existe");

        detailsErrores.add(detail);

        return detailsErrores;
    }

    private static String extractValueMessage(String input) {
        if (input == null) {
            return null;
        }

        int startIndex = input.indexOf('\''); //first single quote.
        if (startIndex == -1) {
        }

        int endIndex = input.indexOf('\'', startIndex + 1); // Second quote.
        if (endIndex == -1) {
            return null;
        }
        // Extract
        return input.substring(startIndex + 1, endIndex);
    }
}
