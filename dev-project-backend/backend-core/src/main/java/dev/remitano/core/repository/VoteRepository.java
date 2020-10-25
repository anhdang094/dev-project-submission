package dev.remitano.core.repository;

import dev.remitano.core.models.Video;
import dev.remitano.core.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
