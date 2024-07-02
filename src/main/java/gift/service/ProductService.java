package gift.service;

import gift.entity.Product;
import gift.entity.ProductDao;
import gift.exception.KakaoNameException;
import gift.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product addProduct(Product product) {
        if (product.name.contains("카카오")) {
            throw new KakaoNameException();
        }
        productDao.insertProduct(product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        if (product.name.contains("카카오")) {
            throw new KakaoNameException();
        }
        Product existingProduct = productDao.selectProduct(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException(id);
        }
        Product updatedProduct = new Product(id, product.name, product.price, product.imageUrl);
        productDao.updateProduct(updatedProduct);
        return updatedProduct;
    }

    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public boolean deleteProduct(Long id) {
        Product product = productDao.selectProduct(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productDao.deleteProduct(id);
        return true;
    }
}
