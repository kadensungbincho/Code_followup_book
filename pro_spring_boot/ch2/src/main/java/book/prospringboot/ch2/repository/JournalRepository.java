package book.prospringboot.ch2.repository;

import book.prospringboot.ch2.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
