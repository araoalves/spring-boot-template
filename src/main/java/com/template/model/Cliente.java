package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Geração do ID de clientes")
    private long id;
    
    @Column
    @ApiModelProperty(notes = "Nome do cliente")
    private String nome;
    
    @Column
    @ApiModelProperty(notes = "Telefone do Cliente")
    private String telefone;
}
