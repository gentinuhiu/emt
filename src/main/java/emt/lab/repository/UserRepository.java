package emt.lab.repository;

import emt.lab.model.domain.User;
import emt.lab.model.enumeration.ROLE;
import emt.lab.model.projection.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"cart", "cart.rented"}) // 💡 NO cart.review here!
    List<User> findAll();

    @EntityGraph(attributePaths = {"cart", "cart.rented"})
    Optional<User> findByUsernameAndPassword(String username, String password);

    @EntityGraph(attributePaths = {"cart", "cart.rented"})
    Optional<User> findByUsername(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts"}
    )
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"carts"}
    )
    @Query("select u from User u")
    List<User> loadAll();

    UserProjection findByRole(ROLE role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}


