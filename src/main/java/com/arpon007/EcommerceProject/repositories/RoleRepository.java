package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.AppRole;
import com.arpon007.EcommerceProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}
