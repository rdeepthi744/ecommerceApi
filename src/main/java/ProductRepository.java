import com.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // ✅ Search by name or category (paged)
    Page<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
        String name, String category, Pageable pageable
    );
}
