package com.control_2_tbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tarea")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String titulo;

    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vencimiento;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

}
