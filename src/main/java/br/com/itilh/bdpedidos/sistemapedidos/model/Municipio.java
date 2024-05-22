package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Municipio {

    private BigInteger id;
    private String nome;
    private Boolean entrega;
    private Estado estado;

}
