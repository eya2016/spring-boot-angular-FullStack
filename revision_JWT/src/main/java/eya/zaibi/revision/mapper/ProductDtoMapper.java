package eya.zaibi.revision.mapper;

import eya.zaibi.revision.dto.ProductDto;
import eya.zaibi.revision.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {
    public ProductDto ModelToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setMarque(product.getMarque());
        dto.setPrice(product.getPrice());
        dto.setStore(product.getStore().getName());
        return dto;
    }
    public List<ProductDto> ListModelToDto(List<Product> products){
        return products.stream().
                map(item -> ModelToDto(item))
                .collect(Collectors.toList());
    }

}
