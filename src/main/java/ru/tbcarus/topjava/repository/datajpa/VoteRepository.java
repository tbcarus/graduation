package ru.tbcarus.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.topjava.model.Restaurant;
import ru.tbcarus.topjava.model.Vote;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id = :userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId ORDER BY v.date DESC")
    List<Vote> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v ORDER BY v.date, v.id DESC")
    List<Vote> getAll();

    @Query("SELECT v FROM Vote v WHERE v.date = :date ORDER BY v.restaurant.name ASC" )
    List<Vote> getAllByDate(@Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.date = :date AND v.user.id = :userId")
    Vote getByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Vote v SET v.restaurant = :restaurant where v.id = :voteId")
    void update(@Param("restaurant") Restaurant restaurant, @Param("voteId") int voteId);
}
