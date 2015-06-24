package net.narusas.tools.deplor.domain.repository;

import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	@Cacheable("resources")
	Resource findByPathAndRepository(String path, Repository repository);
}
