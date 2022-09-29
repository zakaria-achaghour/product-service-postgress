package com.product.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    @NotEmpty
    @Size(min = 2)
    private String name;
    @NotEmpty
    @NotNull
    private UUID category_id;
}
