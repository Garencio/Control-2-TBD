package com.control_2_tbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaEntity {
    private int id;
    private String titulo;
    private String descripcion;
    private Date vencimiento;
    private String estado;
    private int id_usuario; // Reemplaza la relación @ManyToOne por un campo simple

}




