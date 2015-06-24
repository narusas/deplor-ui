package net.narusas.tools.deplor.domain.repository;

import java.util.List;

import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByName(String name);

}
