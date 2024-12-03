package model;

import javax.persistence.*;

@Entity
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String especialidad;

    // Horarios separados por comas, por ejemplo: "10:00,11:00"
    private String horariosDisponibles;
    public Psicologo(){

    }

    public Psicologo(Long id, String nombre, String especialidad, String horariosDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horariosDisponibles = horariosDisponibles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public void setHorariosDisponibles(String horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }

    @Override
    public String toString() {
        return "Psicologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", horariosDisponibles='" + horariosDisponibles + '\'' +
                '}';
    }
}


