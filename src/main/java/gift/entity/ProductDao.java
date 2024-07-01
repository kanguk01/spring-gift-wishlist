package gift.entity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProductTable() {
        var sql = """
            create table product (
              id bigint auto_increment,
              name varchar(255),
              price int,
              image_url varchar(255),
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public Long insertProduct(Product product) {
        var sql = "insert into product (name, price, image_url) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.name);
            ps.setInt(2, product.price);
            ps.setString(3, product.imageUrl);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Product selectProduct(Long id) {
        var sql = "select id, name, price, image_url from product where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("image_url")
                ),
                id
        );
    }

    public List<Product> selectAllProducts() {
        var sql = "select id, name, price, image_url from product";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("image_url")
                )
        );
    }

    public void updateProduct(Product product) {
        var sql = "update product set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(sql, product.name, product.price, product.imageUrl, product.id);
    }

    public void deleteProduct(Long id) {
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }
}