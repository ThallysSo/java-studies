package br.com.api.produtos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")

@Builder
public class ProductModel {

    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence"
    )
    private Long id;
    private String nome;
    private String marca;


}
    