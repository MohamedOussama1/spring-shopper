package uiass.eia.ecomapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailsCommande {
    @Id
    Long id;
    @OneToOne
    Commande commande;
    String nomProduit;
    int qte;

}
