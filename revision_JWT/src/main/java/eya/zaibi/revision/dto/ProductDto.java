package eya.zaibi.revision.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String marque;
    private float price;
    private String store;
}
